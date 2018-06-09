FROM jboss/wildfly:latest

USER jboss
# This user and password are only inteded to be used in local environment.
RUN /opt/jboss/wildfly/bin/add-user.sh admin admin --silent
# Binding 0.0.0.0 is done only to be used in local environment.
CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]
