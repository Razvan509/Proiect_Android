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



public class MainActivity extends AppCompatActivity {

    private FirstPageFragment firstPageFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent data = getIntent();

        FloatingActionButton buttonAddUser = findViewById(R.id.button_add_transaction);

        firstPageFragment = new FirstPageFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.container, firstPageFragment);
        Bundle b = new Bundle();
        b.putString("userId",data.getStringExtra(AuthActivity.EXTRA_USER));
        firstPageFragment.setArguments(b);
        fragmentTransaction.commit();


    }



}
