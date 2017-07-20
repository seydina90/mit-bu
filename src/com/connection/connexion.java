

package com.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;

 
public class connexion {
     

  
	// URL de connection
	String url = "jdbc:mysql://127.0.0.1:3306/javabooks";
	// Nom du user
	private String user = "root";
	// Mot de passe du user

	private String passwd = "passer";
	// Objet Connection

	private static Connection connect = null;

	// Constructeur privee

	private connexion() {
		try {
			connect = DriverManager.getConnection(url, user, passwd);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static Connection getInstance() {
		if (connect == null) {
			new connexion();
		}
		return connect;
	}


    
}

/*
package com.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class connexion 
    {
        public static void main(String[] args)
        {
            try
            {
                Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver");

                String userName = "root";
                String password = "passer@1";
                String url = "jdbc:microsoft:sqlserver://localhost:1433"+";databaseName=javabooks";
                Connection con = DriverManager.getConnection(url, userName, password);
                Statement s1 = con.createStatement();
                ResultSet rs = s1.executeQuery("SELECT TOP 1 * FROM HumanResources.Employee");
                String[] result = new String[20];
                if(rs!=null){
                    while (rs.next()){
                        for(int i = 0; i <result.length ;i++)
                        {
                            for(int j = 0; j <result.length;j++)
                            {
                                result[j]=rs.getString(i);
                            System.out.println(result[j]);
                        }
                        }
                    }
                }

                //String result = new result[20];

            } catch (Exception e)
            {
                e.printStackTrace();
            }
    }


}

    
*/