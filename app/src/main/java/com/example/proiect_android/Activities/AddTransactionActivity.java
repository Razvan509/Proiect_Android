package com.example.proiect_android.Activities;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.proiect_android.Fragments.AddExpenseFragment;
import com.example.proiect_android.Fragments.AddIncomeFragment;
import com.example.proiect_android.Fragments.FirstPageFragment;
import com.example.proiect_android.R;

public class AddTransactionActivity extends AppCompatActivity {

    public static final String EXTRA_USER_NAME =
            "com.example.proiect_android.activities.EXTRA_USER_NAME";

    private EditText editTextExpenseAmount;
    private RadioGroup radioButtonSwitchBetweenTransactions;
    private RadioButton radioButtonExpenses;
    private RadioButton radioButtonIncome;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    private AddExpenseFragment addExpenseFragment;
    private AddIncomeFragment addIncomeFragment;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_transaction_main);

        fragmentManager = getSupportFragmentManager();


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
                }

                if(radioButtonIncome.isChecked()) {
                    fragmentTransaction = fragmentManager.beginTransaction();
                    addIncomeFragment = new AddIncomeFragment();
                    fragmentTransaction.replace(R.id.addTransactionContainer, addIncomeFragment);
                    fragmentTransaction.commit();
                }

            }
        });
    }




}
