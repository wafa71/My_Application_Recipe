package com.example.myapplicationrecipe.Database;

import android.content.Context;

import com.example.myapplicationrecipe.DAO.FoodDAO;
import com.example.myapplicationrecipe.entity.Food;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Food.class}, version = 1, exportSchema = false)
public  abstract class AppDataBase extends RoomDatabase {
    private static AppDataBase instance;
    public abstract FoodDAO foodDAO();
    public static AppDataBase getAppDatabase(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), AppDataBase.class, "recipe_db")

                    .allowMainThreadQueries()
                    .build();

        }
        return instance;
    }
}