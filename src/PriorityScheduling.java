import java.util.*;

public class PriorityScheduling {
    public PriorityScheduling(Vector<Process> arr, int n) {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < n; i++) {
            System.out.print("Enter The process name: ");
            String processName = sc.next();
            System.out.print("Enter The burst time: ");
            int burstTime = sc.nextInt();
            System.out.print("Enter The priority: ");
            int priority = sc.nextInt();
            Process k = new Process();
            k.setData(processName, 0, burstTime, i, 0, priority);
            arr.add(k);
        }
    }

    public void prioritySchedule(Vector<Process> arr, int n) {
        avgTime(arr, n);
    }

    static void waitingTime(Vector<Process> arr, int n, int[] wTime) {
       int c = 0;
       int[] rTime = new int[n];
       int[] pTime = new int[n];
       for(int i=0;i<n;i++){
           rTime[i] = arr.get(i).burstTime;
           pTime[i] = arr.get(i).priority;
       }
       int completed =0, t=0, max = Integer.MAX_VALUE, highest = 0, finishTime;
       boolean check = false;
       while(completed != n){
           for (int j = 0; j < n; j++) {
               if ((arr.get(j).arrivalTime <= t) && (pTime[j] < max) && rTime[j] > 0) {
                   max = pTime[j];
                   highest = j;
                   check = true;
               }
           }
           if (check == false) {
               t++;
               continue;
           }
           rTime[highest]--;
           c++;
           if (c == 3) {
               for (int i = 0; i < n; i++) {
                   if (arr.get(i).arrivalTime <= t && rTime[i] > 0) {
                       pTime[i]--;
                   }
               }
               c = 0;
           }
           max = pTime[highest];
           if (rTime[highest] == 0)
               max = Integer.MAX_VALUE;
           if (rTime[highest] == 0) {
               completed++;
               check = false;
               finishTime = t + 1;
               wTime[highest] = finishTime - arr.get(highest).burstTime - arr.get(highest).arrivalTime;
               arr.get(highest).order = completed;
               if (wTime[highest] < 0)
                   wTime[highest] = 0;

           }
           t++;

       }

    }

    static void turnAroundTime(Vector<Process> arr, int n, int[] wTime, int[] tAroundTime) {
        for (int i = 0; i < n; i++) {
            tAroundTime[i] = arr.get(i).burstTime + wTime[i];
        }
    }

    static void avgTime(Vector<Process> arr, int n) {
        int[] wTime = new int[n], tAroundTime = new int[n];
        int _tWaitingTime = 0, _totalTurnArTime = 0;
        waitingTime(arr, n, wTime);
        turnAroundTime(arr, n, wTime, tAroundTime);
        System.out.println("Processes " + " Priority " + " Burst time " + " Waiting time " + " Turn around time");
        for (int i = 0; i < n; i++) {
            _tWaitingTime += wTime[i];
            _totalTurnArTime += tAroundTime[i];
            System.out.println(" " + arr.get(i).processName + "\t\t\t" + arr.get(i).priority + "\t\t\t"
                    + arr.get(i).burstTime + "\t\t\t " + wTime[i] + "\t\t\t\t" + tAroundTime[i]);
        }
        System.out.println("Average waiting time = " + (float) _tWaitingTime / (float) n);
        System.out.println("Average turn around time = " + (float) _totalTurnArTime / (float) n);
    }
}