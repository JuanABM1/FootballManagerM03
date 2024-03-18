package proyecto;

import java.util.ArrayList;

public class Equipo {

    // Atributos

    private String name;
    private int foundationYear;
    private String cityName;
    private String stadiumName;
    private String presidentName;
    private Entrenador entrenador;
    private ArrayList<Jugador> jugadores = new ArrayList<>();
    private double punctuation;
    private Stats stats;


    // constructores

        // constructor completo (preguntar al usuario si quiere poner todos los valores)
    public Equipo(String name, int foundationYear, String cityName, String stadiumName, String presidentName) {
        this.name = name;
        this.foundationYear = foundationYear;
        this.cityName = cityName;
        this.stadiumName = stadiumName;
        this.presidentName = presidentName;
        this.stats = new Stats();
    }

        // constructor sin opcionales (stadiumName, PresidentName)
    public Equipo(String name, int foundationYear, String cityName) {
        this.name = name;
        this.foundationYear = foundationYear;
        this.cityName = cityName;
        this.stadiumName = "null";
        this.presidentName = "null";
        this.stats = new Stats();
    }


    // getters y setters

    public String getName() {
        return name;
    }


    public int getFoundationYear() {
        return foundationYear;
    }


    public String getCityName() {
        return cityName;
    }


    public String getStadiumName() {
        return stadiumName;
    }

    public void setStadiumName(String stadiumName) {
        this.stadiumName = stadiumName;
    }

    public String getPresidentName() {
        return presidentName;
    }

    public void setPresidentName(String presidentName) {
        this.presidentName = presidentName;
    }

    public Entrenador getEntrenador() {
        return entrenador;
    }

    public void setEntrenador(Entrenador entrenador) {
        this.entrenador = entrenador;
    }

    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(ArrayList<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public double getPunctuation() {
        return punctuation;
    }

    // Metodos

    public void dismissCoach(){
        this.entrenador = null;
    }

    public void signPlayer(Jugador newPlayer){
        jugadores.add(newPlayer);
        punctuation += newPlayer.getQuality();
    }

    public void signCoach(Entrenador newTrainer){
        this.entrenador = newTrainer;
    }

    @Override
    public String toString() {
        return "Team data : \n" +
                "name = " + name + '\'' +
                ", foundation Year = " + foundationYear +
                ", city Name = " + cityName + '\'' +
                ", stadium Name = " + stadiumName + '\'' +
                ", president Name = " + presidentName + '\'' +
                 "\n" + entrenador +
                ", players\n = " + jugadores +
                ", punctuation = " + punctuation +
                ", stats = " + stats;
    }

    public void winMatch(int goalsInFavor, int goalsAgainst){

        stats.winMatch(goalsInFavor, goalsAgainst);

    }
    public void tieMatch(){
        int numberOfGoals = (int) (Math.random()*5);
        stats.tieMatch(numberOfGoals);

    }
    public void loseMatch(int goalsInFavor, int goalsAgainst){
        stats.loseMatch(goalsInFavor, goalsAgainst);
    }

}
