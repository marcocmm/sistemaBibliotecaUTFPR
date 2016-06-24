SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

DROP SCHEMA IF EXISTS `bdBiblioteca` ;
CREATE SCHEMA IF NOT EXISTS `bdBiblioteca` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `bdBiblioteca` ;




-- -----------------------------------------------------
-- Table `bdBiblioteca`.`usuarios`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bdBiblioteca`.`Usuarios` ;

CREATE TABLE IF NOT EXISTS `bdBiblioteca`.`Usuarios` (
  `ra` CHAR(10) NOT NULL,
  `nome` VARCHAR(45) NOT NULL,
  `senha` VARCHAR(30) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `administrador` BOOLEAN NOT NULL,
  PRIMARY KEY (`ra`))
ENGINE = InnoDB;

CREATE UNIQUE INDEX `email_UNIQUE` ON `bdBiblioteca`.`Usuarios` (`email` ASC);


-- -----------------------------------------------------
-- Table `bdBiblioteca`.`Salas`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bdBiblioteca`.`Salas` ;

CREATE TABLE IF NOT EXISTS `bdBiblioteca`.`Salas` (
  `id` INT NOT NULL,
  `ar` TINYINT(1) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bdBiblioteca`.`Status`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bdBiblioteca`.`Status` ;

CREATE TABLE IF NOT EXISTS `bdBiblioteca`.`Status` (
  `name` VARCHAR(15) NOT NULL,
  PRIMARY KEY (`name`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bdBiblioteca`.`Reservas`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bdBiblioteca`.`Reservas` ;

CREATE TABLE IF NOT EXISTS `bdBiblioteca`.`Reservas` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `quantidade_alunos` INT NOT NULL,
  `data_inicial` DATETIME NOT NULL,
  `data_final` DATETIME NOT NULL,
  `usuario_ra` CHAR(10) NOT NULL,
  `sala_id` INT NOT NULL,
  `status_name` VARCHAR(15) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_reservas_usuarios_ra`
    FOREIGN KEY (`usuario_ra`)
    REFERENCES `bdBiblioteca`.`Usuarios` (`ra`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_reservas_salas_id`
    FOREIGN KEY (`sala_id`)
    REFERENCES `bdBiblioteca`.`Salas` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_reservas_status_name`
    FOREIGN KEY (`status_name`)
    REFERENCES `bdBiblioteca`.`Status` (`name`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_reservas_salas_id_idx` ON `bdBiblioteca`.`Reservas` (`sala_id` ASC);

CREATE INDEX `data_inicial_idx` ON `bdBiblioteca`.`Reservas` (`data_inicial` ASC);

CREATE INDEX `fk_reservas_usuarios_ra_idx` ON `bdBiblioteca`.`Reservas` (`usuario_ra` ASC);

CREATE INDEX `fk_reservas_status_name_idx` ON `bdBiblioteca`.`Reservas` (`status_name` ASC);


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
SET GLOBAL event_scheduler = ON;

DROP EVENT IF EXISTS `bdBiblioteca`.`e_AtualizaCheckIn`;

CREATE EVENT e_AtualizaCheckIn
ON SCHEDULE EVERY 1 HOUR STARTS '2016-06-06 00:15:00'
DO UPDATE Reservas
SET status_name = 'desistencia'
WHERE status_name = 'ativa' AND data_inicial < NOW();

DROP EVENT IF EXISTS `bdBiblioteca`.`e_AtualizaCheckOut`;

CREATE EVENT e_AtualizaCheckOut
ON SCHEDULE EVERY 1 HOUR STARTS '2016-06-06 00:00:00'
DO UPDATE Reservas
SET status_name = 'concluida'
WHERE status_name = 'emCurso' AND data_inicial < NOW();
