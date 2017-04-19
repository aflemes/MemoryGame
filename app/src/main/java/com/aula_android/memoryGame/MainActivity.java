package com.aula_android.memoryGame;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    GridView gridview;
    Button btnReiniciar;

    Runnable runVirarCartas = new Runnable() {
        @Override
        public void run(){
            virar_cartas(); //<-- put your code in here.
        }
    };

    @Override
    protected void onStart(){
        super.onStart();

        Handler handlerVirarCartas = new Handler();
        handlerVirarCartas.postDelayed(runVirarCartas, 4000);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        gridview = (GridView) findViewById(R.id.gridView1);
        gridview.setAdapter(new ImageAdapter(this));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                ImageAdapter img_adpt = (ImageAdapter) gridview.getAdapter();
                img_adpt.virar_carta(position);
            }
        });

        btnReiniciar = (Button) findViewById(R.id.btnReiniciar);
        btnReiniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageAdapter img_adpt = (ImageAdapter) gridview.getAdapter();
                img_adpt.reiniciar();

                Handler handlerVirarCartas = new Handler();
                handlerVirarCartas.postDelayed(runVirarCartas, 4000);
            }
        });
    }

    public void virar_cartas(){
        ImageAdapter img_adpt = (ImageAdapter) gridview.getAdapter();
        img_adpt.setImagens();
        img_adpt.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
