-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Généré le :  Mar 26 Mars 2019 Ã  17:18
-- Version du serveur :  5.6.17
-- Version de PHP :  5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données :  `modularisbdd`
--

--
-- Contenu de la table `angle`
--

INSERT INTO `angle` (`ANGLE_ID`, `TYPE_ANGLE`, `PRIX_UNITAIRE`) VALUES
(1, ' Angle ouvrant', 150),
(2, 'Angle sortant', 150);

--
-- Contenu de la table `angle`
--

INSERT INTO `etape_facture` (`ETAPE_FACTURE_ID`, `ETAPE`, `POURCENTAGE`, `N_ETAPE`) VALUES
(1,'Devis accepter',3,1),
(2,'Obtention du permis de construire',10,2),
(3,'Ouverture du chantier',15,3),
(4,'Achevement des fondation',25,4),
(5,'Achevement des murs',40,5),
(6,'Mise hors d''eau/hors d''air',75,6),
(7,'Achévement des travaux d''equipement',95,7),
(8,'Remise des clés',100,8);

--
-- Contenu de la table `catalogue`
--

INSERT INTO `catalogue` (`CATALOGUE_ID`, `ANNEE`, `CATALOGUE_NOM`) VALUES
(1, 2019, 'Maison écologique'),
(2, 2019, 'Maison luxieuse'),
(3, 2019, 'Petite maison'),
(5, 2019, 'Maison familial');

--
-- Contenu de la table `donnees_personelle`
--

INSERT INTO `donnees_personelle` (`DONNEES_PERSONELLE_ID`, `NOM`, `PRENOM`, `EMAIL`, `ADRESSE`, `TELEPHONE`, `CODE_POSTAL`,`VILLE`) VALUES
(1, 'Schrute', 'Dwigth', 'dwigth.schrute@madera.fr', '8 Rue des betraves', '0685321789', '59000','Lille'),
(2, 'Halpert', 'Jim', 'jim.halpert@madera.fr', '2 avenue des sésames', '0612384258', '74000' ,'Annecy'),
(3, 'Bernard', 'Andy', 'andry.bernard@madera.fr', '7 Avenue du chien costaud', '0684233489', '40100','Dax'),
(4, 'Flenderson', 'Toby', 'toby.flenderson@madera.fr', '5, Quartier du diable', '0666874934', '40100','Dax'),
(5, 'Malone', 'Kevin', 'kevin.malone@madera.fr', '3bis, Rue du chocolat', '0654785513', '74150','Annecy'),
(6, 'Jackson', 'Mickael', 'mickael.jackson', '2bis, Boulevard des enfants', '0254778953', '59000','Lille'),
(7, 'Beesly', 'Pamela', 'pamela.beesly@madera', '8, Quartier des promesses', '0623445877', '74000','Annecy'),
(8, 'Philbin', 'Daryl', 'daryl.philbin@madera.fr', '12 Résidence du ruisseaux', '0699845125', '40100','Dax'),
(9, 'administrateur', 'Master', 'admin.master@madera.fr', '123 bis rue des chatelier.', '0683573608', '74000','Annecy');


--
-- Contenu de la table `entrepot`
--

INSERT INTO `entrepot` (`ENTREPOT_ID`, `LIEUX`) VALUES
(1, 'Site de Lille'),
(2, 'Site de production de Dax'),
(3, 'Site de production d''Annecy');

--
-- Contenu de la table `famille_composant`
--

INSERT INTO `famille_composant` (`FAMILLE_COMPOSANT_ID`, `NOM`) VALUES
(1, 'Montants'),
(3, 'Panneau intérieur'),
(24, 'Elements de montage'),
(25, 'Planchers'),
(26, 'Couverture'),
(27, 'Isolation'),
(28, 'Panneau extérieur'),
(29, 'Ferme de charpente');

--
-- Contenu de la table `fournisseur`
--

