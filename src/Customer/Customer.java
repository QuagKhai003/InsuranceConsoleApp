package Customer;

import Claim.*;
import Interface.ClaimProcessManager;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author <Ngo Quang Khai - s3975831>
 */
public abstract class Customer {
    private String id;
    private String fullName;
    private String insuranceCard;
    private ListClaimOfCustomer listClaims;

    public Customer() {
        this.id = "Default";
        this.fullName = "Default";
        this.insuranceCard = "Default";
        this.listClaims = new ListClaimOfCustomer();
    }

    public Customer(String id, String fullName, String insuranceCard) {
        this.id = id;
        this.fullName = fullName;
        this.insuranceCard = insuranceCard;
        this.listClaims = new ListClaimOfCustomer();
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

    public ListClaimOfCustomer getListClaims() {
        return listClaims;
    }

    public void setListClaims(ListClaimOfCustomer listClaims) {
        this.listClaims = listClaims;
    }

    public abstract boolean isPolicyHolder();

    @Override
    public String toString() {
        return "Customer{" +
                "id='" + id + '\'' +
                ", fullName='" + fullName + '\'' +
                ", insuranceCard='" + insuranceCard + '\'' +
                ", listClaims=" + "[" + listClaims + "]" +
                '}';
    }
}

