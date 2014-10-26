package fi.menuapp.contract;

import android.net.Uri;
import android.provider.BaseColumns;

public class ProductContract {
	public static final String AUTHORITY = "fi.menuapp.contract.MenuProvider";
	public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/products");
	public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.menuapp.products";
	public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.menuapp.products";
	
	public static abstract class Product implements BaseColumns {
		public static final String TABLE_NAME = "products";
		public static final String COLUMN_NAME_PRODUCT_NAME = "productName";
		public static final String COLUMN_NAME_PRODUCT_PRICE = "productPrice";
	}
}
