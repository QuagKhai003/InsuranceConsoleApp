package Customer;

import Claim.*;
import Interface.ClaimProcessManager;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author <Ngo Quang Khai - s3975831>
 */
public abstract class Customer implements ClaimProcessManager{
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
        if ( listClaims == null) {
            return "";
        }
        return listClaims.stream().map(Claim::getId).collect(Collectors.joining(","));
    }

    public abstract boolean isPolicyHolder();

    @Override
    public void add(Claim c) {
        if (listClaims.contains(c)) {
            System.out.println("");
        }
        listClaims.add(c);
    }

    @Override
    public void update(Claim claim) {
        Claim foundClaim = listClaims.stream().filter(aClaim -> aClaim.equals(claim)).findFirst().orElse(null);
        System.out.println(foundClaim);
        System.out.println(claim);
        if (foundClaim != null) {
            /*foundClaim.setClaimDate(claim.getClaimDate());
            foundClaim.setExamDate(claim.getExamDate());
            foundClaim.setStatus(claim.getStatus());
            foundClaim.setDocuments(claim.getDocuments());
            foundClaim.setInfoBank(claim.getInfoBank());
            foundClaim.setClaimAmounts(claim.getClaimAmounts());
            System.out.println("After update:" + foundClaim);*/
            listClaims.remove(foundClaim);
            listClaims.add(claim);
        } else {
            System.out.println("None existed claim");
            System.out.println("Adding a new claim");
            this.add(claim);
        }
        System.out.println("Processing");
        System.out.println("Done!!!");
    }

    @Override
    public void delete(String claimID) {
        Claim foundClaim = listClaims.stream().filter(aClaim -> aClaim.getId().equals(claimID)).findFirst().orElse(null);
        if (foundClaim != null) {
            listClaims.remove(foundClaim);
        } else {
            System.out.println("None existed claim");
        }
        System.out.println("Processing");
        System.out.println("Done!!!");
    }

    @Override
    public Claim getOne(String claimID) {
        return listClaims.stream().filter(aClaim -> aClaim.getId().equals(claimID)).findFirst().orElse(null);
    }

    @Override
    public List<Claim> getAll() {
        return listClaims;
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

