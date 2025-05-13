CREATE TABLE Pessoa (
  idPessoa INT IDENTITY(1,1) NOT NULL PRIMARY KEY,
  nome VARCHAR(255) NOT NULL,
  logradouro VARCHAR(255) NOT NULL,
  cidade VARCHAR(255) NOT NULL,
  estado VARCHAR(255) NOT NULL,
  telefone VARCHAR(20) NOT NULL,
  email VARCHAR(255) NOT NULL
);
GO

CREATE TABLE PessoaFisica (
  idPessoa INT NOT NULL PRIMARY KEY,
  cpf VARCHAR(14) NOT NULL UNIQUE,
  FOREIGN KEY (idPessoa) REFERENCES Pessoa(idPessoa)
);
GO

CREATE TABLE PessoaJuridica (
  idPessoa INT NOT NULL PRIMARY KEY,
  cnpj VARCHAR(14) NOT NULL UNIQUE,
  FOREIGN KEY (idPessoa) REFERENCES Pessoa(idPessoa)
);
GO

CREATE TABLE Usuario (
  idUsuario INT IDENTITY(1,1) NOT NULL PRIMARY KEY,
  email VARCHAR(255) NOT NULL,
  senha VARCHAR(255) NOT NULL
);
GO

CREATE TABLE Produto (
  idProduto INT IDENTITY(1,1) NOT NULL PRIMARY KEY,
  nome VARCHAR(255) NOT NULL,
  quantidade INT NOT NULL,
  preco DECIMAL(10, 2) NOT NULL
);
GO

CREATE TABLE Movimentacao (
  idMovimentacao INT IDENTITY(1,1) PRIMARY KEY,
  idUsuario INT NOT NULL,
  idPessoa INT NOT NULL,
  idProduto INT NOT NULL,
  quantidade INT NOT NULL CHECK (quantidade > 0),
  tipo CHAR(1) NOT NULL CHECK (tipo IN ('E', 'S')),
  valorUnitario DECIMAL(10,2) NOT NULL CHECK (valorUnitario >= 0),
  dataMovimentacao DATETIME DEFAULT GETDATE(),

  FOREIGN KEY (idUsuario) REFERENCES Usuario(idUsuario),
  FOREIGN KEY (idPessoa) REFERENCES Pessoa(idPessoa),
  FOREIGN KEY (idProduto) REFERENCES Produto(idProduto)
);
GO