use bdBiblioteca;
-- Insert de Usuarios -- 
INSERT INTO bdBiblioteca.Usuarios (ra, nome, senha, email, administrador)
VALUES ('1136631', 'Leonardo', MD5('baiser'), 'lpbaiser@gmail.com', false),
       ('1137131', 'Rômulo', MD5('meloca'), 'rmmeloca@gmail.com', false),
	   ('1137085', 'Marco', MD5('mattos'), 'marco.cmm@gmail.com', false),
	   ('1602055', 'Mateus Tomoo', MD5('tomoo'), 'mateustomooo@gmail.com', false),
	   ('1136632', 'Marina', MD5('silva'), 'msilva@gmail.com', false),
	   ('637085', 'Dilmae', MD5('dilmae'), 'dilmae@gmail.com', false),
	   ('1138081', 'João', MD5('ferreira'), 'fjoao@gmail.com', false),
       ('1602063', 'Mateus Torres', MD5('torres'), 'lottar1996@gmail.com', false),
	   ('admin', 'admin', MD5('admin'), 'admin@gmail.com', true);

-- Insert de salas-- 
INSERT INTO bdBiblioteca.Salas (id, ar)
VALUES (1, 0),
	   (2, 0),
	   (3, 1),
	   (4, 0),
	   (5, 0),
	   (6, 1);


-- Insert Status--
INSERT INTO bdBiblioteca.Status (name)
VALUES	('inativa'),
		('ativa'),
		('desistencia'),
		('emCurso'),
		('concluida');

-- Insert Reservas --
INSERT INTO bdBiblioteca.Reservas(quantidade_alunos, data_inicial, data_final, usuario_ra, sala_id, status_name)
VALUES (5, '2016-05-05 08:00:00', '2016-05-05 09:00:00', '1136631', 1,  'ativa'),
	   (4, '2016-05-10 09:00:00', '2016-05-10 10:00:00', '1602063', 1,  'ativa'),
	   (4, '2016-05-10 09:00:00', '2016-05-10 10:00:00', '1602063', 2,  'ativa'),
	   (4, '2016-05-10 09:00:00', '2016-05-10 10:00:00', '1602063', 3,  'ativa'),
	   (4, '2016-05-10 09:00:00', '2016-05-10 10:00:00', '1602063', 4,  'ativa'),
	   (4, '2016-05-10 09:00:00', '2016-05-10 10:00:00', '1602063', 5,  'ativa'),
	   (5, '2016-05-10 09:00:00', '2016-05-10 10:00:00', '1136632', 3,  'ativa'),
	   (4, '2016-05-10 12:00:00', '2016-05-10 13:00:00', '1137131', 2,  'ativa'),
	   (5, '2016-05-06 10:00:00', '2016-05-06 11:00:00', '1137085', 3,  'inativa'),
	   (3, '2016-05-05 11:00:00', '2016-05-05 12:00:00', '637085', 4,  'inativa'),
	   (5, '2016-05-09 12:00:00', '2016-05-09 13:00:00', '1602063', 5,  'inativa');
