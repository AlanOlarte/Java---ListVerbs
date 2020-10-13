package com.alanolarte.myverblist.utils;

import android.content.Context;
import android.widget.LinearLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class ListFilling {


    private Context context;
    private ArrayList<String[]> rows = new ArrayList<>();
    private int type;


    public ListFilling(Context context, int type) {
        this.type = type;
        this.context = context;
    }

    public ArrayList<String[]> FillList() {
        rows.clear();

        String jsonFileContent = null;

        try {
            jsonFileContent = ReadingJson.leerJson(context,"list_verb.json");
            JSONArray jsonArray = new JSONArray(jsonFileContent);

            for ( int i=0; i < jsonArray.length(); i++ ) {
                JSONObject jsonObj = jsonArray.getJSONObject(i);

                switch (type) {
                    case 0:
                        rows.add( new String[] {
                                jsonObj.getString("infinitive"),
                                jsonObj.getString("past"),
                                jsonObj.getString("participle"),
                                jsonObj.getString("spanish")
                        });
                        break;
                    case 1:
                        if ( jsonObj.getInt("type")==0 ){
                            rows.add( new String[] {
                                    jsonObj.getString("infinitive"),
                                    jsonObj.getString("past"),
                                    jsonObj.getString("participle"),
                                    jsonObj.getString("spanish")
                            });
                        }
                        break;
                    case 2:
                        if ( jsonObj.getInt("type")==1 ){
                            rows.add( new String[] {
                                    jsonObj.getString("infinitive"),
                                    jsonObj.getString("past"),
                                    jsonObj.getString("participle"),
                                    jsonObj.getString("spanish")
                            });
                        }
                        break;
                }
            }
        } catch (IOException | JSONException e) {
            e.getMessage();
        }
        return rows;
    }
}
