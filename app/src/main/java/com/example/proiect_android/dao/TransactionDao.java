package com.example.proiect_android.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.proiect_android.beans.Transaction;

import java.util.List;

@Dao
public interface TransactionDao {
    @Insert
    void insert(Transaction transaction);
    @Update
    void update(Transaction transaction);
    @Delete
    void delete(Transaction transaction);
    @Query("SELECT * FROM transactions")
    LiveData<List<Transaction>> getAllTransactions();
}
