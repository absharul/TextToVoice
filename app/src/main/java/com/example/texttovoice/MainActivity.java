package com.example.texttovoice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextToSpeech textToSpeech;

    ImageView imageView;
    EditText  editText;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter<MyAdapter.ViewHolder> adapter;
    private List<String> aList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.bt_img);
        editText = findViewById(R.id.txt_write);

        RecyclerView recyclerView = findViewById(R.id.main_recyshow);

        aList = new ArrayList<>();
        adapter = new MyAdapter(aList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);


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
              textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null);

              InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
              imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
              if (!text.isEmpty()) {
                  aList.add(text);
                  adapter.notifyDataSetChanged();
                  editText.clearFocus();// Notify the adapter of data change
                  editText.setText(""); // Clear the EditText
              }
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