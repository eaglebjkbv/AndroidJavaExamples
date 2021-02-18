package com.example.customlistview2;

public class Person {
    int id;
    String ad;
    String soyad;
    String resim;

    public Person(int id, String ad, String soyad, String resim) {
        this.id = id;
        this.ad = ad;
        this.soyad = soyad;
        this.resim = resim;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getSoyad() {
        return soyad;
    }

    public void setSoyad(String soyad) {
        this.soyad = soyad;
    }

    public String getResim() {
        return resim;
    }

    public void setResim(String resim) {
        this.resim = resim;
    }
}
