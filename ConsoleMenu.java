package testImportCSV;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class ConsoleMenu {
	
	public static void main(String[] args) throws MalformedURLException, SQLException {
		Connection conn = null;
		int userSelected;
		do {userSelected = MenuData();
		switch(userSelected){
		case 1:
			System.out.println("選擇1-Import_CSV");
			ImportCSV.main(null);
			break;
		case 2:
			System.out.println("選擇2-SelectCSV_Data");
				TestScaner.main(null);
			break;
		case 3:
			System.out.println("選擇3-DeleteData");
			DeleteData.main(null);
			break;
		case 4:
			System.out.println("選擇4-Import_Photo");
			ImportPhoto.main(null);
			break;
			
		case 5:
			System.out.println("選擇5-Output_Photo");
			OutPutPhoto.main(null);
			break;
		default:
			System.out.println("沒有這麼多功能啦!!!,重選!!! ");
			break;
			}
		}
			while(userSelected >5);
	}
	public static int MenuData() {
		
		int selection;
		Scanner sc = new Scanner(System.in);
		System.out.println("Select your option:");
		System.out.printf("1-Import_CSV%n");
		System.out.printf("2-SelectCSV_Data%n");
		System.out.printf("3-DeleteData%n");
		System.out.printf("4-Import_Photo%n");
		System.out.printf("5-Output_Photo%n");
	
		System.out.println("Your selected option is:");
		selection = sc.nextInt();
		return selection;
	}
}
