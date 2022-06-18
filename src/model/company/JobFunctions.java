package model.company;

import java.util.Vector;

public class JobFunctions {

    private Vector<String> designations = new Vector<String>();

    public JobFunctions(){}
    public void addJobFunction(String function){
        if (!designations.contains(function)){
            designations.add(function);
        }
    }
    public String getJobFunction(int index){
        if (index >= 0 && index <= designations.size()){
            return designations.get(index);
        }
        return null;
    }
    public void removeJobFunction(int index){
        if (index >= 0 && index <= designations.size()){
            designations.remove(index);
        }
    }
    public String getName() {
        return null;
    }
}
