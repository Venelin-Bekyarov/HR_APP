package apprentice.hr.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private Context context;
    Activity activity;
    private ArrayList candidate_id, candidate_name, candidate_phone, candidate_position, candidate_skills, candidate_date;


    CustomAdapter(Activity activity, Context context, ArrayList candidate_id, ArrayList candidate_name, ArrayList candidate_phone, ArrayList candidate_position, ArrayList candidate_skills,
                  ArrayList candidate_date) {
        this.activity = activity;
        this.context = context;
        this.candidate_id = candidate_id;
        this.candidate_name = candidate_name;
        this.candidate_phone = candidate_phone;
        this.candidate_position = candidate_position;
        this.candidate_skills = candidate_skills;
        this.candidate_date = candidate_date;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.res_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        holder.candidate_id_txt.setText(String.valueOf(candidate_id.get(position)));
        holder.candidate_name_txt.setText(String.valueOf(candidate_name.get(position)));
        holder.candidate_phone_txt.setText(String.valueOf(candidate_phone.get(position)));
        holder.candidate_position_txt.setText(String.valueOf(candidate_position.get(position)));
        holder.candidate_skills_txt.setText(String.valueOf(candidate_skills.get(position)));
        holder.candidate_date_txt.setText(String.valueOf(candidate_date.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id", String.valueOf(candidate_id.get(position)));
                intent.putExtra("name", String.valueOf(candidate_name.get(position)));
                intent.putExtra("phone", String.valueOf(candidate_phone.get(position)));
                intent.putExtra("position", String.valueOf(candidate_position.get(position)));
                intent.putExtra("skills", String.valueOf(candidate_skills.get(position)));
                intent.putExtra("date", String.valueOf(candidate_date.get(position)));
                activity.startActivityForResult(intent, 1);

            }
        });

    }

    @Override
    public int getItemCount() {
        return candidate_id.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView candidate_id_txt, candidate_name_txt, candidate_phone_txt, candidate_position_txt, candidate_skills_txt, candidate_date_txt;
        LinearLayout mainLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            candidate_id_txt = itemView.findViewById(R.id.candidate_id_txt);
            candidate_name_txt = itemView.findViewById(R.id.candidate_name_txt);
            candidate_phone_txt = itemView.findViewById(R.id.candidate_phone_txt);
            candidate_position_txt = itemView.findViewById(R.id.candidate_position_txt);
            candidate_skills_txt = itemView.findViewById(R.id.candidate_skills_txt);
            candidate_date_txt = itemView.findViewById(R.id.candidate_date_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
