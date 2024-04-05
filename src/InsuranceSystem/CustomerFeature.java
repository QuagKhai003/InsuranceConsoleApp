package InsuranceSystem;

import Claim.*;
import Customer.*;
import DataSource.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class CustomerFeature {
    private static int cusAmount = Integer.parseInt(LoadDataBase.customerList.get(LoadDataBase.customerList.size()-1).getId().substring(2));

    public static void displayAllCustomer() {
        int index = 0;
        for (Customer c: LoadDataBase.customerList) {
            System.out.println(index + ". " + c);
            index = index + 1;
        }
    }

    public static void displayAllPolicyHolder() {
        int index = 0;
        for (Customer c: LoadDataBase.policyHolderList) {
            System.out.println(index + ". " + c);
            index = index + 1;
        }
    }

    public static void displayAllDependent() {
        int index = 0;
        for (Customer c: LoadDataBase.dependentList) {
            System.out.println(index + ". " + c);
            index = index + 1;
        }
    }

    public static void displayClaimFromCustomer(Customer customer) {
        int index = 0;
        List<Claim> listClaim = customer.getListClaims().getAll();
        for (Claim c: listClaim) {
            System.out.println(index + ". " + c);
            index = index + 1;
        }
    }

    public static Customer chooseCustomer() {
        displayAllCustomer();
        Scanner scanner = new Scanner(System.in);
        System.out.println("PLease enter the index of customer you want to operate:");
        int indexCustomer = 0;
        while (scanner.hasNext()) {
            if(scanner.hasNextInt()) {
                indexCustomer = scanner.nextInt();
                break;
            } else {
                System.out.println("Please enter a valid index");
                scanner.next();
            }
        }
        return LoadDataBase.customerList.get(indexCustomer);
    }

    public static Claim chooseClaimFromCustomer(Customer customer) {
        displayClaimFromCustomer(customer);
        Scanner scanner = new Scanner(System.in);
        System.out.println("PLease enter the index of the claim:");
        int indexClaim = 0;
        while (scanner.hasNext()) {
            if(scanner.hasNextInt()) {
                indexClaim = scanner.nextInt();
                break;
            } else {
                System.out.println("Please enter a valid index");
                scanner.next();
            }
        }
        return customer.getListClaims().getOne(indexClaim);
    }

    public static void operationAllCustomer () throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("-------------------All Customers---------------------");
        System.out.println("1. Create a customer ");
        System.out.println("2. Update a customer");
        System.out.println("3. Delete a customer");
        System.out.println("4. View dependents of a policy holder");
        System.out.println("5. Find policy holder of a dependent");
        System.out.println("6. View claim of a customer");
        System.out.println("7. View all customer");
        System.out.println("8. Go Back");
        System.out.println("Please choose action : ");
        if (scanner.hasNextInt()) {
            int action = scanner.nextInt();
            switch (action) {
                case 1:
                    addCustomer();
                    operationAllCustomer();
                    break;
                case 2:
                    Customer customer1 = chooseCustomer();
                    updateCustomerMenu(customer1);
                    operationAllCustomer();
                    break;
                case 3:
                    Customer customer2 = chooseCustomer();
                    deleteCustomer(customer2);
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
                    Customer customer = chooseCustomer();
                    updateClaimListMenu(customer);
                    operationAllCustomer();
                    break;
                case 7:
                    displayAllCustomer();
                    operationAllCustomer();
                case 8:
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
                        System.out.println("This card did not exist");
                        System.out.println("Assign new customer to a empty card");
                        Customer c = new PolicyHolder(generateCustomerId(),fullName, "null");
                        LoadDataBase.customerList.add(c);
                        LoadDataBase.policyHolderList.add(c);
                        System.out.println("Please create insurance card of this policy holder afterward");
                    } catch (Exception e) {
                        System.out.println("Error while creating new policy holder");
                        throw e;
                    }
                    Thread.sleep(1000);
                    System.out.println("Done creating!!!");
                } else {
                    System.out.println("This card already have another policy holder");
                    System.out.println("Exit without creating");
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
                        System.out.println("Please create a policy holder with empty insurance card id first");
                        System.out.println("Then create insurance card of this policy holder");
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

    public static void deleteCustomer(Customer customer) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Your chosen customer: "+ customer);
        System.out.println("Do you sure to delete this customer (Y/N):");
        String answer = scanner.nextLine();
        if (answer.equalsIgnoreCase("y")) {
            System.out.println("Processing...");
            try {
                deleteRelate(customer);
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

    public static void updateCustomerMenu(Customer customer) throws Exception {
        Scanner scanner = new Scanner(System.in);
        if (customer == null) {
            System.out.println("This customer did not exist");
        } else {
            System.out.println("Your chosen customer: " + customer);
            System.out.println("-------------------Update Option---------------------");
            System.out.println("1. Full name ");
            System.out.println("2. List Claim Modify");
            System.out.println("3. Choose another customer");
            System.out.println("4. Go Back");
            System.out.println("5. Go To Main Menu");
            System.out.println("Please choose action : ");
            if (scanner.hasNextInt()) {
                int action = scanner.nextInt();
                switch (action) {
                    case 1:
                        updateFullName(customer);
                        updateCustomerMenu(customer);
                        break;
                    case 2:
                        updateClaimListMenu(customer);
                        updateCustomerMenu(customer);
                        break;
                    case 3:
                        Customer anotherCustomer = chooseCustomer();
                        updateCustomerMenu(anotherCustomer);
                        break;
                    case 4:
                        break;
                    case 5:
                        InsuranceManager.displayMainMenu();
                        break;
                    default:
                        System.out.println();
                        System.out.println("Wrong number please enter again!");
                        System.out.println();
                        updateCustomerMenu(customer);
                        break;
                }
            } else {
                String invalidInput = scanner.nextLine();
                System.out.println();
                System.out.println("Invalid input: " + invalidInput);
                System.out.println("Please enter a valid number.");
                System.out.println();
                Thread.sleep(1000);
                updateCustomerMenu(customer);
            }
        }
        System.out.println("Exiting the updating process");
        Thread.sleep(1000);
    }

    public static void updateFullName(Customer customer) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("-------------------Update Full Name---------------------");
        System.out.println("Enter a new full name: ");
        String fullName = scanner.nextLine();
        System.out.println("Do yo want to update this new name (Y/N): ");
        String answer = scanner.nextLine();
        if (answer.equalsIgnoreCase("y")) {
            try {
                customer.setFullName(fullName);
                System.out.println("Update new name successfully");
            } catch (Exception e) {
                System.out.println("Error when updating new name");
                throw new Exception(e);
            }
        }
        System.out.println("Exiting updating new full name process");
        Thread.sleep(1000);
    }

    public static void updateClaimListMenu(Customer customer) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("-------------------Claim List Menu---------------------");
        System.out.println("1. Make a claim");
        System.out.println("2. Update a claim");
        System.out.println("3. Delete a claim");
        System.out.println("4. Go Back");
        System.out.println("5. Go To Main Menu");
        System.out.println("Please choose action : ");
        if (scanner.hasNextInt()) {
            int action = scanner.nextInt();
            switch (action) {
                case 1:
                    makeAClaimFromCustomer(customer);
                    updateClaimListMenu(customer);
                    break;
                case 2:
                    Claim claim = chooseClaimFromCustomer(customer);
                    if (claim != null) {
                        updateClaimOfCustomer(claim, customer);
                    } else {
                        System.out.println("There is no claim of this customer");
                    }
                    updateClaimListMenu(customer);
                    break;
                case 3:
                    deleteAClaimFromCustomer(customer);
                    updateClaimListMenu(customer);
                    break;
                case 4:
                    break;
                case 5:
                    InsuranceManager.displayMainMenu();
                    break;
                default:
                    System.out.println();
                    System.out.println("Wrong number please enter again!");
                    System.out.println();
                    updateClaimListMenu(customer);
                    break;
            }
        } else {
            String invalidInput = scanner.nextLine();
            System.out.println();
            System.out.println("Invalid input: " + invalidInput);
            System.out.println("Please enter a valid number.");
            System.out.println();
            Thread.sleep(1000);
            updateClaimListMenu(customer);
        }
    }

    public static void makeAClaimFromCustomer(Customer customer) throws ParseException {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("PLease enter the claim amount:");
            String claimAmount = scanner.nextLine();
            System.out.println("PLease enter the bank info (Bank – Name – Number)::");
            String bankInfo = scanner.nextLine();
            String newDate1 = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
            String newDate2 = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
            Claim claim  = new Claim(ClaimFeature.generateClaimId(),newDate1,customer,customer.getInsuranceCard(),newDate2,claimAmount,bankInfo);
            claim.setStatus(Claim.Status.NEW);
            System.out.println("Create claim successfully...");
            customer.getListClaims().add(claim);
            LoadDataBase.claimList.add(claim);
            System.out.println("Do you want to create documents for this claim? (Y/N): ");
            String answer2 = scanner.nextLine();
            if (answer2.equalsIgnoreCase("y")) {
                System.out.println("Processing...");
                ClaimFeature.createDocument(claim);
            }
        } catch (ParseException e) {
            System.out.println("Error when parsing data for new claim");
            throw e;
        }
    }

    public static void deleteAClaimFromCustomer(Customer customer) throws Exception {
        Claim claim = chooseClaimFromCustomer(customer);
        System.out.println("Your chosen claim: " + claim);
        if (claim == null) {
            System.out.println("Can not delete a non existed claim");
        } else {
            System.out.println("Do you want to delete this claim (Y/N): ");
            Scanner scanner = new Scanner(System.in);
            String answer = scanner.nextLine();
            if (answer.equalsIgnoreCase("y")) {
                try {
                    customer.getListClaims().delete(claim);
                    LoadDataBase.claimList.remove(claim);
                    for ( Document d: claim.getDocuments()) {
                        LoadDataBase.documentList.remove(d);
                    }
                    System.out.println("Delete claim successfully...");
                } catch (Exception e) {
                    System.out.println("Error when deleting this claim");
                    throw new Exception(e);
                }
            }
        }
        System.out.println("Exiting deleting process...");
        Thread.sleep(1000);
    }

    public static void updateClaimOfCustomer(Claim foundClaim, Customer customer) throws Exception {
        Scanner scanner = new Scanner(System.in);
        if (foundClaim == null) {
            System.out.println("This claim is not existed");
        } else {
            System.out.println("Your chosen claim for updating process: " + foundClaim);
            System.out.println("-------------------Update Option---------------------");
            System.out.println("1. Insured People");
            System.out.println("2. Claim Date");
            System.out.println("3. Exam Date");
            System.out.println("4. Claim amount");
            System.out.println("5. Documents");
            System.out.println("6. Bank info");
            System.out.println("7. Status");
            System.out.println("8. Choose another claim");
            System.out.println("9. Exit update claim process");
            System.out.println("Enter the category you want to update: ");
            if (scanner.hasNextInt()) {
                int action = scanner.nextInt();
                switch (action) {
                    case 1:
                        ClaimFeature.updateClaimInsuredPeople(foundClaim);
                        updateClaimOfCustomer(foundClaim, customer);
                        break;
                    case 2:
                        ClaimFeature.updateClaimClaimDate(foundClaim);
                        updateClaimOfCustomer(foundClaim, customer);
                        break;
                    case 3:
                        ClaimFeature.updateClaimExamDate(foundClaim);
                        updateClaimOfCustomer(foundClaim, customer);
                        break;
                    case 4:
                        ClaimFeature.updateClaimClaimAmount(foundClaim);
                        updateClaimOfCustomer(foundClaim, customer);
                        break;
                    case 5:
                        ClaimFeature.updateDocumentMenu(foundClaim);
                        updateClaimOfCustomer(foundClaim, customer);
                        break;
                    case 6:
                        ClaimFeature.updateClaimBankInfo(foundClaim);
                        updateClaimOfCustomer(foundClaim, customer);
                        break;
                    case 7:
                        ClaimFeature.updateClaimStatus(foundClaim);
                        updateClaimOfCustomer(foundClaim, customer);
                        break;
                    case 8:
                        Claim claim = chooseClaimFromCustomer(customer);
                        updateClaimOfCustomer(claim, customer);
                        break;
                    case 9:
                        break;
                    default:
                        System.out.println();
                        System.out.println("Wrong number please enter again!");
                        System.out.println();
                        updateClaimOfCustomer(foundClaim, customer);
                        break;
                }
            } else {
                String invalidInput = scanner.nextLine();
                System.out.println();
                System.out.println("Invalid input: " + invalidInput);
                System.out.println("Please enter a valid number.");
                System.out.println();
                Thread.sleep(1000);
                updateClaimOfCustomer(foundClaim, customer);
            }
        }
    }

    public static void viewDependentsOfPolicyHolder() throws InterruptedException {
        displayAllPolicyHolder();
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        System.out.println("Choose your an index of a policy holder you want to see the dependents");
        int chosenIndex = scanner.nextInt();
        Customer desiredPolicyHolder = LoadDataBase.policyHolderList.get(chosenIndex);
        System.out.println("Your chosen policy holder: " + desiredPolicyHolder);
        System.out.println();
        System.out.println("The list dependent of the chosen policy holder:");
        for (Customer dep: ((PolicyHolder) desiredPolicyHolder).getListDependents().getDependentList()) {
            System.out.println(dep);
        }
        System.out.println();
    }

    public static void viewPolicyHolderOfDependent() throws InterruptedException {
        displayAllDependent();
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        System.out.println("Choose your an index of a dependent you want to see the policy holder");
        int chosenIndex = scanner.nextInt();
        Customer desiredDependent = LoadDataBase.dependentList.get(chosenIndex);
        System.out.println("Your chosen policy holder: " + desiredDependent);
        System.out.println();
        System.out.println("The policy holder of the chosen dependent:");
        System.out.println(LoadDataBase.findCard(desiredDependent.getInsuranceCard()).getCardHolder());
        System.out.println();
    }

    public static void deleteRelate(Customer customer) {
        if (customer instanceof Dependent) {
            ((PolicyHolder) LoadDataBase.findPolicyHolderByCardId(customer.getInsuranceCard())).getListDependents().delete(customer);
            LoadDataBase.dependentList.remove(customer);
        } else {
            deleteRelateDependent(customer);
            LoadDataBase.cardList.remove(LoadDataBase.findCard(customer.getInsuranceCard()));
            LoadDataBase.policyHolderList.remove(customer);
            LoadDataBase.customerList.remove(customer);
        }
        deleteRelateClaim(customer);
        LoadDataBase.customerList.remove(customer);
    }

    public static void deleteRelateClaim(Customer customer) {
        ListClaimOfCustomer listClaimOfCustomer = customer.getListClaims();
        List<Claim> claimNeedDelete = new ArrayList<>(listClaimOfCustomer.getAll());
        if (!claimNeedDelete.isEmpty()) {
            for (Claim claimHistory : claimNeedDelete) {
                if (claimHistory != null) {
                    if (!claimHistory.getDocuments().isEmpty()) {
                        List<Document> documentNeedDelete = new ArrayList<>(claimHistory.getDocuments());
                        for (Document d : documentNeedDelete) {
                            claimHistory.getDocuments().remove(d);
                            LoadDataBase.documentList.remove(d);
                        }
                    }
                    listClaimOfCustomer.delete(claimHistory);
                    LoadDataBase.claimList.remove(claimHistory);
                }
            }
        }
    }

    public static void deleteRelateDependent(Customer customer) {
        ListDependentOfCustomer policyHolderDependentList = ((PolicyHolder) customer).getListDependents();
        List<Customer> dependentNeedDelete = new ArrayList<>(policyHolderDependentList.getAll());
        if (!dependentNeedDelete.isEmpty()) {
            for (Customer depend : dependentNeedDelete) {
                if (depend != null) {
                    deleteRelateClaim(depend);
                    policyHolderDependentList.delete(depend);
                    LoadDataBase.dependentList.remove(depend);
                    LoadDataBase.customerList.remove(depend);
                }
            }
        } else {
            System.out.println("This policy Holder did not have any dependent");
        }
    }

    //Auto Generating Customer ID Function
    public static String generateCustomerId() {
        cusAmount = cusAmount + 1;
        int loopAdd = 7 - Integer.toString(cusAmount).length();
        return "c-" + "0".repeat(Math.max(0, loopAdd)) + (cusAmount);
    }
}
