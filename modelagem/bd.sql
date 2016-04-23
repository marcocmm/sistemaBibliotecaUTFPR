SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

DROP SCHEMA IF EXISTS `bdBiblioteca` ;
CREATE SCHEMA IF NOT EXISTS `bdBiblioteca` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `bdBiblioteca` ;

-- -----------------------------------------------------
-- Table `bdBiblioteca`.`Estudantes`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bdBiblioteca`.`Estudantes` ;

CREATE TABLE IF NOT EXISTS `bdBiblioteca`.`Estudantes` (
  `ra` CHAR(10) NOT NULL,
  `nome` VARCHAR(45) NOT NULL,
  `senha` VARCHAR(30) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ra`))
ENGINE = InnoDB;

CREATE UNIQUE INDEX `email_UNIQUE` ON `bdBiblioteca`.`Estudantes` (`email` ASC);


-- -----------------------------------------------------
-- Table `bdBiblioteca`.`Salas`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bdBiblioteca`.`Salas` ;

CREATE TABLE IF NOT EXISTS `bdBiblioteca`.`Salas` (
  `id` INT NOT NULL,
  `ar` TINYINT(1) NULL COMMENT '							\n\n						',
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bdBiblioteca`.`Status`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bdBiblioteca`.`Status` ;

CREATE TABLE IF NOT EXISTS `bdBiblioteca`.`Status` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(15) NOT NULL,
  PRIMARY KEY (`id`))
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
  `estudante_ra` CHAR(10) NOT NULL,
  `sala_id` INT NOT NULL,
  `status_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_reservas_estudantes_ra`
    FOREIGN KEY (`estudante_ra`)
    REFERENCES `bdBiblioteca`.`Estudantes` (`ra`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_reservas_salas_id`
    FOREIGN KEY (`sala_id`)
    REFERENCES `bdBiblioteca`.`Salas` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_reservas_status_id`
    FOREIGN KEY (`status_id`)
    REFERENCES `bdBiblioteca`.`Status` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_reservas_salas_id_idx` ON `bdBiblioteca`.`Reservas` (`sala_id` ASC);

CREATE INDEX `data_inicial_idx` ON `bdBiblioteca`.`Reservas` (`data_inicial` ASC);

CREATE INDEX `fk_reservas_estudantes_ra_idx` ON `bdBiblioteca`.`Reservas` (`estudante_ra` ASC);


-- -----------------------------------------------------
-- Table `bdBiblioteca`.`Administradores`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bdBiblioteca`.`Administradores` ;

CREATE TABLE IF NOT EXISTS `bdBiblioteca`.`Administradores` (
  `login` VARCHAR(15) NOT NULL,
  `senha` VARCHAR(30) NULL,
  PRIMARY KEY (`login`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bdBiblioteca`.`Reservas_Ativas`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bdBiblioteca`.`Reservas_Ativas` ;

CREATE TABLE IF NOT EXISTS `bdBiblioteca`.`Reservas_Ativas` (
  `reserva_id` INT NOT NULL,
  `reserva_estudante_ra` CHAR(10) NOT NULL,
  `reserva_data_inicial` DATETIME NOT NULL,
  PRIMARY KEY (`reserva_data_inicial`, `reserva_estudante_ra`),
  CONSTRAINT `fk_reservas_ativas_reservas_id`
    FOREIGN KEY (`reserva_id`)
    REFERENCES `bdBiblioteca`.`Reservas` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_reservas_ativas_reservas_estudante_ra`
    FOREIGN KEY (`reserva_estudante_ra`)
    REFERENCES `bdBiblioteca`.`Reservas` (`estudante_ra`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_reservas_ativas_reservas_data_inicial`
    FOREIGN KEY (`reserva_data_inicial`)
    REFERENCES `bdBiblioteca`.`Reservas` (`data_inicial`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE UNIQUE INDEX `reserva_data_inicial_UNIQUE` ON `bdBiblioteca`.`Reservas_Ativas` (`reserva_data_inicial` ASC);

CREATE INDEX `fk_reservas_ativas_reservas_id_idx` ON `bdBiblioteca`.`Reservas_Ativas` (`reserva_id` ASC);

CREATE INDEX `fk_reservas_ativas_reservas_estudante_ra_idx` ON `bdBiblioteca`.`Reservas_Ativas` (`reserva_estudante_ra` ASC);


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
