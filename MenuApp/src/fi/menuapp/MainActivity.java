package fi.menuapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import fi.menuapp.database.MenuDbHelper;

public class MainActivity extends ActionBarActivity {
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // db query test -> haut siirtyy domain-kerrokseen
        MenuDbHelper menuDb = new MenuDbHelper(getBaseContext());
        SQLiteDatabase db =  menuDb.getWritableDatabase();
        
        // select * from products
        Cursor c = db.query("products", null, null, null, null, null, null);
        
        while (c.moveToNext()) {
        	System.err.println(c.getPosition());
        	System.err.println(c.getString(0) + ", " + c.getString(1));
        }
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
