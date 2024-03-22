package Claim;

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
//    private String status;
    private String infoBank;

    enum Status {
        New,
        Processing,
        Done
    }

    private Status status;

    public Claim(String id, String claimDate, Customer insuredPerson, String cardNumber, String examDate, List<Document> documents, String claimAmounts, String statusString, String infoBank) throws ParseException {
        this.id = id;
        this.claimDate = new SimpleDateFormat("dd/MM/yyyy").parse(claimDate);
        this.insuredPerson = insuredPerson;
        this.cardNumber = cardNumber;
        this.examDate = new SimpleDateFormat("dd/MM/yyyy").parse(examDate);
        this.documents = documents;
        this.claimAmounts = claimAmounts;
        this.status = Status.valueOf(statusString);
        this.infoBank = infoBank;
    }

    public Claim(String id, String claimDate, Customer insuredPerson, String cardNumber, String examDate, String claimAmounts, String infoBank) throws ParseException {
        this.id = "f-" + id;
        this.claimDate = new SimpleDateFormat("dd/MM/yyyy").parse(claimDate);
        this.insuredPerson = insuredPerson;
        this.cardNumber = cardNumber;
        this.examDate = new SimpleDateFormat("dd/MM/yyyy").parse(examDate);
        this.documents = new ArrayList<>();
        this.claimAmounts = claimAmounts;
        this.status = Status.New;
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
