import java.util.Scanner;
import java.util.Vector;
public class ShortestJobFirst {
//    test
    public ShortestJobFirst(Vector<Process> arr, int n){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter context switch: ");
        int contextSwitch = sc.nextInt();
        for (int i=0;i<n;i++)
        {
            System.out.print("Enter The process name: ");
            String processName= sc.next();
            System.out.print("Enter The arrival time: ");
            int arrivalTime= sc.nextInt();
            System.out.print("Enter The burst time: ");
            int burstTime= sc.nextInt();
            Process k=new Process();
            k.setData(processName,arrivalTime,burstTime,i,contextSwitch,0);
            arr.add(k);
        }
    }

    public void SJFSchedule(Vector<Process> arr, int n){
        avgTime(arr, n);
    }

    static void waitingTime(Vector<Process> arr, int n, int[] wTime) {
        int[] rTime = new int[n];
        for (int i = 0; i < n; i++) {
            rTime[i] = arr.get(i).burstTime;
        }

        int completed = 0, t = 0,finishTime;

        while (completed != n) {
            int minBurst = Integer.MAX_VALUE;
            int selectedProcess = -1;

            for (int j = 0; j < n; j++) {
                if (arr.get(j).arrivalTime <= t && rTime[j] < minBurst && rTime[j] > 0) {
                    minBurst = rTime[j];
                    selectedProcess = j;
                }
            }

            if (selectedProcess == -1) {
                t++;
            } else {
                arr.get(selectedProcess).order = ++completed;
                finishTime = t + rTime[selectedProcess];
                wTime[selectedProcess] = finishTime - arr.get(selectedProcess).burstTime - arr.get(selectedProcess).arrivalTime;
                t = finishTime;
                rTime[selectedProcess] = 0;
            }
        }
    }
    static void turnAroundTime(Vector<Process> arr, int n, int[] wTime, int[] tAroundTime){
        for(int i=0;i <n;i++)
            tAroundTime[i] = arr.get(i).burstTime + wTime[i];
    }
    static void avgTime(Vector<Process> arr, int n){
        int[] wTime = new int[n], tAroundTime = new int[n];
        int _tWaitingTime = 0, _totalTurnArTime = 0;
        waitingTime(arr, n, wTime);
        turnAroundTime(arr, n, wTime, tAroundTime);
        System.out.println("Processes "+ " Order " + " Burst time " + " Waiting time " + " Turn around time");
        for(int i=0; i<n; i++){
            _tWaitingTime += wTime[i];
            _totalTurnArTime += tAroundTime[i];
            System.out.println(" " + arr.get(i).processName +"\t\t\t" + arr.get(i).order + "\t\t\t" + arr.get(i).burstTime +"\t\t\t "  + wTime[i] + "\t\t\t\t" + tAroundTime[i]);
        }
        System.out.println("Average waiting time = " + (float)_tWaitingTime / (float)n);
        System.out.println("Average turn around time = " + (float)_totalTurnArTime / (float)n);
    }
}

