package Claim;

import Interface.ClaimProcessManager;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListClaimOfCustomer implements ClaimProcessManager {
    private List<Claim> listClaim;

    public ListClaimOfCustomer() {
        this.listClaim = new ArrayList<Claim>();
    }

    public ListClaimOfCustomer(List<Claim> claimListFromDatabase) {
        this.listClaim = claimListFromDatabase;
    }

    public List<Claim> getListClaim() {
        return listClaim;
    }

    public void setListClaim(List<Claim> listClaim) {
        this.listClaim = listClaim;
    }

    @Override
    public void add(Claim c) {
        this.listClaim.add(c);
    }

    @Override
    public void update(Claim c) {
        Claim foundClaim = listClaim.stream().filter(aClaim -> aClaim.equals(c)).findFirst().orElse(null);
        System.out.println(foundClaim);
        System.out.println(c);
        if (foundClaim != null) {
            this.listClaim.remove(foundClaim);
            this.listClaim.add(c);
        } else {
            System.out.println("None existed claim");
            System.out.println("Adding a new claim");
            this.listClaim.add(c);
        }
        System.out.println("Processing");
        System.out.println("Done!!!");
    }

    @Override
    public void delete(String claimId) {
        Claim foundClaim = listClaim.stream().filter(aClaim -> aClaim.getId().equals(claimId)).findFirst().orElse(null);
        System.out.println(foundClaim);
        if (foundClaim != null) {
            this.listClaim.remove(foundClaim);
        } else {
            System.out.println("None existed claim");
            System.out.println("Adding a new claim");
        }
    }

    @Override
    public Claim getOne(String claimId) {
        return listClaim.stream().filter(aClaim -> aClaim.getId().equals(claimId)).findFirst().orElse(null);
    }

    @Override
    public List<Claim> getAll() {
        return this.listClaim;
    }

    public String listAllIdClaim() {
        if ( listClaim == null) {
            return "";
        }
        return listClaim.stream().map(Claim::getId).collect(Collectors.joining(","));
    }

    @Override
    public String toString() {
        return listAllIdClaim();
    }
}
