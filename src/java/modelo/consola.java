/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author degana
 */
public class consola {

    private String[] columNames = {"Id", "Valor", "Variación", "Fecha"};

    public consola() {
        super();
    }

    public TableModel MostrarEmp(String indicador) throws MalformedURLException, IOException {
        DefaultTableModel tablemodel = new DefaultTableModel();

        System.out.print("Indicador: " + indicador);

        URL url = new URL("http://mindicador.cl/api/" + indicador);

        InputStream is = url.openStream();
        JsonReader rdr = Json.createReader(is);
        JsonObject obj = rdr.readObject();


        int producto = obj.getJsonArray("serie").size();
        
        String [][] data2 = new String [producto][4];

        for (int i = 0; i < obj.getJsonArray("serie").size(); i++) {
            System.out.println("Entre al loop. Valor: "+ obj.getJsonArray("serie").getJsonObject(i).get("valor"));
            double variacion;

            data2[i][0] =  (""+(i + 1));
            data2[i][1] = (obj.getJsonArray("serie").getJsonObject(i).get("valor")).toString();
            if (i >= 1) {
                variacion = Double.parseDouble((obj.getJsonArray("serie").getJsonObject(i).get("valor")).toString())- Double.parseDouble((obj.getJsonArray("serie").getJsonObject(i-1).get("valor")).toString());
                data2[i][2] = (""+variacion);
            } else {
                data2[i][2] = "N/A";
            }
            data2[i][3] = obj.getJsonArray("serie").getJsonObject(i).get("fecha").toString();
        }
        tablemodel.setDataVector(data2, columNames);

        return tablemodel;
    }

}
