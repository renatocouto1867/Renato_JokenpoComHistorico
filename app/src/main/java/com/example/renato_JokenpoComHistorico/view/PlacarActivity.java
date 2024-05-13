package com.example.renato_JokenpoComHistorico.view;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.renato_JokenpoComHistorico.R;
import com.example.renato_JokenpoComHistorico.viewModel.Auxiliar;
import com.google.android.material.button.MaterialButton;

public class PlacarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_placar);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView textViewPrimeiro = findViewById(R.id.textViewPrimeiro);
        TextView textViewSegundo = findViewById(R.id.textViewSegundo);

        textViewPrimeiro.setText(Auxiliar.jogo.getPrimeiroColocado(PlacarActivity.this));
        textViewSegundo.setText(Auxiliar.jogo.getSegundoColocado(PlacarActivity.this));

        MaterialButton materialButtonNovo = findViewById(R.id.buttonNovo);
        materialButtonNovo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Auxiliar.abrirMainActivity(PlacarActivity.this);
            }
        });
        setupToolbar();
    }//onCreate

    private void setupToolbar() {
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        myToolbar.setTitle("");
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return Auxiliar.onOptionsItemSelected(this, item);
    }
}