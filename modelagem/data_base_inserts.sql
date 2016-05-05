-- Insert de Estudantes -- 
INSERT INTO bdBiblioteca.Estudantes (ra, nome, senha, email)
VALUES ('1136631', 'Leonardo', 'baiser', 'lpbaiser@gmail.com'),
       ('1137112', 'Rômulo', 'meloca', 'rmmeloca@gmail.com'),
	   ('1137085', 'Marco', 'mattos', 'marco.cmm@gmail.com'),
	   ('1137085', 'Mateus Tomoo', 'tomoo', 'mateustomooo@gmail.com'),
	   ('1136632', 'Marina', 'silva', 'msilva@gmail.com'),
	   ('637085', 'Dilmae', 'dilmae', 'dilmae@gmail.com'),
	   ('1138081', 'João', 'ferreira', 'fjoao@gmail.com'),
       ('1602063', 'Mateus Torres', 'torres', 'lottar1996@gmail.com');

-- Insert de salas-- 
INSERT INTO bdBiblioteca.Salas (id, ar)
VALUES (1, 0),
	   (2, 0),
	   (3, 1),
	   (4, 0),
	   (5, 1);

-- Insert Administradores-- 
INSERT INTO bdBiblioteca.Administradores (login, senha)
VALUES ('dellconte@gmail.com', 'dellconte'),
	   ('root', 'root');

-- Insert Reservas --
INSERT INTO bdBiblioteca.Reservas(quantidade_alunos, data_inicial, data_final, estudante_ra, sala_id, status_name)
VALUES (5, '2016-05-05 08:00:00', '2016-05-05 09:00:00', '1136631', 1,  'inativa');