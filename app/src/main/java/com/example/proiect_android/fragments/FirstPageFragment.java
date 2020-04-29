package com.example.proiect_android.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.proiect_android.R;
import com.example.proiect_android.activities.AddTransactionActivity;
import com.example.proiect_android.activities.AuthActivity;
import com.example.proiect_android.activities.MainActivity;
import com.example.proiect_android.activities.ShowTransactions;
import com.example.proiect_android.beans.Transaction;
import com.example.proiect_android.models.TransactionViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class FirstPageFragment extends Fragment {

    private Context context;
    public static final int ADD_TRANSACTION_REQUEST = 1;
    private TransactionViewModel transactionViewModel;
    private Button logOut;
    private FirebaseAuth mAuth;

    @NonNull
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        context = getContext();
        mAuth = FirebaseAuth.getInstance();
        View view = inflater.inflate(R.layout.main_page, container, false);
        FloatingActionButton floatingActionButton = (FloatingActionButton) view.findViewById(R.id.button_add_transaction);
        floatingActionButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(), AddTransactionActivity.class);
                startActivityForResult(intent,ADD_TRANSACTION_REQUEST);
            }
        });

        Button button = view.findViewById(R.id.allTransactions);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ShowTransactions.class);
                startActivity(intent);
            }
        });

        transactionViewModel = ViewModelProviders.of(this).get(TransactionViewModel.class);

        logOut = view.findViewById(R.id.logOut);
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                Intent intent = new Intent(getActivity(), AuthActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_TRANSACTION_REQUEST && resultCode == MainActivity.RESULT_OK){
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            String description = data.getStringExtra(AddTransactionActivity.EXTRA_DESCRIPTION);
            float amount = Float.valueOf(data.getStringExtra(AddTransactionActivity.EXTRA_AMOUNT));
            int categoryId = Integer.valueOf(data.getStringExtra(AddTransactionActivity.EXTRA_CATEGORYID));
            Date transactionDate = null;
            try {
                transactionDate = format.parse(data.getStringExtra(AddTransactionActivity.EXTRA_DATE));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            Transaction transaction = new Transaction(description,amount,categoryId,transactionDate);
//            transaction.setAmount(amount);
//            transaction.setCategoryId(categoryId);
//            transaction.setDescription(description);
            //transaction.setTransactionDate(transactionDate);

            transactionViewModel.insert(transaction);

            Toast.makeText(getActivity(), "Transaction saved", Toast.LENGTH_SHORT).show();
        } else{
            Toast.makeText(getActivity(), "Transaction not saved", Toast.LENGTH_SHORT).show();
        }
    }

}


