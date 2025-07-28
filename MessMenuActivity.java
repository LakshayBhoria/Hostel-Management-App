package com.example.hostelease;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.Map;

public class MessMenuActivity extends AppCompatActivity {
    EditText dayInput, breakfastInput, lunchInput, dinnerInput;
    Button saveMenuBtn;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mess_menu);

        dayInput = findViewById(R.id.dayInput);
        breakfastInput = findViewById(R.id.breakfastInput);
        lunchInput = findViewById(R.id.lunchInput);
        dinnerInput = findViewById(R.id.dinnerInput);
        saveMenuBtn = findViewById(R.id.saveMenuBtn);

        db = FirebaseFirestore.getInstance();

        saveMenuBtn.setOnClickListener(v -> saveMenu());
    }

    private void saveMenu() {
        String day = dayInput.getText().toString().trim();
        String breakfast = breakfastInput.getText().toString().trim();
        String lunch = lunchInput.getText().toString().trim();
        String dinner = dinnerInput.getText().toString().trim();

        if (day.isEmpty() || breakfast.isEmpty() || lunch.isEmpty() || dinner.isEmpty()) {
            Toast.makeText(this, "Fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        Map<String, Object> menu = new HashMap<>();
        menu.put("day", day);
        menu.put("breakfast", breakfast);
        menu.put("lunch", lunch);
        menu.put("dinner", dinner);

        db.collection("messMenu").document(day).set(menu)
                .addOnSuccessListener(doc -> Toast.makeText(this, "Menu Saved", Toast.LENGTH_SHORT).show())
                .addOnFailureListener(e -> Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show());
    }
}
