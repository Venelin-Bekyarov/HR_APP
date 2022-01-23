package apprentice.hr.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EmployeeActivity extends AppCompatActivity {

    EditText username, password, repassword, status;
    Button signup, backList;
    EmployeeDBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);

        username = (EditText) findViewById(R.id.editTextUsername);
        status = (EditText) findViewById(R.id.editTextStatus);
        password = (EditText) findViewById(R.id.editTextPass);
        repassword = (EditText) findViewById(R.id.editTextRetype);
        signup = (Button) findViewById(R.id.add_accountBtn);
        DB = new EmployeeDBHelper(this);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();
                String stat = status.getText().toString();

                if (user.equals("") || pass.equals("") || repass.equals("") || status.equals(""))
                    Toast.makeText(EmployeeActivity.this, "Mandatory fields!", Toast.LENGTH_SHORT).show();
                else {
                    if (pass.equals(repass)) {
                        Boolean checkUser = DB.checkUsername(user);
                        if (checkUser == false) {
                            Boolean insert = DB.insertData(user, pass, stat);
                            if (insert==true){
                                Toast.makeText(EmployeeActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(EmployeeActivity.this, "Registration Failed!", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(EmployeeActivity.this, "User already exists!", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(EmployeeActivity.this, "Passwords not matching!", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        backList = findViewById(R.id.back_list_btn);
        backList.setOnClickListener(v -> {
            Intent intent = new Intent(EmployeeActivity.this, HomePageActivity.class);
            startActivity(intent);
        });

    }

}
