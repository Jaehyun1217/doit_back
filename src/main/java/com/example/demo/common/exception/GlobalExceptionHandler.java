//package com.example.demo.common.exception;
//
//import java.net.BindException;
//
//import com.example.demo.user.exception.UnauthorizedUserException;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.ResponseEntity;
//import org.springframework.http.converter.HttpMessageNotReadableException;
//import org.springframework.web.HttpRequestMethodNotSupportedException;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
//
///**
// * 전역적인 에러를 처리하는 클래스
// */
//@Slf4j
//@RestControllerAdvice
//public class GlobalExceptionHandler {
//
//    /**
//     * Valid/Validated 에서 에러가 발생한 경우
//     */
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    private ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
//        log.error("MethodArgumentNotValidException Error", e);
//        ErrorResponse error = ErrorResponse.of(GlobalErrorCode.INVALID_HTTP_MESSAGE_BODY,
//                e.getFieldError().getDefaultMessage());
//        return ResponseEntity.status(error.getHttpStatus()).body(error);
//    }
//
//    /**
//     * 바인딩에서 에러가 발생한 경우
//     */
//    @ExceptionHandler(BindException.class)
//    private ResponseEntity<ErrorResponse> handleBindException(BindException e) {
//        ErrorResponse error = ErrorResponse.of(GlobalErrorCode.INVALID_HTTP_MESSAGE_BODY);
//        return ResponseEntity.status(error.getHttpStatus()).body(error);
//    }
//
//    /**
//     * Enum 타입이 일치하지 않는 경우
//     */
//    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
//    private ResponseEntity<ErrorResponse> handleMethodArgumentTypeMismatchException(
//            MethodArgumentTypeMismatchException e) {
//        log.error("MethodArgumentTypeMismatchException Error", e);
//        ErrorResponse error = ErrorResponse.of(GlobalErrorCode.INVALID_HTTP_MESSAGE_BODY);
//        return ResponseEntity.status(error.getHttpStatus()).body(error);
//    }
//
//    /**
//     * 지원하지 않는 API 메소드인 경우
//     */
////    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
////    private ResponseEntity<ErrorResponse> handleHttpRequestMethodNotSupportedException(
////            HttpRequestMethodNotSupportedException e) {
////        log.error("HttpRequestMethodNotSupportedException Error", e);
////        ErrorResponse error = ErrorResponse.of(GlobalErrorCode.UNSUPPORTED_HTTP_METHOD);
////        return ResponseEntity.status(error.getHttpStatus()).body(error);
////    }
//
//    /**
//     * Request 를 읽을 수 없는 경우
//     */
//    @ExceptionHandler(HttpMessageNotReadableException.class)
//    protected ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
//        log.error("HttpMessageNotReadableException error", e);
//        ErrorResponse error = ErrorResponse.of(GlobalErrorCode.BAD_REQUEST_ERROR);
//        return ResponseEntity.status(error.getHttpStatus()).body(error);
//    }
//
//    /**
//     * 기타 비즈니스 로직에서 에러가 난 경우
//     */
//    @ExceptionHandler(BaseException.class)
//    private ResponseEntity<ErrorResponse> handleBusinessException(BaseException e) {
//        log.error("BusinessError ");
//        log.error(e.getErrorCode().getMessage());
//        ErrorResponse error = ErrorResponse.of(e.getErrorCode());
//        return ResponseEntity.status(error.getHttpStatus()).body(error);
//    }
//
//    /**
//     * 인증되지 않은 사용자 예외 처리
//     */
//    @ResponseBody
//    @ExceptionHandler(UnauthorizedUserException.class)
//    private ResponseEntity<ErrorResponse> handleUnauthorizedUserException(UnauthorizedUserException e) {
//        log.error("UnauthorizedUserException Error", e);
//        ErrorResponse error = ErrorResponse.of(e.getErrorCode());
//        return ResponseEntity.status(error.getHttpStatus()).body(error);
//    }
//
//    /**
//     * 나머지 에러가 난 경우
//     */
////    @ExceptionHandler(Exception.class)
////    private ResponseEntity<ErrorResponse> handleException(Exception e) {
////        log.error("Exception Error ", e);
////        ErrorResponse error = ErrorResponse.of(GlobalErrorCode.SERVER_ERROR);
////        return ResponseEntity.status(error.getHttpStatus()).body(error);
////    }
//}