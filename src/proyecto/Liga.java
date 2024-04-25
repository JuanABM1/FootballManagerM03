package proyecto;

import java.util.ArrayList;

public class Liga {

    // atributos
    private String name;
    private ArrayList<Equipo> equipos = new ArrayList<>();
    private int numTeams;

    // constructor

    public Liga(String name, int numTeams) {
        this.name = name;
        this.numTeams = numTeams;
    }


    // getters y setters

    public ArrayList<Equipo> getEquipos() {
        return equipos;
    }

    // metodos

    public void addTeam(Equipo newTeam){
        equipos.add(newTeam);
    }

    public void playMatches(){
        for (int i = 0; i < this.numTeams; i++) {
            for (int j = i + 1; j < this.numTeams; j++) {
                if (equipos.get(i).getPunctuation() > equipos.get(j).getPunctuation()){
                    int numGoalsWinner = (int) (Math.random()*5)+1;
                    int numGoalsLoser = (int) (Math.random()*numGoalsWinner);

                    equipos.get(i).winMatch(numGoalsWinner, numGoalsLoser);
                    equipos.get(j).loseMatch(numGoalsLoser, numGoalsWinner);

                } else if (equipos.get(i).getPunctuation() == equipos.get(j).getPunctuation()) {
                    equipos.get(i).tieMatch();
                    equipos.get(j).tieMatch();

                } else if (equipos.get(j).getPunctuation() > equipos.get(i).getPunctuation()) {
                    int numGoalsWinner = (int) (Math.random()*5)+1;
                    int numGoalsLoser = (int) (Math.random()*numGoalsWinner);

                    equipos.get(j).winMatch(numGoalsWinner, numGoalsLoser);
                    equipos.get(i).loseMatch(numGoalsLoser, numGoalsWinner);
                }
            }
        }
    }

    @Override
    public String toString() {
        return "The finale results of the league: " +
                "name =" + name + '\'' +
                "\n Teams = " + equipos;
    }
}
