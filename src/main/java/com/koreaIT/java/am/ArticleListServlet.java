package com.koreaIT.java.am;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
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

@WebServlet("/article/list")
public class ArticleListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Connection conn = null;

		try {
			System.out.println("===== ArticleListServlet =====");

			Class.forName(Config.getDBDriverName());
			String url = Config.getDBUrl();
			conn = DriverManager.getConnection(url, Config.getDBUser(), Config.getDBPwd());

			int page = 1; // 몇번째 페이지 골라놨냐
			if (request.getParameter("page") != null && request.getParameter("page").length() != 0) {
				page = Integer.parseInt(request.getParameter("page"));
			}
			int itemsInAPage = 10;
			int limitFrom = (page - 1) * itemsInAPage; // 시작 페이지
			SecSql sql = new SecSql();
			sql.append("SELECT COUNT(*) FROM article");
			int totalCount = DBUtil.selectRowIntValue(conn, sql); // 총 게시글 수

			int totalPage = (int) Math.ceil((double) totalCount / itemsInAPage);

			int pageSize = 5;
			int from = page - pageSize; // 앞에 5개
			if (from < 1) {
				from = 1;
			}
			int end = page + pageSize; // 뒤에 5개
			if (end > totalPage) {
				end = totalPage;
			}

			HttpSession session = request.getSession();
			int loginedMemberId = -1;
			if (session.getAttribute("loginedMemberId") != null) {
				loginedMemberId = (int) session.getAttribute("loginedMemberId");
			}

			sql = new SecSql();
			sql.append("SELECT a.*, m.name");
			sql.append("FROM article AS a");
			sql.append("INNER JOIN `member` AS m");
			sql.append("ON a.memberId = m.id");
			sql.append("ORDER BY a.id DESC");
			sql.append("LIMIT ?, ?", limitFrom, itemsInAPage);

			List<Map<String, Object>> articleListMap = DBUtil.selectRows(conn, sql);

			request.setAttribute("page", page);
			request.setAttribute("totalPage", totalPage);
			request.setAttribute("from", from);
			request.setAttribute("end", end);
			request.setAttribute("articleListMap", articleListMap);
			request.setAttribute("loginedMemberId", loginedMemberId);
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
