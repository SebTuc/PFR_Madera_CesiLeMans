package com.ril.servlet;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.ril.model.Image;
import com.ril.service.ImageService;
import com.ril.service.ProjetService;

/**
 * Servlet implementation class CreerProjet
 */
@WebServlet("/CreerProjet")
public class CreerProjet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ImageService imageService = new ImageService();
	private ProjetService projetService = new ProjetService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.getRequestDispatcher("/jsp/application/DevisProjetFacture/CreerProjet.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String nomProjet=null;

		if (!ServletFileUpload.isMultipartContent(request)) {
			request.setAttribute("Erreur", "Aucune saisie.");
			doGet(request, response);
			return;
		}

		FileItemFactory itemfactory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(itemfactory);
		
		try {

			List<FileItem> items = upload.parseRequest(request);
			for (FileItem item : items) {
				if(item.getFieldName().equals("nomProjet")) {
					//On recup le nom du projet
					if(items.get(1).getSize() == 0) {
						nomProjet = item.getString();
						projetService.addProjet(nomProjet);
						response.sendRedirect("/Modularis/DevisFacture/ListProjet");
						return;
					}else {
						nomProjet = item.getString();
						continue;
					}
				}
				String contentType = item.getContentType();
				if (!contentType.equals("image/png") && !contentType.equals("image/jpeg")) {
					request.setAttribute("Erreur", "Ne peut contenir uniquement des fichiers images");
					doGet(request, response);
					return;
				}

				File uploadDir = new File(System.getProperty("java.io.tmpdir"));
				File file = File.createTempFile("img", ".png", uploadDir);
				item.write(file);

				if(file.length() < 9000000){

					byte[] array = Files.readAllBytes(file.toPath());
					Integer imageId = imageService.addImage(array);
					Image image = imageService.getImageById(imageId);


					projetService.addProjet(nomProjet,image);
					response.sendRedirect("/Modularis/DevisFacture/ListProjet");

				}else {
					request.setAttribute("Erreur", "Taille de l'image supérieur a la limite maximal (9Mo).");
					doGet(request, response);

				}
			}
		} catch (FileUploadException e) {
			request.setAttribute("Erreur", "Erreur lors de l'ajout des données.");
			doGet(request, response);
		} catch (Exception ex) {
			request.setAttribute("Erreur", "Une erreur est survenue.");
			doGet(request, response);
		}

	}

}
