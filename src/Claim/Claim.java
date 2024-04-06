package Claim;

/**
 * @author <Ngo Quang Khai - s3975831>
 */

import Customer.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Claim {
    private String id;
    private Date claimDate;
    private Customer insuredPerson;
    private String cardNumber;
    private Date examDate;
    private List<Document> documents;
    private String claimAmounts;
    private String infoBank;

    public enum Status {
        NEW,
        PROCESSING,
        DONE
    }

    private Status status;

    public Claim() {
        this.id = "Default";
        this.claimDate = null;
        this.insuredPerson = null;
        this.cardNumber = "Default";
        this.examDate = null;
        this.documents = null;
        this.claimAmounts = "Default";
        this.infoBank = "Default";
        this.status = Status.NEW;
    }

    public Claim(String id, String claimDate, Customer insuredPerson, String cardNumber, String examDate, List<Document> documents, String claimAmounts, String statusString, String infoBank) throws ParseException {
        this.id = id;
        this.claimDate = new SimpleDateFormat("dd/MM/yyyy").parse(claimDate);
        this.insuredPerson = insuredPerson;
        this.cardNumber = cardNumber;
        this.examDate = new SimpleDateFormat("dd/MM/yyyy").parse(examDate);
        this.documents = documents;
        this.claimAmounts = claimAmounts;
        this.status = Status.valueOf(statusString.toUpperCase());
        this.infoBank = infoBank;
    }

    public Claim(String id, String claimDate, Customer insuredPerson, String cardNumber, String examDate, String claimAmounts, String infoBank) throws ParseException {
        this.id = id;
        this.claimDate = new SimpleDateFormat("dd/MM/yyyy").parse(claimDate);
        this.insuredPerson = insuredPerson;
        this.cardNumber = cardNumber;
        this.examDate = new SimpleDateFormat("dd/MM/yyyy").parse(examDate);
        this.documents = new ArrayList<>();
        this.claimAmounts = claimAmounts;
        this.status = Status.NEW;
        this.infoBank = infoBank;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Customer getInsuredPerson() {
        return insuredPerson;
    }

    public void setInsuredPerson(Customer insuredPerson) {
        this.insuredPerson = insuredPerson;
    }

    public Date getClaimDate() {
        return claimDate;
    }

    public void setClaimDate(Date claimDate) {
        this.claimDate = claimDate;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Date getExamDate() {
        return examDate;
    }

    public void setExamDate(Date examDate) {
        this.examDate = examDate;
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }

    public String getClaimAmounts() {
        return claimAmounts;
    }

    public void setClaimAmounts(String claimAmounts) {
        this.claimAmounts = claimAmounts;
    }

    public String getInfoBank() {
        return infoBank;
    }

    public void setInfoBank(String infoBank) {
        this.infoBank = infoBank;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        return Integer.parseInt(this.getId().substring(2));
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj  instanceof Claim)) {
            return false;
        }

        Claim otherClaim = (Claim) obj;

        return this.getId().equals(otherClaim.getId());
    }

    @Override
    public String toString() {
        return "Claim{" +
                "id='" + id + '\'' +
                ", claimDate=" + claimDate +
                ", insuredPerson=" + insuredPerson.getFullName() +
                ", cardNumber='" + cardNumber + '\'' +
                ", examDate=" + examDate +
                ", documents=" + documents +
                ", claimAmounts='" + claimAmounts + '\'' +
                ", infoBank='" + infoBank + '\'' +
                ", status=" + status +
                '}';
    }
}
