package modelo;


// Clase Abogado que extiende de Usuario
public class Abogado extends Usuario {
    private String especialidad; // Atributo especifico de Abogado

    // Constructor
    public Abogado(String nombre, String apellido, int edad, String email, String password) {
        super(nombre, apellido, edad, email, password);
        this.especialidad = especialidad;
    }
    // Implementacion del metodo abstracto getRol
    @Override
    public String getRol() {
        return "Abogado";
    }
    // Getters y Setters
    public String getEspecialidad() {
        return especialidad;
    }
    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }
}

