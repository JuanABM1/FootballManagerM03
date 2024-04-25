package proyecto;

import java.util.Objects;

public class Stats implements Comparable {

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

    public int getLeaguePuntuation() {
        return leaguePuntuation;
    }

    public int getGoalsInFavor() {
        return goalsInFavor;
    }

    public int getGoalsAgainst() {
        return goalsAgainst;
    }

    // stats

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stats stats = (Stats) o;
        return leaguePuntuation == stats.leaguePuntuation && goalsInFavor == stats.goalsInFavor && goalsAgainst == stats.goalsAgainst;
    }

    @Override
    public int hashCode() {
        return Objects.hash(leaguePuntuation, goalsInFavor, goalsAgainst);
    }

    // compare to

    @Override
    public int compareTo(Object o) {
        Stats stats = (Stats) o;

        int diff = stats.leaguePuntuation - this.leaguePuntuation;
        if (diff != 0){
            return diff;
        }else {
            return stats.goalsInFavor - this.goalsAgainst;
        }
    }

    // metodos

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
