package com.example.bruno.onlinecoach.Common;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bruno.onlinecoach.Coach.CoachActivity;
import com.example.bruno.onlinecoach.Objects.User;
import com.example.bruno.onlinecoach.Student.MainActivity;
import com.example.bruno.onlinecoach.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    Button btnLogin;
    EditText txtEmail, txtPassword;
    TextView linkSignUp;
    private DatabaseReference mDatabase;
    private static final String TAG = "QueryLogin";
    ProgressDialog progressDialog = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtEmail = findViewById(R.id.input_email);
        txtPassword = findViewById(R.id.input_password);
        btnLogin = findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        linkSignUp = findViewById(R.id.link_signup);
        linkSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signup = new Intent(getBaseContext(), SignUpActivity.class);
                startActivity(signup);
            }
        });
    }

    private void login(){
        //Validate formats

        if(!validate()){
            onLoginFailed();
            return;
        }

        btnLogin.setEnabled(false);

        progressDialog = new ProgressDialog(this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        final String email = txtEmail.getText().toString();
        final String password = txtPassword.getText().toString();
        loginDB(email, password);

        // TODO: Implement your own authentication logic here.

        /*
        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        boolean result =
                        System.out.println("Result2" + result);
                        // On complete call either onLoginSuccess or onLoginFailed
                        if(result)
                            onLoginSuccess();
                        else
                            onLoginFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);
                */

    }

    private void loginDB(String email, final String password){
        final boolean[] result = {false};
        mDatabase = FirebaseDatabase.getInstance().getReference();
        Query myTopPostsQuery = mDatabase.child("users").orderByChild("email").equalTo(email);

        // [START basic_query_value_listener]
        // My top posts by number of stars
        myTopPostsQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.w(TAG, "loadPost:onDataChange");
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    // TODO: handle the post
                    //System.out.println(dataSnapshot.getValue());
                    User u = postSnapshot.getValue(User.class);
                    String id = postSnapshot.getKey();
                    System.out.println(u.getName() + u.getEmail() + u.getPassword());
                    if(u.getPassword().equals(password)){
                        Log.w("LoginResult", "Validated");
                        onLoginSuccess(id, u.getEmail(), u.getType());
                    }
                }
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                progressDialog.dismiss();
            }
        });

    }

    private boolean validate(){
        return true;
    }

    public void onLoginSuccess(String id, String email, int user_type) {
        btnLogin.setEnabled(true);

        Intent menu = new Intent(this, MainActivity.class);
        menu.putExtra("id", id);
        menu.putExtra("email", email);
        menu.putExtra("type", user_type);
        startActivity(menu);

    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();
        btnLogin.setEnabled(true);
    }
}
