package fi.menuapp.contract;

import android.net.Uri;
import android.provider.BaseColumns;

public class OrderContract {
	public static final String AUTHORITY = "fi.menuapp.contract.MenuProvider";
	public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/orders");
	public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.menuapp.orders";
	public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.menuapp.orders";
	
	public static abstract class Order implements BaseColumns {
		public static final String TABLE_NAME = "order";
		public static final String TABLE_NAME_TIMESTAMP = "timestamp";
	}

}
