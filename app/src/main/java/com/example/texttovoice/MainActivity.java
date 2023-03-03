package com.example.texttovoice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private TextToSpeech textToSpeech;

    ImageView imageView;
    EditText  editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.bt_img);
        editText = findViewById(R.id.txt_write);

      textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
          @Override
          public void onInit(int i) {

              if(i == TextToSpeech.SUCCESS){

              }
          }
      });
      imageView.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              String text = editText.getText().toString();
              textToSpeech.speak(text,TextToSpeech.QUEUE_FLUSH,null);

              InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
              imm.hideSoftInputFromWindow(editText.getWindowToken(),0);
              editText.clearFocus();
              editText.setText("");
          }
      });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        textToSpeech.stop();
        textToSpeech.shutdown();
    }
}