DROP TABLE IF EXISTS Card;

CREATE TABLE Card (ID VARCHAR(42) PRIMARY KEY, EMBOSSING_LINE_1 VARCHAR(40) NOT NULL, CARD_NUMBER VARCHAR(21) NOT NULL, EXPIRATION_DATE DATE NOT NULL, CVV VARCHAR(3) NOT NULL);