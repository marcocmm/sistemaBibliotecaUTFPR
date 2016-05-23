use bdBiblioteca;
-- Insert de Estudantes -- 
INSERT INTO bdBiblioteca.Estudantes (ra, nome, senha, email)
VALUES ('1136631', 'Leonardo', 'baiser', 'lpbaiser@gmail.com'),
       ('1137112', 'Rômulo', 'meloca', 'rmmeloca@gmail.com'),
	   ('1137085', 'Marco', 'mattos', 'marco.cmm@gmail.com'),
	   ('11370090', 'Mateus Tomoo', 'tomoo', 'mateustomooo@gmail.com'),
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

-- Insert Status--
INSERT INTO bdBiblioteca.Status (name)
VALUES ('inativa'),
	   ('ativa');

-- Insert Reservas --
INSERT INTO bdBiblioteca.Reservas(quantidade_alunos, data_inicial, data_final, estudante_ra, sala_id, status_name)
VALUES (5, '2016-05-05 08:00:00', '2016-05-05 09:00:00', '1136631', 1,  'ativa'),
	   (4, '2016-05-10 09:00:00', '2016-05-10 10:00:00', '1602063', 1,  'ativa'),
	   (4, '2016-05-10 09:00:00', '2016-05-10 10:00:00', '1602063', 2,  'ativa'),
	   (4, '2016-05-10 09:00:00', '2016-05-10 10:00:00', '1602063', 3,  'ativa'),
	   (4, '2016-05-10 09:00:00', '2016-05-10 10:00:00', '1602063', 4,  'ativa'),
	   (4, '2016-05-10 09:00:00', '2016-05-10 10:00:00', '1602063', 5,  'ativa'),
	   (5, '2016-05-10 09:00:00', '2016-05-10 10:00:00', '1136632', 3,  'inativa'),
	   (4, '2016-05-10 09:00:00', '2016-05-10 10:00:00', '1137112', 2,  'inativa'),
	   (5, '2016-05-06 10:00:00', '2016-05-06 11:00:00', '1137085', 3,  'inativa'),
	   (3, '2016-05-05 11:00:00', '2016-05-05 12:00:00', '637085', 4,  'inativa'),
	   (5, '2016-05-09 12:00:00', '2016-05-09 13:00:00', '1602063', 5,  'inativa');