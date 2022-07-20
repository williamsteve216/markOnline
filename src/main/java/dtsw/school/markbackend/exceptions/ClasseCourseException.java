package dtsw.school.markbackend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ClasseCourseException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ClasseCourseException() {
        super(String.format("This course alredy exist in this classe"));
    }
}