INSERT INTO `fournisseur` (`FOURNISSEUR_ID`, `NOM`, `ADRESSE`, `CODE_POSTAL`, `TELEPHONE`, `EMAIL`) VALUES
(1, 'POINT P ', '61 63 av Félix Faure, 59350 SAINT ANDRÉ LEZ LILLE', '59000', '03 20 42 44 24', 'contact@pointp.fr'),
(2, 'France Matériaux', '177 r Maufait, 59100 ROUBAIX', '59100 ', '03 20 75 88 34', ''),
(3, 'Tout Faire Matériaux Lambert Matériaux ', ' zi Les Grives, 74150 MARIGNY SAINT MARCEL', '74150 ', '04 50 01 65 65', 'jlambert@gmail.com'),
(4, 'M Matériaux', ' Route Saint Pandelon 17 rte Peyrehorade, 40100 DAX', '40100 ', '05 58 74 36 50', ''),
(5, 'BigMat', '184 rue Albert Thomas, 59160 LOMME', '59160 ', '03 20 22 75 22', 'contact@lille.bimat.fr');

--
-- Contenu de la table `gamme`
--

INSERT INTO `gamme` (`GAMME_ID`, `NOM`) VALUES
(1, 'Comfort'),
(2, 'Économie d''énergie'),
(3, 'Luxe'),
(4, 'Standard');


--
-- Contenu de la table `Etat`
--

INSERT INTO `etat` (`ETAT_ID`, `NOM`) VALUES
(1, 'Brouillon'),
(2, 'Accepter'),
(3, 'En commande'),
(4, 'En facturation');


--
-- Contenu de la table `materiaux`
--

INSERT INTO `materiaux` (`MATERIAUX_ID`, `NOM`) VALUES
(1, 'Bois composite'),
(2, 'Bois de chêne'),
(3, 'Bois de hêtre'),
(4, 'Bois de bouleau'),
(5, 'Aluminium'),
(6, 'Acier'),
(7, 'Bâton'),
(8, 'Carreau de plâtre'),
(9, 'Ciment'),
(10, 'Carrelage'),
(11, 'Granulat'),
(12, 'Géocomposite '),
(13, 'Géosynthâtique'),
(14, 'Géotextile'),
(15, 'Laine de roche'),
(16, 'Laine de verre'),
(17, 'Liant papier'),
(18, 'Pavé'),
(19, 'Plaque de plâtre'),
(20, 'PVC'),
(21, 'Terre cuite'),
(22, 'Mortier'),
(23, 'Chaux'),
(24, 'Verre'),
(25, 'Plâtre'),
(26, 'Plomb'),
(27, 'Zinc'),
(28, 'Ardoise'),
(29, 'Granite'),
(30, 'Calcaire'),
(31, 'Grès'),
(32, 'Pierre meulière'),
(33, 'Marne'),
(34, 'Marbre'),
(35, 'Schiste'),
(36, 'Brique'),
(37, 'Polyurèthane'),
(38, 'Polypropylène'),
(39, 'Bois de peuplier'),
(40, 'Acier inoxidable'),
(41, 'Bois recyclé');

--
-- Contenu de la table `metier`
--

INSERT INTO `metier` (`METIER_ID`, `NOM`) VALUES
(1, 'Commercial'),
(2, 'Direction des ressources humaines'),
(3, 'Comptabilité'),
(4, 'Responsable de Bureau d''études'),
(5, 'Dessinateur'),
(6, 'Logisticien'),
(7, 'Moderateur');

--
-- Contenu de la table `unite_mesure`
--

INSERT INTO `unite_mesure` (`UNITE_MESURE_ID`, `NOM`) VALUES
(1, 'mm'),
(2, 'cm'),
(3, 'cm²'),
(4, 'cm3'),
(5, 'mètre(s)'),
(6, 'm²'),
(7, 'm3'),
(8, 'litres(s)'),
(9, 'unité(s)'),
(10, 'gramme(s)'),
(11, 'kg');

--
-- Contenu de la table `composant`
--

