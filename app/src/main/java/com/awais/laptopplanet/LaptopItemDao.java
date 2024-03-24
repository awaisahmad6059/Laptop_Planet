package com.awais.laptopplanet;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface LaptopItemDao {
    @Query("SELECT * FROM Laptop WHERE isDeleted = 0 ORDER BY id DESC LIMIT 500")
    List<Laptop> getAll();

    @Query("SELECT * FROM Laptop WHERE isSold = 1 AND isDeleted = 0 ORDER BY id DESC LIMIT 500")
    List<Laptop> getAllSold();

    @Query("SELECT * FROM Laptop WHERE isDeleted = 1 ORDER BY id DESC LIMIT 500")
    List<Laptop> getAllDeleted();

    @Query("SELECT * FROM Laptop WHERE isSold = 0 AND isDeleted = 0 ORDER BY id DESC LIMIT 500")
    List<Laptop> getAllInStock();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertOrReplace(Laptop laptop);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertOrReplaceAll(List<Laptop> laptops);

    @Delete
    void delete(Laptop laptop);

    @Update
    void update(Laptop laptop);

    @Query("DELETE FROM Laptop")
    void deleteAll();
}
