package mgmt.store.exceptions;

public class ProductOutOfStock extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ProductOutOfStock(Long id) {
		super("Product with id: " + id +" is out of stock and can not be added to order");
	}
	
}

