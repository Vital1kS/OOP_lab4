package me.vital1ks.lab4;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mysql.cj.jdbc.Driver;

import jakarta.servlet.ServletContext;

/**
 * Servlet implementation class SaveChanges
 */
@WebServlet("/saveChanges")
public class SaveChanges extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection connection;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String data = request.getReader().readLine();
		Flower flower = gson.fromJson(data, Flower.class);
		String query = String.format("update flowers set name='%s', main_color='%s', family='%s', height='%d', price='%d' where flower_index='%d'",
				flower.getName(),flower.getMain_color(),flower.getFamily(),flower.getHeight(),flower.getPrice(),flower.getIdInDatabase());
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		sc.getRequestDispatcher("/jsp/read.jsp").forward(request, response);
	}
	}


