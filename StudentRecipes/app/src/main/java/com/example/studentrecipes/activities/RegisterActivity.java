package com.example.studentrecipes.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.studentrecipes.R;
import com.example.studentrecipes.data.SQLiteDatabaseHelper;

public class RegisterActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private SQLiteDatabaseHelper db;
    private EditText editTextUsername, editTextPassword;
    private Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = new SQLiteDatabaseHelper(this);

        editTextUsername = (EditText) findViewById(R.id.new_username);
        editTextPassword = (EditText) findViewById(R.id.new_password);

        registerButton = (Button) findViewById(R.id.register_button);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editTextUsername.getText().toString();
                String password = editTextPassword.getText().toString();

                if(username.equals("") || password.equals("")){
                    Toast.makeText(RegisterActivity.this, "Fields cannot be empty", Toast.LENGTH_SHORT).show();
                }
                else{
                    Boolean checkUsername = db.checkUsername(username);
                    Log.d(TAG,"usernames being checked");
                    if(checkUsername == true){
                        Boolean insert = db.addUser(username, password);
                        Log.d(TAG,"usernames being added to the database");
                        if(insert == true){
                            Log.v(TAG,"registry successful");
                            Toast.makeText(RegisterActivity.this, "Registry is successful", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Log.d(TAG,"registry not successful");
                            Toast.makeText(RegisterActivity.this, "Registry is unsuccessful", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Log.d(TAG,"Username already taken");
                        Toast.makeText(RegisterActivity.this, "Username already taken", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.v(TAG,"onPause");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
        Log.v(TAG,"onBackPressed");
    }
}