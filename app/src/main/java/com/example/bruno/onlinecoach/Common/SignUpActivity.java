package com.example.bruno.onlinecoach.Common;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bruno.onlinecoach.Objects.User;
import com.example.bruno.onlinecoach.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class SignUpActivity extends AppCompatActivity {

    EditText txtEmail, txtPassword, txtName;
    Button btnSignUp;
    TextView loginLink;
    private DatabaseReference mDatabase;
    private static final String TAG = "QueryActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        txtName = findViewById(R.id.input_signup_name);
        txtEmail = findViewById(R.id.input_signup_email);
        txtPassword = findViewById(R.id.input_signup_password);
        btnSignUp = findViewById(R.id.btn_signup);
        loginLink = findViewById(R.id.link_login);


        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();

            }
        });

        loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    public void signup(){
        if (!validate()) {
            onSignupFailed();
            return;
        }

        btnSignUp.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(SignUpActivity.this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating Account...");
        progressDialog.show();

        String name = txtName.getText().toString();
        String email = txtEmail.getText().toString();
        String password = txtPassword.getText().toString();

        createUser(name, email, password);

        // TODO: Implement your own signup logic here.

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onSignupSuccess or onSignupFailed
                        // depending on success
                        onSignupSuccess();
                        // onSignupFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);
    }

    public void onSignupSuccess() {
        btnSignUp.setEnabled(true);
        setResult(RESULT_OK, null);
        finish();
    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        btnSignUp.setEnabled(true);
    }

    public boolean validate(){
        boolean val = true;

        if (txtName.getText().toString().length() == 0) val = false;
        if (txtEmail.getText().toString().length() < 3) val = false;
        if (txtPassword.getText().toString().length() < 3) val = false;

        return val;
    }

    public void createUser(String name, String email, String password){
        mDatabase = FirebaseDatabase.getInstance().getReference();
        User u = new User(email, password, name, 0);
        String key = mDatabase.push().getKey();
        mDatabase.child("users").child(key).setValue(u);
        Log.w(TAG, "Inserted");

    }
}


