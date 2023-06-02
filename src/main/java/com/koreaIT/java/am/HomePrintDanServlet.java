package com.koreaIT.java.am;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/home/printDan")
public class HomePrintDanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//디스패처로 경로 알려주고, 포워드로 요청,응답 넘겨줌
		request.getRequestDispatcher("/jsp/home/printDan.jsp").forward(request, response);//요청을 jsp한테 넘겨 준다.
		
	}
}
