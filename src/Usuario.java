import java.awt.*;


//atributos del usuario

public class Usuario {

    private  String name;
    private String last_name;
    private String password;
    private String email;
    private int age;



    // Constructor con base al usuario

    public Usuario(String name, String last_name, int age, String email, String password) {
        this.name = name;
        this.last_name = last_name;
        this.password = password;
        this.email = email;
        this.age = age;

        // Setters y getters de los atributos del usuario

    }
    // Getters
    public String getName() { return name; }
    public String getLastName() { return last_name; }
    public int getAge() { return age; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }

    // Setters
    public void setName(String name) { this.name = name; }
    public void setLastName(String lastName) { this.last_name = lastName; }
    public void setAge(int age) { this.age = age; }
    public void setEmail(String email) { this.email = email; }
    public void setPassword(String password) { this.password = password; }



        }









