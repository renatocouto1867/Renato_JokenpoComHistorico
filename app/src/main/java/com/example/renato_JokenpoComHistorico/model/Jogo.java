package com.example.renato_JokenpoComHistorico.model;

import android.content.Context;

import com.example.renato_JokenpoComHistorico.R;
import com.example.renato_JokenpoComHistorico.viewModel.Auxiliar;

public class Jogo {
    private int pontosUsuario = 0;
    private int pontosAndroid = 0;
    private String nomeJogador;

    public Jogo(String nomeJogador) {
        this.nomeJogador = nomeJogador;
    }

    public String getNomeJogador() {
        return nomeJogador;
    }

    public void setNomeJogador(String nomeJogador) {
        this.nomeJogador = nomeJogador;
    }

    public int getPontosUsuario() {
        return pontosUsuario;
    }

    public int getPontosAndroid() {
        return pontosAndroid;
    }


    public void adicionarJogo(Jogada jogada, Context context) {//foi definido que cada rodada vale 1 pontos
        if (!jogada.isImpate()) {
            if (jogada.getPonto() == 1) {
                pontosUsuario++;
            } else pontosAndroid++;
        }
        Auxiliar.salvarPontos(context);

    }

    public void zerarJogo() {
        pontosAndroid = 0;
        pontosUsuario = 0;
    }

    public String getPrimeiroColocado(Context context) {
        if (pontosUsuario <= pontosAndroid)
            return context.getString(R.string.andoid) + pontosAndroid;
        else return context.getString(R.string.usuario) + pontosUsuario;
    }

    public String getSegundoColocado(Context context) {
        if (pontosUsuario > pontosAndroid)
            return context.getString(R.string.andoid) + pontosAndroid;
        else return context.getString(R.string.usuario) + pontosUsuario;
    }

    public void setPontosHistorico(int jogador, int android) {
        pontosAndroid = android;
        pontosUsuario = jogador;
    }

}
