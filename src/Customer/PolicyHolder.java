package Customer;

import InsuranceSystem.InsuranceCard;

import java.util.ArrayList;

public class PolicyHolder extends Customer {
    private ArrayList<Dependent> listDependents;

    public PolicyHolder() {
        super();
    }

    public PolicyHolder(String id, String fullName, String insuranceCardId) {
        super(id, fullName, insuranceCardId);
    }

    public ArrayList<Dependent> getListDependents() {
        return listDependents;
    }

    public void setListDependents(ArrayList<Dependent> listDependents) {
        this.listDependents = listDependents;
    }

    @Override
    public boolean isPolicyHolder() {
        return true;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id='" + super.getId() + '\'' +
                ", fullName='" + super.getFullName() + '\'' +
                ", insuranceCard=" + super.getInsuranceCard() + '\'' +
                ", listClaims=" + super.getListClaims() + '\'' +
                ", listDependents=" + this.listDependents +
                '}';
    }

}
