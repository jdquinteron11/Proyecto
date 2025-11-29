package modelo;


// Clase abstracta Usuario que implementa la interfaz Autenticable (Herencia) y atributos comunes para todos los usuarios
public abstract class Usuario implements Autenticable {
    private String nombre;
    private String apellido;
    private int edad;
    private String email;
    private String password;

    // Constructor -------> Plantilla para poder crear diferentes tipos de usuarios con base en esta clase y sus atributos
    public Usuario(String nombre, String apellido, int edad, String email, String password) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.email = email;
        this.password = password;
    }

    @Override // Implementacion del metodo login de la interfaz Autenticable
    public boolean login(String email, String password) {
        return this.email.equals(email) && this.password.equals(password);
    }
    // Metodo abstracto para obtener el rol del usuario
    public abstract String getRol();

    // Getters ------> Encapsulamiento
    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public int getEdad() { return edad; }
    public String getEmail() { return email; }

    // Setters
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setApellido(String apellido) { this.apellido = apellido; }
    public void setEdad(int edad) { this.edad = edad; }
    public void setEmail(String email) { this.email = email; }
    public void setPassword(String password) { this.password = password; }
}
