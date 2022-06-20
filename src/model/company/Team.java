package model.company;

import java.util.Vector;

import javax.swing.AbstractListModel;

public class Team {

    private Vector<String> designations = new Vector<String>();

    public Team(){}
    public void addTeam(String function){
        if (!designations.contains(function)){
            designations.add(function);
        }
    }
    public String getTeam(int index){
        if (index >= 0 && index <= designations.size()){
            return designations.get(index);
        }
        return null;
    }
    public void removeTeam(int index){
        if (index >= 0 && index <= designations.size()){
            designations.remove(index);
        }
    }
    public String getName() {
        return null;
    }
}