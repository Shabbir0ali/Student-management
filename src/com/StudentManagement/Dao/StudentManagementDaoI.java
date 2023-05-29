package com.StudentManagement.Dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;

import com.StudentManagement.bean.StudentManagementBean;

public interface StudentManagementDaoI {
	
	public void register(BufferedReader br, StudentManagementBean student) throws NumberFormatException, IOException, SQLException;
	public void delete(BufferedReader br, StudentManagementBean student) throws SQLException;
	public void update(BufferedReader br, StudentManagementBean student) throws SQLException, NumberFormatException, IOException;
	public void seeData(BufferedReader br, StudentManagementBean student) throws NumberFormatException, IOException, SQLException;
	public boolean studentLogin(BufferedReader br, StudentManagementBean student) throws SQLException, IOException;
	public boolean adminLogin(BufferedReader br, StudentManagementBean student, int id, String password) throws SQLException;
	public void adminSeeAllData(BufferedReader br, StudentManagementBean student) throws NumberFormatException, IOException, SQLException;
	public void adminDelete(BufferedReader br, StudentManagementBean student) throws NumberFormatException, IOException, SQLException;
}