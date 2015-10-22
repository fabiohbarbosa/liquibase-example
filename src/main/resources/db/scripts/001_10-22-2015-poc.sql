--liquibase formatted sql
--changeset fabio.barbosa:001.1
CREATE SEQUENCE POC_SEQUENCE MINVALUE 1 MAXVALUE 999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20;
CREATE TABLE POC (
  ID NUMBER(19) NOT NULL,
  NAME VARCHAR2(255) NULL
);
--rollback DROP SEQUENCE POC_SEQUENCE;
--rollback DROP TABLE POC;

--liquibase formatted sql
--changeset fabio.barbosa:001.2 context:test
INSERT INTO POC VALUES(POC_SEQUENCE.nextval, 'Poc Test 1');
INSERT INTO POC VALUES(POC_SEQUENCE.nextval, 'Poc Test 2');
--rollback DELETE FROM POC WHERE NAME in('Poc Test 1', 'Poc Test 2');