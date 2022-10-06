package com.example.meuapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity4 extends AppCompatActivity {

    private Database helper;
    private EditText txtAno;

    private ListView listView;
    List<Map<String, Object>> lista;
    String[] de = {"id", "modelo", "ano", "valor"};
    int[] para = {R.id.id, R.id.modelo, R.id.ano, R.id.valor};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        txtAno = findViewById(R.id.txtAno);
        listView = findViewById(R.id.listView);
        helper = new Database(this);
    }

    public void actionClick(View view) {

        String busca = txtAno.getText().toString();
        String query = "";
        if (busca.isEmpty()) {
            query = "SELECT * FROM carro";
        }
        else {
            query = "SELECT * FROM carro WHERE ano = " + busca;
        }
            lista = listarCarros(query);
        SimpleAdapter adaptador = new SimpleAdapter(this,
                lista,
                R.layout.listagem,
                de,
                para);
        listView.setAdapter(adaptador);

        }

    private List<Map<String, Object>> listarCarros(String query) {
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        lista = new ArrayList<Map<String, Object>>();

        Map<String, Object> item = new HashMap<String, Object>();

        item.put("id", "id");
        item.put("modelo",  "modelo");
        item.put("ano",   "ano");
        item.put("valor", "Valor");
        lista.add(item);

        for (int i = 0; i < cursor.getCount(); i++) {
            item = new HashMap<String, Object>();
            String id = cursor.getString(0);
            String modelo = cursor.getString(1);
            int ano = cursor.getInt(2);
            double valor = cursor.getDouble(3);
            item.put("id",   id);
            item.put("modelo",  modelo);
            item.put("ano",  + ano);
            item.put("valor", String.format("R$ %.2f", valor));
            lista.add(item);
            cursor.moveToNext();
        }
        cursor. close();
        return lista;
    }

}
/*
        SQLiteDatabase db = helper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("modelo", "XR3 Conversivel");
        values.put("ano", 1987);
        values.put("valor", 33500.00);
        long resultado = db.insert("carro", null, values);
        if (resultado != -1) {
            Toast.makeText(this, "Registro salvo cos sucesso!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Erro ao salvar!", Toast.LENGTH_SHORT).show();
        }*/