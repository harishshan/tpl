package com.exterro.tpl3;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LiveScore
 */
@WebServlet("/LiveScore")
public class LiveScore extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);

	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		PreparedStatement ps;

		String updateScore = "update livescore set score=?,wicket=?,team_extra=?,bowl_extra=?,overs=?,balls=? where team=? and match_id=?";



		int wickets = Integer.parseInt(request.getParameter("wickt_detl"));
		int teamScore = Integer.parseInt(request.getParameter("team_scr_cnt"));
		int overs = Integer.parseInt(request.getParameter("over_detl"));
		int balls = Integer.parseInt(request.getParameter("balls_delt"));
		int team_extras = Integer.parseInt(request.getParameter("team_ext"));
		int bowler_extras = Integer.parseInt(request.getParameter("bwl_ext"));
		String match_id = request.getParameter("match_id");
		String Batting = request.getParameter("innings");

		String databaseURL = "jdbc:sqlserver://localhost:1433;databaseName=tpl3";
		String userName="sa";
		String password="exterro-123";
		Connection conn = null;


		try
		{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection(databaseURL, userName, password);
			ps = conn.prepareStatement(updateScore);
			ps.setInt(1,teamScore);
			ps.setInt(2,wickets);
			ps.setInt(3,team_extras);
			ps.setInt(4,bowler_extras);
			ps.setInt(5,overs);
			ps.setInt(6,balls);
			ps.setString(7,Batting);
			ps.setString(8,match_id);
			ps.executeUpdate();
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
