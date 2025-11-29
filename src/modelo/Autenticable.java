package modelo;
// Interfaz Autenticable que define el metodo login >>>> esta sera implementada por la clase Usuario y el inicio del codigo en la rama herencia
public interface Autenticable {
    boolean login(String email, String password);
}
