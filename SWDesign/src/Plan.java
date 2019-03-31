import java.io.*;
import java.util.*;

public class Plan {
	private int price, max_age, message;
	private String name;
	private double data;
	private int calling;
	private String detail_message, detail_calling, detail_data, detail_etc1, detail_etc2;
	private String telecom;
	
	ArrayList<Customer> CustomerList = new ArrayList<Customer>();
	ArrayList<Rating> RatingList = new ArrayList<Rating>(3);
	
	Admin a = new Admin();
	
	public Plan() {

	}

	public void AddPlanList(Plan p) {
		String str2 = new String();

		str2 = p.getName() + 't' + p.getMax_age() + 't' + p.getData() + 't' + p.getCalling() + 't' + p.getPrice() + 't'
				+ p.getDetail_data() + 't' + p.getDetail_calling() + 't' + p.getDetail_message() + 't' + p.getTelecom()
				+ 't' + p.getDetail_etc1() + 't' + p.getDetail_etc2() + p.getMessage();

		try {
			FileWriter filewriter = new FileWriter("plan.txt", true);

			BufferedWriter out = new BufferedWriter(filewriter);
			out.newLine();
			out.append(str2);
			out.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/*
	 * public Plan(int price, String name, double data, int calling, int
	 * message, String telecom) { this.price = price; this.name = name;
	 * this.data = data; this.calling = calling; this.message = message;
	 * this.telecom = telecom; }
	 */

	public Plan(String name, int max_age, double data, int calling, int price, String detail_data,
			String detail_calling, String detail_message, String telecom, String detail_etc1, String detail_etc2,
			int message) {
		this.price = price;
		this.max_age = max_age;
		this.message = message;
		this.name = name;
		this.data = data;
		this.calling = calling;
		this.detail_message = detail_message;
		this.detail_calling = detail_calling;
		this.detail_data = detail_data;
		this.detail_etc1 = detail_etc1;
		this.detail_etc2 = detail_etc2;
		this.telecom = telecom;
	}

	public void simple_print() {

		System.out.println("��Ż� : " + telecom);
		System.out.println("�̸� : " + name);
		System.out.println("���� : " + price + "��");
		System.out.println();
	}

	public void detail() {

		System.out.println("��Ż� : " + telecom);
		System.out.println("�̸� : " + name);
		System.out.println("���� : " + price + "��");
		System.out.println("������ �� : " + detail_data);
		System.out.println("��ȭ�ð� : " + detail_calling);
		System.out.println("�޼��� �� : " + detail_message);
		System.out.println("Ư�̻��� : " + detail_etc1 + " " + detail_etc2);
		System.out.println();
	}

	public void recommand(String[] manu, String[] data, String[] call, String[] msg, Customer user,
			ArrayList<Plan> PlanList) {
		// Rating rating;
		Plan[] recommanded_plans;
		
		Rating r = new Rating();
		r.PlanRating(manu, data, call, msg, user, PlanList);
		recommanded_plans = new Plan[3];

		// recommanded_plans = rating.PlanRating(c);
		System.out.println("��õ ����� 3��");
		for (int i = 0; i < 3; i++) {
			System.out.println(i + "��");
			PrintPlans(recommanded_plans[i]);
		}
	}

	public static void PrintPlans(Plan a) {
		System.out.println("����� �̸� : " + a.name);
		System.out.println("����� ���� : " + a.price);
		System.out.println("��Ż� : " + a.telecom);
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getData() {
		return data;
	}

	public void setData(double data) {
		this.data = data;
	}

	public int getCalling() {
		return calling;
	}

	public void setCalling(int calling) {
		this.calling = calling;
	}

	public int getMessage() {
		return message;
	}

	public void setMessage(int message) {
		this.message = message;
	}

	public String getTelecom() {
		return telecom;
	}

	public void setTelecom(String telecom) {
		this.telecom = telecom;
	}

	public int getMax_age() {
		return max_age;
	}

	public void setMax_age(int max_age) {
		this.max_age = max_age;
	}

	public String getDetail_message() {
		return detail_message;
	}

	public void setDetail_message(String detail_message) {
		this.detail_message = detail_message;
	}

	public String getDetail_calling() {
		return detail_calling;
	}

	public void setDetail_calling(String detail_calling) {
		this.detail_calling = detail_calling;
	}

	public String getDetail_data() {
		return detail_data;
	}

	public void setDetail_data(String detail_data) {
		this.detail_data = detail_data;
	}

	public String getDetail_etc1() {
		return detail_etc1;
	}

	public void setDetail_etc1(String detail_etc1) {
		this.detail_etc1 = detail_etc1;
	}

	public String getDetail_etc2() {
		return detail_etc2;
	}

	public void setDetail_etc2(String detail_etc2) {
		this.detail_etc2 = detail_etc2;
	}
}