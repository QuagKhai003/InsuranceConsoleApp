package InsuranceSystem;

import Customer.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class InsuranceCard {
    private String cardId;
    private Customer cardHolder;
    private String policyOwner;
    private Date expireDate;

    public InsuranceCard() {
        this.cardId = "Default";
        this.cardHolder = null;
        this.policyOwner = "Default";
        this.expireDate = new Date();
    }

    public InsuranceCard(String cardId, Customer cardHolder, String policyOwner, String expireDate) throws ParseException {
        this.cardId = cardId;
        this.cardHolder = cardHolder;
        this.policyOwner = policyOwner;
        this.expireDate = (new SimpleDateFormat("dd/MM/yyyy").parse(expireDate));
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public Customer getCardHolder() {
        return cardHolder;
    }

    public void setCardHolder(Customer cardHolder) {
        this.cardHolder = cardHolder;
    }

    @Override
    public String toString() {
        /*if (this.cardHolder == null) {
            return "InsuranceCard{" +
                    "cardId='" + cardId + '\'' +
                    ", cardHolder=None" +
                    ", policyOwner=" + policyOwner.getFullName()+
                    ", expireDate=" + expireDate +
                    '}';
        } else if (this.policyOwner == null) {
            return "InsuranceCard{" +
                    "cardId='" + cardId + '\'' +
                    ", cardHolder=" + cardHolder.getFullName() +
                    ", policyOwner=None" +
                    ", expireDate=" + expireDate +
                    '}';
        } else if (this.cardHolder == null && this.policyOwner == null) {
            return "InsuranceCard{" +
                    "cardId='" + cardId + '\'' +
                    ", cardHolder=None" +
                    ", policyOwner=None" +
                    ", expireDate=" + expireDate +
                    '}';
        } else {*/
            return "InsuranceCard{" +
                    "cardId='" + cardId + '\'' +
                    ", cardHolder=" + cardHolder.getFullName() +
                    ", policyOwner=" + policyOwner+
                    ", expireDate=" + expireDate +
                    '}';
    }
}
