package fi.menuapp.contract;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MenuDbHelper extends SQLiteOpenHelper {

	private static int DATABASE_VERSION = 1;
	private static String DATABASE_NAME = "Menu.db";
	
	public MenuDbHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override 
	public void onConfigure(SQLiteDatabase db) {
		super.onConfigure(db);
		db.setForeignKeyConstraintsEnabled(true);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) { 
		db.execSQL(createTables());
		insertTestData(db); 
	}

	@Override 
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL(dropTables());
		onCreate(db);
	}

	private static String createTables() {
		/*return "create table " + Product.TABLE_NAME + "(" +
				Product._ID + " integer primary key," +
				Product.COLUMN_NAME_PRODUCT_NAME + " text," +
				Product.COLUMN_NAME_PRODUCT_PRICE + " numeric);" +
				"create table " + Order.TABLE_NAME + "("  +
				Order._ID + " integer primary key," + 
				Order.COLUMN_NAME_TIMESTAMP + " timestamp);" +
				"create table " + ProductsOfOrder.TABLE_NAME + "(" +
				"ProductsOfOrder.PRODUCT_ID integer," + 
				"ProductsOfOrder.ORDER_ID integer," +
				"ProductsOfOrder.AMOUNT integer," +
				"foreign key product_id references products(_id)," +
				"foreign key order_id references orders(_id));";*/
		return "create table " + fi.menuapp.contract.ProductContract.Product.TABLE_NAME + " (" +
				fi.menuapp.contract.ProductContract.Product._ID + " integer primary key," +
				fi.menuapp.contract.ProductContract.Product.COLUMN_NAME_PRODUCT_NAME + " text," + 
				fi.menuapp.contract.ProductContract.Product.COLUMN_NAME_PRODUCT_PRICE + " numeric);";
	}
	
	private static String dropTables() {
		 /*return "drop table if exists productsOfOrder," +
				"drop table if exists orders," +
				"drop table if exists products;";*/
		return "drop table if exists " + fi.menuapp.contract.ProductContract.Product.TABLE_NAME;
	}
	
	private void insertTestData(SQLiteDatabase db) {
		db.execSQL("insert into " + fi.menuapp.contract.ProductContract.Product.TABLE_NAME 
				+ " values(100, 'Koko illan oluet', '49.10');");
		db.execSQL("insert into " + fi.menuapp.contract.ProductContract.Product.TABLE_NAME 
				+ " values(101, 'Karhun jänteitä jäillä', '29.10');");
	}
}
