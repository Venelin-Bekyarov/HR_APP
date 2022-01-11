package apprentice.hr.app;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InitialLoginActivity extends AppCompatActivity {

    EditText username, password;
    Button btnLogin;

    String correct_username = "1";
    String correct_password = "1";

//    String correct_username = "HRTeam@edynamix.com";
//    String correct_password = "HR_admin1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial_login);

        username = findViewById(R.id.username);
        password = findViewById((R.id.password));
        btnLogin = findViewById(R.id.btnLogin);

        final LoadingDialog loadingDialog = new LoadingDialog(InitialLoginActivity.this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(username.getText().toString()) || TextUtils.isEmpty(
                        password.getText().toString())) {
                    Toast.makeText(InitialLoginActivity.this, "Empty data provided",
                            Toast.LENGTH_LONG).show();
                } else if (username.getText().toString().equals(correct_username)) {
                    if (password.getText().toString().equals(correct_password)) {
                        Toast.makeText(InitialLoginActivity.this, "Success",
                                Toast.LENGTH_LONG).show();

                        AlertDialog.Builder builder = new AlertDialog.Builder(
                                InitialLoginActivity.this);
                        builder.setMessage("Are you sure you want to log in?").setCancelable(
                                false).setPositiveButton("Yes",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        loadingDialog.startLoadingDialog();
                                        Handler handler = new Handler();
                                        handler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                Intent intent = new Intent(InitialLoginActivity.this, HomePageActivity.class);
                                                startActivity(intent);
                                                loadingDialog.dismissDialog();
                                            }
                                        }, 3000);

                                    }
                                }).setNegativeButton("No",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        InitialLoginActivity.this.finish();
                                        dialog.cancel();
                                    }
                                });

                        AlertDialog alert = builder.create();
                        alert.show();
                        alert.getButton(alert.BUTTON_NEGATIVE).setTextColor(Color.parseColor("#FF0000"));
                        alert.getButton(alert.BUTTON_POSITIVE).setTextColor(Color.parseColor("#008000"));

                    } else {
                        Toast.makeText(InitialLoginActivity.this,
                                "Invalid Username and/or Password", Toast.LENGTH_LONG).show();

                    }
                } else {
                    Toast.makeText(InitialLoginActivity.this,
                            "Invalid Username and/or Password", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
