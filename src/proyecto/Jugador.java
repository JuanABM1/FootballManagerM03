package proyecto;

import java.time.LocalDate;

public class Jugador extends Persona implements Transferible {

    // Atributos

    private static int counter = 0;
    private int id;
    private positions position;
    private double quality;
    private int dorsal;
    public enum positions{
        POR,
        DEF,
        MIG,
        DEL
    }

    // Constructores

    public Jugador(String name, String surname, LocalDate birthDate, int salary, positions position, int dorsal) {
        super(name, surname, birthDate, salary);
        this.position = position;
        this.quality = (int) (Math.random()*(100 - 30 + 1) + 30);
        this.dorsal = dorsal;
        this.id = counter;
        counter++;
    }

    // getters y setters


    public positions getPosition() {
        return position;
    }

    public void setPosition(positions position) {
        this.position = position;
    }

    public double getQuality() {
        return quality;
    }

    public void setQuality(double quality) {
        this.quality = quality;
    }

    public int getDorsal() {
        return dorsal;
    }

    public void setDorsal(int dorsal) {
        this.dorsal = dorsal;
    }

    public int getId() {
        return id;
    }

// metodos

    @Override
    public void exercise(){
        super.exercise();
        int improvementProbability = (int) (Math.random()*(100 + 1));
        if (improvementProbability <= 70){          // Equivale a un 70% de probabilidad
            this.quality += 0.10;
        } else if (improvementProbability < 90) {   // Equivale a un 20 %
            this.quality += 0.20;
        }else {
            this.quality += 0.30;        // 10%
        }
    }
    public boolean changePosition(){
        int changeProbability = (int) (Math.random()*(100 + 1));    // saca un numero entre el 0 y el 100
        int newPosition;
        boolean hasChanged = false;
        if (changeProbability >= 95){
            newPosition = (int) (Math.random()* 4 + 1); // Saca un numero entre el 1 el 4
            switch (newPosition){
                case 1:
                    this.position = positions.POR;
                    hasChanged = true;
                    break;
                case 2:
                    this.position = positions.DEF;
                    hasChanged = true;
                    break;
                case 3:
                    this.position = positions.MIG;
                    hasChanged = true;
                    break;
                case 4:
                    this.position = positions.DEL;
                    hasChanged = true;
                    break;
                default:
                    break;
            }
            this.quality += 1;
        }
        return hasChanged;
    }

    @Override
    public void transferirAEquipo(Equipo equipoActual, Equipo equipoAtransferir) {
        equipoActual.getJugadores().remove(this);
        equipoAtransferir.getJugadores().add(this);
    }

    @Override
    public boolean esTransferible(char confirmation) {
        boolean esTransferible;
        if (confirmation == 'Y'){
            esTransferible = true;
        }else {
            esTransferible = false;
        }
        return esTransferible;
    }

    @Override
    public String toString() {
        return "Player data" +
                " id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birth Date=" + birthDate +
                ", position=" + position +
                ", quality=" + quality +
                ", dorsal=" + dorsal +
                ", motivation=" + motivation +
                ", salary=" + salary;
    }
}
