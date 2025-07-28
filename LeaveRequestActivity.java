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

public class LeaveRequestActivity extends AppCompatActivity {
    EditText startDateInput, endDateInput, reasonInput;
    Button submitLeaveBtn;
    FirebaseAuth mAuth;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave_request);

        startDateInput = findViewById(R.id.startDateInput);
        endDateInput = findViewById(R.id.endDateInput);
        reasonInput = findViewById(R.id.reasonInput);
        submitLeaveBtn = findViewById(R.id.submitLeaveBtn);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        submitLeaveBtn.setOnClickListener(v -> sendLeaveRequest());
    }

    private void sendLeaveRequest() {
        String startDate = startDateInput.getText().toString().trim();
        String endDate = endDateInput.getText().toString().trim();
        String reason = reasonInput.getText().toString().trim();

        if (startDate.isEmpty() || endDate.isEmpty() || reason.isEmpty()) {
            Toast.makeText(this, "Fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        Map<String, Object> leave = new HashMap<>();
        leave.put("userId", mAuth.getCurrentUser().getUid());
        leave.put("startDate", startDate);
        leave.put("endDate", endDate);
        leave.put("reason", reason);
        leave.put("status", "Pending");

        db.collection("leaveRequests").add(leave)
                .addOnSuccessListener(doc -> Toast.makeText(this, "Leave Requested", Toast.LENGTH_SHORT).show())
                .addOnFailureListener(e -> Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show());
    }
}
