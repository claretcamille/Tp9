/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DaoExtend;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.sql.DataSource;
import simplejdbc.DAO;

/**
 *
 * @author camilleclaret
 */
public class Dao extends DAO {
    
    /**
     * Constructeur de la class
     * @param dataSource 
    */
    public Dao(DataSource dataSource) {
        super(dataSource);
    }
    
    /**
     * Liste des etat de la table customer
     * @return result une arrayList
     * @throws SQLException 
     */
    public ArrayList<String> listePays() throws SQLException{
        ArrayList<String> result = new ArrayList<String>();
        String sql = "SELECT DISTINCT STATE FROM CUSTOMER ORDER BY STATE";
        try(
                Connection c = this.myDataSource.getConnection();
                Statement stmt = c.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                ){
            while(rs.next()){
                result.add(rs.getString("STATE"));
            }
            
        }
        return result;
        
    }
    
    
    
}
