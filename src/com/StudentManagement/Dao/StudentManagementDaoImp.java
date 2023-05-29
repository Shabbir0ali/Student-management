package com.StudentManagement.Dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.StudentManagement.bean.StudentManagementBean;
import com.connectionJDBC.ConnectionProvider;

public class StudentManagementDaoImp implements StudentManagementDaoI {
	int id;

	@Override
	public void register(BufferedReader br, StudentManagementBean student)
			throws NumberFormatException, IOException, SQLException {
		System.out.println("Enter a your id :\t");
		student.setId(Integer.parseInt(br.readLine()));
		System.out.println("Enter a your name :\t");
		student.setName(br.readLine());
		System.out.println("Enter a your course :\t");
		student.setCourse(br.readLine());
		System.out.println("Enter a your city :\t");
		student.setCity(br.readLine());
		System.out.println("Enter your Password :\t");
		student.setPassword(br.readLine());
		Connection con = ConnectionProvider.getConnection();
		PreparedStatement stmt = con.prepareStatement("insert into student values(?,?,?,?,?)");
		stmt.setInt(1, student.getId());
		stmt.setString(2, student.getName());
		stmt.setString(3, student.getCourse());
		stmt.setString(4, student.getCity());
		stmt.setString(5, student.getPassword());
		int i = stmt.executeUpdate();
		if (i > 0) {
			System.out.println("data inserted");
		} else {
			System.out.println("something went wrong");
		}
	}

	@Override
	public void seeData(BufferedReader br, StudentManagementBean student)
			throws NumberFormatException, IOException, SQLException {

		Connection con = ConnectionProvider.getConnection();
		System.out.println("Enter 1 to see all data : ");
		System.out.println("Enter 2 to see your data :");
		int matchId1 = Integer.parseInt(br.readLine());
		if (matchId1 == 1) {
			Statement stmt1 = con.createStatement();
			ResultSet set2 = stmt1.executeQuery("select * from student");
			while (set2.next()) {
				student.setName(set2.getString("name"));
				student.setCourse(set2.getString("Course"));
				student.setCity(set2.getString("City"));
				System.err.println("Student Name :" + student.getName() + "\t Student Course :" + student.getCourse()
						+ "\t Student City :" + student.getCity());
			}
		} else if (matchId1 == 2) {
			Statement stmt2 = con.createStatement();
			ResultSet set2 = stmt2.executeQuery("select * from student where id=" + id);
			while (set2.next()) {
				student.setId(set2.getInt("id"));
				student.setName(set2.getString("name"));
				student.setCourse(set2.getString("Course"));
				student.setCity(set2.getString("City"));
				System.err.println("Student Id :" + student.getId() + "\t Student Name :" + student.getName()
						+ "\t Student Course :" + student.getCourse() + "\t Student City :" + student.getCity());
			}
		} else {
			System.out.println("Enter only number are given above");
		}
	}

	@Override
	public void delete(BufferedReader br, StudentManagementBean student) throws SQLException {
		Connection con = ConnectionProvider.getConnection();
		PreparedStatement stmt = con.prepareStatement("delete from student where id=?");
		stmt.setInt(1, id);
		int i = stmt.executeUpdate();
		if (i > 0) {
			System.out.println("Deleted");
		} else {
			System.out.println("Something went wrong");
		}
	}

	@Override
	public void update(BufferedReader br, StudentManagementBean student)
			throws SQLException, NumberFormatException, IOException {
		Connection con = ConnectionProvider.getConnection();
		Statement stmt = con.createStatement();

		System.out.println("Enter 1 to update name:\t");
		System.out.println("Enter 2 to update course:\t");
		System.out.println("Enter 3 to update city:\t");
		System.out.println("Enter 4 to update password:\t");
		int enter = Integer.parseInt(br.readLine());
		switch (enter) {
		case 1: {
			System.out.println("Enter new name:\t");
			student.setName(br.readLine());
			stmt.executeUpdate("update student set name='" + student.getName() + "' where id = " + id);
		}
			break;
		case 2: {
			System.out.println("Enter new course:\t");
			student.setCourse(br.readLine());
			stmt.executeUpdate("update student set Course ='" + student.getCourse() + "' where id = " + id);
		}
			break;
		case 3: {
			System.out.println("Enter new city:\t");
			student.setCity(br.readLine());
			stmt.executeUpdate("update student set City ='" + student.getCity() + "' where id = " + id);
		}
			break;
		case 4: {
			System.out.println("Enter new password:\t");
			student.setPassword(br.readLine());
			stmt.executeUpdate("update student set password ='" + student.getPassword() + "' where id = " + id);
		}
			break;
		default: {
			System.out.println("Enter vlid number");
		}
		}
	}

