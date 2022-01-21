package apprentice.hr.app;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class UpdateActivity extends AppCompatActivity {

    EditText name_input, phone_input, position_input, skills_input, mDateFormat;
    Button update_button, delete_button;

    String id, name, phone, position, skills, date;

    DatePickerDialog.OnDateSetListener onDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        name_input = findViewById(R.id.editTextName2);
        phone_input = findViewById(R.id.editTextPhone2);
        position_input = findViewById(R.id.editTextPosition2);
        skills_input = findViewById(R.id.editTextSkills2);
        mDateFormat = findViewById(R.id.editTextDate2);
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
                date = mDateFormat.getText().toString().trim();
                myDB.updateData(id, name, phone, position, skills, date);

            }
        });

        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        mDateFormat = findViewById(R.id.editTextDate2);

        mDateFormat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        UpdateActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        onDateSetListener, year, month, day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                datePickerDialog.show();

            }
        });
        onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = dayOfMonth + "/" + month + "/" + year;
                mDateFormat.setText(date);
            }
        };

        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });
        name_input.addTextChangedListener(AddTextWatcher);
        phone_input.addTextChangedListener(AddTextWatcher);
        position_input.addTextChangedListener(AddTextWatcher);
        mDateFormat.addTextChangedListener(AddTextWatcher);

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
            mDateFormat.setText(date);
        } else {
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + name + " ?");
        builder.setMessage("Are you sure you want to delete " + name + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DatabaseHelper myDB = new DatabaseHelper(UpdateActivity.this);
                myDB.deleteOneLine(id);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }

    private TextWatcher AddTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String nameUpdt = name_input.getText().toString();
            String phoneUpdt = phone_input.getText().toString();
            String positionUpdt = position_input.getText().toString();
            String dateUpdt = mDateFormat.getText().toString();



            update_button.setEnabled(!nameUpdt.isEmpty() && !phoneUpdt.isEmpty() && !positionUpdt.isEmpty() && !dateUpdt.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
}