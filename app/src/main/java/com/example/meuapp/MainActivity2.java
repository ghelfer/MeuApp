package com.example.meuapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends ListActivity
        implements AdapterView.OnItemClickListener {

    List<String> listagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ListView listView = getListView();
        listView.setOnItemClickListener(this);

        listagem = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            listagem.add("valor" + i);
        }
        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, listagem);
        setListAdapter(adapter);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        String item = adapterView.getItemAtPosition(i).toString();
        //String item2 = listagem.get(i);
        Toast.makeText(this, item, Toast.LENGTH_LONG).show();
    }
}