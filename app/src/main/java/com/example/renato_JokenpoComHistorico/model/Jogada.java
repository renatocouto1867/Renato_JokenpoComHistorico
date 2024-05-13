package com.example.renato_JokenpoComHistorico.model;

import android.content.Context;

import com.example.renato_JokenpoComHistorico.viewModel.Auxiliar;

import java.util.Random;

public class Jogada {
    private String nome;
    private int ponto=0;
    private int opcao;
    private int sorteio;
    private boolean impate;

    public String getNome() {
        return nome;
    }

    public int getPonto() {
        return ponto;
    }

    private void pontua() {

        if (chegaResultado()) {
            ponto = 1;
        } else ponto = 0;
    }

    private boolean chegaResultado() {
        int pedra = 0;
        int papel = 1;
        int tesoura = 2;

        switch (opcao) {
            case 0:
                return sorteio == tesoura;
            case 1:
                return sorteio == pedra;
            case 2:
                return sorteio == papel;
            default:
                return false;
        }
    }

    private void chegaImpate() {
        impate = opcao == sorteio;
    }

    public int getOpcao() {
        return opcao;
    }

    public boolean isImpate() {
        return impate;
    }

    public void setOpcao(int opcao, Context context) {
        if (opcao == 0) {
            this.opcao = opcao;
        } else if (opcao == 1) {
            this.opcao = opcao;
        } else if (opcao == 2) {
            this.opcao = opcao;
        }
        this.nome = Auxiliar.jogo.getNomeJogador();
        sorteiaResultado();
        chegaImpate();
        pontua();
        Auxiliar.salvarPontos(context);
    }

    public int getSorteio() {
        return sorteio;
    }

    private void sorteiaResultado() {
        this.sorteio = new Random().nextInt(3);
    }

    @Override
    public String toString() {
        return "Jogada{" +
                "ponto=" + ponto +
                ", opcao=" + opcao +
                ", sorteio=" + sorteio +
                '}';
    }
}

