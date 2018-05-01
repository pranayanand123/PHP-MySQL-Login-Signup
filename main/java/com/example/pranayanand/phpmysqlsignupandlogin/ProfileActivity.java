package com.example.pranayanand.phpmysqlsignupandlogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {
    TextView textViewId, textViewUsername, textViewEmail, textViewGender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);





        textViewId = (TextView) findViewById(R.id.textViewId);
        textViewUsername = (TextView) findViewById(R.id.textViewUsername);
        textViewEmail = (TextView) findViewById(R.id.textViewEmail);
        textViewGender = (TextView) findViewById(R.id.textViewGender);


        //getting the current user
        User user = SharedPrefManager.getInstance(this).getUser();

        //setting the values to the textviews
        textViewId.setText(String.valueOf(user.getId()));
        textViewUsername.setText(user.getUsername());
        textViewEmail.setText(user.getEmail());
        textViewGender.setText(user.getGender());
        Log.d("information", user.getUsername());

        //when the user presses logout button
        //calling the logout method
        findViewById(R.id.buttonLogout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPrefManager.getInstance(getApplicationContext()).logout();
                finish();
            }
        });


    }
}
