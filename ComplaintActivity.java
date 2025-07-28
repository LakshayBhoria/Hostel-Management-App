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

public class ComplaintActivity extends AppCompatActivity {
    EditText categoryInput, descriptionInput;
    Button submitComplaintBtn;
    FirebaseAuth mAuth;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint);

        categoryInput = findViewById(R.id.categoryInput);
        descriptionInput = findViewById(R.id.descriptionInput);
        submitComplaintBtn = findViewById(R.id.submitComplaintBtn);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        submitComplaintBtn.setOnClickListener(v -> sendComplaint());
    }

    private void sendComplaint() {
        String category = categoryInput.getText().toString().trim();
        String description = descriptionInput.getText().toString().trim();

        if (category.isEmpty() || description.isEmpty()) {
            Toast.makeText(this, "Fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        Map<String, Object> complaint = new HashMap<>();
        complaint.put("userId", mAuth.getCurrentUser().getUid());
        complaint.put("category", category);
        complaint.put("description", description);
        complaint.put("status", "Pending");
        complaint.put("date", System.currentTimeMillis());

        db.collection("complaints").add(complaint)
                .addOnSuccessListener(doc -> Toast.makeText(this, "Complaint Submitted", Toast.LENGTH_SHORT).show())
                .addOnFailureListener(e -> Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show());
    }
}
