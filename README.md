# ToDoApp 
-----------------------------------------------------------------

 Technology used for this app:
 
 Vaadin    : 13.0.6
 
 Java      : 1.8
 
 AppServer : wildfly-20.0.0.Final
 
 Java EE   :jakartaee -8 
 
 Bean Valiation : 2.0
 
 CDI       : 2.0
 
 EJB       : 3.2
 
 JPA       : 2.2
 
 Hibernate : 5
 
 Database  : Mysql 5/8, Postgresql
 
 IDE       : Eclipse 
 
 Maven     : 3.6
----------------------------------

 Project structure 
 1. Monolithic multimodule project structure  using maven 

  
 Todo App build : mvn clean install
 
  
 Todo App url :    http://localhost:8080/todoweb-1.0/
 
 
 
 ============================data source config for wildfy===========================
 
<subsystem xmlns="urn:jboss:domain:datasources:6.0">

<datasources>

<datasource jndi-name="java:jboss/datasource/TodoDS" pool-name="todo_ds" enabled="true" spy="true">

<connection-url>jdbc:postgresql://localhost:5432/crms</connection-url>

<driver>postgres</driver>

<pool>

 <min-pool-size>5</min-pool-size>
 
 <initial-pool-size>10</initial-pool-size>
 
 <max-pool-size>20</max-pool-size>
 
 <prefill>true</prefill>
 
</pool>

<security>

 <user-name>postgres</user-name>
 
 <password>root</password>
 
</security>

<validation>

 <valid-connection-checker class-name="org.jboss.jca.adapters.jdbc.extensions.postgres.PostgreSQLValidConnectionChecker"/>
 
 <exception-sorter class-name="org.jboss.jca.adapters.jdbc.extensions.postgres.PostgreSQLExceptionSorter"/>
 
</datasource>

<drivers>

<driver name="postgres" module="org.postgres">

 <xa-datasource-class>org.postgresql.xa.PGXADataSource</xa-datasource-class>
 
</driver>

</drivers>

</datasources>