import java.util.Comparator;
import java.util.Scanner;
import java.lang.String;
import java.util.Collections;
public  class Process  {
    String processName="";
    int arrivalTime=0;
    int burstTime=0;
    int processNumber=0;
    int waitingTime=0;
    int turnAroundTime=0;
    int order=0;
    int contextSwitch;
    int priority;


    public void setData(String processName,int arrivalTime,  int burstTime,int num,int contextSwitch,int priority)
    {
        this.processName=processName;
        this.arrivalTime=arrivalTime;
        this.burstTime=burstTime;
        this.processNumber=num;
        this.waitingTime=0;
        this.turnAroundTime=0;
        this.contextSwitch=contextSwitch;
        this.priority = priority;
    }




}