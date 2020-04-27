package com.example.proiect_android.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.proiect_android.Fragments.BalanceFragment;
import com.example.proiect_android.Fragments.FirstPageFragment;
import com.example.proiect_android.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private FirstPageFragment firstPageFragment;
    private BalanceFragment balanceFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstPageFragment = new FirstPageFragment();
        balanceFragment = new BalanceFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.container, firstPageFragment);
        fragmentTransaction.commit();
    }

    public void allTransactionsClicked(View view)
    {
        Intent intent = new Intent(this, AddTransactionActivity.class);
        startActivity(intent);
    }
}
