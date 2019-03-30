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
import com.ril.model.Projet;
import com.ril.service.ImageService;
import com.ril.service.ProjetService;

/**
 * Servlet implementation class EditProjetName
 */
@WebServlet("/EditProjetName")
public class EditProjetName extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ProjetService projetService = new ProjetService();
	private ImageService imageService = new ImageService();

	private static boolean isInteger(String s) {
		try { 
			Integer.parseInt(s); 
		} catch(NumberFormatException e) { 
			return false; 
		} catch(NullPointerException e) {
			return false;
		}
		// only got here if we didn't return false
		return true;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String idProjet = request.getParameter("id");
		if(idProjet != null) {
			if(isInteger(idProjet)) {
				Projet projet = projetService.getProjetById(Integer.valueOf(idProjet));
				request.setAttribute("Projet", projet);

				if(projet.getImage() != null) {

					request.setAttribute("TakeImage", true);

				}else {

					request.setAttribute("TakeImage", false);
				}

				request.getRequestDispatcher("/jsp/application/DevisProjetFacture/EditProjetName.jsp").forward(request, response);
			}else {
				response.sendRedirect(request.getContextPath()+ "/DevisFacture/ListProjet");
			}
		}else {
			response.sendRedirect(request.getContextPath()+ "/DevisFacture/ListProjet");
		}

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nomProjet=null;
		String id=null;
		byte[] array=null;
		String btnType=null;


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

					nomProjet = item.getString();
					if(nomProjet!=null && !(nomProjet.equals(""))) {

						continue;
					}else {
						request.setAttribute("Erreur", "Veuillez saisir un nom de projet");
						doGet(request, response);
						return;
					}


				}else if(item.getFieldName().equals("id")) {
					//On recup l'id du projet
					id=item.getString();
					if(!isInteger(id)) {
						request.setAttribute("Erreur", "Id projet incorrect");
						doGet(request, response);
						return;
					} 
					continue;
				}else if(item.getFieldName().equals("customFile")){
					if(!item.getString().equals("") && item.getString() != null) {
						String contentType = item.getContentType();
						if (!contentType.equals("image/png") && !contentType.equals("image/jpeg")) {
							request.setAttribute("Erreur", "Ne peut contenir uniquement des fichiers images");
							doGet(request, response);
							return;
						}

						File uploadDir = new File(System.getProperty("java.io.tmpdir"));
						File file = File.createTempFile("img", ".png", uploadDir);
						item.write(file);

						if(file.length() < 10000000){

							array = Files.readAllBytes(file.toPath());
						}else {
							request.setAttribute("Erreur", "Taille de l'image supérieur a la limite maximal (1Go).");
							doGet(request, response);

						}
					}

				}else if(item.getFieldName().equals("supprImage")) {

					btnType = "supprImage";


				}else if(item.getFieldName().equals("submitProjet")) {

					btnType = "submitProjet";
				}

			}
		} catch (FileUploadException e) {
			request.setAttribute("Erreur", "Erreur lors de l'ajout des données.");
			doGet(request, response);
		} catch (Exception ex) {
			request.setAttribute("Erreur", "Une erreur est survenue.");
			doGet(request, response);
		}

		if(btnType.equals("submitProjet")) {

			try {
				Projet projet = projetService.getProjetById(Integer.valueOf(id));

				if(array!=null) {
					Integer imageId = imageService.addImage(array);
					Image image = imageService.getImageById(imageId);

					projet.setImage(image);
				}
				Boolean flag = projet.isClone();
				if(flag == null || flag != false) {
					if(!nomProjet.equals(projet.getNom())) {
						//Si le nom est modifier on supprime le Is Clone
						projet.setClone(null);
					}
					
				}
				
				projet.setNom(nomProjet);

				projetService.editProjet(projet);

				response.sendRedirect("/Modularis/DevisFacture/ListProjet");
			}catch(Exception e) {
				request.setAttribute("Erreur", "Erreur lors de l'edition du projet");
				doGet(request, response);
			}

		}else if(btnType.equals("supprImage")){

			try {
				Projet projet = projetService.getProjetById(Integer.valueOf(id));

				projet.setImage(null);

				projetService.editProjet(projet);
				
				request.setAttribute("Erreur", "Suppresion de l'image OK.");
				doGet(request, response);

			}catch(Exception e) {
				request.setAttribute("Erreur", "Suppression abort.");
				doGet(request, response);
				return;
			}
		}
	}

}
