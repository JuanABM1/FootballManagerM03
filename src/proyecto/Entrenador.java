package proyecto;

import java.time.LocalDate;

public class Entrenador extends Persona{

    // Atributos

    private static int counter = 0;
    private int id;
    private int tournamentsWon;

    private boolean nationalSelection;

    // constructores

    public Entrenador(String name, String surname, LocalDate birthDate, int salary, int tournamentsWon, boolean nationalSelection) {
        super(name, surname, birthDate, salary);
        this.tournamentsWon = tournamentsWon;
        this.nationalSelection = nationalSelection;
        this.id = counter;
        counter++;
    }

    // getters y setters

    public int getTournamentsWon() {
        return tournamentsWon;
    }

    public void setTournamentsWon(int tournamentsWon) {
        this.tournamentsWon = tournamentsWon;
    }

    public boolean isNationalSelection() {
        return nationalSelection;
    }

    public void setNationalSelection(boolean nationalSelection) {
        this.nationalSelection = nationalSelection;
    }

    public int getId() {
        return id;
    }
// metodos

    @Override
    public void exercise(){
        if (nationalSelection){
            this.motivation += 0.30;
        }else {
            this.motivation -= 0.15;
        }
    }

    public void raiseSalary(){
        this.salary += (int) ((0.5 / salary) * 100);
    }

    @Override
    public String toString() {
        return "Trainer data" +
                "id = " + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birthDate=" + birthDate +
                ", tournaments Won = " + tournamentsWon +
                ", national Selection = " + nationalSelection +
                ", motivation=" + motivation +
                ", salary=" + salary;
    }
}
