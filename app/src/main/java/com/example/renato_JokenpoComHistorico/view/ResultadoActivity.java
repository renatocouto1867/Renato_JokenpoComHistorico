package com.example.renato_JokenpoComHistorico.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
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

public class ResultadoActivity extends AppCompatActivity {

    ImageView imageViewResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_resultado);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView textViewResultado = findViewById(R.id.textViewResultado);
        imageViewResultado = findViewById(R.id.imageViewResultado);

        Intent intent = getIntent();
        boolean impate = intent.getBooleanExtra("impate", false);
        int ponto = intent.getIntExtra("ponto", -1);
        int sorteio = intent.getIntExtra("sorteio", -1);

        if (impate) {
            textViewResultado.setText(R.string.voce_empatou);
            defineImagem(sorteio);
        } else {
            if (ponto == 0) {
                textViewResultado.setText(R.string.voce_perdeu);
            } else if (ponto == 1) {
                textViewResultado.setText(R.string.voce_ganhou);
            } else {
                textViewResultado.setText(R.string.erro_no_sistema);
            }
            defineImagem(sorteio);
        }

        MaterialButton materialButtonNovo = findViewById(R.id.buttonNovoJogo);
        MaterialButton materialButtonPlacar = findViewById(R.id.buttonPlacar);

        materialButtonNovo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Auxiliar.abrirMainActivity(ResultadoActivity.this);
            }
        });

        materialButtonPlacar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Auxiliar.abrirPlacarActivity(ResultadoActivity.this);
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

    private void defineImagem(int sorteio) {
        switch (sorteio) {
            case 0:
                imageViewResultado.setImageResource(R.drawable.pedra);
                break;
            case 1:
                imageViewResultado.setImageResource(R.drawable.papel);
                break;
            case 2:
                imageViewResultado.setImageResource(R.drawable.tesoura);
                break;
            default:
                imageViewResultado.setImageResource(R.drawable.icon);
        }
    }
}
