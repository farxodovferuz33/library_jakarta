package com.example.cloned_library.servlet.admin;

import com.example.cloned_library.dao.BookDAO;
import com.example.cloned_library.dao.UploadDAO;
import com.example.cloned_library.entity.Book;
import com.example.cloned_library.entity.Upload;
import com.example.cloned_library.utils.StringUtils;
import com.example.cloned_library.utils.ValidationUtils;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Set;
import java.util.UUID;

@WebServlet(name = "AdminBookCreateServlet", value = "/admin/book/create")
@MultipartConfig
public class AdminBookCreateServlet extends HttpServlet {

    private static final Path rootPath = Path.of( "C:\\Users\\Feruz\\Books");
    private static final BookDAO bookDAO = new BookDAO();
    private static final UploadDAO uploadDAO = new UploadDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/admin/create_book.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        Part file = request.getPart("file");

        String originalName = file.getSubmittedFileName();
        String extension = StringUtils.fileExtension(originalName);
        Upload upload = Upload.childBuilder()
                .generatedName(UUID.randomUUID() + "." + extension)
                .originalName(originalName)
                .extension(extension)
                .size(file.getSize())
                .mimeType(file.getContentType())
                .build();

        Book book = Book.childBuilder()
                .title(title)
                .description(description)
                .file(upload)
                .build();

        Set<String> bookErrors = ValidationUtils.validate(book);

        if (bookErrors.isEmpty()) {
            bookDAO.save(book);

            Path pathToUpload = rootPath.resolve(upload.getGeneratedName());
            System.out.println(pathToUpload);
            Files.copy(file.getInputStream(), pathToUpload, StandardCopyOption.REPLACE_EXISTING);
            response.sendRedirect("/book/list");
        }
        else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/admin/create_book.jsp");
            request.setAttribute("error", bookErrors);
            System.out.println(bookErrors);
            dispatcher.forward(request, response);
        }


    }
}
