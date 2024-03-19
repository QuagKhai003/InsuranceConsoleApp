package InsuranceSystem;

import Customer.Customer;
import DataSource.*;
import java.io.FileNotFoundException;
import java.text.ParseException;


public class InsuranceManager{
    public static void main(String[] args) throws FileNotFoundException, ParseException {
        LoadDataBase.createCustomers();
        LoadDataBase.createCards();

        for(Customer c: LoadDataBase.customerList) {
            System.out.println(c);
        }

        for(InsuranceCard c: LoadDataBase.cardList) {
            System.out.println(c);
        }
    }
}
