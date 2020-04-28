package com.example.proiect_android.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.proiect_android.R;

public class AddUserActivity extends AppCompatActivity {
    public static final String EXTRA_USER_NAME =
            "com.example.proiect_android.activities.EXTRA_USER_NAME";

    private EditText editTextUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        editTextUserName = findViewById(R.id.edit_text_user_name);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        setTitle("Add User");
    }

    private void saveUser() {
        String userName = editTextUserName.getText().toString();

        if (userName.trim().isEmpty()){
            Toast.makeText(this, "Please insert the user name", Toast.LENGTH_SHORT).show();

            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_USER_NAME, userName);

        setResult(RESULT_OK, data);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_user_menu, menu);
        return true; // to display the menu
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_user:
                saveUser();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
