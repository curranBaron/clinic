import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.*;
import java.util.Scanner;

public class Question2Updated{

		public static void main(String[] args){
		try{
			//create database connection
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/eduvosClinicApp_db", "Username", "Password");
			Statement statement = connection.createStatement();
			
			//Create table if it does not already exist
			
			String createTableQuery ="Create table if not exists patients(id INT AUTO_INCREMENT PRIMARY KEY";
		
			PreparedStatement createTableStatement = connection.prepareStatement(createTableQuery);
			createTableStatement.executeUpdate();
			
			Scanner myObj = new Scanner(System.in);
			String patientStudNumber = "";
			
			while (patientStudNumber != "0"){
				System.out.println("Welcome to Eduvos Clinic App");
				System.out.println("Enter Patient name:");
				String patientName = myObj.nextLine();
				System.out.println("Enter Patient surname:");
				String patientSurname = myObj.nextLine();
				System.out.println("Enter ID Number:");
				String idNumber = myObj.nextLine();
				System.out.println("Enter Cell Number:");
				String patientCellNumber = myObj.nextLine();
				System.out.println("Enter Student Number:");
				patientStudNumber = myObj.nextLine();
				
				
				//validation for inputs("0")
				
				if (!patientStudNumber.equals("0") &&! isNumeric(idNumber) && isNumeric(patientCellNumber)){
						System.out.println("Error: ID Number and Cell Number must be in numeric characters");
						continue;
					}
					
				//Saving the patients details into the database
				
				String insertQuery = "INSERT INTO patients(name, surname, idNumber, cellNumber, studentNumber) VALUES (?, ?, ?, ?, ?)";
				PreparedStatement prepareStatement = connection.prepareStatement(insertQuery);
					prepareStatement.setString(1, patientName);
					prepareStatement.setString(2, patientSurname);
					prepareStatement.setString(3, idNumber);
					prepareStatement.setString(4, patientCellNumber);
					prepareStatement.setString(5, patientStudNumber);
					prepareStatement.executeUpdate();
					
					System.out.println("Patient details have been added to the database");
					
				if (patientStudNumber.equals("0")) {
					System.out.println("Thank you for using Eduvos Clinic App");
					
					break;
					
				}
			}
				//Closing the database connection
				
				connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			private static boolean isNumeric(String str){
				return str.matches("\\d+");
			
		}
		
}