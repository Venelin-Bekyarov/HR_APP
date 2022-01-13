package apprentice.hr.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddCandidateActivity extends AppCompatActivity {

    EditText editTextName, editTextPhone, editTextPosition, editTextSkills, editTextDate;
    Button add_candidate_data_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_candidate);

        editTextName = findViewById(R.id.editTextName);
        editTextPhone = findViewById(R.id.editTextPhone);
        editTextPosition = findViewById(R.id.editTextPosition);
        editTextSkills = findViewById(R.id.editTextSkills);
        editTextDate = findViewById(R.id.editTextDate);
        add_candidate_data_btn = findViewById(R.id.add_candidate_data_btn);
        add_candidate_data_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper myDB = new DatabaseHelper(AddCandidateActivity.this);
                myDB.addCandidate(editTextName.getText().toString().trim(),
                        editTextPhone.getText().toString().trim(),
                        editTextPosition.getText().toString().trim(),
                        editTextSkills.getText().toString().trim(),
                        Integer.parseInt(editTextDate.getText().toString().trim()));

            }
        });
    }
}