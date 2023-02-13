package exception;

public class FileProcessingException extends RuntimeException{
    public FileProcessingException() {super("Error while reading file");
    }

    public FileProcessingException(String message) {
        super(message);
    }

    public FileProcessingException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileProcessingException(Throwable cause) {
        super(cause);
    }

    public FileProcessingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

