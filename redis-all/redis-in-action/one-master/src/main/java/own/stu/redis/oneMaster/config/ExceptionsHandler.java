package own.stu.redis.oneMaster.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(RuntimeException.class)
    public String runtimeException(RuntimeException e) {
        return "fail cause by " + e.getMessage();
    }
}
