package com.example.hostelease;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class WardenDashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warden_dashboard);

        findViewById(R.id.manageLeavesBtn).setOnClickListener(v ->
                startActivity(new Intent(this, LeaveRequestActivity.class)));

        findViewById(R.id.manageComplaintsBtn).setOnClickListener(v ->
                startActivity(new Intent(this, ComplaintActivity.class)));

        findViewById(R.id.manageMenuBtn).setOnClickListener(v ->
                startActivity(new Intent(this, MessMenuActivity.class)));

        findViewById(R.id.postAnnouncementBtn).setOnClickListener(v ->
                startActivity(new Intent(this, AnnouncementActivity.class)));
    }
}
