package com.example.hostelease;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.Map;

public class AnnouncementActivity extends AppCompatActivity {
    EditText titleInput, descriptionInput;
    Button postAnnouncementBtn;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcement);

        titleInput = findViewById(R.id.titleInput);
        descriptionInput = findViewById(R.id.descriptionInput);
        postAnnouncementBtn = findViewById(R.id.postAnnouncementBtn);

        db = FirebaseFirestore.getInstance();

        postAnnouncementBtn.setOnClickListener(v -> postAnnouncement());
    }

    private void postAnnouncement() {
        String title = titleInput.getText().toString().trim();
        String description = descriptionInput.getText().toString().trim();

        if (title.isEmpty() || description.isEmpty()) {
            Toast.makeText(this, "Fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        Map<String, Object> announcement = new HashMap<>();
        announcement.put("title", title);
        announcement.put("description", description);
        announcement.put("date", System.currentTimeMillis());

        db.collection("announcements").add(announcement)
                .addOnSuccessListener(doc -> Toast.makeText(this, "Announcement Posted", Toast.LENGTH_SHORT).show())
                .addOnFailureListener(e -> Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show());
    }
}
