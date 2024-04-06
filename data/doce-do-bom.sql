CREATE DATABASE doceDoBom;

USE doceDoBom;

CREATE TABLE funcionario (
    cdFunc INTEGER PRIMARY KEY AUTO_INCREMENT,
    cdOperador INTEGER,
    cdSupervisor INTEGER,
    nome VARCHAR(100),
    cpf VARCHAR(20),
    cep VARCHAR(15),
    rua VARCHAR(80),
    numero VARCHAR(5),
    bairro VARCHAR(50),
    cidade VARCHAR(80),
    estado VARCHAR(2),
    complemento VARCHAR(90),
    dtNascimento VARCHAR(12),
    telFixo VARCHAR(20),
    telCel VARCHAR(20),
    telEmerg VARCHAR(20),
    nomeTelEmerg VARCHAR(100),
    emailPessoal VARCHAR(120),
    emailInst VARCHAR(120),
    hrEntrada VARCHAR(14),
    hrSaida VARCHAR(14),
    observacao VARCHAR(300)
);

CREATE TABLE cliente (
    cod_cliente INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100),
    senha VARCHAR(50),
    email VARCHAR(120),
    cpf VARCHAR(20),
    foto BLOB,
    rua VARCHAR(80),
    numero VARCHAR(5),
    complemento VARCHAR(90),
    bairro VARCHAR(50),
    cidade VARCHAR(80),
    estado VARCHAR(2),
    cep VARCHAR(15),
    dt_nascimento VARCHAR(14),
    tel_fixo VARCHAR(16),
    tel_celular VARCHAR(16)
);

CREATE TABLE produto (
    cdProd INTEGER PRIMARY KEY AUTO_INCREMENT,
    cdOperador INTEGER,
    nome VARCHAR(50),
    quantidade INTEGER,
    vlUnit DECIMAL(7 , 2 ),
    descricao VARCHAR(400),
    FOREIGN KEY (cdOperador)
        REFERENCES funcionario (cdFunc)
);

CREATE TABLE item_favorito (
    cod_item_favo INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
    cod_cliente INTEGER,
    cdProd INTEGER,

    -- RELACIONAMENTO
    FOREIGN KEY (cod_cliente)
        REFERENCES cliente (cod_cliente),
    FOREIGN KEY (cdProd)
        REFERENCES produto (cdProd)
);

CREATE TABLE venda (
    numVenda INTEGER PRIMARY KEY AUTO_INCREMENT,
    cdFunc INTEGER,
    cod_cliente INTEGER,
    cdProduto INTEGER,
    dtVenda VARCHAR(50),
    quantidade INTEGER,
    vlUnit DECIMAL(7 , 2 ),
    totCompra DECIMAL(7 , 2 ),
    FOREIGN KEY (cdFunc)
        REFERENCES funcionario (cdFunc),
    FOREIGN KEY (cod_cliente)
        REFERENCES cliente (cod_cliente)
);

CREATE TABLE itemVenda (
    cdItem INTEGER PRIMARY KEY AUTO_INCREMENT,
    numVenda INTEGER,
    cdProd INTEGER,
    vlUnit DECIMAL(7 , 2 ),
    quantidade DECIMAL(7 , 2 ),
    FOREIGN KEY (numVenda)
        REFERENCES venda (numVenda),
    FOREIGN KEY (cdProd)
        REFERENCES produto (cdProd)
);

SELECT * FROM cliente;
