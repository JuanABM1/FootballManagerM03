package proyecto;

public class Stats {

    // Atributos
    private int leaguePuntuation;

    private int goalsInFavor;

    private int goalsAgainst;

    // constructores

    public Stats() {
        this.leaguePuntuation = 0;
        this.goalsInFavor = 0;
        this.goalsAgainst = 0;
    }


    // getters y setters

    public int getLeaguePuntuation() {
        return leaguePuntuation;
    }

    public void setLeaguePuntuation(int leaguePuntuation) {
        this.leaguePuntuation = leaguePuntuation;
    }

    public int getGoalsInFavor() {
        return goalsInFavor;
    }

    public void setGoalsInFavor(int goalsInFavor) {
        this.goalsInFavor = goalsInFavor;
    }

    public int getGoalsAgainst() {
        return goalsAgainst;
    }

    public void setGoalsAgainst(int goalsAgainst) {
        this.goalsAgainst = goalsAgainst;
    }

    //

    @Override
    public String toString() {
        return "This team has ended with the next stats: " +
                "league Punctuation =" + leaguePuntuation +
                ", goals In Favor =" + goalsInFavor +
                ", goals Against =" + goalsAgainst;
    }

    public void winMatch(int goalsInFavorInMatch, int goalsAgainstInMatch) {
        this.leaguePuntuation += 3;

        this.goalsInFavor += goalsInFavorInMatch;
        this.goalsAgainst += goalsAgainstInMatch;
    }
    public void tieMatch(int numberOfGoals){
        this.leaguePuntuation += 1;

        this.goalsInFavor += numberOfGoals;
        this.goalsAgainst += numberOfGoals;
    }
    public void loseMatch(int goalsInFavorInMatch, int goalsAgainstInMatch){
        this.goalsInFavor += goalsInFavorInMatch;
        this.goalsAgainst += goalsAgainstInMatch;
    }
}
