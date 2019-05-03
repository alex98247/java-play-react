package service;

import models.Claim;

import java.util.List;

public interface ClaimService {
    Claim getClaimById(long id);
    List<Claim> getClaims();
    void deleteClaim(long id);
    void createClaim(Claim claim);
    void updateClaim(Claim claim);
}