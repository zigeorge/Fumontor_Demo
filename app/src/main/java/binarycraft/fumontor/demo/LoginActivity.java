package binarycraft.fumontor.demo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import binarycraft.fumontor.demo.Utils.ApplicationUtility;
import binarycraft.fumontor.demo.Utils.StaticData;
import binarycraft.fumontor.demo.interfaces.APIServiceInterface;
import binarycraft.fumontor.demo.responses.SignUpResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    Button btnFacebook;

    CallbackManager callbackManager;
    LoginManager loginManager;
    AccessToken accessToken;

    Retrofit retrofit;
    APIServiceInterface apiService;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        context = this;

        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);

        initUI();
        addClickListener();
        initWebService();
    }

    private void initUI(){
        btnFacebook = (Button) findViewById(R.id.btnFacebook);
    }

    private void addClickListener(){
        btnFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginByFacebook();
            }
        });
    }

    private void initWebService() {
        retrofit = new Retrofit.Builder()
                .baseUrl(StaticData.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(APIServiceInterface.class);
    }

    //Integrating Facebook Sign In Starts >
    private void loginByFacebook() {
        callbackManager = CallbackManager.Factory.create();
        ArrayList<String> permissions = new ArrayList<String>();
        permissions.add("email");
        loginManager.logInWithReadPermissions(this, permissions);
        loginManager.registerCallback(callbackManager, facebookCallbackManager);

    }

    FacebookCallback<LoginResult> facebookCallbackManager = new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {
            accessToken = loginResult.getAccessToken();
            GraphRequest request = GraphRequest.newMeRequest(accessToken, new GraphRequest.GraphJSONObjectCallback() {
                @Override
                public void onCompleted(JSONObject jsonObject, GraphResponse graphResponse) {
                    Log.d("FacebookData", jsonObject.toString());
                    Bundle bundle = ApplicationUtility.getFacebookData(jsonObject, accessToken);
                    signUpUser(bundle, "facebook");
                }
            });
            requestToExecuteAPI(request);
        }

        @Override
        public void onCancel() {

        }

        @Override
        public void onError(FacebookException e) {

        }
    };

    private void requestToExecuteAPI(GraphRequest request) {
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id, first_name, middle_name, last_name, email, gender, birthday, location"); // Facebook permission parameters
        request.setParameters(parameters);
        request.executeAsync();
    }

    private void signUpUser(Bundle bundleString, String authType) {
        String jSonParam = ApplicationUtility.getSignUpJSONParam(bundleString, authType);
        String imgUrl = bundleString.getString(StaticData.USER_IMAGE);
        String link = bundleString.getString(StaticData.ACCESSTOKEN);
        try {
            jSonParam = URLEncoder.encode(jSonParam, "UTF-8");
            Log.e("JSONPARAM", jSonParam);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Call<SignUpResponse> call = apiService.signUpUser(jSonParam, imgUrl, link);
        call.enqueue(signUpResponseCallback);
    }

    Callback<SignUpResponse> signUpResponseCallback = new Callback<SignUpResponse>() {
        @Override
        public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
            if (response.body().isSuccess()) {
                Log.e("RESPONSE", response.body().getMessage());
                processSignIn(response.body());
            } else {
                Log.e("RESPONSE", response.body().getMessage());
            }
        }

        @Override
        public void onFailure(Call<SignUpResponse> call, Throwable t) {
            Toast.makeText(context, "Problem connecting server. Try again", Toast.LENGTH_SHORT).show();
        }
    };

    private void processSignIn(SignUpResponse response) {
        if (response.isSuccess()) {
            Log.e("USER_ID", response.getUser_id());
        } else {
            Toast.makeText(context, "Try Again.. It seems we cannot authenticate your account from server", Toast.LENGTH_LONG).show();
        }
    }
}
