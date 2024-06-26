package DataSource;

/**
 * @author <Ngo Quang Khai - s3975831>
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import Customer.*;
import Claim.*;
import InsuranceSystem.*;

public class LoadDataBase {
    public static List<Customer> customerList = new ArrayList<>();
    public static List<Customer> dependentList = new ArrayList<>();
    public static List<Customer> policyHolderList = new ArrayList<>();
    public static List<Document> documentList = new ArrayList<>();
    public static List<InsuranceCard> cardList = new ArrayList<>();
    public static List<Claim> claimList = new ArrayList<>();

    public static void createCustomers() throws FileNotFoundException {
        BufferedReader reader1 = new BufferedReader(new FileReader("src/DataSource/PolicyHolders.txt"));
        BufferedReader reader2 = new BufferedReader(new FileReader("src/DataSource/Dependents.txt"));

        for(String line: reader2.lines().toList()) {
            String[] parts = line.split(",");
            Customer c = new Dependent(parts[0],parts[1],parts[2]);
            customerList.add(c);
            dependentList.add(c);
        }

        for(String line: reader1.lines().toList()) {
            ArrayList<Customer> dependentList = new ArrayList<Customer>();
            String[] parts = line.split(",");
            Customer c = new PolicyHolder(parts[0],parts[1],parts[2]);
            customerList.add(c);
            policyHolderList.add(c);
        }

        for(Customer c : policyHolderList) {
            ((PolicyHolder) c).setListDependents(new ListDependentOfCustomer(makeDependentList(c.getInsuranceCard())));
        }

    }

    public static void createCards() throws FileNotFoundException, ParseException {
        BufferedReader reader1 = new BufferedReader(new FileReader("src/DataSource/InsuranceCards.txt"));

        for(String line: reader1.lines().toList()) {
            String[] parts = line.split(",");
            InsuranceCard card = new InsuranceCard(parts[0],findPolicyHolder(parts[1]),parts[2], parts[3]);
            cardList.add(card);
        }
    }

    public static void createDocuments() throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new FileReader("src/DataSource/Documents.txt"));
        for(String line: reader.lines().toList()) {
            String[] parts = line.split(",");
            Document d = new Document(parts[0], parts[1], parts[2]);
            documentList.add(d);
        }
    }

    public static void createClaims() throws FileNotFoundException, ParseException {
        BufferedReader reader = new BufferedReader(new FileReader("src/DataSource/Claims.txt"));
        for(String line: reader.lines().toList()) {
            String[] parts = line.split(",");
            Claim claim = new Claim(parts[0], parts[1], findCustomer(parts[2]), parts[3], parts[4], makeDocumentsList(parts[0]), parts[5], parts[6], parts[7]);
            claimList.add(claim);
        }

        for (Customer p: customerList) {
            p.setListClaims(new ListClaimOfCustomer(makeClaimsList(p.getId())));
        }
    }

    // Find a first Customer has the same ID with cID
    public static Customer findCustomer(String cID) {
        return customerList.stream().filter(customer -> customer.getId().equals(cID)).findFirst().orElse(null);
    }

    // Find a first PolicyHolder has the same ID with cID
    public static Customer findPolicyHolder(String cID) {
        return policyHolderList.stream().filter(customer -> customer.getId().equals(cID)).findFirst().orElse(null);
    }

    public static Customer findPolicyHolderByCardId(String cardID) {
        return policyHolderList.stream().filter(holder -> holder.getInsuranceCard().equals(cardID)).findFirst().orElse(null);
    }

    // Create a list of dependent base on the same InsuranceCard and then set PolicyHolders' listDependents by those list
    public static List<Customer> makeDependentList(String insuranceCardId) {
        return dependentList.stream().filter(customer -> customer.getInsuranceCard().equals(insuranceCardId)).collect(Collectors.toList());
//        return dependentList.stream().filter(customer -> customer.getInsuranceCard().equals(insuranceCardId)).collect(Collectors.toCollection(ArrayList<Customer>::new));
    }

    public static List<Document> makeDocumentsList(String claimId) {
        return documentList.stream().filter(document -> document.getClaimId().equals(claimId)).collect(Collectors.toList());
//        return documentList.stream().filter(document -> document.getClaimId().equals(claimId)).collect(Collectors.toCollection(ArrayList<Document>::new));
    }

    public static List<Claim> makeClaimsList(String cID) {
        return claimList.stream().filter(aClaim -> aClaim.getInsuredPerson().getId().equals(cID)).collect(Collectors.toList());
//        return claimList.stream().filter(aClaim -> aClaim.getInsuredPerson().getId().equals(cID)).collect(Collectors.toCollection(ArrayList<Claim>::new));
    }


    // Find a first insuranceCard has the same cardId with cardID
    public static InsuranceCard findCard(String cardID) {
        return LoadDataBase.cardList.stream().filter(card -> card.getCardId().equals(cardID)).findFirst().orElse(null);
    }

    public static void createAll() throws FileNotFoundException, ParseException {
        try {
            createCustomers();
            createCards();
            createDocuments();
            createClaims();
        } catch (FileNotFoundException e) {
            System.out.println("File error");
            throw e;
        } catch (ParseException e) {
            System.out.println("Error while parsing value");
            throw e;
        }
    }

}
