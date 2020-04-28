package com.example.proiect_android.models;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.proiect_android.beans.Transaction;
import com.example.proiect_android.repos.TransactionRepository;

import java.util.List;

public class TransactionViewModel extends AndroidViewModel {
    private TransactionRepository repository;
    private LiveData<List<Transaction>> transactions;

    public TransactionViewModel(Application application) {
        super(application);
        repository = new TransactionRepository(application);
        transactions = repository.getAllTransactions();
    }

    public void insert(Transaction transaction){
        repository.insert(transaction);
    }

    public void update(Transaction transaction){
        repository.update(transaction);
    }

    public void delete(Transaction transaction){
        repository.delete(transaction);
    }

    public LiveData<List<Transaction>> getTransactions(){
        return transactions;
    }
}
