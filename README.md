# liquibase-example
Example Liquibase integrated with Spring Boot

**This solution uses Spring Boot.**

### SQL Files

#### Pattern
> The files must follow a standard that respects an order, so they can be executed properly.

Pattern:
```
SCRIPT NUMBER_DATE (mm/dd/YYYY)-DESCRIPTION.sql
```

Example:
```
001_10-22-2015-poc.sql
```

#### Changeset Control
A sql file can have one or more changeset, changeset are script blocks to be executed, and the author and the unique ID must be informed.

```sql
--liquibase formatted sql
--changeset fabio.barbosa:001.1
```

ID Pattern (ex: 001.1):
```
SCRIPT NUMBER.CHANGESET INCREMENT NUMBER
```

Every changeset must have their rollback statement below.

```sql
--rollback DROP SEQUENCE POC_SEQUENCE;
--rollback DROP TABLE POC;
```

And full file:
```sql
--liquibase formatted sql
--changeset fabio.barbosa:001.1
CREATE SEQUENCE POC_SEQUENCE MINVALUE 1 MAXVALUE 999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20;
CREATE TABLE POC (
  ID NUMBER(19) NOT NULL,
  NAME VARCHAR2(255) NULL
);
--rollback DROP SEQUENCE POC_SEQUENCE;
--rollback DROP TABLE POC;
```

#### Executions
Liquibase is included in maven cycles, so when running a clean install the *liquibase update* function will be executed. Also integrated with Spring Boot to up the application will also run.

> By default liquibase runs on the local profile as spring.profiles.active property in [application.yaml](https://github.com/gsw-team/liquibase-example/blob/master/src/main/resources/application.yaml).

#### Liquibase Plugin
> As the Spring that manages the profiles, the properties of database access for Liquibase are not in the pom.xml. So you have two options, duplicate the properties in the pom.xml, or pass as argument to the plugin while running.

*Using the plugin only happen in atypical situations, so we do not recommend duplication of properties.*

##### Drop database
```bash
mvn liquibase:dropAll -Dliquibase.url=jdbc:oracle:thin:@localhost:1521:xe -Dliquibase.username=erm_user -Dliquibase.password=erm
```

##### Update differences in database
```bash
mvn liquibase:update -Dliquibase.url=jdbc:oracle:thin:@localhost:1521:xe -Dliquibase.username=erm_user -Dliquibase.password=erm

```

##### Rollback
```bash
mvn liquibase:rollback -Dliquibase.url=jdbc:oracle:thin:@localhost:1521:xe -Dliquibase.username=erm_user -Dliquibase.password=erm -Dliquibase.rollbackCount=1
```

*Pass the amount of changeset you want to rollback*

Check how many changeset want to rollback, referring in DATABASECHANGELOG table
```sql
SELECT * FROM DATABASECHANGELOG ORDER BY DATEEXECUTED DESC;
```

##### Test Rollback
```bash
mvn liquibase:updateTestingRollback -Dliquibase.url=jdbc:oracle:thin:@localhost:1521:xe -Dliquibase.username=erm_user -Dliquibase.password=erm
```

*To test all rollback, drop database*

##### Clear checksum
When a file changes its hash changes, if you want to clear this check run

```bash
mvn liquibase:clearCheckSums -Dliquibase.url=jdbc:oracle:thin:@localhost:1521:xe -Dliquibase.username=erm_user -Dliquibase.password=erm
```
