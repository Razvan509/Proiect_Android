package com.example.proiect_android.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import com.example.proiect_android.beans.Transaction;
import com.example.proiect_android.fragments.FirstPageFragment;
import com.example.proiect_android.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.proiect_android.activities.AddTransactionActivity;
import com.example.proiect_android.models.TransactionViewModel;
import com.example.proiect_android.models.UserViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.sql.Date;

public class MainActivity extends AppCompatActivity {
    public static final int ADD_TRANSACTION_REQUEST = 1;
    private FirstPageFragment firstPageFragment;
    private TransactionViewModel transactionViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton buttonAddUser = findViewById(R.id.button_add_transaction);

        firstPageFragment = new FirstPageFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.container, firstPageFragment);
        fragmentTransaction.commit();

        transactionViewModel = ViewModelProviders.of(this).get(TransactionViewModel.class);



    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_TRANSACTION_REQUEST && resultCode == RESULT_OK){
            String description = data.getStringExtra(AddTransactionActivity.EXTRA_DESCRIPTION);
            float amount = Float.valueOf(data.getStringExtra(AddTransactionActivity.EXTRA_AMOUNT));
            int categoryId = Integer.valueOf(data.getStringExtra(AddTransactionActivity.EXTRA_CATEGORYID));
            //Date transactionDate = Date.valueOf(data.getStringExtra(AddTransactionActivity.EXTRA_DATE));

            Transaction transaction = new Transaction(description,amount,categoryId);
//            transaction.setAmount(amount);
//            transaction.setCategoryId(categoryId);
//            transaction.setDescription(description);
            //transaction.setTransactionDate(transactionDate);

            transactionViewModel.insert(transaction);

            Toast.makeText(this, "Transaction saved", Toast.LENGTH_SHORT).show();
        } else{
            Toast.makeText(this, "Transaction not saved", Toast.LENGTH_SHORT).show();
        }
    }
}
