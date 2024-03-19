package Customer;

import Claim.*;
import InsuranceSystem.InsuranceCard;

import java.util.ArrayList;

/**
 * @author <Ngo Quang Khai - s3975831>
 */
public abstract class Customer {
    private String id;
    private String fullName;
    private String insuranceCard;
    private ArrayList<Claim> listClaims;

    public Customer() {
        this.id = "Default";
        this.fullName = "Default";
        this.insuranceCard = "Default";
        this.listClaims = new ArrayList<>();
    }

    public Customer(String id, String fullName, String insuranceCard) {
        this.id = "c-" + id;
        this.fullName = fullName;
        this.insuranceCard = insuranceCard;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getInsuranceCard() {
        return insuranceCard;
    }

    public void setInsuranceCard(String insuranceCard) {
        this.insuranceCard = insuranceCard;
    }

    public ArrayList<Claim> getListClaims() {
        return listClaims;
    }

    public void setListClaims(ArrayList<Claim> listClaims) {
        this.listClaims = listClaims;
    }

    public abstract boolean isPolicyHolder();

    @Override
    public String toString() {
        return "Customer{" +
                "id='" + id + '\'' +
                ", fullName='" + fullName + '\'' +
                ", insuranceCard=" + insuranceCard + '\'' +
                ", listClaims=" + listClaims +
                '}';
    }
}

