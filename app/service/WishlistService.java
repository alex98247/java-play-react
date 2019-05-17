package service;

import models.Wishlist;

import java.util.List;

public interface WishlistService {
    Wishlist getWishlistById(long id);
    List<Wishlist> getWishlists();
    void deleteWishlist(long id);
    void createWishlist(Wishlist wishlist);
    void updateWishlist(Wishlist wishlist);
}
