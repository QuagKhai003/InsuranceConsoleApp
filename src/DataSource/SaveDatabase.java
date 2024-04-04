package DataSource;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import Claim.*;
import Customer.*;
import InsuranceSystem.InsuranceCard;

public class SaveDatabase {
    public static void writeAll() throws IOException {
        try {
            writePolicyHolder("src/DataSource/PolicyHolders.txt", LoadDataBase.policyHolderList);
            writeDependent("src/DataSource/Dependents.txt", LoadDataBase.dependentList);
            writeInsuranceCard("src/DataSource/InsuranceCards.txt", LoadDataBase.cardList);
            writeClaim("src/DataSource/Claims.txt", LoadDataBase.claimList);
            writeDocument("src/DataSource/Documents.txt", LoadDataBase.documentList);
        } catch (IOException e) {
            System.out.println("Error in saving all");
        }
    }

    public static void writePolicyHolder(String pathName, List<Customer> policyHolderList ) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(pathName, false));
        try {
            for (Customer c: policyHolderList) {
                String policyholderData = c.getId() + "," + c.getFullName() + "," + c.getInsuranceCard();
                writer.write(policyholderData);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error in saving Policy Holder");
        }
    }
    public static void writeDependent(String pathName, List<Customer> dependentList ) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(pathName, false));
        try {
            for (Customer c: dependentList) {
                String dependentData = c.getId() + "," + c.getFullName() + "," + c.getInsuranceCard();
                writer.write(dependentData);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error in saving Dependent");
        }
    }

    public static void writeClaim(String pathName, List<Claim> claimList ) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(pathName, false));
        try {
            for (Claim c: claimList) {
                String status = String.valueOf(c.getStatus()).toUpperCase();
                String claimDateData = new SimpleDateFormat("dd/MM/yyyy").format(c.getClaimDate());
                String examDateData = new SimpleDateFormat("dd/MM/yyyy").format(c.getExamDate());
                String claimData = c.getId() + "," + claimDateData + "," + c.getInsuredPerson().getId()
                        + "," + c.getCardNumber() + "," + examDateData + "," + c.getClaimAmounts() + "," + status + "," + c.getInfoBank();
                writer.write(claimData);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error in saving claim");
        }
    }

    public static void writeInsuranceCard(String pathName, List<InsuranceCard> cardList ) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(pathName, false));
        try {
            for (InsuranceCard c: cardList) {
                String expireDateData = new SimpleDateFormat("dd/MM/yyyy").format(c.getExpireDate());
                String insuranceCardData = c.getCardId() + "," + c.getCardHolder().getId() + "," + c.getPolicyOwner()
                        + "," + expireDateData;
                writer.write(insuranceCardData);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error in saving insurance card");
        }
    }

    public static void writeDocument(String pathName, List<Document> documentList ) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(pathName, false));
        try {
            for (Document c: documentList) {
                String documentData = c.getClaimId() + "," + c.getCardNumber() + "," + c.getDocumentName();
                writer.write(documentData);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error in saving Documents");
        }
    }
}
