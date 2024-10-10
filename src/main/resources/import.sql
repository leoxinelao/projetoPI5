INSERT INTO tb_department(name) VALUES ('Gestão');
INSERT INTO tb_department(name) VALUES ('Informática');

INSERT INTO tb_user(department_id, name, email,cpf) VALUES (1, 'Maria', 'maria@gmail.com','11');
INSERT INTO tb_user(department_id, name, email,cpf) VALUES (1, 'Bob', 'bob@gmail.com','11');
INSERT INTO tb_user(department_id, name, email,cpf) VALUES (2, 'Alex', 'alex@gmail.com','11');
INSERT INTO tb_user(department_id, name, email,cpf) VALUES (2, 'Ana', 'ana@gmail.com','11');

INSERT INTO tb_planos (nome, cargoAtual, bonificacao, user_id, department_id) VALUES ('Nome do Plano', 'Cargo Atual', 'Bonificação', 1, 1);

-- Inserir metas associadas ao plano
INSERT INTO tb_metas(plano_id, descricao) VALUES (plano_id, 'Aumentar a eficiência do código');
INSERT INTO tb_metas(plano_id, descricao) VALUES (plano_id, 'Participar de 3 treinamentos');