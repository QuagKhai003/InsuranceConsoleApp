package InsuranceSystem;

/**
 * @author <Ngo Quang Khai - s3975831>
 */

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

    public InsuranceCard(String cardId, Customer cardHolder) throws ParseException {
        this.cardId = cardId;
        this.cardHolder = cardHolder;
        this.policyOwner = "Default";
        this.expireDate = new Date();
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

    public String getPolicyOwner() {
        return policyOwner;
    }

    public void setPolicyOwner(String policyOwner) {
        this.policyOwner = policyOwner;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) throws ParseException {
        try {
            this.expireDate = (new SimpleDateFormat("dd/MM/yyyy").parse(expireDate));;
        } catch ( ParseException e) {
            System.out.println("Invalid Date Input");
        }
    }

    @Override
    public String toString() {
            return "InsuranceCard{" +
                    "cardId='" + cardId + '\'' +
                    ", cardHolder=" + cardHolder.getFullName() +
                    ", policyOwner=" + policyOwner+
                    ", expireDate=" + expireDate +
                    '}';
    }
}
