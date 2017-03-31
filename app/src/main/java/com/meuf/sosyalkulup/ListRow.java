package com.meuf.sosyalkulup;



public class ListRow {
    private String kulup_name;
    private int profil_pic_id;

    public ListRow(String kulup_name, int profil_pic_id){
        this.kulup_name = kulup_name;
        this.profil_pic_id = profil_pic_id;
    }

    public String getKulup_name() {
        return kulup_name;
    }

    public void setKulup_name(String kulup_name) {
        this.kulup_name = kulup_name;
    }

    public int getProfil_pic_id() {
        return profil_pic_id;
    }

    public void setProfil_pic_id(int profil_pic_id) {
        this.profil_pic_id = profil_pic_id;
    }
}
