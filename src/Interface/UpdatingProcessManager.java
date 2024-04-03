package Interface;

import Customer.Customer;
import Customer.Dependent;

import java.util.List;
import java.util.Objects;

public interface UpdatingProcessManager {
    public void add (Customer o);

    public void update(Customer o);

    public void delete(String id);

    public Customer getOne(String id);

    public List<Customer> getAll();
}
