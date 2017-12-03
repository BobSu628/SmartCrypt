package files;
//*******************************************************************************
// class TextInvalidException:
//
//The class for the custom exception Text Invalid Exception.
//*******************************************************************************
public class TextInvalidException extends Exception {
	
	private static final long serialVersionUID = 1L;
	//***************************************************************************
	// TextInvalidException(String mes):
	//
	//Constructs a new TextInvalidException with the specified detail message.
	//***************************************************************************
	public TextInvalidException(String mes) {
		super(mes);
	}
	//***************************************************************************
	// TextInvalidException(String mes, Throwable cause):
	//
	//Constructs a new exception with the specified detail message and cause.
	//***************************************************************************
	public TextInvalidException(String mes, Throwable cause){
		super(mes, cause);
	}
}
