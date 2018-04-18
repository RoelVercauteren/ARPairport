/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hbo5.it.www.beans;

import java.time.LocalDate;

/**
 *
 * @author roel_
 */
public class Persoon {
    private int id;
    private String voornaam;
    private String familienaam;
    private String straat;
    private String huisnr;
    private String postcode;
    private String woonplaats;
    private String land;
    private LocalDate geboortedatum;
    private String Login;
    private String paswoord;
    private char soort;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    public String getFamilienaam() {
        return familienaam;
    }

    public void setFamilienaam(String familienaam) {
        this.familienaam = familienaam;
    }

    public String getStraat() {
        return straat;
    }

    public void setStraat(String straat) {
        this.straat = straat;
    }

    public String getHuisnr() {
        return huisnr;
    }

    public void setHuisnr(String huisnr) {
        this.huisnr = huisnr;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getWoonplaats() {
        return woonplaats;
    }

    public void setWoonplaats(String woonplaats) {
        this.woonplaats = woonplaats;
    }

    public String getLand() {
        return land;
    }

    public void setLand(String land) {
        this.land = land;
    }

    public LocalDate getGeboortedatum() {
        return geboortedatum;
    }

    public void setGeboortedatum(LocalDate geboortedatum) {
        this.geboortedatum = geboortedatum;
    }

    public String getLogin() {
        return Login;
    }

    public void setLogin(String Login) {
        this.Login = Login;
    }

    public String getPaswoord() {
        return paswoord;
    }

    public void setPaswoord(String paswoord) {
        this.paswoord = paswoord;
    }

    public char getSoort() {
        return soort;
    }

    public void setSoort(char soort) {
        this.soort = soort;
    }

    public Persoon() {
    }

    public Persoon(int id, String voornaam, String familienaam, String straat, String huisnr, String postcode, String woonplaats, String land, LocalDate geboortedatum, String Login, String paswoord, char soort) {
        this.id = id;
        this.voornaam = voornaam;
        this.familienaam = familienaam;
        this.straat = straat;
        this.huisnr = huisnr;
        this.postcode = postcode;
        this.woonplaats = woonplaats;
        this.land = land;
        this.geboortedatum = geboortedatum;
        this.Login = Login;
        this.paswoord = paswoord;
        this.soort = soort;
    }
    
    
}
