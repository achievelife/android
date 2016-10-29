package com.example.admin.facebooklog;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

/**
 * A placeholder fragment containing a simple view.
 */
public class FacebookFragment extends Fragment {

    private TextView mTextDetails;
    private Toast toast;

    private CallbackManager mCallBackManager;
    private FacebookCallback<LoginResult> mCallBack = new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {
            AccessToken accessToken = loginResult.getAccessToken();
            Profile profile = Profile.getCurrentProfile();
            if(profile!=null){
            //    mTextDetails.setText("Success");
                toast.setGravity(Gravity.TOP| Gravity.LEFT,0,0);
                toast.makeText(getActivity(),"Welcome " + profile.getName(),toast.LENGTH_SHORT).show();
            }
               // toast.setGravity(Gravity.TOP| Gravity.LEFT,0,0);
               // toast.makeText(FacebookFragment.this,answer.getText(),toast.LENGTH_SHORT).show();            }
        }

        @Override
        public void onCancel() {

        }

        @Override
        public void onError(FacebookException error) {

        }
    };

    public FacebookFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState){

        toast = new Toast(getActivity().getApplicationContext());

        super.onCreate(savedInstanceState);
        mTextDetails = (TextView) getActivity().findViewById(R.id.textView2);
        FacebookSdk.sdkInitialize(getActivity().getApplicationContext());
        mCallBackManager = CallbackManager.Factory.create();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_facebook_login, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);
        LoginButton loginButton = (LoginButton) view.findViewById(R.id.login_button);
        loginButton.setReadPermissions("user_friends");         // Isn't Neccessary For this Part
        loginButton.setFragment(this);
        loginButton.registerCallback(mCallBackManager,mCallBack);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        mCallBackManager.onActivityResult(requestCode,resultCode,data);
    }
}
