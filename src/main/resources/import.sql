-- Script para inserir alguns registros
INSERT INTO customer (id, name, email, gender) VALUES (1, 'Homem Aranha', 'aranha@vingadores.com', 'M');
INSERT INTO ADDRESS (street, city, zipcode, customer_id) VALUES ('Rua 1', 'São Paulo', '12345', 1);
INSERT INTO ADDRESS (street, city, zipcode, customer_id) VALUES ('Rua 2', 'Rio de Janeiro', '67890', 1);

INSERT INTO customer (id, name, email, gender) VALUES (2, 'Thor', 'thor@vingadores.com', 'M');
--INSERT INTO ADDRESS (street, city, zipcode, customer_id) VALUES ('Rua 3', 'Curitiba', '11111', 2);
--INSERT INTO ADDRESS (street, city, zipcode, customer_id) VALUES ('Rua 4', 'Manaus', '22222', 2);

INSERT INTO customer (id, name, email, gender) VALUES (3, 'Viuva Negra', 'viuva@vingadores.com', 'F');
INSERT INTO customer (id, name, email, gender) VALUES (4, 'Namor', 'namor@vingadores.com', 'M');
INSERT INTO customer (id, name, email, gender) VALUES (5, 'Gamora', 'gamora@vingadores.com', 'F');