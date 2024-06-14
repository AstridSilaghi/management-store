package mgmt.store.exceptions;

public class RequestParameterNotProvidedException extends RuntimeException{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RequestParameterNotProvidedException(String paramName) {
		super("Request parameter " + paramName + " is missing.");
	}
	
}
