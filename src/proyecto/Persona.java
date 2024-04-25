package proyecto;

import java.time.LocalDate;

public abstract class Persona {

    // Atributos

    protected String name;
    protected String surname;
    protected LocalDate birthDate;
    protected double motivation;
    protected int salary;

    // Constructores

    public Persona(String name, String surname, LocalDate birthDate, int salary) {
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.motivation = 5.00;
        this.salary = salary;
    }

    // getters y setters


    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }



    // metodos
    protected void exercise(){
        this.motivation += 0.20;
    }


}
