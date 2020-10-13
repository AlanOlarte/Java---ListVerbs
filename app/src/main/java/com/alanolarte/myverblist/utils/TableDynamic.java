package com.alanolarte.myverblist.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.alanolarte.myverblist.R;

import java.util.ArrayList;

public class TableDynamic implements View.OnClickListener {

    private TTSManager tts;
    private LinearLayout tableLayout;
    private Context context;
    private int headder = 4;
    private ArrayList<String[]> data;
    private LinearLayout tableRow;
    private LinearLayout layoutContent;
    private TextView txtCell;
    private int[] arrayAutoSize = {9, 10, 11, 12, 13, 14};
    private int indexC;
    private int indexR;

    public TableDynamic(LinearLayout tableLayout, Context context){

        tts = new TTSManager(context);

        this.context = context;
        this.tableLayout = tableLayout;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void addData(ArrayList<String[]>data) {
        this.data = data;
        createDataTable();
    }

    public void clearData() {
        tableLayout.removeAllViewsInLayout();
        if (data!=null){
            data.clear();
        }
    }

    private void newRow() {
        tableRow = new LinearLayout(context);
        tableRow.setBackground(getDrawableWithRadius());
    }

    private Drawable getDrawableWithRadius() {
        GradientDrawable gradientDrawable   =   new GradientDrawable();
        gradientDrawable.setStroke(2, (context.getResources().getColor(R.color.colorBackgroundPrimaryBorder)));
        gradientDrawable.setCornerRadii(new float[]{20, 20, 20, 20, 20, 20, 20, 20});
        return gradientDrawable;
    }

    private Drawable getDrawableWithRadiusText() {
        GradientDrawable gradientDrawable   =   new GradientDrawable();
        gradientDrawable.setCornerRadii(new float[]{20, 20, 20, 20, 20, 20, 20, 20});
        return gradientDrawable;
    }

    private void newContent() {
        layoutContent = new LinearLayout(context);
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void newCell() {
        txtCell = new TextView(context);
        txtCell.setGravity(Gravity.CENTER);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            txtCell.setAutoSizeTextTypeUniformWithPresetSizes(arrayAutoSize, TypedValue.COMPLEX_UNIT_SP);
        } else {
            txtCell.setTextSize(12);
        }
        txtCell.setPadding(0, 30, 0, 30);
        txtCell.setTextColor(context.getResources().getColor(R.color.colorPrimaryText));
        txtCell.setBackground(getDrawableWithRadiusText());
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void createDataTable() {
        String info;
        for ( indexR = 0; indexR < data.size(); indexR++) {
            newContent();
            newRow();
            for ( indexC = 0; indexC < headder; indexC++) {
                newCell();
                String[] row = data.get(indexR);
                info=(indexC<row.length)?row[indexC]:"";
                txtCell.setText(info);
                txtCell.setId(Integer.parseInt( indexR + "" + indexC ) );
                txtCell.setOnClickListener(this);

                if (indexC!=headder-1){
                    txtCell.setClickable(true);
                    TypedValue outValue = new TypedValue();
                    context.getTheme().resolveAttribute(android.R.attr.selectableItemBackgroundBorderless, outValue, true);
                    txtCell.setBackgroundResource(outValue.resourceId);
                }

                tableRow.addView(txtCell,indexC,newTableRowParams());
            }
            layoutContent.addView(tableRow, 0, newLayoutContentParams());
            tableLayout.addView(layoutContent);
        }
        data.clear();
    }

    private LinearLayout.LayoutParams newTableRowParams() {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );
        //params.setMargins(0,10,0,10);
        params.weight = 1;
        return params;
    }

    private LinearLayout.LayoutParams newLayoutContentParams() {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
        );
        params.setMargins(10,15,10,15);
        params.weight = 1;
        return params;
    }

    private LinearLayout getLayoutContent(int index) {
        return (LinearLayout) tableLayout.getChildAt(index);
    }

    private TextView getCellText( int rowIndex, int columIndex ) {
        layoutContent = getLayoutContent(rowIndex);
        tableRow = (LinearLayout) layoutContent.getChildAt(0);
        TextView texto = (TextView) tableRow.getChildAt(columIndex);
        return texto;
    }

    private String getTextFromTextView(View view) {

        String num1 = String.valueOf(view.getId());
        String  fila = "";
        String columna = "";
        String texto = "";

        if (num1.length() < 2) {
            fila = "0";
            columna = num1;
        } else {
            if (num1.length()<3) {
                fila = String.valueOf(num1.charAt(0));
                columna = num1.substring(1);
            } else {
                if (num1.length()<4) {
                    fila = num1.substring(0,2);
                    columna = String.valueOf(num1.charAt(2));
                } else {
                    fila = num1.substring(0,3);
                    columna = String.valueOf(num1.charAt(3));
                }
            }
        }

        if (!columna.equals("3")) {
            //Toast.makeText(context, texto, Toast.LENGTH_SHORT).show();
            texto = getCellText(Integer.parseInt(fila.trim()),Integer.parseInt(columna.trim())).getText().toString();
        }
        return texto;
    }

    @Override
    public void onClick(View view) {
        tts.speak(getTextFromTextView(view));
    }

    public void speechShutdown() {
        tts.onPause();
    }
}
