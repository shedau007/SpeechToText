package com.saurabh.speechtotext;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognitionService;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView SpeakText;
    private Button SpeakButton;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SpeakText = (TextView) findViewById(R.id.SpeakText);
        SpeakButton = (Button) findViewById(R.id.SpeakButton);


        SpeakButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Speak(view);

            }
        });

    }

    private  void Speak(View view){

        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_SPEECH_INPUT_POSSIBLY_COMPLETE_SILENCE_LENGTH_MILLIS,5);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Start Speaking ...");
        startActivityForResult(intent , 007);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 007 &&  resultCode == RESULT_OK){

            SpeakText.setText(data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS).get(0));
        }
    }
}