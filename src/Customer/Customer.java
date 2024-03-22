package Customer;

import Claim.*;
import Interface.ClaimProcessManager;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author <Ngo Quang Khai - s3975831>
 */
public abstract class Customer implements ClaimProcessManager {
    private String id;
    private String fullName;
    private String insuranceCard;
    private List<Claim> listClaims;

    public Customer() {
        this.id = "Default";
        this.fullName = "Default";
        this.insuranceCard = "Default";
        this.listClaims = new ArrayList<>();
    }

    public Customer(String id, String fullName, String insuranceCard) {
        this.id = id;
        this.fullName = fullName;
        this.insuranceCard = insuranceCard;
        this.listClaims = new ArrayList<>();
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

    public List<Claim> getListClaims() {
        return listClaims;
    }

    public void setListClaims(List<Claim> listClaims) {
        this.listClaims = listClaims;
    }

    public String listIdClaim() {
        return listClaims.stream().map(Claim::getId).collect(Collectors.joining(","));
    }

    public abstract boolean isPolicyHolder();

    @Override
    public void add(Claim c) {
        listClaims.add(c);
    }

    @Override
    public void update(Claim c) {

    }

    @Override
    public void delete(Claim c) {

    }

    @Override
    public Claim getOne(String id) {
        return null;
    }

    @Override
    public ArrayList<Claim> getAll() {
        return null;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id='" + id + '\'' +
                ", fullName='" + fullName + '\'' +
                ", insuranceCard='" + insuranceCard + '\'' +
                ", listClaims=" + "[" + listIdClaim() + "]" +
                '}';
    }
}

