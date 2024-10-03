use casamento;

CREATE TABLE locais (
    id INT AUTO_INCREMENT PRIMARY KEY,
    municipio VARCHAR(50) NOT NULL,
    uf CHAR(2) NOT NULL,
    valor FLOAT NOT NULL,
    quantidade_max_pessoas SMALLINT NOT NULL,
    rua VARCHAR(50) NOT NULL,
    bairro VARCHAR(50) NOT NULL,
    numero VARCHAR(10) NOT NULL,
    complemento VARCHAR(40),
    descricao VARCHAR(400)
);


INSERT INTO locais (municipio, uf, valor, quantidade_max_pessoas, rua, bairro, numero, complemento)
VALUES
('São Paulo', 'SP', 5000.00, 200, 'Avenida Paulista', 'Centro', '123', 'Apartamento 101'),
('Rio de Janeiro', 'RJ', 7000.00, 150, 'Rua Copacabana', 'Copacabana', '456', ''),
('Belo Horizonte', 'MG', 4000.00, 100, 'Avenida Afonso Pena', 'Funcionários', '789', ''),
('Salvador', 'BA', 6000.00, 180, 'Avenida Sete de Setembro', 'Barra', '1011', ''),
('Curitiba', 'PR', 4500.00, 120, 'Rua XV de Novembro', 'Centro', '1213', 'Sala 301'),
('Recife', 'PE', 5500.00, 160, 'Avenida Boa Viagem', 'Boa Viagem', '1415', ''),
('Brasília', 'DF', 6500.00, 170, 'Setor Comercial Sul', 'Asa Sul', '1617', ''),
('Fortaleza', 'CE', 4800.00, 130, 'Avenida Beira Mar', 'Meireles', '1819', ''),
('Manaus', 'AM', 5200.00, 140, 'Avenida Djalma Batista', 'Chapada', '2021', ''),
('Porto Alegre', 'RS', 4300.00, 110, 'Avenida Borges de Medeiros', 'Centro Histórico', '2223', '');

INSERT INTO locais (municipio, uf, valor, quantidade_max_pessoas, rua, bairro, numero, complemento)
VALUES
('São Paulo', 'SP', 5000.00, 200, 'Paulista', 'Centro', '13', 'Apartamento 11');