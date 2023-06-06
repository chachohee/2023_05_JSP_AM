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

@WebServlet("/article/doDelete")
public class ArticleDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html; charset=utf-8");
		Connection conn = null;

		try {
			System.out.println("===== ArticleDeleteServlet =====");

			Class.forName("com.mysql.cj.jdbc.Driver"); // ClassNotFoundException
			String url = "jdbc:mysql://localhost:3306/jsp_article_manager";
			conn = DriverManager.getConnection(url, "root", ""); // SQLException
			
			int id = Integer.parseInt(request.getParameter("id"));//넘겨 받은 id 형변환.
			
			SecSql sql = new SecSql();
			sql.append("DELETE FROM article");
			sql.append("WHERE id =?", id);
			DBUtil.delete(conn, sql);
			response.getWriter().append(String.format("<script>alert('%d번 게시물 삭제 성공'); location.replace('list');</script>", id));
		
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
