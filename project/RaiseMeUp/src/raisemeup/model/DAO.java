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
import raisemeup.model.beans.Pet;
import raisemeup.model.beans.User;

/**
 *
 * @author lekogabor
 */
public class DAO {
    
    Map<Integer, User> users = new HashMap<Integer, User>();
    Map<Integer, Pet> pets = new HashMap<Integer, Pet>();
    
    private static final String dbfile = "C:/db/raise.db";
    
    private static final String SQL_addUser = 
		"insert into User (email, username, password) values " +
		"(?,?,?)";
    private static final String SQL_queryUser = "select * from User where username = ?";
    private static final String SQL_delUser = "delete from User where username = ?";
    private static final String SQL_listUsers = "select * from User";
    
    private static final String SQL_addPet = 
		"insert into Pet (type, variant, name, hunger, energy, fun, hygiene, age, money, image, userid) values " +
		"(?,?,?,?,?,?,?,?,?,?,?)";
    private static final String SQL_queryPet = "select * from Pet, User where user.username = ? and pet.name = ?;";
    private static final String SQL_listPets = "select * from Pet";
    private static final String SQL_delPet = "delete from Pet where userid = ? and name = ?;";
    
    //insert into Pet (type, variant, name, hunger, energy, fun, hygiene, age, image, userid) values ("cica","szep","juci",100,100,100,100,0,"",1);
    
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
		users.clear();
		
		try {
			conn = DriverManager.getConnection("jdbc:sqlite:"+dbfile);
			
			st = conn.createStatement();
			ResultSet rs = st.executeQuery(SQL_listUsers);
			while(rs.next()){
				User u = new User();
                                u.setId(rs.getInt("userid"));
				u.setEmail(rs.getString("email"));
				u.setUsername(rs.getString("username"));
				u.setPassword(rs.getString("password"));
				
				users.put(rs.getInt("userid"), u);
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
    
    public boolean checkIfUserExists(User u) throws SQLException {
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

    public boolean delUser(User u) throws SQLException{
		
                System.out.println("jdbc:sqlite:"+dbfile);

		Connection conn = null;
		PreparedStatement pst = null;
		try {
                        
			conn = DriverManager.getConnection("jdbc:sqlite:"+dbfile);
			pst = conn.prepareStatement(SQL_delUser);
			int index = 1;
			pst.setString(index++, u.getUsername());
                        
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


    public boolean addPet(User u, Pet p) throws SQLException{
		
                System.out.println("jdbc:sqlite:"+dbfile);
		if(checkIfPetExists(u, p))
			return false;

		Connection conn = null;
		PreparedStatement pst = null;
		try {
                        
			conn = DriverManager.getConnection("jdbc:sqlite:"+dbfile);
			pst = conn.prepareStatement(SQL_addPet);
			int index = 1;
			pst.setString(index++, p.getType());
                        pst.setString(index++, p.getVariant());
                        pst.setString(index++, p.getName());
                        pst.setInt(index++, p.getHunger());
                        pst.setInt(index++, p.getEnergy());
                        pst.setInt(index++, p.getFun());
                        pst.setInt(index++, p.getHygiene());
                        pst.setInt(index++, p.getAge());
                        pst.setInt(index++, p.getMoney());
                        pst.setString(index++, p.getImage());
                        pst.setInt(index++, p.getOwner());
			
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
    
    public Map<Integer, Pet> getPet() throws SQLException{
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
			ResultSet rs = st.executeQuery(SQL_listPets);
			//Bejarjuk a visszakapott ResultSet-et (ami a customereket fogja tartalmazni)
			while(rs.next()){
				//Uj Customert hozunk letre
				Pet p = new Pet();
				//A customer nevet a resultSet aktualis soraban talalhato name rekordra allitjuk be
				p.setType(rs.getString("type"));
                                p.setVariant(rs.getString("variant"));
                                p.setName(rs.getString("name"));
                                p.setHunger(rs.getInt("hunger"));
                                p.setEnergy(rs.getInt("energy"));
                                p.setFun(rs.getInt("fun"));
                                p.setHygiene(rs.getInt("hygiene"));
                                p.setAge(rs.getInt("age"));
                                p.setMoney(rs.getInt("money"));
                                p.setImage(rs.getString("image"));
                                p.setOwner(rs.getInt("userid"));
				
				pets.put(rs.getInt("id"), p);
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
		
		return pets;
	}
    
    public boolean checkIfPetExists(User u, Pet p) throws SQLException {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection("jdbc:sqlite:"+dbfile);
			
			pst = conn.prepareStatement(SQL_queryPet);
			int index = 1;
			pst.setString(index++, u.getUsername());
                        pst.setString(index++, p.getName());
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

    public boolean delPet(Pet p) throws SQLException{
		
                System.out.println("jdbc:sqlite:"+dbfile);

		Connection conn = null;
		PreparedStatement pst = null;
		try {
                        
			conn = DriverManager.getConnection("jdbc:sqlite:"+dbfile);
			pst = conn.prepareStatement(SQL_delPet);
			int index = 1;
			pst.setInt(index++, p.getOwner());
                        pst.setString(index++, p.getName());
                        
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

    
}
