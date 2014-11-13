package fi.menuapp.intent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ProductsIntent implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private List<ProductIntent> products;

	public ProductsIntent() {
		products = new ArrayList<ProductIntent>();
	}
	
	public List<ProductIntent> getProducts() {
		return products;
	}

	public void setProducts(List<ProductIntent> products) {
		this.products = products;
	}
}
