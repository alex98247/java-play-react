package service;

import models.Claim;

import java.util.List;

public class ClaimServiceImpl implements ClaimService {

    public Claim getClaimById(long id) {
        return null;
    }

    public List<Claim> getClaims() {
        return Claim.find.all();
    }

    public void deleteClaim(long id) {
        Claim.db().delete(id);
    }

    public void createClaim(Claim claim) {
        claim.save();
    }

    public void updateClaim(Claim claim) {
        claim.update();
    }
}
