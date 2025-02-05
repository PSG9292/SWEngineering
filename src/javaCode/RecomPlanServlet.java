package javaCode;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RecomPlanServlet
 */
@WebServlet("/RecomPlanServlet")
public class RecomPlanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecomPlanServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		
		String[] company = request.getParameterValues("company");
		String[] type = request.getParameterValues("type");
		String minPrice = request.getParameter("minprice");
		String maxPrice = request.getParameter("maxprice");
		String data = request.getParameter("data");
		String dataAmount = new String();
		int dataA=0;
		double dataB=0;
		if(data.contentEquals("self")) // 직접입력
		{
			dataAmount = request.getParameter("dataAmount");
			dataB = Double.parseDouble(dataAmount);
			dataB = dataB * 1000;
			dataA = (int)dataB;
		}
		else // 무제한
		{
			dataAmount = "500000";
			dataA = Integer.parseInt(dataAmount);
		}
		String[] option = request.getParameterValues("option");
		
		System.out.println("company : " + company[0]);
		System.out.println("type : " + type[0]);
		System.out.println("data : " + data);
		System.out.println("data양 : " + dataAmount);
		System.out.println("option : " + option[0]);
		
		Plan[] result = new Plan[3];
		DBTest db = new DBTest();
		db.connectDB();
		
		recomPlan rep = new recomPlan();
		rep.makeplan_list();
		result = rep.recPlan(company, type, Integer.parseInt(minPrice), Integer.parseInt(maxPrice), dataA, option);
		
		request.setAttribute("plan1", result[0]); 
		request.setAttribute("plan2", result[1]);
		request.setAttribute("plan3", result[2]);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("recommendedPlan.jsp");
		
		dispatcher.forward(request, response);
	}

}
