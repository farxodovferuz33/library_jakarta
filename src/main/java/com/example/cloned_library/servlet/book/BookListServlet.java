package com.example.cloned_library.servlet.book;

import com.example.cloned_library.dao.BookDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Objects;

@WebServlet(name = "BookServlet", value = "/book/list")
public class BookListServlet extends HttpServlet {

    private final BookDAO bookDAO = new BookDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pageNumber = request.getParameter("page");
        int pageSize = 7;
        if (pageNumber!=null){
            request.setAttribute("books", bookDAO.getEntitiesByPage(7, Integer.parseInt(request.getParameter("page"))));
            long totalRecords = bookDAO.getTotalRecords();
            int totalPages = (int) Math.ceil((double) totalRecords / pageSize);
            request.setAttribute("pageNumber", pageNumber);
            request.setAttribute("totalPages", totalPages);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/book/book_list.jsp");
            dispatcher.forward(request, response);
        }else {
            response.sendRedirect(request.getContextPath() + "/book/list?page=1");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendError(405);
    }
}
