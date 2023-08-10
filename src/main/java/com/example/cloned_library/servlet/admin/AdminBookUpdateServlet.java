package com.example.cloned_library.servlet.admin;

import com.example.cloned_library.dao.BookDAO;
import com.example.cloned_library.entity.Book;
import com.example.cloned_library.utils.ValidationUtils;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Set;

@WebServlet(name = "AdminBookUpdateServlet", urlPatterns = "/admin/book/update/*")
public class AdminBookUpdateServlet extends HttpServlet {
    BookDAO bookDAO = new BookDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo().substring(1);
        Book bookById = bookDAO.findById(pathInfo);
        request.setAttribute("book", bookById);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/admin/update_book.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo().substring(1);

        String title = request.getParameter("title");
        String description = request.getParameter("description");

        Book book = bookDAO.findById(pathInfo);

        if (title.equals("") && !description.equals("")) {
            book.setTitle(book.getTitle());
            book.setDescription(description);
        } else if (!title.equals("") && description.equals("")) {
            book.setTitle(title);
            book.setDescription(book.getDescription());
        } else {
            book.setTitle(title);
            book.setDescription(description);
        }
        bookDAO.update(book);
        response.sendRedirect("/book/list");
    }
}
