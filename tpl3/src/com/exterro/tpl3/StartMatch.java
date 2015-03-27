package com.exterro.tpl3;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class StartMatch
 */
@WebServlet("/StartMatch")
public class StartMatch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StartMatch() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String match_id = request.getParameter("match_id");
		String toss = request.getParameter("toss_team");
		String Batting = request.getParameter("innings");
		String innings_status = request.getParameter("innings_status");
		PreparedStatement ps;
		
		
		String sqlQuery = "update match_result set teamtoss='"+toss+"' where match_id='"+match_id+"'";
		String insertLiveScore = "insert into livescore values(?,?,0,0,0,0,0,0,?)";
		String databaseURL = "jdbc:sqlserver://localhost:1433;databaseName=tpl3";
		String userName="sa";
		String password="exterro-123";
		Connection conn = null;
		try 
		{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection(databaseURL, userName, password);
			Statement st = conn.createStatement();
			int suc = st.executeUpdate(sqlQuery);
			ps = conn.prepareStatement(insertLiveScore);
			ps.setString(1,Batting);
			ps.setString(2,match_id);
			ps.setString(3,innings_status);
			ps.executeUpdate();
			
			if(suc>0){
				out.print("Successfully updated toss");
			}
			else{
				out.print("no update in toss");
			}
			
		}
		
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		catch (ClassNotFoundException e)
		{
			
			e.printStackTrace();
		}
	}

}
