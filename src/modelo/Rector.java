package modelo;

public class Rector extends Usuario {
    private String institucion;

    public Rector(String nombre, String apellido, int edad, String email, String password, String institucion) {
        super(nombre, apellido, edad, email, password);
        this.institucion = institucion;
    }

    @Override
    public String getRol() {
        return "Rector";
    }

    public String getInstitucion() { return institucion; }
    public void setInstitucion(String institucion) { this.institucion = institucion; }
}
