package InsuranceSystem;

import Claim.Claim;
import Customer.*;
import DataSource.*;
import java.io.FileNotFoundException;
import java.text.ParseException;


public class InsuranceManager{
    public static void main(String[] args) throws FileNotFoundException, ParseException {
        LoadDataBase.createCustomers();
        LoadDataBase.createCards();
        LoadDataBase.createDocuments();
        LoadDataBase.createClaims();

        /*Customer c1 = new Dependent("0000013","Tatiana Avila","0000000001");
        ((PolicyHolder) LoadDataBase.findCustomer("0000001")).addDependent(c1);*/

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
    }
}
