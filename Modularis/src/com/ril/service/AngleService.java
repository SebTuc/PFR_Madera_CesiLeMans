package com.ril.service;

import java.util.List;

import com.ril.daoHibernate.AngleHome;
import com.ril.model.Angle;

public class AngleService {
	
	public int addAngle(String typeAngle, float prixUnitaire) {

		AngleHome dao = new AngleHome();

		if(typeAngle != null) {

			Angle angle = new Angle();
			
			angle.setTypeAngle(typeAngle);
			angle.setPrixUnitaire(prixUnitaire);

			dao.persist(angle);

			return angle.getAngleId();

		} else {

			return -1;
		}
	}

	public void editAngle(Angle angle) {

		AngleHome dao = new AngleHome();

		if(angle != null) {

			dao.merge(angle);
		}
	}

	public void removeAngleById(Integer id) {

		AngleHome dao = new AngleHome();

		if(id != null) {

			Angle angle = getAngleById(id);

			dao.remove(angle);
		}
	}
	public void removeAngle(Angle angle) {

		AngleHome dao = new AngleHome();

		if(angle != null) {

			dao.remove(angle);
		}
	}

	public Angle getAngleById(Integer id) {

		AngleHome dao = new AngleHome();

		return dao.findById(id);
	}

	public List<Angle> getAllAngles(){

		AngleHome dao = new AngleHome();

		return dao.findAll();
	}
}
