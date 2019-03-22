-- DROP DATABASE ModularisBDD;
CREATE DATABASE ModularisBDD;
USE ModularisBDD;


CREATE TABLE DEVIS(
        DEVIS_ID       int (11) Auto_increment  NOT NULL ,
        PRIX_HT        Float NOT NULL ,
        DATE_CREATION  Date NOT NULL ,
        PROJET_ID      Int NOT NULL ,
        ETAT_ID        Int NOT NULL ,
        UTILISATEUR_ID Int NOT NULL ,
        CLIENT_ID      Int NOT NULL ,
        PRIMARY KEY (DEVIS_ID )
)ENGINE=InnoDB;

CREATE TABLE PROJET(
        PROJET_ID int (11) Auto_increment  NOT NULL ,
        NOM       Varchar (255) NOT NULL ,
        IMAGE_ID  Int NOT NULL ,
        PRIMARY KEY (PROJET_ID )
)ENGINE=InnoDB;


CREATE TABLE PLAN(
        PLAN_ID   int (11) Auto_increment  NOT NULL ,
        NOM       Varchar (255) NOT NULL ,
        PROJET_ID Int NOT NULL ,
        PRIMARY KEY (PLAN_ID )
)ENGINE=InnoDB;

CREATE TABLE MODULE(
        MODULE_ID       int (11) Auto_increment  NOT NULL ,
        NOM             Varchar (25) NOT NULL ,
        VALEUR_ANGLE    Int ,
        ANGLE_ID        Int NOT NULL ,
        GAMME_ID        Int NOT NULL ,
        UNITE_MESURE_ID Int NOT NULL ,
        PRIMARY KEY (MODULE_ID )
)ENGINE=InnoDB;


CREATE TABLE COMPOSANT(
        COMPOSANT_ID         int (11) Auto_increment  NOT NULL ,
        NOM                  Varchar (100) NOT NULL ,
        PRIX_UNITAIRE        Float NOT NULL ,
        FAMILLE_COMPOSANT_ID Int NOT NULL ,
        FOURNISSEUR_ID       Int NOT NULL ,
        MATERIAUX_ID         Int NOT NULL ,
        PRIMARY KEY (COMPOSANT_ID )
)ENGINE=InnoDB;

CREATE TABLE MATERIAUX(
        MATERIAUX_ID int (11) Auto_increment  NOT NULL ,
        NOM          Varchar (100) NOT NULL ,
        PRIMARY KEY (MATERIAUX_ID )
)ENGINE=InnoDB;


CREATE TABLE PIECE(
        PIECE_ID int (11) Auto_increment  NOT NULL ,
        NOM      Varchar (255) NOT NULL ,
        SURFACE  Float NOT NULL ,
        PLAN_ID  Int NOT NULL ,
        PRIMARY KEY (PIECE_ID )
)ENGINE=InnoDB;

CREATE TABLE FAMILLE_COMPOSANT(
        FAMILLE_COMPOSANT_ID int (11) Auto_increment  NOT NULL ,
        NOM                  Varchar (255) NOT NULL ,
        PRIMARY KEY (FAMILLE_COMPOSANT_ID )
)ENGINE=InnoDB;

CREATE TABLE STOCK(
        STOCK_ID    int (11) Auto_increment  NOT NULL ,
        QUANTITE    Int NOT NULL ,
        ENTREPOT_ID Int NOT NULL ,
        PRIMARY KEY (STOCK_ID )
)ENGINE=InnoDB;


CREATE TABLE ENTREPOT(
        ENTREPOT_ID int (11) Auto_increment  NOT NULL ,
        LIEUX       Varchar (255) NOT NULL ,
        PRIMARY KEY (ENTREPOT_ID )
)ENGINE=InnoDB;


CREATE TABLE FOURNISSEUR(
        FOURNISSEUR_ID int (11) Auto_increment  NOT NULL ,
        NOM            Varchar (100) NOT NULL ,
        ADRESSE        Varchar (255) NOT NULL ,
        CODE_POSTAL    Varchar (25) NOT NULL ,
        TELEPHONE      Varchar (25) ,
        EMAIL          Varchar (100) ,
        PRIMARY KEY (FOURNISSEUR_ID )
)ENGINE=InnoDB;

CREATE TABLE FACTURE(
        FACTURE_ID  int (11) Auto_increment  NOT NULL ,
        ETAPE       Varchar (100) NOT NULL ,
        POURCENTAGE Int NOT NULL ,
        DEVIS_ID    Int NOT NULL ,
        PRIMARY KEY (FACTURE_ID )
)ENGINE=InnoDB;


CREATE TABLE UTILISATEUR(
        UTILISATEUR_ID        int (11) Auto_increment  NOT NULL ,
        LOGIN                 Varchar (50) NOT NULL ,
        PASSWORD              Varchar (50) NOT NULL ,
        METIER_ID             Int NOT NULL ,
        DONNEES_PERSONELLE_ID Int NOT NULL ,
        PRIMARY KEY (UTILISATEUR_ID )
)ENGINE=InnoDB;


