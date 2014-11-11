package fi.menuapp.contract;

import android.net.Uri;
import android.provider.BaseColumns;

public class ProductsOfOrderContract {
	public static final String AUTHORITY = "fi.menuapp.contract.MenuProvider";
	public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/productsoforder");
	public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.menuapp.productsoforder";
	public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.menuapp.productsoforder";
	
	public static abstract class OrderItem implements BaseColumns {
		public static final String TABLE_NAME = "productsoforder";
		public static final String COLUMN_NAME_PRODUCT_ID = "productid";
		public static final String COLUMN_NAME_ORDER_ID = "orderid";
		public static final String COLUMN_NAME_AMOUNT = "amount";
	}

}
