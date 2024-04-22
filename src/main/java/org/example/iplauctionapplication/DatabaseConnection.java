package org.example.iplauctionapplication;

import java.sql.Connection;
import java.sql.DriverManager;
public class DatabaseConnection {
    public Connection databaseLink;
    public Connection getConnection(){
        String databaseName = "IPLAuction";
        String databaseUser= "root";
        String databasePassword="Kesh123@Keka";
        String url="jdbc:mysql://localhost/"+databaseName;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink= DriverManager.getConnection(url,databaseUser, databasePassword);
            int n=databaseLink.getTransactionIsolation();
            System.out.println(n);
        } catch (Exception e){
            e.printStackTrace();
        }
        return databaseLink;
    }
}