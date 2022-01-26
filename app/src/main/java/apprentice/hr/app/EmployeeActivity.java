package apprentice.hr.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.navigation.NavigationBarView;

public class EmployeeActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText username, password, repassword, status;

    Button signup, backList;
    EmployeeDBHelper DB;
    int lastSelectedStatusPos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);

        Spinner spinner = findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.status_picks, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);


        username = (EditText) findViewById(R.id.editTextUsername);
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
                String stat = (String) spinner.getAdapter().getItem(lastSelectedStatusPos);


                if (user.equals("") || pass.equals("") || repass.equals("") || stat.equals(""))
                    Toast.makeText(EmployeeActivity.this, "Mandatory fields!", Toast.LENGTH_SHORT).show();
                else {
                    if (validatePassword() && validateUsername()) {
                        if (pass.equals(repass)) {
                            Boolean checkUser = DB.checkUsername(user);
                            if (checkUser == false) {
                                Boolean insert = DB.insertData(user, pass, stat);
                                if (insert == true) {
                                    Toast.makeText(EmployeeActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(EmployeeActivity.this, "Registration Failed!", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(EmployeeActivity.this, "User already exists!", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(EmployeeActivity.this, "Passwords not matching!", Toast.LENGTH_SHORT).show();
                        }
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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
        String text = parent.getItemAtPosition(position).toString();
        lastSelectedStatusPos = position;

        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    private boolean validatePassword() {
        String passwordInput = password.getText().toString().trim();

        if (!passwordInput.matches(".*[0-9].*")) {
            Toast.makeText(EmployeeActivity.this, "Password should contain at least 1 digit", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!passwordInput.matches(".*[a-z].*")) {
            Toast.makeText(EmployeeActivity.this, "Password should contain at least 1 lower case letter", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!passwordInput.matches(".*[\\\\\\/%§\"&“|`´}{°><:.;#')(@_$\"!?*=^-].*")) {
            Toast.makeText(EmployeeActivity.this, "Password should contain at least 1 special character", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!passwordInput.matches(".*[A-Z].*")) {
            Toast.makeText(EmployeeActivity.this, "Password should contain at least 1 upper case letter", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!passwordInput.matches(".*[a-zA-Z].*")) {
            Toast.makeText(EmployeeActivity.this, "Password should contain a letter", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!passwordInput.matches(".{8,24}")) {
            Toast.makeText(EmployeeActivity.this, "Password should contain between 8 and 24 characters", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }


    private boolean validateUsername() {
        String passwordInput = username.getText().toString().trim();

        if (!passwordInput.matches(".*[a-zA-Z0-9]{2,10}@[edynamix.com].*")) {
            Toast.makeText(EmployeeActivity.this, "Username should be a valid email address", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }
}
