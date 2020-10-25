package software.tinlion.pertwee.exception;

public class RequiredElementNotPresentException extends RuntimeException {

    private static final long serialVersionUID = 1296886957816631357L;
    
    public RequiredElementNotPresentException(String message) {
        
        super(message);
    }

}
