package fi.menuapp.database.contract;

import android.provider.BaseColumns;

/**
 * 
 * @author antti
 *
 */
public class MenuContract {

	public MenuContract() {}
	
	public static abstract class Product implements BaseColumns {
		public static final String TABLE_NAME = "products";
		public static final String COLUMN_NAME_ID = "productId";
		public static final String COLUMN_NAME_PRODUCT_NAME = "productName";
		public static final String COLUMN_NAME_PRODUCT_PRICE = "productPrice";
	}
	
	public static abstract class Order implements BaseColumns {
		public static final String TABLE_NAME = "orders";
		public static final String COLUMN_NAME_ID = "orderTimeStamp";
	}
	
	public static abstract class ProductsOfOrder implements BaseColumns {
		public static final String TABLE_NAME = "products_orders";
		public static final String COLUMN_NAME_ORDER_ID = "orderTimeStamp";
		public static final String COLUMN_NAME_PRODUCT_ID = "productId";
	}
}
