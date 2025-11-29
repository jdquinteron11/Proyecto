package modelo;

public class Administrador extends Usuario {
    private int nivelAcceso; // Atributo especifico de Administrador

    public Administrador(String nombre, String apellido, int edad, String email, String password, int nivelAcceso) {
        super(nombre, apellido, edad, email, password);
        this.nivelAcceso = nivelAcceso;
    }

    @Override // Implementacion del metodo abstracto getRol
    public String getRol() {
        return "Administrador";
    }

    public int getNivelAcceso() { return nivelAcceso; }
    public void setNivelAcceso(int nivelAcceso) { this.nivelAcceso = nivelAcceso; }
}
