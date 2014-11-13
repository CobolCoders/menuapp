package fi.menuapp;

import java.math.BigDecimal;

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
import android.widget.TextView;
import fi.menuapp.component.handler.ComponentUtils;
import fi.menuapp.component.handler.ListViewProcessor;
import fi.menuapp.contract.ProductContract;
import fi.menuapp.contract.ProductContract.Product;
import fi.menuapp.intent.ProductIntent;
import fi.menuapp.intent.ProductsIntent;

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
	
	private BigDecimal totalPrice;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
				setupNumberPickerForEachRow(view);
			}

			private void setupNumberPickerForEachRow(View view) {
				NumberPicker productCount = ComponentUtils.getNumberPricker(view);
				productCount.setMinValue(0);
				productCount.setMaxValue(10);
				productCount.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
					@Override
					public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
						// change total price
						totalPrice = BigDecimal.ZERO;
						
						ListViewProcessor totalPriceProcessor = new ListViewProcessor(menu) {
							@Override
							protected void handleNumberPicker(NumberPicker np, int index) {
								super.handleNumberPicker(np, index);
								Cursor c = getCursorByIndex(index);
								BigDecimal priceOfItem = new BigDecimal(c.getDouble(2));
								BigDecimal totalPriceForItem = priceOfItem.multiply(new BigDecimal(np.getValue()));
								totalPrice = totalPrice.add(totalPriceForItem);
							}
						};
						
						totalPriceProcessor.loopNumberPickers();
						
						TextView totalPriceText = (TextView)findViewById(R.id.totalPriceText);
						StringBuilder sb = new StringBuilder();
						sb.append("Total price: ");
						sb.append(totalPrice.toString());
						totalPriceText.setText(sb.toString());
					}
				});
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
    private void addExtras(final Intent intent) {
		final ProductsIntent products = new ProductsIntent();
    	
    	ListViewProcessor extraProcessor = new ListViewProcessor(menu) {
    		@Override
    		protected void handleNumberPicker(NumberPicker np, int index) {
    			super.handleNumberPicker(np, index);
    			if (np.getValue() > 0) {
        			Cursor c = getCursorByIndex(index);
        			ProductIntent productIntent = new ProductIntent();
        			productIntent.setProductId(c.getInt(0));
        			productIntent.setProductName(c.getString(1));
        			productIntent.setProductPrice(c.getDouble(2));
        			productIntent.setCount(np.getValue());
        			products.getProducts().add(productIntent);
        		}
    			intent.putExtra("products", products);
    		}
    	};
    	
    	extraProcessor.loopNumberPickers();
    }
    
	private Cursor getCursorByIndex(int index) {
		// perhaps questionable
		return (Cursor)menu.getItemAtPosition(index);
	}
}
