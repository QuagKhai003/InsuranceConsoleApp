package InsuranceSystem;

import Customer.*;
import DataSource.LoadDataBase;

import java.text.ParseException;
import java.util.Scanner;

public class CustomerFeature {
    private static int cusAmount = Integer.parseInt(LoadDataBase.customerList.get(LoadDataBase.customerList.size()-1).getId().substring(2));

    public static void displayAllCustomer() {
        for (Customer c: LoadDataBase.customerList) {
            System.out.println(c);
        }
    }

    public static void displayAllPolicyHolder() {
        for (Customer c: LoadDataBase.policyHolderList) {
            System.out.println(c);
        }
    }
    public static void displayAllDependent() {
        for (Customer c: LoadDataBase.dependentList) {
            System.out.println(c);
        }
    }

    public static void operationAllCustomer () throws Exception {
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
                    operationAllCustomer();
                    break;
                case 2:
                    updateCustomer();
                    operationAllCustomer();
                    break;
                case 3:
                    deleteCustomer();
                    operationAllCustomer();
                    break;
                case 4:
                    viewDependentsOfPolicyHolder();
                    operationAllCustomer();
                    break;
                case 5:
                    viewPolicyHolderOfDependent();
                    operationAllCustomer();
                    break;
                case 6:
                    displayAllCustomer();
                    operationAllCustomer();
                    break;
                case 7:
                    InsuranceManager.displayMainMenu();
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
                if (LoadDataBase.findCard(insuranceCardId) == null) {
                    try {
                        Customer c = new PolicyHolder(generateCustomerId(),fullName, insuranceCardId);
                        LoadDataBase.customerList.add(c);
                        LoadDataBase.policyHolderList.add(c);
                    } catch (Exception e) {
                        System.out.println("Error while creating new policy holder");
                        throw e;
                    }
                    Thread.sleep(1000);
                    System.out.println("Done creating!!!");
                } else {
                    System.out.println("This card already have another policy holder");
                }
                Thread.sleep(1000);
            }
            if ( role == 2) {
                try {
                    if ( LoadDataBase.findPolicyHolderByCardId(insuranceCardId) != null ) {
                        Customer c = new Dependent(generateCustomerId(),fullName, insuranceCardId);
                        LoadDataBase.customerList.add(c);
                        LoadDataBase.dependentList.add(c);
                        ((PolicyHolder) LoadDataBase.findPolicyHolderByCardId(insuranceCardId)).getListDependents().add(c);
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
                        if ( LoadDataBase.findPolicyHolderByCardId(insuranceCardId) != null ) {
                            foundC.setInsuranceCard(insuranceCardId);
                            ((PolicyHolder) LoadDataBase.findPolicyHolderByCardId(insuranceCardId)).getListDependents().add(foundC);
                            ((PolicyHolder) LoadDataBase.findPolicyHolderByCardId(oldInsuranceCardId)).getListDependents().delete(foundC.getId());
                        }
                    }
                    if (foundC instanceof PolicyHolder) {
                        if ( LoadDataBase.findPolicyHolderByCardId(insuranceCardId) != null ) {
                            System.out.println("This card already have a policyholder");
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
                if ( foundC instanceof Dependent ) {
                    if (LoadDataBase.findPolicyHolderByCardId(foundC.getInsuranceCard()) != null) {
                        ((PolicyHolder) LoadDataBase.findPolicyHolderByCardId(foundC.getInsuranceCard())).getListDependents().delete(foundC.getId());
                    }
                    LoadDataBase.dependentList.remove(foundC);
                } else {
                    LoadDataBase.policyHolderList.remove(foundC);
                }
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
        System.out.println();
        displayAllCustomer();
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
    }

    //Auto Generating Customer ID Function
    public static String generateCustomerId() {
        cusAmount = cusAmount + 1;
        int loopAdd = 7 - Integer.toString(cusAmount).length();
        return "c-" + "0".repeat(Math.max(0, loopAdd)) + (cusAmount);
    }
}
