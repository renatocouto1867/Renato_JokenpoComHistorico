//versão final
package com.example.renato_JokenpoComHistorico.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.renato_JokenpoComHistorico.R;
import com.example.renato_JokenpoComHistorico.model.Jogada;
import com.example.renato_JokenpoComHistorico.viewModel.Auxiliar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageButton imageButtonPedra = findViewById(R.id.buttonPedra); //0
        ImageButton imageButtonPapel = findViewById(R.id.buttonPapel); //1
        ImageButton imageButtonTesoura = findViewById(R.id.buttonTesoura); //2

        imageButtonPedra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                criaJogada(0);
            }
        });

        imageButtonPapel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                criaJogada(1);
            }
        });

        imageButtonTesoura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                criaJogada(2);
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

    private void criaJogada(int opcao) {
        Intent intentNome=getIntent();
//        String nome = intentNome.getStringExtra("nome");
        Intent intent = new Intent(MainActivity.this, ResultadoActivity.class);
        Jogada jogada = Auxiliar.criaUmaJogada(opcao, MainActivity.this);
        int ponto = jogada.getPonto();
        int sorteio = jogada.getSorteio();
        boolean impate = jogada.isImpate();
        intent.putExtra("ponto", ponto);
        intent.putExtra("sorteio", sorteio);
        intent.putExtra("impate", impate);
//        intent.putExtra("nome", nome);
        startActivity(intent);
    }

}