package testImportCSV;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class DeleteData {
		private static final String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		private static final String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=JavaimportCSV;trustServerCertificate=true";
		private static final String USER = "sa";
		private static final String PASSWORD = "Passw0rd!";
		private static final String SQL = "DELETE FROM MarrageList WHERE \"股票代號\" like ? ";
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("請輸入刪除股票代號");
		String answer = scanner.nextLine();
		System.out.println("您輸入的股票代號為:"+answer);
		Connection conn = null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			
			pstmt.setString(1,"%"+answer+"%");
//			pstmt.setString(2,answer);
//			pstmt.setString(3,answer);
//			pstmt.setString(4,answer);
		
			pstmt.executeUpdate();//真正執行SQL指令
			
//		while (rs.next()) {
//				String ps =  new String();
//				String ps = String.format("股票代號:%s,股票名稱:%s,收盤價:%s,月平均價:%s%n ", rs.getString("股票代號"),rs.getString("股票名稱"),rs.getString("收盤價"),rs.getString("月平均價"));
//			System.out.print("\"股票代號\" = " + rs.getString("股票代號"));
//			System.out.println(", \"股票名稱\" = " + rs.getString("股票名稱"));
//			System.out.println(", \"收盤價\" = " + rs.getString("收盤價"));
//				System.out.println(", \"月平均價\" = " + rs.getString("月平均價"));
//			}
//		rs.close();
			pstmt.close();
		
			System.out.println("刪除成功");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
	}
	}
}
