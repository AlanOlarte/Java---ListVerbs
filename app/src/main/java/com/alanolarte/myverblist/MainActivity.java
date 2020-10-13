package com.alanolarte.myverblist;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.alanolarte.myverblist.utils.ListFilling;
import com.alanolarte.myverblist.utils.TableDynamic;

public class MainActivity extends AppCompatActivity {

    private LinearLayout tableLayout;
    private ListFilling list;
    private TableDynamic tableDynamic;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tableLayout = (LinearLayout) findViewById(R.id.table);

        tableDynamic = new TableDynamic(tableLayout, getApplicationContext());
        list = new ListFilling(getApplicationContext(), 0);
        tableDynamic.addData(list.FillList());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_filtro, menu);
        return true;
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.all_verb_item:
                list = null;
                list = new ListFilling(getApplicationContext(), 0);
                tableDynamic.clearData();
                tableDynamic.addData(list.FillList());
                return true;
            case R.id.regular_verb_item:
                list = null;
                list = new ListFilling(getApplicationContext(), 1);
                tableDynamic.clearData();
                tableDynamic.addData(list.FillList());
                return true;
            case R.id.irregular_verb_item:
                list = null;
                list = new ListFilling(getApplicationContext(), 2);
                tableDynamic.clearData();
                tableDynamic.addData(list.FillList());
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onDestroy() {
        tableDynamic.speechShutdown();
        super.onDestroy();
    }

}
