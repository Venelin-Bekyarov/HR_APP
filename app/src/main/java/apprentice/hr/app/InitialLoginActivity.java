package apprentice.hr.app;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
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
    EmployeeDBHelper DB;


//    Username = "HRTeam@edynamix.com";
//    Password = "HR_admin1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial_login);

        username = findViewById(R.id.username);
        password = findViewById((R.id.password));

        btnLogin = findViewById(R.id.btnLogin);
        DB= new EmployeeDBHelper(this);

        final LoadingDialog loadingDialog = new LoadingDialog(InitialLoginActivity.this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user = username.getText().toString();
                String pass = password.getText().toString();

                if (user.equals("")||pass.equals("")){
                    Toast.makeText(InitialLoginActivity.this, "Please fill all fields!", Toast.LENGTH_SHORT).show();
                }
                else if (user.equals("HRTeam@edynamix.com") && pass.equals("HR_admin1")){
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

                }
                else {
                    Boolean checkUserPass = DB.checkUsernamePassword(user, pass);
                    if (checkUserPass == true) {
                        Toast.makeText(InitialLoginActivity.this, "Sign in successful!", Toast.LENGTH_SHORT).show();

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
                                "Invalid Username/Password and/or not an HR account!", Toast.LENGTH_LONG).show();

                    }
                }
            }
        });
    }
}
