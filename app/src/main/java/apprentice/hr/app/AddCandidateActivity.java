package apprentice.hr.app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

public class AddCandidateActivity extends AppCompatActivity {

    EditText editTextName, editTextPhone, editTextPosition, editTextSkills, editTextDate, mDateFormat;
    Button add_candidate_data_btn;
    Button back_list_btn;
    DatePickerDialog.OnDateSetListener onDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_candidate);
        back_list_btn = findViewById(R.id.back_list_btn);
        back_list_btn.setOnClickListener(v -> {
            Intent intent = new Intent(AddCandidateActivity.this, HomePageActivity.class);
            startActivity(intent);
        });

        editTextName = findViewById(R.id.editTextName);
        editTextPhone = findViewById(R.id.editTextPhone);
        editTextPosition = findViewById(R.id.editTextPosition);
        editTextSkills = findViewById(R.id.editTextSkills);
        //editTextDate = findViewById(R.id.editTextDate);
        add_candidate_data_btn = findViewById(R.id.add_candidate_data_btn);
        add_candidate_data_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper myDB = new DatabaseHelper(AddCandidateActivity.this);
                myDB.addCandidate(editTextName.getText().toString().trim(),
                        editTextPhone.getText().toString().trim(),
                        editTextPosition.getText().toString().trim(),
                        editTextSkills.getText().toString().trim(),
                        editTextDate.getText().toString().trim());

            }
        });

        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        mDateFormat = findViewById(R.id.editTextDate);

        mDateFormat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        AddCandidateActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        onDateSetListener, day, month, year);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();

            }
        });
        onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int dayOfMonth, int month, int year) {
                month = month + 1;
                String date = dayOfMonth + "/" + month + "/" + year;
                mDateFormat.setText(date);
            }
        };

    }
}
