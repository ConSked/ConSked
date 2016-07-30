package com.emailxl.consked;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Login extends AppCompatActivity {
    private static final String TAG = "Login";
    private static final boolean LOG = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findViewById(R.id.button_login).setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        Intent inMain = new Intent(view.getContext(), MainActivity.class);
                        startActivity(inMain);
                        finish();
                    }
                }
        );
    }
}
