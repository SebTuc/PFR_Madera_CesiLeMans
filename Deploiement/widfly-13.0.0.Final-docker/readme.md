
# Serveur Wildfly version 13.0.0.Final avec Docker

### Installation
* Telecharger les documents
* Pour windows : lancer Docker Toolbox et ce rendre sur les fichiers via l'invité de commande
* Aller à l'emplacement du fichier créé puis saisir :
    docker build --rm --tag jboss/wildfly:13.0.0.Final 'directory "$0"'
* ou
    cd.. && docker build --rm --tag jboss/wildfly:13.0.0.Final nom_du_fichier
* Lancer avec la commande :
    docker run -p 8080:8080 -p 9990:9990 -it --rm jboss/wildfly:13.0.0.Final
* Deployer le .war via l'adresse [localhost:9990](http://localhost:9990) (login: admin; mdp: MpJs)

### Procédure d'installation "From scratch"

* Installer Docker (voir doc)
* Lancer un shell Docker
* Récuperer l'image avec "docker pull jboss/wildfly:13.0.0.Final" (companyName/imageName:versionTag)
* Vérifier que l'image est installer avec "docker images"

    docker run -it jboss/wildfly:13.0.0.Final

*(-i: Laisse la possibilité de lancer de action "STDIN" même lorsque le container est détaché ; -t: Alloue un pseudo-tty (terminal emulé par une appli))*


Dans un fichier :
* Creer une standalone-custom.xml configurer avec le connector
* Creer l'arborescence de fichier \modules\system\layers\base\com\mysql\main
* Dans le fichier "main", ajouter le .jar du connector avec le module.xml
* Verifier que la version de mysql-connector correspond a celle decrite dans le module.xml
* Creer un fichier nommé "Dockerfile" sans extension avec à l'interieur les commandes suivantes: 

    # Recuperation du container de base en version 13
    FROM jboss/wildfly:13.0.0.Final

    # Definition des variables d'environnement
    ENV JBOSS_HOME /opt/jboss/wildfly

    # Copie des fichiers
    ADD standalone-custom.xml $JBOSS_HOME/standalone/configuration/
    ADD modules $JBOSS_HOME/modules/

    
    # Ajout d'un utilisateur admin sur l'interface de management
    RUN /opt/jboss/wildfly/bin/add-user.sh admin admin_pass --silent

    # Commande executé lors d'un "docker run" sur l'image
    CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-c", "standalone-custom.xml", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]

Dans docker :
* Aller à l'emplacement du fichier créé puis saisir :
    docker build --rm --tag jboss/wildfly:13.0.0.Final 'directory "$0"'
ou
    cd.. && docker build --rm --tag jboss/wildfly:13.0.0.Final nom_du_fichier 
*(--rm: Ne sauvegarde pas le container créé; --tag: specifie le nom de l'image à build)*
    
* Puis tester avec :
    docker run -p 8080:8080 -p 9990:9990 -it --rm jboss/wildfly:13.0.0.Final
*(-p: Definit les ports à ouvrir {portInterne:portExterne})*

### Avec Docker ToolBox (Windows)

Pour ouvrir les ports du container :
* Aller sur VirtualBox -> box défaut -> Parametre -> Reseau
* Choisir NAT
* Ouvrir Avancé
* Clicker sur Port Forwarding
* Ajouter une nouvelle règle avec le port hote et guest à ouvrir
* Clicker sur OK, OK
* Redemarrer la box

Pour voir les services en écoute
* Dans un invité de commande :
    netstat -an | find /i "LISTENING"

**Si l'ip des container n'est pas localhost**, pour connaitre l'ip, chercher **Carte Ethernet VirtualBox** via la commande :
    ipconfig

### Liens

* [Docker Hub des differentes versions de Widfly](https://hub.docker.com/r/jboss/wildfly/tags)