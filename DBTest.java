import java.util.*;
import java.io.*;
import java.sql.*;

public class DBTest {

	
			private Connection con = null;
			private Statement statement = null;
			public ResultSet result = null;
			public String sql = null;
			
			public DBTest(){
				
				try {
				// ����̹��� �ε�
				Class.forName("com.mysql.jdbc.Driver"); 

				System.out.println("after forName");
				
				// ���� 
				String url = "jdbc:mysql://localhost:3306/recDB?serverTimezone=Asia/Seoul";

				con = DriverManager.getConnection(url, "root", "1234");

				System.out.println("DB ������ ����Ǿ����ϴ�.");
				
				// ������ �����ϱ� ���� Statement ��ü ���� 
				statement = con.createStatement();
			
				// ���� ������ LOAD query : "LOAD DATA INFILE '/Applications/data/planInfo.csv' INTO TABLE planInfo FIELDS TERMINATED BY ',' IGNORE 2 LINES;"
				
			} 
			catch(ClassNotFoundException e){
	            System.out.println("Driver loading fail!");
	        }
	        catch(SQLException e){
	            System.out.println("error: " + e);
	        }
				
		}
				

			// �޴��� ���� �޾ƿ���  
			public void getPhoneDB(String data) {
				// ������ �ۼ� 
				sql = "SELECT * from phoneInfo WHERE phoneName = '" + data + "'";
				// ������ ���� 
				result = statement.executeQuery(sql);	
				// �˻� ��� ��� 
				while(result.next()) {
					String phoneName = result.getString(1);
					String company = result.getString(2);
					int phonePrice = Integer.parseInt(result.getString(3));
					double screenSize = Double.parseDouble(result.getString(4));
					String os = result.getString(5);
					int capacity = Integer.parseInt(result.getString(6));
					int RAM = Integer.parseInt(result.getString(7));
					int frontCamera = Integer.parseInt(result.getString(8));
					int rearCamera = Integer.parseInt(result.getString(9));
					int weight = Integer.parseInt(result.getString(10));
					String phoneSize = result.getString(11);
					int betteryCapacity = Integer.parseInt(result.getString(12));
					double speed = Double.parseDouble(result.getString(13));
					String resolution = result.getString(14);
					int releaseYear = Integer.parseInt(result.getString(15));
					String color = result.getString(16);
					String link = result.getString(17);
					int recCount = Integer.parseInt(result.getString(18));
					
					System.out.println("�𵨸�: " + phoneName 
							+ "\n������: " + company 
							+ "\n����: " + phonePrice + "��"
							+ "\nȭ��ũ��: " + screenSize + "inch" 
							+ "\n�ü��: " + os 
							+ "\n�뷮: " + capacity + "GB" 
							+ "\n��: " + RAM + "GB"
							+ "\n����ī�޶�: " + frontCamera + "�� ȭ��"
							+ "\n�ĸ�ī�޶�: " + rearCamera + "�� ȭ��" 
							+ "\n����: " + weight + "g" 
							+ "\nũ��: " + phoneSize + "mm" 
							+ "\n���͸� �뷮: " + betteryCapacity + "mAh" 
							+ "\n�ӵ�: " + speed + "Ghz" 
							+ "\nȭ���ػ�: " + resolution 
							+ "\n��ó⵵: " + releaseYear + "��" 
							+ "\n����: " + color 
							+ "\n��ũ: " + link 
							+ "\n��õ��: " + recCount + "ȸ\n\n");
				}
				closeDB();
			
			} 	
			
			// ����� ���� �޾ƿ��� 
			public void getPlanDB(String data) {
				// ������ �ۼ� 
				String sql = "SELECT * from planInfo WHERE operator = '" + data + "'";
				// ������ ���� 
				result = statement.executeQuery(sql);
				// �˻� ��� ��� 
				while(result.next()) {
					String operator = result.getString(1);
					String planName = result.getString(2);
					String mobile = result.getString(3);
					int planPrice = Integer.parseInt(result.getString(4));
					String data = result.getString(4);
					String videoCall = result.getString(5);
					String call = result.getString(6);
					String message = result.getString(7);
					String option = result.getString(8);
					
					System.out.println("����� �̸�: " + planName 
							+ "\n��Ż�: " + operator 
							+ "\n���: " + mobile
							+ "\n����(��): " + planPrice + "��" 
							+ "\n������: " + data 
							+ "\n������ȭ: " + videoCall
							+ "\n��ȭ: " + call
							+ "\n�޼���: " + message 
							+ "\nƯ�̻���: " + option + "\n\n");
				}
				closeDB();
			
			}
			
			// �޴��� ���� �Է��ϱ� 
			public void setPhoneDB() {
				// ������ �ۼ� 
				sql = "INSERT INTO phoneInfo(phoneName, company, phonePrice, screenSize, os, capacity, RAM, frontCamera, rearCamera, weight, phoneSize, betteryCapacity, speed, resolution, releaseYear, color, link) 
							VALUES ('iphone', 'apple', '20000', '4.8', 'os', '512', '4', '700', '1200', '208', '112233', '3000', '2.5', 'resolution', '2018', 'gold', 'www.naver.com')";
				// ������ ���� 
				result = statement.executeQuery(sql);	
				
				closeDB();		
			}
			
			// ����� ���� �Է��ϱ� 
			public void setPlanDB() {
				// ������ �ۼ�
				sql = "INSERT INTO plansql(operator, planName, mobile, planPrice, data, videoCall, call, message, option) 
							VALUES ('KT', 'ȭ���ÿ����', 'LTE', '45000', '������', '������', '������', '������', '���л� ���' )";
				// ������ ���� 
				result = statement.executeQuery(sql);
				
				closeDB();
			}
			
			// �� ���� �Է��ϱ�
			public void setCustomerDB(String[] input) {
				// ������ �ۼ�
				sql = "INSERT INTO customerInfo(ID, password, name, age, phone_number, email_address, sex)
						VALUES ('" + input[0] + "', '" + input[1] + "', '" + input[2] + "', '" + input[3] + "', '" + 
						input[4] + "', '" + input[5] + "', '" + input[6] + "')";
				// ������ ���� 
				result = statement.executeQuery(sql);	
				
				closeDB();					
			}
			
						
				
			// DB ���� �����ϱ� 	
			public void closeDB() {
			
			try{
	                if( con != null && !con.isClosed()){
	                    con.close();
	                }
	            }
	            catch( SQLException e){
	                e.printStackTrace();
	            }
	            System.out.println("DB ������ ����Ǿ����ϴ�.");
			}

}
