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

    @Query("SELECT * FROM Cart WHERE tableId=:tableId")
    List<CartItem> getCartsByTableId(int tableId);

    @Query("SELECT * FROM Cart WHERE tableId=:tableId AND proId=:proId")
    List<CartItem> getItemCart(int tableId, int proId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertOrReplaceAll(CartItem... cartItem);


    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(CartItem cartItem);

    @Delete
    void delete(CartItem cartItem);


    @Query("DELETE FROM Cart WHERE tableId=:tableId")
    void clearCart(int tableId);
}
