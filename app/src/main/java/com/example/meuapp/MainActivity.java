package com.example.meuapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = findViewById(R.id.spinner1);

        String [] array = {"a","b","c"};

        ArrayAdapter adapter = new ArrayAdapter<>(this,
                R.layout.meu_spinner_item1, array);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(),array[i],
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.meu_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId()){

            case R.id.item1:
                Intent intent = new Intent(MainActivity.this,
                        MainActivity2.class);
                startActivity(intent);
                return true;
            case R.id.item2:
                Intent intent2 = new Intent(MainActivity.this,
                        MainActivity5.class);
                startActivity(intent2);
                return true;
            case R.id.item3:
                Intent intent3 = new Intent(MainActivity.this,
                        MainActivity3.class);
                startActivity(intent3);
                return true;
            case R.id.item4:
                Toast.makeText(this, meuMetodoJni(),Toast.LENGTH_LONG).show();
                return true;
        }

        return false;
    }

    public void actionAula(View view) {
        Intent intent4 = new Intent(MainActivity.this,
                MainActivity4.class);
        startActivity(intent4);
    }

    public void actionAula6(View view) {
        Intent intent6 = new Intent(MainActivity.this,
                MainActivity6.class);
        startActivity(intent6);
    }

    static {
        System.loadLibrary("meuapp");
    }

    public native String meuMetodoJni();
    public native int meuMetodoJni2(int [] a);
}