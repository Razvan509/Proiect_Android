package com.example.proiect_android.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.proiect_android.beans.Category;

import java.util.List;

@Dao
public interface CategoryDao {
    @Insert
    void insert(Category category);
}
