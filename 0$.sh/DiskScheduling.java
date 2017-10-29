import java.util.Scanner;
public class DiskScheduling {

    public static void main(String[] args) {
        int choice, sum = 0;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter no of processes:");
        int n = sc.nextInt();
        int[] a = new int[n];
        for(int i=0; i < n; i++) {
            System.out.println("Enter disk request: ");
            a[i] = sc.nextInt();
        }
        System.out.println("Enter head location: ");
        int head = sc.nextInt();
        int requests_processed = 0;
        System.out.println("1.FCFS\n2.SSTF\n3.C-SCAN\n4.C-LOOK\nEnter choice: ");
        choice = sc.nextInt();
        switch(choice) {
            case 1:
                for(int i = 0; i < n; i++) {
                    sum+=Math.abs(head-a[i]);
                    head = a[i];
                }
                System.out.println("Total head movement: " + sum);
                break;
            case 2:
                int sum1 = 0, pos = -1;
                int[] visited = new int[n];
                for(int i = 0; i < n; i++) {
                    int min_diff=999, temp=999;

                    for(int j = 0; j < n; j++) {
                        if(visited[j] == 0) {
                            temp = Math.abs(a[j] - head);
                            if(temp < min_diff) {
                                min_diff = temp;
                                pos = j;
                            }
                        }
                    }
                    visited[pos] = 1;
                    sum1 += min_diff;
                    head = a[pos];
                }
                System.out.println("Total head movement: " + sum1);
                break;
            case 3:
                //Consider head moves right then comes back
                //We'll sort a[]
                for(int i = 0; i < n; i++) {
                    for(int j = i+1; j < n; j++) {
                        if(a[i] > a[j]) {
                            int temp = a[i];
                            a[i] = a[j];
                            a[j] = temp;
                        }
                    }
                }
                int i;
                while(requests_processed != n) {
                    for(i = 0; i < n; i++) {
                        if(head <= a[i]) {
                            break;
                        }
                    }
                    //Check if head is on the right of all processes
                    if(i == n) {
                        //Move left till 0
                        head = 0;
                        sum += 199;
                        i = 0;
                        //Now move right
                    }
                    for(int j = i; j < n && requests_processed != n; j++) {
                        sum += Math.abs(head - a[j]);
                        head = a[j];
                        requests_processed++;
                    }
                    if(requests_processed != n) {
                        //Move right and then repeat
                        sum += Math.abs(head - 199);
                        head = 199;;
                    }
                }
                System.out.println("Total head movement = " + sum);
                break;
            case 4:
                //Consider we go right first
                //We'll sort a[]
                for(int p = 0; p < n; p++) {
                    for(int j = p+1; j < n; j++) {
                        if(a[p] > a[j]) {
                            int temp = a[p];
                            a[p] = a[j];
                            a[j] = temp;
                        }
                    }
                }
                int p;
                while(requests_processed != n) {
                    for(p = 0; p < n; p++) {
                        if(head <= a[p]) {
                            break;
                        }
                    }
                    //Check if head is on the right of all processes
                    if(p == n || p == n-1) {
                        //Move left till lowest process and start moving right
                        sum += Math.abs(head - a[0]);
                        head = a[0];
                        p = 0;
                    }
                    for(int j = p; j < n && requests_processed != n; j++) {
                        sum += Math.abs(head - a[j]);
                        head = a[j];
                        requests_processed++;
                    }
                }
                System.out.println("Total head movement = " + sum);
                break;
        }
    }


}