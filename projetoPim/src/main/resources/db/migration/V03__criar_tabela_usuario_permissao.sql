CREATE TABLE usuario_permissao(
codigo_usuario BIGINT(20) NOT NULL,
codigo_permissao BIGINT(20) NOT NULL,
PRIMARY KEY (codigo_usuario, codigo_permissao),
FOREIGN KEY (codigo_usuario) REFERENCES usuario(id),
FOREIGN KEY (codigo_permissao) REFERENCES permissao(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;