package kreel.service;

import java.util.regex.*;
import kreel.model.User;
import kreel.util.DBConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class RegisterService {

	private User usr = new User();

	public void addUser(String fname, String lname, String email, String pw, String cpw, String tel, String dob,
			String houseNo, String street, String city, String district, String bank, String accountNo) {

		usr.setFirstName(fname);
		usr.setLastName(lname);
		usr.setEmail(email);
		usr.setPassword(pw);
		usr.setConfirmPassword(cpw);
		usr.setTelephone(tel);
		usr.setDateOfBirth(dob);
		usr.setHouseNo(houseNo);
		usr.setStreet(street);
		usr.setCity(city);
		usr.setDistrict(district);
		usr.setBank(bank);
		usr.setAccountNo(accountNo);

	}

	public String validateRegister() {
		
		if (!Pattern.matches("^[a-zA-Z]+$", usr.getFirstName()))
			return "First Name should only contain letters";
		else if (!Pattern.matches("^[a-zA-Z]+$", usr.getLastName()))
			return "Last Name should only contain letters";
		else if (!Pattern.matches("^[a-z0-9._]+@[a-z]+\\.[a-z]+$", usr.getEmail()))
			return "Please enter a vali e-mail address";
		else if (!Pattern.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", usr.getPassword()))
			return "Please enter a valid password";
		else if (!usr.getConfirmPassword().equals(usr.getPassword()))
			return "Confirm Password should match Password";
		else if (!Pattern.matches("^\\d{4}-\\d{2}-\\d{2}$", usr.getDateOfBirth()))
			return "Please Select the Date of Birth";
		else if (!Pattern.matches("^[0-9]{10}$", usr.getTelephone()))
			return "Please enter a valid telephone number";
		else if (!Pattern.matches("^.+$", usr.getHouseNo()))
			return "Please enter a valid Postal Number";
		else if (!Pattern.matches("^.+$", usr.getStreet()))
			return "Please enter a valid Street";
		else if (!Pattern.matches("^.+$", usr.getCity()))
			return "Please enter a valid City";
		else if (!Pattern.matches("^.+$", usr.getDistrict()))
			return "Please enter a valid District";
		else
			return "Registration Successful";

	}

	public void saveUser() {

		Connection connection;
		PreparedStatement preparedStatement;

		try {
			
			connection = DBConnect.getDBConnection();
			
			preparedStatement = connection.prepareStatement("INSERT INTO user VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			preparedStatement.setString(1, usr.getEmail());
			preparedStatement.setString(2, usr.getFirstName());
			preparedStatement.setString(3, usr.getLastName());
			preparedStatement.setString(4, usr.getTelephone());
			preparedStatement.setString(5, usr.getPassword());
			preparedStatement.setString(6, usr.getDateOfBirth());
			preparedStatement.setString(7, usr.getHouseNo());
			preparedStatement.setString(8, usr.getStreet());
			preparedStatement.setString(9, usr.getCity());
			preparedStatement.setString(10, usr.getDistrict());
			preparedStatement.setString(11, usr.getBank());
			preparedStatement.setString(12, usr.getAccountNo());
			preparedStatement.execute();
			preparedStatement.close();
			connection.close();
			
		} catch (Exception e) {
			
			System.out.println(e.getMessage());
			
		}

	}

}
