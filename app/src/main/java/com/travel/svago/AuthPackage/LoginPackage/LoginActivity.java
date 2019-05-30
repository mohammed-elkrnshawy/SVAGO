package com.travel.svago.AuthPackage.LoginPackage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.travel.svago.AuthPackage.ForgetPasswordActivity;
import com.travel.svago.AuthPackage.RegisterPackage.RegisterActivity;
import com.travel.svago.Models.SharedResponses.userData;
import com.travel.svago.R;
import com.travel.svago.SharedPackage.Activity.HomeActivity;
import com.travel.svago.SharedPackage.Activity.MainHomeActivity;
import com.travel.svago.SharedPackage.Classes.Constant;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements LoginInterface {

    private static final int RC_SIGN_IN = 1;
    private static final String EMAIL = "email";

    @BindView(R.id.edtEmail)
    EditText edtEmail;
    @BindView(R.id.edtPassword)
    EditText edtPassword;
    @BindView(R.id.imgShow)
    ImageView imgShow;
    @BindView(R.id.cartLogin)
    CardView cartLogin;
    @BindView(R.id.txtRegister)
    TextView txtRegister;
    @BindView(R.id.skip)
    TextView skip;

    String tag = "";

    GoogleSignInClient mGoogleSignInClient;
    @BindView(R.id.sign_in_button)
    SignInButton signInButton;
    @BindView(R.id.faceBook_login_button)
    LoginButton faceBookLoginButton;

    private LoginPresenter loginPresenter;
    private boolean isHide = true;
    CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initGoogleSignin();
        getData();
        initComponents();
        register();
        setFaceBookLoginButton();
    }

    private void initComponents() {
        loginPresenter = new LoginPresenter(this);
        callbackManager = CallbackManager.Factory.create();
    }

    @Override
    public void getData() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            tag = bundle.getString("tag", "");
            Log.d("OOO", tag);
        }
    }

    @OnClick({R.id.cartLogin, R.id.txtRegister, R.id.skip, R.id.imgShow})
    void onButtonClick(View view) {
        switch (view.getId()) {
            case R.id.cartLogin:
                loginPresenter.validData();
                break;
            case R.id.txtRegister:
                loginPresenter.openRegister();
                break;
            case R.id.skip:
                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                intent.putExtra(Constant.userFlag, new userData());
                startActivity(intent);
                finish();
                break;
            case R.id.imgShow:
                showAndHidePassword();
                break;
        }
    }

    @Override
    public void validDate() {
        if (!Patterns.EMAIL_ADDRESS.matcher(edtEmail.getText()).matches()) {
            edtEmail.setError(getResources().getString(R.string.requiredField));
            edtEmail.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(edtPassword.getText())) {
            edtPassword.setError(getResources().getString(R.string.requiredField));
            edtPassword.requestFocus();
            return;
        }

        loginPresenter.callLogon(edtEmail.getText().toString().trim()
                , edtPassword.getText().toString().trim()
        );
    }

    @Override
    public void openRegister() {
        startActivity(new Intent(this, RegisterActivity.class));
    }

    @Override
    public void successLogin(userData userData) {
        if (tag.equals(Constant.GuideTag)) {
            Intent intent = new Intent(this, MainHomeActivity.class);
            intent.putExtra(Constant.userFlag, userData);
            intent.putExtra(Constant.TypeTag, Constant.GuideTag);
            HomeActivity.setData(userData);
            startActivity(intent);
            finishAffinity();
        } else {
            Intent intent = new Intent(this, HomeActivity.class);
            intent.putExtra(Constant.userFlag, userData);
            startActivity(intent);
            finishAffinity();
        }
    }

    @Override
    public void showAndHidePassword() {
        if (isHide) {
            isHide = false;
            imgShow.setImageResource(R.drawable.ic_view);
            edtPassword.setInputType(InputType.TYPE_CLASS_TEXT |
                    InputType.TYPE_TEXT_VARIATION_NORMAL);
            edtPassword.setSelection(edtPassword.getText().length());
        } else {
            isHide = true;
            imgShow.setImageResource(R.drawable.ic_hide);
            edtPassword.setInputType(InputType.TYPE_CLASS_TEXT |
                    InputType.TYPE_TEXT_VARIATION_PASSWORD);
            edtPassword.setSelection(edtPassword.getText().length());
        }
    }

    @OnClick(R.id.forget)
    public void onViewClicked() {
        Intent intent = new Intent(this, ForgetPasswordActivity.class);
        startActivity(intent);
    }

    @Override
    public void initGoogleSignin() {
        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        // Customizing G+ button
        signInButton.setSize(SignInButton.SIZE_WIDE);

        signInButton.setScopes(gso.getScopeArray());
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        //updateUI(account);
    }

    @Override
    public void updateUI(GoogleSignInAccount account) {
        if (account != null) {
            loginPresenter.socialLogin(account.getEmail(), account.getDisplayName(), "google", account.getId(), String.valueOf(account.getPhotoUrl()));
        } else {

        }
    }

    @OnClick(R.id.sign_in_button)
    public void onSigninBtnClicked() {
        signIn();
    }

    @Override
    public void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.
            updateUI(account);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("TAG", "signInResult:failed code=" + e.getStatusCode());
            updateUI(null);
        }
    }


    ///region FaceBook
    private void register() {
        // Callback registration
        faceBookLoginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(final LoginResult loginResult) {
                // App code
                Toast.makeText(LoginActivity.this, "register", Toast.LENGTH_SHORT).show();

                if (loginResult.getAccessToken() != null && !loginResult.getAccessToken().isExpired()) {
                    final Profile profile = Profile.getCurrentProfile();
                    GraphRequest request = GraphRequest.newMeRequest(
                            loginResult.getAccessToken(),
                            new GraphRequest.GraphJSONObjectCallback() {
                                @Override
                                public void onCompleted(JSONObject object, GraphResponse response) {
                                    // Application code
                                    try {
                                        String email = object.getString("email");
                                        loginPresenter.socialLogin(email , profile.getName() , "facebook" , loginResult.getAccessToken().getUserId() , profile.getProfilePictureUri(512 , 512).toString());
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                    Bundle parameters = new Bundle();
                    parameters.putString("fields", "id,name,email,gender, birthday");
                    request.setParameters(parameters);
                    request.executeAsync();

                }
            }

            @Override
            public void onCancel() {
                // App code
                Toast.makeText(LoginActivity.this, "Register : Cancelled", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
            }
        });
    }

    private void setFaceBookLoginButton() {
        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(final LoginResult loginResult) {
                        // App code
                        //Toast.makeText(LoginActivity.this, "Login here", Toast.LENGTH_SHORT).show();
                        if (loginResult.getAccessToken() != null && !loginResult.getAccessToken().isExpired()) {

                            final Profile profile = Profile.getCurrentProfile();
                            GraphRequest request = GraphRequest.newMeRequest(
                                    loginResult.getAccessToken(),
                                    new GraphRequest.GraphJSONObjectCallback() {
                                        @Override
                                        public void onCompleted(JSONObject object, GraphResponse response) {
                                            // Application code
                                            try {
                                                String email = object.getString("email");
                                                loginPresenter.socialLogin(email , profile.getName() , "facebook" , loginResult.getAccessToken().getUserId() , profile.getProfilePictureUri(512 , 512).toString());
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }

                                            // 01/31/1980 format
                                        }
                                    });
                            Bundle parameters = new Bundle();
                            parameters.putString("fields", "id,name,email,gender, birthday");
                            request.setParameters(parameters);
                            request.executeAsync();
                        }
                    }

                    @Override
                    public void onCancel() {
                        // App code
                        Toast.makeText(LoginActivity.this, "Login : Cancelled", Toast.LENGTH_SHORT).show();
                        //LoginManager.getInstance().logOut();

                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                    }
                });
    }

    @OnClick(R.id.faceBook_login_button)
    public void onFaceBookBtnClicked() {
        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile","email", "user_birthday", "user_friends"));
    }
    //endregion


}
