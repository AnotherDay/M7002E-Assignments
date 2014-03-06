package assignment4.exceptions;

public class NoObjectFoundException extends Exception {

	public NoObjectFoundException() {
	}

	public NoObjectFoundException(String message) {
		super(message);
	}

	public NoObjectFoundException(Throwable cause) {
		super(cause);
	}

	public NoObjectFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoObjectFoundException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
