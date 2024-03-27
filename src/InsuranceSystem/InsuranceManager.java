package InsuranceSystem;

import Claim.Claim;
import Customer.*;
import DataSource.*;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.Scanner;


public class InsuranceManager{
    private static boolean status;
    public static Scanner scanner = new Scanner(System.in);
    public InsuranceManager(boolean active) throws FileNotFoundException, ParseException, InterruptedException {
        status = active;
        LoadDataBase.createAll();
        while (status) {
            displayMainMenu();
            status = false;
        }
        System.out.println("Exit successfull");
    }

    public static void displayMainMenu() throws InterruptedException {
        System.out.println("-------------------MAIN  MENU---------------------");
        System.out.println("1. View all customers ");
        System.out.println("2. View all policy holders");
        System.out.println("3. View all dependents");
        System.out.println("4. View dependents of a policy holder");
        System.out.println("5. View policy holder of a dependent");
        System.out.println("6. Exit the system");
        System.out.println("Please choose action : ");
        if (scanner.hasNextInt()) {
            int action = scanner.nextInt();
            switch (action) {
                case 1:
                    viewAllCustomer();
                    break;
                case 2:
                    System.out.println(2);
                    break;
                case 3:
                    System.out.println(3);
                    break;
                case 4:
                    System.out.println(4);
                    break;
                case 5:
                    System.out.println(5);
                    break;
                case 6:
                    System.out.println(6);
                    status = false;
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

    public static void viewAllCustomer () throws InterruptedException {
        for (Customer c: LoadDataBase.customerList) {
            System.out.println(c);
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("-------------------All Customers---------------------");
        System.out.println("1. Create a customer ");
        System.out.println("2. Update a customer");
        System.out.println("3. Delete a customer");
        System.out.println("4. Go Back");
        System.out.println("Please choose action : ");
        if (scanner.hasNextInt()) {
            int action = scanner.nextInt();
            switch (action) {
                case 1:
                    addCustomer();
                    break;
                case 2:
                    updateCustomer();
                    break;
                case 3:
                    deleteCustomer();
                    break;
                case 4:
                    displayMainMenu();
                    break;
                default:
                    System.out.println();
                    System.out.println("Wrong number please enter again!");
                    System.out.println();
                    viewAllCustomer();
                    break;
            }
        } else {
            String invalidInput = scanner.nextLine();
            System.out.println();
            System.out.println("Invalid input: " + invalidInput);
            System.out.println("Please enter a valid number.");
            System.out.println();
            Thread.sleep(1000);
            viewAllCustomer();
        }

    }

    public static void addCustomer() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("-------------------Create Customers---------------------");
        System.out.println("You want to create a policy holder(1) or an dependent(2) (Type the number for your desired customer):");
        int role = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter customer's full name: ");
        String fullName = scanner.nextLine();
        System.out.println("Enter insurance card's id: ");
        String insuranceCardId = scanner.nextLine();
        System.out.println("Do you want to create this customer(Y/N):");
        String answer = scanner.nextLine();
        if (answer.equalsIgnoreCase("y")) {
            System.out.println("Processing...");
            if ( role == 1 ) {
                try {
                    Customer c = new PolicyHolder(generateCustomerId(LoadDataBase.customerList.size()),fullName, insuranceCardId);
                    LoadDataBase.customerList.add(c);
                } catch (Exception e) {
                    System.out.println("Error while creating new policy holder");
                    throw e;
                }
                Thread.sleep(1000);
                System.out.println("Done creating!!!");
            }
            if ( role == 2) {
                try {
                    Customer c = new Dependent(generateCustomerId(LoadDataBase.customerList.size()),fullName, insuranceCardId);
                    LoadDataBase.customerList.add(c);
                } catch (Exception e) {
                    System.out.println("Error while creating new dependent");
                    throw e;
                }
                Thread.sleep(1000);
                System.out.println("Done creating!!!");
            }
            Thread.sleep(500);
            System.out.println("Exiting creating process...");
        } else {
            Thread.sleep(500);
            System.out.println("Exiting creating process...");
        }
        Thread.sleep(1000);
        viewAllCustomer();
    }

    public static void updateCustomer() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("PLease enter the id of customer:");
        String cID = scanner.nextLine();
        while (cID == null) {
            System.out.println("PLease enter a valid id of the customer:");
            cID = scanner.nextLine();
        }
        Customer foundC = LoadDataBase.findCustomer(cID);
        System.out.println(foundC);
        System.out.println("Enter customer's full name: ");
        String fullName = scanner.nextLine();
        System.out.println("Enter insurance card's id: ");
        String insuranceCardId = scanner.nextLine();
        System.out.println("Do you want to update this customer(Y/N):");
        String answer = scanner.nextLine();
        if (answer.equalsIgnoreCase("y")) {
            System.out.println("Processing...");
            try {
                foundC.setFullName(fullName);
                foundC.setInsuranceCard(insuranceCardId);
            } catch (Exception e) {
                System.out.println("Error while updating a customer");
                throw e;
            }
            Thread.sleep(1000);
            System.out.println("Done updating!!!");
        } else {
            System.out.println("Exiting update process...");
        }
        Thread.sleep(1000);
        viewAllCustomer();
    }

    public static void deleteCustomer() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("PLease enter the id of customer:");
        String cID = scanner.nextLine();
        while (cID == null) {
            System.out.println("PLease enter a valid id of the customer:");
            cID = scanner.nextLine();
        }
        Customer foundC = LoadDataBase.findCustomer(cID);
        System.out.println(foundC);
        System.out.println("Do you sure to delete this customer (Y/N):");
        String answer = scanner.nextLine();
        if (answer.equalsIgnoreCase("y")) {
            System.out.println("Processing...");
            try {
                LoadDataBase.customerList.remove(foundC);
            } catch (Exception e) {
                System.out.println("Error while deleting a customer");
                throw e;
            }
            Thread.sleep(1000);
            System.out.println("Done deleting!!!");
        } else {
            System.out.println("Exit deleting process...");
        }
        Thread.sleep(1000);
        viewAllCustomer();
    }

    //Auto Generating Customer ID Function
    public static String generateCustomerId(int number) {
        int loopAdd = 7 - Integer.toString(number).length();
        return "c-" + "0".repeat(Math.max(0, loopAdd)) + (number + 1);
    }

    //Auto Generating Insurance Card ID Function
    public static String generateCardId(int number) {
        int loopAdd = 10 - Integer.toString(number).length();
        return "0".repeat(Math.max(0, loopAdd)) + (number + 1);
    }

    //Auto Generating Claim ID Function
    public static String generateClaimId(int number) {
        int loopAdd = 10 - Integer.toString(number).length();
        return "f-" + "0".repeat(Math.max(0, loopAdd)) + (number + 1);
    }

    /*public static void main(String[] args) throws FileNotFoundException, ParseException {
        LoadDataBase.createAll();
        LoadDataBase.createCustomers();
        LoadDataBase.createCards();
        LoadDataBase.createDocuments();
        LoadDataBase.createClaims();

        Customer cus = LoadDataBase.customerList.stream().filter(aCus -> aCus.getId().equals("c-0000036")).findFirst().orElse(null);
        Customer cus2 = new Dependent("c-0002","sdasdada","dsadadadsd");
        InsuranceCard card2222 = LoadDataBase.cardList.stream().filter(aC -> aC.getCardId().equals("0000000012")).findFirst().orElse(null);
        Claim cl = new Claim("f-0000000013","01/01/2000",cus,"0000000012","01/01/2000","300$","Bank F - hsl111 - 59985");

        System.out.println(cus);
        System.out.println(cus2);
        cus.add(cl);
        cus2.add(cl);
        System.out.println(cus);
        System.out.println(cus2);
        LoadDataBase.claimList.add(cl);
        LoadDataBase.dependentList.add(cus2);
        LoadDataBase.customerList.add(cus2);

        System.out.println("======Customer List======");
        for(Customer c: LoadDataBase.customerList) {
            System.out.println(c);
        }

        System.out.println("======Insurance Card List======");
        for(InsuranceCard c: LoadDataBase.cardList) {
            System.out.println(c);
        }
        System.out.println("======Claim List======");
        for(Claim c: LoadDataBase.claimList) {
            System.out.println(c);
        }
    }*/
}
