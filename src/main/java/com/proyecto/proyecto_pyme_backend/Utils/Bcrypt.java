package com.proyecto.proyecto_pyme_backend.Utils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Bcrypt {

    private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public static String encode(String password) {
        return passwordEncoder.encode(password);
    }

    public static boolean checkPassword(String password, String encodedPassword) {
        return passwordEncoder.matches(password, encodedPassword);
    }
}
