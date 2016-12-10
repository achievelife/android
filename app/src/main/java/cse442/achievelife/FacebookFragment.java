package cse442.achievelife;

import android.content.Intent;
import android.content.pm.PackageInstaller;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.json.*;

import static com.facebook.HttpMethod.POST;

/**
 * A placeholder fragment containing a simple view.
 */
public class FacebookFragment extends Fragment {

    private TextView mTextDetails;
    private Toast toast;


    /* ----------------------------*/
    /* ----------------------------*/
    /* ---This is the SessionID ---*/
    /* ----------------------------*/
    /* ----------------------------*/
    private String sessionID;



    private CallbackManager mCallBackManager;
    private FacebookCallback<LoginResult> mCallBack = new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {

            AccessToken accessToken = loginResult.getAccessToken();
            Profile profile = Profile.getCurrentProfile();

            if(accessToken!=null){
                Intent intent = new Intent(getActivity().getApplicationContext(), FitnessMap.class);
                startActivity(intent);

                String url = "https://achievelife.devnull.rocks/api/v1/fblogin";
                //String url = "http://metatree.xyz";
                final String field = "token";
                final String value = accessToken.getToken();

                RequestQueue queue = Volley.newRequestQueue(getActivity());

                // Request a string response from the provided URL.
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // Display the first 500 characters of the response string.
                                //mTextView.setText("Response is: "+ response.substring(0,500));
                                Log.d("Test Response", response);
                                try {


                                 /* ----------------------------*/
                                 /* ----------------------------*/
                                 /* --Call Back with SessionID--*/
                                 /* ----------------------------*/
                                 /* ----------------------------*/

                                    JSONObject json = new JSONObject(response);
                                    sessionID = json.getString("session");
                                    Log.d("SessionID:", sessionID);


                                } catch(JSONException e){}

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Test Response", "Didn't work.");
                    }
                }) {

                    @Override
                    protected Map<String,String> getParams(){
                        Map<String,String> params = new HashMap<String, String>();
                        params.put(field,value);

                        return params;
                    }

                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        Map<String,String> params = new HashMap<String, String>();
                        params.put("Content-Type","application/x-www-form-urlencoded");
                        return params;
                    }
                };
                // Add the request to the RequestQueue.
                queue.add(stringRequest);


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
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        if(accessToken!=null){
            Intent intent = new Intent(getActivity().getApplicationContext(), FitnessMap.class);
            startActivity(intent);

        }

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