package com.hotelmanagement.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

import jakarta.servlet.annotation.WebFilter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter({
        "/admin/*",
        "/customer/*"
})
public class AuthenticationFilter
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

        HttpSession session =
                httpRequest.getSession(false);

        boolean loggedIn =
                session != null
                        &&
                        session.getAttribute(
                                "loggedInUser"
                        ) != null;

        if (loggedIn) {

            chain.doFilter(
                    request,
                    response
            );

        } else {

            httpResponse.sendRedirect(
                    httpRequest.getContextPath()
                            + "/login"
            );
        }
    }
        }
