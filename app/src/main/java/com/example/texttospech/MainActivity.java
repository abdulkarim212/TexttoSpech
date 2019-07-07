package com.example.texttospech;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends Activity implements TextToSpeech.OnInitListener, View.OnClickListener {
    EditText input;
    Button button_clear, button_speak;
    TextToSpeech tts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        input = (EditText) findViewById(R.id.input);
        button_clear = (Button) findViewById(R.id.button_clear);
        button_speak = (Button) findViewById(R.id.button_speak);
        button_clear.setOnClickListener(this);
        button_speak.setOnClickListener(this);

        tts = new TextToSpeech(this, this);

    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS){
            Locale bahasa = tts.getLanguage();
            int result = tts.setLanguage(bahasa);
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED){
                Log.e("TTS", "This Language is not supported");
            }else {
                //
            }
        } else {
            Log.e("TTS","Initialkizatjhjhjion Failed");
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_clear:
                input.setText("");
                break;
            case R.id.button_speak:
            String text = input.getText().toString();
            if (text.isEmpty()){
                Toast.makeText(MainActivity.this, "Text is Empty", Toast.LENGTH_SHORT).show();

            }else {
                tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);

            }
            break;
        }

    }
}
