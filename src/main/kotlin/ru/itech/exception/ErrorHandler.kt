package ru.itech.exception

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

//  Обработчик ошибок будет применяться ко всем контроллерам за счет этой анннотации
@ControllerAdvice

class ErrorHandler: ResponseEntityExceptionHandler(){

    // Обработчик для BaseException
    @ExceptionHandler(BaseException::class)
    fun handleBaseException(ex: BaseException): ResponseEntity<ApiError>{
        return ResponseEntity(ex.apiError,ex.httpStatus)
    }
}