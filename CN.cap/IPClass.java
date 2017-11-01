import java.util.Scanner;
public class IPClass {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a;
        System.out.println("Enter IP address: ");
        String IP = sc.nextLine();
        System.out.println("Enter subnet: ");
        int subnet = sc.nextInt();
        String vars[] = IP.split("\\.");

        int number_of_hosts = (int) Math.pow(2,(32-subnet));
        a = Integer.parseInt(vars[0]);

        //Let us assume binary_IP contains IP converted to binary without . 10101...10
        // 32 - 3 dots = 29 ones and zeros

        String binary_IP = convert_octet_to_binary(vars[0]) + convert_octet_to_binary(vars[1]) +
                convert_octet_to_binary(vars[2]) + convert_octet_to_binary(vars[3]);

        int temp = 32 - subnet;
        String first_IP = find_first(binary_IP, temp);
        String last_IP = find_last(binary_IP, temp);

        if(a >= 0 && a <= 127){
            System.out.println("Class: A");
            System.out.println("Subnet Mask: 255.0.0.0");
        } else if(a > 127 && a <= 191){
            System.out.println("Class: B");
            System.out.println("Subnet Mask: 255.255.0.0");
        } else if(a > 191 && a <= 223){
            System.out.println("Class: C");
            System.out.println("Subnet Mask: 255.255.255.0");
        } else if(a > 223 && a <= 239){
            System.out.println("Class: D");
        } else {
            System.out.println("Class: E");
        }
        System.out.println("First IP: " + first_IP);
        System.out.println("Last IP: " + last_IP);
    }
    private static String convert_octet_to_binary(String IP) {
        //First we convert the String to int
        int unformatted_octet = Integer.parseInt(IP);
        //Convert the Integer to a binary string
        String unformatted_binary_octet = Integer.toBinaryString(unformatted_octet);
        //Pad the binary string to length 8 ex: 100 becomes 00000100
        String formatted_binary_octet = String.format("%8s", unformatted_binary_octet);
        //format doesn't pad with 0s it pads with ' ' so we replace
        return formatted_binary_octet.replace(' ', '0');
    }
    private static String find_first(String IP, int n) {
        //We find the number of 0s to replace at end
        String multiple_zeros = string_multiply("0", n);
        //We replace the last 32-n bits with 0
        String new_IP = IP.substring(0, IP.length() - n) + multiple_zeros;
        //Now we convert the binary string to its decimal representation
        String s = Integer.parseInt(new_IP.substring(0,8), 2) +  "." +
                Integer.parseInt(new_IP.substring(8,16), 2) + "." +
                Integer.parseInt(new_IP.substring(16,24), 2) + "." +
                Integer.parseInt(new_IP.substring(24,32), 2);
        return s;
    }
    private static String find_last(String IP, int n) {
        String multiple_zeros = string_multiply("1", n);
        String new_IP = IP.substring(0, IP.length() - n) + multiple_zeros;
        String s = Integer.parseInt(new_IP.substring(0,8), 2) +  "." +
                Integer.parseInt(new_IP.substring(8,16), 2) + "." +
                Integer.parseInt(new_IP.substring(16,24), 2) + "." +
                Integer.parseInt(new_IP.substring(24,32), 2);
        return s;
    }
    private static String string_multiply(String s, int n) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i++) {
            sb.append(s);
        }
        return sb.toString();
    }
}