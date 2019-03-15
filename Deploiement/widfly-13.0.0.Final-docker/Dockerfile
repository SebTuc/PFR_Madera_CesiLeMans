# Recuperation du container de base en version 13
FROM jboss/wildfly:13.0.0.Final

# Definition des variables d'environnement
ENV JBOSS_HOME /opt/jboss/wildfly

# Copie des fichiers
ADD standalone-custom.xml $JBOSS_HOME/standalone/configuration/
ADD modules $JBOSS_HOME/modules/

# Ajout d'un utilisateur admin sur l'interface de management
RUN /opt/jboss/wildfly/bin/add-user.sh admin MpJs --silent

# Commande executé lors d'un "docker run" sur l'image
CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-c", "standalone-custom.xml", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]