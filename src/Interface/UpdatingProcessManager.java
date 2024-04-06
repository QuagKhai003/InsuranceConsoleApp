package Interface;

/**
 * @author <Ngo Quang Khai - s3975831>
 */

import Customer.Customer;
import Customer.Dependent;

import java.util.List;
import java.util.Objects;

public interface UpdatingProcessManager {
    public void add (Customer o);

    public void update(Customer o);

    public void delete(Customer o);

    public Customer getOne(int id);

    public List<Customer> getAll();
}
