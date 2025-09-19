package com.example.lab2_1089635;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;

public class SentenceActivity extends Activity {
    EditText editSentence;
    TextView tvSelectedSentence;
    SentenceDatabaseHelper dbHelper;
    String currentSentence = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sentence);

        editSentence = findViewById(R.id.editSentence);
        tvSelectedSentence = findViewById(R.id.tvSelectedSentence);
        dbHelper = new SentenceDatabaseHelper(this);
    }

    // Called when Insert button is clicked
    public void onInsertClick(View v) {
        String sentence = editSentence.getText().toString().trim();
        if (!sentence.isEmpty()) {
            dbHelper.insertSentence(sentence);
            Toast.makeText(this, "Sentence inserted!", Toast.LENGTH_SHORT).show();
            editSentence.setText("");
        } else {
            Toast.makeText(this, "Please enter a sentence.", Toast.LENGTH_SHORT).show();
        }
    }

    // Called when Select button is clicked
    public void onSelectClick(View v) {
        currentSentence = dbHelper.getRandomSentence(); // or however you get the random sentence
        tvSelectedSentence.setText(currentSentence);
        Log.w("Lab2Demo", "Random sentence selected: " + currentSentence);
    }

    // Called when Run button is clicked
    public void onRunClick(View v) {
        if (!currentSentence.isEmpty()) {
            String[] words = currentSentence.split(" ");
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("words", words);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Please select a sentence first!", Toast.LENGTH_SHORT).show();
        }
    }
}