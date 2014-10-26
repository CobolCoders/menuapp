package fi.menuapp.contract;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class MenuProvider extends ContentProvider {

	private MenuDbHelper dbHelper;
	private static final UriMatcher sUriMatcher = new UriMatcher(TRIM_MEMORY_COMPLETE);
	static {
		sUriMatcher.addURI("fi.menuapp.contract.MenuProvider", "products", 1);	
	}
	
	@Override
	public boolean onCreate() {
		Context c = getContext();
		dbHelper = new MenuDbHelper(c);
		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		Cursor c = null;
		
		switch (sUriMatcher.match(uri)) {
		case 1:
			// haetaan kaikki tuotteet
			c = db.query("products", null, null, null, null, null, null);
		}
		
		return c;
	}

	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}
}
