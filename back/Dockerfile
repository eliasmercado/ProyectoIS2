FROM njmittet/alpine-wildfly:10.1.0.Final

RUN /opt/jboss/wildfly/bin/add-user.sh admin password --silent

CMD /opt/jboss/wildfly/bin/standalone.sh --server-config=standalone.xml -bmanagement=0.0.0.0 -b 0.0.0.0
