package com.example.proiect_android.Activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.proiect_android.Fragments.AddTransactionFragment;
import com.example.proiect_android.Fragments.FirstPageFragment;
import com.example.proiect_android.R;

public class AddTransactionActivity extends AppCompatActivity {

    private AddTransactionFragment addTransactionFragment;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_transaction_main);

        addTransactionFragment = new AddTransactionFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.addTransactionContainer, addTransactionFragment);
        fragmentTransaction.commit();

    }

}
