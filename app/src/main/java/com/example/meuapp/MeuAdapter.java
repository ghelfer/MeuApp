package com.example.meuapp;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

public class MeuAdapter extends SimpleAdapter {

    public MeuAdapter(Context ctx, List<HashMap<String, Object>> lista, int line_item,
                      String[] de, int[] para) {
        super(ctx,lista,line_item,de,para);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = super.getView(position, convertView, parent);

        if (position % 2 == 0) {
            v.setBackgroundColor(Color.CYAN);
            TextView tv = v.findViewById(R.id.txtValor);
            tv.setTextColor(Color.parseColor("#FF0000"));
            tv.setBackgroundColor(Color.YELLOW);
        }
        else {
            v.setBackgroundColor(Color.GREEN);
        }

        return v;
    }
}
