package com.example.demo.auth.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class UserAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException {
        log.error("# Authentication failed: {}", exception.getMessage());
        sendErrorResponse(response, exception.getMessage());
    }

    public void sendErrorResponse(HttpServletResponse response, String errorMessage) throws IOException {
//        Gson gson = new Gson();
//
//        class ErrorResponse {
//            String error;
//
//            ErrorResponse(String error) {
//                this.error = error;
//            }
//        }
//
//        ErrorResponse errorResponse = new ErrorResponse(errorMessage);
//
//        String jsonResponse = gson.toJson(errorResponse);
//        log.info("JSON Response: {}", jsonResponse); // null로 변환됨
        String jsonResponse = String.format("{\n\t\"error\": \"%s\"\n}", errorMessage);

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);  // 401
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonResponse);
    }
}
