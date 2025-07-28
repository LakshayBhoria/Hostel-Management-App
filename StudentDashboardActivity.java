package com.example.hostelease;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class StudentDashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_dashboard);

        findViewById(R.id.leaveBtn).setOnClickListener(v ->
                startActivity(new Intent(this, LeaveRequestActivity.class)));

        findViewById(R.id.complaintBtn).setOnClickListener(v ->
                startActivity(new Intent(this, ComplaintActivity.class)));

        findViewById(R.id.menuBtn).setOnClickListener(v ->
                startActivity(new Intent(this, MessMenuActivity.class)));

        findViewById(R.id.announcementBtn).setOnClickListener(v ->
                startActivity(new Intent(this, AnnouncementActivity.class)));
    }
}
