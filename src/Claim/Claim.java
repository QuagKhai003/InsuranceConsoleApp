package Claim;

import Customer.Customer;

import java.util.ArrayList;
import java.util.Date;

public class Claim {
    private String id;
    private Date claimDate;
    private Customer insuredPerson;
    private String cardNumber;
    private Date examDate;
    private  ArrayList<String> documents;
    private String claimAmounts;
    private String status;
    private String infoBank;

    public Claim(String id, Date claimDate, Customer insuredPerson, String cardNumber, Date examDate, String claimAmounts, String status, String infoBank) {
        this.id = "f-" + id;
        this.claimDate = claimDate;
        this.insuredPerson = insuredPerson;
        this.cardNumber = cardNumber;
        this.examDate = examDate;
        this.documents = new ArrayList<String>();
        this.claimAmounts = claimAmounts;
        this.status = status;
        this.infoBank = infoBank;
    }
}
