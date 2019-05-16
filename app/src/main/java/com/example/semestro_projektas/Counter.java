package com.example.semestro_projektas;

public class Counter {
    private String Busena;
    private String Tipas;

    public Counter() {
    }

    public Counter( String busena, String tipas) {

        Busena = busena;
        Tipas = tipas;
    }

    public String getBusena() {
        return Busena;
    }

    public void setBusena(String busena) {
        Busena = busena;
    }

    public String getTipas() {
        return Tipas;
    }

    public void setTipas(String tipas) {
        Tipas = tipas;
    }
}
