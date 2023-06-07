package com.koreaIT.java.am;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.koreaIT.java.am.util.DBUtil;
import com.koreaIT.java.am.util.SecSql;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/article/doWrite")
public class ArticleDoWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html; charset=utf-8");
		Connection conn = null;

		try {
			System.out.println("===== ArticleDeleteServlet =====");

			Class.forName("com.mysql.cj.jdbc.Driver"); // ClassNotFoundException
			String url = "jdbc:mysql://localhost:3306/jsp_article_manager";
			conn = DriverManager.getConnection(url, "root", ""); // SQLException
			
			String title = request.getParameter("title");
			String body = request.getParameter("body");
	
			SecSql sql = new SecSql();
			sql.append("INSERT INTO article");
			sql.append("SET updateDate = NOW()");
			sql.append(", title = ?", title);
			sql.append(", `body` = ?", body);
			int id = DBUtil.insert(conn, sql);
			response.getWriter().append(String.format("<script>alert('%d번 게시글이 생성되었습니다!'); location.replace('list');</script>", id));
		
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
		} catch (SQLException e) {
			System.out.println("에러: " + e);
		} finally {
			try {
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
