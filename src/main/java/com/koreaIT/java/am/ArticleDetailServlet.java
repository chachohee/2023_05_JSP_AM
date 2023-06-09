package com.koreaIT.java.am;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

import com.koreaIT.java.am.config.Config;
import com.koreaIT.java.am.util.DBUtil;
import com.koreaIT.java.am.util.SecSql;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/article/detail")
public class ArticleDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection conn = null;

		try {
			System.out.println("===== ArticleDetailServlet =====");

			Class.forName(Config.getDBDriverName());
			String url = Config.getDBUrl();
			conn = DriverManager.getConnection(url, Config.getDBUser(), Config.getDBPwd());
			
			int id = Integer.parseInt(request.getParameter("id"));//넘겨 받은 id 형변환.
			
			SecSql sql = new SecSql();
			sql.append("SELECT a.*, m.name");
			sql.append("FROM article AS a");
			sql.append("INNER JOIN `member` AS m");
			sql.append("ON a.memberId = m.id");
			sql.append("WHERE a.id = ?", id);
			Map<String, Object> article = DBUtil.selectRow(conn, sql);
			
			HttpSession session = request.getSession();
			int loginedMemberId = -1;
			if(session.getAttribute("loginedMemberId") != null) {
				loginedMemberId = (int) session.getAttribute("loginedMemberId");
			}
			
			request.setAttribute("article", article);
			request.setAttribute("loginedMemberId", loginedMemberId);
			request.getRequestDispatcher("/jsp/article/detail.jsp").forward(request, response);
		
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
