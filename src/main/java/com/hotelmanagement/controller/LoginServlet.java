package com.hotelmanagement.controller;

import com.hotelmanagement.model.User;
import com.hotelmanagement.service.UserService;
import com.hotelmanagement.service.impl.UserServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private UserService userService;

    @Override
    public void init() {

        userService = new UserServiceImpl();
    }

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher(
                "/WEB-INF/views/common/login.jsp"
        ).forward(request, response);
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String email =
                request.getParameter("email");

        String password =
                request.getParameter("password");

        Optional<User> optionalUser =
                userService.login(email, password);

        if (optionalUser.isPresent()) {

            User user = optionalUser.get();

            var session = request.getSession();

            session.setAttribute(
                    "loggedInUser",
                    user
            );

            if (user.getRole().name().equals("ADMIN")) {

                response.sendRedirect(
                        request.getContextPath()
                                + "/admin/dashboard"
                );

            } else if (user.getRole().name().equals("CUSTOMER")) {

                response.sendRedirect(
                        request.getContextPath()
                                + "/customer/dashboard"
                );

            } else if(user.getRole()
                    .name()
                    .equals("RECEPTIONIST")) {

                response.sendRedirect(
                        request.getContextPath()
                                + "/receptionist/dashboard"
                );
            }
            else {

                response.sendRedirect(
                        request.getContextPath()
                                + "/login"
                );
            }

        } else {

            response.sendRedirect(
                    request.getContextPath()
                            + "/login"
            );
        }
    }
}
