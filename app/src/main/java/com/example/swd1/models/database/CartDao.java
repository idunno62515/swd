package com.example.swd1.models.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.swd1.models.database.CartItem;

import java.util.List;

@Dao
public interface CartDao {

    @Query("SELECT * FROM CartItem WHERE tableId=:tableId")
    List<CartItem> getCartsByTableId(int tableId);

    @Query("SELECT * FROM CartItem")
    List<CartItem> getAllCart();

    @Query("SELECT * FROM CartItem WHERE tableId=:tableId AND proId=:proId")
    List<CartItem> getItemCart(int tableId, int proId);


    @Query("SELECT COUNT(*) FROM CartItem WHERE tableId=:tableId")
    int countItemCartInTable(int tableId);

    @Query("SELECT SUM(quantity*proPrice) FROM CartItem WHERE tableId=:tableId")
    double totalPriceInTable(int tableId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertOrReplaceAll(CartItem... cartItem);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(CartItem cartItem);

    @Delete
    void delete(CartItem cartItem);

    @Query("DELETE FROM CartItem WHERE tableId=:tableId")
    void clearCartInTable(int tableId);

    @Query("DELETE FROM CartItem")
    void clearCart();
}
