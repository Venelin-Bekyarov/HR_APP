package apprentice.hr.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InitialLoginActivity extends AppCompatActivity {

    EditText username, password;
    Button btnLogin;

    String correct_username = "HRTeam@edynamix.com";
    String correct_password = "HR_admin1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial_login);

        username = findViewById(R.id.username);
        password = findViewById((R.id.password));
        btnLogin = findViewById(R.id.btnLogin);

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