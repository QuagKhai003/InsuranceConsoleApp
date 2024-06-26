package Interface;

/**
 * @author <Ngo Quang Khai - s3975831>
 */

import Claim.Claim;
import java.util.List;

public interface ClaimProcessManager {
    public void add (Claim c);

    public void update(Claim c);

    public void delete(Claim c);

    public Claim getOne(int index);

    public List<Claim> getAll();
}
