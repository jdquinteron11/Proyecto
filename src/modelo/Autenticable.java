// java
package modelo;
// Interfaz para autenticación de usuarios
public interface Autenticable {
    boolean autenticar(String hashedPassword);
}
