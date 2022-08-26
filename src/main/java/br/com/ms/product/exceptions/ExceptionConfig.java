package br.com.ms.product.exceptions;


public class ExceptionConfig {
	
	private int error_code;
	private String message;
	
	public ExceptionConfig(int error_code, String message) {
		this.error_code = error_code;
		this.message = message;
	}

	public int getError_code() {
		return error_code;
	}

	public void setErrorcode(int error_code) {
		this.error_code = error_code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	

}
