package com.sherry.demorest;
import java.util.*;
import java.sql.*;

public class AlienRepository {

//	static List<Alien> aliens;   //list is static to preserve data in session 
	Connection con=null;
	
	public AlienRepository() {
		String url= "jdbc:mysql://localhost:3306/restdb";
		String user="root";
		String password= "admin";
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			con= DriverManager.getConnection(url, user, password);
		}catch(Exception e)
		{
			System.out.println("=="+e+"==");
		}
	}
	
	
	public List<Alien> getAliens()
	{
		String sql= "select * from alien";
		List<Alien> aliens= new ArrayList<Alien>();
		try {
			Statement st= con.createStatement();
			ResultSet rs= st.executeQuery(sql);
			while(rs.next())
			{
				Alien a= new Alien();
				a.setId(rs.getInt(1));
				a.setName(rs.getString(2));
				a.setPoints(rs.getInt(3));
				
				aliens.add(a);
			}
		}
		catch(Exception e)
		{
			System.out.println("=="+e+"==");
		}
		return aliens;
	}
	
	public Alien getAlien(int id)
	{
		String sql= "select * from alien where id="+id;
		Alien a= new Alien();
		try 
		{
			Statement st= con.createStatement();
			ResultSet rs= st.executeQuery(sql);
			if(rs.next())
			{
				a.setId(rs.getInt(1));
				a.setName(rs.getString(2));
				a.setPoints(rs.getInt(3));

			}
		}
		catch(Exception e)
		{
			System.out.println("=="+e+"==");
		}
		return a;
		
	}

	public void create(Alien a1) {
		String sql= "insert into alien values(?,?,?)";
		List<Alien> aliens= new ArrayList<Alien>();
		try 
		{
			PreparedStatement st= con.prepareStatement(sql);
			st.setInt(1, a1.getId());
			st.setString(2, a1.getName());
			st.setInt(3,a1.getPoints());
			st.executeUpdate();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		System.out.println(a1.getName()+ " added" );
	}
	
	public void update(Alien a1) {
		String sql= "update alien set name=?, points=? where id=?";
		List<Alien> aliens= new ArrayList<Alien>();
		try 
		{
			PreparedStatement st= con.prepareStatement(sql);
			st.setString(1, a1.getName());
			st.setInt(2,a1.getPoints());
			st.setInt(3, a1.getId());
			
			st.executeUpdate();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		System.out.println(a1.getName()+ "   updated" );
	}
}
