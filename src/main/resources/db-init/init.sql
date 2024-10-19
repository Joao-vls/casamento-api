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

CREATE TABLE tipo_servico (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(50),
    descricao VARCHAR(150),
    valor FLOAT NOT NULL
);


INSERT INTO tipo_servico (nome, descricao, valor)
VALUES
('Decoração', 'Serviço de decoração para o casamento', 3000.00),
('Buffet <150', 'Serviço de buffet para o casamento', 5000.00),
('Buffet >150<300', 'Serviço de buffet para o casamento', 9350.00),
('Fotografia', 'Serviço de fotografia para o casamento', 2500.00),
('DJ', 'Serviço de DJ para o casamento', 1500.00),
('Bolo e doces', 'Serviço de bolo e doces para o casamento', 2000.00),
('Transporte', 'Serviço de transporte para o casamento', 1800.00),
('Vestido', 'Vestido  para o casamento', 3500.00),
('Traje', 'Traje  para o casamento', 1000.00),
('Maquiagem e cabelo', 'Serviço de maquiagem e cabelo para o casamento', 1200.00);

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


INSERT INTO locais_imagens (local_id, imagem)
VALUES
(1, '1.jpg'),
(1, '2.jpg'),
(1, '3.jpg'),
(2, '4.jpg'),
(2, '5.jpg'),
(2, '6.jpg'),
(3, '7.jpg'),
(3, '8.jpg'),
(3, '8.jpg'),
(4, '9.jpg'),
(4, '10.jpg'),
(4, '11.jpg'),
(5, '12.jpg'),
(5, '13.jpg'),
(5, '14.jpg'),
(6, '15.jpg'),
(6, '16.jpg'),
(6, '17.jpg'),
(7, '18.jpg'),
(7, '19.jpg'),
(7, '13.jpg'),
(8, '7.jpg'),
(8, '2.jpg'),
(8, '4.jpg'),
(9, '9.jpg'),
(9, '15.jpg'),
(9, '3.jpg'),
(10, '9.jpg'),
(10, '5.jpg'),
(10, '14.jpg');