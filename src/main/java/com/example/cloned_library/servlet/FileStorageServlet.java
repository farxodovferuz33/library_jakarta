package com.example.cloned_library.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@WebServlet(name = "FileStorageServlet", urlPatterns = "/storage/show")
public class FileStorageServlet extends HttpServlet {
    private static final Path rootPath = Path.of("C:\\Users\\Feruz\\Books");

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fileName = request.getParameter("filename");
        Path path = rootPath.resolve(fileName);
        byte[] bytes = Files.readAllBytes(path);
        response.getOutputStream().write(bytes);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
