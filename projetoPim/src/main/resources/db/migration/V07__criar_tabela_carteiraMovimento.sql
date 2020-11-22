CREATE TABLE carteira_movimento(
id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
moeda_id BIGINT(20),
carteira_id BIGINT(20),
quantidade DECIMAL(7,2) NOT NULL,
preco DECIMAL(7,2) NOT NULL,
valor_total DECIMAL(7,2) NOT NULL,
lucro DECIMAL(7,2) NOT NULL,
foreign key(moeda_id) references moeda(id),
foreign key(carteira_id) references carteira(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;