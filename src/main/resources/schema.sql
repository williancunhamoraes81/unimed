-- Script para criar a tabela CUSTOMER
CREATE TABLE CUSTOMER (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    gender VARCHAR(10) NOT NULL
);

-- Script para criar a tabela ADDRESS
CREATE TABLE ADDRESS (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    street VARCHAR(255),
    city VARCHAR(255),
    zipCode VARCHAR(20),
    customer_id BIGINT,
    FOREIGN KEY (customer_id) REFERENCES CUSTOMER(id)
);