INSERT INTO `composant` (`COMPOSANT_ID`, `NOM`, `PRIX_UNITAIRE`, `FAMILLE_COMPOSANT_ID`, `FOURNISSEUR_ID`, `MATERIAUX_ID`) VALUES
(1, 'Montants lisses en bois de hêtre', 50, 1, 1, 3),
(2, 'Montants contrefort en bois de chêne', 75, 1, 1, 2),
(3, 'Sabot métallique', 3, 24, 2, 6),
(4, 'Boulons 35mm', 1, 24, 2, 6),
(5, 'Goujons d''ancrage', 1.5, 24, 2, 6),
(6, 'Panneau d''isolation thermique', 10, 27, 5, 37),
(7, 'Panneau pare-pluie', 35, 27, 5, 38),
(8, 'Panneau mural interieur en bois composite', 35, 3, 3, 1),
(9, 'Parquet en chêne massif', 49.99, 25, 3, 2),
(10, 'Tuiles plates simple en terre cuite', 0.8, 26, 1, 21),
(11, 'Tuile romane simple en terre cuite', 2, 26, 2, 21),
(12, 'Panneau contreplaqué extérieur en bois de peuplier', 70, 28, 1, 4),
(13, 'Panneau exterieur en inox', 110, 28, 2, 40),
(14, 'Panneau mural interieur en bois recyclé', 40, 3, 3, 41),
(15, 'Montants lisse en acier', 105, 1, 5, 6),
(16, 'Entrait de charpente en peuplier brute', 69.99, 29, 4, 39),
(17, 'Contrefiche de charpente en peuplier brute', 60, 29, 4, 39),
(18, 'Poinson de charpente en peuplier brute', 40, 29, 4, 39),
(19, 'Arbalétrier de charpente en bois de peuplier brute', 45, 29, 4, 39),
(20, 'Echantignolle de charpente en bois de peuplier brute', 65, 29, 4, 39),
(21, 'Chevron de charpente en bois de peuplier brute', 10, 29, 4, 39),
(22, 'Panne de charpente en bois de peuplier brute', 60, 29, 4, 39),
(23, 'Ardoise de couverture', 1, 26, 3, 28),
(24, 'Panneau interieur en bois de peuplier brute', 200, 29, 4, 39);


--
-- Contenu de la table `module`
--
INSERT INTO `module` (`MODULE_ID`, `NOM`, `UNITE_MESURE_ID`, `GAMME_ID`, `ANGLE_ID`) VALUES
(1,'Mur inox chambre de luxe nord 7x3',5,3,null),
(2,'Mur inox chambre de luxe sud 7x3',5,3,null),
(3,'Mur inox chambre de luxe est 8x3',5,3,null),
(4,'Mur inox chambre de luxe ouest 8x3',5,3,null),
(5,'Mur en bois chambre de ecologique nord/ouest 13x3',5,2,1),
(6,'Mur en bois chambre de ecologique sud/est 13x3',5,2,1),
(7,'Mur inox salle de bain luxe sud 8x3',5,3,null),
(8,'Mur inox salle de bain luxe nord 8x3',5,3,null),
(9,'Mur inox salle de bain luxe est 8x3',5,3,null),
(10,'Mur inox salle de bain luxe ouest 8x3',5,3,null),
(11,'Parquet luxe de 7x8 chambre',6,3,null),
(12,'Parquet luxe de 8x8 salle de bain',6,3,null),
(13,'Plafond luxe chambre 7x8',6,3,null),
(14,'Plafond luxe salle de bain 25x20',6,3,null),
(15,'Mur inox cuisine de luxe nord 13x3',5,3,null),
(16,'Mur inox cuisine de luxe sud 13x3',5,3,null),
(17,'Mur inox cuisine de luxe est 8x3',5,3,null),
(18,'Mur inox cuisine de luxe ouest 8x3',5,3,null),
(19,'Parquet luxe cuisine 13x8',6,3,null),
(20,'Toiture maison inox de luxe',6,3,null),
(21,'Plafond luxe cuisine 13x8',6,3,null);


