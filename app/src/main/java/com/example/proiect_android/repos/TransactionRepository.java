package com.example.proiect_android.repos;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.proiect_android.ProjectRoomDatabase;
import com.example.proiect_android.beans.Transaction;
import com.example.proiect_android.dao.TransactionDao;

import java.util.List;

public class TransactionRepository {
    private TransactionDao transactionDao;
    private LiveData<List<Transaction>> allTransactions;

    public TransactionRepository(Application app){
        ProjectRoomDatabase database = ProjectRoomDatabase.getInstance(app);
        transactionDao = database.transactionDao();
        allTransactions = transactionDao.getAllTransactions();
    }

    public void insert(Transaction transaction){
        new InsertUserAsyncTask(transactionDao).execute(transaction);
    }

    public void update(Transaction transaction){
        new UpdateUserAsyncTask(transactionDao).execute(transaction);
    }

    public void delete(Transaction transaction){
        new UpdateUserAsyncTask(transactionDao).execute(transaction);
    }

    public LiveData<List<Transaction>> getAllTransactions() {
        return allTransactions;
    }

    private static class InsertUserAsyncTask extends AsyncTask<Transaction, Void, Void> {
        private TransactionDao transactionDao;

        private InsertUserAsyncTask(TransactionDao transactionDao){
            this.transactionDao = transactionDao;
        }

        @Override
        protected Void doInBackground(Transaction... transactions) {
            transactionDao.insert(transactions[0]);
            return null;
        }
    }
    private static class UpdateUserAsyncTask extends AsyncTask<Transaction, Void, Void>{
        private TransactionDao transactionDao;

        private UpdateUserAsyncTask(TransactionDao transactionDao){
            this.transactionDao = transactionDao;
        }

        @Override
        protected Void doInBackground(Transaction... transactions) {
            transactionDao.update(transactions[0]);
            return null;
        }
    }
    private static class DeleteUserAsyncTask extends AsyncTask<Transaction, Void, Void>{
        private TransactionDao transactionDao;

        private DeleteUserAsyncTask(TransactionDao transactionDao){
            this.transactionDao = transactionDao;
        }

        @Override
        protected Void doInBackground(Transaction... transactions) {
            transactionDao.delete(transactions[0]);
            return null;
        }
    }
}
