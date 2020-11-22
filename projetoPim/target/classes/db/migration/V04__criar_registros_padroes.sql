INSERT INTO usuario (id, nome, email, senha, inativo)
VALUES (1, 'Administrador', 'adm@gmail.com', '$2a$10$HllECQZOufZMiSVy..jvSetk2HE1lzmzzOXwoYmlQZCevmOljXkjK', 0);

INSERT INTO permissao
(id, descricao) VALUES (1, 'adm');

INSERT INTO usuario_permissao
(codigo_usuario, codigo_permissao) VALUES (1, 1);
