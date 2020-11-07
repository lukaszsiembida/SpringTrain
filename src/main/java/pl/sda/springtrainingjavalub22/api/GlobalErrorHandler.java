package pl.sda.springtrainingjavalub22.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.sda.springtrainingjavalub22.exception.AlreadyExistException;

import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;
import java.util.UUID;

@Order(1)
@ControllerAdvice // element programowania aspektowego
public class GlobalErrorHandler {


    @ResponseBody // odpowiedzi z metod nie będą responsowane
    @ExceptionHandler(value = AlreadyExistException.class) // tablica klas wyjątów do obsługi
    public ResponseEntity<Error> handleAlreadyExist(AlreadyExistException ex){
        String errorCode = UUID.randomUUID().toString();
        System.out.println("Error code " + errorCode);
        ex.printStackTrace();
        return ResponseEntity.status(409).body(new Error(ex.getMessage(), LocalDateTime.now(), errorCode));
    }

    @ResponseBody // odpowiedzi z metod nie będą responsowane
    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<Error> handleAnyAlreadyExist(RuntimeException ex){
        String errorCode = UUID.randomUUID().toString();
        System.out.println("Error code " + errorCode);
        ex.printStackTrace();
        return ResponseEntity.status(500).body(new Error(ex.getMessage(), LocalDateTime.now(), errorCode));
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    public String handleAccessDenied(){
        return "redirect:/mvc/login";
    }

    @AllArgsConstructor
    @Getter
    static class Error {
        private final String message;
        private final LocalDateTime errorTime;
        private final String errorCode;
    }

}
