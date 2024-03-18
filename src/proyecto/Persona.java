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

    public LocalDate getBirthDate() {
        return birthDate;
    }


    public double getMotivation() {
        return motivation;
    }

    public void setMotivation(double motivation) {
        if(motivation < 1) {
            this.motivation = 1;
        } else if (motivation > 10) {
            this.motivation = 10;
        }else {
            this.motivation = motivation;
        }
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    // metodos
    protected void exercise(){
        this.motivation += 0.20; // preguntar si esta manera de hacer esto esta bien
    }


}
