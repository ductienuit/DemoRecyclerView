package com.example.ductien.viewlist;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

public class LoginActivity extends AppCompatActivity {

    private static final String EMAIL = "email";
    private static final String USER_POSTS = "user_posts";
    private static final String AUTH_TYPE = "rerequest";

    Button btnLogin, btnLoginGmail;
    LoginButton btnLoginFB;
    EditText input_email, input_password;
    TextView link_signup;
    final int REQUEST_SIGNUP = 59;
    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        callbackManager = CallbackManager.Factory.create();
        addControl();
        addEvent();
    }

    private void addControl() {
        btnLogin = findViewById(R.id.btn_login);
        btnLoginFB = findViewById((R.id.btn_loginFB));
        btnLoginGmail = findViewById(R.id.btn_loginGmail);
        input_email = findViewById((R.id.input_email));
        input_password = findViewById(R.id.input_password);
        link_signup = findViewById(R.id.link_signup);
    }

    private void addEvent() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        btnLoginGmail.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //handle click btnLogin gmail
            }
        });
        link_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the Signup activity
                Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
            }
        });

        // Set the initial permissions to request from the user while logging in
        btnLoginFB.setReadPermissions(Arrays.asList(EMAIL, USER_POSTS,"public_profile"));

        btnLoginFB.setAuthType(AUTH_TYPE);

        // Register a callback to respond to the user
        btnLoginFB.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                setResult(RESULT_OK);

                receiveDataUser(loginResult);

                //Go to main layout
                Intent main = new Intent(LoginActivity.this, MainActivity.class);
                LoginActivity.this.startActivity(main);
                Toast.makeText(LoginActivity.this, loginResult.getAccessToken().getUserId(), Toast.LENGTH_SHORT).show();
                //finish();
            }

            @Override
            public void onCancel() {
                setResult(RESULT_CANCELED);
                //finish();
            }

            @Override
            public void onError(FacebookException e) {
                Toast.makeText(LoginActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                // Handle exception
            }
        });
    }

    private void receiveDataUser(LoginResult loginResult) {
        AccessToken accessToken = loginResult.getAccessToken();

        //Get email or something about user
        GraphRequest request = GraphRequest.newMeRequest(
                accessToken,
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(
                            JSONObject object,
                            GraphResponse response) {
                        // Application code
                        try {
                            String Name = object.getString("name");
                            //String FEmail = object.getString("email");
                            Log.v("Name = ", " " + Name);
                            Toast.makeText(getApplicationContext(), "Name " + Name, Toast.LENGTH_LONG).show();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });

        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,link");
        request.setParameters(parameters);
        request.executeAsync();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {
                // TODO: Implement successful signup logic here
                // By default we just finish the Activity and log them in automatically
                this.finish();
            }
        }
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void login() {
        if (!validate()) {
            onLoginFailed();
            return;
        }

        btnLogin.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
                R.style.Theme_AppCompat_Light_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        String email = input_email.getText().toString();
        String password = input_password.getText().toString();

        // TODO: Implement your own authentication logic here.

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onLoginSuccess or onLoginFailed
                        onLoginSuccess();
                        // onLoginFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);
    }

    @Override
    public void onBackPressed() {
        // disable going back to the MainActivity
        moveTaskToBack(true);
    }

    public void onLoginSuccess() {
        btnLogin.setEnabled(true);
        finish();
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        btnLogin.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String email = input_email.getText().toString();
        String password = input_password.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            input_email.setError("enter a valid email address");
            valid = false;
        } else {
            input_email.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            input_password.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            input_password.setError(null);
        }
        return valid;
    }

}
