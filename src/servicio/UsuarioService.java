package servicio;

import modelo.Usuario;
import modelo.Rol;
import modelo.Rector;
import modelo.Abogado;
import modelo.Administrador;
import java.util.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;

public class UsuarioService {
    private final Map<String, Usuario> repo = new HashMap<>();

    public boolean register(Usuario user) {
        if (user == null || user.getEmail() == null) return false;
        if (repo.containsKey(user.getEmail())) return false;
        // Se asume que 'user' ya contiene el passwordHash (no hay setPassword/getPassword)
        repo.put(user.getEmail(), user);
        return true;
    }

    public boolean register(String nombre, String apellido, int edad, String email, String password, Rol rol) {
        if (email == null || rol == null) return false;
        if (repo.containsKey(email)) return false;

        String hashedPassword = hash(password);
        Usuario user;
        switch (rol) {
            case Rector:
                user = new Rector(nombre, apellido, edad, email, hashedPassword);
                break;
            case Abogado:
                user = new Abogado(nombre, apellido, edad, email, hashedPassword);
                break;
            case Administrador:
                user = new Administrador(nombre, apellido, edad, email, hashedPassword);
                break;
            default:
                return false;
        }

        // Ya hemos pasado el hash al constructor, no usar setPassword
        repo.put(email, user);
        return true;
    }

    public Usuario login(String email, String password) {
        Usuario u = repo.get(email);
        if (u == null) return null;
        String hashed = hash(password);
        return u.autenticar(hashed) ? u : null;
    }

    private String hash(String input) {
        if (input == null) return null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] digest = md.digest(input.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) sb.append(String.format("%02x", b));
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
