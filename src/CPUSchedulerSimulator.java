import java.util.Scanner;
import java.util.Vector;


public class CPUSchedulerSimulator{

    public static void main(String[] args) {
        Vector<Process> arr = new Vector<Process>();
        int n;
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter number of processes: ");
        n= sc.nextInt();
        int c;
        System.out.println("Please enter ur choice");
        System.out.println("1- SJF");
        System.out.println("2- SRTF");
        System.out.println("3- Priority");
        System.out.println("4- AG");
        c=sc.nextInt();
        if (c==1)
        {
            ShortestJobFirst sjf=new ShortestJobFirst(arr,n);
            sjf.SJFSchedule(arr,n);
        }
//        else if (c==2)
//        {
//           SRTF()
//        }
        else if (c==3)
        {
            PriorityScheduling pr = new PriorityScheduling(arr, n);
            pr.prioritySchedule(arr, n);
        }
//        else if (c==4)
//        {
//            AG()
        }
}