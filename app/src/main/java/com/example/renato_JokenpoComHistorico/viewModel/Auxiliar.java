package com.example.renato_JokenpoComHistorico.viewModel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.renato_JokenpoComHistorico.R;
import com.example.renato_JokenpoComHistorico.model.Jogada;
import com.example.renato_JokenpoComHistorico.model.Jogo;
import com.example.renato_JokenpoComHistorico.view.MainActivity;
import com.example.renato_JokenpoComHistorico.view.PlacarActivity;

import java.text.Normalizer;

public class Auxiliar {
    public static Jogo jogo;

    public static Jogada criaUmaJogada(int opcao, Context context) {
        Jogada jogada = new Jogada();
        jogada.setOpcao(opcao, context);
        jogo.adicionarJogo(jogada, context);
        salvarPontos(context);
        return jogada;
    }

    public static void abrirMainActivity(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    public static void abrirPlacarActivity(Context context) {
        Intent intent = new Intent(context, PlacarActivity.class);
        context.startActivity(intent);
    }

    public static void fecharApp(Activity context) {
        context.finishAffinity();
    }

    public static void zerarJogo(Context context) {
        jogo.zerarJogo();
        salvarPontos(context);
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    public static boolean onOptionsItemSelected(Activity context, MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_novo) {
            abrirMainActivity(context);
            return true;
        } else if (id == R.id.menu_placar) {
            abrirPlacarActivity(context);
            return true;
        } else if (id == R.id.menu_zerar) {
            zerarJogo(context);
            return true;
        } else if (id == R.id.menu_encerrar) {
            fecharApp(context);
            return true;
        }
        return false;
    }

    public static void placarRecuperado(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(jogo.getNomeJogador(), 0);
        String placarRec = sharedPreferences.getString("placar", "");
        if (!placarRec.equals("")) {
            String[] placar = placarRec.split(";");
//            Toast.makeText(context, "Jogador: "+placar[0], Toast.LENGTH_SHORT).show();
//            Toast.makeText(context, "Android: "+placar[1], Toast.LENGTH_SHORT).show();
            Auxiliar.jogo.setPontosHistorico(Integer.parseInt(placar[0]), Integer.parseInt(placar[1]));
        }
    }

    public static String limpaString(String str) {
        return Normalizer.normalize(str, Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "").replaceAll(" ", "")
                .toLowerCase();
    }


    public static void salvarPontos(Context context) {
        String placar = Auxiliar.jogo.getPontosUsuario() + ";" + Auxiliar.jogo.getPontosAndroid();
//        Toast.makeText(context, "usuario "+ Auxiliar.jogo.getPontosUsuario(), Toast.LENGTH_SHORT).show();
        SharedPreferences sharedPreferences = context.getSharedPreferences(jogo.getNomeJogador(), 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("placar", placar);
        editor.apply();
//        Toast.makeText(context, "Armazenamento TEste: "+sharedPreferences.getString(jogo.getNomeJogador(), ""), Toast.LENGTH_SHORT).show();
    }
}
