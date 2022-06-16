package model.employees;

import java.util.List;

import model.company.JobFunctions;
import model.company.Teams;

public class Participation {
    private List<JobFunctions> function;
    private List<Teams> team;
    private Person owner;

    public Participation(Person owner) {
        this.owner = owner;
    }

    public void addFunction(JobFunctions function) {
        this.function.add(function);
    }

    public String getFunctionName(int index) {
        return this.function.get(index).getName();
    }

    public void removeFunction(int index) {
        this.function.remove(index);
    }

    public int getNumberOfFunctions() {
        return this.function.size();
    }

    public void addTeam(Teams team) {
        this.team.add(team);
    }

    public String getTeamName(int index) {
        return this.team.get(index).getName();
    }

    public void removeTeam(int index) {
        this.team.remove(index);
    }

    public int getNumberOfTeams() {
        return this.team.size();
    }
}