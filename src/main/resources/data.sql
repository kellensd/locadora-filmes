CREATE TABLE IF NOT EXISTS USUARIO (
    ID_USUARIO NUMBER(10,0) NOT NULL AUTO_INCREMENT,
    EMAIL VARCHAR(100) NOT NULL,
    NOME_COMPLETO VARCHAR(255) NOT NULL,
    SENHA VARCHAR(255) NOT NULL,
    PRIMARY KEY (ID_USUARIO)
);

CREATE SEQUENCE IF NOT EXISTS USUARIO_ID_SEQ
  MINVALUE 1
  MAXVALUE 9999999999999999
  START WITH 2
  INCREMENT BY 1
  CACHE 500;

CREATE TABLE IF NOT EXISTS FILME (
    ID_FILME NUMBER(10,0) NOT NULL AUTO_INCREMENT,
    TITULO VARCHAR(150) NOT NULL,
    DIRETOR VARCHAR(255),
    QUANTIDADE INTEGER NOT NULL,
    PRIMARY KEY (ID_FILME)
);

CREATE SEQUENCE IF NOT EXISTS FILME_ID_SEQ
  MINVALUE 1
  MAXVALUE 9999999999999999
  START WITH 5
  INCREMENT BY 1
  CACHE 500;


INSERT INTO USUARIO(ID_USUARIO, EMAIL, NOME_COMPLETO, SENHA)
VALUES(1, 'kellen@gmail.com', 'Kellen Devit', '289160db0d9f39f9ae1754c4ec9c16f90b50e32e09c5fb5481ae642b3d3d1a36');


INSERT INTO FILME(ID_FILME, TITULO, DIRETOR, QUANTIDADE)
VALUES(1, 'Venom', 'Ruben Fleischer', 2);
INSERT INTO FILME(ID_FILME, TITULO, DIRETOR, QUANTIDADE)
VALUES(2, 'Homem-Aranha: Longe de Casa', 'Jon Watts', 1);
INSERT INTO FILME(ID_FILME, TITULO, DIRETOR, QUANTIDADE)
VALUES(3, 'Velozes & Furiosos: Hobbs & Shaw', 'David Leitch', 1);
INSERT INTO FILME(ID_FILME, TITULO, DIRETOR, QUANTIDADE)
VALUES(4, 'Interestelar', 'Christopher Nolan', 0);
