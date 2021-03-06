package com.example.semestro_projektas;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionClass {
    String ip = "";
    String classs = "net.sourceforge.jtds.jdbc.Driver";
    String db = "";
    String un = "";
    String password = "";

    @SuppressLint("NewApi")
    public Connection CONN() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection conn = null;
        String ConnURL = null;
        try {
            Class.forName(classs);
            ConnURL = "jdbc:jtds:sqlserver://" + ip + ";"
                    + "databaseName=" + db + ";user=" + un + ";password="
                    + password + ";";
            conn = DriverManager.getConnection(ConnURL);
        } catch (SQLException se) {
            Log.e("ERRO", se.getMessage());
            System.out.println("pirmas");
        } catch (ClassNotFoundException e) {
            Log.e("ERRO", e.getMessage());
            System.out.println("pirma2s");
        } catch (Exception e) {
            Log.e("ERRO", e.getMessage());
            System.out.println("pirm3as");
        }
        return conn;
    }
}
