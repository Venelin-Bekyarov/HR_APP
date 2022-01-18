package apprentice.hr.app;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    EditText name_input, phone_input, position_input, skills_input, date_input;
    Button update_button, delete_button;

    String id, name, phone, position, skills, date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        name_input = findViewById(R.id.editTextName2);
        phone_input = findViewById(R.id.editTextPhone2);
        position_input = findViewById(R.id.editTextPosition2);
        skills_input = findViewById(R.id.editTextSkills2);
        date_input = findViewById(R.id.editTextDate2);
        update_button = findViewById(R.id.update_btn);
        delete_button = findViewById(R.id.delete_btn);
        getAndSetIntentData();

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(name);
        }

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper myDB = new DatabaseHelper(UpdateActivity.this);
                name = name_input.getText().toString().trim();
                phone = phone_input.getText().toString().trim();
                position = position_input.getText().toString().trim();
                skills = skills_input.getText().toString().trim();
                date = date_input.getText().toString().trim();
                myDB.updateData(id, name, phone, position, skills, date);

            }
        });
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });


    }

    void getAndSetIntentData() {
        if (getIntent().hasExtra("id") && getIntent().hasExtra("name") && getIntent().hasExtra("phone") && getIntent().hasExtra("position")
                && getIntent().hasExtra("skills") && getIntent().hasExtra("date")) {
            id = getIntent().getStringExtra("id");
            name = getIntent().getStringExtra("name");
            phone = getIntent().getStringExtra("phone");
            position = getIntent().getStringExtra("position");
            skills = getIntent().getStringExtra("skills");
            date = getIntent().getStringExtra("date");

            name_input.setText(name);
            phone_input.setText(phone);
            position_input.setText(position);
            skills_input.setText(skills);
            date_input.setText(date);
        } else {
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + name + " ?");
        builder.setMessage("Are you sure you want to delete " + name + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DatabaseHelper myDB = new DatabaseHelper(UpdateActivity.this);
                myDB.deleteOneLine(id);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}