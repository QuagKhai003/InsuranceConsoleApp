package Interface;

import Claim.Claim;

import java.util.ArrayList;

public interface ClaimProcessManager {
    public void add(Claim c);

    public void update(Claim c);

    public void delete(Claim c);

    public Claim getOne(String id);

    public ArrayList<Claim> getAll();
}
