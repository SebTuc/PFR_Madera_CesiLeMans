package com.ril.service;

import java.util.List;

import com.ril.daoHibernate.ImageHome;
import com.ril.model.Image;

public class ImageService {
	
public int addImage(byte[] photo) {
		
		ImageHome dao = new ImageHome();
		
		if(photo != null) {
			
			Image image = new Image(photo);
			
			dao.persist(image);
			
			return image.getImageId();
			
		} else {
			
			return -1;
		}
	}
	
	public void editImage(Image image) {
		
		ImageHome dao = new ImageHome();
		
		if(image != null) {
		
			dao.merge(image);
		}
	}
	
	public void removeImageById(Integer id) {
		
		ImageHome dao = new ImageHome();
		
		if(id != null) {

			Image image = getImageById(id);
			
			dao.remove(image);
		}
	}
	public void removeImage(Image image) {

		ImageHome dao = new ImageHome();
		
		if(image != null) {
			
			dao.remove(image);
		}
	}
	
	public Image getImageById(Integer id) {
		
		ImageHome dao = new ImageHome();
		
		return dao.findById(id);
	}
	
	public List<Image> getAllImages(){
		
		ImageHome dao = new ImageHome();
		
		return dao.findAll();
	}
	

}
