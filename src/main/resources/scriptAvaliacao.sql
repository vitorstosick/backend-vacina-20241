create schema carros;
 
use carros;
 
 CREATE TABLE `montadora` (
  `id_montadora` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(150) NOT NULL,
  `pais` VARCHAR(150) NOT NULL,
  `presidente` VARCHAR(150) NOT NULL,
  `data_fundacao` DATE NOT NULL,
  PRIMARY KEY (`id_montadora`)
 );
 
CREATE TABLE `carro` (
  `id_carro` INT NOT NULL AUTO_INCREMENT,
  `modelo` VARCHAR(150) NOT NULL,
  `placa` VARCHAR(11) NOT NULL,
  `ano` INT NOT NULL,
  `valor` DECIMAL(10,2) NOT NULL,
  `id_montadora` INT NOT NULL,
  PRIMARY KEY (`id_carro`),
  FOREIGN KEY (`id_montadora`) REFERENCES montadora (`id_montadora`)
  );

-- Carro 1: Fiat Uno
INSERT INTO carro (modelo, placa, ano, valor, id_montadora) VALUES ('Uno', 'ABC1234', 2020, 35000.00, 3);

-- Carro 2: BMW Serie 3
INSERT INTO carro (modelo, placa, ano, valor, id_montadora) VALUES ('Serie 3', 'DEF5678', 2022, 80000.00, 4);

-- Carro 3: Ford Fiesta
INSERT INTO carro (modelo, placa, ano, valor, id_montadora) VALUES ('Fiesta', 'GHI9012', 2019, 45000.00, 5);

-- Carro 4: Fiat Palio
INSERT INTO carro (modelo, placa, ano, valor, id_montadora) VALUES ('Palio', 'JKL3456', 2021, 42000.00, 3);

-- Carro 5: BMW X5
INSERT INTO carro (modelo, placa, ano, valor, id_montadora) VALUES ('X5', 'MNO7890', 2023, 120000.00, 4);

-- Carro 6: Ford Focus
INSERT INTO carro (modelo, placa, ano, valor, id_montadora) VALUES ('Focus', 'PQR1234', 2020, 50000.00, 5);
