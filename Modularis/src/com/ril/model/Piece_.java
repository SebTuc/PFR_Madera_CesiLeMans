package com.ril.model;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Piece.class)
public class Piece_ {
	
	public static volatile SingularAttribute<Piece , Integer> pieceId;
	public static volatile SingularAttribute<Piece , Plan> plan;
	public static volatile SingularAttribute<Piece , String> nom;
	public static volatile SingularAttribute<Piece , Float> surface;
	public static volatile SetAttribute<Piece , Module> modules;

}
