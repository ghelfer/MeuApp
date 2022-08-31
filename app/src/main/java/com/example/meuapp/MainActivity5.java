package com.example.meuapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity5 extends AppCompatActivity {

    private ListView listView;

    String [] de = {"img","dt","vlr","city"};
    int [] para = {R.id.imgViagem, R.id.txtData, R.id.txtValor, R.id.txtDestino};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        listView = findViewById(R.id.listView);

        int [] imagens = {R.drawable.ic_agro,R.drawable.ic_seat,R.drawable.ic_anchor,R.drawable.ic_agro,R.drawable.ic_seat,R.drawable.ic_anchor};
        String [] datas = {"02/09/2022","03/09/2022","04/09/2022","05/09/2022","06/09/2022","07/09/2022"};
        String [] valores = {"R$ 20,00","R$ 10,00","R$ 90,00","R$ 80,00","R$ 70,00","R$ 60,00"};
        String [] destinos = {"SCS", "RPD", "VCZ", "POA", "CRI", "ITU"};

        List<HashMap<String, Object>> lista = new ArrayList<>();
        HashMap<String, Object> item;
        for (int i = 0; i < destinos.length; i++) {
            item = new HashMap<>();
            item.put(de[0], imagens[i]);
            item.put(de[1], datas[i]);
            item.put(de[2], valores[i]);
            item.put(de[3], destinos[i]);
            lista.add(item);
        }

        SimpleAdapter adapter = new SimpleAdapter(this, lista,
                R.layout.line_item,de,para);
        listView.setAdapter(adapter);
    }
}