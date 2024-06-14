package mgmt.store.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RespExceptionHandler extends ResponseEntityExceptionHandler {

	
	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<Object> handleProductNotFoundEx(Exception ex) {
		return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(NumberFormatException.class)
	public ResponseEntity<Object> handleWrongBodyFormat(Exception ex) {
		return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}


}
