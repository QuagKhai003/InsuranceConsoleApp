package Interface;

import Claim.Claim;
import java.util.List;

public interface ClaimProcessManager {
    public void add (Claim c);

    public void update(Claim c);

    public void delete(String claimId);

    public Claim getOne(String claimId);

    public List<Claim> getAll();
}
