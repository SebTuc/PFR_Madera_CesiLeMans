-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Généré le :  Mar 26 Mars 2019 à 17:18
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

INSERT INTO `donnees_personelle` (`DONNEES_PERSONELLE_ID`, `NOM`, `PRENOM`, `EMAIL`, `ADRESSE`, `TELEPHONE`, `CODE_POSTAL`) VALUES
(1, 'Schrute', 'Dwigth', 'dwigth.schrute@madera.fr', '8 Rue des betraves', '0685321789', '59000'),
(2, 'Halpert', 'Jim', 'jim.halpert@madera.fr', '2 avenue des sésames', '0612384258', '74000'),
(3, 'Bernard', 'Andy', 'andry.bernard@madera.fr', '7 Avenue du chien costaud', '0684233489', '40100'),
(4, 'Flenderson', 'Toby', 'toby.flenderson@madera.fr', '5, Quartier du diable', '0666874934', '40100'),
(5, 'Malone', 'Kevin', 'kevin.malone@madera.fr', '3bis, Rue du chocolat', '0654785513', '74150'),
(6, 'Scott', 'Mickael', 'mickael.scott', '2bis, Boulevard des enfants', '0254778953', '59000'),
(7, 'Beesly', 'Pamela', 'pamela.beesly@madera', '8, Quartier des promesses', '0623445877', '74000'),
(8, 'Philbin', 'Daryl', 'daryl.philbin@madera.fr', '12 Résidence du ruisseaux', '0699845125', '40100');
(9, 'administrateur', 'Master', 'admin.master@madera.fr', '', '4100', '');

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
-- Contenu de la table `materiaux`
--

INSERT INTO `materiaux` (`MATERIAUX_ID`, `NOM`) VALUES
(1, 'Bois composite'),
(2, 'Bois de chêne'),
(3, 'Bois de hêtre'),
(4, 'Bois de bouleau'),
(5, 'Aluminium'),
(6, 'Acier'),
(7, 'Béton'),
(8, 'Carreau de plâtre'),
(9, 'Ciment'),
(10, 'Carrelage'),
(11, 'Granulat'),
(12, 'Géocomposite '),
(13, 'Géosynthétique'),
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
(37, 'Polyuréthane'),
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
(6, 'Logisticien');
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
(18, 'Poinçon de charpente en peuplier brute', 40, 29, 4, 39),
(19, 'Arbalètrier de charpente en bois de peuplier brute', 45, 29, 4, 39),
(20, 'Echantignolle de charpente en bois de peuplier brute', 65, 29, 4, 39),
(21, 'Chevron de charpente en bois de peuplier brute', 10, 29, 4, 39),
(22, 'Panne de charpente en bois de peuplier brute', 60, 29, 4, 39),
(23, 'Ardoise de couverture', 1, 26, 3, 28);


--
-- Contenu de la table `utilisateur`
--

INSERT INTO `utilisateur` (`UTILISATEUR_ID`, `LOGIN`, `PASSWORD`, `METIER_ID`, `DONNEES_PERSONELLE_ID`) VALUES
(1, 'dschrute', 'dschrute', 1, 1),
(2, 'jhalpert', '74000', 1, 2),
(3, 'abernard', 'abernard', 1, 3),
(4, 'tflenderson', 'tflenderson', 2, 4),
(5, 'kmalone', 'kmalone', 3, 5),
(6, 'mscott', 'mscott', 4, 6),
(7, 'pbeesly', 'pbeesly', 5, 7),
(8, 'dphilbin', 'dphilbin', 6, 8);
(9, 'admin', 'admin', 7, 9);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