--
-- Contenu de la table `module_x_composant`
--
INSERT INTO `module_x_composant` (`COMPOSANT_ID`,`MODULE_ID`,`QUANTITE`) VALUES
(9,19,50),
(9,11,35),
(9,12,20),
(22,20,10),
(19,20,20),
(15,20,8),
(11,20,400),
(16,20,10),
(7,20,50),
(21,21,8),
(6,21,40),
(2,21,4),
(24,21,20),
(21,13,4),
(6,13,20),
(2,13,2),
(24,13,10),
(21,14,4),
(6,14,20),
(2,14,2),
(24,14,10),
(3,11,15),
(3,21,30),
(9,21,15),
(4,1,40),
(6,1,20),
(13,1,4),
(7,1,20),
(24,1,20),
(4,2,40),
(6,2,20),
(13,2,4),
(7,2,20),
(24,2,20),
(4,3,40),
(6,3,20),
(13,3,4),
(7,3,20),
(24,3,20),
(4,4,40),
(6,4,20),
(13,4,4),
(7,4,20),
(24,4,20),
(4,15,40),
(6,15,20),
(13,15,4),
(7,15,20),
(24,15,20),
(4,16,40),
(6,16,20),
(13,16,4),
(7,16,20),
(24,16,20),
(4,17,40),
(6,17,20),
(13,17,4),
(7,17,20),
(24,17,20),
(4,18,40),
(6,18,20),
(13,18,4),
(7,18,20),
(24,18,20),
(4,7,40),
(6,7,20),
(13,7,4),
(7,7,20),
(24,7,20),
(4,8,40),
(6,8,20),
(13,8,4),
(7,8,20),
(24,8,20),
(4,9,40),
(6,9,20),
(13,9,4),
(7,9,20),
(24,9,20),
(4,10,40),
(6,10,20),
(13,10,4),
(7,10,20),
(24,10,20);

--
-- Contenu de la table `utilisateur`
--

INSERT INTO `utilisateur` (`UTILISATEUR_ID`, `LOGIN`, `PASSWORD`, `METIER_ID`, `DONNEES_PERSONELLE_ID`,`ENTREPOT_ID`) VALUES
(1, 'dschrute', 'dschrute', 1, 1,1),
(2, 'jhalpert', '74000', 1, 2,1),
(3, 'abernard', 'abernard', 1, 3,3),
(4, 'tflenderson', 'tflenderson', 2, 4,2),
(5, 'kmalone', 'kmalone', 3, 5,2),
(6, 'mjackson', 'mjackson', 4, 6,1),
(7, 'pbeesly', 'pbeesly', 5, 7,3),
(8, 'dphilbin', 'dphilbin', 6, 8,1),
(9, 'admin', 'admin', 7, 9,1);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

--
-- Contenu de la table `stock`
--	
INSERT INTO `stock` (`QUANTITE`, `COMPOSANT_ID`, `ENTREPOT_ID`) VALUES 
( '10', '1', '1'),
( '25', '2', '1'),
( '2', '3', '1'),
( '19', '4', '1'),
( '56', '5', '1'),
( '45', '6', '1'),
( '15', '7', '1'),
( '28', '8', '1'),
( '20', '9', '1'),
( '12', '10', '1'),
( '3', '11', '1'),
( '72', '12', '1'),
( '47', '13', '1'),
( '33', '14', '1'),
( '29', '15', '1'),
( '10', '1', '2'),
( '25', '2', '2'),
( '55', '23', '2'),
( '19', '4', '2'),
( '56', '5', '2'),
( '45', '18', '2'),
( '15', '21', '2'),
( '28', '8', '2'),
( '20', '9', '2'),
( '12', '10', '2'),
( '15', '11', '2'),
( '72', '12', '2'),
( '47', '13', '2'),
( '33', '14', '2'),
( '29', '15', '2'),
( '0', '1', '3'),
( '25', '2', '3'),
( '68', '23', '3'),
( '36', '4', '3'),
( '15', '5', '3'),
( '19', '18', '3'),
( '87', '7', '3');

