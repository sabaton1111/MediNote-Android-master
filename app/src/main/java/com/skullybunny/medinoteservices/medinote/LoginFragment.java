package com.skullybunny.medinoteservices.medinote;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginFragment extends BaseFragment {

    MediNoteWebAPI mMediNoteWebAPI;
    UserType userType;
    String toDelete;

    Button mLoginButton;
    EditText mEditTextLoginUsername;
    EditText mEditTextLoginPassword;

    public LoginFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMediNoteWebAPI = MediNoteWeb.getWebAPIInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.login_fragment, container, false);

        initializeViews(view);

        mLoginButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        handleLoginClick();
                    }
                }
        );

        return view;
    }

    @Override
    protected void initializeViews(View view) {
        super.initializeViews(view);

        mLoginButton = (Button) view.findViewById(R.id.login_login_button);
        mEditTextLoginUsername = (EditText) view.findViewById(R.id.login_nickname);
        mEditTextLoginPassword = (EditText) view.findViewById(R.id.login_password);
    }

    public void handleLoginClick()
    {
        String loginUsername = mEditTextLoginUsername.getText().toString();
        String loginPassword = mEditTextLoginPassword.getText().toString();

        Call<OAuthToken> call = mMediNoteWebAPI.postCredentials("password", loginUsername, loginPassword);
        call.enqueue(new Callback<OAuthToken>() {
            @Override
            public void onResponse(Call<OAuthToken> call, Response<OAuthToken> response) {
                if (response.isSuccessful())
                {
                    OAuthToken token = response.body();
                    getAccountType(token);
                }
                else
                {
                    Toast.makeText(getActivity(), response.code() + ": " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<OAuthToken> call, Throwable throwable) {
                throwable.printStackTrace();
            }
        });
    }

    private void initializeCurrentUser(OAuthToken token)
    {
        ApplicationUser user = new ApplicationUser(token);
        user.setAccountType(userType.getAccountType());
        CurrentUser.setUser(mActivity, user);
    }

    private void getAccountType(final OAuthToken token)
    {
        Call<UserType> call = MediNoteWeb.getWebAPIInstance().getAccountType(token.getAuthorization());
        call.enqueue(new Callback<UserType>() {
            @Override
            public void onResponse(Call<UserType> call, Response<UserType> response) {
                if (response.isSuccessful())
                {
                    userType = response.body();
                    initializeCurrentUser(token);
                    startMainNavigationActivity();
                }
                else
                {
                    try {
                        Toast.makeText(mActivity, response.code() + ": " + response.errorBody().string(), Toast.LENGTH_SHORT).show();
                    }
                    catch (IOException exception)
                    {
                        exception.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<UserType> call, Throwable throwable) {
                throwable.printStackTrace();
            }
        });
    }

    private void startMainNavigationActivity()
    {
        Intent intent = new Intent(mActivity, Navigation.class);
        startActivity(intent);
        getActivity().finish();
    }
}
