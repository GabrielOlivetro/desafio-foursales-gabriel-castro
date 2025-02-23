-- Criação do banco de dados
CREATE DATABASE IF NOT EXISTS `gaba-games`;
USE `gaba-games`;

CREATE TABLE usuario (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,  
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(255) NOT NULL
);

CREATE TABLE pedido (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    usuario_id BIGINT NOT NULL,
    status VARCHAR(255) NOT NULL,
    valor_total DECIMAL(19, 2), 
    data_criacao DATETIME NOT NULL, 
    data_atualizacao DATETIME,
    CONSTRAINT FK_Pedido_Usuario FOREIGN KEY (usuario_id) REFERENCES usuario(id)
);

CREATE TABLE jogo (
    id BIGINT AUTO_INCREMENT PRIMARY KEY, 
    nome VARCHAR(255) NOT NULL UNIQUE,  
    descricao TEXT,  
    preco DECIMAL(19, 2) NOT NULL,  
    categoria VARCHAR(255),
    quantidade_em_estoque INT NOT NULL,  
    data_criacao DATETIME, 
    data_atualizacao DATETIME 
);

CREATE TABLE pedido_jogo (
    pedido_id BIGINT NOT NULL,
    jogo_id BIGINT NOT NULL,
    quantidade INT NOT NULL,
    preco DECIMAL(19, 2), 
    PRIMARY KEY (pedido_id, jogo_id), 
    CONSTRAINT FK_Pedido FOREIGN KEY (pedido_id) REFERENCES pedido(id),
    CONSTRAINT FK_Jogo FOREIGN KEY (jogo_id) REFERENCES jogo(id)
);
