CREATE TABLE carteira(
id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
nome VARCHAR(50),
usuario_id BIGINT(20),
foreign key(usuario_id) references usuario(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;