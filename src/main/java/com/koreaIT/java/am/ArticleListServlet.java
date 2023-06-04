package com.koreaIT.java.am;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.koreaIT.java.am.util.DBUtil;
import com.koreaIT.java.am.util.SecSql;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/article/list")
public class ArticleListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Connection conn = null;

		try {
			System.out.println("=====프로그램 시작=====");

			Class.forName("com.mysql.cj.jdbc.Driver"); // ClassNotFoundException
			String url = "jdbc:mysql://localhost:3306/jsp_article_manager";
			conn = DriverManager.getConnection(url, "root", ""); // SQLException
			
			SecSql sql = new SecSql();
			sql.append("SELECT *");
			sql.append("FROM article");
			sql.append("ORDER BY id DESC");
			List<Map<String, Object>> articleListMap = DBUtil.selectRows(conn, sql);
			request.setAttribute("articleListMap", articleListMap);// key/value
			request.getRequestDispatcher("/jsp/article/list.jsp").forward(request, response);
		
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
		} // end finally(사용한 자원 종료)
	}

}
