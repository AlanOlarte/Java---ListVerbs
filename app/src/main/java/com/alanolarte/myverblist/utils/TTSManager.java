package com.alanolarte.myverblist.utils;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.util.Log;

import java.util.Locale;

public class TTSManager {

    private TextToSpeech tts = null;
    private boolean isLoaded = false;
    private boolean rate = true;
    private Context context;
    String repeat;

    public TTSManager(Context context) {
        this.context = context;
        initTTS();
    }

    public void initTTS() {
        try {
            tts = new TextToSpeech(context, onInitListener);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    TextToSpeech.OnInitListener onInitListener = new TextToSpeech.OnInitListener() {
        @Override
        public void onInit(int status) {
            if (status == TextToSpeech.SUCCESS){
                int result = tts.setLanguage(Locale.US);
                isLoaded = true;

                if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED){
                    Log.d("TTS", "Idimoa no soportado");
                }else{
                    Log.d("TTS", "TTS configurado correctamente");
                }
            }else{
                Log.d("TTS","Voz no inicializada");
            }
        }
    };

    public void speak(String etText){
        boolean firstTouch = true;

        if (repeat == etText) {
            firstTouch = false;
            rate = !rate;
        } else {
            firstTouch = true;
            rate = true;
        }

        if (isLoaded) {
            if (rate || firstTouch) {
                tts.setSpeechRate(0.8f);
            } else {
                tts.setSpeechRate(0.05f);
            }
            tts.speak(etText,TextToSpeech.QUEUE_FLUSH,null);
        }
        repeat = etText;
    }

    public void onPause(){
        if(tts !=null){
            tts.stop();
            tts.shutdown();
        }
    }
}
