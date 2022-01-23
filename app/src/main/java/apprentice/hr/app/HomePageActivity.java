package apprentice.hr.app;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class HomePageActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Button candidate_button;
    Button employee_button;
    Button logout_button;

    DatabaseHelper myDB;
    ArrayList<String> candidate_id, candidate_name, candidate_phone, candidate_position, candidate_skills, candidate_date;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        recyclerView = findViewById(R.id.recyclerView);
        candidate_button = findViewById(R.id.candidate_button);
        candidate_button.setOnClickListener(v -> {
            Intent intent = new Intent(HomePageActivity.this, AddCandidateActivity.class);
            startActivity(intent);

        });
        employee_button = findViewById(R.id.employee_button);
        employee_button.setOnClickListener(v -> {
                    Intent intent = new Intent(HomePageActivity.this, EmployeeActivity.class);
                    startActivity(intent);
                });

        logout_button = findViewById(R.id.logout_button);
        logout_button.setOnClickListener(v -> {
            Intent intent = new Intent(HomePageActivity.this, InitialLoginActivity.class);
            startActivity(intent);
        });


        myDB = new DatabaseHelper(HomePageActivity.this);
        candidate_id = new ArrayList<>();
        candidate_name = new ArrayList<>();
        candidate_phone = new ArrayList<>();
        candidate_position = new ArrayList<>();
        candidate_skills = new ArrayList<>();
        candidate_date = new ArrayList<>();

        storeDataInArrays();

        customAdapter = new CustomAdapter(HomePageActivity.this, this, candidate_id, candidate_name, candidate_phone, candidate_position, candidate_skills, candidate_date);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(HomePageActivity.this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            recreate();
        }
    }

    void storeDataInArrays() {
        Cursor cursor = myDB.readAllData();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                candidate_id.add(cursor.getString(0));
                candidate_name.add(cursor.getString(1));
                candidate_phone.add(cursor.getString(2));
                candidate_position.add(cursor.getString(3));
                candidate_skills.add(cursor.getString(4));
                candidate_date.add(cursor.getString(5));

            }
        }
    }
}