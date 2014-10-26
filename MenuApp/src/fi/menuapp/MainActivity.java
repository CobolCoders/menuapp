package fi.menuapp;

import android.app.LoaderManager.LoaderCallbacks;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
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
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createListViewMenu();
    }

    private void createListViewMenu() {
    	String[] fromColumns = {Product.COLUMN_NAME_PRODUCT_NAME};
    	int[] toFields = {android.R.id.text1};
    	
		ListView menu = (ListView)findViewById(R.id.listViewMenu);
		menu.setAdapter(productAdapter = new SimpleCursorAdapter(this, 
				android.R.layout.simple_list_item_1, // tähän tehtävä oma list item layout 
				null, 
				fromColumns, 
				toFields, 
				0));
		
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
			//CartActivity puuttuu versionhallinnasta
			
			//Intent intent = new Intent(this, CartActivity.class);
			//startActivity(intent);
		}
		return super.onOptionsItemSelected(item);
	}
    
}
