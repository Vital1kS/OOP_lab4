package me.vital1ks.lab4;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jakarta.servlet.ServletContext;

@WebServlet("/editFlower")
public class EditFlower extends HttpServlet {
	private static final long serialVersionUID = 4L;
	Connection connection;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ServletContext sc = getServletContext();

		try {
			Driver driver = new com.mysql.cj.jdbc.Driver();
			DriverManager.registerDriver(driver);
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/flowers_database", "root", "");
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String query = String.format("select * from flowers where flower_index=" + request.getParameter("id"));
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			if(resultSet.next()) {
			Flower tempFlower = new Flower(resultSet.getString("name"), resultSet.getString("main_color"),
					resultSet.getString("family"), resultSet.getInt("height"), resultSet.getInt("price"),
					resultSet.getInt("flower_index"));
			request.setAttribute("flower", tempFlower);}
			sc.getRequestDispatcher("/jsp/edit.jsp").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}	

}
