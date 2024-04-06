package Customer;

/**
 * @author <Ngo Quang Khai - s3975831>
 */

import InsuranceSystem.InsuranceCard;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PolicyHolder extends Customer {
    private ListDependentOfCustomer listDependents;

    public PolicyHolder() {
        super();
    }

    public PolicyHolder(String id, String fullName, String insuranceCardId) {
        super(id, fullName, insuranceCardId);
        this.listDependents = new ListDependentOfCustomer();
    }

    public ListDependentOfCustomer getListDependents() {
        return listDependents;
    }

    public void setListDependents(ListDependentOfCustomer listDependents) {
        this.listDependents = listDependents;
    }

    /*public List<Customer> getListDependents() {
        return listDependents;
    }

    public void setListDependents(List<Customer> listDependents) {
        this.listDependents = listDependents;
    }

    public boolean addDependent(Customer c) {
        if (listDependents.contains(c)) {
            return false;
        }
        listDependents.add(c);
        return true;
    }

    public String listNameDep() {
        return this.listDependents.stream().map(Customer::getFullName).collect(Collectors.joining(","));
    }*/

    @Override
    public boolean isPolicyHolder() {
        return true;
    }

    @Override
    public String toString() {
        return "PolicyHolder{" +
                "id='" + super.getId() + '\'' +
                ", fullName='" + super.getFullName() + '\'' +
                ", insuranceCard='" + super.getInsuranceCard() + '\'' +
                ", listClaims=" + "[" + super.getListClaims() + "]" +
                ", listDependents=" + "[" + listDependents + "]" +
                '}';
    }
}
