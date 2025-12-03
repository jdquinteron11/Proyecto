package modelo;

// Clase Rector que extiende de Usuario
public class Rector extends Usuario {
    private String institucion;
// Constructor
    public Rector(String nombre, String apellido, int edad, String email, String password) {
        super(nombre, apellido, edad, email, password);
        this.institucion = institucion;
    }
 // Implementacion del metodo abstracto getRol
    @Override
    public String getRol() {
        return "Rector";
    }
 //  Getters y Setters
    public String getInstitucion() { return institucion; }
    public void setInstitucion(String institucion) { this.institucion = institucion; }
}
