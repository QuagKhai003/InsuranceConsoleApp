package MainSystem;

import InsuranceSystem.*;

import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception {

        System.out.println("INSURANCE MANAGEMENT SYSTEM");
        System.out.println("Please press enter to log in to the system");
        String access = scanner.nextLine();
        while (access != "") {
            System.out.println("Please press enter to log in to the system");
            access = scanner.nextLine();
        }
        InsuranceManager start = new InsuranceManager(true);
    }
}
