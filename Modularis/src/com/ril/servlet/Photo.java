package com.ril.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ril.model.Image;
import com.ril.service.ImageService;

/**
 * Servlet implementation class Photo
 */
@WebServlet(name ="/Photo", urlPatterns = {"/Photo/*"})
public class Photo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    private String imagePath;
    
    
    public void init() throws ServletException {

        this.imagePath = "/var/webapp/images";

    }
	
	 protected void processRequest(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        response.setContentType("text/html;charset=UTF-8");
	        try (PrintWriter out = response.getWriter()) {
	            /* TODO output your page here. You may use following sample code. */
	            out.println("<!DOCTYPE html>");
	            out.println("<html>");
	            out.println("<head>");
	            out.println("<title>Servlet ImageServlet</title>");            
	            out.println("</head>");
	            out.println("<body>");
	            out.println("<h1>Servlet ImageServlet at " + request.getContextPath() + "</h1>");
	            out.println("</body>");
	            out.println("</html>");
	        }
	    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String imageId = request.getParameter("id");

        if (imageId == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
            return;
        }
        
        ImageService imageService = new ImageService();
        Image image = imageService.getImageById(Integer.valueOf(imageId));

        if (image == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
            return;
        }

        // Init servlet response.
        response.reset();
        response.setContentType("image/jpeg");
        response.setContentLength(image.getPhoto().length);

        // Write image content to response.
        response.getOutputStream().write(image.getPhoto());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		processRequest(request, response);
	}

}
