SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `class` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `class` ;

-- -----------------------------------------------------
-- Table `class`.`exam_type`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `class`.`exam_type` (
  `idexam_type` INT NOT NULL AUTO_INCREMENT ,
  `type_name` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`idexam_type`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `class`.`subject`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `class`.`subject` (
  `idsubject` INT NOT NULL AUTO_INCREMENT ,
  `subject_code` VARCHAR(45) NULL ,
  `subject_name` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`idsubject`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `class`.`exam_detail`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `class`.`exam_detail` (
  `idexam_detail` INT NOT NULL AUTO_INCREMENT ,
  `school_no` VARCHAR(45) NOT NULL ,
  `date` DATE NULL ,
  `grade` INT NOT NULL ,
  `division` VARCHAR(45) NULL ,
  `term` INT NULL ,
  `exam_type_idexam_type` INT NOT NULL ,
  `subject_idsubject` INT NOT NULL ,
  PRIMARY KEY (`idexam_detail`) ,
  INDEX `fk_exam_detail_exam_type1_idx` (`exam_type_idexam_type` ASC) ,
  INDEX `fk_exam_detail_subject1_idx` (`subject_idsubject` ASC) ,
  CONSTRAINT `fk_exam_detail_exam_type1`
    FOREIGN KEY (`exam_type_idexam_type` )
    REFERENCES `class`.`exam_type` (`idexam_type` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_exam_detail_subject1`
    FOREIGN KEY (`subject_idsubject` )
    REFERENCES `class`.`subject` (`idsubject` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `class`.`student_performance`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `class`.`student_performance` (
  `idstudent_performance` INT NOT NULL AUTO_INCREMENT ,
  `student_id` VARCHAR(45) NULL ,
  `exam_detail_idexam_detail` INT NOT NULL ,
  PRIMARY KEY (`idstudent_performance`) ,
  INDEX `fk_student_performance_exam_detail_idx` (`exam_detail_idexam_detail` ASC) ,
  CONSTRAINT `fk_student_performance_exam_detail`
    FOREIGN KEY (`exam_detail_idexam_detail` )
    REFERENCES `class`.`exam_detail` (`idexam_detail` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `class`.`exam_marks`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `class`.`exam_marks` (
  `student_performance_idstudent_performance` INT NOT NULL ,
  `marks` FLOAT NOT NULL ,
  PRIMARY KEY (`student_performance_idstudent_performance`) ,
  INDEX `fk_exam_marks_student_performance1_idx` (`student_performance_idstudent_performance` ASC) ,
  CONSTRAINT `fk_exam_marks_student_performance1`
    FOREIGN KEY (`student_performance_idstudent_performance` )
    REFERENCES `class`.`student_performance` (`idstudent_performance` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `class`.`exam_results`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `class`.`exam_results` (
  `student_performance_idstudent_performance` INT NOT NULL ,
  `results` VARCHAR(45) NOT NULL ,
  INDEX `fk_exam_results_student_performance1_idx` (`student_performance_idstudent_performance` ASC) ,
  PRIMARY KEY (`student_performance_idstudent_performance`) ,
  CONSTRAINT `fk_exam_results_student_performance1`
    FOREIGN KEY (`student_performance_idstudent_performance` )
    REFERENCES `class`.`student_performance` (`idstudent_performance` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

USE `class` ;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
