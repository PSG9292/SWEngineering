<<<<<<< HEAD:src/Test.java
import java.util.*;
import java.io.*;

public class Test {

	public static void main(String[] args) {
		int k = 0;

		Admin admin;
		Scanner adminfile = null;

		try {
			adminfile = new Scanner(new FileInputStream("admin.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("File open error");
			System.exit(0);
		}

		String admin_id, admin_pw;
		admin_id = adminfile.next();
		admin_pw = adminfile.next();

		admin = new Admin(admin_id, admin_pw);

		ArrayList<Model> ModelList = new ArrayList<Model>();
		String line;
		Scanner ModelIn = null;
		int i;
		try {
			ModelIn = new Scanner(new FileInputStream("model.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("File open error");
			System.exit(0);
		}

		while (ModelIn.hasNext()) {
			line = ModelIn.nextLine();
			String[] split = line.split("\t");
			Model m = new Model(split[0], split[1], Integer.parseInt(split[2]),Double.parseDouble(split[3]), split[4], Integer.parseInt(split[5]), Integer.parseInt(split[6]),
					Integer.parseInt(split[7]), Integer.parseInt(split[8]), Integer.parseInt(split[9]), split[10] , Integer.parseInt(split[11]),split[12],split[13],
					Integer.parseInt(split[14]),split[15],split[16],Integer.parseInt(split[17]));
			
			ModelList.add(m);
		}
		ModelIn.close();

		ArrayList<Customer> customerlist = new ArrayList<Customer>();
		int suc = 0;
		Scanner customerfile = null;
		try {
			customerfile = new Scanner(new FileInputStream("custom.txt"));

		} catch (FileNotFoundException e) {
			System.out.println("File open error");
		}

		while (customerfile.hasNext()) {
			line = customerfile.nextLine();
			String[] split = line.split(" ");
			String temp = null;
			Customer customer = new Customer(split[0], split[1], split[2], split[3], split[4], split[5], split[6]);
			customerlist.add(customer);			
		}

		customerfile.close();

		ArrayList<Plan> PlanList = new ArrayList<Plan>();
		Scanner planfile = null;
		try {
			planfile = new Scanner(new FileInputStream("plan.txt"));

		} catch (FileNotFoundException e) {
			System.out.println("File open error");
		}

		while (planfile.hasNext()) {
			String temp = null;
			line = planfile.nextLine();
			String[] split = line.split("\t");
			Plan p = new Plan(split[0], Integer.parseInt(split[1]), Double.parseDouble(split[2]),
					Integer.parseInt(split[3]), Integer.parseInt(split[4]), split[5], split[6], split[7], split[8],
					split[9], split[10], Integer.parseInt(split[11]));
			PlanList.add(p);
		}
		planfile.close();

		System.out.println("---------- ������� �޴���, ����� ��õ �ý��� ȣ�������Դϴ�  ���ϴ� �޴��� �Է��� �ּ��� ----------");

		int j = 0;

		for (; j == 0;) {
			System.out.println(
					"�α����� ���Ͻø�   \"�α��� \" ��  ȸ�������� ���Ͻø�     \"ȸ������\" ����� �˻��� ���ϸ� \"������˻�\" �� �˻��� ���ϸ� \"�𵨰˻�\"  ��   �Է����ּ��� ���⿡ �������ּ���");
			String str;
			Scanner a = new Scanner(System.in);
			str = a.nextLine();

			if (str.equals("ȸ������")) // customer �� �����ϴ°�
			{
				System.out.println("ȸ������ �޴��� �����ϼ̽��ϴ�");
				System.out.print("ID �� �Է��ϼ���  : ");
				String str2;
				str2 = a.nextLine();
				for (i = 0; i < customerlist.size(); i++) {

					if (str2.equals(customerlist.get(i).getID())) {
						System.out.println("�ߺ��� ���̵� �ֽ��ϴ� . �ٸ� id�� �Է����ּ���");
						i = customerlist.size();
						break;
					} else if (i == customerlist.size() - 1) {

						System.out.println("��� ������ id�Դϴ� .");
						Customer newcustomer = new Customer();
						newcustomer.setID(str2);

						System.out.println("��й�ȣ�� �Է����ּ���.");
						str2 = a.nextLine();
						newcustomer.setpassword(str2);

						System.out.println("�̸��� �Է��� �ּ���");
						str2 = a.nextLine();
						newcustomer.setname(str2);

						System.out.println("���̸� �Է��� �ּ���");
						str2 = a.nextLine();
						newcustomer.setage(str2);

						System.out.println("��ȭ��ȣ�� �Է��� �ּ���");
						str2 = a.nextLine();
						newcustomer.setphone_number(str2);

						System.out.println("�̸��� �ּҸ� �Է��� �ּ���");
						str2 = a.nextLine();
						newcustomer.setemail_address(str2);

						System.out.println("������ �Է��� �ּ��� . ���ڸ� '��' ���ڸ� '��'�� �Է��� �ּ���");
						str2 = a.nextLine();
						newcustomer.setsex(str2);

						System.out.println("������� ��ǰ�� ���� ���並 �ۼ����ּž� ȸ�������� �Ϸ�˴ϴ�.");
						System.out.println("������� ��ǰ���� �Է��� �ּ���(���� ��� '����' �Է�).");
						String using = a.next();
						if (using.equals("����")) {
						} else {
							System.out.println("���並 �Է��� �ּ���.");
							String newreview = a.nextLine();
							ReviewManager reviewManager = new ReviewManager();
							reviewManager.AddReview(using, newreview);
						}

						customerlist.add(newcustomer);
						str2 = newcustomer.getname() + ' ' + newcustomer.getID() + ' ' + newcustomer.getpassword() + ' '
								+ newcustomer.getsex() + ' ' + newcustomer.getage() + ' '
								+ newcustomer.getemail_address() + ' ' + newcustomer.getphone_number();

						try {
							FileWriter filewriter = new FileWriter("custom.txt", true);

							BufferedWriter out = new BufferedWriter(filewriter);
							out.newLine();

							out.append(str2);
							out.close();
							System.out.println("ȸ������ �Ϸ�Ǿ����ϴ� ");
							break;
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

				}

			} else if (str.equals("�α���")) // ������Ʈ�� ���ؼ� ȸ�������� ������ �α��μ���!
			{
				System.out.println("�α��� �޴��� �����ϼ̽��ϴ�");
				System.out.println("���̵� �Է��� �ּ��� ");
				String inputID = new String();
				String inputPW = new String();
				Scanner keyboard = new Scanner(System.in);
				inputID = keyboard.nextLine(); // ���̵��Է¹��� ->�ߺ��˻��� ��й�ȣ�� ��!

				for (k = 0; k < customerlist.size(); k++) {
					if (inputID.equals(customerlist.get(k).getID())) {
						suc = 1;
						System.out.println("��й�ȣ�� �Է��ϼ���");
						inputPW = keyboard.next();
						if (inputPW.equals(customerlist.get(k).getpassword())) {
							suc = 2; // �α��� ����
							break;
						}
					}
				}
				if (inputID.equals(admin_id)) {
					System.out.println("��й�ȣ�� �Է��ϼ���");
					inputPW = keyboard.next();
				}

				if (suc == 0) {
					if (admin.Login(inputID, inputPW)) {
						System.out.println("�����ڷ� �α��� �ϼ̽��ϴ�. ������ �޴��� �̵��մϴ�.");
						suc = 3;
						break;
					} else {
						System.out.println("ID ���� , �α��ο� �����߽��ϴ� . ");

					}
				} else if (suc == 1) {
					System.out.println("password ���� , �α��ο� �����߽��ϴ�.");

				} else if (suc == 2) {
					System.out.println("�α��� ���� .");
					break;
				}

			} else if (str.equals("������˻�")) {
				System.out.println("����� �˻� �޴��� �����ϼ̽��ϴ�");
				j = 3;
				break;
			} else if (str.equals("�𵨰˻�")) {
				System.out.println("�𵨰˻� �޴��� �����ϼ̽��ϴ�");
				j = 2;
				break;
			} else {
				System.out.println("�߸��� �޴��� �����ϼ̽��ϴ� �ٽ� �Է����ּ��� . ���⿡ ������ �ּ���");

			}
		}

		if (suc == 2) {
			System.out.println("���ϴ� �޴��� �������ּ��� . 1.�޴��� ��õ �ޱ� , 2.����� ��õ �ޱ� , 3.��ǰ ��ȸ , 4.ȸ������ ���� , 5.ȸ�� Ż��");
			Scanner in = new Scanner(System.in);
			int menu;

			menu = in.nextInt();
			if (menu == 1) { // �޴��� ��õ�ޱ�
				Scanner keyboard = new Scanner(System.in);

				System.out.println("�޴��� ��õ�ޱ� �޴� �Դϴ�.");
				System.out.println("���ϴ� �����縦 �����ϼ���.");
				System.out.println("1.����  2.�Ｚ  3.LG 4.��Ÿ ");
				String wantmanufacture;
				wantmanufacture = keyboard.nextLine();
				String[] manu_split = wantmanufacture.split(" ");

				System.out.println("���ϴ� ���ݴ븦 �Է��� �ּ���");
				System.out.println(
						"1. 0~10����  2. 10~20����  3. 30~40����  5. 40~50����  6. 50~60����  7. 60~70����  8. 70~80����  9. 80~90����  10. 90~100����  11. 100~110����  12. 110~120����  13. 120~130����  14. 130~140����  15. 140~150����  16. 150���� �̻�");

				String wantprice;
				wantprice = keyboard.nextLine();
				String[] price_split = wantprice.split(" ");

				System.out.println("��� ��� ���� �� ���ϴ� ����� �Է��� �ּ���");
				System.out.println("1. Ȩ��ư �ʿ�  2. ���� ����  3. ��뷮 ���͸�  4. ���� ī�޶�  5. ū ȭ��  6. ��뷮 �޸�  7. ��ȭ�� ������");

				String wantfunction;
				wantfunction = keyboard.nextLine();
				String[] function_split = wantfunction.split(" ");

				customerlist.get(k).recommand_Models(manu_split, price_split, function_split, customerlist.get(k),
						ModelList);

			} else if (menu == 2) {
				Scanner keyboard = new Scanner(System.in);
				System.out.println("����� ��õ�ޱ� �޴� �Դϴ�.");
				System.out.println("���ϴ� ��Ż縦 �����ϼ���.");
				System.out.println("1.SKT  2.KT  3.U+ ");
				String wantmanufacture;
				wantmanufacture = keyboard.nextLine();
				String[] manu_split = wantmanufacture.split(" ");

				System.out.println("������ ��뷮�� �����ϼ���");
				System.out.println(
						"1. 0~1GB  2. 1~2GB  3. 2~3GB  4. 3~4GB  5. 4~5GB  6. 5~6GB  7. 6~7GB  8. 7~8GB  9. 8~9GB  10. 9~10GB  11. 10GB�̻�");
				String wantdata;
				wantdata = keyboard.nextLine();
				String[] data_split = wantdata.split(" ");

				System.out.println("��ȭ ��뷮�� �����ϼ���");
				System.out.println(
						"1. 0~50��  2. 50~100��  3. 100~150��  4. 150~200��  5. 200~250��  6. 250~300��  7. 300~350��  8. 350~400��  9. 400~450��  10. 450~500��  11. 500���̻�");
				String wantcalling;
				wantcalling = keyboard.nextLine();
				String[] calling_split = wantcalling.split(" ");

				System.out.println("���� ��뷮�� �����ϼ���");
				System.out.println(
						"1. 0~50��  2. 50~100��  3. 100~150��  4. 150~200��  5. 200~250��  6. 250~300��  7. 300~350��  8. 350~400��  9. 400~450��  10. 450~500��  11. 500���̻�");
				String wantmessage;
				wantmessage = keyboard.nextLine();
				String[] message_split = wantmessage.split(" ");

				customerlist.get(k).recommand_Plans(manu_split, data_split, calling_split, message_split,customerlist.get(k), PlanList);

			} // ����� ��õ �ޱ�

			else if (menu == 3) {
				String select;
				System.out.println("1.�� ��ȸ, 2.����� ��ȸ");
				select = in.next();
				if (select.equals("����ȸ") || select.equals("�� ��ȸ") || select.equals("1")) {
					view_model(ModelList);
				} else if (select.equals("�������ȸ") || select.equals("����� ��ȸ") || select.equals("2")) {
					view_plan(PlanList);
				}
			} // ��ǰ ��ȸ

			else if (menu == 4) {// ȸ������ ����

				System.out.println("�����ϰ� ���� ������ �������ּ���");
				System.out.println("1.�̸�, 2.PASSWORD, 3.����, 4.����, 5.�����ּ�, 6.����ó");
				int menu2 = in.nextInt();

				if (menu2 == 1) {
					System.out.println("�̸� ������ �����Ͽ����ϴ�. �ٲٽ� �̸��� �Է��� �ּ���");
				} // �̸� ����
				else if (menu2 == 2) {
					System.out.println("��й�ȣ ������ �����Ͽ����ϴ�. �ٲٽ� ��й�ȣ�� �Է��� �ּ���");
				} // ��� ����
				else if (menu2 == 3) {
					System.out.println("���� ������ �����Ͽ����ϴ�. �ٲٽ� ������ �Է��� �ּ���");
				} // ���� ����
				else if (menu2 == 4) {
					System.out.println("���� ������ �����Ͽ����ϴ�. �ٲٽ� ���̸� �Է��� �ּ���");
				} // ���� ����
				else if (menu2 == 5) {
					System.out.println("�����ּ� ������ �����Ͽ����ϴ�. �ٲٽ� �����ּҸ� �Է��� �ּ���");
				} // �ּ� ����
				else if (menu2 == 6) {
					System.out.println("����ó ������ �����Ͽ����ϴ�. �ٲٽ� ����ó�� �Է��� �ּ���");
				} // ����ó ����
				String newstring = in.next();
				Customer temp;
				temp = customerlist.get(k);
				temp.modify(customerlist, newstring, k, menu2);
				System.out.println("�����Ϸ�");

			} else if (menu == 5) {// ȸ��Ż��
				Scanner keyboard = new Scanner(System.in);
				System.out.println("ȸ�� Ż�� ���Ͻø� ��й�ȣ�� �ѹ� �� �Է��� �ֽʽÿ�.");
				String pw;
				Customer Removing;
				Removing = customerlist.get(k);
				pw = keyboard.next();
				if (pw.equals(Removing.getpassword())) {
					Removing.remove_member(customerlist, k);
					System.out.println("Ż��Ϸ�");
				}
			} else {
				System.out.println("��й�ȣ�� Ʋ�Ƚ��ϴ�. ���α׷��� �����մϴ�.");
				System.exit(1);
			}
		} else if (suc == 3) {// ������
			String admin_menu;

			System.out.println("���Ͻô� �޴��� ��ȣ�� �Է����ּ���.");
			System.out.println("1.���߰�, 2.������߰�, 3.�𵨻���, 4.���������");

			Scanner keyboard = new Scanner(System.in);

			admin_menu = keyboard.next();

			if (admin_menu.equals("1")) {
				// �� �߰�
				int price, camera_front, storage, ram, camera_back, weight, battery;
				String name, manufacture, os, led, cpu_name;
				boolean home;
				double size, cpu_rate;

				System.out.println("�� �߰� �޴��Դϴ�.");
				System.out.println("�߰��Ϸ��� ���� ������ �Է��մϴ�.");
				System.out.println("���� : ");
				price = keyboard.nextInt();

				System.out.println("���� ī�޶� ȭ�� : ");
				camera_front = keyboard.nextInt();

				System.out.println("cpu rate : ");
				cpu_rate = keyboard.nextDouble();

				System.out.println("����� �뷮 : ");
				storage = keyboard.nextInt();

				System.out.println("�� ���: ");
				ram = keyboard.nextInt();

				System.out.println("�ĸ� ī�޶� ȭ�� : ");
				camera_back = keyboard.nextInt();

				System.out.println("���� : ");
				weight = keyboard.nextInt();

				System.out.println("���͸� : ");
				battery = keyboard.nextInt();
				keyboard.nextLine();

				System.out.println("�� �̸� : ");
				name = keyboard.nextLine();

				System.out.println("������ : ");
				manufacture = keyboard.nextLine();

				System.out.println("os : ");
				os = keyboard.nextLine();

				System.out.println("led : ");
				led = keyboard.nextLine();

				System.out.println("cpu name : ");
				cpu_name = keyboard.nextLine();

				System.out.println("Ȩ��ư ����(������ 1, ������ 0): ");
				String temp;
				temp = keyboard.next();
				if (temp.equals("1")) {
					home = true;
				} else {
					home = false;
				}

				System.out.println("������ : ");
				size = keyboard.nextDouble();
/*
				Model NewModel = new Model(name, manufacture, cpu_name, size, os, price, storage, ram, camera_front,
						camera_back, weight, battery, cpu_rate, led, home);

				if (admin.AddModel(NewModel, ModelList)) {
					System.out.println("�߰��Ϸ�");
				} else {
					System.out.println("�̹� �����ϴ� ���Դϴ�");
					System.exit(0);
				}
*/
			} else if (admin_menu.equals("2")) {// ����� �߰�
				System.out.println("����� �߰� �޴��Դϴ�.");
				System.out.println("�߰��Ϸ��� ���� ������ �Է��մϴ�.");
				int price, max_age, message;
				String name;
				double data;
				int calling;
				String detail_message, detail_calling, detail_data, detail_etc1, detail_etc2;
				String telecom;

				System.out.println("��Ż� : ");
				telecom = keyboard.next();
				keyboard.nextLine();

				System.out.println("�̸� : ");
				name = keyboard.nextLine();

				System.out.println("���� ����(������  0) : ");
				max_age = keyboard.nextInt();

				System.out.println("��ȭ�� : ");
				calling = keyboard.nextInt();

				System.out.println("���� : ");
				price = keyboard.nextInt();

				System.out.println("������ �� : ");
				data = keyboard.nextDouble();
				keyboard.nextLine();

				System.out.println("������ �������� : ");
				detail_data = keyboard.nextLine();

				System.out.println("��ȭ �������� : ");
				detail_calling = keyboard.nextLine();

				System.out.println("���ڷ� : ");
				message = keyboard.nextInt();
				keyboard.nextLine();

				System.out.println("���� �������� : ");
				detail_message = keyboard.nextLine();

				System.out.println("Ư�̻���1 (������ X): ");
				detail_etc1 = keyboard.nextLine();

				System.out.println("Ư�̻���2 (������ X) : ");
				detail_etc2 = keyboard.nextLine();

				Plan NewPlan = new Plan(name, max_age, data, calling, price, detail_data, detail_calling,
						detail_message, telecom, detail_etc1, detail_etc2, message);

				if (admin.AddPlan(NewPlan, PlanList)) {
					System.out.println("�߰��Ϸ�");
				} else {
					System.out.println("�̹� �����ϴ� ����� �Դϴ�");
					System.exit(0);
				}

			} else if (admin_menu.equals("3")) { // �� ����
				System.out.println("�� ���� �޴��Դϴ�. ������ ���� �̸��� �Է����ּ���");
				String target;
				keyboard.nextLine();
				target = keyboard.nextLine();
				if (admin.RemoveModel(target, ModelList)) {
					System.out.println("���� �Ϸ�");
				} else {
					System.out.println("���� ���� : ���� �� �Դϴ�.");
					System.exit(0);
				}
			} else if (admin_menu.equals("4")) { // ����� ����
				System.out.println("����� ���� �޴��Դϴ�. ������ ������� �̸��� �Է����ּ���");
				String target_name;
				keyboard.nextLine();
				target_name = keyboard.nextLine();
				System.out.println("������ ������� ��Ż縦 �Է��� �ּ���");
				String target_telecom;
				target_telecom = keyboard.nextLine();

				if (admin.RemovePlan(target_telecom, target_name, PlanList)) {
					System.out.println("���� �Ϸ�");
				} else {
					System.out.println("���� ���� : ���� ����� �Դϴ�.");
					System.exit(0);
				}
			}
		}

		if (j == 2) // �� �˻�
		{
			view_model(ModelList);
		} else if (j == 3) // ����� �˻�

		{
			view_plan(PlanList);

		}
	}

	private static void view_model(ArrayList<Model> ModelList) {
		int min = 0, max = 0;// �޾ƿ;���.
		String select;
		String manufacture;
		Customer c = new Customer();
		Scanner in = new Scanner(System.in);

		System.out.println("1.��ü���   2.���ǰ˻�");
		select = in.next();

		if (select.equals("��ü���") || select.equals("1"))
			print_model_all(ModelList);
		else {
			manufacture = print_model_manufacture();
			min = min_model_pricelist();
			max = max_model_pricelist();
			c.search_model(manufacture, min, max); // �������̸�, min ����, max����
		} // ������ ����ϴ� �Լ�
	}

	private static void view_plan(ArrayList<Plan> PlanList) {
		int call_min, call_max, price_min, price_max;
		double data_min, data_max;
		String plan_input, telecom;
		System.out.println("1.��ü��ȸ 2.���� �˻�");
		Customer c = new Customer();
		Scanner in = new Scanner(System.in);
		plan_input = in.next();
		if (plan_input.equals("��ü��ȸ") || plan_input.equals("��ü ��ȸ") || plan_input.equals("1"))
			print_plan_all(PlanList);
		else if (plan_input.equals("���ǰ˻�") || plan_input.equals("���� �˻�") || plan_input.equals("2")) {

			telecom = print_telecom();
			data_min = min_plan_data();
			data_max = max_plan_data();
			call_min = min_plan_call();
			call_max = max_plan_call();
			price_min = min_plan_price();
			price_max = max_plan_price();
			c.search_plan(telecom, data_min, data_max, call_min, call_max, price_min, price_max);
		}
	}

	private static void print_model_all(ArrayList<Model> modellist) {

		int i;
		for (i = 0; i < modellist.size(); i++) {
			modellist.get(i).detail();
		}

	}

	private static String print_telecom() {
		System.out.println("��ȸ�Ͻ� ��Ż縦 �Է��ϼ���.");
		System.out.println("1.SKT   2.KT   3.U+");
		Scanner in = new Scanner(System.in);
		String input;
		input = in.next();
		if (input.equals("1"))
			input = "SKT";
		if (input.equals("2"))
			input = "KT";
		if (input.equals("3"))
			input = "U+";

		return input;
	}

	private static int min_plan_price() {
		int min;
		Scanner in = new Scanner(System.in);
		System.out.println("��ȸ�Ͻ� �ּ� ������ �Է��ϼ���. (��)");
		min = in.nextInt();
		return min;
	}

	private static int max_plan_price() {
		int max;
		String input;
		Scanner in = new Scanner(System.in);
		System.out.println("��ȸ�Ͻ� �ִ� ������ �Է��ϼ���. (��)");
		input = in.next();
		if (input.equals("���Ѵ�"))
			return 99999;
		max = Integer.parseInt(input);
		return max;
	}

	private static int max_plan_call() {
		int max;
		String input;
		Scanner in = new Scanner(System.in);
		System.out.println("��ȸ�Ͻ� �ִ� ��ȭ���� �Է��ϼ���. (��) or ���Ѵ�");
		input = in.next();
		if (input.equals("���Ѵ�"))
			return 99999;
		max = Integer.parseInt(input);
		return max;
	}

	private static int min_plan_call() {
		int min;
		Scanner in = new Scanner(System.in);
		System.out.println("��ȸ�Ͻ� �ּ� ��ȭ���� �Է��ϼ���. (��)");
		min = in.nextInt();
		return min;
	}

	private static double max_plan_data() {
		double max;
		String input;
		Scanner in = new Scanner(System.in);
		System.out.println("��ȸ�Ͻ� �ִ� �������� �Է��ϼ���. (���� : GB) or ���Ѵ�");
		input = in.next();
		if (input.equals("���Ѵ�"))
			return 999;
		max = Integer.parseInt(input);
		return max;
	}

	private static double min_plan_data() {
		double min;
		Scanner in = new Scanner(System.in);
		System.out.println("��ȸ�Ͻ� �ּ� �������� �Է��ϼ���. (GB)");
		min = in.nextDouble();
		return min;
	}

	private static String print_model_manufacture() {
		System.out.println("��ȸ�Ͻ� �����縦 �Է��ϼ���.");
		System.out.println("1.�Ｚ   2.Apple   3.LG   4.��Ÿ");
		Scanner in = new Scanner(System.in);
		String input;
		input = in.next();
		return input;
	}

	private static int min_model_pricelist() {
		int min;
		Scanner in = new Scanner(System.in);
		System.out.println("��ȸ�Ͻ� �ּ� ������ �Է��ϼ���.");
		min = in.nextInt();
		return min;
	}

	private static int max_model_pricelist() {
		int max;
		String input;
		Scanner in = new Scanner(System.in);
		System.out.println("��ȸ�Ͻ� �ִ� ������ �Է��ϼ���. (����:�� or ���Ѵ�)");
		input = in.next();
		if (input.equals("���Ѵ�"))
			return 999;
		max = Integer.parseInt(input);
		return max;
	}

	private static void print_plan_all(ArrayList<Plan> planlist) {

		int i;
		for (i = 0; i < planlist.size(); i++) {
			planlist.get(i).detail();
		}

	}
=======
package javaCode;
import java.util.*;
import java.io.*;

public class Test {

	public static void main(String[] args) {
		int k = 0;

		Admin admin;
		Scanner adminfile = null;

		try {
			adminfile = new Scanner(new FileInputStream("admin.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("File open error");
			System.exit(0);
		}

		String admin_id, admin_pw;
		admin_id = adminfile.next();
		admin_pw = adminfile.next();

		admin = new Admin(admin_id, admin_pw);

		ArrayList<Model> ModelList = new ArrayList<Model>();
		String line;
		Scanner ModelIn = null;
		int i;
		try {
			ModelIn = new Scanner(new FileInputStream("model.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("File open error");
			System.exit(0);
		}

		while (ModelIn.hasNext()) {
			line = ModelIn.nextLine();
			String[] split = line.split("\t");
			Model m = new Model(split[0], split[1], split[2],Double.parseDouble(split[4]), split[5], Integer.parseInt(split[3]), Integer.parseInt(split[6]),
					Integer.parseInt(split[7]), Integer.parseInt(split[8]), Integer.parseInt(split[9]), Integer.parseInt(split[10]), Integer.parseInt(split[11]),Double.parseDouble(split[12]),split[13],
					false);
			if (split[14].equals("x"))
				m.setHome(false);
			else
				m.setHome(true);
			ModelList.add(m);
		}
		ModelIn.close();

		ArrayList<Customer> customerlist = new ArrayList<Customer>();
		int suc = 0;
		Scanner customerfile = null;
		try {
			customerfile = new Scanner(new FileInputStream("custom.txt"));

		} catch (FileNotFoundException e) {
			System.out.println("File open error");
		}

		while (customerfile.hasNext()) {
			line = customerfile.nextLine();
			String[] split = line.split(" ");
			String temp = null;
			Customer customer = new Customer(split[0], split[1], split[2], split[3], split[4], split[5], split[6]);
			customerlist.add(customer);			
		}

		customerfile.close();

		ArrayList<Plan> PlanList = new ArrayList<Plan>();
		Scanner planfile = null;
		try {
			planfile = new Scanner(new FileInputStream("plan.txt"));

		} catch (FileNotFoundException e) {
			System.out.println("File open error");
		}

		while (planfile.hasNext()) {
			String temp = null;
			line = planfile.nextLine();
			String[] split = line.split("\t");
			Plan p = new Plan(split[0], Integer.parseInt(split[1]), Double.parseDouble(split[2]),
					Integer.parseInt(split[3]), Integer.parseInt(split[4]), split[5], split[6], split[7], split[8],
					split[9], split[10], Integer.parseInt(split[11]));
			PlanList.add(p);
		}
		planfile.close();

		System.out.println("---------- ������� �޴���, ����� ��õ �ý��� ȣ�������Դϴ�  ���ϴ� �޴��� �Է��� �ּ��� ----------");

		int j = 0;

		for (; j == 0;) {
			System.out.println(
					"�α����� ���Ͻø�   \"�α��� \" ��  ȸ�������� ���Ͻø�     \"ȸ������\" ����� �˻��� ���ϸ� \"������˻�\" �� �˻��� ���ϸ� \"�𵨰˻�\"  ��   �Է����ּ��� ���⿡ �������ּ���");
			String str;
			Scanner a = new Scanner(System.in);
			str = a.nextLine();

			if (str.equals("ȸ������")) // customer �� �����ϴ°�
			{
				System.out.println("ȸ������ �޴��� �����ϼ̽��ϴ�");
				System.out.print("ID �� �Է��ϼ���  : ");
				String str2;
				str2 = a.nextLine();
				for (i = 0; i < customerlist.size(); i++) {

					if (str2.equals(customerlist.get(i).getID())) {
						System.out.println("�ߺ��� ���̵� �ֽ��ϴ� . �ٸ� id�� �Է����ּ���");
						i = customerlist.size();
						break;
					} else if (i == customerlist.size() - 1) {

						System.out.println("��� ������ id�Դϴ� .");
						Customer newcustomer = new Customer();
						newcustomer.setID(str2);

						System.out.println("��й�ȣ�� �Է����ּ���.");
						str2 = a.nextLine();
						newcustomer.setpassword(str2);

						System.out.println("�̸��� �Է��� �ּ���");
						str2 = a.nextLine();
						newcustomer.setname(str2);

						System.out.println("���̸� �Է��� �ּ���");
						str2 = a.nextLine();
						newcustomer.setage(str2);

						System.out.println("��ȭ��ȣ�� �Է��� �ּ���");
						str2 = a.nextLine();
						newcustomer.setphone_number(str2);

						System.out.println("�̸��� �ּҸ� �Է��� �ּ���");
						str2 = a.nextLine();
						newcustomer.setemail_address(str2);

						System.out.println("������ �Է��� �ּ��� . ���ڸ� '��' ���ڸ� '��'�� �Է��� �ּ���");
						str2 = a.nextLine();
						newcustomer.setsex(str2);

						System.out.println("������� ��ǰ�� ���� ���並 �ۼ����ּž� ȸ�������� �Ϸ�˴ϴ�.");
						System.out.println("������� ��ǰ���� �Է��� �ּ���(���� ��� '����' �Է�).");
						String using = a.next();
						if (using.equals("����")) {
						} else {
							System.out.println("���並 �Է��� �ּ���.");
							String newreview = a.nextLine();
							ReviewManager reviewManager = new ReviewManager();
							reviewManager.AddReview(using, newreview);
						}

						customerlist.add(newcustomer);
						str2 = newcustomer.getname() + ' ' + newcustomer.getID() + ' ' + newcustomer.getpassword() + ' '
								+ newcustomer.getsex() + ' ' + newcustomer.getage() + ' '
								+ newcustomer.getemail_address() + ' ' + newcustomer.getphone_number();

						try {
							FileWriter filewriter = new FileWriter("custom.txt", true);

							BufferedWriter out = new BufferedWriter(filewriter);
							out.newLine();

							out.append(str2);
							out.close();
							System.out.println("ȸ������ �Ϸ�Ǿ����ϴ� ");
							break;
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

				}

			} else if (str.equals("�α���")) // ������Ʈ�� ���ؼ� ȸ�������� ������ �α��μ���!
			{
				System.out.println("�α��� �޴��� �����ϼ̽��ϴ�");
				System.out.println("���̵� �Է��� �ּ��� ");
				String inputID = new String();
				String inputPW = new String();
				Scanner keyboard = new Scanner(System.in);
				inputID = keyboard.nextLine(); // ���̵��Է¹��� ->�ߺ��˻��� ��й�ȣ�� ��!

				for (k = 0; k < customerlist.size(); k++) {
					if (inputID.equals(customerlist.get(k).getID())) {
						suc = 1;
						System.out.println("��й�ȣ�� �Է��ϼ���");
						inputPW = keyboard.next();
						if (inputPW.equals(customerlist.get(k).getpassword())) {
							suc = 2; // �α��� ����
							break;
						}
					}
				}
				if (inputID.equals(admin_id)) {
					System.out.println("��й�ȣ�� �Է��ϼ���");
					inputPW = keyboard.next();
				}

				if (suc == 0) {
					if (admin.Login(inputID, inputPW)) {
						System.out.println("�����ڷ� �α��� �ϼ̽��ϴ�. ������ �޴��� �̵��մϴ�.");
						suc = 3;
						break;
					} else {
						System.out.println("ID ���� , �α��ο� �����߽��ϴ� . ");

					}
				} else if (suc == 1) {
					System.out.println("password ���� , �α��ο� �����߽��ϴ�.");

				} else if (suc == 2) {
					System.out.println("�α��� ���� .");
					break;
				}

			} else if (str.equals("������˻�")) {
				System.out.println("����� �˻� �޴��� �����ϼ̽��ϴ�");
				j = 3;
				break;
			} else if (str.equals("�𵨰˻�")) {
				System.out.println("�𵨰˻� �޴��� �����ϼ̽��ϴ�");
				j = 2;
				break;
			} else {
				System.out.println("�߸��� �޴��� �����ϼ̽��ϴ� �ٽ� �Է����ּ��� . ���⿡ ������ �ּ���");

			}
		}

		if (suc == 2) {
			System.out.println("���ϴ� �޴��� �������ּ��� . 1.�޴��� ��õ �ޱ� , 2.����� ��õ �ޱ� , 3.��ǰ ��ȸ , 4.ȸ������ ���� , 5.ȸ�� Ż��");
			Scanner in = new Scanner(System.in);
			int menu;

			menu = in.nextInt();
			if (menu == 1) { // �޴��� ��õ�ޱ�
				Scanner keyboard = new Scanner(System.in);

				System.out.println("�޴��� ��õ�ޱ� �޴� �Դϴ�.");
				System.out.println("���ϴ� �����縦 �����ϼ���.");
				System.out.println("1.����  2.�Ｚ  3.LG 4.��Ÿ ");
				String wantmanufacture;
				wantmanufacture = keyboard.nextLine();
				String[] manu_split = wantmanufacture.split(" ");

				System.out.println("���ϴ� ���ݴ븦 �Է��� �ּ���");
				System.out.println(
						"1. 0~10����  2. 10~20����  3. 30~40����  5. 40~50����  6. 50~60����  7. 60~70����  8. 70~80����  9. 80~90����  10. 90~100����  11. 100~110����  12. 110~120����  13. 120~130����  14. 130~140����  15. 140~150����  16. 150���� �̻�");

				String wantprice;
				wantprice = keyboard.nextLine();
				String[] price_split = wantprice.split(" ");

				System.out.println("��� ��� ���� �� ���ϴ� ����� �Է��� �ּ���");
				System.out.println("1. Ȩ��ư �ʿ�  2. ���� ����  3. ��뷮 ���͸�  4. ���� ī�޶�  5. ū ȭ��  6. ��뷮 �޸�  7. ��ȭ�� ������");

				String wantfunction;
				wantfunction = keyboard.nextLine();
				String[] function_split = wantfunction.split(" ");

				customerlist.get(k).recommand_Models(manu_split, price_split, function_split, customerlist.get(k),
						ModelList);

			} else if (menu == 2) {
				Scanner keyboard = new Scanner(System.in);
				System.out.println("����� ��õ�ޱ� �޴� �Դϴ�.");
				System.out.println("���ϴ� ��Ż縦 �����ϼ���.");
				System.out.println("1.SKT  2.KT  3.U+ ");
				String wantmanufacture;
				wantmanufacture = keyboard.nextLine();
				String[] manu_split = wantmanufacture.split(" ");

				System.out.println("������ ��뷮�� �����ϼ���");
				System.out.println(
						"1. 0~1GB  2. 1~2GB  3. 2~3GB  4. 3~4GB  5. 4~5GB  6. 5~6GB  7. 6~7GB  8. 7~8GB  9. 8~9GB  10. 9~10GB  11. 10GB�̻�");
				String wantdata;
				wantdata = keyboard.nextLine();
				String[] data_split = wantdata.split(" ");

				System.out.println("��ȭ ��뷮�� �����ϼ���");
				System.out.println(
						"1. 0~50��  2. 50~100��  3. 100~150��  4. 150~200��  5. 200~250��  6. 250~300��  7. 300~350��  8. 350~400��  9. 400~450��  10. 450~500��  11. 500���̻�");
				String wantcalling;
				wantcalling = keyboard.nextLine();
				String[] calling_split = wantcalling.split(" ");

				System.out.println("���� ��뷮�� �����ϼ���");
				System.out.println(
						"1. 0~50��  2. 50~100��  3. 100~150��  4. 150~200��  5. 200~250��  6. 250~300��  7. 300~350��  8. 350~400��  9. 400~450��  10. 450~500��  11. 500���̻�");
				String wantmessage;
				wantmessage = keyboard.nextLine();
				String[] message_split = wantmessage.split(" ");

				customerlist.get(k).recommand_Plans(manu_split, data_split, calling_split, message_split,customerlist.get(k), PlanList);

			} // ����� ��õ �ޱ�

			else if (menu == 3) {
				String select;
				System.out.println("1.�� ��ȸ, 2.����� ��ȸ");
				select = in.next();
				if (select.equals("����ȸ") || select.equals("�� ��ȸ") || select.equals("1")) {
					view_model(ModelList);
				} else if (select.equals("�������ȸ") || select.equals("����� ��ȸ") || select.equals("2")) {
					view_plan(PlanList);
				}
			} // ��ǰ ��ȸ

			else if (menu == 4) {// ȸ������ ����

				System.out.println("�����ϰ� ���� ������ �������ּ���");
				System.out.println("1.�̸�, 2.PASSWORD, 3.����, 4.����, 5.�����ּ�, 6.����ó");
				int menu2 = in.nextInt();

				if (menu2 == 1) {
					System.out.println("�̸� ������ �����Ͽ����ϴ�. �ٲٽ� �̸��� �Է��� �ּ���");
				} // �̸� ����
				else if (menu2 == 2) {
					System.out.println("��й�ȣ ������ �����Ͽ����ϴ�. �ٲٽ� ��й�ȣ�� �Է��� �ּ���");
				} // ��� ����
				else if (menu2 == 3) {
					System.out.println("���� ������ �����Ͽ����ϴ�. �ٲٽ� ������ �Է��� �ּ���");
				} // ���� ����
				else if (menu2 == 4) {
					System.out.println("���� ������ �����Ͽ����ϴ�. �ٲٽ� ���̸� �Է��� �ּ���");
				} // ���� ����
				else if (menu2 == 5) {
					System.out.println("�����ּ� ������ �����Ͽ����ϴ�. �ٲٽ� �����ּҸ� �Է��� �ּ���");
				} // �ּ� ����
				else if (menu2 == 6) {
					System.out.println("����ó ������ �����Ͽ����ϴ�. �ٲٽ� ����ó�� �Է��� �ּ���");
				} // ����ó ����
				String newstring = in.next();
				Customer temp;
				temp = customerlist.get(k);
				temp.modify(customerlist, newstring, k, menu2);
				System.out.println("�����Ϸ�");

			} else if (menu == 5) {// ȸ��Ż��
				Scanner keyboard = new Scanner(System.in);
				System.out.println("ȸ�� Ż�� ���Ͻø� ��й�ȣ�� �ѹ� �� �Է��� �ֽʽÿ�.");
				String pw;
				Customer Removing;
				Removing = customerlist.get(k);
				pw = keyboard.next();
				if (pw.equals(Removing.getpassword())) {
					Removing.remove_member(customerlist, k);
					System.out.println("Ż��Ϸ�");
				}
			} else {
				System.out.println("��й�ȣ�� Ʋ�Ƚ��ϴ�. ���α׷��� �����մϴ�.");
				System.exit(1);
			}
		} else if (suc == 3) {// ������
			String admin_menu;

			System.out.println("���Ͻô� �޴��� ��ȣ�� �Է����ּ���.");
			System.out.println("1.���߰�, 2.������߰�, 3.�𵨻���, 4.���������");

			Scanner keyboard = new Scanner(System.in);

			admin_menu = keyboard.next();

			if (admin_menu.equals("1")) {
				// �� �߰�
				int price, camera_front, storage, ram, camera_back, weight, battery;
				String name, manufacture, os, led, cpu_name;
				boolean home;
				double size, cpu_rate;

				System.out.println("�� �߰� �޴��Դϴ�.");
				System.out.println("�߰��Ϸ��� ���� ������ �Է��մϴ�.");
				System.out.println("���� : ");
				price = keyboard.nextInt();

				System.out.println("���� ī�޶� ȭ�� : ");
				camera_front = keyboard.nextInt();

				System.out.println("cpu rate : ");
				cpu_rate = keyboard.nextDouble();

				System.out.println("����� �뷮 : ");
				storage = keyboard.nextInt();

				System.out.println("�� ���: ");
				ram = keyboard.nextInt();

				System.out.println("�ĸ� ī�޶� ȭ�� : ");
				camera_back = keyboard.nextInt();

				System.out.println("���� : ");
				weight = keyboard.nextInt();

				System.out.println("���͸� : ");
				battery = keyboard.nextInt();
				keyboard.nextLine();

				System.out.println("�� �̸� : ");
				name = keyboard.nextLine();

				System.out.println("������ : ");
				manufacture = keyboard.nextLine();

				System.out.println("os : ");
				os = keyboard.nextLine();

				System.out.println("led : ");
				led = keyboard.nextLine();

				System.out.println("cpu name : ");
				cpu_name = keyboard.nextLine();

				System.out.println("Ȩ��ư ����(������ 1, ������ 0): ");
				String temp;
				temp = keyboard.next();
				if (temp.equals("1")) {
					home = true;
				} else {
					home = false;
				}

				System.out.println("������ : ");
				size = keyboard.nextDouble();

				Model NewModel = new Model(name, manufacture, cpu_name, size, os, price, storage, ram, camera_front,
						camera_back, weight, battery, cpu_rate, led, home);

				if (admin.AddModel(NewModel, ModelList)) {
					System.out.println("�߰��Ϸ�");
				} else {
					System.out.println("�̹� �����ϴ� ���Դϴ�");
					System.exit(0);
				}

			} else if (admin_menu.equals("2")) {// ����� �߰�
				System.out.println("����� �߰� �޴��Դϴ�.");
				System.out.println("�߰��Ϸ��� ���� ������ �Է��մϴ�.");
				int price, max_age, message;
				String name;
				double data;
				int calling;
				String detail_message, detail_calling, detail_data, detail_etc1, detail_etc2;
				String telecom;

				System.out.println("��Ż� : ");
				telecom = keyboard.next();
				keyboard.nextLine();

				System.out.println("�̸� : ");
				name = keyboard.nextLine();

				System.out.println("���� ����(������  0) : ");
				max_age = keyboard.nextInt();

				System.out.println("��ȭ�� : ");
				calling = keyboard.nextInt();

				System.out.println("���� : ");
				price = keyboard.nextInt();

				System.out.println("������ �� : ");
				data = keyboard.nextDouble();
				keyboard.nextLine();

				System.out.println("������ �������� : ");
				detail_data = keyboard.nextLine();

				System.out.println("��ȭ �������� : ");
				detail_calling = keyboard.nextLine();

				System.out.println("���ڷ� : ");
				message = keyboard.nextInt();
				keyboard.nextLine();

				System.out.println("���� �������� : ");
				detail_message = keyboard.nextLine();

				System.out.println("Ư�̻���1 (������ X): ");
				detail_etc1 = keyboard.nextLine();

				System.out.println("Ư�̻���2 (������ X) : ");
				detail_etc2 = keyboard.nextLine();

				Plan NewPlan = new Plan(name, max_age, data, calling, price, detail_data, detail_calling,
						detail_message, telecom, detail_etc1, detail_etc2, message);

				if (admin.AddPlan(NewPlan, PlanList)) {
					System.out.println("�߰��Ϸ�");
				} else {
					System.out.println("�̹� �����ϴ� ����� �Դϴ�");
					System.exit(0);
				}

			} else if (admin_menu.equals("3")) { // �� ����
				System.out.println("�� ���� �޴��Դϴ�. ������ ���� �̸��� �Է����ּ���");
				String target;
				keyboard.nextLine();
				target = keyboard.nextLine();
				if (admin.RemoveModel(target, ModelList)) {
					System.out.println("���� �Ϸ�");
				} else {
					System.out.println("���� ���� : ���� �� �Դϴ�.");
					System.exit(0);
				}
			} else if (admin_menu.equals("4")) { // ����� ����
				System.out.println("����� ���� �޴��Դϴ�. ������ ������� �̸��� �Է����ּ���");
				String target_name;
				keyboard.nextLine();
				target_name = keyboard.nextLine();
				System.out.println("������ ������� ��Ż縦 �Է��� �ּ���");
				String target_telecom;
				target_telecom = keyboard.nextLine();

				if (admin.RemovePlan(target_telecom, target_name, PlanList)) {
					System.out.println("���� �Ϸ�");
				} else {
					System.out.println("���� ���� : ���� ����� �Դϴ�.");
					System.exit(0);
				}
			}
		}

		if (j == 2) // �� �˻�
		{
			view_model(ModelList);
		} else if (j == 3) // ����� �˻�

		{
			view_plan(PlanList);

		}
	}

	private static void view_model(ArrayList<Model> ModelList) {
		int min = 0, max = 0;// �޾ƿ;���.
		String select;
		String manufacture;
		Customer c = new Customer();
		Scanner in = new Scanner(System.in);

		System.out.println("1.��ü���   2.���ǰ˻�");
		select = in.next();

		if (select.equals("��ü���") || select.equals("1"))
			print_model_all(ModelList);
		else {
			manufacture = print_model_manufacture();
			min = min_model_pricelist();
			max = max_model_pricelist();
			c.search_model(manufacture, min, max); // �������̸�, min ����, max����
		} // ������ ����ϴ� �Լ�
	}

	private static void view_plan(ArrayList<Plan> PlanList) {
		int call_min, call_max, price_min, price_max;
		double data_min, data_max;
		String plan_input, telecom;
		System.out.println("1.��ü��ȸ 2.���� �˻�");
		Customer c = new Customer();
		Scanner in = new Scanner(System.in);
		plan_input = in.next();
		if (plan_input.equals("��ü��ȸ") || plan_input.equals("��ü ��ȸ") || plan_input.equals("1"))
			print_plan_all(PlanList);
		else if (plan_input.equals("���ǰ˻�") || plan_input.equals("���� �˻�") || plan_input.equals("2")) {

			telecom = print_telecom();
			data_min = min_plan_data();
			data_max = max_plan_data();
			call_min = min_plan_call();
			call_max = max_plan_call();
			price_min = min_plan_price();
			price_max = max_plan_price();
			c.search_plan(telecom, data_min, data_max, call_min, call_max, price_min, price_max);
		}
	}

	private static void print_model_all(ArrayList<Model> modellist) {

		int i;
		for (i = 0; i < modellist.size(); i++) {
			modellist.get(i).detail();
		}

	}

	private static String print_telecom() {
		System.out.println("��ȸ�Ͻ� ��Ż縦 �Է��ϼ���.");
		System.out.println("1.SKT   2.KT   3.U+");
		Scanner in = new Scanner(System.in);
		String input;
		input = in.next();
		if (input.equals("1"))
			input = "SKT";
		if (input.equals("2"))
			input = "KT";
		if (input.equals("3"))
			input = "U+";

		return input;
	}

	private static int min_plan_price() {
		int min;
		Scanner in = new Scanner(System.in);
		System.out.println("��ȸ�Ͻ� �ּ� ������ �Է��ϼ���. (��)");
		min = in.nextInt();
		return min;
	}

	private static int max_plan_price() {
		int max;
		String input;
		Scanner in = new Scanner(System.in);
		System.out.println("��ȸ�Ͻ� �ִ� ������ �Է��ϼ���. (��)");
		input = in.next();
		if (input.equals("���Ѵ�"))
			return 99999;
		max = Integer.parseInt(input);
		return max;
	}

	private static int max_plan_call() {
		int max;
		String input;
		Scanner in = new Scanner(System.in);
		System.out.println("��ȸ�Ͻ� �ִ� ��ȭ���� �Է��ϼ���. (��) or ���Ѵ�");
		input = in.next();
		if (input.equals("���Ѵ�"))
			return 99999;
		max = Integer.parseInt(input);
		return max;
	}

	private static int min_plan_call() {
		int min;
		Scanner in = new Scanner(System.in);
		System.out.println("��ȸ�Ͻ� �ּ� ��ȭ���� �Է��ϼ���. (��)");
		min = in.nextInt();
		return min;
	}

	private static double max_plan_data() {
		double max;
		String input;
		Scanner in = new Scanner(System.in);
		System.out.println("��ȸ�Ͻ� �ִ� �������� �Է��ϼ���. (���� : GB) or ���Ѵ�");
		input = in.next();
		if (input.equals("���Ѵ�"))
			return 999;
		max = Integer.parseInt(input);
		return max;
	}

	private static double min_plan_data() {
		double min;
		Scanner in = new Scanner(System.in);
		System.out.println("��ȸ�Ͻ� �ּ� �������� �Է��ϼ���. (GB)");
		min = in.nextDouble();
		return min;
	}

	private static String print_model_manufacture() {
		System.out.println("��ȸ�Ͻ� �����縦 �Է��ϼ���.");
		System.out.println("1.�Ｚ   2.Apple   3.LG   4.��Ÿ");
		Scanner in = new Scanner(System.in);
		String input;
		input = in.next();
		return input;
	}

	private static int min_model_pricelist() {
		int min;
		Scanner in = new Scanner(System.in);
		System.out.println("��ȸ�Ͻ� �ּ� ������ �Է��ϼ���.");
		min = in.nextInt();
		return min;
	}

	private static int max_model_pricelist() {
		int max;
		String input;
		Scanner in = new Scanner(System.in);
		System.out.println("��ȸ�Ͻ� �ִ� ������ �Է��ϼ���. (����:�� or ���Ѵ�)");
		input = in.next();
		if (input.equals("���Ѵ�"))
			return 999;
		max = Integer.parseInt(input);
		return max;
	}

	private static void print_plan_all(ArrayList<Plan> planlist) {

		int i;
		for (i = 0; i < planlist.size(); i++) {
			planlist.get(i).detail();
		}

	}
>>>>>>> origin/hsy:src/javaCode/Test.java
}