	@Override
	public boolean studentLogin(BufferedReader br, StudentManagementBean student) throws SQLException, IOException {
		boolean flag = false;
		System.out.println("Enter your Id to login:");
		id = Integer.parseInt(br.readLine());
		System.out.println("Enter your Password to login:");
		String password = br.readLine();
		Connection con = ConnectionProvider.getConnection();
		Statement stmt = con.createStatement();
		ResultSet set = stmt.executeQuery("select * from student");
		while (set.next()) {
			student.setId(set.getInt("id"));
			student.setPassword(set.getString("password"));
			if (id == student.getId() && password.equals(student.getPassword())) {
				flag = true;
				System.out.println("login successfully");
			}

		}
		
		//System.out.println("wrong id or password");
		
		return flag;
	}

	@Override
	public boolean adminLogin(BufferedReader br, StudentManagementBean student, int id, String password)
			throws SQLException {
		boolean flag = false;
		Connection con = ConnectionProvider.getConnection();
		Statement stmt = con.createStatement();
		ResultSet set = stmt.executeQuery("select * from admin");
		while (set.next()) {
			int adminId = set.getInt("id");
			String adminPassword = set.getString("password");
			String adminName = set.getString("name");
			if (id == adminId && password.equals(adminPassword)) {
				flag = true;
				System.out.println("Wecome MR " + adminName);
			}
		}
		return flag;
	}

	@Override
	public void adminSeeAllData(BufferedReader br, StudentManagementBean student)
			throws NumberFormatException, IOException, SQLException {
		Connection con = ConnectionProvider.getConnection();
		System.out.println("Enter 1 to see all data : ");
		System.out.println("Enter 2 to see single data :");
		int matchId1 = Integer.parseInt(br.readLine());
		if (matchId1 == 1) {
			Statement stmt1 = con.createStatement();
			ResultSet set2 = stmt1.executeQuery("select * from student");
			while (set2.next()) {
				student.setName(set2.getString("name"));
				student.setCourse(set2.getString("Course"));
				student.setCity(set2.getString("City"));
				student.setId(set2.getInt("id"));
				System.err.println("Student Id :" + student.getId() + "\t Student Name :" + student.getName()
						+ "\t Student Course :" + student.getCourse() + "\t Student City :" + student.getCity());
			}
		} else if (matchId1 == 2) {
			System.out.println("Enter Id you want to see :");
			student.setId(Integer.parseInt(br.readLine()));
			Statement stmt2 = con.createStatement();
			ResultSet set2 = stmt2.executeQuery("select * from student where id=" + student.getId());
			while (set2.next()) {
				student.setId(set2.getInt("id"));
				student.setName(set2.getString("name"));
				student.setCourse(set2.getString("Course"));
				student.setCity(set2.getString("City"));
				System.err.println("Student Id :" + student.getId() + "\t Student Name :" + student.getName()
						+ "\t Student Course :" + student.getCourse() + "\t Student City :" + student.getCity());
			}
		} else {
			System.out.println("Enter only number are given above");
		}
	}

	@Override
	public void adminDelete(BufferedReader br, StudentManagementBean student) throws NumberFormatException, IOException, SQLException {
		Connection con = ConnectionProvider.getConnection();
		System.out.println("Ente id do you want delete :\t");
		id = Integer.parseInt(br.readLine());
		PreparedStatement stmt = con.prepareStatement("delete from student where id = ?");
		stmt.setInt(1,id );
		int i = stmt.executeUpdate();
		if (i > 0) {
			System.out.println("Deleted");
		} else {
			System.out.println("Something went wrong");
		}	
	}
}