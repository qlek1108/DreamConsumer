package com.example.demo.auth.handler;

import com.example.demo.user.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class UserAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        Object principal = authentication.getPrincipal();
        if(principal instanceof User) {
            log.info("# User Authenticated successfully!");

            User user = (User) principal;
            sendSuccessResponse(response, user);
        }
    }

    private void sendSuccessResponse(HttpServletResponse response, User user) throws IOException {
        String jsonResponse = String.format("{\n\t\"tier\": \"%s\",\n \t\"userId\": %d\n}", "tier 구현 중(user.getTier)", user.getUserId()); // Refactoring : Gson 적용
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonResponse);
    }
}
