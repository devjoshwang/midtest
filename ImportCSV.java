package testImportCSV;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
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

	public static void main(String[] args) {
		File file = new File("C:\\Users\\Student\\Desktop\\期中專題\\STOCK_DAY_AVG_ALL_20230204.csv");
		Connection conn = null;
		try {
			Class.forName(JDBC_DRIVER);
		} catch (ClassNotFoundException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}

		try (FileInputStream fis = new FileInputStream(file);
				InputStreamReader isr = new InputStreamReader(fis);
				BufferedReader br = new BufferedReader(isr);) {
			String line;
			while ((line = br.readLine()) != null) {
				System.out.println(line);
				line = line.replace("\"", "");// 把資料中"變成空格,才能轉檔
				String[] columns = line.split(",");
//						System.out.println(columns);

				try {
					conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
					PreparedStatement pstmt = conn.prepareStatement(UPDATE);
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
					int count = pstmt.executeUpdate();
					System.out.println("成功匯入");
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
	}
}