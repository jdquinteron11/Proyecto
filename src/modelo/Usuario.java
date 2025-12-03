// java
package modelo;
// Clase base para representar un usuario en el sistema
public class Usuario implements Autenticable {
    private String nombre;
    private String apellido;
    private int edad;
    private String email;
    private String password;
 // Constructor
    public Usuario(String nombre, String apellido, int edad, String email, String password) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.email = email;
        this.password = password;
    }
 // Getters
    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public int getEdad() { return edad; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }

    // Método base que las subclases pueden sobreescribir
    public String getRol() {
        return "Usuario";
    }
 //
    @Override  // Implementacion del metodo autenticar de la interfaz Autenticable
    public boolean autenticar(String hashedPassword) {
        if (hashedPassword == null || this.password == null) return false;
        return this.password.equals(hashedPassword);
    }
}
