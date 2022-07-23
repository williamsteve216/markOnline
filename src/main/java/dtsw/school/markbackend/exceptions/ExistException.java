package dtsw.school.markbackend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ExistException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    String classeNameTarget;
    String classeNameSource;

    public ExistException(String classeNameTarget, String classeNameSource){
        super(String.format("this %s already exist in this %s",classeNameTarget,classeNameSource));
    }
}
