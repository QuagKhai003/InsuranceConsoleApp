package InsuranceSystem;

import Claim.Claim;
import Customer.*;
import DataSource.*;

import java.awt.*;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.InputMismatchException;
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
        System.out.println("1. View all customers ");
        System.out.println("2. View all policy holders");
        System.out.println("3. View all dependents");
        System.out.println("4. View all insurance card");
        System.out.println("5. View all claim");
        System.out.println("6. View all document");
        System.out.println("7. Exit the system");
        System.out.println("Please choose action : ");
        if (scanner.hasNextInt()) {
            int action = scanner.nextInt();
            switch (action) {
                case 1:
                    CustomerFeature.displayAllCustomer();
                    CustomerFeature.operationAllCustomer();
                    break;
                case 2:
                    CustomerFeature.displayAllPolicyHolder();
                    CustomerFeature.operationAllCustomer();
                    break;
                case 3:
                    CustomerFeature.displayAllDependent();
                    CustomerFeature.operationAllCustomer();
                    break;
                case 4:
                    CardFeature.displayAllInsuranceCard();
                    CardFeature.operationCard();
                    break;
                case 5:
                    ClaimFeature.displayAllClaim();
                    ClaimFeature.operationClaim();
                    break;
                case 6:
                    ClaimFeature.displayAllDocument();
                    ClaimFeature.operationClaim();
                case 7:
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

    /*public static void displayAllCustomer() {
        for (Customer c: LoadDataBase.customerList) {
            System.out.println(c);
        }
    }
    public static void operationAllCustomer () throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("-------------------All Customers---------------------");
        System.out.println("1. Create a customer ");
        System.out.println("2. Update a customer");
        System.out.println("3. Delete a customer");
        System.out.println("4. View dependents of a policy holder");
        System.out.println("5. View policy holder of a dependent");
        System.out.println("6. Get all customer");
        System.out.println("7. Go Back");
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
                    viewDependentsOfPolicyHolder();
                    break;
                case 5:
                    viewPolicyHolderOfDependent();
                    break;
                case 6:
                    displayAllCustomer();
                    operationAllCustomer();
                    break;
                case 7:
                    displayMainMenu();
                    break;
                default:
                    System.out.println();
                    System.out.println("Wrong number please enter again!");
                    System.out.println();
                    operationAllCustomer();
                    break;
            }
        } else {
            String invalidInput = scanner.nextLine();
            System.out.println();
            System.out.println("Invalid input: " + invalidInput);
            System.out.println("Please enter a valid number.");
            System.out.println();
            Thread.sleep(1000);
            operationAllCustomer();
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
                    Customer c = new PolicyHolder(generateCustomerId(),fullName, insuranceCardId);
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
                    if ( LoadDataBase.findCard(insuranceCardId) != null ) {
                        Customer c = new Dependent(generateCustomerId(),fullName, insuranceCardId);
                        LoadDataBase.customerList.add(c);
                        ((PolicyHolder) LoadDataBase.findCard(insuranceCardId).getCardHolder()).getListDependents().add(c);
                    } else {
                        System.out.println("Cannot create a dependent without non-existed policy holder of the given insurance card id");
                        System.out.println("Please create a policy holder with this insurance card id first");
                    }
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
        displayAllCustomer();
        operationAllCustomer();
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
        if (answer.equalsIgnoreCase("y") && foundC != null) {
            System.out.println("Processing...");
            try {
                String oldInsuranceCardId = foundC.getInsuranceCard();
                //compare the old insurance card id with the new one
                //if they are same just update the name
                //if they are different, update full name first and process to next step
                if (oldInsuranceCardId.equals(insuranceCardId)) {
                    foundC.setFullName(fullName);
                } else {
                    foundC.setFullName(fullName);
                    //check if the found customer is a policyholder or a dependent
                    if (foundC instanceof Dependent) {
                        foundC.setInsuranceCard(insuranceCardId);
                        if ( LoadDataBase.findCard(insuranceCardId) != null ) {
                            ((PolicyHolder) LoadDataBase.findCard(insuranceCardId).getCardHolder()).getListDependents().add(foundC);
                            ((PolicyHolder) LoadDataBase.findCard(oldInsuranceCardId).getCardHolder()).getListDependents().delete(foundC.getId());
                        }
                    }
                    if (foundC instanceof PolicyHolder) {
                        if ( LoadDataBase.findCard(insuranceCardId) != null ) {
                            if ( LoadDataBase.findCard(insuranceCardId).getCardHolder().equals(foundC) ) {
                                foundC.setInsuranceCard(insuranceCardId);
                            } else {
                                System.out.println("This card already have a policyholder");
                            }
                        } else {
                            foundC.setInsuranceCard(insuranceCardId);
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println("Error while updating a customer");
                throw e;
            }
            Thread.sleep(1000);
            System.out.println("Done updating!!!");
        } else {
            System.out.println("None existed customer");
            System.out.println("Exiting update process...");
        }
        Thread.sleep(1000);
        displayAllCustomer();
        operationAllCustomer();
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
                ((PolicyHolder) LoadDataBase.findCard(foundC.getInsuranceCard()).getCardHolder()).getListDependents().delete(foundC.getId());
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
        displayAllCustomer();
        operationAllCustomer();
    }

    public static void viewDependentsOfPolicyHolder() throws InterruptedException {
        int index = 0;
        for (Customer c : LoadDataBase.policyHolderList) {
            index = index + 1;
            System.out.println(index + ". " + c);
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        System.out.println("Choose your an index of a policy holder you want to see the dependents");
        int chosenIndex = scanner.nextInt();
        Customer desiredPolicyHolder = LoadDataBase.policyHolderList.get(chosenIndex - 1);
        System.out.println("Your chosen policy holder: " + desiredPolicyHolder);
        System.out.println();
        System.out.println("The list dependent of the chosen policy holder:");
        for (Customer dep: ((PolicyHolder) desiredPolicyHolder).getListDependents().getDependentList()) {
            System.out.println(dep);
        }
        System.out.println();
        operationAllCustomer();
    }
    public static void viewPolicyHolderOfDependent() throws InterruptedException {
        int index = 0;
        for (Customer c : LoadDataBase.dependentList) {
            index = index + 1;
            System.out.println(index + ". " + c);
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        System.out.println("Choose your an index of a dependent you want to see the policy holder");
        int chosenIndex = scanner.nextInt();
        Customer desiredDependent = LoadDataBase.dependentList.get(chosenIndex - 1);
        System.out.println("Your chosen policy holder: " + desiredDependent);
        System.out.println();
        System.out.println("The policy holder of the chosen dependent:");
        System.out.println(LoadDataBase.findCard(desiredDependent.getInsuranceCard()).getCardHolder());
        System.out.println();
        operationAllCustomer();
    }

    //Auto Generating Customer ID Function
    public static String generateCustomerId() {
        cusAmount = cusAmount + 1;
        int loopAdd = 7 - Integer.toString(cusAmount).length();
        return "c-" + "0".repeat(Math.max(0, loopAdd)) + (cusAmount);
    }*/


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
