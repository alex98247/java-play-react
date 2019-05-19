package service;


import models.dao.Wishlist;

import java.util.List;

public class WishlistServiceImpl implements WishlistService {
    public Wishlist getWishlistById(long id) {
        return null;
    }

    public List<Wishlist> getWishlists() {
        return Wishlist.find.all();
    }

    public void deleteWishlist(long id) {
        Wishlist.db().delete(id);
    }

    public void createWishlist(Wishlist wishlist) {
        wishlist.save();
    }

    public void updateWishlist(Wishlist wishlist) {
        wishlist.update();
    }
}
