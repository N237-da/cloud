package PFE.CDSIR_AGENCY.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception levée lorsqu'un colis n'est pas trouvé dans le système
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ColisNotFoundException extends RuntimeException {

    public ColisNotFoundException() {
        super("Colis non trouvé");
    }

    public ColisNotFoundException(String message) {
        super(message);
    }

    public ColisNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ColisNotFoundException(Throwable cause) {
        super(cause);
    }

    public ColisNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}