CREATE TABLE CLIENT(
        CLIENT_ID             int (11) Auto_increment  NOT NULL ,
        DONNEES_PERSONELLE_ID Int NOT NULL ,
        PRIMARY KEY (CLIENT_ID )
)ENGINE=InnoDB;

CREATE TABLE METIER(
        METIER_ID int (11) Auto_increment  NOT NULL ,
        NOM       Varchar (50) NOT NULL ,
        PRIMARY KEY (METIER_ID )
)ENGINE=InnoDB;

CREATE TABLE ETAT(
        ETAT_ID int (11) Auto_increment  NOT NULL ,
        NOM     Varchar (25) NOT NULL ,
        PRIMARY KEY (ETAT_ID )
)ENGINE=InnoDB;

CREATE TABLE ANGLE(
        ANGLE_ID      int (11) Auto_increment  NOT NULL ,
        TYPE_ANGLE    Varchar (25) NOT NULL ,
        PRIX_UNITAIRE Float NOT NULL ,
        PRIMARY KEY (ANGLE_ID )
)ENGINE=InnoDB;

CREATE TABLE GAMME(
        GAMME_ID int (11) Auto_increment  NOT NULL ,
        NOM      Varchar (100) NOT NULL ,
        PRIMARY KEY (GAMME_ID )
)ENGINE=InnoDB;

CREATE TABLE DONNEES_PERSONELLE(
        DONNEES_PERSONELLE_ID int (11) Auto_increment  NOT NULL ,
        NOM                   Varchar (50) NOT NULL ,
        PRENOM                Varchar (50) NOT NULL ,
        EMAIL                 Varchar (100) ,
        ADRESSE               Varchar (250) ,
        TELEPHONE             Varchar (25) ,
        CODE_POSTAL           Varchar (25) ,
        PRIMARY KEY (DONNEES_PERSONELLE_ID )
)ENGINE=InnoDB;

CREATE TABLE CATALOGUE(
        CATALOGUE_ID  int (11) Auto_increment  NOT NULL ,
        ANNEE         Int NOT NULL ,
        CATALOGUE_NOM Varchar (50) NOT NULL ,
        PRIMARY KEY (CATALOGUE_ID )
)ENGINE=InnoDB;

CREATE TABLE UNITE_MESURE(
        UNITE_MESURE_ID int (11) Auto_increment  NOT NULL ,
        NOM             Varchar (25) NOT NULL ,
        PRIMARY KEY (UNITE_MESURE_ID )
)ENGINE=InnoDB;

CREATE TABLE IMAGE(
        IMAGE_ID int (11) Auto_increment  NOT NULL ,
        PHOTO    Blob NOT NULL ,
        PRIMARY KEY (IMAGE_ID )
)ENGINE=InnoDB;

CREATE TABLE COMPOSANT_X_STOCK(
        COMPOSANT_ID Int NOT NULL ,
        STOCK_ID     Int NOT NULL ,
        PRIMARY KEY (COMPOSANT_ID ,STOCK_ID )
)ENGINE=InnoDB;

CREATE TABLE MODULE_X_COMPOSANT(
        QUANTITE     Int NOT NULL ,
        COMPOSANT_ID Int NOT NULL ,
        MODULE_ID    Int NOT NULL ,
        PRIMARY KEY (COMPOSANT_ID ,MODULE_ID )
)ENGINE=InnoDB;

CREATE TABLE PIECE_X_MODULE(
        MODULE_ID Int NOT NULL ,
        PIECE_ID  Int NOT NULL ,
        PRIMARY KEY (MODULE_ID ,PIECE_ID )
)ENGINE=InnoDB;

CREATE TABLE PROJET_X_CATALOGUE(
        CATALOGUE_ID Int NOT NULL ,
        PROJET_ID    Int NOT NULL ,
        PRIMARY KEY (CATALOGUE_ID ,PROJET_ID )
)ENGINE=InnoDB;

