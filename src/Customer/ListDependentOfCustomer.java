package Customer;

import Claim.Claim;
import Interface.UpdatingProcessManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ListDependentOfCustomer implements UpdatingProcessManager {
    private List<Customer> listDependents;

    public ListDependentOfCustomer() {
        this.listDependents = new ArrayList<Customer>();
    }

    public ListDependentOfCustomer(List<Customer> listDependents) {
        this.listDependents = listDependents;
    }

    public List<Customer> getDependentList() {
        return listDependents;
    }

    public void setDependentList(List<Customer> listDependents) {
        this.listDependents = listDependents;
    }

    public String listNameDep() {
        return this.listDependents.stream().map(Customer::getFullName).collect(Collectors.joining(","));
    }

    @Override
    public String toString() {
        return listNameDep();
    }

    @Override
    public void add(Customer o) {
        if (listDependents.contains(o)) {
            System.out.println("already added");
        } else {
            this.listDependents.add(o);
        }
    }

    @Override
    public void update(Customer o) {
        Customer foundDep = listDependents.stream().filter(aDep -> aDep.equals(o)).findFirst().orElse(null);
        System.out.println(foundDep);
        if (foundDep != null) {
            this.listDependents.remove(foundDep);
            this.listDependents.add(o);
        } else {
            System.out.println("None existed dependent");
            System.out.println("Adding a new dependent");
            this.listDependents.add(o);
        }
        System.out.println("Processing");
        System.out.println("Done!!!");
    }

    @Override
    public void delete(Customer o) {
        if (this.listDependents.contains(o)) {
            this.listDependents.remove(o);
        } else {
            System.out.println("None existed dependent");
        }
    }

    @Override
    public Customer getOne(int index) {
        try {
            return listDependents.get(index);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    @Override
    public List<Customer> getAll() {
        return this.listDependents;
    }
}
