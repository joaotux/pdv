-- -----------------------------------------------------
-- Table `pais`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS pais
(
    `codigo`      int          NOT NULL AUTO_INCREMENT,
    `nome`        varchar(100) NOT NULL,
    `pais_codigo` varchar(5)   NOT NULL,
    PRIMARY KEY (`codigo`)
);

-- -----------------------------------------------------
-- Table `estado`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS estado
(
    `codigo`      INT(11)     NOT NULL,
    `sigla`       VARCHAR(3)  NOT NULL,
    `nome`        VARCHAR(45) NOT NULL,
    `codigoUF`    VARCHAR(4)  NOT NULL,
    `pais_codigo` INT(11)     NOT NULL,
    PRIMARY KEY (`codigo`),
    CONSTRAINT `fk_estado_table11`
        FOREIGN KEY (`pais_codigo`)
            REFERENCES `pais` (`codigo`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

-- -----------------------------------------------------
-- Table `cidade`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS cidade
(
    `codigo`           INT(11)     NOT NULL AUTO_INCREMENT,
    `nome`             VARCHAR(45) NOT NULL,
    `estado_codigo`    INT(11)     NOT NULL,
    `codigo_municipio` VARCHAR(12) NOT NULL,
    PRIMARY KEY (`codigo`),
    CONSTRAINT `fk_cidade_estado1`
        FOREIGN KEY (`estado_codigo`)
            REFERENCES `estado` (`codigo`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

-- -----------------------------------------------------
-- Table `endereco`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS endereco
(
    `codigo`         INT(11)      NOT NULL AUTO_INCREMENT,
    `rua`            VARCHAR(60)  NOT NULL,
    `bairro`         VARCHAR(60)  NOT NULL,
    `numero`         VARCHAR(6)   NOT NULL,
    `cep`            VARCHAR(20)  NULL,
    `referencia`     VARCHAR(250) NULL,
    `data_cadastro`  TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `data_alteracao` TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `cidade_codigo`  INT(11)      NOT NULL,
    PRIMARY KEY (`codigo`),
    CONSTRAINT `fk_endereco_cidade1`
        FOREIGN KEY (`cidade_codigo`)
            REFERENCES `cidade` (`codigo`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

-- -----------------------------------------------------
-- Table `telefone`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS telefone
(
    `codigo`         INT(11)     NOT NULL AUTO_INCREMENT,
    `fone`           VARCHAR(15) NOT NULL,
    `tipo`           VARCHAR(10) NULL,
    `data_cadastro`  TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `data_alteracao` TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`codigo`)
);

-- -----------------------------------------------------
-- Table `fornecedor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS fornecedor
(
    `codigo`             INT(11)      NOT NULL AUTO_INCREMENT,
    `nome_fantasia`      VARCHAR(250) NOT NULL,
    `nome`               VARCHAR(250) NOT NULL,
    `cnpj`               VARCHAR(18)  NOT NULL,
    `inscricao_estadual` VARCHAR(40)  NULL,
    `ativo`              INT          NOT NULL,
    `endereco_codigo`    INT(11)      NOT NULL,
    `observacao`         VARCHAR(250) NULL,
    `data_alteracao`     TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `data_cadastro`      TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`codigo`),
    CONSTRAINT `fk_fornecedor_endereco1`
        FOREIGN KEY (`endereco_codigo`)
            REFERENCES `endereco` (`codigo`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

-- -----------------------------------------------------
-- Table `fornecedor_telefone`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS fornecedor_telefone
(
    `codigo`            INT(11) NOT NULL AUTO_INCREMENT,
    `telefone_codigo`   INT(11) NOT NULL,
    `fornecedor_codigo` INT(11) NOT NULL,
    PRIMARY KEY (`codigo`),
    CONSTRAINT `fk_fornecedor_telefone_telefone1`
        FOREIGN KEY (`telefone_codigo`)
            REFERENCES `telefone` (`codigo`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_fornecedor_telefone_fornecedor1`
        FOREIGN KEY (`fornecedor_codigo`)
            REFERENCES `fornecedor` (`codigo`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

-- -----------------------------------------------------
-- Table `grupo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS grupo
(
    `codigo`         INT(11)     NOT NULL AUTO_INCREMENT,
    `descricao`      VARCHAR(45) NOT NULL,
    `data_cadastro`  TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `data_alteracao` TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`codigo`)
);

-- -----------------------------------------------------
-- Table `categoria`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS categoria
(
    `codigo`         INT(11)     NOT NULL AUTO_INCREMENT,
    `descricao`      VARCHAR(45) NOT NULL,
    `data_cadastro`  TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `data_alteracao` TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`codigo`)
);

-- -----------------------------------------------------
-- Table `regime_tributario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS regime_tributario
(
    `codigo`         INT(11)      NOT NULL AUTO_INCREMENT,
    `descricao`      VARCHAR(100) NOT NULL,
    `tipo_regime`    INT          NOT NULL,
    `data_alteracao` TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`codigo`)

);

-- -----------------------------------------------------
-- Table `tipo_ambiente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS tipo_ambiente
(
    `codigo`    INT(11)     NOT NULL,
    `descricao` VARCHAR(45) NOT NULL,
    `tipo`      INT         NOT NULL,
    PRIMARY KEY (`codigo`)
);

-- -----------------------------------------------------
-- Table `empresa_parametros`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS empresa_parametros
(
    `codigo`               INT(11) NOT NULL AUTO_INCREMENT,
    `serie_nfe`            INT     NULL,
    `tipo_ambiente_codigo` INT(11) NULL,
    `p_credsn`             DOUBLE  NULL,
    PRIMARY KEY (`codigo`),
    CONSTRAINT `fk_empresa_parametros_tipo_ambiente1`
        FOREIGN KEY (`tipo_ambiente_codigo`)
            REFERENCES `tipo_ambiente` (`codigo`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

-- -----------------------------------------------------
-- Table `empresa`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS empresa
(
    `codigo`                   INT(11)      NOT NULL AUTO_INCREMENT,
    `nome`                     VARCHAR(255) NOT NULL,
    `nome_fantasia`            VARCHAR(255) NOT NULL,
    `cnpj`                     VARCHAR(18)  NOT NULL,
    `ie`                       VARCHAR(45)  NULL,
    `regime_tributario_codigo` INT(11)      NOT NULL,
    `data_alteracao`           TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `parametro_codigo`         INT(11)      NULL,
    `endereco_codigo`          INT(11)      NOT NULL,
    PRIMARY KEY (`codigo`),
    CONSTRAINT `fk_empresa_regime_tributario1`
        FOREIGN KEY (`regime_tributario_codigo`)
            REFERENCES `regime_tributario` (`codigo`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_empresa_empresa_parametros1`
        FOREIGN KEY (`parametro_codigo`)
            REFERENCES `empresa_parametros` (`codigo`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_empresa_endereco1`
        FOREIGN KEY (`endereco_codigo`)
            REFERENCES `endereco` (`codigo`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

-- -----------------------------------------------------
-- Table `tributacao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS tributacao
(
    `codigo`          INT(11)      NOT NULL AUTO_INCREMENT,
    `descricao`       VARCHAR(250) NOT NULL,
    `subs_tributaria` TINYINT(1)   NOT NULL,
    `data_cadastro`   TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `data_alteracao`  TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `empresa_codigo`  INT(11)      NOT NULL,
    PRIMARY KEY (`codigo`),
    CONSTRAINT `fk_tributacao_empresa1`
        FOREIGN KEY (`empresa_codigo`)
            REFERENCES `empresa` (`codigo`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

-- -----------------------------------------------------
-- Table `mod_bc_icms`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS mod_bc_icms
(
    `codigo`         INT(11)      NOT NULL AUTO_INCREMENT,
    `tipo`           INT(11)      NOT NULL,
    `descricao`      VARCHAR(255) NOT NULL,
    `sub_tributaria` TINYINT(1)   NOT NULL,
    PRIMARY KEY (`codigo`)
);

-- -----------------------------------------------------
-- Table `produto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS produto
(
    `codigo`            INT(11)      NOT NULL AUTO_INCREMENT,
    `descricao`         VARCHAR(250) NOT NULL,
    `valor_custo`       DOUBLE       NULL,
    `valor_venda`       DOUBLE       NULL,
    `valor_balanca`     DOUBLE       NULL,
    `data_validade`     DATE         NULL,
    `balanca`           INT          NOT NULL,
    `ativo`             VARCHAR(10)  NOT NULL,
    `fornecedor_codigo` INT(11)      NOT NULL,
    `grupo_codigo`      INT(11)      NOT NULL,
    `unidade`           VARCHAR(10)  NULL,
    `subtributaria`     INT          NOT NULL,
    `categoria_codigo`  INT(11)      NOT NULL,
    `ncm`               VARCHAR(12)  NULL,
    `cest`              VARCHAR(12)  NULL,
    `vendavel`          VARCHAR(5)   NOT NULL,
    `controla_estoque`  VARCHAR(10)  NOT NULL,
    `tributacao_codigo` INT(11)      NULL,
    `bc_icms_codigo`    INT(11)      NULL,
    `data_cadastro`     TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `data_alteracao`    TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`codigo`),
    CONSTRAINT `fk_produto_fornecedor1`
        FOREIGN KEY (`fornecedor_codigo`)
            REFERENCES `fornecedor` (`codigo`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_produto_grupo1`
        FOREIGN KEY (`grupo_codigo`)
            REFERENCES `grupo` (`codigo`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_produto_categoria1`
        FOREIGN KEY (`categoria_codigo`)
            REFERENCES `categoria` (`codigo`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_produto_tributacao1`
        FOREIGN KEY (`tributacao_codigo`)
            REFERENCES `tributacao` (`codigo`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_produto_mod_bc_icms1`
        FOREIGN KEY (`bc_icms_codigo`)
            REFERENCES `mod_bc_icms` (`codigo`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

-- -----------------------------------------------------
-- Table `produto_imagem`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS produto_imagem
(
    `codigo`         INT(11)     NOT NULL AUTO_INCREMENT,
    `descricao`      VARCHAR(45) NULL,
    `uri`            TEXT        NOT NULL,
    `data_cadastro`  TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `data_alteracao` TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `produto_codigo` INT(11)     NOT NULL,
    PRIMARY KEY (`codigo`),
    CONSTRAINT `fk_imagem_produto1`
        FOREIGN KEY (`produto_codigo`)
            REFERENCES `produto` (`codigo`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

-- -----------------------------------------------------
-- Table `pessoa`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS pessoa
(
    `codigo`          INT(11)      NOT NULL AUTO_INCREMENT,
    `nome`            VARCHAR(250) NOT NULL,
    `cpfcnpj`         VARCHAR(18)  NOT NULL,
    `data_cadastro`   TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `data_alteracao`  TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `endereco_codigo` INT(11)      NOT NULL,
    `apelido`         VARCHAR(45)  NULL,
    `data_nascimento` DATE         NOT NULL,
    `observacao`      VARCHAR(250) NULL,
    PRIMARY KEY (`codigo`),
    CONSTRAINT `fk_pessoa_endereco1`
        FOREIGN KEY (`endereco_codigo`)
            REFERENCES `endereco` (`codigo`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

-- -----------------------------------------------------
-- Table `pessoa_telefone`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS pessoa_telefone
(
    `codigo`          INT(11) NOT NULL AUTO_INCREMENT,
    `pessoa_codigo`   INT(11) NOT NULL,
    `telefone_codigo` INT(11) NOT NULL,
    PRIMARY KEY (`codigo`),
    CONSTRAINT `fk_pessoa_telefone_pessoa1`
        FOREIGN KEY (`pessoa_codigo`)
            REFERENCES `pessoa` (`codigo`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_pessoa_telefone_telefone1`
        FOREIGN KEY (`telefone_codigo`)
            REFERENCES `telefone` (`codigo`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

-- -----------------------------------------------------
-- Table `usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS usuario
(
    `codigo`         INT(11)      NOT NULL AUTO_INCREMENT,
    `user`           VARCHAR(20)  NOT NULL,
    `senha`          VARCHAR(350) NOT NULL,
    `data_cadastro`  TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `data_alteracao` TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `pessoa_codigo`  INT(11)      NOT NULL,
    PRIMARY KEY (`codigo`),
    CONSTRAINT `fk_usuario_pessoa1`
        FOREIGN KEY (`pessoa_codigo`)
            REFERENCES `pessoa` (`codigo`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

-- -----------------------------------------------------
-- Table `caixa`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS caixa
(
    `codigo`           INT(11)      NOT NULL AUTO_INCREMENT,
    `descricao`        VARCHAR(250) NOT NULL,
    `valor_abertura`   DOUBLE       NOT NULL DEFAULT 0,
    `valor_total`      DOUBLE       NULL,
    `valor_fechamento` DOUBLE       NULL     DEFAULT 0,
    `valor_entrada`    DOUBLE       NULL,
    `valor_saida`      DOUBLE       NULL,
    `tipo`             VARCHAR(10)  NOT NULL,
    `usuario_codigo`   INT(11)      NOT NULL,
    `data_cadastro`    TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `data_fechamento`  DATETIME     NULL,
    `data_alteracao`   TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `agencia`          VARCHAR(6)   NULL,
    `conta`            VARCHAR(10)  NULL,
    PRIMARY KEY (`codigo`),
    CONSTRAINT `fk_caixa_usuario1`
        FOREIGN KEY (`usuario_codigo`)
            REFERENCES `usuario` (`codigo`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

-- -----------------------------------------------------
-- Table `pagartipo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS pagartipo
(
    `codigo`        INT(11)      NOT NULL AUTO_INCREMENT,
    `descricao`     VARCHAR(200) NOT NULL,
    `data_cadastro` TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`codigo`)
);

-- -----------------------------------------------------
-- Table `pagar`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS pagar
(
    `codigo`            INT(11)      NOT NULL AUTO_INCREMENT,
    `observacao`        VARCHAR(255) NULL,
    `valor_total`       DOUBLE       NOT NULL,
    `data_cadastro`     TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `data_alteracao`    TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `fornecedor_codigo` INT(11)      NOT NULL,
    `pagartipo_codigo`  INT(11)      NOT NULL,
    PRIMARY KEY (`codigo`),
    CONSTRAINT `fk_pagar_fornecedor1`
        FOREIGN KEY (`fornecedor_codigo`)
            REFERENCES `fornecedor` (`codigo`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_pagar_pagartipo1`
        FOREIGN KEY (`pagartipo_codigo`)
            REFERENCES `pagartipo` (`codigo`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

-- -----------------------------------------------------
-- Table `parcela_pagar`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS parcela_pagar
(
    `codigo`          INT       NOT NULL AUTO_INCREMENT,
    `valor_total`     DOUBLE    NOT NULL,
    `valor_desconto`  DOUBLE    NULL,
    `valor_acrescimo` DOUBLE    NULL,
    `valor_pago`      DOUBLE    NULL,
    `valor_restante`  DOUBLE    NOT NULL,
    `data_cadastro`   TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `data_alteracao`  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `data_vencimento` DATE      NULL,
    `data_pagamento`  DATETIME  NULL,
    `quitado`         INT       NOT NULL,
    `pagar_codigo`    INT(11)   NOT NULL,
    PRIMARY KEY (`codigo`),
    CONSTRAINT `fk_parcela_pagar_pagar1`
        FOREIGN KEY (`pagar_codigo`)
            REFERENCES `pagar` (`codigo`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

-- -----------------------------------------------------
-- Table `titulo_tipo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS titulo_tipo
(
    `codigo`    INT(11)      NOT NULL,
    `descricao` VARCHAR(100) NOT NULL,
    `sigla`     VARCHAR(12)  NOT NULL,
    PRIMARY KEY (`codigo`)
);

-- -----------------------------------------------------
-- Table `maquina_cartao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS maquina_cartao
(
    `codigo`           INT(11)      NOT NULL AUTO_INCREMENT,
    `descricao`        VARCHAR(100) NOT NULL,
    `taxa_debito`      DOUBLE       NULL,
    `taxa_credito`     DOUBLE       NULL,
    `dias_debito`      INT(11)      NULL,
    `dias_credito`     INT(11)      NULL,
    `taxa_antecipacao` DOUBLE       NULL,
    `data_alteracao`   TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `banco_codigo`     INT(11)      NOT NULL,
    PRIMARY KEY (`codigo`),
    CONSTRAINT `fk_maquina_cartao_caixa1`
        FOREIGN KEY (`banco_codigo`)
            REFERENCES `caixa` (`codigo`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

-- -----------------------------------------------------
-- Table `titulo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS titulo
(
    `codigo`                INT(11)      NOT NULL AUTO_INCREMENT,
    `descricao`             VARCHAR(100) NOT NULL,
    `tipo_codigo`           INT(11)      NOT NULL,
    `maquina_cartao_codigo` INT(11)      NULL,
    PRIMARY KEY (`codigo`),
    CONSTRAINT `fk_titulo_titulo_tipo1`
        FOREIGN KEY (`tipo_codigo`)
            REFERENCES `titulo_tipo` (`codigo`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_titulo_maquina_cartao1`
        FOREIGN KEY (`maquina_cartao_codigo`)
            REFERENCES `maquina_cartao` (`codigo`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

-- -----------------------------------------------------
-- Table `tributacao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS tributacao
(
    `codigo`          INT(11)      NOT NULL AUTO_INCREMENT,
    `descricao`       VARCHAR(250) NOT NULL,
    `subs_tributaria` TINYINT(1)   NOT NULL,
    `data_cadastro`   TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `data_alteracao`  TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `empresa_codigo`  INT(11)      NOT NULL,
    PRIMARY KEY (`codigo`),
    CONSTRAINT `fk_tributacao_empresa1`
        FOREIGN KEY (`empresa_codigo`)
            REFERENCES `empresa` (`codigo`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

-- -----------------------------------------------------
-- Table `mod_bc_icms`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS mod_bc_icms
(
    `codigo`         INT(11)      NOT NULL AUTO_INCREMENT,
    `tipo`           INT(11)      NOT NULL,
    `descricao`      VARCHAR(255) NOT NULL,
    `sub_tributaria` TINYINT(1)   NOT NULL,
    PRIMARY KEY (`codigo`)
);

-- -----------------------------------------------------
-- Table `produto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS produto
(
    `codigo`            INT(11)      NOT NULL AUTO_INCREMENT,
    `descricao`         VARCHAR(250) NOT NULL,
    `valor_custo`       DOUBLE       NULL,
    `valor_venda`       DOUBLE       NULL,
    `valor_balanca`     DOUBLE       NULL,
    `data_validade`     DATE         NULL,
    `balanca`           INT          NOT NULL,
    `ativo`             VARCHAR(10)  NOT NULL,
    `fornecedor_codigo` INT(11)      NOT NULL,
    `grupo_codigo`      INT(11)      NOT NULL,
    `unidade`           VARCHAR(10)  NULL,
    `subtributaria`     INT          NOT NULL,
    `categoria_codigo`  INT(11)      NOT NULL,
    `ncm`               VARCHAR(12)  NULL,
    `cest`              VARCHAR(12)  NULL,
    `vendavel`          VARCHAR(5)   NOT NULL,
    `controla_estoque`  VARCHAR(10)  NOT NULL,
    `tributacao_codigo` INT(11)      NULL,
    `bc_icms_codigo`    INT(11)      NULL,
    `data_cadastro`     TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `data_alteracao`    TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`codigo`),
    CONSTRAINT `fk_produto_fornecedor1`
        FOREIGN KEY (`fornecedor_codigo`)
            REFERENCES `fornecedor` (`codigo`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_produto_grupo1`
        FOREIGN KEY (`grupo_codigo`)
            REFERENCES `grupo` (`codigo`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_produto_categoria1`
        FOREIGN KEY (`categoria_codigo`)
            REFERENCES `categoria` (`codigo`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_produto_tributacao1`
        FOREIGN KEY (`tributacao_codigo`)
            REFERENCES `tributacao` (`codigo`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_produto_mod_bc_icms1`
        FOREIGN KEY (`bc_icms_codigo`)
            REFERENCES `mod_bc_icms` (`codigo`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

-- -----------------------------------------------------
-- Table `produto_imagem`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS produto_imagem
(
    `codigo`         INT(11)     NOT NULL AUTO_INCREMENT,
    `descricao`      VARCHAR(45) NULL,
    `uri`            TEXT        NOT NULL,
    `data_cadastro`  TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `data_alteracao` TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `produto_codigo` INT(11)     NOT NULL,
    PRIMARY KEY (`codigo`),
    CONSTRAINT `fk_imagem_produto1`
        FOREIGN KEY (`produto_codigo`)
            REFERENCES `produto` (`codigo`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

-- -----------------------------------------------------
-- Table `pessoa`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS pessoa
(
    `codigo`          INT(11)      NOT NULL AUTO_INCREMENT,
    `nome`            VARCHAR(250) NOT NULL,
    `cpfcnpj`         VARCHAR(18)  NOT NULL,
    `data_cadastro`   TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `data_alteracao`  TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `endereco_codigo` INT(11)      NOT NULL,
    `apelido`         VARCHAR(45)  NULL,
    `data_nascimento` DATE         NOT NULL,
    `observacao`      VARCHAR(250) NULL,
    PRIMARY KEY (`codigo`),
    CONSTRAINT `fk_pessoa_endereco1`
        FOREIGN KEY (`endereco_codigo`)
            REFERENCES `endereco` (`codigo`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

-- -----------------------------------------------------
-- Table `pessoa_telefone`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS pessoa_telefone
(
    `codigo`          INT(11) NOT NULL AUTO_INCREMENT,
    `pessoa_codigo`   INT(11) NOT NULL,
    `telefone_codigo` INT(11) NOT NULL,
    PRIMARY KEY (`codigo`),
    CONSTRAINT `fk_pessoa_telefone_pessoa1`
        FOREIGN KEY (`pessoa_codigo`)
            REFERENCES `pessoa` (`codigo`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_pessoa_telefone_telefone1`
        FOREIGN KEY (`telefone_codigo`)
            REFERENCES `telefone` (`codigo`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

-- -----------------------------------------------------
-- Table `usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS usuario
(
    `codigo`         INT(11)      NOT NULL AUTO_INCREMENT,
    `user`           VARCHAR(20)  NOT NULL,
    `senha`          VARCHAR(350) NOT NULL,
    `data_cadastro`  TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `data_alteracao` TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `pessoa_codigo`  INT(11)      NOT NULL,
    PRIMARY KEY (`codigo`),
    CONSTRAINT `fk_usuario_pessoa1`
        FOREIGN KEY (`pessoa_codigo`)
            REFERENCES `pessoa` (`codigo`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

-- -----------------------------------------------------
-- Table `caixa`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS caixa
(
    `codigo`           INT(11)      NOT NULL AUTO_INCREMENT,
    `descricao`        VARCHAR(250) NOT NULL,
    `valor_abertura`   DOUBLE       NOT NULL DEFAULT 0,
    `valor_total`      DOUBLE       NULL,
    `valor_fechamento` DOUBLE       NULL     DEFAULT 0,
    `valor_entrada`    DOUBLE       NULL,
    `valor_saida`      DOUBLE       NULL,
    `tipo`             VARCHAR(10)  NOT NULL,
    `usuario_codigo`   INT(11)      NOT NULL,
    `data_cadastro`    TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `data_fechamento`  DATETIME     NULL,
    `data_alteracao`   TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `agencia`          VARCHAR(6)   NULL,
    `conta`            VARCHAR(10)  NULL,
    PRIMARY KEY (`codigo`),
    CONSTRAINT `fk_caixa_usuario1`
        FOREIGN KEY (`usuario_codigo`)
            REFERENCES `usuario` (`codigo`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

-- -----------------------------------------------------
-- Table `pagartipo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS pagartipo
(
    `codigo`        INT(11)      NOT NULL AUTO_INCREMENT,
    `descricao`     VARCHAR(200) NOT NULL,
    `data_cadastro` TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`codigo`)
);

-- -----------------------------------------------------
-- Table `pagar`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS pagar
(
    `codigo`            INT(11)      NOT NULL AUTO_INCREMENT,
    `observacao`        VARCHAR(255) NULL,
    `valor_total`       DOUBLE       NOT NULL,
    `data_cadastro`     TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `data_alteracao`    TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `fornecedor_codigo` INT(11)      NOT NULL,
    `pagartipo_codigo`  INT(11)      NOT NULL,
    PRIMARY KEY (`codigo`),
    CONSTRAINT `fk_pagar_fornecedor1`
        FOREIGN KEY (`fornecedor_codigo`)
            REFERENCES `fornecedor` (`codigo`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_pagar_pagartipo1`
        FOREIGN KEY (`pagartipo_codigo`)
            REFERENCES `pagartipo` (`codigo`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

-- -----------------------------------------------------
-- Table `parcela_pagar`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS parcela_pagar
(
    `codigo`          INT       NOT NULL AUTO_INCREMENT,
    `valor_total`     DOUBLE    NOT NULL,
    `valor_desconto`  DOUBLE    NULL,
    `valor_acrescimo` DOUBLE    NULL,
    `valor_pago`      DOUBLE    NULL,
    `valor_restante`  DOUBLE    NOT NULL,
    `data_cadastro`   TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `data_alteracao`  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `data_vencimento` DATE      NULL,
    `data_pagamento`  DATETIME  NULL,
    `quitado`         INT       NOT NULL,
    `pagar_codigo`    INT(11)   NOT NULL,
    PRIMARY KEY (`codigo`),
    CONSTRAINT `fk_parcela_pagar_pagar1`
        FOREIGN KEY (`pagar_codigo`)
            REFERENCES `pagar` (`codigo`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

-- -----------------------------------------------------
-- Table `titulo_tipo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS titulo_tipo
(
    `codigo`    INT(11)      NOT NULL,
    `descricao` VARCHAR(100) NOT NULL,
    `sigla`     VARCHAR(12)  NOT NULL,
    PRIMARY KEY (`codigo`)
);

-- -----------------------------------------------------
-- Table `maquina_cartao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS maquina_cartao
(
    `codigo`           INT(11)      NOT NULL AUTO_INCREMENT,
    `descricao`        VARCHAR(100) NOT NULL,
    `taxa_debito`      DOUBLE       NULL,
    `taxa_credito`     DOUBLE       NULL,
    `dias_debito`      INT(11)      NULL,
    `dias_credito`     INT(11)      NULL,
    `taxa_antecipacao` DOUBLE       NULL,
    `data_alteracao`   TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `banco_codigo`     INT(11)      NOT NULL,
    PRIMARY KEY (`codigo`),
    CONSTRAINT `fk_maquina_cartao_caixa1`
        FOREIGN KEY (`banco_codigo`)
            REFERENCES `caixa` (`codigo`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

-- -----------------------------------------------------
-- Table `titulo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS titulo
(
    `codigo`                INT(11)      NOT NULL AUTO_INCREMENT,
    `descricao`             VARCHAR(100) NOT NULL,
    `tipo_codigo`           INT(11)      NOT NULL,
    `maquina_cartao_codigo` INT(11)      NULL,
    PRIMARY KEY (`codigo`),
    CONSTRAINT `fk_titulo_titulo_tipo1`
        FOREIGN KEY (`tipo_codigo`)
            REFERENCES `titulo_tipo` (`codigo`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_titulo_maquina_cartao1`
        FOREIGN KEY (`maquina_cartao_codigo`)
            REFERENCES `maquina_cartao` (`codigo`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

-- -----------------------------------------------------
-- Table `recebimento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS recebimento
(
    `codigo`             INT(11)   NOT NULL AUTO_INCREMENT,
    `valor_total`        DOUBLE    NOT NULL,
    `valor_desconto`     DOUBLE    NULL,
    `valor_acrescimo`    DOUBLE    NULL,
    `valor_recebido`     DOUBLE    NULL,
    `pessoa_codigo`      INT(11)   NOT NULL,
    `titulo_codigo`      INT(11)   NULL,
    `data_cadastro`      TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `data_processamento` DATETIME  NULL,
    `data_alteracao`     TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`codigo`),
    CONSTRAINT `fk_recebimento_pessoa1`
        FOREIGN KEY (`pessoa_codigo`)
            REFERENCES `pessoa` (`codigo`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_recebimento_titulo1`
        FOREIGN KEY (`titulo_codigo`)
            REFERENCES `titulo` (`codigo`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

-- -----------------------------------------------------
-- Table `caixa_lancamento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS caixa_lancamento
(
    `codigo`               INT(11)      NOT NULL AUTO_INCREMENT,
    `valor`                DOUBLE       NOT NULL,
    `observacao`           VARCHAR(250) NULL,
    `tipo`                 VARCHAR(45)  NOT NULL,
    `estilo`               VARCHAR(10)  NOT NULL,
    `data_cadastro`        TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `data_alteracao`       TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `caixa_codigo`         INT(11)      NOT NULL,
    `usuario_codigo`       INT(11)      NOT NULL,
    `parcela_pagar_codigo` INT(11)      NULL,
    `recebimento_codigo`   INT(11)      NULL,
    PRIMARY KEY (`codigo`),
    CONSTRAINT `fk_caixa_lancamento_caixa1`
        FOREIGN KEY (`caixa_codigo`)
            REFERENCES `caixa` (`codigo`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_caixa_lancamento_parcela_pagar1`
        FOREIGN KEY (`parcela_pagar_codigo`)
            REFERENCES `parcela_pagar` (`codigo`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_caixa_lancamento_usuario1`
        FOREIGN KEY (`usuario_codigo`)
            REFERENCES `usuario` (`codigo`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_caixa_lancamento_recebimento1`
        FOREIGN KEY (`recebimento_codigo`)
            REFERENCES `recebimento` (`codigo`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

-- -----------------------------------------------------
-- Table `venda`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS venda
(
    `codigo`          INT(11)      NOT NULL AUTO_INCREMENT,
    `observacao`      VARCHAR(250) NULL,
    `valor_produtos`  DOUBLE       NULL,
    `valor_desconto`  DOUBLE       NULL,
    `valor_acrescimo` DOUBLE       NULL,
    `valor_total`     DOUBLE       NULL,
    `situacao`        VARCHAR(45)  NOT NULL,
    `pessoa_codigo`   INT(11)      NULL,
    `usuario_codigo`  INT(11)      NOT NULL,
    `data_finalizado` DATETIME     NULL,
    `data_cancelado`  DATETIME     NULL,
    `data_cadastro`   TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`codigo`),
    CONSTRAINT `fk_pedido_pessoa1`
        FOREIGN KEY (`pessoa_codigo`)
            REFERENCES `pessoa` (`codigo`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_pedido_usuario1`
        FOREIGN KEY (`usuario_codigo`)
            REFERENCES `usuario` (`codigo`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

-- -----------------------------------------------------
-- Table `venda_produtos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS venda_produtos
(
    `codigo`         INT(11)   NOT NULL AUTO_INCREMENT,
    `produto_codigo` INT(11)   NOT NULL,
    `venda_codigo`   INT(11)   NOT NULL,
    `valor_balanca`  DOUBLE    NULL,
    `data_cadastro`  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`codigo`, `produto_codigo`),
    CONSTRAINT `fk_pedido_produtos_has_produto_produto1`
        FOREIGN KEY (`produto_codigo`)
            REFERENCES `produto` (`codigo`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_pedido_produtos_has_produto_pedido1`
        FOREIGN KEY (`venda_codigo`)
            REFERENCES `venda` (`codigo`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

-- -----------------------------------------------------
-- Table `grupousuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS grupousuario
(
    `codigo`         INT(11)      NOT NULL AUTO_INCREMENT,
    `nome`           VARCHAR(45)  NULL,
    `descricao`      VARCHAR(250) NOT NULL,
    `data_alteracao` TIMESTAMP    NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`codigo`)
);

-- -----------------------------------------------------
-- Table `permissoes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS permissoes
(
    `codigo`         INT(11)      NOT NULL,
    `nome`           VARCHAR(45)  NOT NULL,
    `descricao`      VARCHAR(250) NOT NULL,
    `data_alteracao` TIMESTAMP    NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`codigo`)
);

-- -----------------------------------------------------
-- Table `permissoes_grupo_usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS permissoes_grupo_usuario
(
    `codigo`               INT(11) NOT NULL AUTO_INCREMENT,
    `grupo_usuario_codigo` INT(11) NOT NULL,
    `permissoes_codigo`    INT(11) NOT NULL,
    PRIMARY KEY (`codigo`),
    CONSTRAINT `fk_grupousuario_has_permissoes_grupousuario1`
        FOREIGN KEY (`grupo_usuario_codigo`)
            REFERENCES `grupousuario` (`codigo`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_grupousuario_has_permissoes_permissoes1`
        FOREIGN KEY (`permissoes_codigo`)
            REFERENCES `permissoes` (`codigo`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

-- -----------------------------------------------------
-- Table `usuario_permissoes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS usuario_permissoes
(
    `usuario_codigo`    INT(11) NOT NULL,
    `permissoes_codigo` INT(11) NOT NULL,
    PRIMARY KEY (`usuario_codigo`, `permissoes_codigo`),
    CONSTRAINT `fk_usuario_has_permissoes_usuario1`
        FOREIGN KEY (`usuario_codigo`)
            REFERENCES `usuario` (`codigo`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_usuario_has_permissoes_permissoes1`
        FOREIGN KEY (`permissoes_codigo`)
            REFERENCES `permissoes` (`codigo`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

-- -----------------------------------------------------
-- Table `usuario_grupousuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS usuario_grupousuario
(
    `codigo`               INT(11) NOT NULL AUTO_INCREMENT,
    `grupo_usuario_codigo` INT(11) NOT NULL,
    `usuario_codigo`       INT(11) NOT NULL,
    PRIMARY KEY (`codigo`, `grupo_usuario_codigo`, `usuario_codigo`),
    CONSTRAINT `fk_grupousuario_has_usuario_grupousuario1`
        FOREIGN KEY (`grupo_usuario_codigo`)
            REFERENCES `grupousuario` (`codigo`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_grupousuario_has_usuario_usuario1`
        FOREIGN KEY (`usuario_codigo`)
            REFERENCES `usuario` (`codigo`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

-- -----------------------------------------------------
-- Table `pagamento_tipo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS pagamento_tipo
(
    `codigo`          INT(11)      NOT NULL AUTO_INCREMENT,
    `descricao`       VARCHAR(45)  NOT NULL,
    `forma_pagamento` VARCHAR(250) NOT NULL,
    `qtd_parcelas`    INT          NOT NULL,
    `data_cadastro`   TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `data_alteracao`  TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`codigo`)
);

-- -----------------------------------------------------
-- Table `pagamento_tipo_venda`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS pagamento_tipo_venda
(
    `ven_codigo`      INT(11) NOT NULL,
    `pag_tipo_codigo` INT(11) NOT NULL,
    PRIMARY KEY (`ven_codigo`, `pag_tipo_codigo`),
    CONSTRAINT `fk_pagamento_tipo_has_venda_pagamento_tipo1`
        FOREIGN KEY (`pag_tipo_codigo`)
            REFERENCES `pagamento_tipo` (`codigo`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_pagamento_tipo_has_venda_venda1`
        FOREIGN KEY (`ven_codigo`)
            REFERENCES `venda` (`codigo`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

-- -----------------------------------------------------
-- Table `receber`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS receber
(
    `codigo`         INT(11)      NOT NULL AUTO_INCREMENT,
    `observacao`     VARCHAR(255) NOT NULL,
    `valor_total`    DOUBLE       NOT NULL,
    `pessoa_codigo`  INT(11)      NULL,
    `data_cadastro`  TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `data_alteracao` TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `venda_codigo`   INT(11)      NOT NULL,
    PRIMARY KEY (`codigo`, `venda_codigo`),
    CONSTRAINT `fk_Receber_pessoa1`
        FOREIGN KEY (`pessoa_codigo`)
            REFERENCES `pessoa` (`codigo`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_receber_venda1`
        FOREIGN KEY (`venda_codigo`)
            REFERENCES `venda` (`codigo`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

-- -----------------------------------------------------
-- Table `parcela`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS parcela
(
    `codigo`          INT(11)   NOT NULL AUTO_INCREMENT,
    `valor_total`     DOUBLE    NOT NULL,
    `valor_desconto`  DOUBLE    NULL,
    `valor_acrescimo` DOUBLE    NULL,
    `valor_recebido`  DOUBLE    NULL,
    `valor_restante`  DOUBLE    NOT NULL,
    `data_cadastro`   TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `data_alteracao`  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `data_vencimento` DATETIME  NOT NULL,
    `data_pagamento`  DATETIME  NULL,
    `receber_codigo`  INT(11)   NOT NULL,
    `quitado`         INT       NULL,
    `sequencia`       INT       NOT NULL,
    PRIMARY KEY (`codigo`),
    CONSTRAINT `fk_parcela_receber1`
        FOREIGN KEY (`receber_codigo`)
            REFERENCES `receber` (`codigo`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

-- -----------------------------------------------------
-- Table `transferencia`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS transferencia
(
    `codigo`             INT(11)      NOT NULL AUTO_INCREMENT,
    `valor`              DOUBLE       NOT NULL,
    `observacao`         VARCHAR(255) NOT NULL,
    `origem_codigo`      INT(11)      NOT NULL,
    `destino_codigo`     INT(11)      NOT NULL,
    `usuario_codigo`     INT(11)      NOT NULL,
    `data_transferencia` DATETIME     NOT NULL,
    `data_alteracao`     TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`codigo`),
    CONSTRAINT `fk_transferencia_usuario1`
        FOREIGN KEY (`usuario_codigo`)
            REFERENCES `usuario` (`codigo`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_transferencia_caixa1`
        FOREIGN KEY (`origem_codigo`)
            REFERENCES `caixa` (`codigo`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_transferencia_caixa2`
        FOREIGN KEY (`destino_codigo`)
            REFERENCES `caixa` (`codigo`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

-- -----------------------------------------------------
-- Table `recebimento_parcelas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS recebimento_parcelas
(
    `codigo`          INT(11) NOT NULL AUTO_INCREMENT,
    `recebimento_cod` INT(11) NOT NULL,
    `parcela_cod`     INT(11) NOT NULL,
    PRIMARY KEY (`codigo`, `recebimento_cod`, `parcela_cod`),
    CONSTRAINT `fk_recebimento_has_parcela_recebimento1`
        FOREIGN KEY (`recebimento_cod`)
            REFERENCES `recebimento` (`codigo`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_recebimento_has_parcela_parcela1`
        FOREIGN KEY (`parcela_cod`)
            REFERENCES `parcela` (`codigo`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

-- -----------------------------------------------------
-- Table `cst_csosn`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS cst_csosn
(
    `codigo`           INT(11)     NOT NULL AUTO_INCREMENT,
    `cst_csosn`        VARCHAR(12) NOT NULL,
    `simples_nacional` INT         NOT NULL,
    PRIMARY KEY (`codigo`)
);

-- -----------------------------------------------------
-- Table `cst`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS cst
(
    `codigo`    INT(11)      NOT NULL AUTO_INCREMENT,
    `cst`       VARCHAR(12)  NOT NULL,
    `descricao` VARCHAR(250) NOT NULL,
    PRIMARY KEY (`codigo`)
);

-- -----------------------------------------------------
-- Table `cfop`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS cfop
(
    `codigo`    INT(11)       NOT NULL AUTO_INCREMENT,
    `cfop`      VARCHAR(5)    NOT NULL,
    `descricao` VARCHAR(255)  NOT NULL,
    `aplicacao` VARCHAR(1500) NULL,
    PRIMARY KEY (`codigo`)
);

-- -----------------------------------------------------
-- Table `cst_ipi`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS cst_ipi
(
    `codigo`    INT(11)      NOT NULL AUTO_INCREMENT,
    `cst`       VARCHAR(10)  NOT NULL,
    `descricao` VARCHAR(100) NOT NULL,
    `tipo`      VARCHAR(8)   NOT NULL,
    PRIMARY KEY (`codigo`)
);

-- -----------------------------------------------------
-- Table `tributacao_regra`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS tributacao_regra
(
    `codigo`            INT(11)    NOT NULL AUTO_INCREMENT,
    `tributacao_codigo` INT(11)    NOT NULL,
    `uf`                VARCHAR(3) NOT NULL,
    `pis`               DOUBLE     NULL,
    `cofins`            DOUBLE     NULL,
    `cst_pis`           INT(11)    NULL,
    `cst_cofins`        INT(11)    NULL,
    `aliq_ipi`          DOUBLE     NULL,
    `aliq_icms`         DOUBLE     NULL,
    `tipo`              VARCHAR(7) NOT NULL,
    `cst_csosn_codigo`  INT(11)    NOT NULL,
    `cfop_codigo`       INT(11)    NOT NULL,
    `cst_ipi_codigo`    INT(11)    NULL,
    `data_cadastro`     TIMESTAMP  NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `data_alteracao`    TIMESTAMP  NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`codigo`),
    CONSTRAINT `fk_tributacao_regra_tributacao1`
        FOREIGN KEY (`tributacao_codigo`)
            REFERENCES `tributacao` (`codigo`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_tributacao_regra_cst_csosn1`
        FOREIGN KEY (`cst_csosn_codigo`)
            REFERENCES `cst_csosn` (`codigo`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_tributacao_regra_cest1`
        FOREIGN KEY (`cst_pis`)
            REFERENCES `cst` (`codigo`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_tributacao_regra_cest2`
        FOREIGN KEY (`cst_cofins`)
            REFERENCES `cst` (`codigo`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_tributacao_regra_cfop1`
        FOREIGN KEY (`cfop_codigo`)
            REFERENCES `cfop` (`codigo`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_tributacao_regra_cst_ipi1`
        FOREIGN KEY (`cst_ipi_codigo`)
            REFERENCES `cst_ipi` (`codigo`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

-- -----------------------------------------------------
-- Table `mva`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS mva
(
    `codigo`         INT(11)    NOT NULL AUTO_INCREMENT,
    `uf`             VARCHAR(3) NOT NULL,
    `mva`            INT(11)    NOT NULL,
    `data_cadastro`  TIMESTAMP  NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `data_alteracao` TIMESTAMP  NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `produto_codigo` INT(11)    NOT NULL,
    PRIMARY KEY (`codigo`),
    CONSTRAINT `fk_mva_produto1`
        FOREIGN KEY (`produto_codigo`)
            REFERENCES `produto` (`codigo`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

-- -----------------------------------------------------
-- Table `nota_fiscal_totais`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS nota_fiscal_totais
(
    `codigo`   INT(11) NOT NULL AUTO_INCREMENT,
    `v_bc`     DOUBLE  NOT NULL,
    `v_icms`   DOUBLE  NOT NULL,
    `v_st`     DOUBLE  NOT NULL,
    `v_prod`   DOUBLE  NOT NULL,
    `v_frete`  DOUBLE  NOT NULL,
    `v_seg`    DOUBLE  NOT NULL,
    `v_desc`   DOUBLE  NOT NULL,
    `v_ii`     DOUBLE  NOT NULL,
    `v_ipi`    DOUBLE  NOT NULL,
    `v_pis`    DOUBLE  NOT NULL,
    `v_cofins` DOUBLE  NOT NULL,
    `v_outros` DOUBLE  NOT NULL,
    `v_nf`     DOUBLE  NOT NULL,
    PRIMARY KEY (`codigo`)
);

-- -----------------------------------------------------
-- Table `frete_tipo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS frete_tipo
(
    `codigo`    INT(11)      NOT NULL AUTO_INCREMENT,
    `tipo`      INT          NOT NULL,
    `descricao` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`codigo`)
);

-- -----------------------------------------------------
-- Table `nota_fiscal_finalidade`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS nota_fiscal_finalidade
(
    `codigo`    INT(11)      NOT NULL AUTO_INCREMENT,
    `tipo`      INT          NOT NULL,
    `descricao` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`codigo`)
);

-- -----------------------------------------------------
-- Table `nota_fiscal`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS nota_fiscal
(
    `codigo`              INT(11)      NOT NULL AUTO_INCREMENT,
    `numero`              INT(11)      NOT NULL,
    `modelo`              INT          NOT NULL,
    `tipo`                VARCHAR(8)   NOT NULL,
    `chave_acesso`        VARCHAR(50)  NULL,
    `natureza_operacao`   VARCHAR(255) NOT NULL,
    `serie`               INT          NOT NULL,
    `situacao`            VARCHAR(45)  NULL,
    `emissor_codigo`      INT(11)      NOT NULL,
    `destinatario_codigo` INT(11)      NOT NULL,
    `data_emissao`        DATE         NULL,
    `data_saida`          DATE         NULL,
    `hora_saida`          TIME         NULL,
    `data_alteracao`      TIMESTAMP    NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `data_cadastro`       TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `tipo_emissao`        INT          NULL,
    `totais_codigo`       INT(11)      NOT NULL,
    `tipo_impressao`      INT          NULL,
    `cDV`                 VARCHAR(45)  NULL,
    `procEmi`             INT          NULL,
    `verProc`             VARCHAR(255) NULL,
    `frete_tipo_codigo`   INT(11)      NOT NULL,
    `finalidade_codigo`   INT(11)      NOT NULL,
    `tipo_ambiente`       INT          NULL,
    PRIMARY KEY (`codigo`, `totais_codigo`),
    CONSTRAINT `fk_nota_fiscal_empresa10`
        FOREIGN KEY (`emissor_codigo`)
            REFERENCES `empresa` (`codigo`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_nota_fiscal_pessoa10`
        FOREIGN KEY (`destinatario_codigo`)
            REFERENCES `pessoa` (`codigo`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_nota_fiscal_nota_fiscal_totais1`
        FOREIGN KEY (`totais_codigo`)
            REFERENCES `nota_fiscal_totais` (`codigo`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_nota_fiscal_nota_fiscal_frete1`
        FOREIGN KEY (`frete_tipo_codigo`)
            REFERENCES `frete_tipo` (`codigo`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_nota_fiscal_nota_fiscal_finalidade1`
        FOREIGN KEY (`finalidade_codigo`)
            REFERENCES `nota_fiscal_finalidade` (`codigo`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

-- -----------------------------------------------------
-- Table `nota_fiscal_item_imposto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS nota_fiscal_item_imposto
(
    `codigo`     INT(11) NOT NULL AUTO_INCREMENT,
    `orig`       INT     NULL,
    `cst`        INT     NULL,
    `mod_bc`     INT     NULL,
    `v_bc`       DOUBLE  NULL,
    `p_icms`     DOUBLE  NULL,
    `v_icms`     DOUBLE  NULL,
    `cst_pis`    INT     NULL,
    `vbc_pis`    DOUBLE  NULL,
    `p_pis`      DOUBLE  NULL,
    `v_pis`      DOUBLE  NULL,
    `cst_cofins` INT     NULL,
    `vbc_cofins` DOUBLE  NULL,
    `p_cofins`   DOUBLE  NULL,
    `v_cofins`   DOUBLE  NULL,
    `cst_ipi`    INT     NULL,
    `vbc_ipi`    DOUBLE  NULL,
    `p_ipi`      DOUBLE  NULL,
    `v_ipi`      DOUBLE  NULL,
    PRIMARY KEY (`codigo`)
);

-- -----------------------------------------------------
-- Table `nota_fiscal_item`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS nota_fiscal_item
(
    `codigo`             INT(11)      NOT NULL AUTO_INCREMENT,
    `cod_prod`           INT(11)      NOT NULL,
    `qtd`                INT          NOT NULL,
    `vl_total`           DOUBLE       NOT NULL,
    `ceantrib`           VARCHAR(255) NULL,
    `unidade_tribu`      VARCHAR(12)  NOT NULL,
    `qtd_tribu`          INT          NOT NULL,
    `vl_uni_tribu`       DOUBLE       NOT NULL,
    `nota_fiscal_codigo` INT(11)      NOT NULL,
    `imposto_codigo`     INT(11)      NOT NULL,
    `cfop`               VARCHAR(12)  NOT NULL,
    PRIMARY KEY (`codigo`, `imposto_codigo`),
    CONSTRAINT `fk_nota_fiscal_item_nota_fiscal1`
        FOREIGN KEY (`nota_fiscal_codigo`)
            REFERENCES `nota_fiscal` (`codigo`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_nota_fiscal_item_nota_fiscal_item_imposto1`
        FOREIGN KEY (`imposto_codigo`)
            REFERENCES `nota_fiscal_item_imposto` (`codigo`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

-- -----------------------------------------------------
-- Table `cartao_lancamento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS cartao_lancamento
(
    `codigo`                INT(11)     NOT NULL AUTO_INCREMENT,
    `vl_parcela`            DOUBLE      NOT NULL,
    `taxa`                  DOUBLE      NOT NULL,
    `vl_taxa`               DOUBLE      NOT NULL,
    `vl_liq_parcela`        DOUBLE      NOT NULL,
    `taxa_antecipacao`      DOUBLE      NOT NULL,
    `vl_taxa_antecipacao`   DOUBLE      NOT NULL,
    `vl_liq_antecipacao`    DOUBLE      NOT NULL,
    `maquina_cartao_codigo` INT(11)     NOT NULL,
    `situacao`              VARCHAR(15) NOT NULL,
    `tipo`                  VARCHAR(10) NOT NULL,
    `data_recebimento`      DATE        NOT NULL,
    `data_cadastro`         TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `data_alteracao`        TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`codigo`),
    CONSTRAINT `fk_cartao_gerencial_maquina_cartao1`
        FOREIGN KEY (`maquina_cartao_codigo`)
            REFERENCES `maquina_cartao` (`codigo`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

-- -----------------------------------------------------
-- Table `estoque_movimentacao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS estoque_movimentacao
(
    `codigo`            INT(11)      NOT NULL AUTO_INCREMENT,
    `produto_codigo`    INT(11)      NOT NULL,
    `tipo`              VARCHAR(10)  NOT NULL,
    `qtd`               INT          NOT NULL,
    `origem_operacao`   VARCHAR(100) NOT NULL,
    `data_movimentacao` DATE         NOT NULL,
    `data_alteracao`    TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`codigo`),
    CONSTRAINT `fk_estoque_movimentacao_produto1`
        FOREIGN KEY (`produto_codigo`)
            REFERENCES `produto` (`codigo`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

-- -----------------------------------------------------
-- Table `produto_estoque`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS produto_estoque
(
    `codigo`         INT(11)   NOT NULL AUTO_INCREMENT,
    `produto_codigo` INT(11)   NOT NULL,
    `qtd`            INT       NOT NULL,
    `data_alteracao` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`codigo`),
    CONSTRAINT `fk_produto_estoque_produto1`
        FOREIGN KEY (`produto_codigo`)
            REFERENCES `produto` (`codigo`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

-- -----------------------------------------------------
-- Table `ajuste`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS ajuste
(
    `codigo`             INT          NOT NULL AUTO_INCREMENT,
    `observacao`         VARCHAR(255) NULL,
    `status`             VARCHAR(15)  NOT NULL,
    `usuario`            VARCHAR(100) NOT NULL,
    `data_cadastro`      TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `data_processamento` DATE         NULL,
    `data_alteracao`     TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`codigo`)
);

-- -----------------------------------------------------
-- Table `ajuste_produtos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS ajuste_produtos
(
    `codigo`         INT(11)   NOT NULL AUTO_INCREMENT,
    `ajuste_codigo`  INT       NOT NULL,
    `produto_codigo` INT(11)   NOT NULL,
    `estoque_atual`  INT       NOT NULL,
    `qtd_alteracao`  INT       NULL,
    `qtd_nova`       INT       NULL,
    `data_alteracao` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`codigo`),
    CONSTRAINT `fk_ajuste_produtos_produto1`
        FOREIGN KEY (`produto_codigo`)
            REFERENCES `produto` (`codigo`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_ajuste_produtos_ajuste1`
        FOREIGN KEY (`ajuste_codigo`)
            REFERENCES `ajuste` (`codigo`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);
