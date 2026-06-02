package com.hotelmanagement.filter;

import com.hotelmanagement.model.User;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

import jakarta.servlet.annotation.WebFilter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter({
        "/admin/*",
        "/customer/*",
        "/receptionist/*"
})
public class AuthorizationFilter
        implements Filter {

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest =
                (HttpServletRequest) request;

        HttpServletResponse httpResponse =
                (HttpServletResponse) response;

        User user =
                (User) httpRequest
                        .getSession(false)
                        .getAttribute(
                                "loggedInUser"
                        );

        String uri =
                httpRequest.getRequestURI();

        if (uri.contains("/admin/")
                &&
                !user.getRole()
                                .

                        name()
                                .

                        equals("ADMIN")) {

            request.getRequestDispatcher(
                    "/WEB-INF/views/errors/access-denied.jsp"
            ).forward(
                    request,
                    response
            );

            return;
        }
        if (uri.contains("/customer/")
                &&
                !user.getRole()
                        .name()
                        .equals("CUSTOMER")) {

            request.getRequestDispatcher(
                    "/WEB-INF/views/errors/access-denied.jsp"
            ).forward(
                    request,
                    response
            );

            return;
        }
        if(uri.contains("/receptionist/")
                &&
                !user.getRole()
                        .name()
                        .equals("RECEPTIONIST")) {

            request.getRequestDispatcher(
                    "/WEB-INF/views/errors/access-denied.jsp"
            ).forward(
                    request,
                    response
            );

            return;
        }
        chain.doFilter(
                request,
                response
        );
    }
}

