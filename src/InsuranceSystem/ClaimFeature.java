package InsuranceSystem;

import Customer.Customer;
import DataSource.*;
import Claim.*;

import java.sql.SQLOutput;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ClaimFeature {
    private static int claimAmount = Integer.parseInt(LoadDataBase.claimList.get(LoadDataBase.claimList.size()-1).getId().substring(2));

    public static void displayAllClaim() {
        int index = 0;
        for (Claim claim: LoadDataBase.claimList) {
            System.out.println(index + ". " + claim);
            index = index + 1;
        }
    }

    public static void displayAllDocumentInClaim(Claim claim) {
        int index = 0;
        for(Document d: claim.getDocuments()) {
            System.out.println(index + ". " + d);
            index = index + 1;
        }
    }

    public static void displayAllDocument() {
        for(Document d: LoadDataBase.documentList) {
            System.out.println(d);
        }
    }

    public static void operationClaim() throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("-------------------Claim Menu---------------------");
        System.out.println("1. Make a claim");
        System.out.println("2. Update a claim");
        System.out.println("3. Delete a claim");
        System.out.println("4. Display all claim");
        System.out.println("5. Go Back");
        System.out.println("Please choose action : ");
        if (scanner.hasNextInt()) {
            int action = scanner.nextInt();
            switch (action) {
                case 1:
                    CustomerFeature.displayAllCustomer();
                    makeClaim();
                    operationClaim();
                    break;
                case 2:
                    displayAllClaim();
                    Claim claim = chooseClaim();
                    updateClaim(claim);
                    operationClaim();
                    break;
                case 3:
                    displayAllClaim();
                    deleteClaim();
                    operationClaim();
                    break;
                case 4:
                    displayAllClaim();
                    operationClaim();
                    break;
                case 5:
                    break;
                default:
                    System.out.println();
                    System.out.println("Wrong number please enter again!");
                    System.out.println();
                    operationClaim();
                    break;
            }
        } else {
            String invalidInput = scanner.nextLine();
            System.out.println();
            System.out.println("Invalid input: " + invalidInput);
            System.out.println("Please enter a valid number.");
            System.out.println();
            Thread.sleep(1000);
            operationClaim();
        }
    }

    public static Claim chooseClaim() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("-------------------Choose a Claim---------------------");
        System.out.println("Enter the index of a claim you want to update: ");
        int claimIndex = scanner.nextInt();
        return LoadDataBase.claimList.get(claimIndex);
    }

    public static void makeClaim() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("-------------------Make a Claim---------------------");
        System.out.println("Enter the id of insured people: ");
        String insuredPerson =  scanner.nextLine();
        System.out.println("Enter the claim date (DD/MM/YYYY): ");
        String claimDate = scanner.nextLine();
        System.out.println("Enter the exam date (DD/MM/YYYY): ");
        String examDate = scanner.nextLine();
        System.out.println("Enter the claim amount: ");
        String claimAmount = scanner.nextLine();
        System.out.println("Enter the bank info (Bank – Name – Number): ");
        String bankInfo = scanner.nextLine();
        System.out.println("Do you want to create this customer(Y/N):");
        String answer = scanner.nextLine();
        if (answer.equalsIgnoreCase("y")) {
            System.out.println("Processing...");
            Customer foundCus = LoadDataBase.findCustomer(insuredPerson);
            if ( foundCus != null ) {
                try {
                    Claim newClaim = new Claim(generateClaimId(), claimDate, foundCus, foundCus.getInsuranceCard(), examDate, claimAmount,bankInfo);
                    LoadDataBase.claimList.add(newClaim);
                    Thread.sleep(500);
                    System.out.println("Create claim successfully...");
                    System.out.println("Do you want to create documents for this claim? (Y/N): ");
                    String answer2 = scanner.nextLine();
                    if (answer2.equalsIgnoreCase("y")) {
                        System.out.println("Processing...");
                        createDocument(newClaim);
                    }
                } catch (ParseException e) {
                    System.out.println("Error when parsing the data value");
                }
            } else {
                System.out.println("This customer id is not existed");
                System.out.println("Can not make a claim");
            }
            Thread.sleep(1000);
        }
        System.out.println("Exiting making claim process...");
        Thread.sleep(1000);
    }

    public static void updateClaim(Claim foundClaim) throws Exception {
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
            System.out.println("10. Go to Main Menu");
            System.out.println("Enter the category you want to update: ");
            if (scanner.hasNextInt()) {
                int action = scanner.nextInt();
                switch (action) {
                    case 1:
                        updateClaimInsuredPeople(foundClaim);
                        updateClaim(foundClaim);
                        break;
                    case 2:
                        updateClaimClaimDate(foundClaim);
                        updateClaim(foundClaim);
                        break;
                    case 3:
                        updateClaimExamDate(foundClaim);
                        updateClaim(foundClaim);
                        break;
                    case 4:
                        updateClaimClaimAmount(foundClaim);
                        updateClaim(foundClaim);
                        break;
                    case 5:
                        updateDocumentMenu(foundClaim);
                        updateClaim(foundClaim);
                        break;
                    case 6:
                        updateClaimBankInfo(foundClaim);
                        updateClaim(foundClaim);
                        break;
                    case 7:
                        updateClaimStatus(foundClaim);
                        updateClaim(foundClaim);
                        break;
                    case 8:
                        displayAllClaim();
                        Claim claim = chooseClaim();
                        updateClaim(claim);
                        break;
                    case 9:
                        break;
                    case 10:
                        InsuranceManager.displayMainMenu();
                        break;
                    default:
                        System.out.println();
                        System.out.println("Wrong number please enter again!");
                        System.out.println();
                        updateClaim(foundClaim);
                        break;
                }
            } else {
                String invalidInput = scanner.nextLine();
                System.out.println();
                System.out.println("Invalid input: " + invalidInput);
                System.out.println("Please enter a valid number.");
                System.out.println();
                Thread.sleep(1000);
                updateClaim(foundClaim);
            }
        }
    }

    public static void deleteClaim() throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("-------------------Delete a Claim---------------------");
        System.out.println("Enter the index of a claim you want to delete: ");
        int oldClaim = scanner.nextInt();
        Claim foundClaim = LoadDataBase.claimList.get(oldClaim);
        System.out.println();
        if (foundClaim == null) {
            System.out.println("This claim is not existed");
        } else {
            System.out.println("Your chosen claim: " + foundClaim);
            scanner.nextLine();
            System.out.println("Do you want to delete this (Y/N): ");
            String answer2 = scanner.nextLine();
            if(answer2.equalsIgnoreCase("y")) {
                try {
                    foundClaim.getInsuredPerson().getListClaims().delete(foundClaim);
                    LoadDataBase.claimList.remove(foundClaim);
                    for ( Document d: foundClaim.getDocuments()) {
                        LoadDataBase.documentList.remove(d);
                    }
                    System.out.println("Delete a claim successfully");
                    System.out.println("Exiting deleting process...");
                } catch (Exception e) {
                    System.out.println("Error while deleting a claim");
                    throw new Exception(e);
                }
            }
            System.out.println("Exiting deleting a claim process");
        }
        Thread.sleep(1000);
    }

    public static void updateClaimInsuredPeople(Claim claim) throws InterruptedException {
        CustomerFeature.displayAllCustomer();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the id of new insured people: ");
        String newInsuredPeople = scanner.nextLine();
        Customer foundCus = LoadDataBase.findCustomer(newInsuredPeople);
        Customer oldCustomer = claim.getInsuredPerson();
        if(foundCus == null) {
            System.out.println("This customer did not exist");
        } else {
            System.out.println("Your customer from the input: " + foundCus);
            oldCustomer.getListClaims().delete(claim);
            List<Document> documentNeedDelete = new ArrayList<>(claim.getDocuments());
            for (Document d: documentNeedDelete) {
                claim.getDocuments().remove(d);
                LoadDataBase.documentList.remove(d);
            }
            claim.setInsuredPerson(foundCus);
            claim.setCardNumber(foundCus.getInsuranceCard());
            foundCus.getListClaims().add(claim);
            System.out.println("Update new insured people successfully");
            System.out.println("Going back to update option menu...");
            Thread.sleep(1000);
        }
    }

    public static void updateClaimClaimDate(Claim claim) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a new claim date (DD/MM/YYYY): ");
        String claimDate = scanner.nextLine();
        try {
            claim.setClaimDate(new SimpleDateFormat("dd/MM/yyyy").parse(claimDate));
            System.out.println("Update new claim date successfully");
        } catch (ParseException e) {
            System.out.println("Cannot update new claim date");
            Thread.sleep(500);
            throw new RuntimeException(e);
        }
        System.out.println("Going back to update option menu...");
        Thread.sleep(1000);
    }

    public static void updateClaimExamDate(Claim claim) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a new claim date (DD/MM/YYYY): ");
        String examDate = scanner.nextLine();
        try {
            claim.setExamDate(new SimpleDateFormat("dd/MM/yyyy").parse(examDate));
            System.out.println("Update new exam date successfully");
        } catch (ParseException e) {
            System.out.println("Cannot update new exam date");
            Thread.sleep(500);
            throw new RuntimeException(e);
        }
        System.out.println("Going back to update option menu...");
        Thread.sleep(1000);
    }

    public static void updateClaimClaimAmount(Claim claim) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a new claim amount: ");
        String claimAmount = scanner.nextLine();
        try {
            claim.setClaimAmounts(claimAmount + "$");
            System.out.println("Update new claim amount successfully");
        } catch (RuntimeException e) {
            System.out.println("Cannot update new claim amount");
            Thread.sleep(500);
            throw new RuntimeException(e);
        }
        System.out.println("Going back to update option menu...");
        Thread.sleep(1000);
    }

    public static void updateClaimBankInfo(Claim claim) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a new bank info (Format: Bank – Name – Number): ");
        String bankInfo = scanner.nextLine();
        try {
            claim.setInfoBank(bankInfo);
            System.out.println("Update new bank info successfully");
        } catch (RuntimeException e) {
            System.out.println("Cannot update new bank info");
            Thread.sleep(500);
            throw new RuntimeException(e);
        }
        System.out.println("Going back to update option menu...");
        Thread.sleep(1000);
    }

    public static void updateClaimStatus(Claim claim) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose a new status for this claim: ");
        System.out.println("1. NEW");
        System.out.println("2. PROCESSING");
        System.out.println("3. DONE");
        try {
            if(scanner.hasNext()) {
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        claim.setStatus(Claim.Status.NEW);
                        break;
                    case 2:
                        claim.setStatus(Claim.Status.PROCESSING);
                        break;
                    case 3:
                        claim.setStatus(Claim.Status.DONE);
                        break;
                    default:
                        System.out.println();
                        System.out.println("Wrong number please enter again!");
                        System.out.println();
                        updateClaimStatus(claim);
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println("Can not set new status for claim");
            throw new Exception(e);
        }
        Thread.sleep(1000);
    }

    public static void updateDocumentMenu(Claim claim) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("-------------------Update Document Menu---------------------");
        System.out.println("1. Create Document");
        System.out.println("2. Update Document");
        System.out.println("3. Delete Document");
        System.out.println("4. List all document of this claim");
        System.out.println("5. Exit update document process");
        System.out.println("Enter the action: ");
        if (scanner.hasNextInt()) {
            int action = scanner.nextInt();
            switch (action) {
                case 1:
                    createDocument(claim);
                    updateDocumentMenu(claim);
                    break;
                case 2:
                    updateDocument(claim);
                    updateDocumentMenu(claim);
                    break;
                case 3:
                    deleteDocument(claim);
                    updateDocumentMenu(claim);
                    break;
                case 4:
                    displayAllDocumentInClaim(claim);
                    updateDocumentMenu(claim);
                    break;
                case 5:
                    break;
                default:
                    System.out.println();
                    System.out.println("Wrong number please enter again!");
                    System.out.println();
                    updateDocumentMenu(claim);
                    break;
            }
        } else {
            String invalidInput = scanner.nextLine();
            System.out.println();
            System.out.println("Invalid input: " + invalidInput);
            System.out.println("Please enter a valid number.");
            System.out.println();
            Thread.sleep(1000);
            updateDocumentMenu(claim);
        }
    }

    public static void createDocument(Claim Claim) {
        String createLoop = "y";
        Scanner scanner = new Scanner(System.in);
        while(createLoop.equalsIgnoreCase("y")) {
            System.out.println("Enter the name of document");
            String documentName = scanner.nextLine();
            Document document = new Document(Claim.getId(), Claim.getCardNumber(), documentName);
            LoadDataBase.documentList.add(document);
            Claim.getDocuments().add(document);
            System.out.println("Do you want to create more (Y/N):");
            createLoop = scanner.nextLine();
        }
    }

    public static void updateDocument(Claim claim) throws InterruptedException {
        String createLoop = "y";
        Scanner scanner = new Scanner(System.in);
        while(createLoop.equalsIgnoreCase("y")) {
            displayAllDocumentInClaim(claim);
            System.out.println("Choose an index of document you want to update");
            int documentIndex = scanner.nextInt();
            Document document = claim.getDocuments().get(documentIndex);
            System.out.println();
            System.out.println("Your chosen document: " + document);
            scanner.nextLine();
            System.out.println("Enter new document name: ");
            String documentName = scanner.nextLine();
            if (!documentName.equals(document.getDocumentName())) {
                document.setDocumentName(documentName);
            }
            System.out.println("Do you want to update more (Y/N)");
            createLoop = scanner.nextLine();
        }
    }

    public static void deleteDocument(Claim claim) throws Exception {
        String createLoop = "y";
        Scanner scanner = new Scanner(System.in);
        while(createLoop.equalsIgnoreCase("y")) {
            displayAllDocumentInClaim(claim);
            System.out.println("Choose an index of document you want to delete");
            int documentIndex = scanner.nextInt();
            Document document = claim.getDocuments().get(documentIndex);
            System.out.println("Your chosen document: " + document);
            scanner.nextLine();
            System.out.println("Do you want to delete this document (Y/N): ");
            String answer2 = scanner.nextLine();
            if(answer2.equalsIgnoreCase("y")) {
                try {
                    claim.getDocuments().remove(document);
                    LoadDataBase.documentList.remove(document);
                    System.out.println("Delete document successfully");
                    Thread.sleep(200);
                } catch (Exception e) {
                    System.out.println("Error when deleting document");
                    throw new Exception(e);
                }

            }
            System.out.println("Do you want to delete more (Y/N)");
            createLoop = scanner.nextLine();
        }
    }

    //Auto Generating Claim ID Function
    public static String generateClaimId() {
        claimAmount = claimAmount + 1;
        int loopAdd = 10 - Integer.toString(claimAmount).length();
        return "f-" + "0".repeat(Math.max(0, loopAdd)) + (claimAmount);
    }
}
