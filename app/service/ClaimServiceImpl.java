package service;

import models.dao.Claim;

import java.sql.Timestamp;
import java.util.Date;
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
        Date date = new Date();
        Timestamp ts = new Timestamp(date.getTime());
        Timestamp empts = new Timestamp(0);
        claim.setCreated_at(ts);
        claim.setSolved_at(empts);
        claim.setSolved(false);
        claim.save();
    }

    public void updateClaim(Claim claim) {
        claim.update();
    }
}
