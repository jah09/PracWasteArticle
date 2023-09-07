package com.example.practiceapp_elearning;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {
    DatabaseReference dbRef;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }

    public void Loginbtn(View v){

       // Button btnlogin = findViewById(R.id.loginBtn);
        boolean clearFields = getIntent().getBooleanExtra("isLogout", false);


        FirebaseAuth mAuth = FirebaseAuth.getInstance(); // Initialize Firebase Auth
        EditText editTextLoginEmail = findViewById(R.id.edittext_logInEmail);
        EditText editTextPassword = findViewById(R.id.edittext_Password);
        String email = editTextLoginEmail.getText().toString().trim();
        String passWord = editTextPassword.getText().toString().trim();
        if (clearFields) {

            editTextLoginEmail.setText("");
            editTextPassword.setText("");
        }

        if (editTextLoginEmail.getText().toString().isEmpty() || editTextPassword.getText().toString().isEmpty()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
            builder.setTitle("Warning");
            builder.setMessage("Please enter username or password");
            builder.setPositiveButton("OK", null);
            AlertDialog dialog = builder.create();
            dialog.show();
        }
        else{
            // on below line we are calling a sign in method and passing email and password to it.
            mAuth.signInWithEmailAndPassword(email, passWord).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        Log.d("onComplete", "onComplete: "+user);
                        Toast.makeText(getApplicationContext(), "Successfully Login", Toast.LENGTH_LONG).show();
                        Intent intent;
                        intent = new Intent(getApplicationContext(), HomePage.class);
                        startActivity(intent);
                    }
                    else {
                        // An error occurred during authentication
                        Exception exception = task.getException();
                        if(exception instanceof FirebaseAuthInvalidUserException){
                            editTextLoginEmail.requestFocus();
                            editTextLoginEmail.setError("Email not found");
                        }else if(exception instanceof FirebaseAuthInvalidCredentialsException){
                            editTextPassword.requestFocus();
                            editTextPassword.setError("Incorrect password");
                        }

                        else{
                            Log.e("RegistrationError", "Error: " + task.getException()); // Log the error message
                            Toast.makeText(getApplicationContext(), "Login failed", Toast.LENGTH_LONG).show();
                        }

                    }
                }
            });
        }
    }

    public void togglePasswordVisibility(View v) {
        EditText editTextPassword = findViewById(R.id.edittext_Password);
        ImageView imageViewToogleShowPass = findViewById(R.id.imageViewPasswordToggle);
        if (editTextPassword.getInputType() == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
            editTextPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            imageViewToogleShowPass.setImageResource(R.drawable.baseline_remove_red_eye_24);
        } else {
            editTextPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            imageViewToogleShowPass.setImageResource(R.drawable.outline_remove_red_eye_24);
        }

        // Move the cursor to the end of the text
        editTextPassword.setSelection(editTextPassword.getText().length());
    }

    public void onClickSignUpNow(View v) {
        TextView txtViewSignUpNowText = findViewById(R.id.signupText);
        Intent intent;
        intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }
}