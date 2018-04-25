/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hbo5.it.www.dataaccess;

import hbo5.it.www.beans.Luchthaven;
import hbo5.it.www.beans.Luchtvaartmaatschappij;
import hbo5.it.www.beans.Vliegtuig;
import hbo5.it.www.beans.Vlucht;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author andre
 */
public class DAVlucht {

    private final String url, login, password;

    public DAVlucht(String url, String login, String password, String driver)
            throws ClassNotFoundException {
        Class.forName(driver);
        this.url = url;
        this.login = login;
        this.password = password;
    }

    public Vlucht getVlucht() throws SQLException {
        Vlucht vlucht = null;
        try (
                Connection connection = DriverManager.getConnection(url, login, password);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM vlucht");) {
            if (resultSet.next()) {
                vlucht = new Vlucht();
                vlucht.setId(resultSet.getInt("id"));
                vlucht.setCode(resultSet.getString("code"));
                vlucht.setVertrektijd(resultSet.getDate("vertrektijd"));
                vlucht.setAankomsttijd(resultSet.getDate("aankomsttijd"));
                vlucht.setVliegtuig_id(resultSet.getInt("vliegtuig_id"));
                vlucht.setVertrekluchthaven_id(resultSet.getInt("vertrekluchthaven_id"));
                vlucht.setAankomstluchthaven_id(resultSet.getInt("aankomstluchthaven_id"));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vlucht;
    }

    public ArrayList<Vlucht> getBinnenkomendeVluchten(int luchthavenId) {
        ArrayList<Vlucht> vluchten = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(url, login, password);
                PreparedStatement statement = connection.prepareStatement("select * from vlucht  join  vliegtuig on VLIEGTUIG_ID=VLIEGTUIG.ID join LUCHTHAVEN on AANKOMSTLUCHTHAVEN_ID=LUCHTHAVEN.ID join LUCHTHAVEN on VERTREKLUCHTHAVEN_ID=LUCHTHAVEN.ID join LUCHTVAARTMAATSCHAPPIJ on VLIEGTUIG.LUCHTVAARTMAATSCHAPPIJ_ID=LUCHTVAARTMAATSCHAPPIJ.ID where AANKOMSTLUCHTHAVEN_ID=?");) {
            statement.setInt(1, luchthavenId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Vlucht vlucht = new Vlucht();

                vlucht.setId(resultSet.getInt("id"));
                vlucht.setCode(resultSet.getString("code"));
                vlucht.setVertrektijd(resultSet.getDate("vertrektijd"));
                vlucht.setAankomsttijd(resultSet.getDate("aankomsttijd"));
                vlucht.setVliegtuig_id(resultSet.getInt("vliegtuig_id"));

                Vliegtuig vliegtuig = new Vliegtuig();
                vliegtuig.setId(resultSet.getInt("vliegtuig_id"));
                vliegtuig.setLuchtvaartmaatschappij_id(resultSet.getInt("luchtvaartmaatschappij_id"));
                Luchtvaartmaatschappij luchtvmp = new Luchtvaartmaatschappij();
                luchtvmp.setId(resultSet.getInt("luchtvaartmaatschappij_id"));
                luchtvmp.setLuchtvaartnaam(resultSet.getString("luchtvaartnaam"));
                vliegtuig.setLuchtvaartmaatschappij(luchtvmp);
                vliegtuig.setVliegtuigtype_id(resultSet.getInt("vliegtuigtype_id"));
                vlucht.setVliegtuig(vliegtuig);

                vlucht.setVertrekluchthaven_id(resultSet.getInt("vertrekluchthaven_id"));

                Luchthaven vertrekhaven = new Luchthaven();
                vertrekhaven.setId(resultSet.getInt(11));
                vertrekhaven.setLuchthavennaam(resultSet.getString(12));
                vertrekhaven.setStad(resultSet.getString(13));
                vertrekhaven.setLand_id(resultSet.getInt(14));
                vlucht.setVertrekluchthaven(vertrekhaven);

                vlucht.setAankomstluchthaven_id(resultSet.getInt("aankomstluchthaven_id"));

                Luchthaven aankomsthaven = new Luchthaven();
                aankomsthaven.setId(resultSet.getInt(15));
                aankomsthaven.setLuchthavennaam(resultSet.getString(16));
                aankomsthaven.setStad(resultSet.getString(17));
                aankomsthaven.setLand_id(resultSet.getInt(18));
                vlucht.setAankomstluchthaven(aankomsthaven);

                vluchten.add(vlucht);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return vluchten;
    }

    public ArrayList<Vlucht> getVertrekkendeVluchten(int luchthavenId) {
        ArrayList<Vlucht> vluchten = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(url, login, password);
                PreparedStatement statement = connection.prepareStatement("select * from vlucht  join  vliegtuig on VLIEGTUIG_ID=VLIEGTUIG.ID join LUCHTHAVEN on AANKOMSTLUCHTHAVEN_ID=LUCHTHAVEN.ID join LUCHTHAVEN on VERTREKLUCHTHAVEN_ID=LUCHTHAVEN.ID join LUCHTVAARTMAATSCHAPPIJ on VLIEGTUIG.LUCHTVAARTMAATSCHAPPIJ_ID=LUCHTVAARTMAATSCHAPPIJ.ID where VertrekLUCHTHAVEN_ID=?");) {
            statement.setInt(1, luchthavenId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Vlucht vlucht = new Vlucht();

               vlucht.setId(resultSet.getInt("id"));
                vlucht.setCode(resultSet.getString("code"));
                vlucht.setVertrektijd(resultSet.getDate("vertrektijd"));
                vlucht.setAankomsttijd(resultSet.getDate("aankomsttijd"));
                vlucht.setVliegtuig_id(resultSet.getInt("vliegtuig_id"));

                Vliegtuig vliegtuig = new Vliegtuig();
                vliegtuig.setId(resultSet.getInt("vliegtuig_id"));
                vliegtuig.setLuchtvaartmaatschappij_id(resultSet.getInt("luchtvaartmaatschappij_id"));
                Luchtvaartmaatschappij luchtvmp = new Luchtvaartmaatschappij();
                luchtvmp.setId(resultSet.getInt("luchtvaartmaatschappij_id"));
                luchtvmp.setLuchtvaartnaam(resultSet.getString("luchtvaartnaam"));
                vliegtuig.setLuchtvaartmaatschappij(luchtvmp);
                vliegtuig.setVliegtuigtype_id(resultSet.getInt("vliegtuigtype_id"));
                vlucht.setVliegtuig(vliegtuig);

                vlucht.setVertrekluchthaven_id(resultSet.getInt("vertrekluchthaven_id"));

                Luchthaven vertrekhaven = new Luchthaven();
                vertrekhaven.setId(resultSet.getInt(11));
                vertrekhaven.setLuchthavennaam(resultSet.getString(12));
                vertrekhaven.setStad(resultSet.getString(13));
                vertrekhaven.setLand_id(resultSet.getInt(14));
                vlucht.setVertrekluchthaven(vertrekhaven);

                vlucht.setAankomstluchthaven_id(resultSet.getInt("aankomstluchthaven_id"));

                Luchthaven aankomsthaven = new Luchthaven();
                aankomsthaven.setId(resultSet.getInt(15));
                aankomsthaven.setLuchthavennaam(resultSet.getString(16));
                aankomsthaven.setStad(resultSet.getString(17));
                aankomsthaven.setLand_id(resultSet.getInt(18));
                vlucht.setAankomstluchthaven(aankomsthaven);

                vluchten.add(vlucht);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return vluchten;
    }

    public ArrayList<Vlucht> GetFilteredVluchten(String code, LocalDate datum, String bestemming, String luchtvaartmaatschappij) {
        ArrayList<Vlucht> vluchten = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(url, login, password);
                PreparedStatement statement = connection.prepareStatement("select * from vlucht join vliegtuig "
                        + "on VLIEGTUIG_ID=VLIEGTUIG.ID join LUCHTHAVEN on AANKOMSTLUCHTHAVEN_ID=LUCHTHAVEN.ID "
                        + "join LUCHTHAVEN on VERTREKLUCHTHAVEN_ID=LUCHTHAVEN.ID join LUCHTVAARTMAATSCHAPPIJ "
                        + "on VLIEGTUIG.LUCHTVAARTMAATSCHAPPIJ_ID=LUCHTVAARTMAATSCHAPPIJ.ID where code like ? "
                        + "and (VERTREKTIJD >= ? "
                        + "AND VERTREKTIJD < ? "
                        + "or AANKOMSTTIJD >= ? "
                        + "AND AANKOMSTTIJD < ?) "
                        + "and LUCHTHAVEN.STAD like ? "
                        + "and LUCHTVAARTMAATSCHAPPIJ.LUCHTVAARTNAAM like ?");) {

            statement.setString(1, "%" + code + "%");
            
            statement.setDate(2, Date.valueOf(datum));
            statement.setDate(3, Date.valueOf(datum.plusDays(1)));
            statement.setDate(4, Date.valueOf(datum));
            statement.setDate(5, Date.valueOf(datum.plusDays(1)));

            statement.setString(6, "%" + bestemming+ "%");
            statement.setString(7, "%" + luchtvaartmaatschappij+ "%");

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Vlucht vlucht = new Vlucht();

                vlucht.setId(resultSet.getInt("id"));
                vlucht.setCode(resultSet.getString("code"));
                vlucht.setVertrektijd(resultSet.getDate("vertrektijd"));
                vlucht.setAankomsttijd(resultSet.getDate("aankomsttijd"));
                vlucht.setVliegtuig_id(resultSet.getInt("vliegtuig_id"));

                Vliegtuig vliegtuig = new Vliegtuig();
                vliegtuig.setId(resultSet.getInt("vliegtuig_id"));
                vliegtuig.setLuchtvaartmaatschappij_id(resultSet.getInt("luchtvaartmaatschappij_id"));
                Luchtvaartmaatschappij luchtvmp = new Luchtvaartmaatschappij();
                luchtvmp.setId(resultSet.getInt("luchtvaartmaatschappij_id"));
                luchtvmp.setLuchtvaartnaam(resultSet.getString("luchtvaartnaam"));
                vliegtuig.setLuchtvaartmaatschappij(luchtvmp);
                vliegtuig.setVliegtuigtype_id(resultSet.getInt("vliegtuigtype_id"));
                vlucht.setVliegtuig(vliegtuig);

                vlucht.setVertrekluchthaven_id(resultSet.getInt("vertrekluchthaven_id"));

                Luchthaven vertrekhaven = new Luchthaven();
                vertrekhaven.setId(resultSet.getInt(11));
                vertrekhaven.setLuchthavennaam(resultSet.getString(12));
                vertrekhaven.setStad(resultSet.getString(13));
                vertrekhaven.setLand_id(resultSet.getInt(14));
                vlucht.setVertrekluchthaven(vertrekhaven);

                vlucht.setAankomstluchthaven_id(resultSet.getInt("aankomstluchthaven_id"));

                Luchthaven aankomsthaven = new Luchthaven();
                aankomsthaven.setId(resultSet.getInt(15));
                aankomsthaven.setLuchthavennaam(resultSet.getString(16));
                aankomsthaven.setStad(resultSet.getString(17));
                aankomsthaven.setLand_id(resultSet.getInt(18));
                vlucht.setAankomstluchthaven(aankomsthaven);

                vluchten.add(vlucht);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return vluchten;
    }
}
