/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raisemeup.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import raisemeup.model.beans.User;

/**
 *
 * @author lekogabor
 */
public class DAO {
    
    Map<Integer, User> users = new HashMap<Integer, User>();
    
    private static final String dbfile = "C:/db/raise.db";
    
    private static final String SQL_addUser = 
		"insert into User (email, username, password) values " +
		"(?,?,?)";
    private static final String SQL_queryUser = "select * from User where username = ?";
    private static final String SQL_listUsers = "select * from User";
    //insert into User (email, name, username, password) values ("geabei25@gmail.com","Leko Gabor", "gale", "123" );
    
    public DAO() throws ClassNotFoundException{
		Class.forName("org.sqlite.JDBC");
	}
    
    public boolean addUser(User u) throws SQLException{
		
                System.out.println("jdbc:sqlite:"+dbfile);
		if(checkIfUserExists(u))
			return false;

		Connection conn = null;
		PreparedStatement pst = null;
		try {
                        
			conn = DriverManager.getConnection("jdbc:sqlite:"+dbfile);
			pst = conn.prepareStatement(SQL_addUser);
			int index = 1;
			pst.setString(index++, u.getEmail());
                        pst.setString(index++, u.getUsername());
			pst.setString(index++, u.getPassword());
			
			pst.executeUpdate();
		} finally {
			try {
				if(pst != null)
					pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if(conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return true;
	}
    
    public Map<Integer, User> getUser() throws SQLException{
		Connection conn = null;
		Statement st = null;
		//Toroljuk a memoriabol a customereket (azert tartjuk bennt, mert lehetnek kesobb olyan muveletek, melyekhez nem kell frissiteni)
		users.clear();
		
		try {
			//Az adatb�zis kapcsolatunkat a DriverManager seg�ts�g�vel hozzuk l�tre
			//Megadjuk hogy a jdbc milyen driveren keresztul milyen f�jlt keressen
			conn = DriverManager.getConnection("jdbc:sqlite:"+dbfile);
			
			//a kapcsolat(conn) objektumtol kerunk egy egyszeru (nem parameterezheto) utasitast
			st = conn.createStatement();
			//Az utasitas objektumon keresztul inditunk egy queryt(SQL_listCustomers)
			//Az eredmenyeket egy ResultSet objektumban kapjuk vissza
			ResultSet rs = st.executeQuery(SQL_listUsers);
			//Bejarjuk a visszakapott ResultSet-et (ami a customereket fogja tartalmazni)
			while(rs.next()){
				//Uj Customert hozunk letre
				User u = new User();
				//A customer nevet a resultSet aktualis soraban talalhato name rekordra allitjuk be
				u.setEmail(rs.getString("email"));
				//A customer korat a resultSet aktualis soraban talalhato age rekordra allitjuk be
				u.setUsername(rs.getString("username"));
				u.setPassword(rs.getString("password"));
				
				users.put(rs.getInt("id"), u);
			}
		} finally {
			try {
				if(st != null)
					st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if(conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return users;
	}
    
    private boolean checkIfUserExists(User u) throws SQLException {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection("jdbc:sqlite:"+dbfile);
			
			pst = conn.prepareStatement(SQL_queryUser);
			int index = 1;
			pst.setString(index++, u.getUsername());
			rs = pst.executeQuery();
			
			if (rs.next())
				return true;
			return false;			
		} finally {
			try {
				if(pst != null)
					pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if(rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if(conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}	
}
