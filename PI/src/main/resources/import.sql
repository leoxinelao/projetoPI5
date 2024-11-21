INSERT INTO tb_user( name, email,cpf, senha, department) VALUES (1, 'Maria', 'maria@gmail.com','11','123','department');
INSERT INTO tb_user( name, email,cpf, senha, department) VALUES (1, 'Bob', 'bob@gmail.com','11','123','department');
INSERT INTO tb_user( name, email,cpf, senha, department) VALUES (2, 'Alex', 'alex@gmail.com','11','123','department');
INSERT INTO tb_user( name, email,cpf, senha, department) VALUES (2, 'Ana', 'ana@gmail.com','11','123','department');

INSERT INTO tb_planos (nome, cargoAtual, bonificacao, user_id, department) VALUES ('Nome do Plano', 'Cargo Atual', 'Bonificação', 1,'TI');

-- Inserir metas associadas ao plano
INSERT INTO tb_metas(plano_id, descricao) VALUES (plano_id, 'Aumentar a eficiência do código');
INSERT INTO tb_metas(plano_id, descricao) VALUES (plano_id, 'Participar de 3 treinamentos');