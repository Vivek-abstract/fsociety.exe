import java.util.*;
public class Bankers {

    public static void main(String[] args) {
        int number_of_processes, number_of_resources;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of processes: ");
        number_of_processes = sc.nextInt();
        System.out.println("Enter number of resources: ");
        number_of_resources = sc.nextInt();

        int allocation[][] = new int[number_of_processes][number_of_resources];
        int max[][] = new int[number_of_processes][number_of_resources];

        System.out.println("Enter allocation matrix: ");
        for(int i = 0; i < number_of_processes; i++) {
            for(int j = 0; j < number_of_resources; j++) {
                System.out.println("Enter element: ");
                allocation[i][j] = sc.nextInt();
            }
        }
        System.out.println("Enter max matrix: ");
        for(int i = 0; i < number_of_processes; i++) {
            for(int j = 0; j < number_of_resources; j++) {
                System.out.println("Enter element: ");
                max[i][j] = sc.nextInt();
            }
        }

        int available[] = new int[number_of_resources];
        System.out.println("Enter available resources: ");
        for(int i = 0; i < number_of_resources; i++) {
            System.out.println("Enter element: ");
            available[i] = sc.nextInt();
        }

        int need[][] = new int[number_of_processes][number_of_resources];
        //Calculating the need matrix
        for(int i = 0; i < number_of_processes; i++) {
            for(int j = 0; j < number_of_resources; j++) {
                need[i][j] = max[i][j] - allocation[i][j];
            }
        }

        System.out.println("Allocation matrix is: ");
        for(int i = 0; i < number_of_processes; i++) {
            for(int j = 0; j < number_of_resources; j++) {
                System.out.print(allocation[i][j]);
            }
            System.out.println();
        }

        System.out.println("Max matrix is: ");
        for(int i = 0; i < number_of_processes; i++) {
            for(int j = 0; j < number_of_resources; j++) {
                System.out.print(max[i][j]);
            }
            System.out.println();
        }

        System.out.println("Available matrix is: ");
        for(int i = 0; i < number_of_resources; i++) {
            System.out.print(available[i] + " ");
        }


        System.out.println("\nNeed matrix is: ");
        for(int i = 0; i < number_of_processes; i++) {
            for(int j = 0; j < number_of_resources; j++) {
                System.out.print(need[i][j]);
            }
            System.out.println();
        }

        int pcount = 0;
        //Now we go process by process
        int visited[] = new int[number_of_processes];
        for(int iter = 0; iter < 3; iter++) {
            for(int i = 0; i < number_of_processes; i++) {
                int flag = 0;

                for(int j = 0; j < number_of_resources; j++) {
                    if(visited[i] == 0 && need[i][j] > available[j]) {
                        flag = 1;
                        break;
                        //Consider next process
                    }
                }
                if(visited[i] == 1){
                    flag = 1;
                }
                if(flag == 0) {
                    //Consider this process
                    pcount++;
                    visited[i] = 1;
                    System.out.print("P" + i + " ");
                    for(int k = 0; k < number_of_resources; k++) {
                        available[k] -= need[i][k];
                    }
                    //Process executed now release resources
                    for(int k = 0; k < number_of_resources; k++) {
                        available[k] += max[i][k];
                    }
                }
            }
        }
        if(pcount == number_of_processes) {
            System.out.println("\nThe system is in safe state");
        } else {
            System.out.println("\nThe system is not in safe state");
        }
    }
}