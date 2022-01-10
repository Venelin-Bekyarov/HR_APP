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

    }
}