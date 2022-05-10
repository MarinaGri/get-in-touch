package ru.itis.controllers;


import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.exceptions.EntityNotFoundException;
import ru.itis.dto.response.ExceptionResponse;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ControllerAdvice
public class ExceptionController {

    private final String HEADER = "X-Requested-With";

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Object handleBadRequest(MethodArgumentTypeMismatchException ex, HttpServletRequest request) {
        return processException(request, HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Object handleNotFound(EntityNotFoundException ex, HttpServletRequest request) {
        return processException(request, HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception) {
        List<String> errors = new ArrayList<>();

        exception.getBindingResult().getAllErrors().forEach((error)
                -> errors.add(error.getDefaultMessage()));

        return new ResponseEntity<>(
                new ExceptionResponse(HttpStatus.BAD_REQUEST.value(), errors), HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Object handleServerError(Exception ex, HttpServletRequest request) throws Exception {
        if (AnnotationUtils.findAnnotation(ex.getClass(), ResponseStatus.class) != null
                || ex.getClass().equals(AccessDeniedException.class)) {
            throw ex;
        }
        return processException(request, HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
    }

    private Object processException(HttpServletRequest request, int status, String message) {
        if (isAjax(request)) {
            return new ExceptionResponse(status, Collections.singletonList(message));
        } else {
            return createModelAndView(status, message);
        }
    }

    private ModelAndView createModelAndView(int status, String message) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("status", status);
        mav.addObject("error", message);
        mav.setViewName("error");
        return mav;
    }

    private boolean isAjax(HttpServletRequest request) {
        return (request.getHeader(HEADER) != null && "XMLHttpRequest".equals(request.getHeader(HEADER)));
    }

}
