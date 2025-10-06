package com.proyecto.proyecto_pyme_backend.security;

import com.proyecto.proyecto_pyme_backend.dto.PymeUsuDto;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component // ✅ Hace que Spring pueda inyectarlo con @Autowired
public class JwtUtil {

    // 🔐 Clave secreta interna para firmar el token (nadie más debe tenerla)
    private final Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    // ⏳ Tiempo de expiración del token (5 horas)
    private final long expirationMs = 1000 * 60 * 60 * 5;

    // 🔧 Método para generar un token JWT con datos del usuario
    public String generateToken(PymeUsuDto usuario) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id_usuario", usuario.getId_usuario());
        claims.put("id_pyme", usuario.getId_pyme());
        claims.put("nombre_pyme", usuario.getNombre_pyme());
        claims.put("nom_usuario", usuario.getNom_usuario());

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(usuario.getNom_usuario()) // El "dueño" del token
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMs)) // ⏳ expiración
                .signWith(secretKey) // 🔐 firma
                .compact(); // 🧾 genera el string final del token
    }

    // 👀 Método para obtener el username desde el token
    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject(); // Esto fue lo que pusiste en .setSubject(...)
    }
}
