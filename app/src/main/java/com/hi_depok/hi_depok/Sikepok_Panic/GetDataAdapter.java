package com.hi_depok.hi_depok.Sikepok_Panic;

/**
 * Created by SONY-VAIO on 3/15/2017.
 */

public class GetDataAdapter {

    String Id;
    String name;
    int foto;
    String deskripsi;
    String alamat;
    String no_telp;


    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getId() {

        return Id;
    }

    public void setId(String Id1) {

        this.Id = Id1;
    }


    public int getFoto() {

        return foto;
    }

    public void setFoto(int foto) {

        this.foto = foto;
    }

    public String getDeskripsi() {

        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {

        this.deskripsi = deskripsi;
    }
    public String getAlamat() {

        return alamat;
    }

    public void setAlamat(String alamat) {

        this.alamat = alamat;
    }

    public String getNoTelp(){
        return no_telp;
    }
    public void setNoTelp(String no_telp){
        this.no_telp = no_telp;
    }

}
