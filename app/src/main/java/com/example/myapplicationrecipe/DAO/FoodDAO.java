package com.example.myapplicationrecipe.DAO;

import com.example.myapplicationrecipe.entity.Food;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface FoodDAO {
    @Insert
    void insertOne(Food food);
    @Delete
    void delete(Food food);
    @Query("SELECT * FROM food")
    List<Food> getAll();
}
