package files;

//*****************************************************************************
// class Text
//
//An abstract class which can hold a string text and a string result, serves
//as an interface of subclasses of Text objects.
//*****************************************************************************

abstract public class Text{
	String text, result;
	
	abstract public String getText() ;
	abstract public String getResult() ;
	
}
