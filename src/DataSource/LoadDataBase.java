package DataSource;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.ParseException;
import java.util.ArrayList;
import Customer.*;
import Claim.*;
import InsuranceSystem.*;

public class LoadDataBase {
    public static ArrayList<Customer> customerList = new ArrayList<>();
    public static ArrayList<InsuranceCard> cardList = new ArrayList<>();
    public static ArrayList<Claim> claimList = new ArrayList<>();

    public static void createCustomers() throws FileNotFoundException {
        BufferedReader reader1 = new BufferedReader(new FileReader("src/DataSource/PolicyHolders.txt"));
        BufferedReader reader2 = new BufferedReader(new FileReader("src/DataSource/Dependents.txt"));

        for(String line: reader1.lines().toList()) {
            String[] parts = line.split(",");
            Customer c = new PolicyHolder(parts[0],parts[1],parts[2]);
            customerList.add(c);
        }

        for(String line: reader2.lines().toList()) {
            String[] parts = line.split(",");
            Customer c = new Dependent(parts[0],parts[1],parts[2]);
            customerList.add(c);
        }
    }

    public static void createCards() throws FileNotFoundException, ParseException {
        BufferedReader reader1 = new BufferedReader(new FileReader("src/DataSource/InsuranceCards.txt"));

        for(String line: reader1.lines().toList()) {
            String[] parts = line.split(",");
            InsuranceCard card = new InsuranceCard(parts[0],findCustomer(parts[1]),findCustomer(parts[2]), parts[3]);
            cardList.add(card);
        }

    }

    public static Customer findCustomer(String cID) {
        return LoadDataBase.customerList.stream().filter(customer -> customer.getId().equals("c-" + cID)).findFirst().orElse(null);
    }

    public static InsuranceCard findCard(String cardID) {
        return LoadDataBase.cardList.stream().filter(card -> card.getCardId().equals(cardID)).findFirst().orElse(null);
    }

}
