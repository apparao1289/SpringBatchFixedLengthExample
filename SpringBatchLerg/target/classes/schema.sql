DROP TABLE IF EXISTS COMPANY;

DROP SEQUENCE IF EXISTS COMPANY_SEQ;

CREATE SEQUENCE COMPANY_SEQ MAXVALUE 9223372036854775807 NO CYCLE;

CREATE TABLE COMPANY  (
    COMPANY_ID INTEGER DEFAULT NEXTVAL('COMPANY_SEQ'),
    column1 VARCHAR(100),
    column2 VARCHAR(100),
    column3 VARCHAR(100),
    column4 VARCHAR(100),
    column5 VARCHAR(100),
    column6 VARCHAR(100),
    column7 VARCHAR(100),
    column8 VARCHAR(100),
    column9 VARCHAR(100),
    column10 VARCHAR(100),
    column11 VARCHAR(100),
    column12 VARCHAR(100),
    column13 VARCHAR(100),
    column14 VARCHAR(100),
    column15 VARCHAR(100),
    column16 VARCHAR(100),
    column17 VARCHAR(100)
    
);
