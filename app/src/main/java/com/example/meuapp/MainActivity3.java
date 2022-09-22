package com.example.meuapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.AsyncTaskLoader;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class MainActivity3 extends AppCompatActivity {

    private EditText etCep;
    private TextView tvRes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        etCep = findViewById(R.id.etCep);
        tvRes= findViewById(R.id.tvRes);
    }

    public void btClick(View view) throws Exception {
        String cep = etCep.getText().toString();
        String urlStr = "https://viacep.com.br/ws/" + cep.replace("-","") + "/json";
        HttpAsyncTask task = new HttpAsyncTask();
        task.execute(urlStr);
        String resposta = task.get();

        JSONObject obj = new JSONObject(resposta);
        String logr = obj.getString("logradouro");

        tvRes.setText(logr);
    }

}