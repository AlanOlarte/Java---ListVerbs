package com.alanolarte.myverblist.utils;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReadingJson {

    public static String leerJson(Context context, String filename) throws IOException {
        BufferedReader reader = null;
        String unicode = "UTF-8";

        reader = new BufferedReader(new InputStreamReader(context.getAssets().open(filename),unicode));
        String content = "";
        String line;

        while ( (line = reader.readLine()) != null ) {
            content = content + line;
        }

        return content;
    }
}
