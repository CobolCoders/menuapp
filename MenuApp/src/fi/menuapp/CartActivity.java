package fi.menuapp;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import fi.menuapp.intent.ProductIntent;
import fi.menuapp.intent.ProductsIntent;

public class CartActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cart);
		Intent intent = getIntent();
		ProductsIntent products = (ProductsIntent)intent.getExtras().get("products");
		ListView listView1 = (ListView)findViewById(R.id.listViewCart);
		List<ProductIntent> productList = products.getProducts();
		double totalSum = 0;
		
		for(int i = 0; i < productList.size(); i++) {
			ProductIntent product = productList.get(i);
			totalSum = totalSum + (product.getCount() * product.getProductPrice());
		}
		ArrayAdapter<ProductIntent> adapter = new ArrayAdapter<ProductIntent>(this, android.R.layout.simple_list_item_1, productList);
		listView1.setAdapter(adapter);
		TextView totalPriceText = (TextView)findViewById(R.id.totalPriceTextCart);
		String total = String.valueOf(totalSum);
		totalPriceText.setText(total);
		// Update totalSum;
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.cart, menu);
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
		
		if(id == R.id.toMenu) {
			
			Intent intent = new Intent(this, MainActivity.class);
			startActivity(intent);
		}
		
		if(id == R.id.toHistory) {
			Intent intent = new Intent(this, OrderHistoryActivity.class);
			startActivity(intent);
		}
		
		return super.onOptionsItemSelected(item);
	}
}
