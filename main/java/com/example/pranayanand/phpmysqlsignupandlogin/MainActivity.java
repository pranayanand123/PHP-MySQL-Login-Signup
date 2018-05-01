package com.example.pranayanand.phpmysqlsignupandlogin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText editname, editemail, editpassword;
    Button btnRegister;

    RadioGroup radioGroup;
    



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        editname = (EditText) findViewById(R.id.name);
        editemail = (EditText) findViewById(R.id.email);
        editpassword = (EditText) findViewById(R.id.password);
        btnRegister = (Button) findViewById(R.id.btnRegister);
        radioGroup = (RadioGroup) findViewById(R.id.radioGender);




        btnRegister.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {


        final String name = editname.getText().toString().trim();
        final String email = editemail.getText().toString().trim();
        final String password = editpassword.getText().toString().trim();
        final String gender = ((RadioButton) findViewById(radioGroup.getCheckedRadioButtonId())).getText().toString();

        if (TextUtils.isEmpty(name)) {
            editname.setError("Please enter username");
            editname.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(email)) {
            editemail.setError("Please enter your email");
            editemail.requestFocus();
            return;
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editemail.setError("Enter a valid email");
            editemail.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            editpassword.setError("Enter a password");
            editpassword.requestFocus();
            return;
        }


        ApiInterface apiInterface = RetrofitClient.getClient().create(ApiInterface.class);
        Call<Responses> call = apiInterface.register(name, email, password, gender);
        call.enqueue(new Callback<Responses>() {
            @Override
            public void onResponse(Call<Responses> call, Response<Responses> response) {

                try {
                    JSONObject jsonObject = new JSONObject(String.valueOf(response));
                    JSONObject user = jsonObject.getJSONObject("user");
                    Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_SHORT).show();

                    User u = new User(user.getInt("id"), user.getString("username"), user.getString("email"), user.getString("gender"));
                    SharedPrefManager.getInstance(getApplicationContext()).userLogin(u);



                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(intent);
                MainActivity.this.finish();












            }

            @Override
            public void onFailure(Call<Responses> call, Throwable t) {

                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();


            }
        });

    }
}
