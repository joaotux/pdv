-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema pdv
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema pdv
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `pdv` DEFAULT CHARACTER SET utf8 ;
USE `pdv` ;

-- -----------------------------------------------------
-- Table `pdv`.`pais`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pdv`.`pais` (
  `codigo` INT(11) NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(100) NOT NULL,
  `pais_codigo` VARCHAR(5) NOT NULL,
  PRIMARY KEY (`codigo`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pdv`.`estado`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pdv`.`estado` (
  `codigo` INT(11) NOT NULL,
  `sigla` VARCHAR(3) NOT NULL,
  `nome` VARCHAR(45) NOT NULL,
  `codigoUF` VARCHAR(4) NOT NULL,
  `pais_codigo` INT(11) NOT NULL,
  PRIMARY KEY (`codigo`),
  INDEX `fk_estado_table11_idx` (`pais_codigo` ASC),
  CONSTRAINT `fk_estado_table11`
    FOREIGN KEY (`pais_codigo`)
    REFERENCES `pdv`.`pais` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pdv`.`cidade`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pdv`.`cidade` (
  `codigo` INT(11) NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `estado_codigo` INT(11) NOT NULL,
  `codigo_municipio` VARCHAR(12) NOT NULL,
  PRIMARY KEY (`codigo`),
  INDEX `fk_cidade_estado1_idx` (`estado_codigo` ASC),
  CONSTRAINT `fk_cidade_estado1`
    FOREIGN KEY (`estado_codigo`)
    REFERENCES `pdv`.`estado` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pdv`.`endereco`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pdv`.`endereco` (
  `codigo` INT(11) NOT NULL AUTO_INCREMENT,
  `rua` VARCHAR(60) NOT NULL,
  `bairro` VARCHAR(60) NOT NULL,
  `numero` VARCHAR(6) NOT NULL,
  `cep` VARCHAR(20) NULL,
  `referencia` VARCHAR(250) NULL,
  `data_cadastro` DATE NOT NULL,
  `data_alteracao` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `cidade_codigo` INT(11) NOT NULL,
  PRIMARY KEY (`codigo`),
  INDEX `fk_endereco_cidade1_idx` (`cidade_codigo` ASC),
  CONSTRAINT `fk_endereco_cidade1`
    FOREIGN KEY (`cidade_codigo`)
    REFERENCES `pdv`.`cidade` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pdv`.`telefone`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pdv`.`telefone` (
  `codigo` INT(11) NOT NULL AUTO_INCREMENT,
  `fone` VARCHAR(15) NOT NULL,
  `tipo` VARCHAR(10) NULL,
  `data_cadastro` DATE NOT NULL,
  `data_alteracao` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`codigo`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pdv`.`fornecedor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pdv`.`fornecedor` (
  `codigo` INT(11) NOT NULL AUTO_INCREMENT,
  `nome_fantasia` VARCHAR(250) NOT NULL,
  `nome` VARCHAR(250) NOT NULL,
  `cnpj` VARCHAR(18) NOT NULL,
  `inscricao_estadual` VARCHAR(40) NULL,
  `ativo` INT NOT NULL,
  `endereco_codigo` INT(11) NOT NULL,
  `observacao` VARCHAR(250) NULL,
  `data_alteracao` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `data_cadastro` DATE NOT NULL,
  PRIMARY KEY (`codigo`),
  INDEX `fk_fornecedor_endereco1_idx` (`endereco_codigo` ASC),
  UNIQUE INDEX `cnpj_UNIQUE` (`cnpj` ASC),
  CONSTRAINT `fk_fornecedor_endereco1`
    FOREIGN KEY (`endereco_codigo`)
    REFERENCES `pdv`.`endereco` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pdv`.`fornecedor_telefone`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pdv`.`fornecedor_telefone` (
  `codigo` INT(11) NOT NULL AUTO_INCREMENT,
  `telefone_codigo` INT(11) NOT NULL,
  `fornecedor_codigo` INT(11) NOT NULL,
  PRIMARY KEY (`codigo`),
  INDEX `fk_fornecedor_telefone_telefone1_idx` (`telefone_codigo` ASC),
  INDEX `fk_fornecedor_telefone_fornecedor1_idx` (`fornecedor_codigo` ASC),
  CONSTRAINT `fk_fornecedor_telefone_telefone1`
    FOREIGN KEY (`telefone_codigo`)
    REFERENCES `pdv`.`telefone` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_fornecedor_telefone_fornecedor1`
    FOREIGN KEY (`fornecedor_codigo`)
    REFERENCES `pdv`.`fornecedor` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pdv`.`grupo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pdv`.`grupo` (
  `codigo` INT(11) NOT NULL AUTO_INCREMENT,
  `descricao` VARCHAR(45) NOT NULL,
  `data_cadastro` DATE NOT NULL,
  `data_alteracao` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`codigo`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pdv`.`categoria`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pdv`.`categoria` (
  `codigo` INT(11) NOT NULL AUTO_INCREMENT,
  `descricao` VARCHAR(45) NOT NULL,
  `data_cadastro` DATE NOT NULL,
  `data_alteracao` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`codigo`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pdv`.`regime_tributario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pdv`.`regime_tributario` (
  `codigo` INT(11) NOT NULL AUTO_INCREMENT,
  `descricao` VARCHAR(100) NOT NULL,
  `tipo_regime` INT NOT NULL,
  `data_alteracao` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`codigo`),
  UNIQUE INDEX `tipo_regime_UNIQUE` (`tipo_regime` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pdv`.`tipo_ambiente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pdv`.`tipo_ambiente` (
  `codigo` INT(11) NOT NULL,
  `descricao` VARCHAR(45) NOT NULL,
  `tipo` INT NOT NULL,
  PRIMARY KEY (`codigo`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pdv`.`empresa_parametros`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pdv`.`empresa_parametros` (
  `codigo` INT(11) NOT NULL AUTO_INCREMENT,
  `serie_nfe` INT NULL,
  `tipo_ambiente_codigo` INT(11) NULL,
  `p_credsn` DOUBLE NULL,
  PRIMARY KEY (`codigo`),
  INDEX `fk_empresa_parametros_tipo_ambiente1_idx` (`tipo_ambiente_codigo` ASC),
  CONSTRAINT `fk_empresa_parametros_tipo_ambiente1`
    FOREIGN KEY (`tipo_ambiente_codigo`)
    REFERENCES `pdv`.`tipo_ambiente` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pdv`.`empresa`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pdv`.`empresa` (
  `codigo` INT(11) NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(255) NOT NULL,
  `nome_fantasia` VARCHAR(255) NOT NULL,
  `cnpj` VARCHAR(18) NOT NULL,
  `ie` VARCHAR(45) NULL,
  `regime_tributario_codigo` INT(11) NOT NULL,
  `data_alteracao` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `parametro_codigo` INT(11) NULL,
  `endereco_codigo` INT(11) NOT NULL,
  PRIMARY KEY (`codigo`),
  INDEX `fk_empresa_regime_tributario1_idx` (`regime_tributario_codigo` ASC),
  INDEX `fk_empresa_empresa_parametros1_idx` (`parametro_codigo` ASC),
  INDEX `fk_empresa_endereco1_idx` (`endereco_codigo` ASC),
  CONSTRAINT `fk_empresa_regime_tributario1`
    FOREIGN KEY (`regime_tributario_codigo`)
    REFERENCES `pdv`.`regime_tributario` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_empresa_empresa_parametros1`
    FOREIGN KEY (`parametro_codigo`)
    REFERENCES `pdv`.`empresa_parametros` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_empresa_endereco1`
    FOREIGN KEY (`endereco_codigo`)
    REFERENCES `pdv`.`endereco` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pdv`.`tributacao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pdv`.`tributacao` (
  `codigo` INT(11) NOT NULL AUTO_INCREMENT,
  `descricao` VARCHAR(250) NOT NULL,
  `subs_tributaria` TINYINT(1) NOT NULL,
  `data_cadastro` DATE NOT NULL,
  `data_alteracao` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `empresa_codigo` INT(11) NOT NULL,
  PRIMARY KEY (`codigo`),
  INDEX `fk_tributacao_empresa1_idx` (`empresa_codigo` ASC),
  CONSTRAINT `fk_tributacao_empresa1`
    FOREIGN KEY (`empresa_codigo`)
    REFERENCES `pdv`.`empresa` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = 'Tabela criada com o objetivo de cadastrar as regras fiscais dos produtos.\n\nO campor subTributaria deve conter o valor ZERO para não substituição e UM quando a regra for de substituição tributaria.';


-- -----------------------------------------------------
-- Table `pdv`.`mod_bc_icms`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pdv`.`mod_bc_icms` (
  `codigo` INT(11) NOT NULL AUTO_INCREMENT,
  `tipo` INT(11) NOT NULL,
  `descricao` VARCHAR(255) NOT NULL,
  `sub_tributaria` TINYINT(1) NOT NULL,
  PRIMARY KEY (`codigo`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pdv`.`produto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pdv`.`produto` (
  `codigo` INT(11) NOT NULL AUTO_INCREMENT,
  `descricao` VARCHAR(250) NOT NULL,
  `valor_custo` DOUBLE NULL,
  `valor_venda` DOUBLE NULL,
  `valor_balanca` DOUBLE NULL,
  `data_validade` DATE NULL,
  `balanca` INT NOT NULL,
  `ativo` VARCHAR(10) NOT NULL,
  `fornecedor_codigo` INT(11) NOT NULL,
  `grupo_codigo` INT(11) NOT NULL,
  `unidade` VARCHAR(10) NULL,
  `subtributaria` INT NOT NULL,
  `categoria_codigo` INT(11) NOT NULL,
  `ncm` VARCHAR(12) NULL,
  `cest` VARCHAR(12) NULL,
  `vendavel` VARCHAR(5) NOT NULL,
  `controla_estoque` VARCHAR(10) NOT NULL,
  `tributacao_codigo` INT(11) NULL,
  `bc_icms_codigo` INT(11) NULL,
  `data_cadastro` DATE NOT NULL,
  `data_alteracao` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`codigo`),
  INDEX `fk_produto_fornecedor1_idx` (`fornecedor_codigo` ASC),
  INDEX `fk_produto_grupo1_idx` (`grupo_codigo` ASC),
  INDEX `fk_produto_categoria1_idx` (`categoria_codigo` ASC),
  INDEX `fk_produto_tributacao1_idx` (`tributacao_codigo` ASC),
  INDEX `fk_produto_mod_bc_icms1_idx` (`bc_icms_codigo` ASC),
  CONSTRAINT `fk_produto_fornecedor1`
    FOREIGN KEY (`fornecedor_codigo`)
    REFERENCES `pdv`.`fornecedor` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_produto_grupo1`
    FOREIGN KEY (`grupo_codigo`)
    REFERENCES `pdv`.`grupo` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_produto_categoria1`
    FOREIGN KEY (`categoria_codigo`)
    REFERENCES `pdv`.`categoria` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_produto_tributacao1`
    FOREIGN KEY (`tributacao_codigo`)
    REFERENCES `pdv`.`tributacao` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_produto_mod_bc_icms1`
    FOREIGN KEY (`bc_icms_codigo`)
    REFERENCES `pdv`.`mod_bc_icms` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pdv`.`produto_imagem`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pdv`.`produto_imagem` (
  `codigo` INT(11) NOT NULL AUTO_INCREMENT,
  `descricao` VARCHAR(45) NULL,
  `uri` TEXT NOT NULL,
  `data_cadastro` DATE NOT NULL,
  `data_alteracao` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `produto_codigo` INT(11) NOT NULL,
  PRIMARY KEY (`codigo`),
  INDEX `fk_imagem_produto1_idx` (`produto_codigo` ASC),
  CONSTRAINT `fk_imagem_produto1`
    FOREIGN KEY (`produto_codigo`)
    REFERENCES `pdv`.`produto` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pdv`.`pessoa`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pdv`.`pessoa` (
  `codigo` INT(11) NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(250) NOT NULL,
  `cpfcnpj` VARCHAR(18) NOT NULL,
  `data_cadastro` DATE NOT NULL,
  `data_alteracao` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `endereco_codigo` INT(11) NOT NULL,
  `apelido` VARCHAR(45) NULL,
  `data_nascimento` DATE NOT NULL,
  `observacao` VARCHAR(250) NULL,
  PRIMARY KEY (`codigo`),
  UNIQUE INDEX `cpf_cnpj_UNIQUE` (`cpfcnpj` ASC),
  INDEX `fk_pessoa_endereco1_idx` (`endereco_codigo` ASC),
  CONSTRAINT `fk_pessoa_endereco1`
    FOREIGN KEY (`endereco_codigo`)
    REFERENCES `pdv`.`endereco` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pdv`.`pessoa_telefone`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pdv`.`pessoa_telefone` (
  `codigo` INT(11) NOT NULL AUTO_INCREMENT,
  `pessoa_codigo` INT(11) NOT NULL,
  `telefone_codigo` INT(11) NOT NULL,
  PRIMARY KEY (`codigo`),
  INDEX `fk_pessoa_telefone_pessoa1_idx` (`pessoa_codigo` ASC),
  INDEX `fk_pessoa_telefone_telefone1_idx` (`telefone_codigo` ASC),
  CONSTRAINT `fk_pessoa_telefone_pessoa1`
    FOREIGN KEY (`pessoa_codigo`)
    REFERENCES `pdv`.`pessoa` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_pessoa_telefone_telefone1`
    FOREIGN KEY (`telefone_codigo`)
    REFERENCES `pdv`.`telefone` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pdv`.`usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pdv`.`usuario` (
  `codigo` INT(11) NOT NULL AUTO_INCREMENT,
  `user` VARCHAR(20) NOT NULL,
  `senha` VARCHAR(350) NOT NULL,
  `data_cadastro` DATE NOT NULL,
  `data_alteracao` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `pessoa_codigo` INT(11) NOT NULL,
  PRIMARY KEY (`codigo`),
  UNIQUE INDEX `usuario_UNIQUE` (`user` ASC),
  INDEX `fk_usuario_pessoa1_idx` (`pessoa_codigo` ASC),
  UNIQUE INDEX `pessoa_codigo_UNIQUE` (`pessoa_codigo` ASC),
  CONSTRAINT `fk_usuario_pessoa1`
    FOREIGN KEY (`pessoa_codigo`)
    REFERENCES `pdv`.`pessoa` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pdv`.`caixa`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pdv`.`caixa` (
  `codigo` INT(11) NOT NULL AUTO_INCREMENT,
  `descricao` VARCHAR(250) NOT NULL,
  `valor_abertura` DOUBLE NOT NULL DEFAULT 0,
  `valor_total` DOUBLE NULL,
  `valor_fechamento` DOUBLE NULL DEFAULT 0,
  `valor_entrada` DOUBLE NULL,
  `valor_saida` DOUBLE NULL,
  `tipo` VARCHAR(10) NOT NULL,
  `usuario_codigo` INT(11) NOT NULL,
  `data_cadastro` DATE NOT NULL,
  `data_fechamento` DATETIME NULL,
  `data_alteracao` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `agencia` VARCHAR(6) NULL,
  `conta` VARCHAR(10) NULL,
  PRIMARY KEY (`codigo`),
  INDEX `fk_caixa_usuario1_idx` (`usuario_codigo` ASC),
  CONSTRAINT `fk_caixa_usuario1`
    FOREIGN KEY (`usuario_codigo`)
    REFERENCES `pdv`.`usuario` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pdv`.`pagartipo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pdv`.`pagartipo` (
  `codigo` INT(11) NOT NULL AUTO_INCREMENT,
  `descricao` VARCHAR(200) NOT NULL,
  `data_cadastro` DATETIME NOT NULL,
  PRIMARY KEY (`codigo`))
ENGINE = InnoDB
COMMENT = ' Esta tabela sera responsável or guardar os tipos de pagar, ex: FORNECEDOR, MERCADO, FUNCIONARIO.';


-- -----------------------------------------------------
-- Table `pdv`.`pagar`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pdv`.`pagar` (
  `codigo` INT(11) NOT NULL AUTO_INCREMENT,
  `observacao` VARCHAR(255) NULL,
  `valor_total` DOUBLE NOT NULL,
  `data_cadastro` DATE NOT NULL,
  `data_alteracao` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `fornecedor_codigo` INT(11) NOT NULL,
  `pagartipo_codigo` INT(11) NOT NULL,
  PRIMARY KEY (`codigo`),
  INDEX `fk_pagar_fornecedor1_idx` (`fornecedor_codigo` ASC),
  INDEX `fk_pagar_pagartipo1_idx` (`pagartipo_codigo` ASC),
  CONSTRAINT `fk_pagar_fornecedor1`
    FOREIGN KEY (`fornecedor_codigo`)
    REFERENCES `pdv`.`fornecedor` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_pagar_pagartipo1`
    FOREIGN KEY (`pagartipo_codigo`)
    REFERENCES `pdv`.`pagartipo` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pdv`.`parcela_pagar`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pdv`.`parcela_pagar` (
  `codigo` INT NOT NULL AUTO_INCREMENT,
  `valor_total` DOUBLE NOT NULL,
  `valor_desconto` DOUBLE NULL,
  `valor_acrescimo` DOUBLE NULL,
  `valor_pago` DOUBLE NULL,
  `valor_restante` DOUBLE NOT NULL,
  `data_cadastro` DATETIME NOT NULL,
  `data_alteracao` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `data_vencimento` DATE NULL,
  `data_pagamento` DATETIME NULL,
  `quitado` INT NOT NULL,
  `pagar_codigo` INT(11) NOT NULL,
  PRIMARY KEY (`codigo`),
  INDEX `fk_parcela_pagar_pagar1_idx` (`pagar_codigo` ASC),
  CONSTRAINT `fk_parcela_pagar_pagar1`
    FOREIGN KEY (`pagar_codigo`)
    REFERENCES `pdv`.`pagar` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pdv`.`titulo_tipo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pdv`.`titulo_tipo` (
  `codigo` INT(11) NOT NULL,
  `descricao` VARCHAR(100) NOT NULL,
  `sigla` VARCHAR(12) NOT NULL,
  PRIMARY KEY (`codigo`),
  UNIQUE INDEX `sigla_UNIQUE` (`sigla` ASC))
ENGINE = InnoDB
COMMENT = ' Esta tabela contera as informações dos tipos de titulos, DIN, CART CRED, CART DEBI.';


-- -----------------------------------------------------
-- Table `pdv`.`maquina_cartao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pdv`.`maquina_cartao` (
  `codigo` INT(11) NOT NULL AUTO_INCREMENT,
  `descricao` VARCHAR(100) NOT NULL,
  `taxa_debito` DOUBLE NULL,
  `taxa_credito` DOUBLE NULL,
  `dias_debito` INT(11) NULL,
  `dias_credito` INT(11) NULL,
  `taxa_antecipacao` DOUBLE NULL,
  `data_alteracao` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `banco_codigo` INT(11) NOT NULL,
  PRIMARY KEY (`codigo`),
  INDEX `fk_maquina_cartao_caixa1_idx` (`banco_codigo` ASC),
  CONSTRAINT `fk_maquina_cartao_caixa1`
    FOREIGN KEY (`banco_codigo`)
    REFERENCES `pdv`.`caixa` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pdv`.`titulo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pdv`.`titulo` (
  `codigo` INT(11) NOT NULL AUTO_INCREMENT,
  `descricao` VARCHAR(100) NOT NULL,
  `tipo_codigo` INT(11) NOT NULL,
  `maquina_cartao_codigo` INT(11) NULL,
  PRIMARY KEY (`codigo`),
  INDEX `fk_titulo_titulo_tipo1_idx` (`tipo_codigo` ASC),
  INDEX `fk_titulo_maquina_cartao1_idx` (`maquina_cartao_codigo` ASC),
  CONSTRAINT `fk_titulo_titulo_tipo1`
    FOREIGN KEY (`tipo_codigo`)
    REFERENCES `pdv`.`titulo_tipo` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_titulo_maquina_cartao1`
    FOREIGN KEY (`maquina_cartao_codigo`)
    REFERENCES `pdv`.`maquina_cartao` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pdv`.`recebimento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pdv`.`recebimento` (
  `codigo` INT(11) NOT NULL AUTO_INCREMENT,
  `valor_total` DOUBLE NOT NULL,
  `valor_desconto` DOUBLE NULL,
  `valor_acrescimo` DOUBLE NULL,
  `valor_recebido` DOUBLE NULL,
  `pessoa_codigo` INT(11) NOT NULL,
  `titulo_codigo` INT(11) NULL,
  `data_cadastro` DATETIME NOT NULL,
  `data_processamento` DATETIME NULL,
  `data_alteracao` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`codigo`),
  INDEX `fk_recebimento_pessoa1_idx` (`pessoa_codigo` ASC),
  INDEX `fk_recebimento_titulo1_idx` (`titulo_codigo` ASC),
  CONSTRAINT `fk_recebimento_pessoa1`
    FOREIGN KEY (`pessoa_codigo`)
    REFERENCES `pdv`.`pessoa` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_recebimento_titulo1`
    FOREIGN KEY (`titulo_codigo`)
    REFERENCES `pdv`.`titulo` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = ' Esta tabela é responsável por guardar as informações da rotina de recebimento de parcelas do receber.';


-- -----------------------------------------------------
-- Table `pdv`.`caixa_lancamento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pdv`.`caixa_lancamento` (
  `codigo` INT(11) NOT NULL AUTO_INCREMENT,
  `valor` DOUBLE NOT NULL,
  `observacao` VARCHAR(250) NULL,
  `tipo` VARCHAR(45) NOT NULL,
  `estilo` VARCHAR(10) NOT NULL,
  `data_cadastro` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `data_alteracao` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `caixa_codigo` INT(11) NOT NULL,
  `usuario_codigo` INT(11) NOT NULL,
  `parcela_pagar_codigo` INT(11) NULL,
  `recebimento_codigo` INT(11) NULL,
  PRIMARY KEY (`codigo`),
  INDEX `fk_caixa_lancamento_caixa1_idx` (`caixa_codigo` ASC),
  INDEX `fk_caixa_lancamento_parcela_pagar1_idx` (`parcela_pagar_codigo` ASC),
  INDEX `fk_caixa_lancamento_usuario1_idx` (`usuario_codigo` ASC),
  INDEX `fk_caixa_lancamento_recebimento1_idx` (`recebimento_codigo` ASC),
  CONSTRAINT `fk_caixa_lancamento_caixa1`
    FOREIGN KEY (`caixa_codigo`)
    REFERENCES `pdv`.`caixa` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_caixa_lancamento_parcela_pagar1`
    FOREIGN KEY (`parcela_pagar_codigo`)
    REFERENCES `pdv`.`parcela_pagar` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_caixa_lancamento_usuario1`
    FOREIGN KEY (`usuario_codigo`)
    REFERENCES `pdv`.`usuario` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_caixa_lancamento_recebimento1`
    FOREIGN KEY (`recebimento_codigo`)
    REFERENCES `pdv`.`recebimento` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pdv`.`venda`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pdv`.`venda` (
  `codigo` INT(11) NOT NULL AUTO_INCREMENT,
  `observacao` VARCHAR(250) NULL,
  `valor_produtos` DOUBLE NULL,
  `valor_desconto` DOUBLE NULL,
  `valor_acrescimo` DOUBLE NULL,
  `valor_total` DOUBLE NULL,
  `situacao` VARCHAR(45) NOT NULL,
  `pessoa_codigo` INT(11) NULL,
  `usuario_codigo` INT(11) NOT NULL,
  `data_finalizado` DATETIME NULL,
  `data_cancelado` DATETIME NULL,
  `data_cadastro` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`codigo`),
  INDEX `fk_pedido_pessoa1_idx` (`pessoa_codigo` ASC),
  INDEX `fk_pedido_usuario1_idx` (`usuario_codigo` ASC),
  CONSTRAINT `fk_pedido_pessoa1`
    FOREIGN KEY (`pessoa_codigo`)
    REFERENCES `pdv`.`pessoa` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_pedido_usuario1`
    FOREIGN KEY (`usuario_codigo`)
    REFERENCES `pdv`.`usuario` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pdv`.`venda_produtos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pdv`.`venda_produtos` (
  `codigo` INT(11) NOT NULL AUTO_INCREMENT,
  `produto_codigo` INT(11) NOT NULL,
  `venda_codigo` INT(11) NOT NULL,
  `valor_balanca` DOUBLE NULL,
  `data_cadastro` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`codigo`, `produto_codigo`),
  INDEX `fk_pedido_produtos_has_produto_produto1_idx` (`produto_codigo` ASC),
  INDEX `fk_pedido_produtos_has_produto_pedido1_idx` (`venda_codigo` ASC),
  CONSTRAINT `fk_pedido_produtos_has_produto_produto1`
    FOREIGN KEY (`produto_codigo`)
    REFERENCES `pdv`.`produto` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_pedido_produtos_has_produto_pedido1`
    FOREIGN KEY (`venda_codigo`)
    REFERENCES `pdv`.`venda` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pdv`.`grupousuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pdv`.`grupousuario` (
  `codigo` INT(11) NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NULL,
  `descricao` VARCHAR(250) NOT NULL,
  `data_alteracao` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`codigo`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pdv`.`permissoes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pdv`.`permissoes` (
  `codigo` INT(11) NOT NULL,
  `nome` VARCHAR(45) NOT NULL,
  `descricao` VARCHAR(250) NOT NULL,
  `data_alteracao` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`codigo`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pdv`.`permissoes_grupo_usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pdv`.`permissoes_grupo_usuario` (
  `codigo` INT(11) NOT NULL AUTO_INCREMENT,
  `grupo_usuario_codigo` INT(11) NOT NULL,
  `permissoes_codigo` INT(11) NOT NULL,
  INDEX `fk_grupousuario_has_permissoes_permissoes1_idx` (`permissoes_codigo` ASC),
  INDEX `fk_grupousuario_has_permissoes_grupousuario1_idx` (`grupo_usuario_codigo` ASC),
  PRIMARY KEY (`codigo`),
  CONSTRAINT `fk_grupousuario_has_permissoes_grupousuario1`
    FOREIGN KEY (`grupo_usuario_codigo`)
    REFERENCES `pdv`.`grupousuario` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_grupousuario_has_permissoes_permissoes1`
    FOREIGN KEY (`permissoes_codigo`)
    REFERENCES `pdv`.`permissoes` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pdv`.`usuario_permissoes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pdv`.`usuario_permissoes` (
  `usuario_codigo` INT(11) NOT NULL,
  `permissoes_codigo` INT(11) NOT NULL,
  PRIMARY KEY (`usuario_codigo`, `permissoes_codigo`),
  INDEX `fk_usuario_has_permissoes_permissoes1_idx` (`permissoes_codigo` ASC),
  INDEX `fk_usuario_has_permissoes_usuario1_idx` (`usuario_codigo` ASC),
  CONSTRAINT `fk_usuario_has_permissoes_usuario1`
    FOREIGN KEY (`usuario_codigo`)
    REFERENCES `pdv`.`usuario` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_usuario_has_permissoes_permissoes1`
    FOREIGN KEY (`permissoes_codigo`)
    REFERENCES `pdv`.`permissoes` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pdv`.`usuario_grupousuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pdv`.`usuario_grupousuario` (
  `codigo` INT(11) NOT NULL AUTO_INCREMENT,
  `grupo_usuario_codigo` INT(11) NOT NULL,
  `usuario_codigo` INT(11) NOT NULL,
  PRIMARY KEY (`codigo`, `grupo_usuario_codigo`, `usuario_codigo`),
  INDEX `fk_grupousuario_has_usuario_usuario1_idx` (`usuario_codigo` ASC),
  INDEX `fk_grupousuario_has_usuario_grupousuario1_idx` (`grupo_usuario_codigo` ASC),
  CONSTRAINT `fk_grupousuario_has_usuario_grupousuario1`
    FOREIGN KEY (`grupo_usuario_codigo`)
    REFERENCES `pdv`.`grupousuario` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_grupousuario_has_usuario_usuario1`
    FOREIGN KEY (`usuario_codigo`)
    REFERENCES `pdv`.`usuario` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pdv`.`pagamento_tipo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pdv`.`pagamento_tipo` (
  `codigo` INT(11) NOT NULL AUTO_INCREMENT,
  `descricao` VARCHAR(45) NOT NULL,
  `forma_pagamento` VARCHAR(250) NOT NULL,
  `qtd_parcelas` INT NOT NULL,
  `data_cadastro` DATE NOT NULL,
  `data_alteracao` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '	',
  PRIMARY KEY (`codigo`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pdv`.`pagamento_tipo_venda`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pdv`.`pagamento_tipo_venda` (
  `ven_codigo` INT(11) NOT NULL,
  `pag_tipo_codigo` INT(11) NOT NULL,
  PRIMARY KEY (`ven_codigo`, `pag_tipo_codigo`),
  INDEX `fk_pagamento_tipo_has_venda_venda1_idx` (`ven_codigo` ASC),
  INDEX `fk_pagamento_tipo_has_venda_pagamento_tipo1_idx` (`pag_tipo_codigo` ASC),
  CONSTRAINT `fk_pagamento_tipo_has_venda_pagamento_tipo1`
    FOREIGN KEY (`pag_tipo_codigo`)
    REFERENCES `pdv`.`pagamento_tipo` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_pagamento_tipo_has_venda_venda1`
    FOREIGN KEY (`ven_codigo`)
    REFERENCES `pdv`.`venda` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pdv`.`receber`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pdv`.`receber` (
  `codigo` INT(11) NOT NULL AUTO_INCREMENT,
  `observacao` VARCHAR(255) NOT NULL,
  `valor_total` DOUBLE NOT NULL,
  `pessoa_codigo` INT(11) NULL,
  `data_cadastro` DATETIME NOT NULL,
  `data_alteracao` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '				',
  `venda_codigo` INT(11) NOT NULL,
  PRIMARY KEY (`codigo`, `venda_codigo`),
  INDEX `fk_Receber_pessoa1_idx` (`pessoa_codigo` ASC),
  INDEX `fk_receber_venda1_idx` (`venda_codigo` ASC),
  CONSTRAINT `fk_Receber_pessoa1`
    FOREIGN KEY (`pessoa_codigo`)
    REFERENCES `pdv`.`pessoa` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_receber_venda1`
    FOREIGN KEY (`venda_codigo`)
    REFERENCES `pdv`.`venda` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = 'Tabela para gravar os titulos a receber dos clientes.';


-- -----------------------------------------------------
-- Table `pdv`.`parcela`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pdv`.`parcela` (
  `codigo` INT(11) NOT NULL AUTO_INCREMENT,
  `valor_total` DOUBLE NOT NULL,
  `valor_desconto` DOUBLE NULL,
  `valor_acrescimo` DOUBLE NULL,
  `valor_recebido` DOUBLE NULL,
  `valor_restante` DOUBLE NOT NULL,
  `data_cadastro` DATETIME NOT NULL,
  `data_alteracao` TIMESTAMP NOT NULL,
  `data_vencimento` DATETIME NOT NULL,
  `data_pagamento` DATETIME NULL,
  `receber_codigo` INT(11) NOT NULL,
  `quitado` INT NULL,
  `sequencia` INT NOT NULL,
  PRIMARY KEY (`codigo`),
  INDEX `fk_parcela_receber1_idx` (`receber_codigo` ASC),
  CONSTRAINT `fk_parcela_receber1`
    FOREIGN KEY (`receber_codigo`)
    REFERENCES `pdv`.`receber` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pdv`.`transferencia`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pdv`.`transferencia` (
  `codigo` INT(11) NOT NULL AUTO_INCREMENT,
  `valor` DOUBLE NOT NULL,
  `observacao` VARCHAR(255) NOT NULL,
  `origem_codigo` INT(11) NOT NULL,
  `destino_codigo` INT(11) NOT NULL,
  `usuario_codigo` INT(11) NOT NULL,
  `data_transferencia` DATETIME NOT NULL,
  `data_alteracao` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`codigo`),
  INDEX `fk_transferencia_usuario1_idx` (`usuario_codigo` ASC),
  INDEX `fk_transferencia_caixa1_idx` (`origem_codigo` ASC),
  INDEX `fk_transferencia_caixa2_idx` (`destino_codigo` ASC),
  CONSTRAINT `fk_transferencia_usuario1`
    FOREIGN KEY (`usuario_codigo`)
    REFERENCES `pdv`.`usuario` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_transferencia_caixa1`
    FOREIGN KEY (`origem_codigo`)
    REFERENCES `pdv`.`caixa` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_transferencia_caixa2`
    FOREIGN KEY (`destino_codigo`)
    REFERENCES `pdv`.`caixa` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = 'Tabela criada com o objetivo de ser utilizada para guardar os dados de transferencias entre caixa e cofre.';


-- -----------------------------------------------------
-- Table `pdv`.`recebimento_parcelas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pdv`.`recebimento_parcelas` (
  `codigo` INT(11) NOT NULL AUTO_INCREMENT,
  `recebimento_cod` INT(11) NOT NULL,
  `parcela_cod` INT(11) NOT NULL,
  PRIMARY KEY (`codigo`, `recebimento_cod`, `parcela_cod`),
  INDEX `fk_recebimento_has_parcela_parcela1_idx` (`parcela_cod` ASC),
  INDEX `fk_recebimento_has_parcela_recebimento1_idx` (`recebimento_cod` ASC),
  CONSTRAINT `fk_recebimento_has_parcela_recebimento1`
    FOREIGN KEY (`recebimento_cod`)
    REFERENCES `pdv`.`recebimento` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_recebimento_has_parcela_parcela1`
    FOREIGN KEY (`parcela_cod`)
    REFERENCES `pdv`.`parcela` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pdv`.`cst_csosn`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pdv`.`cst_csosn` (
  `codigo` INT(11) NOT NULL AUTO_INCREMENT,
  `cst_csosn` VARCHAR(12) NOT NULL,
  `simples_nacional` INT NOT NULL,
  PRIMARY KEY (`codigo`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pdv`.`cst`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pdv`.`cst` (
  `codigo` INT(11) NOT NULL AUTO_INCREMENT,
  `cst` VARCHAR(12) NOT NULL,
  `descricao` VARCHAR(250) NOT NULL,
  PRIMARY KEY (`codigo`))
ENGINE = InnoDB
COMMENT = ' Esta tabela guardara os códigos cest do pis e cofins';


-- -----------------------------------------------------
-- Table `pdv`.`cfop`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pdv`.`cfop` (
  `codigo` INT(11) NOT NULL AUTO_INCREMENT,
  `cfop` VARCHAR(5) NOT NULL,
  `descricao` VARCHAR(255) NOT NULL,
  `aplicacao` VARCHAR(1500) NULL,
  PRIMARY KEY (`codigo`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pdv`.`cst_ipi`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pdv`.`cst_ipi` (
  `codigo` INT(11) NOT NULL AUTO_INCREMENT,
  `cst` VARCHAR(10) NOT NULL,
  `descricao` VARCHAR(100) NOT NULL,
  `tipo` VARCHAR(8) NOT NULL,
  PRIMARY KEY (`codigo`))
ENGINE = InnoDB
COMMENT = 'O campo tipo guarda a informação se o mesmo é ENTRADA ou SAÍDA.';


-- -----------------------------------------------------
-- Table `pdv`.`tributacao_regra`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pdv`.`tributacao_regra` (
  `codigo` INT(11) NOT NULL AUTO_INCREMENT,
  `tributacao_codigo` INT(11) NOT NULL,
  `uf` VARCHAR(3) NOT NULL,
  `pis` DOUBLE NULL,
  `cofins` DOUBLE NULL,
  `cst_pis` INT(11) NULL,
  `cst_cofins` INT(11) NULL,
  `aliq_ipi` DOUBLE NULL,
  `aliq_icms` DOUBLE NULL,
  `tipo` VARCHAR(7) NOT NULL,
  `cst_csosn_codigo` INT(11) NOT NULL,
  `cfop_codigo` INT(11) NOT NULL,
  `cst_ipi_codigo` INT(11) NULL,
  `data_cadastro` DATE NOT NULL,
  `data_alteracao` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  INDEX `fk_tributacao_regra_tributacao1_idx` (`tributacao_codigo` ASC),
  INDEX `fk_tributacao_regra_cst_csosn1_idx` (`cst_csosn_codigo` ASC),
  INDEX `fk_tributacao_regra_cest1_idx` (`cst_pis` ASC),
  INDEX `fk_tributacao_regra_cest2_idx` (`cst_cofins` ASC),
  INDEX `fk_tributacao_regra_cfop1_idx` (`cfop_codigo` ASC),
  PRIMARY KEY (`codigo`),
  INDEX `fk_tributacao_regra_cst_ipi1_idx` (`cst_ipi_codigo` ASC),
  CONSTRAINT `fk_tributacao_regra_tributacao1`
    FOREIGN KEY (`tributacao_codigo`)
    REFERENCES `pdv`.`tributacao` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tributacao_regra_cst_csosn1`
    FOREIGN KEY (`cst_csosn_codigo`)
    REFERENCES `pdv`.`cst_csosn` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tributacao_regra_cest1`
    FOREIGN KEY (`cst_pis`)
    REFERENCES `pdv`.`cst` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tributacao_regra_cest2`
    FOREIGN KEY (`cst_cofins`)
    REFERENCES `pdv`.`cst` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tributacao_regra_cfop1`
    FOREIGN KEY (`cfop_codigo`)
    REFERENCES `pdv`.`cfop` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tributacao_regra_cst_ipi1`
    FOREIGN KEY (`cst_ipi_codigo`)
    REFERENCES `pdv`.`cst_ipi` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = 'O campo tipo define se é entrada ou saída';


-- -----------------------------------------------------
-- Table `pdv`.`mva`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pdv`.`mva` (
  `codigo` INT(11) NOT NULL AUTO_INCREMENT,
  `uf` VARCHAR(3) NOT NULL,
  `mva` INT(11) NOT NULL,
  `data_cadastro` DATE NOT NULL,
  `data_alteracao` TIMESTAMP NOT NULL,
  `produto_codigo` INT(11) NOT NULL,
  PRIMARY KEY (`codigo`),
  INDEX `fk_mva_produto1_idx` (`produto_codigo` ASC),
  CONSTRAINT `fk_mva_produto1`
    FOREIGN KEY (`produto_codigo`)
    REFERENCES `pdv`.`produto` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pdv`.`nota_fiscal`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pdv`.`nota_fiscal` (
  `codigo` INT(11) NOT NULL AUTO_INCREMENT,
  `numero` INT(11) NOT NULL,
  `modelo` INT NOT NULL,
  `tipo` VARCHAR(8) NOT NULL,
  `chave_acesso` VARCHAR(50) NULL,
  `natureza_operacao` VARCHAR(255) NOT NULL,
  `serie` INT NOT NULL,
  `situacao` VARCHAR(45) NULL,
  `emissor_codigo` INT(11) NOT NULL,
  `destinatario_codigo` INT(11) NOT NULL,
  `data_emissao` DATE NULL,
  `data_saida` DATE NULL,
  `hora_saida` TIME NULL,
  `data_alteracao` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `data_cadastro` DATE NOT NULL,
  `tipo_emissao` INT NULL,
  `totais_codigo` INT(11) NOT NULL,
  `tipo_impressao` INT NULL,
  `cDV` VARCHAR(45) NULL,
  `procEmi` INT NULL,
  `verProc` VARCHAR(255) NULL,
  `frete_tipo_codigo` INT(11) NOT NULL,
  `finalidade_codigo` INT(11) NOT NULL,
  `tipo_ambiente` INT NULL,
  PRIMARY KEY (`codigo`, `totais_codigo`),
  UNIQUE INDEX `chave_acesso_UNIQUE` (`chave_acesso` ASC),
  INDEX `fk_nota_fiscal_empresa1_idx` (`emissor_codigo` ASC),
  INDEX `fk_nota_fiscal_pessoa1_idx` (`destinatario_codigo` ASC),
  INDEX `fk_nota_fiscal_nota_fiscal_totais1_idx` (`totais_codigo` ASC),
  INDEX `fk_nota_fiscal_nota_fiscal_frete1_idx` (`frete_tipo_codigo` ASC),
  INDEX `fk_nota_fiscal_nota_fiscal_finalidade1_idx` (`finalidade_codigo` ASC),
  CONSTRAINT `fk_nota_fiscal_empresa10`
    FOREIGN KEY (`emissor_codigo`)
    REFERENCES `pdv`.`empresa` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_nota_fiscal_pessoa10`
    FOREIGN KEY (`destinatario_codigo`)
    REFERENCES `pdv`.`pessoa` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_nota_fiscal_nota_fiscal_totais1`
    FOREIGN KEY (`totais_codigo`)
    REFERENCES `pdv`.`nota_fiscal_totais` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_nota_fiscal_nota_fiscal_frete1`
    FOREIGN KEY (`frete_tipo_codigo`)
    REFERENCES `pdv`.`frete_tipo` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_nota_fiscal_nota_fiscal_finalidade1`
    FOREIGN KEY (`finalidade_codigo`)
    REFERENCES `pdv`.`nota_fiscal_finalidade` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = 'tipo_emissao é pra tag tpEmis e pode ter os seguintes valores: 1 - Normal, 2 - contigência, 3';


-- -----------------------------------------------------
-- Table `pdv`.`nota_fiscal_totais`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pdv`.`nota_fiscal_totais` (
  `codigo` INT(11) NOT NULL AUTO_INCREMENT,
  `v_bc` DOUBLE NOT NULL,
  `v_icms` DOUBLE NOT NULL,
  `v_st` DOUBLE NOT NULL,
  `v_prod` DOUBLE NOT NULL,
  `v_frete` DOUBLE NOT NULL,
  `v_seg` DOUBLE NOT NULL,
  `v_desc` DOUBLE NOT NULL,
  `v_ii` DOUBLE NOT NULL,
  `v_ipi` DOUBLE NOT NULL,
  `v_pis` DOUBLE NOT NULL,
  `v_cofins` DOUBLE NOT NULL,
  `v_outros` DOUBLE NOT NULL,
  `v_nf` DOUBLE NOT NULL,
  PRIMARY KEY (`codigo`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pdv`.`frete_tipo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pdv`.`frete_tipo` (
  `codigo` INT(11) NOT NULL AUTO_INCREMENT,
  `tipo` INT NOT NULL,
  `descricao` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`codigo`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pdv`.`nota_fiscal_finalidade`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pdv`.`nota_fiscal_finalidade` (
  `codigo` INT(11) NOT NULL AUTO_INCREMENT,
  `tipo` INT NOT NULL,
  `descricao` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`codigo`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pdv`.`nota_fiscal`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pdv`.`nota_fiscal` (
  `codigo` INT(11) NOT NULL AUTO_INCREMENT,
  `numero` INT(11) NOT NULL,
  `modelo` INT NOT NULL,
  `tipo` VARCHAR(8) NOT NULL,
  `chave_acesso` VARCHAR(50) NULL,
  `natureza_operacao` VARCHAR(255) NOT NULL,
  `serie` INT NOT NULL,
  `situacao` VARCHAR(45) NULL,
  `emissor_codigo` INT(11) NOT NULL,
  `destinatario_codigo` INT(11) NOT NULL,
  `data_emissao` DATE NULL,
  `data_saida` DATE NULL,
  `hora_saida` TIME NULL,
  `data_alteracao` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `data_cadastro` DATE NOT NULL,
  `tipo_emissao` INT NULL,
  `totais_codigo` INT(11) NOT NULL,
  `tipo_impressao` INT NULL,
  `cDV` VARCHAR(45) NULL,
  `procEmi` INT NULL,
  `verProc` VARCHAR(255) NULL,
  `frete_tipo_codigo` INT(11) NOT NULL,
  `finalidade_codigo` INT(11) NOT NULL,
  `tipo_ambiente` INT NULL,
  PRIMARY KEY (`codigo`, `totais_codigo`),
  UNIQUE INDEX `chave_acesso_UNIQUE` (`chave_acesso` ASC),
  INDEX `fk_nota_fiscal_empresa1_idx` (`emissor_codigo` ASC),
  INDEX `fk_nota_fiscal_pessoa1_idx` (`destinatario_codigo` ASC),
  INDEX `fk_nota_fiscal_nota_fiscal_totais1_idx` (`totais_codigo` ASC),
  INDEX `fk_nota_fiscal_nota_fiscal_frete1_idx` (`frete_tipo_codigo` ASC),
  INDEX `fk_nota_fiscal_nota_fiscal_finalidade1_idx` (`finalidade_codigo` ASC),
  CONSTRAINT `fk_nota_fiscal_empresa10`
    FOREIGN KEY (`emissor_codigo`)
    REFERENCES `pdv`.`empresa` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_nota_fiscal_pessoa10`
    FOREIGN KEY (`destinatario_codigo`)
    REFERENCES `pdv`.`pessoa` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_nota_fiscal_nota_fiscal_totais1`
    FOREIGN KEY (`totais_codigo`)
    REFERENCES `pdv`.`nota_fiscal_totais` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_nota_fiscal_nota_fiscal_frete1`
    FOREIGN KEY (`frete_tipo_codigo`)
    REFERENCES `pdv`.`frete_tipo` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_nota_fiscal_nota_fiscal_finalidade1`
    FOREIGN KEY (`finalidade_codigo`)
    REFERENCES `pdv`.`nota_fiscal_finalidade` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = 'tipo_emissao é pra tag tpEmis e pode ter os seguintes valores: 1 - Normal, 2 - contigência, 3';


-- -----------------------------------------------------
-- Table `pdv`.`nota_fiscal_item_imposto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pdv`.`nota_fiscal_item_imposto` (
  `codigo` INT(11) NOT NULL AUTO_INCREMENT,
  `orig` INT NULL,
  `cst` INT NULL,
  `mod_bc` INT NULL,
  `v_bc` DOUBLE NULL,
  `p_icms` DOUBLE NULL,
  `v_icms` DOUBLE NULL,
  `cst_pis` INT NULL,
  `vbc_pis` DOUBLE NULL,
  `p_pis` DOUBLE NULL,
  `v_pis` DOUBLE NULL,
  `cst_cofins` INT NULL,
  `vbc_cofins` DOUBLE NULL,
  `p_cofins` DOUBLE NULL,
  `v_cofins` DOUBLE NULL,
  `cst_ipi` INT NULL,
  `vbc_ipi` DOUBLE NULL,
  `p_ipi` DOUBLE NULL,
  `v_ipi` DOUBLE NULL,
  PRIMARY KEY (`codigo`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pdv`.`nota_fiscal_item`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pdv`.`nota_fiscal_item` (
  `codigo` INT(11) NOT NULL AUTO_INCREMENT,
  `cod_prod` INT(11) NOT NULL,
  `qtd` INT NOT NULL,
  `vl_total` DOUBLE NOT NULL,
  `ceantrib` VARCHAR(255) NULL,
  `unidade_tribu` VARCHAR(12) NOT NULL,
  `qtd_tribu` INT NOT NULL,
  `vl_uni_tribu` DOUBLE NOT NULL,
  `nota_fiscal_codigo` INT(11) NOT NULL,
  `imposto_codigo` INT(11) NOT NULL,
  `cfop` VARCHAR(12) NOT NULL,
  PRIMARY KEY (`codigo`, `imposto_codigo`),
  INDEX `fk_nota_fiscal_item_nota_fiscal1_idx` (`nota_fiscal_codigo` ASC),
  INDEX `fk_nota_fiscal_item_nota_fiscal_item_imposto1_idx` (`imposto_codigo` ASC),
  CONSTRAINT `fk_nota_fiscal_item_nota_fiscal1`
    FOREIGN KEY (`nota_fiscal_codigo`)
    REFERENCES `pdv`.`nota_fiscal` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_nota_fiscal_item_nota_fiscal_item_imposto1`
    FOREIGN KEY (`imposto_codigo`)
    REFERENCES `pdv`.`nota_fiscal_item_imposto` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pdv`.`cartao_lancamento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pdv`.`cartao_lancamento` (
  `codigo` INT(11) NOT NULL AUTO_INCREMENT,
  `vl_parcela` DOUBLE NOT NULL,
  `taxa` DOUBLE NOT NULL,
  `vl_taxa` DOUBLE NOT NULL,
  `vl_liq_parcela` DOUBLE NOT NULL,
  `taxa_antecipacao` DOUBLE NOT NULL,
  `vl_taxa_antecipacao` DOUBLE NOT NULL,
  `vl_liq_antecipacao` DOUBLE NOT NULL,
  `maquina_cartao_codigo` INT(11) NOT NULL,
  `situacao` VARCHAR(15) NOT NULL,
  `tipo` VARCHAR(10) NOT NULL,
  `data_recebimento` DATE NOT NULL,
  `data_cadastro` DATE NOT NULL,
  `data_alteracao` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`codigo`),
  INDEX `fk_cartao_gerencial_maquina_cartao1_idx` (`maquina_cartao_codigo` ASC),
  CONSTRAINT `fk_cartao_gerencial_maquina_cartao1`
    FOREIGN KEY (`maquina_cartao_codigo`)
    REFERENCES `pdv`.`maquina_cartao` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = ' Nesta tabela serão gurdados os registro dos cartões quando for realizado um venda com este tipo de titulo.\n\n- O campo tipo guardara a informação: DEBITO, CREDITO.';


-- -----------------------------------------------------
-- Table `pdv`.`estoque_movimentacao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pdv`.`estoque_movimentacao` (
  `codigo` INT(11) NOT NULL AUTO_INCREMENT,
  `produto_codigo` INT(11) NOT NULL,
  `tipo` VARCHAR(10) NOT NULL,
  `qtd` INT NOT NULL,
  `origem_operacao` VARCHAR(100) NOT NULL,
  `data_movimentacao` DATE NOT NULL,
  `data_alteracao` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`codigo`),
  INDEX `fk_estoque_movimentacao_produto1_idx` (`produto_codigo` ASC),
  CONSTRAINT `fk_estoque_movimentacao_produto1`
    FOREIGN KEY (`produto_codigo`)
    REFERENCES `pdv`.`produto` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = ' Registra as movimentações de entrada e saída dos produtos.\n\n Campo TIPO, guarda a informação se é ENTRADA ou SAÍDA.';


-- -----------------------------------------------------
-- Table `pdv`.`produto_estoque`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pdv`.`produto_estoque` (
  `codigo` INT(11) NOT NULL AUTO_INCREMENT,
  `produto_codigo` INT(11) NOT NULL,
  `qtd` INT NOT NULL,
  `data_alteracao` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`codigo`),
  INDEX `fk_produto_estoque_produto1_idx` (`produto_codigo` ASC),
  UNIQUE INDEX `produto_codigo_UNIQUE` (`produto_codigo` ASC),
  CONSTRAINT `fk_produto_estoque_produto1`
    FOREIGN KEY (`produto_codigo`)
    REFERENCES `pdv`.`produto` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = ' Esta tabela recebe um insert quando a tabela estoque_movimentacao recebe um insert.';


-- -----------------------------------------------------
-- Table `pdv`.`ajuste`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pdv`.`ajuste` (
  `codigo` INT NOT NULL AUTO_INCREMENT,
  `observacao` VARCHAR(255) NULL,
  `status` VARCHAR(15) NOT NULL,
  `usuario` VARCHAR(100) NOT NULL,
  `data_cadastro` DATE NOT NULL,
  `data_processamento` DATE NULL,
  `data_alteracao` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`codigo`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pdv`.`ajuste_produtos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pdv`.`ajuste_produtos` (
  `codigo` INT(11) NOT NULL AUTO_INCREMENT,
  `ajuste_codigo` INT NOT NULL,
  `produto_codigo` INT(11) NOT NULL,
  `estoque_atual` INT NOT NULL,
  `qtd_alteracao` INT NULL,
  `qtd_nova` INT NULL,
  `data_alteracao` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`codigo`),
  INDEX `fk_ajuste_produtos_produto1_idx` (`produto_codigo` ASC),
  INDEX `fk_ajuste_produtos_ajuste1_idx` (`ajuste_codigo` ASC),
  CONSTRAINT `fk_ajuste_produtos_produto1`
    FOREIGN KEY (`produto_codigo`)
    REFERENCES `pdv`.`produto` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ajuste_produtos_ajuste1`
    FOREIGN KEY (`ajuste_codigo`)
    REFERENCES `pdv`.`ajuste` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

USE `pdv`;

DELIMITER $$
USE `pdv`$$
CREATE DEFINER = CURRENT_USER TRIGGER `pdv`.`insere_estoque_inicial_AFTER_INSERT` AFTER INSERT ON `produto` FOR EACH ROW
BEGIN
	SET @codprod = new.codigo;
    
    insert into produto_estoque (produto_codigo, qtd) values (@codprod, 0);

END$$

USE `pdv`$$
CREATE DEFINER = CURRENT_USER TRIGGER `pdv`.`tr_atualizaValoresCaixa_AFTER_INSERT` AFTER INSERT ON `caixa_lancamento` FOR EACH ROW
BEGIN
	SET @valorLancamento = NEW.valor;
    SET @estilo = NEW.estilo;
    SET @caixa = NEW.caixa_codigo;
    
    select coalesce(c.valor_total, 0) into @vlTotalCaixa from caixa c where c.codigo = @caixa;
    
    if(@estilo = "ENTRADA") then
		select coalesce(c.valor_entrada, 0) into @vlEntrada from caixa c where c.codigo = @caixa;
        update caixa set valor_entrada = (@vlEntrada + @valorLancamento), valor_total = (@vlTotalCaixa + @valorLancamento) where codigo = @caixa;
	elseif(@estilo = "SAIDA") then
		select coalesce(c.valor_saida, 0) into @vlSaida from caixa c where c.codigo = @caixa;
        update caixa set valor_saida = (@vlSaida - @valorLancamento), valor_total = (@vlTotalCaixa + @valorLancamento) where codigo = @caixa;
	end if;
END$$

USE `pdv`$$
CREATE DEFINER = CURRENT_USER TRIGGER `pdv`.`tr_atualizaTotalProdutosVenda_AFTER_INSERT` AFTER INSERT ON `venda_produtos` FOR EACH ROW
BEGIN
	SET @codigoVen = NEW.venda_codigo;
    SET @codigoPro = NEW.produto_codigo;
    SET @vlBalanca = NEW.valor_balanca;
    
    select coalesce(v.valor_produtos, 0) INTO @vlProdutos from venda v where v.codigo = @codigoVen;
    
    IF(@vlBalanca > 0) THEN
		update venda set valor_produtos = (@vlProdutos + @vlBalanca) where codigo = @codigoVen;
	else 
		select p.valor_venda INTO @vlVenda from produto p where p.codigo = @codigoPro;
		UPDATE venda set valor_produtos = (@vlProdutos + @vlVenda) where codigo = @codigoVen;
	end if;
END;$$

USE `pdv`$$
CREATE DEFINER = CURRENT_USER TRIGGER `pdv`.`atualizaTotalProdutos_BEFORE_DELETE` BEFORE DELETE ON `venda_produtos` FOR EACH ROW
BEGIN
	SET @codigoVen = OLD.venda_codigo;
    SET @vlBalanca = OLD.valor_balanca;
    SET @codigoProd = OLD.produto_codigo;
    
    select coalesce(v.valor_produtos, 0) into @vlProdutos from venda v where v.codigo = @codigoVen;
    
    if(@vlBalanca > 0) then
		update venda set valor_produtos = (@vlProdutos - @vlBalanca) where codigo = @codigoVen;
	else
		select valor_venda into @vlVenda from produto where codigo = @codigoProd;
        update venda set valor_produtos = (@vlProdutos - @vlVenda) where codigo = @codigoVen;
	end if;
END$$

USE `pdv`$$
CREATE DEFINER = CURRENT_USER TRIGGER `pdv`.`tr_lanca_cai_lancamento_AFTER_INSERT` AFTER INSERT ON `transferencia` FOR EACH ROW
BEGIN
	SET @valor = new.valor;
    SET @origem = new.origem_codigo;
    SET @destino = new.destino_codigo;
    SET @usuario = new.usuario_codigo;
    
	insert caixa_lancamento (valor, observacao, tipo, estilo, caixa_codigo, usuario_codigo, data_cadastro) 
    values (-@valor, 'Saída de transferência', 'TRANSFERENCIA', 'SAIDA', @origem, @usuario, sysdate());
        
    insert caixa_lancamento (valor, observacao, tipo, estilo, caixa_codigo, usuario_codigo, data_cadastro) 
    values (@valor, 'Entrada de transferência', 'TRANSFERENCIA', 'ENTRADA', @destino, @usuario, sysdate());
END$$

USE `pdv`$$
CREATE DEFINER = CURRENT_USER TRIGGER `pdv`.`atualiza_produto_estoque_AFTER_INSERT` AFTER INSERT ON `estoque_movimentacao` FOR EACH ROW
BEGIN
		SET @codprod = new.produto_codigo;
        SET @qtd = new.qtd;
        
        select coalesce(qtd, 0) INTO @qtd_estoque from produto_estoque where produto_codigo = @codprod;
        
        IF(@qtd <= @qtd_estoque) THEN
			SET @novo_estoque = (@qtd_estoque - @qtd);
			update produto_estoque set qtd = @novo_estoque where produto_codigo = @codprod;
		end if;
END;$$


DELIMITER ;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
