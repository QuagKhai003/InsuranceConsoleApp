package InsuranceSystem;

import Claim.Claim;
import Customer.*;
import DataSource.*;

import java.io.IOException;
import java.util.Scanner;


public class InsuranceManager{
    private static boolean status;
    public static Scanner scanner = new Scanner(System.in);
    public InsuranceManager(boolean active) throws Exception {
        status = active;
        LoadDataBase.createAll();
        while (status) {
            displayMainMenu();
            status = false;
        }
        System.out.println("Exit successfully");
    }

    public static void displayMainMenu() throws Exception {
        System.out.println("-------------------MAIN  MENU---------------------");
        System.out.println("1. Customer Menu ");
        System.out.println("2. Insurance Card Menu");
        System.out.println("3. Claim Menu");
        System.out.println("4. View all policy holders");
        System.out.println("5. View all dependents");
        System.out.println("6. View all document");
        System.out.println("7. Exit the system");
        System.out.println("Please choose action : ");
        if (scanner.hasNextInt()) {
            int action = scanner.nextInt();
            switch (action) {
                case 1:
                    CustomerFeature.displayAllCustomer();
                    CustomerFeature.operationAllCustomer();
                    displayMainMenu();
                    break;
                case 2:
                    CardFeature.displayAllInsuranceCard();
                    CardFeature.operationCard();
                    displayMainMenu();
                    break;
                case 3:
                    ClaimFeature.displayAllClaim();
                    ClaimFeature.operationClaim();
                    displayMainMenu();
                    break;
                case 4:
                    CustomerFeature.displayAllPolicyHolder();
                    CustomerFeature.operationAllCustomer();
                    displayMainMenu();
                    break;
                case 5:
                    CustomerFeature.displayAllDependent();
                    CustomerFeature.operationAllCustomer();
                    displayMainMenu();
                    break;
                case 6:
                    ClaimFeature.displayAllDocument();
                    CustomerFeature.operationAllCustomer();
                    displayMainMenu();
                case 7:
                    saveDataBase();
                    break;
                default:
                    System.out.println();
                    System.out.println("Wrong number please enter again!");
                    System.out.println();
                    break;
            }
        } else {
            String invalidInput = scanner.nextLine();
            System.out.println();
            System.out.println("Invalid input: " + invalidInput);
            System.out.println("Please enter a valid number.");
            System.out.println();
        }
    }
    public static void saveDataBase() throws IOException, InterruptedException {
        Scanner scannerSave =  new Scanner(System.in);
        System.out.println("Do you want to save everything you modified (Y/N)?");
        String answer = scannerSave.nextLine();
        try {
            if(answer.equalsIgnoreCase("y")) {
                System.out.println("Saving...");
                SaveDatabase.writeAll();
            } else {
                System.out.println("Resetting...");
            }
            Thread.sleep(1000);
            System.out.println("Successfully");
        } catch (Exception e) {
            System.out.println("Error while saving database");
        }
    }
}

