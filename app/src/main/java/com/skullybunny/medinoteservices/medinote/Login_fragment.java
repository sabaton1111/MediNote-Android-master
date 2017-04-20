package com.skullybunny.medinoteservices.medinote;

import android.app.Activity;
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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login_fragment extends Fragment {

    private Activity mActivity;
    SharedPreferences sharedPreferences;
    MediNoteWebAPI mMediNoteWebAPI;
    OAuthToken mOAuthToken;
    String mCredentials;

    EditText mEditTextLoginUsername;
    EditText mEditTextLoginPassword;

    public Login_fragment() {
    }

    private void ChangeFragment(Fragment fragment, boolean addReverseTransaction)
    {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container_home, fragment);
        if (addReverseTransaction)
        {
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.commit();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();
        mMediNoteWebAPI = MediNoteWeb.getWebAPIInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_fragment, container, false);

        Button loginButton = (Button) view.findViewById(R.id.login_login_button);
        mEditTextLoginUsername = (EditText) view.findViewById(R.id.login_nickname);
        mEditTextLoginPassword = (EditText) view.findViewById(R.id.login_password);
        loginButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        HandleLoginClick();
                    }
                }
        );

        //Button registerButton = (Button) view.findViewById(R.id.buttonRegisterStartUp);
        //registerButton.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //   public void onClick(View view) {
        //        ChangeFragment(new layout.StartFragment(), true);
        //    }
        //});

        return view;
    }

    public void HandleLoginClick()
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
                    initializeCurrentUser(token);
                    startMainNavigationActivity();
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
        CurrentUser.setUser(mActivity, user);
    }

    private void startMainNavigationActivity()
    {
        Intent intent = new Intent(mActivity, Navigation.class);
        startActivity(intent);
        mActivity.finish();
    }
}
