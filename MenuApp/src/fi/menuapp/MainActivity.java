package fi.menuapp;

import java.util.ArrayList;
import java.util.List;

import android.app.LoaderManager.LoaderCallbacks;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.SimpleCursorAdapter;
import fi.menuapp.contract.ProductContract;
import fi.menuapp.contract.ProductContract.Product;

public class MainActivity extends ActionBarActivity implements LoaderCallbacks<Cursor> {
	
	private static String[] PROJECTION = new String[] {
		Product._ID,
		Product.COLUMN_NAME_PRODUCT_NAME,
		Product.COLUMN_NAME_PRODUCT_PRICE
	};
	
	private static String SELECTION = "(" +
			Product.COLUMN_NAME_PRODUCT_NAME + " NOT NULL)";
	
	private SimpleCursorAdapter productAdapter;
	
	private ListView menu;
	private List<NumberPicker> numberPickers;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        numberPickers = new ArrayList<NumberPicker>();
        createListViewMenu();
    }

    private void createListViewMenu() {
    	String[] fromColumns = {Product.COLUMN_NAME_PRODUCT_NAME, 
    			Product.COLUMN_NAME_PRODUCT_PRICE};
    	int[] toFields = {R.id.productName, 
    			R.id.productPrice};
    	
    	menu = (ListView)findViewById(R.id.listViewMenu);
		
		menu.setAdapter(productAdapter = new SimpleCursorAdapter(this, 
				R.layout.menu_list_item, 
				null, 
				fromColumns, 
				toFields, 
				0) {
			
			@Override
			public void bindView(View view, Context context, Cursor cursor) {
				super.bindView(view, context, cursor);
				
				NumberPicker productCount = (NumberPicker)view.findViewById(R.id.count);
				productCount.setMinValue(0);
				productCount.setMaxValue(10);
				
				// using arraylist to access the numberpickers. maybe bad idea?
				if (!numberPickers.contains(productCount)) {
					numberPickers.add(productCount);
				}
			}
			
		});
		
		getLoaderManager().initLoader(0, null, this);
	}
    
    @Override
	public Loader<Cursor> onCreateLoader(int id, Bundle args) {
		return new CursorLoader(this, ProductContract.CONTENT_URI, PROJECTION, SELECTION, null, null);
	}

	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
		productAdapter.swapCursor(data);
	}

	@Override
	public void onLoaderReset(Loader<Cursor> loader) {
		productAdapter.swapCursor(null);
	}	
    

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    @Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		if(id == R.id.toCart) {
			
			Intent intent = new Intent(this, CartActivity.class);		
			addExtras(intent);
			startActivity(intent);
		}
		if(id == R.id.toHistory) {
			Intent intent = new Intent(this, OrderHistoryActivity.class);		
			addExtras(intent);
			startActivity(intent);
		}
		return super.onOptionsItemSelected(item);
	}
    
    // add products with > 0 count to extras
    private void addExtras(Intent intent) {
    	for (int i = 0; i < numberPickers.size(); i++) {
    		NumberPicker np = numberPickers.get(i);
    		if (np.getValue() > 0) {
    			Cursor c = (Cursor)menu.getItemAtPosition(i);
    			intent.putExtra(appendIndex(Product._ID, i), c.getInt(0));
    			intent.putExtra(appendIndex(Product.COLUMN_NAME_PRODUCT_NAME, i), c.getString(1));
    			intent.putExtra(appendIndex(Product.COLUMN_NAME_PRODUCT_PRICE, i), c.getDouble(2));
    			intent.putExtra(appendIndex("productCount", i), np.getValue());
    		}
    	}
    }
    
    private String appendIndex(String key, int index) {
    	StringBuilder sb = new StringBuilder();
    	sb.append(key);
    	sb.append(".");
    	sb.append(index);
    	return sb.toString();
    }
}