ALTER TABLE DEVIS ADD CONSTRAINT FK_DEVIS_PROJET_ID FOREIGN KEY (PROJET_ID) REFERENCES PROJET(PROJET_ID);
ALTER TABLE DEVIS ADD CONSTRAINT FK_DEVIS_ETAT_ID FOREIGN KEY (ETAT_ID) REFERENCES ETAT(ETAT_ID);
ALTER TABLE DEVIS ADD CONSTRAINT FK_DEVIS_UTILISATEUR_ID FOREIGN KEY (UTILISATEUR_ID) REFERENCES UTILISATEUR(UTILISATEUR_ID);
ALTER TABLE DEVIS ADD CONSTRAINT FK_DEVIS_CLIENT_ID FOREIGN KEY (CLIENT_ID) REFERENCES CLIENT(CLIENT_ID);
ALTER TABLE PROJET ADD CONSTRAINT FK_PROJET_IMAGE_ID FOREIGN KEY (IMAGE_ID) REFERENCES IMAGE(IMAGE_ID);
ALTER TABLE PLAN ADD CONSTRAINT FK_PLAN_PROJET_ID FOREIGN KEY (PROJET_ID) REFERENCES PROJET(PROJET_ID);
ALTER TABLE MODULE ADD CONSTRAINT FK_MODULE_ANGLE_ID FOREIGN KEY (ANGLE_ID) REFERENCES ANGLE(ANGLE_ID);
ALTER TABLE MODULE ADD CONSTRAINT FK_MODULE_GAMME_ID FOREIGN KEY (GAMME_ID) REFERENCES GAMME(GAMME_ID);
ALTER TABLE MODULE ADD CONSTRAINT FK_MODULE_UNITE_MESURE_ID FOREIGN KEY (UNITE_MESURE_ID) REFERENCES UNITE_MESURE(UNITE_MESURE_ID);
ALTER TABLE COMPOSANT ADD CONSTRAINT FK_COMPOSANT_FAMILLE_COMPOSANT_ID FOREIGN KEY (FAMILLE_COMPOSANT_ID) REFERENCES FAMILLE_COMPOSANT(FAMILLE_COMPOSANT_ID);
ALTER TABLE COMPOSANT ADD CONSTRAINT FK_COMPOSANT_FOURNISSEUR_ID FOREIGN KEY (FOURNISSEUR_ID) REFERENCES FOURNISSEUR(FOURNISSEUR_ID);
ALTER TABLE COMPOSANT ADD CONSTRAINT FK_COMPOSANT_MATERIAUX_ID FOREIGN KEY (MATERIAUX_ID) REFERENCES MATERIAUX(MATERIAUX_ID);
ALTER TABLE PIECE ADD CONSTRAINT FK_PIECE_PLAN_ID FOREIGN KEY (PLAN_ID) REFERENCES PLAN(PLAN_ID);
ALTER TABLE STOCK ADD CONSTRAINT FK_STOCK_ENTREPOT_ID FOREIGN KEY (ENTREPOT_ID) REFERENCES ENTREPOT(ENTREPOT_ID);
ALTER TABLE FACTURE ADD CONSTRAINT FK_FACTURE_DEVIS_ID FOREIGN KEY (DEVIS_ID) REFERENCES DEVIS(DEVIS_ID);
ALTER TABLE UTILISATEUR ADD CONSTRAINT FK_UTILISATEUR_METIER_ID FOREIGN KEY (METIER_ID) REFERENCES METIER(METIER_ID);
ALTER TABLE UTILISATEUR ADD CONSTRAINT FK_UTILISATEUR_DONNEES_PERSONELLE_ID FOREIGN KEY (DONNEES_PERSONELLE_ID) REFERENCES DONNEES_PERSONELLE(DONNEES_PERSONELLE_ID);
ALTER TABLE CLIENT ADD CONSTRAINT FK_CLIENT_DONNEES_PERSONELLE_ID FOREIGN KEY (DONNEES_PERSONELLE_ID) REFERENCES DONNEES_PERSONELLE(DONNEES_PERSONELLE_ID);
ALTER TABLE COMPOSANT_X_STOCK ADD CONSTRAINT FK_COMPOSANT_X_STOCK_COMPOSANT_ID FOREIGN KEY (COMPOSANT_ID) REFERENCES COMPOSANT(COMPOSANT_ID);
ALTER TABLE COMPOSANT_X_STOCK ADD CONSTRAINT FK_COMPOSANT_X_STOCK_STOCK_ID FOREIGN KEY (STOCK_ID) REFERENCES STOCK(STOCK_ID);
ALTER TABLE MODULE_X_COMPOSANT ADD CONSTRAINT FK_MODULE_X_COMPOSANT_COMPOSANT_ID FOREIGN KEY (COMPOSANT_ID) REFERENCES COMPOSANT(COMPOSANT_ID);
ALTER TABLE MODULE_X_COMPOSANT ADD CONSTRAINT FK_MODULE_X_COMPOSANT_MODULE_ID FOREIGN KEY (MODULE_ID) REFERENCES MODULE(MODULE_ID);
ALTER TABLE PIECE_X_MODULE ADD CONSTRAINT FK_PIECE_X_MODULE_MODULE_ID FOREIGN KEY (MODULE_ID) REFERENCES MODULE(MODULE_ID);
ALTER TABLE PIECE_X_MODULE ADD CONSTRAINT FK_PIECE_X_MODULE_PIECE_ID FOREIGN KEY (PIECE_ID) REFERENCES PIECE(PIECE_ID);
ALTER TABLE PROJET_X_CATALOGUE ADD CONSTRAINT FK_PROJET_X_CATALOGUE_CATALOGUE_ID FOREIGN KEY (CATALOGUE_ID) REFERENCES CATALOGUE(CATALOGUE_ID);
ALTER TABLE PROJET_X_CATALOGUE ADD CONSTRAINT FK_PROJET_X_CATALOGUE_PROJET_ID FOREIGN KEY (PROJET_ID) REFERENCES PROJET(PROJET_ID);

