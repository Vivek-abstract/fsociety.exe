import java.util.*;
public class ProcessScheduling {

    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter no of processes: ");
        int n = sc.nextInt();
        int burst[] = new int[n];
        int arrival[] = new int[n];
        int index[] = new int[n];
        int priority[] = new int[n];
        int waiting[] = new int[n];
        int time_executed[] = new int[n];
        int last_executed[] = new int[n];
        int total_waiting_time=0, temp1=0, temp2 = 0;
        float average_waiting_time;

        System.out.println("Welcome to Process Scheduler 1.0");
        System.out.println("1.FCFS\n2.SJF\n3.Non Preemptive Priority\n4.Round Robin Scheduling\n5.Preemptive SJF");
        int choice = sc.nextInt();

        switch(choice) {
            case 1:
                int temp = 0;
                for(int i = 0; i < n; i++) {
                    System.out.println("Enter Burst time of process " + i);
                    burst[i] = sc.nextInt();
                }
                System.out.println("The Gnatt Chart is: ");
                for(int i = 0; i < n; i++) {
                    System.out.print(" P" + i + "\t");
                }
                System.out.println();
                for(int i = 0; i <= n; i++) {
                    System.out.print(temp + "\t");
                    if(i != n){
                        temp += burst[i];
                        total_waiting_time+=temp;
                    }
                }
                total_waiting_time-=temp;
                average_waiting_time = (float)total_waiting_time / n;
                System.out.println("\nAverage Waiting Time = " + average_waiting_time + "msec.");

                break;
            case 2:
                for(int i = 0; i < n; i++) {
                    System.out.println("Enter Burst time of process" + i);
                    burst[i] = sc.nextInt();
                    index[i] = i+1;
                }

                for(int i = 0; i < n; i++) {
                    for(int j = i + 1; j < n; j++) {
                        if(burst[i] >  burst[j]) {
                            temp1 = burst[i];
                            burst[i] = burst[j];
                            burst[j] = temp1;
                            temp1 = index[i];
                            index[i] = index[j];
                            index[j] = temp1;
                        }
                    }
                }
                System.out.println("The Gnatt Chart is: ");
                for(int i = 0; i < n; i++) {
                    System.out.print(" P" + index[i] + "\t");
                }
                System.out.println();
                for(int i = 0; i <= n; i++) {
                    System.out.print(temp2 + "\t");
                    if(i != n){
                        temp2 += burst[i];
                        total_waiting_time+=temp2;
                    }
                }
                total_waiting_time-=temp2;
                average_waiting_time = total_waiting_time / n;
                System.out.println("\nAverage Waiting Time = " + average_waiting_time + "msec.");

                break;
            case 3:
                for(int i = 0; i < n; i++) {
                    System.out.println("Enter Burst time of process" + i);
                    burst[i] = sc.nextInt();
                    System.out.println("Enter priority of process: " + i);
                    priority[i] = sc.nextInt();
                    index[i] = i+1;
                }

                for(int i = 0; i < n; i++) {
                    for(int j = i + 1; j < n; j++) {
                        if(priority[i] >  priority[j]) {
                            temp1 = priority[i];
                            priority[i] = priority[j];
                            priority[j] = temp1;
                            temp1 = burst[i];
                            burst[i] = burst[j];
                            burst[j] = temp1;
                            temp1 = index[i];
                            index[i] = index[j];
                            index[j] = temp1;
                        }
                    }
                }
                System.out.println("The Gantt Chart is: ");
                for(int i = 0; i < n; i++) {
                    System.out.print(" P" + index[i] + "\t");
                }
                System.out.println();
                for(int i = 0; i <= n; i++) {
                    System.out.print(temp2 + "\t");
                    if(i != n){
                        temp2 += burst[i];
                        total_waiting_time+=temp2;
                    }
                }
                total_waiting_time-=temp2;
                average_waiting_time = total_waiting_time /(float) n;
                System.out.println("\nAverage Waiting Time = " + average_waiting_time + "msec.");

                break;
            case 4:
                for(int i = 0; i < n; i++) {
                    System.out.println("Enter Burst time of process" + i);
                    burst[i] = sc.nextInt();
                    index[i] = i+1;
                }
                for(int i = 0; i < n; i++) {
                    System.out.println("Enter arrival time of process" + i);
                    arrival[i] = sc.nextInt();
                }
                System.out.println("Enter time quantum: ");
                int quantum = sc.nextInt();
                int k = 0, m = 0, flag = 1;
                int gantt_chart_time[] = new int[100];
                int orderi[] = new int[100];
                gantt_chart_time[k++] = 0;
                int i=0, lolsum = 0, current_time=0;
                while(flag == 1){
                    if(burst[i] > 0) {
                        if(burst[i] < quantum){
                            time_executed[i] += burst[i];
                            last_executed[i] = current_time + burst[i];
                            gantt_chart_time[k++] = current_time;
                            current_time += burst[i];
                        } else {
                            time_executed[i] += quantum;
                            last_executed[i] = current_time + quantum;
                            gantt_chart_time[k++] = current_time;
                            current_time += quantum;
                        }
                        orderi[m++] = i;
                        burst[i] -= quantum;
                        flag = 0;
                        for(int l=0; l < n; l++) {
                            //Check if any process is remaining
                            if(burst[l] > 0){
                                flag = 1;
                                break;
                            }
                        }
                    }
                    i=(i+1)%n;
                }
                for(int j=0; j<m; j++) {
                    System.out.print("P" + (orderi[j]+1) + " ");
                }
                System.out.println();
                for(int j=0; j<k; j++) {
                    System.out.print(gantt_chart_time[j] + " ");
                }
                total_waiting_time=0;
                for(int p = 0; p < n; p++) {
                    int waiting_time = last_executed[p] - time_executed[p] - arrival[p];
                    total_waiting_time += waiting_time;
                }
                average_waiting_time = (float) total_waiting_time / n;
                System.out.println("\nAverage Waiting Time = " + average_waiting_time);
                break;
            case 5:
                for(int j = 0; j < n; j++) {
                    System.out.println("Enter Burst time of process" + j);
                    burst[j] = sc.nextInt();
                    System.out.println("Enter arrival time of process: " + j);
                    arrival[j] = sc.nextInt();
                    index[j] = j+1;
                }
                int min = arrival[0], max = arrival[n-1], l;
                current_time=0;
                for(int j = min; j <= max; j++) {
                    int minof[] = new int[100];
                    l=0;
                    for(int k1 = 0; k1 < n; k1++) {
                        if(arrival[k1] <= j && burst[k1] > 0) {
                            //Consider this process in minimum array
                            minof[l++] = k1;
                        }
                    }
                    //Now find the max of these processes
                    int min1 = burst[minof[0]];
                    int pos = minof[0];
                    for(int p = 0; p < minof.length; p++) {
                        if(burst[minof[p]] < min1) {
                            min1 = burst[minof[p]];
                            pos = minof[p];
                        }
                    }
                    current_time+=1;
                    System.out.println(" P" + (pos+1) + " executes till " + current_time);
                    time_executed[pos]++;
                    last_executed[pos] = j + burst[pos];
                    burst[pos]--;
                }
                //Now do normal sjf
                for(int r = 0; r < n; r++) {
                    for(int j = r + 1; j < n; j++) {
                        if(burst[r] >  burst[j]) {
                            temp1 = burst[r];
                            burst[r] = burst[j];
                            burst[j] = temp1;
                            temp1 = index[r];
                            index[r] = index[j];
                            index[j] = temp1;
                            temp1 = waiting[r];
                            waiting[r] = waiting[j];
                            waiting[j] = temp1;
                            temp1 = last_executed[r];
                            last_executed[r] = last_executed[j];
                            last_executed[j] = temp1;
                            temp1 = time_executed[r];
                            time_executed[r] = time_executed[j];
                            time_executed[j] = temp1;
                            temp1 = arrival[r];
                            arrival[r] = arrival[j];
                            arrival[j] = temp1;
                        }
                    }
                }
                for(int e = 0; e < n; e++) {
                    if(burst[e] > 0) {
                        last_executed[e] = current_time + burst[e];
                        current_time += burst[e];
                        time_executed[e] += burst[e];
                        System.out.println(" P" + index[e] + " executes till " + current_time);
                    }
                }
                System.out.println();
                for(int z = 0; z < n; z++) {
                    int waiting_time = last_executed[z] - arrival[z] - time_executed[z];
                    total_waiting_time += waiting_time;
                }
                average_waiting_time = (float) total_waiting_time / n;
                System.out.println("Average Waiting Time = " + average_waiting_time);
                break;
        }

    }

}