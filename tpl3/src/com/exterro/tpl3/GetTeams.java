package com.exterro.tpl3;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GetMatchDetails
 */
@WebServlet("/GetTeams")
public class GetTeams extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GetTeams() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doPost(request,response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
		String match_id = request.getParameter("match_id"); 
		PrintWriter out=response.getWriter();
		String sqlQuery = "select team1,team2 from match_result where match_id='"+match_id+"'";
		String databaseURL = "jdbc:sqlserver://localhost:1433;databaseName=tpl3";
		String userName="sa";
		String password="exterro-123";
		Connection conn = null;
		try 
		{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection(databaseURL, userName, password);
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sqlQuery);
			while(rs.next())
			{
				out.print(rs.getString("team1")+","+rs.getString("team2"));
				
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
