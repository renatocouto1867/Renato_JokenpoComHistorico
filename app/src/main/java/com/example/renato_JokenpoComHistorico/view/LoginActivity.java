package com.example.renato_JokenpoComHistorico.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.renato_JokenpoComHistorico.R;
import com.example.renato_JokenpoComHistorico.model.Jogo;
import com.example.renato_JokenpoComHistorico.viewModel.Auxiliar;
import com.google.android.material.button.MaterialButton;

public class LoginActivity extends AppCompatActivity {
    MaterialButton materialButtonJogar;
    EditText editTextNome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        materialButtonJogar = findViewById(R.id.button_login);
        editTextNome = findViewById(R.id.editTextNome);

        materialButtonJogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pegaLogin();
            }
        });

    }

    private void pegaLogin() {
        String nome = editTextNome.getText().toString().toLowerCase();
        if (!nome.isEmpty()) {
            nome = Auxiliar.limpaString(nome);
            Auxiliar.jogo = new Jogo(nome);
//            Toast.makeText(LoginActivity.this, "usuario logado "+ Auxiliar.jogo.getNomeJogador(), Toast.LENGTH_SHORT).show();
            Auxiliar.placarRecuperado(LoginActivity.this);
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.putExtra("nome", nome);
            startActivity(intent);
        } else {
            editTextNome.setError(getString(R.string.digite_seu_nome));
        }
    }


}