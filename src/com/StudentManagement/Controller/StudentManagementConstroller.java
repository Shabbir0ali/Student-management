package com.StudentManagement.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

import com.StudentManagement.Dao.StudentManagementDaoI;
import com.StudentManagement.Dao.StudentManagementDaoImp;
import com.StudentManagement.bean.StudentManagementBean;

public class StudentManagementConstroller {

	public static void main(String[] args) {
		int login = 0;
		StudentManagementBean student = new StudentManagementBean();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StudentManagementDaoI daoImp = new StudentManagementDaoImp();
		try {
			System.out.println("Are you a student our Admin");
			String line = br.readLine();
			if (line.equalsIgnoreCase("student")) {
				while (true) {
					System.out.println("Enter 1 to Register:\t");
					System.out.println("Enter 2 to Login:\t");
					System.out.println("Enter 3 to exit:\t");
					login = Integer.parseInt(br.readLine());
					if (login == 1) {
						daoImp.register(br, student);
					} else if (login == 2) {
						boolean flag = daoImp.studentLogin(br, student);
						while (flag) {
							System.out.println("Enter 1 to See All data :\t");
							System.out.println("Enter 2 to Update data :\t");
							System.out.println("Enter 3 to delete :\t");
							System.out.println("Enter 4 to exit :\t");
							byte b = Byte.parseByte(br.readLine());

							switch (b) {
							case 1: {
								daoImp.seeData(br, student);
							}
								break;
							case 2: {
								daoImp.update(br, student);
							}
								break;
							case 3: {
								System.out.println("please confirm you want to delete your data");
								String con= br.readLine();
								if(con.equalsIgnoreCase("confirm")) {
								daoImp.delete(br, student);
							}
								}
								break;
							case 4: {
								flag = false;
							}
							}
						}
					} else if (login == 3) {
						break;
					}
				}
			} else if (line.equalsIgnoreCase("admin")) {

				System.out.println("Enter your ID");
				int id = Integer.parseInt(br.readLine());

				System.out.println("Enter your Password");
				String password = br.readLine();
				boolean flag = daoImp.adminLogin(br,student,id,password);

				while (flag) {
					System.out.println("Enter 1 to See All data :\t");
					System.out.println("Enter 2 to delete student :\t");
					System.out.println("Enter 3 to exit :\t");
					id = Integer.parseInt(br.readLine());
					if(id==1) {
						daoImp.adminSeeAllData(br,student);
					}
					else if (id==2) {
					daoImp.adminDelete(br,student);	
					}
					else if (id==3) {
						break;
					}
					else
					{
						System.out.println("Enter valid number");
					}
				}

			} else {
				System.out.println("Enter only given option above");
				StudentManagementConstroller.main(args);
			}

		} catch (IOException | NumberFormatException | SQLException e) {
			e.printStackTrace();
		}
	}
}