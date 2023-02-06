package testImportCSV;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class OutPutPhoto {
	private static final String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static final String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=JavaImportPhoto;trustServerCertificate=true";
	private static final String USER = "sa";
	private static final String PASSWORD = "Passw0rd!";
	private static final String SQL = "SELECT * FROM ImportPhoto WHERE FileID = ? ";
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("請輸入代號");
		String answer = scanner.nextLine();
		System.out.println("您輸入的代號為:"+answer);
		Connection conn = null;
		
		String inFile = answer;
		try {
			
			conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, answer);
			
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				String Filename = rs.getString("Filename");
				String outFile = "C:\\Users\\Student\\Desktop\\"+Filename+".jpg";
				FileOutputStream fos = new FileOutputStream(outFile);
				Blob b = rs.getBlob("FileContent");
				byte[] data = b.getBytes(1, (int)b.length());
				fos.write(data);
				fos.close();
				System.out.println("File output is successful!");
			} 
			rs.close();
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
