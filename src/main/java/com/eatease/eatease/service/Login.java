// src/main/java/com/eatease/eatease/service/Login.java
package com.eatease.eatease.service;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import org.mindrot.jbcrypt.BCrypt;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletRequest;

import com.eatease.eatease.model.Funcionario;

public class Login {

    // Token -> Funcionario
    private static final ConcurrentHashMap<String, Funcionario> SESSOES = new ConcurrentHashMap<>();
    private static FuncionarioService funcionarioService;
    private static CargoService cargoService;

    private Login() {
        /* util class */ }

    public static void setFuncionarioService(FuncionarioService service) {
        funcionarioService = service;
    }

    public static void setCargoService(CargoService service) {
        cargoService = service;
    }

    // ----- Password helpers -----
    public static String hashPassword(String plainPassword) {
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt());
    }

    private static boolean checkPassword(String plainPassword, String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }

    // ----- Login / Sessão -----
    public static String login(String username, String password, HttpServletResponse response) {
        Funcionario funcionario = funcionarioService.findByUsername(username).orElse(null);
        if (funcionario == null || !checkPassword(password, funcionario.getPassword())) {
            return null; // inválido
        }

        String token = UUID.randomUUID().toString();
        SESSOES.put(token, funcionario);

        // token cookie
        Cookie tokenCookie = new Cookie("token", token);
        tokenCookie.setPath("/");
        tokenCookie.setHttpOnly(true);
        tokenCookie.setMaxAge(60 * 60 * 24 * 4); // 4 dias
        response.addCookie(tokenCookie);

        // username cookie (útil para front-end)
        Cookie userCookie = new Cookie("username", username);
        userCookie.setPath("/");
        userCookie.setMaxAge(60 * 60 * 24 * 4);
        response.addCookie(userCookie);

        return username;
    }

    public static String checkLogin(HttpServletRequest request) {
        if (funcionarioService == null || cargoService == null) {
            throw new IllegalStateException("Services not properly initialized");
        }

        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return null;
        }

        String token = null;
        String username = null;

        // Extract token and username from cookies
        for (Cookie cookie : cookies) {
            if ("token".equals(cookie.getName())) {
                token = cookie.getValue();
            } else if ("username".equals(cookie.getName())) {
                username = cookie.getValue();
            }
        }

        // If either cookie is missing, authentication fails
        if (token == null || username == null) {
            return null;
        }

        // Check if token exists in sessions and username matches
        Funcionario f = SESSOES.get(token);
        return (f != null && f.getUsername().equals(username)) ? username : null;
    }

    public static String getUsername(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return null;
        }

        for (Cookie cookie : cookies) {
            if ("username".equals(cookie.getName())) {
                return cookie.getValue();
            }
        }

        return null; // username não encontrado
    }

    public static void logout(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("token".equals(cookie.getName())) {
                    // remove session
                    SESSOES.remove(cookie.getValue());
                    // clear token cookie
                    cookie.setValue("");
                    cookie.setPath("/");
                    cookie.setHttpOnly(true);
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                } else if ("username".equals(cookie.getName())) {
                    System.out.println("A dar logout a: " + cookie.getValue());
                    // clear username cookie
                    cookie.setValue("");
                    cookie.setPath("/");
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }
            }
        }
    }

    public static String checkLoginWithCargos(HttpServletRequest request, String... cargos) {
        if (funcionarioService == null || cargoService == null) {
            throw new IllegalStateException("Services not properly initialized");
        }

        String username = checkLogin(request);
        if (username == null) {
            return null;
        }

        Funcionario funcionario = funcionarioService.findByUsername(username).orElse(null);
        if (funcionario == null) {
            return null;
        }

        for (String cargo : cargos) {
            long cargoId = cargoService.getCargoId(cargo);
            if (cargoId == -1) {
                System.err.println("Cargo não existe: " + cargo);
                continue; // cargo não existe
            }

            if (funcionario.getCargoId() == cargoId) {
                return username; // usuário tem o cargo
            }
        }
        return null;
    }
}
