package com.example.semestro_projektas;

public class WorkshopInformation {
    private String Adresas;
    private String Aukstai;
    private String Cecho_vad;
    private String Darb_Sk;
    private String Pavadinimas;

    public WorkshopInformation() {
    }

    public WorkshopInformation(String adresas, String aukstai, String cecho_vad, String darb_Sk, String pavadinimas) {
        Adresas = adresas;
        Aukstai = aukstai;
        Cecho_vad = cecho_vad;
        Darb_Sk = darb_Sk;
        Pavadinimas = pavadinimas;
    }

    public String getAdresas() {
        return Adresas;
    }

    public void setAdresas(String adresas) {
        Adresas = adresas;
    }

    public String getAukstai() {
        return Aukstai;
    }

    public void setAukstai(String aukstai) {
        Aukstai = aukstai;
    }

    public String getCecho_vad() {
        return Cecho_vad;
    }

    public void setCecho_vad(String cecho_vad) {
        Cecho_vad = cecho_vad;
    }

    public String getDarb_Sk() {
        return Darb_Sk;
    }

    public void setDarb_Sk(String darb_Sk) {
        Darb_Sk = darb_Sk;
    }

    public String getPavadinimas() {
        return Pavadinimas;
    }

    public void setPavadinimas(String pavadinimas) {
        Pavadinimas = pavadinimas;
    }
}
