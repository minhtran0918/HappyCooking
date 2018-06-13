package calebzone.hcmute.edu.vn.happycooking.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.util.Arrays;

import calebzone.hcmute.edu.vn.happycooking.R;

public class LoginActivity extends AppCompatActivity {

    private LoginButton mBtnLoginFacebook;
    private CallbackManager mCallbackManager;
    String mess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_login_facebook);
        loginFb();
    }

    private void loginFb() {

        mCallbackManager = CallbackManager.Factory.create();

        mBtnLoginFacebook = (LoginButton) findViewById(R.id.login_button);
        mBtnLoginFacebook.setReadPermissions(Arrays.asList("public_profile"));
        /*// If using in a fragment
        loginButton.setFragment(this);*/

        // Callback registration
        mBtnLoginFacebook.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                //loginResult AccessToken
                // App code
                mess = "User ID: " + loginResult.getAccessToken().getUserId() + "\n" +
                        "Auth Token: " + loginResult.getAccessToken().getToken();
                Toast.makeText(LoginActivity.this, mess, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancel() {
                // App code
                mess = "Login Cancel";
                Toast.makeText(LoginActivity.this, mess, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
                mess = "Login failed";
                Toast.makeText(LoginActivity.this, mess, Toast.LENGTH_LONG).show();
            }
        });
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();
        if (isLoggedIn) {
            Toast.makeText(this, "is Logged in true", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(this, "is logged in false", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
