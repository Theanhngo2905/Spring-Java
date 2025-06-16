package com.devteria.indentity_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException e) {
//        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        return ResponseEntity.badRequest().body(e.getMessage());
    }

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
//        return ResponseEntity.badRequest().body(e.getMessage());
//    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        // 1. Lấy BindingResult từ ngoại lệ
        // BindingResult chứa tất cả các lỗi validation
        // (bao gồm FieldError cho các lỗi trên trường cụ thể)
        List<String> errors = e.getBindingResult()
                .getFieldErrors() // Lấy danh sách các lỗi trên từng trường
                .stream()         // Chuyển sang Stream để dễ xử lý
                .map(error -> error.getDefaultMessage()) // Lấy thông báo lỗi mặc định
                .collect(Collectors.toList()); // Thu thập lại thành List<String>

        // 2. Kết hợp các thông báo lỗi thành một chuỗi duy nhất (hoặc trả về List)
        // Ví dụ: "Password phải lớn hơn 8 ký tự, Username không được để trống"
        String errorMessage = String.join(", ", errors);

        // 3. Trả về ResponseEntity với thông báo lỗi tùy chỉnh và mã trạng thái 400 Bad Request
        return ResponseEntity.badRequest().body(errorMessage);
    }

}
