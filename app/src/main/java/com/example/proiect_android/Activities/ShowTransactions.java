package com.example.proiect_android.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.proiect_android.R;
import com.example.proiect_android.adapters.TransactionAdapter;
import com.example.proiect_android.fragments.ShowTransactionsFragment;
import com.example.proiect_android.models.TransactionViewModel;

public class ShowTransactions extends AppCompatActivity {
    private ShowTransactionsFragment showTransactionsFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_transaction_main);

        showTransactionsFragment = new ShowTransactionsFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.container_transaction,showTransactionsFragment);
        fragmentTransaction.commit();



    }
}
