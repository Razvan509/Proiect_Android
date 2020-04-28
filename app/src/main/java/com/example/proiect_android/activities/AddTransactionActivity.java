package com.example.proiect_android.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.proiect_android.fragments.AddExpenseFragment;
import com.example.proiect_android.fragments.AddIncomeFragment;
import com.example.proiect_android.R;

import java.util.Date;

public class AddTransactionActivity extends AppCompatActivity {
    public static final String EXTRA_DESCRIPTION =
            "com.example.proiect_android.activities.EXTRA_DESCRIPTION";
    public static final String EXTRA_AMOUNT =
            "com.example.proiect_android.activities.EXTRA_AMOUNT";
    public static final String EXTRA_CATEGORYID =
            "com.example.proiect_android.activities.EXTRA_CATEGORYID";
    public static final String EXTRA_DATE =
            "com.example.proiect_android.activities.EXTRA_DATE";


    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    private EditText editTextAmount;
    private EditText editTextDescription;
    private EditText editTextDate;
    private RadioButton radioButtonNecessary;
    private RadioButton radioButtonUnnecessary;
    private RadioGroup radioButtonSwitchBetweenTransactions;
    private Button button;
    private RadioButton radioButtonExpenses;
    private RadioButton radioButtonIncome;

    private AddExpenseFragment addExpenseFragment;
    private AddIncomeFragment addIncomeFragment;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_transaction_main);


        fragmentManager = getSupportFragmentManager();
//        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
//        setTitle("Add transaction");

        radioButtonSwitchBetweenTransactions = (RadioGroup) findViewById(R.id.radio_group_switch_between_transactions_types);
        radioButtonExpenses = (RadioButton) findViewById(R.id.radio_button_expense);
        radioButtonIncome = (RadioButton) findViewById(R.id.radio_button_income);
        radioButtonSwitchBetweenTransactions.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if(radioButtonExpenses.isChecked()) {
                    fragmentTransaction = fragmentManager.beginTransaction();
                    addExpenseFragment = new AddExpenseFragment();
                    fragmentTransaction.replace(R.id.addTransactionContainer, addExpenseFragment);
                    fragmentTransaction.commit();

                    editTextAmount = findViewById(R.id.edit_text_expanse_amount);
                    editTextDescription = findViewById(R.id.edit_text_expanse_description);
                    editTextDate = findViewById(R.id.edit_text_expanse_date);
                }

                if(radioButtonIncome.isChecked()) {
                    fragmentTransaction = fragmentManager.beginTransaction();
                    addIncomeFragment = new AddIncomeFragment();
                    fragmentTransaction.replace(R.id.addTransactionContainer, addIncomeFragment);
                    fragmentTransaction.commit();

                    editTextAmount = findViewById(R.id.edit_text_income_amount);
                    editTextDescription = findViewById(R.id.edit_text_income_description);
                    editTextDate = findViewById(R.id.edit_text_income_date);

                }

            }
        });

    }

    public void addTransactionButtonClicked(View view)
    {
        if(radioButtonExpenses.isChecked()) {
            editTextAmount = findViewById(R.id.edit_text_expanse_amount);
            editTextDescription = findViewById(R.id.edit_text_expanse_description);
            editTextDate = findViewById(R.id.edit_text_expanse_date);
        }

        if(radioButtonIncome.isChecked()) {
            editTextAmount = findViewById(R.id.edit_text_income_amount);
            editTextDescription = findViewById(R.id.edit_text_income_description);
            editTextDate = findViewById(R.id.edit_text_income_date);

        }
        saveTransaction();
    }

    private void saveTransaction(){
        String description = editTextDescription.getText().toString();
        String amount = editTextAmount.getText().toString();
        String transactionDate = editTextDate.getText().toString();
        String categoryId = "";
        if(radioButtonIncome.isChecked()){
            categoryId = "1";
        }else{
            radioButtonNecessary = findViewById(R.id.radio_button_necessary);
            radioButtonUnnecessary = findViewById(R.id.radio_button_unnecessary);
            if (radioButtonNecessary.isChecked()) {
                categoryId = "2";
            }else{
                if(radioButtonUnnecessary.isChecked()){
                    categoryId = "3";
                }else{
                    Toast.makeText(this, "Please select the category of your transaction", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        }
        if (description.trim().isEmpty() ||
        amount.trim().isEmpty() || transactionDate.trim().isEmpty()){
            Toast.makeText(this, "One or more fields are empty!", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_DATE, transactionDate);
        data.putExtra(EXTRA_CATEGORYID, categoryId);
        data.putExtra(EXTRA_AMOUNT, amount);
        data.putExtra(EXTRA_DESCRIPTION, description);
        setResult(RESULT_OK, data);
        finish();
    }




}
