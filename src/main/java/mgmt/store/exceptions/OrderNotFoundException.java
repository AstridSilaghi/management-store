package mgmt.store.exceptions;

public class OrderNotFoundException extends RuntimeException{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OrderNotFoundException(Long id) {
		super("Product with id: " + id +" does not exist");
	}
	
	public OrderNotFoundException(String number) {
		super("Product with number: " + number +" does not exist");
	}
	
}
