package testImportCSV;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

public class ImportCSV {
	private static final String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static final String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=JavaimportCSV;trustServerCertificate=true";
	private static final String USER = "sa";
	private static final String PASSWORD = "Passw0rd!";
	private static final String UPDATE = "INSERT INTO MarrageList VALUES(?,?,?,?)";

	public static void main(String[] args) throws MalformedURLException, SQLException {
//		File file = new File("C:\\Users\\Student\\Desktop\\期中專題\\STOCK_DAY_AVG_ALL_20230204.csv");
		String urlString = "https://www.twse.com.tw/exchangeReport/STOCK_DAY_AVG_ALL?response=open_data";
		Connection conn = null;
		try {
			Class.forName(JDBC_DRIVER);
		} catch (ClassNotFoundException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		
//		FileInputStream fis = new FileInputStream(file);
//		InputStreamReader isr = new InputStreamReader(fis);
//		BufferedReader br = new BufferedReader(isr);) {
		
		URL url = new URL(urlString);
												//openStream() 用於讀取網頁,FileInputStream()用於讀取電腦內檔案
		try (BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()))){
			
			String line;
			conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
			PreparedStatement pstmt = conn.prepareStatement(UPDATE);
			while ((line = br.readLine()) != null) {
				System.out.println(line);
				line = line.replace("\"", "");// 把資料中"變成空格,才能轉檔
				String[] columns = line.split(",");
//						System.out.println(columns);

				try {
					pstmt.setString(1, columns[0]);
					pstmt.setString(2, columns[1]);
					try {
						pstmt.setDouble(3, Double.parseDouble(columns[2]));
					} catch (Exception e) {
						pstmt.setNull(3, Types.DECIMAL);// 資料中沒有數字的轉為Null
					}
					try {
						pstmt.setDouble(4, Double.parseDouble(columns[3]));
					} catch (Exception e) {
						pstmt.setNull(4, Types.DECIMAL);
					}
//					pstmt.addBatch();//再研究一下怎麼用
					int count = pstmt.executeUpdate();
//					System.out.println("成功匯入");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			pstmt.close();
			conn.close();
			
		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
	}
}