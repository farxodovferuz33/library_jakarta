package com.example.cloned_library.servlet.admin;

import com.example.cloned_library.dao.BookDAO;
import com.example.cloned_library.entity.Book;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "AdminBookDeleteServlet", urlPatterns = "/admin/book/delete/*")
public class AdminBookDeleteServlet extends HttpServlet {
    BookDAO bookDAO = new BookDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo().substring(1);
        Book bookById = bookDAO.findById(pathInfo);
        request.setAttribute("book", bookById);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/admin/delete_book.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo().substring(1);
        boolean deleted = bookDAO.deleteById(pathInfo);
        if (deleted)
            response.sendRedirect("/book/list");
        else response.sendError(500, "Unable to delete");
    }
}
