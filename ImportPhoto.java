package testImportCSV;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ImportPhoto {
	private static final String DB_URL = 
			"jdbc:sqlserver://localhost:1433;databaseName=JavaImportPhoto;trustServerCertificate=true";
	private static final String USER = "sa";
	private static final String PASSWORD = "Passw0rd!";
	private static final String SQL =
			"INSERT INTO ImportPhoto VALUES(?,?,?,?)";
	
	public static void main(String[] args) {
		Connection conn = null;
		String inFile = "C:\\Users\\Student\\Desktop\\期中專題\\wtf.jpg";
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);			
			
			FileInputStream fis = new FileInputStream(inFile);
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, 1);
			pstmt.setString(2, "wtf");
			pstmt.setBinaryStream(3, fis);
			pstmt.setString(4, inFile);
			pstmt.executeUpdate();
//			int count = pstmt.executeUpdate();
//			if (count > 0)
				System.out.println("Insert blob is successful!");	

			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				try {
					conn.close();
				} catch(SQLException e) {
					e.printStackTrace();
				}
		}
	}
} 
