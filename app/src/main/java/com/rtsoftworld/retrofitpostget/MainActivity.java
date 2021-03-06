package com.rtsoftworld.retrofitpostget;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.rtsoftworld.retrofitpostget.Interface.ApiInterface;
import com.rtsoftworld.retrofitpostget.Model.ServerResponse;
import com.rtsoftworld.retrofitpostget.Model.User;
import com.rtsoftworld.retrofitpostget.Retrofit.RetrofitApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    private ApiInterface apiInterface;
    private EditText edt_login_name;
    private EditText edt_login_password;
    private Button login_btn;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apiInterface = RetrofitApiClient.getClient().create(ApiInterface.class);

        edt_login_name = findViewById(R.id.edt_login_name);
        edt_login_password = findViewById(R.id.edt_login_password);
        login_btn = findViewById(R.id.login_btn);
        textView = findViewById(R.id.textView);

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                init();
            }
        });


    }

    void init(){
        String userId;
        String password;
        User user = new User();

        userId = edt_login_name.getText().toString();
        password = edt_login_password.getText().toString();

        user.setUserId(userId);
        user.setPassword(password);

        checkUserValidity(user);

        getJokeFromServer(userId);
    }

    private void getJokeFromServer(String userId) {
        Call<ServerResponse> call = apiInterface.getJoke(userId);
        call.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                ServerResponse serverResponse = response.body();
                textView.setText(serverResponse.getMessageString());
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {

            }
        });
    }

    //post method to determine user validity
    private void checkUserValidity(User userCredential) {

        //Toast.makeText(MainActivity.this, "Button Clicked!", Toast.LENGTH_SHORT).show();

        Call<ServerResponse> call = apiInterface.getUserValidity(userCredential);

        call.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                ServerResponse validity = response.body();

                if (validity != null){
                    Toast.makeText(MainActivity.this, validity.getMessageString(), Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(MainActivity.this, "Server response is null.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                Log.e("TAG", t.toString());
            }
        });

    }


}