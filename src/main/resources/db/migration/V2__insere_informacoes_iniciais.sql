-- #INSERE PAISES
insert into pais (nome, pais_codigo)
values ('Brasil', '1058');

-- #INSERE ESTADO
insert into estado (codigo, codigoUF, nome, sigla, pais_codigo)
values (1, '11', 'Rondônia', 'RO', 1);
insert into estado (codigo, codigoUF, nome, sigla, pais_codigo)
values (2, '12', 'Acre', 'AC', 1);
insert into estado (codigo, codigoUF, nome, sigla, pais_codigo)
values (3, '13', 'Amazonas', 'AM', 1);
insert into estado (codigo, codigoUF, nome, sigla, pais_codigo)
values (4, '14', 'Roraima', 'RR', 1);
insert into estado (codigo, codigoUF, nome, sigla, pais_codigo)
values (5, '15', 'Pará', 'PA', 1);
insert into estado (codigo, codigoUF, nome, sigla, pais_codigo)
values (6, '16', 'Amapá', 'AP', 1);
insert into estado (codigo, codigoUF, nome, sigla, pais_codigo)
values (7, '17', 'Tocantins', 'TO', 1);
insert into estado (codigo, codigoUF, nome, sigla, pais_codigo)
values (8, '21', 'Maranhão', 'MA', 1);
insert into estado (codigo, codigoUF, nome, sigla, pais_codigo)
values (9, '22', 'Piauí', 'PI', 1);
insert into estado (codigo, codigoUF, nome, sigla, pais_codigo)
values (10, '23', 'Ceará', 'CE', 1);
insert into estado (codigo, codigoUF, nome, sigla, pais_codigo)
values (11, '24', 'Rio Grande do Norte', 'RN', 1);
insert into estado (codigo, codigoUF, nome, sigla, pais_codigo)
values (12, '25', 'Paraíba', 'PB', 1);
insert into estado (codigo, codigoUF, nome, sigla, pais_codigo)
values (13, '26', 'Pernambuco', 'PE', 1);
insert into estado (codigo, codigoUF, nome, sigla, pais_codigo)
values (14, '27', 'Alagoas', 'AL', 1);
insert into estado (codigo, codigoUF, nome, sigla, pais_codigo)
values (15, '28', 'Sergipe', 'SE', 1);
insert into estado (codigo, codigoUF, nome, sigla, pais_codigo)
values (16, '29', 'Bahia', 'BA', 1);
insert into estado (codigo, codigoUF, nome, sigla, pais_codigo)
values (17, '31', 'Minas Gerais', 'MG', 1);
insert into estado (codigo, codigoUF, nome, sigla, pais_codigo)
values (18, '32', 'Espírito Santo', 'ES', 1);
insert into estado (codigo, codigoUF, nome, sigla, pais_codigo)
values (19, '33', 'Rio de Janeiro', 'RJ', 1);
insert into estado (codigo, codigoUF, nome, sigla, pais_codigo)
values (20, '35', 'São Paulo', 'SP', 1);
insert into estado (codigo, codigoUF, nome, sigla, pais_codigo)
values (21, '41', 'Paraná', 'PR', 1);
insert into estado (codigo, codigoUF, nome, sigla, pais_codigo)
values (22, '42', 'Santa Catarina', 'SC', 1);
insert into estado (codigo, codigoUF, nome, sigla, pais_codigo)
values (23, '43', 'Rio Grande do Sul', 'RS', 1);
insert into estado (codigo, codigoUF, nome, sigla, pais_codigo)
values (24, '50', 'Mato Grosso do Sul', 'MS', 1);
insert into estado (codigo, codigoUF, nome, sigla, pais_codigo)
values (25, '51', 'Mato Grosso', 'MT', 1);
insert into estado (codigo, codigoUF, nome, sigla, pais_codigo)
values (26, '52', 'Goiás', 'GO', 1);
insert into estado (codigo, codigoUF, nome, sigla, pais_codigo)
values (27, '53', 'Distrito Federal', 'DF', 1);

-- #INSERE CIDADES
insert into cidade (nome, codigo_municipio, estado_codigo)
values ('Cacoal', '1100049', 1);
insert into cidade (nome, codigo_municipio, estado_codigo)
values ('Seringueiras', '1101500', 1);
insert into cidade (nome, codigo_municipio, estado_codigo)
values ('Rolim de Moura', '1100288', 1);

-- #INSERE GRUPO
insert into grupo (codigo, descricao)
values (1, 'Padrão');

-- #INSERE CATEGORIA
insert into categoria (codigo, descricao)
values (1, 'Padrão');

-- #INSERE TIPOS DE AMBIENTES PARA EMPRESA
insert into tipo_ambiente (codigo, descricao, tipo)
values (1, 'Produção', 1);
insert into tipo_ambiente (codigo, descricao, tipo)
values (2, 'Homologação', 2);

-- #INSERE PAGARTIPO
insert into pagartipo (descricao)
values ('Despesa com fornecedor');
insert into pagartipo (descricao)
values ('Despesa com funcionário');
insert into pagartipo (descricao)
values ('Despesa com equipamentos');
insert into pagartipo (descricao)
values ('Despesa com saude');
insert into pagartipo (descricao)
values ('Outras');

-- #INSERE TITULOS
insert into titulo_tipo (codigo, descricao, sigla)
values (1, 'Dinheiro', 'DIN');
insert into titulo_tipo (codigo, descricao, sigla)
values (2, 'Cartão Debito', 'CARTDEB');
insert into titulo_tipo (codigo, descricao, sigla)
values (3, 'Cartão Crédito', 'CARTCRED');

-- #INSERE OS TIPOS DE PAGAMENTO
insert into pagamento_tipo (descricao, forma_pagamento, qtd_parcelas)
values ('À Vista', '00', 1);
insert into pagamento_tipo (descricao, forma_pagamento, qtd_parcelas)
values ('A Prazo', '30', 1);
insert into pagamento_tipo (descricao, forma_pagamento, qtd_parcelas)
values ('Uma entrada + 30', '00/33', 2);

-- #INSERE ENDERECO DO FORNECEDOR
insert into endereco (codigo, rua, bairro, numero, cep, referencia, cidade_codigo)
values (2, 'av: integração nacional', 'Centro', '725', '75934000', 'O Sorvetão', 2);

-- #INSERE FORNECEDOR
insert into fornecedor (codigo, nome_fantasia, nome, cnpj, inscricao_estadual, ativo, endereco_codigo, observacao)
values (1, 'Fornecedor Padrão', 'Fornecedor Padrão', '11915857000158', '', 1, 2, 'Fornecedor padrão do sistema');

-- #INSERE TELEFONE DO FORNECEDOR
insert into telefone (codigo, fone, tipo)
values (2, '684442467', 'CELULAR');

-- #VINCULA TELEFONE AO FORNECEDOR
insert into fornecedor_telefone (telefone_codigo, fornecedor_codigo)
values (2, 1);

-- #INSERE PRODUTO
insert into produto (descricao, valor_venda, ativo, fornecedor_codigo, grupo_codigo, categoria_codigo, valor_balanca,
                     balanca, subtributaria, vendavel, controla_estoque)
values ('Picolé', 6.5, 'ATIVO', 1, 1, 1, 0, 0, 0, 'SIM', 'SIM');

-- #INSERE PRODUTO
insert into produto (descricao, valor_venda, ativo, fornecedor_codigo, grupo_codigo, categoria_codigo, valor_balanca,
                     balanca, subtributaria, vendavel, controla_estoque)
values ('Sorvete Kg', 0, 'ATIVO', 1, 1, 1, 0, 1, 0, 'SIM', 'SIM');

-- #INSERE ENDERECO
insert into endereco (codigo, rua, bairro, numero, cep, referencia, cidade_codigo)
values (1, 'av: integração nacional', 'Centro', '725', '75934000', 'O Sorvetão', 2);

-- #INSERE TELEFONE
insert into telefone (codigo, fone, tipo)
values (1, '684442467', 'CELULAR');

-- #INSERE PESSOA
insert into pessoa (nome, cpfcnpj, apelido, data_nascimento, observacao, endereco_codigo)
values ('João Rafael Mendes Nogueira', '015.505.822-32', 'João', '1993-04-30', 'Cliente de teste', 1);

-- #VINCULA PESSOA AO TELEFONE
insert into pessoa_telefone (pessoa_codigo, telefone_codigo)
values (1, 1);

-- #INSERE USUÁRIO GERENTE
insert into usuario (user, senha, pessoa_codigo)
values ('gerente', '$2a$10$xuMmyd6tQXff3DbzCvpnMuRqnYhs7IT6OsoZM48tPeclqB2d7FQb.', 1);

-- #INSERE GRUPOS ADMINISTRADOR E VENDEDOR
insert into grupousuario (nome, descricao)
values ('ADMINISTRADOR', 'Administrador com todas as permissões do sistema');
insert into grupousuario (nome, descricao)
values ('VENDEDOR', 'Grupo de vendedor do sistema');

-- #VINCULA USUARIO A UM DETERMINADO GRUPO
insert into usuario_grupousuario (grupo_usuario_codigo, usuario_codigo)
values (1, 1);

-- #INSERE PERMISSÕES
insert into permissoes (codigo, nome, descricao)
values (1, 'ENTRAR_NO_SISTEMA', 'Permite que o usuario realize o login');
insert into permissoes (codigo, nome, descricao)
values (2, 'VISUALIZAR_PESSOA', 'Permite que o usuario visualize o cadastro de pessoas');
insert into permissoes (codigo, nome, descricao)
values (3, 'EDITAR_PESSOA', 'Permite que o usuario edite cadastro de pessoas');
insert into permissoes (codigo, nome, descricao)
values (4, 'VISUALIZAR_FORNECEDOR', 'Permite que o usuario visualize o cadastro de fornecedor');
insert into permissoes (codigo, nome, descricao)
values (5, 'EDITAR_FORNECEDOR', 'Permite que o usuario edite o cadastro de fornecedor');
insert into permissoes (codigo, nome, descricao)
values (6, 'VISUALIZAR_GRUPO', 'Permite que o usuario visualize o cadastro de grupo');
insert into permissoes (codigo, nome, descricao)
values (7, 'EDITAR_GRUPO', 'Permite que o usuario edite o cadastro de grupo');
insert into permissoes (codigo, nome, descricao)
values (8, 'VISUALIZAR_CATEGORIA', 'Permite que o usuario visualize o cadastro de categoria');
insert into permissoes (codigo, nome, descricao)
values (9, 'EDITAR_CATEGORIA', 'Permite que o usuario edite o cadastro de categoria');
insert into permissoes (codigo, nome, descricao)
values (10, 'VISUALIZAR_PRODUTO', 'Permite que o usuario visualize o cadastro de produto');
insert into permissoes (codigo, nome, descricao)
values (11, 'EDITAR_PRODUTO', 'Permite que o usuario edite o cadastro de produto');
insert into permissoes (codigo, nome, descricao)
values (12, 'VISUALIZAR_USUARIO', 'Permite que o usuario visualize o cadastro de usuario');
insert into permissoes (codigo, nome, descricao)
values (13, 'EDITAR_USUARIO', 'Permite que o usuario edite o cadastro de usuario');
insert into permissoes (codigo, nome, descricao)
values (14, 'VISUALIZAR_MENU_CADASTRO', 'Permite que o usuario tenha acesso ao menu de cadastro');
insert into permissoes (codigo, nome, descricao)
values (15, 'VISUALIZAR_MENU_CAIXA', 'Permite que o usuario tenha acesso ao menu de caixa');
insert into permissoes (codigo, nome, descricao)
values (16, 'VISUALIZAR_MENU_RELATORIO', 'Permite que o usuario tenha acesso ao menu de relatorios');
insert into permissoes (codigo, nome, descricao)
values (17, 'VISUALIZAR_MENU_USUARIO', 'Permite que o usuario tenha acesso ao menu de usuario');
insert into permissoes (codigo, nome, descricao)
values (18, 'VISUALIZAR_PEDIDO_ABERTO', 'Permite que o usuario veja pedidos abertos');
insert into permissoes (codigo, nome, descricao)
values (19, 'VISUALIZAR_PEDIDO_FECHADO', 'Permite que o usuario veja pedidos fechados');
insert into permissoes (codigo, nome, descricao)
values (20, 'ABRIR_PEDIDO', 'Permite que o usuario abra um novo pedido');
insert into permissoes (codigo, nome, descricao)
values (21, 'GERAR_VENDA', 'Permite que o usuario gere uma venda');
insert into permissoes (codigo, nome, descricao)
values (22, 'INSERIR_PRODUTO_VENDA', 'Permite que o usuario insira um produto na venda');
insert into permissoes (codigo, nome, descricao)
values (23, 'REMOVER_PRODUTO_VENDA', 'Permite que o usuario remova um produto na venda');
insert into permissoes (codigo, nome, descricao)
values (24, 'LISTAR_CAIXA', 'Permite que o usuario liste os caixas');
insert into permissoes (codigo, nome, descricao)
values (25, 'ACESSAR_CAIXA', 'Permite que o usuario acesse o caixa');
insert into permissoes (codigo, nome, descricao)
values (26, 'CAIXA_SUPRIMENTO', 'Permite que o usuario faça suprimento');
insert into permissoes (codigo, nome, descricao)
values (27, 'CAIXA_SANGRIA', 'Permite que o usuario faça sangria');
insert into permissoes (codigo, nome, descricao)
values (28, 'CAIXA_TRANSFERENCIA', 'Permite que o usuario faça transferência entre contas');
insert into permissoes (codigo, nome, descricao)
values (29, 'FECHAR_CAIXA', 'Permite que o usuario feche o caixa');
insert into permissoes (codigo, nome, descricao)
values (30, 'VISUALIZAR_RECEBER', 'Permite que o usuario visualize o receber');
insert into permissoes (codigo, nome, descricao)
values (31, 'REALIZAR_RECEBIMENTO', 'Permite que o usuario realize o recebimento');
insert into permissoes (codigo, nome, descricao)
values (32, 'VISUALIZAR_DESPESAS', 'Permite que o usuario visualize as despesas');
insert into permissoes (codigo, nome, descricao)
values (33, 'PAGAR_DESPESA', 'Permite que o usuario page uma despesa');
insert into permissoes (codigo, nome, descricao)
values (34, 'VISUALIZAR_FORMA_PAGAMENTO', 'Permite que o usuario visualize as formas de pagamento');
insert into permissoes (codigo, nome, descricao)
values (35, 'CADASTRAR_FORMA_PAGAMENTO', 'Permite que o usuario cradastre formas de pagamento');
insert into permissoes (codigo, nome, descricao)
values (36, 'LISTA_TRIBUTAÇÃO', 'Permite que o usuario liste as tributações');
insert into permissoes (codigo, nome, descricao)
values (37, 'CADASTRA_TRIBUTACAO', 'Permite que o usuario cadastre uma tributação');
insert into permissoes (codigo, nome, descricao)
values (38, 'CADATRAR_REGRA_TRIBUTACAO', 'Permite que o usuario cadastre nova regra na tributação');
insert into permissoes (codigo, nome, descricao)
values (39, 'EXCLUIR_REGRA_TRIBUTACAO', 'Permite que o usuario exclua uma regra da tributação');
insert into permissoes (codigo, nome, descricao)
values (40, 'EDITAR_REGRA_TRIBUTACAO', 'Permite que o usuario edite uma regra da tributação');
insert into permissoes (codigo, nome, descricao)
values (41, 'CRIAR_NOTAFISCAL', 'Permite que o usuario crie nota fiscal');
insert into permissoes (codigo, nome, descricao)
values (42, 'VISUALIZA_NOTAFISCAL', 'Permite que o usuario visualize nota fiscal');
insert into permissoes (codigo, nome, descricao)
values (43, 'EDITAR_PARAMETROS', 'Permite que o usuario edite os parâmetros');
insert into permissoes (codigo, nome, descricao)
values (44, 'LISTAR_BANCO', 'Permite que o usuario liste os bancos');
insert into permissoes (codigo, nome, descricao)
values (45, 'EDITAR_CARTAO', 'Permite que o usuario crie e edite cartões de crédito e debito');
insert into permissoes (codigo, nome, descricao)
values (46, 'EDITAR_TITULO', 'Permite que o usuario crie e edite titulos');
insert into permissoes (codigo, nome, descricao)
values (47, 'GERENCIAR_CARTOES', 'Permite que o usuario gerencie os lançamentos de cartão');
insert into permissoes (codigo, nome, descricao)
values (48, 'ANTECIPAR_CARTAO', 'Permite que o usuario gerencie os lançamentos de cartão');
insert into permissoes (codigo, nome, descricao)
values (49, 'PROCESSAR_CARTAO', 'Permite que o usuario gerencie os lançamentos de cartão');
insert into permissoes (codigo, nome, descricao)
values (50, 'LISTA_AJUSTE', 'Permite que o usuario listar os ajustes de estoque');
insert into permissoes (codigo, nome, descricao)
values (51, 'FAZ_AJUSTE', 'Permite que o usuario realize e cancele ajustes');

-- #VINCULA PERMISSÕES AOS GRUPOS
insert into permissoes_grupo_usuario (grupo_usuario_codigo, permissoes_codigo)
values (1, 1);
insert into permissoes_grupo_usuario (grupo_usuario_codigo, permissoes_codigo)
values (1, 2);
insert into permissoes_grupo_usuario (grupo_usuario_codigo, permissoes_codigo)
values (1, 3);
insert into permissoes_grupo_usuario (grupo_usuario_codigo, permissoes_codigo)
values (1, 4);
insert into permissoes_grupo_usuario (grupo_usuario_codigo, permissoes_codigo)
values (1, 5);
insert into permissoes_grupo_usuario (grupo_usuario_codigo, permissoes_codigo)
values (1, 6);
insert into permissoes_grupo_usuario (grupo_usuario_codigo, permissoes_codigo)
values (1, 7);
insert into permissoes_grupo_usuario (grupo_usuario_codigo, permissoes_codigo)
values (1, 8);
insert into permissoes_grupo_usuario (grupo_usuario_codigo, permissoes_codigo)
values (1, 9);
insert into permissoes_grupo_usuario (grupo_usuario_codigo, permissoes_codigo)
values (1, 10);
insert into permissoes_grupo_usuario (grupo_usuario_codigo, permissoes_codigo)
values (1, 11);
insert into permissoes_grupo_usuario (grupo_usuario_codigo, permissoes_codigo)
values (1, 12);
insert into permissoes_grupo_usuario (grupo_usuario_codigo, permissoes_codigo)
values (1, 13);
insert into permissoes_grupo_usuario (grupo_usuario_codigo, permissoes_codigo)
values (1, 14);
insert into permissoes_grupo_usuario (grupo_usuario_codigo, permissoes_codigo)
values (1, 15);
insert into permissoes_grupo_usuario (grupo_usuario_codigo, permissoes_codigo)
values (1, 16);
insert into permissoes_grupo_usuario (grupo_usuario_codigo, permissoes_codigo)
values (1, 17);
insert into permissoes_grupo_usuario (grupo_usuario_codigo, permissoes_codigo)
values (1, 18);
insert into permissoes_grupo_usuario (grupo_usuario_codigo, permissoes_codigo)
values (1, 19);
insert into permissoes_grupo_usuario (grupo_usuario_codigo, permissoes_codigo)
values (1, 20);
insert into permissoes_grupo_usuario (grupo_usuario_codigo, permissoes_codigo)
values (1, 21);
insert into permissoes_grupo_usuario (grupo_usuario_codigo, permissoes_codigo)
values (1, 22);
insert into permissoes_grupo_usuario (grupo_usuario_codigo, permissoes_codigo)
values (1, 23);
insert into permissoes_grupo_usuario (grupo_usuario_codigo, permissoes_codigo)
values (1, 24);
insert into permissoes_grupo_usuario (grupo_usuario_codigo, permissoes_codigo)
values (1, 25);
insert into permissoes_grupo_usuario (grupo_usuario_codigo, permissoes_codigo)
values (1, 26);
insert into permissoes_grupo_usuario (grupo_usuario_codigo, permissoes_codigo)
values (1, 27);
insert into permissoes_grupo_usuario (grupo_usuario_codigo, permissoes_codigo)
values (1, 28);
insert into permissoes_grupo_usuario (grupo_usuario_codigo, permissoes_codigo)
values (1, 29);
insert into permissoes_grupo_usuario (grupo_usuario_codigo, permissoes_codigo)
values (1, 30);
insert into permissoes_grupo_usuario (grupo_usuario_codigo, permissoes_codigo)
values (1, 31);
insert into permissoes_grupo_usuario (grupo_usuario_codigo, permissoes_codigo)
values (1, 32);
insert into permissoes_grupo_usuario (grupo_usuario_codigo, permissoes_codigo)
values (1, 33);
insert into permissoes_grupo_usuario (grupo_usuario_codigo, permissoes_codigo)
values (1, 34);
insert into permissoes_grupo_usuario (grupo_usuario_codigo, permissoes_codigo)
values (1, 35);
insert into permissoes_grupo_usuario (grupo_usuario_codigo, permissoes_codigo)
values (1, 36);
insert into permissoes_grupo_usuario (grupo_usuario_codigo, permissoes_codigo)
values (1, 37);
insert into permissoes_grupo_usuario (grupo_usuario_codigo, permissoes_codigo)
values (1, 38);
insert into permissoes_grupo_usuario (grupo_usuario_codigo, permissoes_codigo)
values (1, 39);
insert into permissoes_grupo_usuario (grupo_usuario_codigo, permissoes_codigo)
values (1, 40);
insert into permissoes_grupo_usuario (grupo_usuario_codigo, permissoes_codigo)
values (1, 41);
insert into permissoes_grupo_usuario (grupo_usuario_codigo, permissoes_codigo)
values (1, 42);
insert into permissoes_grupo_usuario (grupo_usuario_codigo, permissoes_codigo)
values (1, 43);
insert into permissoes_grupo_usuario (grupo_usuario_codigo, permissoes_codigo)
values (1, 44);
insert into permissoes_grupo_usuario (grupo_usuario_codigo, permissoes_codigo)
values (1, 45);
insert into permissoes_grupo_usuario (grupo_usuario_codigo, permissoes_codigo)
values (1, 46);
insert into permissoes_grupo_usuario (grupo_usuario_codigo, permissoes_codigo)
values (1, 47);
insert into permissoes_grupo_usuario (grupo_usuario_codigo, permissoes_codigo)
values (1, 48);
insert into permissoes_grupo_usuario (grupo_usuario_codigo, permissoes_codigo)
values (1, 49);
insert into permissoes_grupo_usuario (grupo_usuario_codigo, permissoes_codigo)
values (1, 50);
insert into permissoes_grupo_usuario (grupo_usuario_codigo, permissoes_codigo)
values (1, 51);

-- #INSERE TIPOS DE REGIMES TRIBUTARIOS
insert into regime_tributario (descricao, tipo_regime)
values ('Simples Nacional', 1),
       ('Regime Normal', 2);

-- #INSERE FINALIDADES DA NOTA FISCAL
insert into nota_fiscal_finalidade (tipo, descricao)
values (1, 'NF-e normal'),
       (2, 'NF-e complementar'),
       (3, 'NF-e de ajuste');

-- #INSERE FRETE TIPO
insert into frete_tipo (tipo, descricao)
values (0, 'Por conta do emitente'),
       (1, 'Por conta do destinatário/remetente'),
       (2, 'Por conta de terceiros'),
       (9, 'Sem frete');

-- #INSERE MODALIDADE DA BASE DE CALCULO SEM ST
insert into mod_bc_icms (tipo, descricao, sub_tributaria)
values (0, 'Margem Valor Agregado (%)', 0),
       (1, 'Pauta (Valor)', 0),
       (2, 'Preço Tabelado Máx. (valor)', 0),
       (3, 'valor da operação', 0);

-- #INSERE MODALIDADE DA BASE DE CALCULO COM ST
insert into mod_bc_icms (tipo, descricao, sub_tributaria)
values (0, 'Preço tabelado ou máximo sugerido', 1),
       (1, 'Lista Negativa (valor)', 1),
       (2, 'Lista Positiva (valor)', 1),
       (3, 'Lista Neutra (valor)', 1),
       (4, 'Margem Valor Agregado (%)', 1),
       (3, 'Pauta (valor)', 1);

-- #INSERE CSOSN
insert into cst_csosn (cst_csosn, simples_nacional)
values ('101', 1),
       ('102', 1),
       ('103', 1),
       ('201', 1),
       ('202', 1),
       ('203', 1),
       ('300', 1),
       ('400', 1),
       ('500', 1),
       ('900', 1),
       ('00', 0),
       ('10', 0),
       ('20', 0),
       ('30', 0),
       ('40', 0),
       ('41', 0),
       ('50', 0),
       ('51', 0),
       ('60', 0),
       ('70', 0),
       ('90', 0);

-- #INSERE CST IPI ENTRADA
insert into cst_ipi (cst, descricao, tipo)
values ('00', 'Entrada com Recuperação de Crédito', 'ENTRADA'),
       ('01', 'Entrada Tributada com Alíquota Zero', 'ENTRADA'),
       ('02', 'Entrada Isenta', 'ENTRADA'),
       ('03', 'Entrada Não Tributada', 'ENTRADA'),
       ('04', 'Entrada Imune', 'ENTRADA'),
       ('05', 'Entrada com Suspensão', 'ENTRADA'),
       ('49', 'Outras Entradas', 'ENTRADA');

-- #INSERE CST IPI SAÍDA
insert into cst_ipi (cst, descricao, tipo)
values ('50', 'Saída Tributada', 'SAIDA'),
       ('51', 'Saída Tributável com Alíquota Zero', 'SAIDA'),
       ('52', 'Saída Isenta', 'SAIDA'),
       ('53', 'Saída Não Tributada', 'SAIDA'),
       ('54', 'Saída Imune', 'SAIDA'),
       ('55', 'Saída com Suspensão', 'SAIDA'),
       ('99', 'Outras Saídas', 'SAIDA');

-- #INSERE CST - PIS/COFINS
insert into cst (cst, descricao)
values ('01', 'Operação Tributável com Alíquota Básica'),
       ('02', 'Operação Tributável com Alíquota Diferenciada'),
       ('03', 'Operação Tributável com Alíquota por Unidade de Medida de Produto'),
       ('04', 'Operação Tributável Monofásica - Revenda a Alíquota Zero'),
       ('05', 'Operação Tributável por Substituição Tributária'),
       ('06', 'Operação Tributável a Alíquota Zero'),
       ('07', 'Operação Isenta da Contribuição'),
       ('08', 'Operação sem Incidência da Contribuição'),
       ('09', 'Operação com Suspensão da Contribuição'),
       ('49', 'Outras Operações de Saída'),
       ('50', 'Operação com Direito a Crédito - Vinculada Exclusivamente a Receita Tributada no Mercado Interno'),
       ('51', 'Operação com Direito a Crédito – Vinculada Exclusivamente a Receita Não Tributada no Mercado Interno'),
       ('52', 'Operação com Direito a Crédito - Vinculada Exclusivamente a Receita de Exportação'),
       ('53', 'Operação com Direito a Crédito - Vinculada a Receitas Tributadas e Não-Tributadas no Mercado Interno'),
       ('54', 'Operação com Direito a Crédito - Vinculada a Receitas Tributadas no Mercado Interno e de Exportação'),
       ('55',
        'Operação com Direito a Crédito - Vinculada a Receitas Não-Tributadas no Mercado Interno e de Exportação'),
       ('56',
        'Operação com Direito a Crédito - Vinculada a Receitas Tributadas e Não-Tributadas no Mercado Interno, e de Exportação'),
       ('60',
        'Crédito Presumido - Operação de Aquisição Vinculada Exclusivamente a Receita Tributada no Mercado Interno	'),
       ('61',
        'Crédito Presumido - Operação de Aquisição Vinculada Exclusivamente a Receita Não-Tributada no Mercado Interno'),
       ('62', 'Crédito Presumido - Operação de Aquisição Vinculada Exclusivamente a Receita de Exportação'),
       ('63',
        'Crédito Presumido - Operação de Aquisição Vinculada a Receitas Tributadas e Não-Tributadas no Mercado Interno'),
       ('64',
        'Crédito Presumido - Operação de Aquisição Vinculada a Receitas Tributadas no Mercado Interno e de Exportação'),
       ('65',
        'Crédito Presumido - Operação de Aquisição Vinculada a Receitas Não-Tributadas no Mercado Interno e de Exportação'),
       ('66',
        'Crédito Presumido - Operação de Aquisição Vinculada a Receitas Tributadas e Não-Tributadas no Mercado Interno, e de Exportação'),
       ('67', 'Crédito Presumido - Outras Operações'),
       ('70', 'Operação de Aquisição sem Direito a Crédito'),
       ('71', 'Operação de Aquisição com Isenção'),
       ('72', 'Operação de Aquisição com Suspensão'),
       ('73', 'Operação de Aquisição a Alíquota Zero'),
       ('74', 'Operação de Aquisição sem Incidência da Contribuição'),
       ('75', 'Operação de Aquisição por Substituição Tributária'),
       ('98', 'Outras Operações de Entrada'),
       ('99', 'Outras Operações');

-- #INSERE CFOPs
INSERT INTO `cfop` (`cfop`, `descricao`, `aplicacao`)
VALUES ('1000', 'ENTRADAS OU AQUISIÇÕES DE SERVIÇOS DO ESTADO',
        'Classificam-se, neste grupo, as operações ou prestações em que o estabelecimento remetente esteja localizado na mesma unidade da Federação do destinatário'),
       ('1100', 'COMPRAS PARA INDUSTRIALIZAÇÃO, PRODUÇÃO RURAL, COMERCIALIZAÇÃO OU PRESTAÇÃO DE SERVIÇOS',
        '(NR Ajuste SINIEF 05/2005) (DECRETO Nº 28.868, DE 31/01/2006)\r\n\r\n(Dec. 28.868/2006 – Efeitos a partir de 01/01/2006, ficando facultada ao contribuinte a sua adoção para fatos geradores ocorridos no período de 01 de novembro a 31 de dezembro de 2005)'),
       ('1101', 'Compra para industrialização ou produção rural (NR Ajuste SINIEF 05/2005) (Decreto 28.868/2006)',
        'Compra de mercadoria a ser utilizada em processo de industrialização ou produção rural, bem como a entrada de mercadoria em estabelecimento industrial ou produtor rural de cooperativa recebida de seus cooperados ou de estabelecimento de outra cooperativa.\r\n(DECRETO Nº 28.868, DE 31/01/2006-– Efeitos a partir de 01/01/2006, ficando facultada ao contribuinte a sua adoção para fatos geradores ocorridos no período de 01 de novembro a 31 de dezembro de 2005).'),
       ('1102', 'Compra para comercialização',
        'Classificam-se neste código as compras de mercadorias a serem comercializadas. Também serão classificadas neste código as entradas de mercadorias em estabelecimento comercial de cooperativa recebidas de seus cooperados ou de estabelecimento de outra cooperativa.'),
       ('1111', 'Compra para industrialização de mercadoria recebida anteriormente em consignação industrial',
        'Classificam-se neste código as compras efetivas de mercadorias a serem utilizadas em processo de industrialização, recebidas anteriormente a título de consignação industrial.'),
       ('1113', 'Compra para comercialização, de mercadoria recebida anteriormente em consignação mercantil',
        'Classificam-se neste código as compras efetivas de mercadorias recebidas anteriormente a título de consignação mercantil.'),
       ('1116',
        'Compra para industrialização ou produção rural originada de encomenda para recebimento futuro (NR Ajuste SINIEF 05/2005) (Decreto 28.868/2006)',
        'Compra de mercadoria, a ser utilizada em processo de industrialização ou produção rural, quando da entrada real da mercadoria, cuja aquisição tenha sido classificada no código “1.922 – Lançamento efetuado a título de simples faturamento decorrente de compra para recebimento futuro”.\r\n\r\n (DECRETO Nº 28.868, DE 31/01/2006-– Efeitos a partir de 01/01/2006, ficando facultada ao contribuinte a sua adoção para fatos geradores ocorridos no período de 01 de novembro a 31 de dezembro de 2005).'),
       ('1117', 'Compra para comercialização originada de encomenda para recebimento futuro',
        'Classificam-se neste código as compras de mercadorias a serem comercializadas, quando da entrada real da mercadoria, cuja aquisição tenha sido classificada no código 1.922 - Lançamento efetuado a título de simples faturamento decorrente de compra para recebimento futuro.'),
       ('1118',
        'Compra de mercadoria para comercialização pelo adquirente originário, entregue pelo vendedor remetente ao destinatário, em venda à ordem.',
        'Classificam-se neste código as compras de mercadorias já comercializadas, que, sem transitar pelo estabelecimento do adquirente originário, sejam entregues pelo vendedor remetente diretamente ao destinatário, em operação de venda à ordem, cuja venda seja classificada, pelo adquirente originário, no código 5.120 - Venda de mercadoria adquirida ou recebida de terceiros entregue ao destinatário pelo vendedor remetente, em venda à ordem.'),
       ('1120', 'Compra para industrialização, em venda à ordem, já recebida do vendedor remetente',
        'Classificam-se neste código as compras de mercadorias a serem utilizadas em processo de industrialização, em vendas à ordem, já recebidas do vendedor remetente, por ordem do adquirente originário.'),
       ('1121', 'Compra para comercialização, em venda à ordem, já recebida do vendedor remetente',
        'Classificam-se neste código as compras de mercadorias a serem comercializadas, em vendas à ordem, já recebidas do vendedor remetente por ordem do adquirente originário.'),
       ('1122',
        'Compra para industrialização em que a mercadoria foi remetida pelo fornecedor ao industrializador sem transitar pelo estabelecimento adquirente',
        'Classificam-se neste código as compras de mercadorias a serem utilizadas em processo de industrialização, remetidas pelo fornecedor para o industrializador sem que a mercadoria tenha transitado pelo estabelecimento do adquirente.'),
       ('1124', 'Industrialização efetuada por outra empresa',
        'Classificam-se neste código as entradas de mercadorias industrializadas por terceiros, compreendendo os valores referentes aos serviços prestados e os das mercadorias de propriedade do industrializador empregadas no processo industrial. Quando a industrialização efetuada se referir a bens do ativo imobilizado ou de mercadorias para uso ou consumo do estabelecimento encomendante, a entrada deverá ser classificada nos códigos 1.551 - Compra de bem para o ativo imobilizado ou 1.556 - Compra de material para uso ou consumo.'),
       ('1125',
        'Industrialização efetuada por outra empresa quando a mercadoria remetida para utilização no processo de industrialização não transitou pelo estabelecimento adquirente da mercadoria',
        'Classificam-se neste código as entradas de mercadorias industrializadas por outras empresas, em que as mercadorias remetidas para utilização no processo de industrialização não transitaram pelo estabelecimento do adquirente das mercadorias, compreendendo os valores referentes aos serviços prestados e os das mercadorias de propriedade do industrializador empregadas no processo industrial. Quando a industrialização efetuada se referir a bens do ativo imobilizado ou de mercadorias para uso ou consumo do estabelecimento encomend'),
       ('1126', 'Compra para utilização na prestação de serviço',
        'Classificam-se neste código as entradas de mercadorias a serem utilizadas nas prestações de serviços.'),
       ('1150',
        'TRANSFERÊNCIAS PARA INDUSTRIALIZAÇÃO, PRODUÇÃO RURAL, COMERCIALIZAÇÃO OU PRESTAÇÃO DE SERVIÇOS (NR Ajuste SINIEF 05/2005) (Decreto 28.868/2006)',
        '(DECRETO Nº 28.868, DE 31/01/2006 -&#x2013; Efeitos a partir de 01/01/2006, ficando facultada ao contribuinte a sua adoção para fatos geradores ocorridos no período de 01 de novembro a 31 de dezembro de 2005).'),
       ('1151',
        'Transferência para industrialização ou produção rural (NR Ajuste SINIEF 05/2005) (Decreto 28.868/2006)',
        'Entrada de mercadoria recebida, em transferência de outro estabelecimento da mesma empresa, para ser utilizada em processo de industrialização ou produção rural.\r\n\r\n(DECRETO Nº 28.868, DE 31/01/2006-– Efeitos a partir de 01/01/2006, ficando facultada ao contribuinte a sua adoção para fatos geradores ocorridos no período de 01 de novembro a 31 de dezembro de 2005).'),
       ('1152', 'Transferência para comercialização',
        'Classificam-se neste código as entradas de mercadorias recebidas em transferência de outro estabelecimento da mesma empresa, para serem comercializadas.'),
       ('1153', 'Transferência de energia elétrica para distribuição',
        'Classificam-se neste código as entradas de energia elétrica recebida em transferência de outro estabelecimento da mesma empresa, para distribuição.'),
       ('1154', 'Transferência para utilização na prestação de serviço',
        'Classificam-se neste código as entradas de mercadorias recebidas em transferência de outro estabelecimento da mesma empresa, para serem utilizadas nas prestações de serviços.'),
       ('1200', 'DEVOLUÇÕES DE VENDAS DE PRODUÇÃO DO ESTABELECIMENTO, DE PRODUTOS DE TERCEIROS OU ANULAÇÕES DE VALORES',
        NULL),
       ('1201', 'Devolução de venda de produção do estabelecimento',
        'Devolução de venda de produto industrializado ou produzido pelo estabelecimento, cuja saída tenha sido classificada como \"Venda de produção do estabelecimento\". (NR Ajuste SINIEF 05/2005) (Decreto 28.868/2006)\r\n\r\n(DECRETO Nº 28.868, DE 31/01/2006-– Efeitos a partir de 01/01/2006, ficando facultada ao contribuinte a sua adoção para fatos geradores ocorridos no período de 01 de novembro a 31 de dezembro de 2005).'),
       ('1202', 'Devolução de venda de mercadoria adquirida ou recebida de terceiros',
        'Classificam-se neste código as devoluções de vendas de mercadorias adquiridas ou recebidas de terceiros, que não tenham sido objeto de industrialização no estabelecimento, cujas saídas tenham sido classificadas como Venda de mercadoria adquirida ou recebida de terceiros.'),
       ('1203',
        'Devolução de venda de produção do estabelecimento, destinada à Zona Franca de Manaus ou Áreas de Livre Comércio',
        'Devolução de venda de produto industrializado ou produzido pelo estabelecimento, cuja saída tenha sido classificada no código \"5.109 – Venda de produção do estabelecimento destinada à Zona Franca de Manaus ou Áreas de Livre Comércio\". (NR Ajuste SINIEF 05/2005) (Decreto 28.868/2006)\r\n\r\n(DECRETO Nº 28.868, DE 31/01/2006-– Efeitos a partir de 01/01/2006, ficando facultada ao contribuinte a sua adoção para fatos geradores ocorridos no período de 01 de novembro a 31 de dezembro de 2005).'),
       ('1204',
        'Devolução de venda de mercadoria adquirida ou recebida de terceiros, destinada à Zona Franca de Manaus ou Áreas de Livre Comércio',
        'Classificam-se neste código as devoluções de vendas de mercadorias adquiridas ou recebidas de terceiros, cujas saídas foram classificadas no código 5.110 - Venda de mercadoria adquirida ou recebida de terceiros, destinada à Zona Franca de Manaus ou Áreas de Livre Comércio.'),
       ('1205', 'Anulação de valor relativo à prestação de serviço de comunicação',
        'Classificam-se neste código as anulações correspondentes a valores faturados indevidamente, decorrentes de prestações de serviços de comunicação.'),
       ('1206', 'Anulação de valor relativo à prestação de serviço de transporte',
        'Classificam-se neste código as anulações correspondentes a valores faturados indevidamente, decorrentes de prestações de serviços de transporte.'),
       ('1207', 'Anulação de valor relativo à venda de energia elétrica',
        'Classificam-se neste código as anulações correspondentes a valores faturados indevidamente, decorrentes de venda de energia elétrica.'),
       ('1208', 'Devolução de produção do estabelecimento, remetida em transferência',
        'Devolução de produto industrializado ou produzido pelo estabelecimento transferido para outro estabelecimento da mesma empresa. (NR Ajuste SINIEF 05/2005) (Decreto 28.868/2006)\r\n\r\n(DECRETO Nº 28.868, DE 31/01/2006-– Efeitos a partir de 01/01/2006, ficando facultada ao contribuinte a sua adoção para fatos geradores ocorridos no período de 01 de novembro a 31 de dezembro de 2005).'),
       ('1209', 'Devolução de mercadoria adquirida ou recebida de terceiros, remetida em transferência',
        'Classificam-se neste código as devoluções de mercadorias adquiridas ou recebidas de terceiros, transferidas para outros estabelecimentos da mesma empresa.'),
       ('1250', 'COMPRAS DE ENERGIA ELÉTRICA', NULL),
       ('1251', 'Compra de energia elétrica para distribuição ou comercialização',
        'Classificam-se neste código as compras de energia elétrica utilizada em sistema de distribuição ou comercialização. Também serão classificadas neste código as compras de energia elétrica por cooperativas para distribuição aos seus cooperados.'),
       ('1252', 'Compra de energia elétrica por estabelecimento industrial',
        'Classificam-se neste código as compras de energia elétrica utilizada no processo de industrialização. Também serão classificadas neste código as compras de energia elétrica utilizada por estabelecimento industrial de cooperativa.'),
       ('1253', 'Compra de energia elétrica por estabelecimento comercial',
        'Classificam-se neste código as compras de energia elétrica utilizada por estabelecimento comercial. Também serão classificadas neste código as compras de energia elétrica utilizada por estabelecimento comercial de cooperativa.'),
       ('1254', 'Compra de energia elétrica por estabelecimento prestador de serviço de transporte',
        'Classificam-se neste código as compras de energia elétrica utilizada por estabelecimento prestador de serviços de transporte.'),
       ('1255', 'Compra de energia elétrica por estabelecimento prestador de serviço de comunicação',
        'Classificam-se neste código as compras de energia elétrica utilizada por estabelecimento prestador de serviços de comunicação.'),
       ('1256', 'Compra de energia elétrica por estabelecimento de produtor rural',
        'Classificam-se neste código as compras de energia elétrica utilizada por estabelecimento de produtor rural.'),
       ('1257', 'Compra de energia elétrica para consumo por demanda contratada',
        'Classificam-se neste código as compras de energia elétrica para consumo por demanda contratada, que prevalecerá sobre os demais códigos deste subgrupo.'),
       ('1300', 'AQUISIÇÕES DE SERVIÇOS DE COMUNICAÇÃO', NULL),
       ('1301', 'Aquisição de serviço de comunicação para execução de serviço da mesma natureza',
        'Classificam-se neste código as aquisições de serviços de comunicação utilizados nas prestações de serviços da mesma natureza.'),
       ('1302', 'Aquisição de serviço de comunicação por estabelecimento industrial',
        'Classificam-se neste código as aquisições de serviços de comunicação utilizados por estabelecimento industrial. Também serão classificadas neste código as aquisições de serviços de comunicação utilizados por estabelecimento industrial de cooperativa.'),
       ('1303', 'Aquisição de serviço de comunicação por estabelecimento comercial',
        'Classificam-se neste código as aquisições de serviços de comunicação utilizados por estabelecimento comercial. Também serão classificadas neste código as aquisições de serviços de comunicação utilizados por estabelecimento comercial de cooperativa.'),
       ('1304', 'Aquisição de serviço de comunicação por estabelecimento de prestador de serviço de transporte',
        'Classificam-se neste código as aquisições de serviços de comunicação utilizados por estabelecimento prestador de serviço de transporte.'),
       ('1305',
        'Aquisição de serviço de comunicação por estabelecimento de geradora ou de distribuidora de energia elétrica',
        'Classificam-se neste código as aquisições de serviços de comunicação utilizados por estabelecimento de geradora ou de distribuidora de energia elétrica.'),
       ('1306', 'Aquisição de serviço de comunicação por estabelecimento de produtor rural',
        'Classificam-se neste código as aquisições de serviços de comunicação utilizados por estabelecimento de produtor rural.'),
       ('1350', 'AQUISIÇÕES DE SERVIÇOS DE TRANSPORTE', NULL),
       ('1351', 'Aquisição de serviço de transporte para execução de serviço da mesma natureza',
        'Classificam-se neste código as aquisições de serviços de transporte utilizados nas prestações de serviços da mesma natureza.'),
       ('1352', 'Aquisição de serviço de transporte por estabelecimento industrial',
        'Classificam-se neste código as aquisições de serviços de transporte utilizados por estabelecimento industrial. Também serão classificadas neste código as aquisições de serviços de transporte utilizados por estabelecimento industrial de cooperativa.'),
       ('1353', 'Aquisição de serviço de transporte por estabelecimento comercial',
        'Classificam-se neste código as aquisições de serviços de transporte utilizados por estabelecimento comercial. Também serão classificadas neste código as aquisições de serviços de transporte utilizados por estabelecimento comercial de cooperativa.'),
       ('1354', 'Aquisição de serviço de transporte por estabelecimento de prestador de serviço de comunicação',
        'Classificam-se neste código as aquisições de serviços de transporte utilizados por estabelecimento prestador de serviços de comunicação.'),
       ('1355',
        'Aquisição de serviço de transporte por estabelecimento de geradora ou de distribuidora de energia elétrica',
        'Classificam-se neste código as aquisições de serviços de transporte utilizados por estabelecimento de geradora ou de distribuidora de energia elétrica.'),
       ('1356', 'Aquisição de serviço de transporte por estabelecimento de produtor rural',
        'Classificam-se neste código as aquisições de serviços de transporte utilizados por estabelecimento de produtor rural.'),
       ('1360',
        'Aquisição de serviço de transporte por contribuinte-substituto em relação ao serviço de transporte (ACR) (Ajuste SINIEF 06/2007- Decreto nº 30.861/2007) –– a partir de 01.01.2008',
        'Aquisição de serviço de transporte quando o adquirente for contribuinte-substituto em relação ao imposto incidente na prestação dos serviços'),
       ('1400', 'ENTRADAS DE MERCADORIAS SUJEITAS AO REGIME DE SUBSTITUIÇÃO TRIBUTÁRIA', NULL),
       ('1401',
        'Compra para industrialização ou produção rural de mercadoria sujeita ao regime de substituição tributária (NR Ajuste SINIEF 05/2005) (Decreto 28.868/2006)',
        'Compra de mercadoria sujeita ao regime de substituição tributária, a ser utilizada em processo de industrialização ou produção rural, bem como compra, por estabelecimento industrial ou produtor rural de cooperativa, de mercadoria sujeita ao mencionado regime.\r\n\r\n(DECRETO Nº 28.868, DE 31/01/2006-– Efeitos a partir de 01/01/2006, ficando facultada ao contribuinte a sua adoção para fatos geradores ocorridos no período de 01 de novembro a 31 de dezembro de 2005).'),
       ('1403', 'Compra para comercialização em operação com mercadoria sujeita ao regime de substituição tributária',
        'Classificam-se neste código as compras de mercadorias a serem comercializadas, decorrentes de operações com mercadorias sujeitas ao regime de substituição tributária. Também serão classificadas neste código as compras de mercadorias sujeitas ao regime de substituição tributária em estabelecimento comercial de cooperativa.'),
       ('1406',
        'Compra de bem para o ativo imobilizado cuja mercadoria está sujeita ao regime de substituição tributária',
        'Classificam-se neste código as compras de bens destinados ao ativo imobilizado do estabelecimento, em operações com mercadorias sujeitas ao regime de substituição tributária.'),
       ('1407',
        'Compra de mercadoria para uso ou consumo cuja mercadoria está sujeita ao regime de substituição tributária',
        'Classificam-se neste código as compras de mercadorias destinadas ao uso ou consumo do estabelecimento, em operações com mercadorias sujeitas ao regime de substituição tributária.'),
       ('1408',
        'Transferência para industrialização ou produção rural de mercadoria sujeita ao regime de substituição tributária (NR Ajuste SINIEF 05/2005) (Decreto 28.868/2006)',
        'Mercadoria sujeita ao regime de substituição tributária, recebida em transferência de outro estabelecimento da mesma empresa, para ser industrializada ou consumida na produção rural no estabelecimento.\r\n\r\n(DECRETO Nº 28.868, DE 31/01/2006-– Efeitos a partir de 01/01/2006, ficando facultada ao contribuinte a sua adoção para fatos geradores ocorridos no período de 01 de novembro a 31 de dezembro de 2005).'),
       ('1409',
        'Transferência para comercialização em operação com mercadoria sujeita ao regime de substituição tributária',
        'Classificam-se neste código as mercadorias recebidas em transferência de outro estabelecimento da mesma empresa, para serem comercializadas, decorrentes de operações sujeitas ao regime de substituição tributária.'),
       ('1410',
        'Devolução de venda de mercadoria, de produção do estabelecimento, sujeita ao regime de substituição tributária',
        'Devolução de produto industrializado ou produzido pelo estabelecimento, cuja saída tenha sido classificada como \"Venda de mercadoria de produção do estabelecimento sujeita ao regime de substituição tributária\".\r\n\r\n(NR Ajuste SINIEF 05/2005) (DECRETO Nº 28.868, DE 31/01/2006-– Efeitos a partir de 01/01/2006, ficando facultada ao contribuinte a sua adoção para fatos geradores ocorridos no período de 01 de novembro a 31 de dezembro de 2005).'),
       ('1411',
        'Devolução de venda de mercadoria adquirida ou recebida de terceiros em operação com mercadoria sujeita ao regime de substituição tributária',
        'Classificam-se neste código as devoluções de vendas de mercadorias adquiridas ou recebidas de terceiros, cujas saídas tenham sido classificadas como Venda de mercadoria adquirida ou recebida de terceiros em operação com mercadoria sujeita ao regime de substituição tributária.'),
       ('1414',
        'Retorno de mercadoria de produção do estabelecimento, remetida para venda fora do estabelecimento, sujeita ao regime de substituição tributária',
        'Entrada, em retorno, de produto industrializado ou produzido pelo próprio estabelecimento, remetido para venda fora do estabelecimento, inclusive por meio de veículo, sujeito ao regime de substituição tributária e não comercializado.\r\n\r\n (NR Ajuste SINIEF 05/2005) (DECRETO Nº 28.868, DE 31/01/2006-– Efeitos a partir de 01/01/2006, ficando facultada ao contribuinte a sua adoção para fatos geradores ocorridos no período de 01 de novembro a 31 de dezembro de 2005).'),
       ('1415',
        'Retorno de mercadoria adquirida ou recebida de terceiros, remetida para venda fora do estabelecimento em operação com mercadoria sujeita ao regime de substituição tributária',
        'Classificam-se neste código as entradas, em retorno, de mercadorias adquiridas ou recebidas de terceiros remetidas para vendas fora do estabelecimento, inclusive por meio de veículos, em operações com mercadorias sujeitas ao regime de substituição tributária, e não comercializadas.'),
       ('1450', 'SISTEMAS DE INTEGRAÇÃO', NULL),
       ('1451', 'Retorno de animal do estabelecimento produtor',
        'Classificam-se neste código as entradas referentes ao retorno de animais criados pelo produtor no sistema integrado.'),
       ('1452', 'Retorno de insumo não utilizado na produção',
        'Classificam-se neste código o retorno de insumos não utilizados pelo produtor na criação de animais pelo sistema integrado.'),
       ('1500',
        'ENTRADAS DE MERCADORIAS REMETIDAS PARA FORMAÇÃO DE LOTE OU COM FIM ESPECÍFICO DE EXPORTAÇÃO E EVENTUAIS DEVOLUÇÕES (NR Ajuste SINIEF 09/2005)',
        '(DECRETO Nº 28.868, DE 31/01/2006—(Dec.\r\n28.868/2006 - a sua aplicação será obrigatória em relação aos fatos geradores ocorridos a partir de 01 de julho de 2006, ficando facultada ao contribuinte a sua adoção para fatos geradores ocorridos no período de 01 de janeiro a 30 de junho de 2006.'),
       ('1501', 'Entrada de mercadoria recebida com fim específico de exportação',
        'Classificam-se neste código as entradas de mercadorias em estabelecimento de trading company, empresa comercial exportadora ou outro estabelecimento do remetente, com fim específico de exportação.'),
       ('1503',
        'Entrada decorrente de devolução de produto, de fabricação do estabelecimento, remetido com fim específico de exportação',
        'Devolução de produto industrializado ou produzido pelo estabelecimento, remetido a \"trading company\", a empresa comercial exportadora ou a outro estabelecimento do remetente, com fim específico de exportação, cuja saída tenha sido classificada no código \"5.501 – Remessa de produção do estabelecimento com fim específico de exportação\".\r\n\r\n(NR Ajuste SINIEF 05/2005) (Dec.28.868/2006 - a sua aplicação será obrigatória em relação aos fatos geradores ocorridos a partir de 01 de julho de 2006, ficando facultada ao contribuinte a s'),
       ('1504',
        'Entrada decorrente de devolução de mercadoria remetida com fim específico de exportação, adquirida ou recebida de terceiros',
        'Devolução de mercadoria, adquirida ou recebida de terceiro, remetida a trading company, a empresa comercial exportadora ou a outro estabelecimento do remetente, com fim específico de exportação, cuja saída tenha sido classificada no código “5.502 - Remessa de mercadoria adquirida ou recebida de terceiros, com fim específico de exportação”.'),
       ('1505',
        'Entrada decorrente de devolução simbólica de mercadoria remetida para formação de lote de exportação, de produto industrializado ou produzido pelo próprio estabelecimento.',
        'Devolução simbólica de mercadoria remetida para formação de lote de exportação, cuja saída tenha sido classificada no código \"5.504 – Remessa de mercadoria para formação de lote de exportação, de produto industrializado ou produzido pelo próprio estabelecimento\".\r\n\r\n (ACR Ajuste SINIEF 09/2005) (Dec.28.868/2006 - a sua aplicação será obrigatória em relação aos fatos geradores ocorridos a partir de 01 de julho de 2006, ficando facultada ao contribuinte a sua adoção para fatos geradores ocorridos no período de 01 de janeiro a '),
       ('1506',
        'Entrada decorrente de devolução simbólica de mercadoria, adquirida ou recebida de terceiros, remetida para formação de lote de exportação.',
        'Devolução simbólica de mercadoria remetida para formação de lote de exportação em armazéns alfandegados, entrepostos aduaneiros ou outros estabelecimentos que venham a ser regulamentados pela legislação tributária de cada Unidade Federada, efetuada pelo estabelecimento depositário, cuja saída tenha sido classificada no código \"5.505 – Remessa de mercadoria, adquirida ou recebida de terceiros, para formação de lote de exportação\".\r\n\r\n (ACR Ajuste SINIEF 09/2005) (NR Ajuste SINIEF 05/2005) (Dec.28.868/2006 - a sua aplicação se'),
       ('1550', 'OPERAÇÕES COM BENS DE ATIVO IMOBILIZADO E MATERIAIS PARA USO OU CONSUMO', NULL),
       ('1551', 'Compra de bem para o ativo imobilizado',
        'Classificam-se neste código as compras de bens destinados ao ativo imobilizado do estabelecimento.'),
       ('1552', 'Transferência de bem do ativo imobilizado',
        'Classificam-se neste código as entradas de bens destinados ao ativo imobilizado recebidos em transferência de outro estabelecimento da mesma empresa.'),
       ('1553', 'Devolução de venda de bem do ativo imobilizado',
        'Classificam-se neste código as devoluções de vendas de bens do ativo imobilizado, cujas saídas tenham sido classificadas no código 5.551 - Venda de bem do ativo imobilizado.'),
       ('1554', 'Retorno de bem do ativo imobilizado remetido para uso fora do estabelecimento',
        'Classificam-se neste código as entradas por retorno de bens do ativo imobilizado remetidos para uso fora do estabelecimento, cujas saídas tenham sido classificadas no código 5.554 - Remessa de bem do ativo imobilizado para uso fora do estabelecimento.'),
       ('1555', 'Entrada de bem do ativo imobilizado de terceiro, remetido para uso no estabelecimento',
        'Classificam-se neste código as entradas de bens do ativo imobilizado de terceiros, remetidos para uso no estabelecimento.'),
       ('1556', 'Compra de material para uso ou consumo',
        'Classificam-se neste código as compras de mercadorias destinadas ao uso ou consumo do estabelecimento.'),
       ('1557', 'Transferência de material para uso ou consumo',
        'Classificam-se neste código as entradas de materiais para uso ou consumo recebidos em transferência de outro estabelecimento da mesma empresa.'),
       ('1600', 'CRÉDITOS E RESSARCIMENTOS DE ICMS', NULL),
       ('1601', 'Recebimento, por transferência, de crédito de ICMS',
        'Classificam-se neste código os lançamentos destinados ao registro de créditos de ICMS, recebidos por transferência de outras empresas.'),
       ('1602',
        'Recebimento, por transferência, de saldo credor do ICMS, de outro estabelecimento da mesma empresa, para compensação de saldo devedor do imposto.',
        'Lançamento destinado ao registro da transferência de saldo credor do ICMS, recebido de outro estabelecimento da mesma empresa, destinado à compensação do saldo devedor do estabelecimento, inclusive no caso de apuração centralizada do imposto.\r\n\r\n(NR Ajuste SINIEF 9/2003 – a partir 01.01.2004) (Decreto nº 26.174/2003)'),
       ('1603', 'Ressarcimento de ICMS retido por substituição tributária',
        'Lançamento destinado ao registro de ressarcimento de ICMS retido por substituição tributária à contribuinte substituído, efetuado pelo contribuinte substituto, ou, ainda, quando o ressarcimento for apropriado pelo próprio contribuinte substituído, nas hipóteses previstas na legislação aplicável.'),
       ('1604', 'Lançamento do crédito relativo à compra de bem para o ativo imobilizado',
        'Lançamento destinado ao registro da apropriação de crédito de bem do ativo imobilizado. (Dec.25.068/2003-EFEITOS A PARTIR DE 01.01.2003)'),
       ('1605', 'Recebimento, por transferência, de saldo devedor do ICMS de outro estabelecimento da mesma empresa',
        'Lançamento destinado ao registro da transferência de saldo devedor do ICMS, recebido de outro estabelecimento da mesma empresa, para efetivação da apuração centralizada do imposto. (ACR Ajuste SINIEF 03/2004) (DECRETO Nº 26.810/2004) (a partir de 01.01.2005)'),
       ('1650',
        'ENTRADAS DE COMBUSTÍVEIS, DERIVADOS OU NÃO DE PETRÓLEO, E LUBRIFICANTES (ACR Ajuste SINIEF 9/2003 - a partir 01.01.2004)',
        NULL),
       ('1651', 'Compra de combustível ou lubrificante para industrialização subseqüente',
        'Compra de combustível ou lubrificante a ser utilizados em processo de industrialização do próprio produto. (ACR Ajuste SINIEF 9/2003 - a partir 01.01.2004)'),
       ('1652', 'Compra de combustível ou lubrificante para comercialização',
        'Compra de combustível ou lubrificante a ser comercializados. (ACR Ajuste SINIEF 9/2003 - a partir 01.01.2004)'),
       ('1653', 'Compra de combustível ou lubrificante por consumidor ou usuário final',
        'Compra de combustível ou lubrificante, a ser consumidos em processo de industrialização de outros produtos, na produção rural, na prestação de serviço ou por usuário final.\r\n\r\n(NR Ajuste SINIEF 05/2005) (Dec.28.868/2006 - Efeitos a partir de 01/01/2006, ficando facultada ao contribuinte a sua adoção para fatos geradores ocorridos no período de 01 de novembro a 31 de dezembro de 2005)'),
       ('1658', 'Transferência de combustível ou lubrificante para industrialização',
        'Entrada de combustível ou lubrificante, recebidos em transferência de outro estabelecimento da mesma empresa, para ser utilizados em processo de industrialização do próprio produto.(Decreto 26.174/2003)(efeitos a partir 01.01.2004)'),
       ('1659', 'Transferência de combustível ou lubrificante para comercialização',
        'Entrada de combustível ou lubrificante, recebidos em transferência de outro estabelecimento da mesma empresa, para ser comercializados. .(Decreto 26.174/2003) (efeitos a partir 01.01.2004)'),
       ('1660', 'Devolução de venda de combustível ou lubrificante destinados à industrialização subseqüente',
        'Devolução de venda de combustível ou lubrificante, cuja saída tenha sido classificada como \"Venda de combustível ou lubrificante destinados à industrialização subseqüente\". (Decreto 26.174/2003)(efeitos a partir 01.01.2004)'),
       ('1661', 'Devolução de venda de combustível ou lubrificante destinados à comercialização',
        'Devolução de venda de combustível ou lubrificante, cuja saída tenha sido classificada como \"Venda de combustível ou lubrificante para comercialização\".(Decreto 26.174/2003)(efeitos a partir 01.01.2004).'),
       ('1662', 'Devolução de venda de combustível ou lubrificante destinados a consumidor ou usuário final',
        'Devolução de venda de combustível ou lubrificante, cuja saída tenha sido classificada como \"Venda de combustível ou lubrificante por consumidor ou usuário final\"..(Decreto 26.174/2003)(efeitos a partir 01.01.2004)'),
       ('1663', 'Entrada de combustível ou lubrificante para armazenagem',
        'Entrada de combustível ou lubrificante para armazenagem. .(Decreto 26.174/2003)(efeitos a partir 01.01.2004)'),
       ('1664', 'Retorno de combustível ou lubrificante remetidos para armazenagem',
        'Entrada, ainda que simbólica, por retorno de combustível ou lubrificante, remetidos para armazenagem. .(Decreto 26.174/2003)(efeitos a partir 01.01.2004)'),
       ('1900', 'OUTRAS ENTRADAS DE MERCADORIAS OU AQUISIÇÕES DE SERVIÇOS', NULL),
       ('1901', 'Entrada para industrialização por encomenda',
        'Classificam-se neste código as entradas de insumos recebidos para industrialização por encomenda de outra empresa ou de outro estabelecimento da mesma empresa.'),
       ('1902', 'Retorno de mercadoria remetida para industrialização por encomenda',
        'Classificam-se neste código o retorno dos insumos remetidos para industrialização por encomenda, incorporados ao produto final pelo estabelecimento industrializador.'),
       ('1903', 'Entrada de mercadoria remetida para industrialização e não aplicada no referido processo',
        'Classificam-se neste código as entradas em devolução de insumos remetidos para industrialização e não aplicados no referido processo.'),
       ('1904', 'Retorno de remessa para venda fora do estabelecimento',
        'Classificam-se neste código as entradas em retorno de mercadorias remetidas para venda fora do estabelecimento, inclusive por meio de veículos, e não comercializadas.'),
       ('1905', 'Entrada de mercadoria recebida para depósito em depósito fechado ou armazém geral',
        'Classificam-se neste código as entradas de mercadorias recebidas para depósito em depósito fechado ou armazém geral.'),
       ('1906', 'Retorno de mercadoria remetida para depósito fechado ou armazém geral',
        'Classificam-se neste código as entradas em retorno de mercadorias remetidas para depósito em depósito fechado ou armazém geral.'),
       ('1907', 'Retorno simbólico de mercadoria remetida para depósito fechado ou armazém geral',
        'Classificam-se neste código as entradas em retorno simbólico de mercadorias remetidas para depósito em depósito fechado ou armazém geral, quando as mercadorias depositadas tenham sido objeto de saída a qualquer título e que não tenham retornado ao estabelecimento depositante.'),
       ('1908', 'Entrada de bem por conta de contrato de comodato',
        'Classificam-se neste código as entradas de bens recebidos em cumprimento de contrato de comodato.'),
       ('1909', 'Retorno de bem remetido por conta de contrato de comodato',
        'Classificam-se neste código as entradas de bens recebidos em devolução após cumprido o contrato de comodato.'),
       ('1910', 'Entrada de bonificação, doação ou brinde',
        'Classificam-se neste código as entradas de mercadorias recebidas a título de bonificação, doação ou brinde.'),
       ('1911', 'Entrada de amostra grátis',
        'Classificam-se neste código as entradas de mercadorias recebidas a título de amostra grátis.'),
       ('1912', 'Entrada de mercadoria ou bem recebido para demonstração',
        'Classificam-se neste código as entradas de mercadorias ou bens recebidos para demonstração.'),
       ('1913', 'Retorno de mercadoria ou bem remetido para demonstração',
        'Classificam-se neste código as entradas em retorno de mercadorias ou bens remetidos para demonstração.'),
       ('1914', 'Retorno de mercadoria ou bem remetido para exposição ou feira',
        'Classificam-se neste código as entradas em retorno de mercadorias ou bens remetidos para exposição ou feira.'),
       ('1915', 'Entrada de mercadoria ou bem recebido para conserto ou reparo',
        'Classificam-se neste código as entradas de mercadorias ou bens recebidos para conserto ou reparo.'),
       ('1916', 'Retorno de mercadoria ou bem remetido para conserto ou reparo',
        'Classificam-se neste código as entradas em retorno de mercadorias ou bens remetidos para conserto ou reparo.'),
       ('1917', 'Entrada de mercadoria recebida em consignação mercantil ou industrial',
        'Classificam-se neste código as entradas de mercadorias recebidas a título de consignação mercantil ou industrial.'),
       ('1918', 'Devolução de mercadoria remetida em consignação mercantil ou industrial',
        'Classificam-se neste código as entradas por devolução de mercadorias remetidas anteriormente a título de consignação mercantil ou industrial.'),
       ('1919',
        'Devolução simbólica de mercadoria vendida ou utilizada em processo industrial, remetida anteriormente em consignação mercantil ou industrial',
        'Classificam-se neste código as entradas por devolução simbólica de mercadorias vendidas ou utilizadas em processo industrial, remetidas anteriormente a título de consignação mercantil ou industrial.'),
       ('1920', 'Entrada de vasilhame ou sacaria', 'Classificam-se neste código as entradas de vasilhame ou sacaria.'),
       ('1921', 'Retorno de vasilhame ou sacaria',
        'Classificam-se neste código as entradas em retorno de vasilhame ou sacaria.'),
       ('1922', 'Lançamento efetuado a título de simples faturamento decorrente de compra para recebimento futuro',
        'Classificam-se neste código os registros efetuados a título de simples faturamento decorrente de compra para recebimento futuro.'),
       ('1923', 'Entrada de mercadoria recebida do vendedor remetente, em venda à ordem',
        'Classificam-se neste código as entradas de mercadorias recebidas do vendedor remetente, em vendas à ordem, cuja compra do adquirente originário, foi classificada nos códigos 1.120 - Compra para industrialização, em venda à ordem, já recebida do vendedor remetente ou 1.121 - Compra para comercialização, em venda à ordem, já recebida do vendedor remetente.'),
       ('1924',
        'Entrada para industrialização por conta e ordem do adquirente da mercadoria, quando esta não transitar pelo estabelecimento do adquirente',
        'Classificam-se neste código as entradas de insumos recebidos para serem industrializados por conta e ordem do adquirente, nas hipóteses em que os insumos não tenham transitado pelo estabelecimento do adquirente dos mesmos.'),
       ('1925',
        'Retorno de mercadoria remetida para industrialização por conta e ordem do adquirente da mercadoria, quando esta não transitar pelo estabelecimento do adquirente',
        'Classificam-se neste código o retorno dos insumos remetidos por conta e ordem do adquirente, para industrialização e incorporados ao produto final pelo estabelecimento industrializador, nas hipóteses em que os insumos não tenham transitado pelo estabelecimento do adquirente.'),
       ('1926',
        'Lançamento efetuado a título de reclassificação de mercadoria decorrente de formação de kit ou de sua desagregação',
        'Classificam-se neste código os registros efetuados a título de reclassificação decorrente de formação de kit de mercadorias ou de sua desagregação.'),
       ('1931',
        'Lançamento efetuado pelo tomador do serviço de transporte, quando a responsabilidade de retenção do imposto for atribuída ao remetente ou alienante da mercadoria, pelo serviço de transporte realizado ',
        'Lançamento efetuado pelo tomador do serviço de transporte realizado por transportador autônomo ou por transportador não-inscrito na Unidade da Federação onde se tenha iniciado o serviço, quando a responsabilidade pela retenção do imposto for atribuída ao remetente ou alienante da mercadoria.\r\n\r\n (ACR Ajuste SINIEF 03/2004) (DECRETO Nº 26.810/2004))(efeitos a partir 01.01.2005)'),
       ('1932',
        'Aquisição de serviço de transporte iniciado em Unidade da Federação diversa daquela onde esteja inscrito o prestador',
        'Aquisição de serviço de transporte que tenha sido iniciado em Unidade da Federação diversa daquela onde o prestador esteja inscrito como contribuinte. (ACR Ajuste SINIEF 03/2004) (DECRETO Nº 26.810/2004) (efeitos a partir 01.01.2005)'),
       ('1933',
        'Aquisição de serviço tributado pelo Imposto sobre Serviços de Qualquer Natureza (Ajuste SINIEF 06/2005) (NR)',
        'Aquisição de serviço, cujo imposto é de competência municipal, desde que informado em Nota Fiscal modelo 1 ou 1-A. (NR Ajuste SINIEF 06/2005) (DECRETO Nº 26.868/2006 - efeitos a partir 01.01.2006)'),
       ('1949', 'Outra entrada de mercadoria ou prestação de serviço não especificada',
        'Classificam-se neste código as outras entradas de mercadorias ou prestações de serviços que não tenham sido especificadas nos códigos anteriores.'),
       ('2000', 'ENTRADAS OU AQUISIÇÕES DE SERVIÇOS DE OUTROS ESTADOS',
        'Classificam-se, neste grupo, as operações ou prestações em que o estabelecimento remetente esteja localizado em unidade da Federação diversa daquela do destinatário'),
       ('2100',
        'COMPRAS PARA INDUSTRIALIZAÇÃO, PRODUÇÃO RURAL, COMERCIALIZAÇÃO OU PRESTAÇÃO DE SERVIÇOS (NR Ajuste SINIEF 05/2005  (Decreto 28.868/2006)',
        '(NR Ajuste SINIEF 05/2005) (Dec. 28.868/2006 - Efeitos a partir de 01/01/2006, ficando facultada ao contribuinte a sua adoção para fatos geradores ocorridos no período de 01 de novembro a 31 de dezembro de 2005)'),
       ('2101', 'Compra para industrialização ou produção rural (NR Ajuste SINIEF 05/2005) (Decreto 28.868/2006)',
        'Compra de mercadoria a ser utilizada em processo de industrialização ou produção rural, bem como a entrada de mercadoria em estabelecimento industrial ou produtor rural de cooperativa, recebida de seus cooperados ou de estabelecimento de outra cooperativa.\r\n\r\n(NR Ajuste SINIEF 05/2005) (Dec. 28.868/2006 - Efeitos a partir de 01/01/2006, ficando facultada ao contribuinte a sua adoção para fatos geradores ocorridos no período de 01 de novembro a 31 de dezembro de 2005)'),
       ('2102', 'Compra para comercialização',
        'Classificam-se neste código as compras de mercadorias a serem comercializadas. Também serão classificadas neste código as entradas de mercadorias em estabelecimento comercial de cooperativa recebidas de seus cooperados ou de estabelecimento de outra cooperativa.'),
       ('2111', 'Compra para industrialização de mercadoria recebida anteriormente em consignação industrial',
        'Classificam-se neste código as compras efetivas de mercadorias a serem utilizadas em processo de industrialização, recebidas anteriormente a título de consignação industrial.'),
       ('2113', 'Compra para comercialização, de mercadoria recebida anteriormente em consignação mercantil',
        'Classificam-se neste código as compras efetivas de mercadorias recebidas anteriormente a título de consignação mercantil.'),
       ('2116',
        'Compra para industrialização ou produção rural originada de encomenda para recebimento futuro (NR Ajuste SINIEF 05/2005) (Decreto 28.868/2006)',
        'Compra de mercadoria a ser utilizada em processo de industrialização ou produção rural, quando da entrada real da mercadoria, cuja aquisição tenha sido classificada no código \"2.922 – Lançamento efetuado a título de simples faturamento decorrente de compra para recebimento futuro\".\r\n\r\n(NR Ajuste SINIEF 05/2005) (Dec. 28.868/2006 - Efeitos a partir de 01/01/2006, ficando facultada ao contribuinte a sua adoção para fatos geradores ocorridos no período de 01 de novembro a 31 de dezembro de 2005)'),
       ('2117', 'Compra para comercialização originada de encomenda para recebimento futuro',
        'Classificam-se neste código as compras de mercadorias a serem comercializadas, quando da entrada real da mercadoria, cuja aquisição tenha sido classificada no código 2.922 - Lançamento efetuado a título de simples faturamento decorrente de compra para recebimento futuro.'),
       ('2118',
        'Compra de mercadoria para comercialização pelo adquirente originário, entregue pelo vendedor remetente ao destinatário, em venda à ordem',
        'Classificam-se neste código as compras de mercadorias já comercializadas, que, sem transitar pelo estabelecimento do adquirente originário, sejam entregues pelo vendedor remetente diretamente ao destinatário, em operação de venda à ordem, cuja venda seja classificada, pelo adquirente originário, no código 6.120 - Venda de mercadoria adquirida ou recebida de terceiros entregue ao destinatário pelo vendedor remetente, em venda à ordem.'),
       ('2120', 'Compra para industrialização, em venda à ordem, já recebida do vendedor remetente',
        'Classificam-se neste código as compras de mercadorias a serem utilizadas em processo de industrialização, em vendas à ordem, já recebidas do vendedor remetente, por ordem do adquirente originário.'),
       ('2121', 'Compra para comercialização, em venda à ordem, já recebida do vendedor remetente',
        'Classificam-se neste código as compras de mercadorias a serem comercializadas, em vendas à ordem, já recebidas do vendedor remetente por ordem do adquirente originário.'),
       ('2122',
        'Compra para industrialização em que a mercadoria foi remetida pelo fornecedor ao industrializador sem transitar pelo estabelecimento adquirente',
        'Classificam-se neste código as compras de mercadorias a serem utilizadas em processo de industrialização, remetidas pelo fornecedor para o industrializador sem que a mercadoria tenha transitado pelo estabelecimento do adquirente.'),
       ('2124', 'Industrialização efetuada por outra empresa',
        'Classificam-se neste código as entradas de mercadorias industrializadas por terceiros, compreendendo os valores referentes aos serviços prestados e os das mercadorias de propriedade do industrializador empregadas no processo industrial. Quando a industrialização efetuada se referir a bens do ativo imobilizado ou de mercadorias para uso ou consumo do estabelecimento encomendante, a entrada deverá ser classificada nos códigos 2.551 - Compra de bem para o ativo imobilizado ou 2.556 - Compra de material para uso ou consumo.'),
       ('2125',
        'Industrialização efetuada por outra empresa quando a mercadoria remetida para utilização no processo de industrialização não transitou pelo estabelecimento adquirente da mercadoria',
        'Classificam-se neste código as entradas de mercadorias industrializadas por outras empresas, em que as mercadorias remetidas para utilização no processo de industrialização não transitaram pelo estabelecimento do adquirente das mercadorias, compreendendo os valores referentes aos serviços prestados e os das mercadorias de propriedade do industrializador empregadas no processo industrial. Quando a industrialização efetuada se referir a bens do ativo imobilizado ou de mercadorias para uso ou consumo do estabelecimento encomend'),
       ('2126', 'Compra para utilização na prestação de serviço',
        'Classificam-se neste código as entradas de mercadorias a serem utilizadas nas prestações de serviços.'),
       ('2150',
        'TRANSFERÊNCIAS PARA INDUSTRIALIZAÇÃO, PRODUÇÃO RURAL, COMERCIALIZAÇÃO OU PRESTAÇÃO DE SERVIÇOS (NR Ajuste SINIEF 05/2005) (Decreto 28.868/2006)',
        '(NR Ajuste SINIEF 05/2005) (Dec. 28.868/2006 - Efeitos a partir de 01/01/2006, ficando facultada ao contribuinte a sua adoção para fatos geradores ocorridos no período de 01 de novembro a 31 de dezembro de 2005)'),
       ('2151',
        'Transferência para industrialização ou produção rural (NR Ajuste SINIEF 05/2005) (Decreto 28.868/2006)',
        'Entrada de mercadoria, recebida em transferência de outro estabelecimento da mesma empresa, para ser utilizada em processo de industrialização ou produção rural.\r\n\r\n(NR Ajuste SINIEF 05/2005) (Dec. 28.868/2006 - Efeitos a partir de 01/01/2006, ficando facultada ao contribuinte a sua adoção para fatos geradores ocorridos no período de 01 de novembro a 31 de dezembro de 2005)'),
       ('2152', 'Transferência para comercialização',
        'Classificam-se neste código as entradas de mercadorias recebidas em transferência de outro estabelecimento da mesma empresa, para serem comercializadas.'),
       ('2153', 'Transferência de energia elétrica para distribuição',
        'Classificam-se neste código as entradas de energia elétrica recebida em transferência de outro estabelecimento da mesma empresa, para distribuição.'),
       ('2154', 'Transferência para utilização na prestação de serviço',
        'Classificam-se neste código as entradas de mercadorias recebidas em transferência de outro estabelecimento da mesma empresa, para serem utilizadas nas prestações de serviços.'),
       ('2200', 'DEVOLUÇÕES DE VENDAS DE PRODUÇÃO DO ESTABELECIMENTO OU DE TERCEIROS OU ANULAÇÕES DE VALORES', NULL),
       ('2201', 'Devolução de venda de produção do estabelecimento',
        'Devolução de venda de produto industrializado ou produzido pelo estabelecimento, cuja saída tenha sido classificada no código \"6.101 - Venda de produção do estabelecimento\".\r\n\r\n(NR Ajuste SINIEF 05/2005) (Dec. 28.868/2006 - Efeitos a partir de 01/01/2006, ficando facultada ao contribuinte a sua adoção para fatos geradores ocorridos no período de 01 de novembro a 31 de dezembro de 2005)'),
       ('2202', 'Devolução de venda de mercadoria adquirida ou recebida de terceiros',
        'Devolução de vendas de mercadoria, adquirida ou recebida de terceiro, que não tenham sido objeto de industrialização no estabelecimento, cuja saída tenha sido classificada como Venda de mercadoria adquirida ou recebida de terceiros.'),
       ('2203',
        'Devolução de venda de produção do estabelecimento destinada à Zona Franca de Manaus ou Áreas de Livre Comércio',
        'Devolução de venda de produto industrializado ou produzido pelo estabelecimento, cuja saída tenha sido classificada no código \"6.109 – Venda de produção do estabelecimento destinada à Zona Franca de Manaus ou Áreas de Livre Comércio\".\r\n\r\n (NR Ajuste SINIEF 05/2005) (Dec. 28.868/2006 - Efeitos a partir de 01/01/2006, ficando facultada ao contribuinte a sua adoção para fatos geradores ocorridos no período de 01 de novembro a 31 de dezembro de 2005)'),
       ('2204',
        'Devolução de venda de mercadoria adquirida ou recebida de terceiros, destinada à Zona Franca de Manaus ou Áreas de Livre Comércio',
        'Devolução de venda de mercadoria adquirida ou recebida de terceiro, cuja saída tenha sido classificada no código “6.110 - Venda de mercadoria adquirida ou recebida de terceiros, destinada à Zona Franca de Manaus ou Áreas de Livre Comércio”.'),
       ('2205', 'Anulação de valor relativo à prestação de serviço de comunicação',
        'Anulação correspondente a valor faturado indevidamente, decorrente de prestação de serviço de comunicação.'),
       ('2206', 'Anulação de valor relativo à prestação de serviço de transporte',
        'Anulação correspondente a valor faturado indevidamente, decorrente de prestação de serviço de transporte.'),
       ('2207', 'Anulação de valor relativo à venda de energia elétrica',
        'Anulação correspondente a valor faturado indevidamente, decorrente de venda de energia elétrica.'),
       ('2208', 'Devolução de produção do estabelecimento, remetida em transferência.',
        'Devolução de produto industrializado ou produzido pelo estabelecimento e transferido para outro estabelecimento da mesma empresa.\r\n\r\n(NR Ajuste SINIEF 05/2005) (Dec. 28.868/2006 - Efeitos a partir de 01/01/2006, ficando facultada ao contribuinte a sua adoção para fatos geradores ocorridos no período de 01 de novembro a 31 de dezembro de 2005)'),
       ('2209', 'Devolução de mercadoria adquirida ou recebida de terceiros e remetida em transferência',
        'Devolução de mercadoria adquirida ou recebida de terceiros, transferidas para outros estabelecimentos da mesma empresa.'),
       ('2250', 'COMPRAS DE ENERGIA ELÉTRICA', NULL),
       ('2251', 'Compra de energia elétrica para distribuição ou comercialização',
        'Classificam-se neste código as compras de energia elétrica utilizada em sistema de distribuição ou comercialização. Também serão classificadas neste código as compras de energia elétrica por cooperativas para distribuição com seus cooperados.'),
       ('2252', 'Compra de energia elétrica por estabelecimento industrial',
        'Classificam-se neste código as compras de energia elétrica utilizada no processo de industrialização. Também serão classificadas neste código as compras de energia elétrica utilizada por estabelecimento industrial de cooperativa.'),
       ('2253', 'Compra de energia elétrica por estabelecimento comercial',
        'Classificam-se neste código as compras de energia elétrica utilizada por estabelecimento comercial. Também serão classificadas neste código as compras de energia elétrica utilizada por estabelecimento comercial de cooperativa.'),
       ('2254', 'Compra de energia elétrica por estabelecimento prestador de serviço de transporte',
        'Classificam-se neste código as compras de energia elétrica utilizada por estabelecimento prestador de serviços de transporte.'),
       ('2255', 'Compra de energia elétrica por estabelecimento prestador de serviço de comunicação',
        'Classificam-se neste código as compras de energia elétrica utilizada por estabelecimento prestador de serviços de comunicação.'),
       ('2256', 'Compra de energia elétrica por estabelecimento de produtor rural',
        'Classificam-se neste código as compras de energia elétrica utilizada por estabelecimento de produtor rural.'),
       ('2257', 'Compra de energia elétrica para consumo por demanda contratada',
        'Classificam-se neste código as compras de energia elétrica para consumo por demanda contratada, que prevalecerá sobre os demais códigos deste subgrupo.'),
       ('2300', 'AQUISIÇÕES DE SERVIÇOS DE COMUNICAÇÃO', NULL),
       ('2301', 'Aquisição de serviço de comunicação para execução de serviço da mesma natureza',
        'Classificam-se neste código as aquisições de serviços de comunicação utilizados nas prestações de serviços da mesma natureza.'),
       ('2302', 'Aquisição de serviço de comunicação por estabelecimento industrial',
        'Classificam-se neste código as aquisições de serviços de comunicação utilizados por estabelecimento industrial. Também serão classificadas neste código as aquisições de serviços de comunicação utilizados por estabelecimento industrial de cooperativa.'),
       ('2303', 'Aquisição de serviço de comunicação por estabelecimento comercial',
        'Classificam-se neste código as aquisições de serviços de comunicação utilizados por estabelecimento comercial. Também serão classificadas neste código as aquisições de serviços de comunicação utilizados por estabelecimento comercial de cooperativa.'),
       ('2304', 'Aquisição de serviço de comunicação por estabelecimento de prestador de serviço de transporte',
        'Classificam-se neste código as aquisições de serviços de comunicação utilizado por estabelecimento prestador de serviço de transporte.'),
       ('2305',
        'Aquisição de serviço de comunicação por estabelecimento de geradora ou de distribuidora de energia elétrica',
        'Classificam-se neste código as aquisições de serviços de comunicação utilizados por estabelecimento de geradora ou de distribuidora de energia elétrica.'),
       ('2306', 'Aquisição de serviço de comunicação por estabelecimento de produtor rural',
        'Classificam-se neste código as aquisições de serviços de comunicação utilizados por estabelecimento de produtor rural.'),
       ('2350', 'AQUISIÇÕES DE SERVIÇOS DE TRANSPORTE', NULL),
       ('2351', 'Aquisição de serviço de transporte para execução de serviço da mesma natureza',
        'Classificam-se neste código as aquisições de serviços de transporte utilizados nas prestações de serviços da mesma natureza.'),
       ('2352', 'Aquisição de serviço de transporte por estabelecimento industrial',
        'Classificam-se neste código as aquisições de serviços de transporte utilizados por estabelecimento industrial. Também serão classificadas neste código as aquisições de serviços de transporte utilizados por estabelecimento industrial de cooperativa.'),
       ('2353', 'Aquisição de serviço de transporte por estabelecimento comercial',
        'Classificam-se neste código as aquisições de serviços de transporte utilizados por estabelecimento comercial. Também serão classificadas neste código as aquisições de serviços de transporte utilizados por estabelecimento comercial de cooperativa.'),
       ('2354', 'Aquisição de serviço de transporte por estabelecimento de prestador de serviço de comunicação',
        'Classificam-se neste código as aquisições de serviços de transporte utilizados por estabelecimento prestador de serviços de comunicação.'),
       ('2355',
        'Aquisição de serviço de transporte por estabelecimento de geradora ou de distribuidora de energia elétrica',
        'Classificam-se neste código as aquisições de serviços de transporte utilizados por estabelecimento de geradora ou de distribuidora de energia elétrica.'),
       ('2356', 'Aquisição de serviço de transporte por estabelecimento de produtor rural',
        'Classificam-se neste código as aquisições de serviços de transporte utilizados por estabelecimento de produtor rural.'),
       ('2400', 'ENTRADAS DE MERCADORIAS SUJEITAS AO REGIME DE SUBSTITUIÇÃO TRIBUTÁRIA', NULL),
       ('2401',
        'Compra para industrialização ou produção rural de mercadoria sujeita ao regime de substituição tributária (NR Ajuste SINIEF 05/2005) (Decreto 28.868/2006)',
        'Compra de mercadoria, sujeita ao regime de substituição tributária, a ser utilizada em processo de industrialização ou produção rural, bem como compra, por estabelecimento industrial ou produtor rural de cooperativa, de mercadoria sujeita ao mencionado regime.\n\n(NR Ajuste SINIEF 05/2005) (Dec. 28.868/2006 - Efeitos a partir de 01/01/2006, ficando facultada ao contribuinte a sua adoção para fatos geradores ocorridos no período de 01 de novembro a 31 de dezembro de 2005)'),
       ('2403', 'Compra para comercialização em operação com mercadoria sujeita ao regime de substituição tributária',
        'Classificam-se neste código as compras de mercadorias a serem comercializadas, decorrentes de operações com mercadorias sujeitas ao regime de substituição tributária. Também serão classificadas neste código as compras de mercadorias sujeitas ao regime de substituição tributária em estabelecimento comercial de cooperativa.'),
       ('2406',
        'Compra de bem para o ativo imobilizado cuja mercadoria está sujeita ao regime de substituição tributária',
        'Classificam-se neste código as compras de bens destinados ao ativo imobilizado do estabelecimento, em operações com mercadorias sujeitas ao regime de substituição tributária.'),
       ('2407',
        'Compra de mercadoria para uso ou consumo cuja mercadoria está sujeita ao regime de substituição tributária',
        'Classificam-se neste código as compras de mercadorias destinadas ao uso ou consumo do estabelecimento, em operações com mercadorias sujeitas ao regime de substituição tributária.'),
       ('2408',
        'Transferência para industrialização ou produção rural de mercadoria sujeita ao regime de substituição tributária (NR Ajuste SINIEF 05/2005) (Decreto 28.868/2006)',
        'Entrada de mercadoria, sujeita ao regime de substituição tributária, recebida em transferência de outro estabelecimento da mesma empresa, para ser industrializada ou consumida na produção rural no estabelecimento destinatário.\r\n\r\n(NR Ajuste SINIEF 05/2005) (Dec. 28.868/2006 - Efeitos a partir de 01/01/2006, ficando facultada ao contribuinte a sua adoção para fatos geradores ocorridos no período de 01 de novembro a 31 de dezembro de 2005)'),
       ('2409',
        'Transferência para comercialização em operação com mercadoria sujeita ao regime de substituição tributária',
        'Classificam-se neste código as mercadorias recebidas em transferência de outro estabelecimento da mesma empresa, para serem comercializadas, decorrentes de operações sujeitas ao regime de substituição tributária.'),
       ('2410',
        'Devolução de venda de produção do estabelecimento, quando o produto estiver sujeito ao regime de substituição tributária',
        'Devolução de produto industrializado ou produzido pelo estabelecimento, cuja saída tenha sido classificada como \"Venda de produção do estabelecimento quando o produto estiver sujeito ao regime de substituição tributária\".\r\n\r\n(NR Ajuste SINIEF 05/2005) (Dec. 28.868/2006 - Efeitos a partir de 01/01/2006, ficando facultada ao contribuinte a sua adoção para fatos geradores ocorridos no período de 01 de novembro a 31 de dezembro de 2005)'),
       ('2411',
        'Devolução de venda de mercadoria adquirida ou recebida de terceiros em operação com mercadoria sujeita ao regime de substituição tributária',
        'Devolução de vendas de mercadoria adquirida ou recebida de terceiro, cuja saída tenha sido classificada como “Venda de mercadoria adquirida ou recebida de terceiros em operação com mercadoria sujeita ao regime de substituição tributária”.'),
       ('2414',
        'Retorno de produção do estabelecimento, remetida para venda fora do estabelecimento, quando o produto estiver sujeito ao regime de substituição tributária',
        'Entrada, em retorno, de produto industrializado ou produzido pelo estabelecimento sujeito ao regime de substituição tributária, remetido para venda fora do estabelecimento, inclusive por meio de veículo, e não comercializado.\r\n\r\n(NR Ajuste SINIEF 05/2005) (Dec. 28.868/2006 - Efeitos a partir de 01/01/2006, ficando facultada ao contribuinte a sua adoção para fatos geradores ocorridos no período de 01 de novembro a 31 de dezembro de 2005)'),
       ('2415',
        'Retorno de mercadoria adquirida ou recebida de terceiros, remetida para venda fora do estabelecimento em operação com mercadoria sujeita ao regime de substituição tributária',
        'Entrada, em retorno, de mercadoria sujeita ao regime de substituição tributária, adquirida ou recebida de terceiro remetida para venda fora do estabelecimento, inclusive por meio de veículo, e não comercializada.'),
       ('2500',
        'ENTRADAS DE MERCADORIAS REMETIDAS PARA FORMAÇÃO DE LOTE OU COM FIM ESPECÍFICO DE EXPORTAÇÃO E EVENTUAIS DEVOLUÇÕES (NR Ajuste SINIEF 09/2005)',
        '(ACR Ajuste SINIEF 09/2005) (Dec. 28.868/2006 - a sua aplicação será obrigatória em relação aos fatos geradores ocorridos a partir de 01 de julho de 2006, ficando facultada ao contribuinte a sua adoção para fatos geradores ocorridos no período de 01 de janeiro a 30 de junho de 2006)'),
       ('2501', 'Entrada de mercadoria recebida com fim específico de exportação',
        'Classificam-se neste código as entradas de mercadorias em estabelecimento de trading company, empresa comercial exportadora ou outro estabelecimento do remetente, com fim específico de exportação.'),
       ('2503',
        'Entrada decorrente de devolução de produto industrializado pelo estabelecimento, remetido com fim específico de exportação',
        'Devolução de produto industrializado ou produzido pelo estabelecimento, remetido a \"trading company\", a empresa comercial exportadora ou a outro estabelecimento do remetente, com fim específico de exportação, cuja saída tenha sido classificada no código \"6.501 – Remessa de produção do estabelecimento com fim específico de exportação\".\r\n\r\n(NR Ajuste SINIEF 05/2005) (Dec.28.868/2006 - Efeitos a partir de 01/01/2006, ficando facultada ao contribuinte a sua adoção para fatos geradores ocorridos no período de 01 de novembro a 31 '),
       ('2504',
        'Entrada decorrente de devolução de mercadoria remetida com fim específico de exportação, adquirida ou recebida de terceiros',
        'Classificam-se neste código as devoluções de mercadorias adquiridas ou recebidas de terceiros remetidas a trading company, a empresa comercial exportadora ou a outro estabelecimento do remetente, com fim específico de exportação, cujas saídas tenham sido classificadas no código 6.502 - Remessa de mercadoria adquirida ou recebida de terceiros, com fim específico de exportação.'),
       ('2505',
        'Entrada decorrente de devolução simbólica de mercadoria remetida para formação de lote de exportação, de produto industrializado ou produzido pelo próprio estabelecimento.',
        'Devolução simbólica de mercadoria remetida para formação de lote de exportação, cuja saída tenha sido classificada no código \"6.504 – Remessa de mercadoria para formação de lote de exportação, de produto industrializado ou produzido pelo próprio estabelecimento\". (ACR Ajuste SINIEF 09/2005) (Decreto 28.868/2006)\r\n\r\n(ACR Ajuste SINIEF 09/2005) (Dec. 28.868/2006 - a sua aplicação será obrigatória em relação aos fatos geradores ocorridos a partir de 01 de julho de 2006, ficando facultada ao contribuinte a sua adoção para fatos '),
       ('2506',
        'Entrada decorrente de devolução simbólica de mercadoria, adquirida ou recebida de terceiros, remetida para formação de lote de exportação.',
        'Devolução simbólica de mercadoria remetida para formação de lote de exportação em armazéns alfandegados, entrepostos aduaneiros ou outros estabelecimentos que venham a ser regulamentados pela legislação tributária de cada Unidade Federada, efetuada pelo estabelecimento depositário, cuja saída tenha sido classificada no código \"6.505 – Remessa de mercadoria, adquirida ou recebida de terceiros, para formação de lote de exportação\".\r\n\r\n(ACR Ajuste SINIEF 09/2005) (Dec. 28.868/2006 - a sua aplicação será obrigatória em relação a'),
       ('2550', 'OPERAÇÕES COM BENS DE ATIVO IMOBILIZADO E MATERIAIS PARA USO OU CONSUMO', NULL),
       ('2551', 'Compra de bem para o ativo imobilizado',
        'Classificam-se neste código as compras de bens destinados ao ativo imobilizado do estabelecimento.'),
       ('2552', 'Transferência de bem do ativo imobilizado',
        'Classificam-se neste código as entradas de bens destinados ao ativo imobilizado recebidos em transferência de outro estabelecimento da mesma empresa.'),
       ('2553', 'Devolução de venda de bem do ativo imobilizado',
        'Classificam-se neste código as devoluções de vendas de bens do ativo imobilizado, cujas saídas tenham sido classificadas no código 6.551 - Venda de bem do ativo imobilizado.'),
       ('2554', 'Retorno de bem do ativo imobilizado remetido para uso fora do estabelecimento',
        'Classificam-se neste código as entradas por retorno de bens do ativo imobilizado remetidos para uso fora do estabelecimento, cujas saídas tenham sido classificadas no código 6.554 - Remessa de bem do ativo imobilizado para uso fora do estabelecimento.'),
       ('2555', 'Entrada de bem do ativo imobilizado de terceiro, remetido para uso no estabelecimento',
        'Classificam-se neste código as entradas de bens do ativo imobilizado de terceiros, remetidos para uso no estabelecimento.'),
       ('2556', 'Compra de material para uso ou consumo',
        'Classificam-se neste código as compras de mercadorias destinadas ao uso ou consumo do estabelecimento.'),
       ('2557', 'Transferência de material para uso ou consumo',
        'Classificam-se neste código as entradas de materiais para uso ou consumo recebidos em transferência de outro estabelecimento da mesma empresa.'),
       ('2600', 'CRÉDITOS E RESSARCIMENTOS DE ICMS', NULL),
       ('2603', 'Ressarcimento de ICMS retido por substituição tributária',
        'Classificam-se neste código os lançamentos destinados ao registro de ressarcimento de ICMS retido por substituição tributária a contribuinte substituído, efetuado pelo contribuinte substituto, nas hipóteses previstas na legislação aplicável.'),
       ('2650', 'ENTRADAS DE COMBUSTÍVEIS, DERIVADOS OU NÃO DE PETRÓLEO, E LUBRIFICANTES (ACR Ajuste SINIEF 9/2003)',
        '(ACR Ajuste SINIEF 05/2005) (Dec.28.868/2006 )'),
       ('2651', 'Compra de combustível ou lubrificante para industrialização subseqüente',
        'Compra de combustível ou lubrificante a ser utilizados em processo de industrialização do próprio produto. (a partir 01.01.2004 -  Decreto Nº 26.174 de 26/11/2003)'),
       ('2652', 'Compra de combustível ou lubrificante para comercialização',
        'Compra de combustível ou lubrificante a ser comercializados. (a partir 01.01.2004 -  Decreto Nº 26.174 de 26/11/2003)'),
       ('2653', 'Compra de combustível ou lubrificante por consumidor ou usuário final',
        'Compra de combustível ou lubrificante, a ser consumidos em processo de industrialização de outros produtos, na produção rural, na prestação de serviço ou por usuário final.\r\n\r\n(NR Ajuste SINIEF 05/2005) (Dec.28.868/2006 - Efeitos a partir de 01/01/2006, ficando facultada ao contribuinte a sua adoção para fatos geradores ocorridos no período de 01 de novembro a 31 de dezembro de 2005)'),
       ('2658', 'Transferência de combustível ou lubrificante para industrialização',
        'Entrada de combustível ou lubrificante, recebidos em transferência de outro estabelecimento da mesma empresa, para ser utilizados em processo de industrialização do próprio produto. (a partir 01.01.2004 -  Decreto Nº 26.174 de 26/11/2003)'),
       ('2659', 'Transferência de combustível ou lubrificante para comercialização',
        'Entrada de combustível ou lubrificante, recebidos em transferência de outro estabelecimento da mesma empresa, para ser comercializados. (a partir 01.01.2004 -  Decreto Nº 26.174 de 26/11/2003)'),
       ('2660', 'Devolução de venda de combustível ou lubrificante destinados à industrialização subseqüente',
        'Devolução de venda de combustível ou lubrificante, cuja saída tenha sido classificada como \"Venda de combustível ou lubrificante destinados à industrialização subseqüente\".(a partir 01.01.2004 -  Decreto Nº 26.174 de 26/11/2003)'),
       ('2661', 'Devolução de venda de combustível ou lubrificante destinados à comercialização',
        'Devolução de venda de combustível ou lubrificante, cuja saída tenha sido classificada como \"Venda de combustível ou lubrificante para comercialização\".(a partir 01.01.2004 -  Decreto Nº 26.174 de 26/11/2003)'),
       ('2662', 'Devolução de venda de combustível ou lubrificante destinados a consumidor ou usuário final',
        'Devolução de venda de combustível ou lubrificante, cuja saída tenha sido classificada como \"Venda de combustível ou lubrificante por consumidor ou usuário final\".(a partir 01.01.2004 -  Decreto Nº 26.174 de 26/11/2003)'),
       ('2663', 'Entrada de combustível ou lubrificante para armazenagem',
        'Entrada de combustível ou lubrificante para armazenagem. (a partir 01.01.2004 -  Decreto Nº 26.174 de 26/11/2003)'),
       ('2664', 'Retorno de combustível ou lubrificante remetidos para armazenagem',
        'Entrada, ainda que simbólica, por retorno de combustível ou lubrificante, remetidos para armazenagem. (a partir 01.01.2004 -  Decreto Nº 26.174 de 26/11/2003)'),
       ('2900', 'OUTRAS ENTRADAS DE MERCADORIAS OU AQUISIÇÕES DE SERVIÇOS', NULL),
       ('2901', 'Entrada para industrialização por encomenda',
        'Classificam-se neste código as entradas de insumos recebidos para industrialização por encomenda de outra empresa ou de outro estabelecimento da mesma empresa.'),
       ('2902', 'Retorno de mercadoria remetida para industrialização por encomenda',
        'Classificam-se neste código o retorno dos insumos remetidos para industrialização por encomenda, incorporados ao produto final pelo estabelecimento industrializador.'),
       ('2903', 'Entrada de mercadoria remetida para industrialização e não aplicada no referido processo',
        'Classificam-se neste código as entradas em devolução de insumos remetidos para industrialização e não aplicados no referido processo.'),
       ('2904', 'Retorno de remessa para venda fora do estabelecimento',
        'Classificam-se neste código as entradas em retorno de mercadorias remetidas para venda fora do estabelecimento, inclusive por meio de veículos, e não comercializadas.'),
       ('2905', 'Entrada de mercadoria recebida para depósito em depósito fechado ou armazém geral',
        'Classificam-se neste código as entradas de mercadorias recebidas para depósito em depósito fechado ou armazém geral.'),
       ('2906', 'Retorno de mercadoria remetida para depósito fechado ou armazém geral',
        'Classificam-se neste código as entradas em retorno de mercadorias remetidas para depósito em depósito fechado ou armazém geral.'),
       ('2907', 'Retorno simbólico de mercadoria remetida para depósito fechado ou armazém geral',
        'Classificam-se neste código as entradas em retorno simbólico de mercadorias remetidas para depósito em depósito fechado ou armazém geral, quando as mercadorias depositadas tenham sido objeto de saída a qualquer título e que não tenham retornado ao estabelecimento depositante.'),
       ('2908', 'Entrada de bem por conta de contrato de comodato',
        'Classificam-se neste código as entradas de bens recebidos em cumprimento de contrato de comodato.'),
       ('2909', 'Retorno de bem remetido por conta de contrato de comodato',
        'Classificam-se neste código as entradas de bens recebidos em devolução após cumprido o contrato de comodato.'),
       ('2910', 'Entrada de bonificação, doação ou brinde',
        'Classificam-se neste código as entradas de mercadorias recebidas a título de bonificação, doação ou brinde.'),
       ('2911', 'Entrada de amostra grátis',
        'Classificam-se neste código as entradas de mercadorias recebidas a título de amostra grátis.'),
       ('2912', 'Entrada de mercadoria ou bem recebido para demonstração',
        'Classificam-se neste código as entradas de mercadorias ou bens recebidos para demonstração.'),
       ('2913', 'Retorno de mercadoria ou bem remetido para demonstração',
        'Classificam-se neste código as entradas em retorno de mercadorias ou bens remetidos para demonstração.'),
       ('2914', 'Retorno de mercadoria ou bem remetido para exposição ou feira',
        'Classificam-se neste código as entradas em retorno de mercadorias ou bens remetidos para exposição ou feira.'),
       ('2915', 'Entrada de mercadoria ou bem recebido para conserto ou reparo',
        'Classificam-se neste código as entradas de mercadorias ou bens recebidos para conserto ou reparo.'),
       ('2916', 'Retorno de mercadoria ou bem remetido para conserto ou reparo',
        'Classificam-se neste código as entradas em retorno de mercadorias ou bens remetidos para conserto ou reparo.'),
       ('2917', 'Entrada de mercadoria recebida em consignação mercantil ou industrial',
        'Classificam-se neste código as entradas de mercadorias recebidas a título de consignação mercantil ou industrial.'),
       ('2918', 'Devolução de mercadoria remetida em consignação mercantil ou industrial',
        'Classificam-se neste código as entradas por devolução de mercadorias remetidas anteriormente a título de consignação mercantil ou industrial.'),
       ('2919',
        'Devolução simbólica de mercadoria vendida ou utilizada em processo industrial, remetida anteriormente em consignação mercantil ou industrial',
        'Classificam-se neste código as entradas por devolução simbólica de mercadorias vendidas ou utilizadas em processo industrial, remetidas anteriormente a título de consignação mercantil ou industrial.'),
       ('2920', 'Entrada de vasilhame ou sacaria', 'Classificam-se neste código as entradas de vasilhame ou sacaria.'),
       ('2921', 'Retorno de vasilhame ou sacaria',
        'Classificam-se neste código as entradas em retorno de vasilhame ou sacaria.'),
       ('2922', 'Lançamento efetuado a título de simples faturamento decorrente de compra para recebimento futuro',
        'Classificam-se neste código os registros efetuados a título de simples faturamento decorrente de compra para recebimento futuro.'),
       ('2923', 'Entrada de mercadoria recebida do vendedor remetente, em venda à ordem',
        'Classificam-se neste código as entradas de mercadorias recebidas do vendedor remetente, em vendas à ordem, cuja compra do adquirente originário, foi classificada nos códigos 2.120 - Compra para industrialização, em venda à ordem, já recebida do vendedor remetente ou 2.121 - Compra para comercialização, em venda à ordem, já recebida do vendedor remetente.'),
       ('2924',
        'Entrada para industrialização por conta e ordem do adquirente da mercadoria, quando esta não transitar pelo estabelecimento do adquirente',
        'Classificam-se neste código as entradas de insumos recebidos para serem industrializados por conta e ordem do adquirente, nas hipóteses em que os insumos não tenham transitado pelo estabelecimento do adquirente dos mesmos.'),
       ('2925',
        'Retorno de mercadoria remetida para industrialização por conta e ordem do adquirente da mercadoria, quando esta não transitar pelo estabelecimento do adquirente',
        'Classificam-se neste código o retorno dos insumos remetidos por conta e ordem do adquirente, para industrialização e incorporados ao produto final pelo estabelecimento industrializador, nas hipóteses em que os insumos não tenham transitado pelo estabelecimento do adquirente.'),
       ('2931',
        'Lançamento efetuado pelo tomador do serviço de transporte, quando a responsabilidade de retenção do imposto for atribuída ao remetente ou alienante da mercadoria, pelo serviço de transporte realizado ',
        'Lançamento efetuado pelo tomador do serviço de transporte realizado por transportador autônomo ou por transportador não-inscrito na Unidade da Federação onde se tenha iniciado o serviço, quando a responsabilidade pela retenção do imposto for atribuída ao remetente ou alienante da mercadoria.\r\n\r\n (ACR Ajuste SINIEF 03/2004) (DECRETO Nº 26.810/2004) (a partir de 01.01.2005)'),
       ('2932',
        'Aquisição de serviço de transporte iniciado em Unidade da Federação diversa daquela onde esteja inscrito o prestador',
        'Aquisição de serviço de transporte que tenha sido iniciado em Unidade da Federação diversa daquela onde o prestador esteja inscrito como contribuinte. (ACR Ajuste SINIEF 03/2004) (DECRETO Nº 26.810/2004) (a partir de 01.01.2005)'),
       ('2933', 'Aquisição de serviço tributado pelo Imposto Sobre Serviços de Qualquer Natureza',
        'Aquisição de serviço, cujo imposto é de competência municipal, desde que informado em Nota Fiscal modelo 1 e 1-A. (NR Ajuste SINIEF 06/2005) (a partir de 01.01.2006)'),
       ('2949', 'Outra entrada de mercadoria ou prestação de serviço não especificado',
        'Classificam-se neste código as outras entradas de mercadorias ou prestações de serviços que não tenham sido especificados nos códigos anteriores.'),
       ('3000', 'ENTRADAS OU AQUISIÇÕES DE SERVIÇOS DO EXTERIOR',
        'Classificam-se, neste grupo, as entradas de mercadorias oriundas de outro país, inclusive as decorrentes de aquisição por arrematação, concorrência ou qualquer outra forma de alienação promovida pelo poder público, e os serviços iniciados no exterior'),
       ('3100',
        'COMPRAS PARA INDUSTRIALIZAÇÃO, PRODUÇÃO RURAL, COMERCIALIZAÇÃO OU PRESTAÇÃO DE SERVIÇOS (NR Ajuste SINIEF 05/2005) (Decreto 28.868/2006)',
        '(NR Ajuste SINIEF 05/2005) (Dec.28.868/2006 - Efeitos a partir de 01/01/2006, ficando facultada ao contribuinte a sua adoção para fatos geradores ocorridos no período de 01 de novembro a 31 de dezembro de 2005)'),
       ('3101', 'Compra para industrialização ou produção rural (NR Ajuste SINIEF 05/2005) (Decreto 28.868/2006)',
        'Compra de mercadoria a ser utilizada em processo de industrialização ou produção rural, bem como a entrada de mercadoria em estabelecimento industrial ou produtor rural de cooperativa.\r\n(NR Ajuste SINIEF 05/2005) (Dec.28.868/2006 - Efeitos a partir de 01/01/2006, ficando facultada ao contribuinte a sua adoção para fatos geradores ocorridos no período de 01 de novembro a 31 de dezembro de 2005)'),
       ('3102', 'Compra para comercialização',
        'Classificam-se neste código as compras de mercadorias a serem comercializadas. Também serão classificadas neste código as entradas de mercadorias em estabelecimento comercial de cooperativa.'),
       ('3126', 'Compra para utilização na prestação de serviço',
        'Classificam-se neste código as entradas de mercadorias a serem utilizadas nas prestações de serviços.'),
       ('3127', 'Compra para industrialização sob o regime de drawback',
        'Classificam-se neste código as compras de mercadorias a serem utilizadas em processo de industrialização e posterior exportação do produto resultante, cujas vendas serão classificadas no código 7.127 - Venda de produção do estabelecimento sob o regime de drawback.'),
       ('3200', 'DEVOLUÇÕES DE VENDAS DE PRODUÇÃO PRÓPRIA, DE TERCEIROS OU ANULAÇÕES DE VALORES', NULL),
       ('3201', 'Devolução de venda de produção do estabelecimento',
        'Devolução de venda de produto industrializado ou produzido pelo próprio estabelecimento, cuja saída tenha sido classificada como \"Venda de produção do estabelecimento\".\r\n\r\n(NR Ajuste SINIEF 05/2005) (Dec.28.868/2006 - Efeitos a partir de 01/01/2006, ficando facultada ao contribuinte a sua adoção para fatos geradores ocorridos no período de 01 de novembro a 31 de dezembro de 2005)'),
       ('3202', 'Devolução de venda de mercadoria adquirida ou recebida de terceiros',
        'Classificam-se neste código as devoluções de vendas de mercadorias adquiridas ou recebidas de terceiros, que não tenham sido objeto de industrialização no estabelecimento, cujas saídas tenham sido classificadas como Venda de mercadoria adquirida ou recebida de terceiros.'),
       ('3205', 'Anulação de valor relativo à prestação de serviço de comunicação',
        'Classificam-se neste código as anulações correspondentes a valores faturados indevidamente, decorrentes de prestações de serviços de comunicação.'),
       ('3206', 'Anulação de valor relativo à prestação de serviço de transporte',
        'Classificam-se neste código as anulações correspondentes a valores faturados indevidamente, decorrentes de prestações de serviços de transporte.'),
       ('3207', 'Anulação de valor relativo à venda de energia elétrica',
        'Classificam-se neste código as anulações correspondentes a valores faturados indevidamente, decorrentes de venda de energia elétrica.'),
       ('3211', 'Devolução de venda de produção do estabelecimento sob o regime de drawback',
        'Classificam-se neste código as devoluções de vendas de produtos industrializados pelo estabelecimento sob o regime de drawback.'),
       ('3250', 'COMPRAS DE ENERGIA ELÉTRICA', NULL),
       ('3251', 'Compra de energia elétrica para distribuição ou comercialização',
        'Classificam-se neste código as compras de energia elétrica utilizada em sistema de distribuição ou comercialização. Também serão classificadas neste código as compras de energia elétrica por cooperativas para distribuição aos seus cooperados.'),
       ('3301', 'Aquisição de serviço de comunicação para execução de serviço da mesma natureza',
        'Classificam-se neste código as aquisições de serviços de comunicação utilizados nas prestações de serviços da mesma natureza.'),
       ('3350', 'AQUISIÇÕES DE SERVIÇOS DE TRANSPORTE', NULL),
       ('3351', 'Aquisição de serviço de transporte para execução de serviço da mesma natureza',
        'Classificam-se neste código as aquisições de serviços de transporte utilizados nas prestações de serviços da mesma natureza.'),
       ('3352', 'Aquisição de serviço de transporte por estabelecimento industrial',
        'Classificam-se neste código as aquisições de serviços de transporte utilizados por estabelecimento industrial. Também serão classificadas neste código as aquisições de serviços de transporte utilizados por estabelecimento industrial de cooperativa.'),
       ('3353', 'Aquisição de serviço de transporte por estabelecimento comercial',
        'Classificam-se neste código as aquisições de serviços de transporte utilizados por estabelecimento comercial. Também serão classificadas neste código as aquisições de serviços de transporte utilizados por estabelecimento comercial de cooperativa.'),
       ('3354', 'Aquisição de serviço de transporte por estabelecimento de prestador de serviço de comunicação',
        'Classificam-se neste código as aquisições de serviços de transporte utilizados por estabelecimento prestador de serviços de comunicação.'),
       ('3355',
        'Aquisição de serviço de transporte por estabelecimento de geradora ou de distribuidora de energia elétrica',
        'Classificam-se neste código as aquisições de serviços de transporte utilizados por estabelecimento de geradora ou de distribuidora de energia elétrica.'),
       ('3356', 'Aquisição de serviço de transporte por estabelecimento de produtor rural',
        'Classificam-se neste código as aquisições de serviços de transporte utilizados por estabelecimento de produtor rural.'),
       ('3500', 'ENTRADAS DE MERCADORIAS REMETIDAS COM FIM ESPECÍFICO DE EXPORTAÇÃO E EVENTUAIS DEVOLUÇÕES', NULL),
       ('3503', 'Devolução de mercadoria exportada que tenha sido recebida com fim específico de exportação',
        'Classificam-se neste código as devoluções de mercadorias exportadas por trading company, empresa comercial exportadora ou outro estabelecimento do remetente, recebidas com fim específico de exportação, cujas saídas tenham sido classificadas no código 7.501 - Exportação de mercadorias recebidas com fim específico de exportação.'),
       ('3550', 'OPERAÇÕES COM BENS DE ATIVO IMOBILIZADO E MATERIAIS PARA USO OU CONSUMO', NULL),
       ('3551', 'Compra de bem para o ativo imobilizado',
        'Classificam-se neste código as compras de bens destinados ao ativo imobilizado do estabelecimento.'),
       ('3553', 'Devolução de venda de bem do ativo imobilizado',
        'Classificam-se neste código as devoluções de vendas de bens do ativo imobilizado, cujas saídas tenham sido classificadas no código 7.551 - Venda de bem do ativo imobilizado.'),
       ('3556', 'Compra de material para uso ou consumo',
        'Classificam-se neste código as compras de mercadorias destinadas ao uso ou consumo do estabelecimento.'),
       ('3650', 'ENTRADAS DE COMBUSTÍVEIS, DERIVADOS OU NÃO DE PETRÓLEO, E LUBRIFICANTES',
        '(ACR Ajuste SINIEF 9/2003 - a partir 01.01.2004) (Decreto Nº 26.174 de 26/11/2003)'),
       ('3651', 'Compra de combustível ou lubrificante para industrialização subseqüente',
        'Compra de combustível ou lubrificante a ser utilizados em processo de industrialização do próprio produto. (a partir 01.01.2004 -  Decreto Nº 26.174 de 26/11/2003)'),
       ('3652', 'Compra de combustível ou lubrificante para comercialização',
        'Compra de combustível ou lubrificante a ser comercializados. (a partir 01.01.2004 -  Decreto Nº 26.174 de 26/11/2003)'),
       ('3653', 'Compra de combustível ou lubrificante por consumidor ou usuário final',
        'Compra de combustível ou lubrificante, a ser consumidos em processo de industrialização de outros produtos, na produção rural, na prestação de serviço ou por usuário final.\r\n\r\n(NR Ajuste SINIEF 05/2005) (Dec.28.868/2006 - Efeitos a partir de 01/01/2006, ficando facultada ao contribuinte a sua adoção para fatos geradores ocorridos no período de 01 de novembro a 31 de dezembro de 2005)'),
       ('3900', 'OUTRAS ENTRADAS DE MERCADORIAS OU AQUISIÇÕES DE SERVIÇOS', NULL),
       ('3930',
        'Lançamento efetuado a título de entrada de bem sob amparo de regime especial aduaneiro de admissão temporária',
        'Lançamento efetuado a título de entrada de bem amparada por regime especial aduaneiro de admissão temporária. – (Decreto Nº 26.174 de 26/11/2003). a partir 01.01.2004   '),
       ('3949', 'Outra entrada de mercadoria ou prestação de serviço não especificado',
        'Outra entrada de mercadoria ou prestação de serviço que não tenham sido especificada nos códigos anteriores. – (Decreto Nº 26.174 de 26/11/2003). a partir 01.01.2004  '),
       ('5000', 'SAÍDAS OU PRESTAÇÕES DE SERVIÇOS PARA O ESTADO',
        'Classificam-se, neste grupo, as operações ou prestações em que o estabelecimento remetente esteja localizado na mesma unidade da Federação do destinatário.'),
       ('5100', 'VENDAS DE PRODUÇÃO PRÓPRIA OU DE TERCEIROS', NULL),
       ('5101', 'Venda de produção do estabelecimento',
        'Venda de produto industrializado ou produzido pelo estabelecimento, bem como a de mercadoria por estabelecimento industrial ou produtor rural de cooperativa destinada a seus cooperados ou a estabelecimento de outra cooperativa.\n\n(NR Ajuste SINIEF 05/2005) (Dec.28.868/2006 - Efeitos a partir de 01/01/2006, ficando facultada ao contribuinte a sua adoção para fatos geradores ocorridos no período de 01 de novembro a 31 de dezembro de 2005)'),
       ('5102', 'Venda de mercadoria adquirida ou recebida de terceiros',
        'Classificam-se neste código as vendas de mercadorias adquiridas ou recebidas de terceiros para industrialização ou comercialização, que não tenham sido objeto de qualquer processo industrial no estabelecimento. Também serão classificadas neste código as vendas de mercadorias por estabelecimento comercial de cooperativa destinadas a seus cooperados ou estabelecimento de outra cooperativa.'),
       ('5103', 'Venda de produção do estabelecimento efetuada fora do estabelecimento',
        'Venda efetuada fora do estabelecimento, inclusive por meio de veículo, de produto industrializado ou produzido no estabelecimento.\r\n\r\n(NR Ajuste SINIEF 05/2005) (Dec.28.868/2006 - Efeitos a partir de 01/01/2006, ficando facultada ao contribuinte a sua adoção para fatos geradores ocorridos no período de 01 de novembro a 31 de dezembro de 2005)'),
       ('5104', 'Venda de mercadoria adquirida ou recebida de terceiros, efetuada fora do estabelecimento',
        'Classificam-se neste código as vendas efetuadas fora do estabelecimento, inclusive por meio de veículo, de mercadorias adquiridas ou recebidas de terceiros para industrialização ou comercialização, que não tenham sido objeto de qualquer processo industrial no estabelecimento.'),
       ('5105', 'Venda de produção do estabelecimento que não deva por ele transitar',
        'Classificam-se neste código as vendas de produtos industrializados no estabelecimento, armazenados em depósito fechado, armazém geral ou outro sem que haja retorno ao estabelecimento depositante.'),
       ('5106', 'Venda de mercadoria adquirida ou recebida de terceiros, que não deva por ele transitar',
        'Classificam-se neste código as vendas de mercadorias adquiridas ou recebidas de terceiros para industrialização ou comercialização, armazenadas em depósito fechado, armazém geral ou outro, que não tenham sido objeto de qualquer processo industrial no estabelecimento sem que haja retorno ao estabelecimento depositante. Também serão classificadas neste código as vendas de mercadorias importadas, cuja saída ocorra do recinto alfandegado ou da repartição alfandegária onde se processou o desembaraço aduaneiro, com destino ao esta'),
       ('5109', 'Venda de produção do estabelecimento destinada à Zona Franca de Manaus ou Áreas de Livre Comércio',
        'Venda de produto industrializado ou produzido pelo estabelecimento destinado à Zona Franca de Manaus ou Áreas de Livre Comércio.\r\n\r\n(NR Ajuste SINIEF 05/2005) (Dec.28.868/2006 - Efeitos a partir de 01/01/2006, ficando facultada ao contribuinte a sua adoção para fatos geradores ocorridos no período de 01 de novembro a 31 de dezembro de 2005)'),
       ('5110',
        'Venda de mercadoria, adquirida ou recebida de terceiros, destinada à Zona Franca de Manaus ou Áreas de Livre Comercio, de que trata o Anexo do Convênio SINIEF s/n, de 15 de dezembro de 1970, que dispõ',
        'Venda de mercadoria, adquirida ou recebida de terceiros, destinada à Zona Franca de Manaus ou Áreas de Livre Comércio, desde que alcançada pelos benefícios fiscais de que tratam o Decreto-Lei nº 288, de 28 de fevereiro de 1967, o Convênio ICM 65/88, de 06 de dezembro de 1988, o Convênio ICMS 36/97, de 23 de maio de 1997, e o Convênio ICMS 37/97, de 23 de maio de 1997. (NR Ajuste SINIEF 09/2004) (Decreto nº 26.955/2004) RETROAGINDO SEUS EFEITOS A 24.06.2004.'),
       ('5111', 'Venda de produção do estabelecimento remetida anteriormente em consignação industrial',
        'Classificam-se neste código as vendas efetivas de produtos industrializados no estabelecimento remetidos anteriormente a título de consignação industrial.'),
       ('5112',
        'Venda de mercadoria adquirida ou recebida de terceiros remetida anteriormente em consignação industrial',
        'Classificam-se neste código as vendas efetivas de mercadorias adquiridas ou recebidas de terceiros, que não tenham sido objeto de qualquer processo industrial no estabelecimento, remetidas anteriormente a título de consignação industrial.'),
       ('5113', 'Venda de produção do estabelecimento remetida anteriormente em consignação mercantil',
        'Classificam-se neste código as vendas efetivas de produtos industrializados no estabelecimento remetidos anteriormente a título de consignação mercantil.'),
       ('5114',
        'Venda de mercadoria adquirida ou recebida de terceiros remetida anteriormente em consignação mercantil',
        'Classificam-se neste código as vendas efetivas de mercadorias adquiridas ou recebidas de terceiros, que não tenham sido objeto de qualquer processo industrial no estabelecimento, remetidas anteriormente a título de consignação mercantil.'),
       ('5115',
        'Venda de mercadoria adquirida ou recebida de terceiros, recebida anteriormente em consignação mercantil',
        'Classificam-se neste código as vendas de mercadorias adquiridas ou recebidas de terceiros, recebidas anteriormente a título de consignação mercantil.'),
       ('5116', 'Venda de produção do estabelecimento originada de encomenda para entrega futura',
        'Venda de produto industrializado ou produzido pelo estabelecimento, quando da saída real do produto, cujo faturamento tenha sido classificado no código \"5.922 – Lançamento efetuado a título de simples faturamento decorrente de venda para entrega futura\".\r\n\r\n(NR Ajuste SINIEF 05/2005) (Dec.28.868/2006 - Efeitos a partir de 01/01/2006, ficando facultada ao contribuinte a sua adoção para fatos geradores ocorridos no período de 01 de novembro a 31 de dezembro de 2005)'),
       ('5118',
        'Venda de produção do estabelecimento entregue ao destinatário por conta e ordem do adquirente originário, em venda à ordem',
        'Classificam-se neste código as vendas à ordem de produtos industrializados pelo estabelecimento, entregues ao destinatário por conta e ordem do adquirente originário.'),
       ('5119',
        'Venda de mercadoria adquirida ou recebida de terceiros entregue ao destinatário por conta e ordem do adquirente originário, em venda à ordem',
        'Classificam-se neste código as vendas à ordem de mercadorias adquiridas ou recebidas de terceiros, que não tenham sido objeto de qualquer processo industrial no estabelecimento, entregues ao destinatário por conta e ordem do adquirente originário.'),
       ('5120',
        'Venda de mercadoria adquirida ou recebida de terceiros entregue ao destinatário pelo vendedor remetente, em venda à ordem',
        'Classificam-se neste código as vendas à ordem de mercadorias adquiridas ou recebidas de terceiros, que não tenham sido objeto de qualquer processo industrial no estabelecimento, entregues pelo vendedor remetente ao destinatário, cuja compra seja classificada, pelo adquirente originário, no código 1.118 - Compra de mercadoria pelo adquirente originário, entregue pelo vendedor remetente ao destinatário, em venda à ordem.'),
       ('5122',
        'Venda de produção do estabelecimento remetida para industrialização, por conta e ordem do adquirente, sem transitar pelo estabelecimento do adquirente',
        'Classificam-se neste código as vendas de produtos industrializados no estabelecimento, remetidos para serem industrializados em outro estabelecimento, por conta e ordem do adquirente, sem que os produtos tenham transitado pelo estabelecimento do adquirente.'),
       ('5123',
        'Venda de mercadoria adquirida ou recebida de terceiros remetida para industrialização, por conta e ordem do adquirente, sem transitar pelo estabelecimento do adquirente',
        'Classificam-se neste código as vendas de mercadorias adquiridas ou recebidas de terceiros, que não tenham sido objeto de qualquer processo industrial no estabelecimento, remetidas para serem industrializadas em outro estabelecimento, por conta e ordem do adquirente, sem que as mercadorias tenham transitado pelo estabelecimento do adquirente.'),
       ('5124', 'Industrialização efetuada para outra empresa',
        'Classificam-se neste código as saídas de mercadorias industrializadas para terceiros, compreendendo os valores referentes aos serviços prestados e os das mercadorias de propriedade do industrializador empregadas no processo industrial.'),
       ('5125',
        'Industrialização efetuada para outra empresa quando a mercadoria recebida para utilização no processo de industrialização não transitar pelo estabelecimento adquirente da mercadoria',
        'Classificam-se neste código as saídas de mercadorias industrializadas para outras empresas, em que as mercadorias recebidas para utilização no processo de industrialização não tenham transitado pelo estabelecimento do adquirente das mercadorias, compreendendo os valores referentes aos serviços prestados e os das mercadorias de propriedade do industrializador empregadas no processo industrial.'),
       ('5150', 'TRANSFERÊNCIAS DE PRODUÇÃO PRÓPRIA OU DE TERCEIROS', NULL),
       ('5151', 'Transferência de produção do estabelecimento',
        'Transferência de produto industrializado ou produzido no estabelecimento para outro estabelecimento da mesma empresa.\r\n\r\n(NR Ajuste SINIEF 05/2005) (Dec.28.868/2006 - Efeitos a partir de 01/01/2006, ficando facultada ao contribuinte a sua adoção para fatos geradores ocorridos no período de 01 de novembro a 31 de dezembro de 2005)'),
       ('5152', 'Transferência de mercadoria adquirida ou recebida de terceiros',
        'Mercadoria adquirida ou recebida de terceiros para industrialização, comercialização ou utilização na prestação de serviço e que não tenha sido objeto de qualquer processo industrial no estabelecimento, transferida para outro estabelecimento da mesma empresa. A partir  10 de julho de 2003. (Decreto nº 26.020/2003)'),
       ('5153', 'Transferência de energia elétrica',
        'Classificam-se neste código as transferências de energia elétrica para outro estabelecimento da mesma empresa, para distribuição.'),
       ('5155', 'Transferência de produção do estabelecimento, que não deva por ele transitar',
        'Classificam-se neste código as transferências para outro estabelecimento da mesma empresa, de produtos industrializados no estabelecimento que tenham sido remetidos para armazém geral, depósito fechado ou outro, sem que haja retorno ao estabelecimento depositante.'),
       ('5156', 'Transferência de mercadoria adquirida ou recebida de terceiros, que não deva por ele transitar',
        'Classificam-se neste código as transferências para outro estabelecimento da mesma empresa, de mercadorias adquiridas ou recebidas de terceiros para industrialização ou comercialização, que não tenham sido objeto de qualquer processo industrial, remetidas para armazém geral, depósito fechado ou outro, sem que haja retorno ao estabelecimento depositante.'),
       ('5200',
        'DEVOLUÇÕES DE COMPRAS PARA INDUSTRIALIZAÇÃO, PRODUÇÃO RURAL, COMERCIALIZAÇÃO OU ANULAÇÕES DE VALORES (NR Ajuste SINIEF 05/2005) (Decreto 28.868/2006)',
        '(NR Ajuste SINIEF 05/2005) (Dec.28.868/2006 - Efeitos a partir de 01/01/2006, ficando facultada ao contribuinte a sua adoção para fatos geradores ocorridos no período de 01 de novembro a 31 de dezembro de 2005)'),
       ('5201',
        'Devolução de compra para industrialização ou produção rural (NR Ajuste SINIEF 05/2005) (Decreto 28.868/2006)',
        'Devolução de mercadoria adquirida para ser utilizada em processo de industrialização ou produção rural, cuja entrada tenha sido classificada como \"1.101 - Compra para industrialização ou produção rural\".\r\n\r\n(NR Ajuste SINIEF 05/2005) (Dec.28.868/2006 - Efeitos a partir de 01/01/2006, ficando facultada ao contribuinte a sua adoção para fatos geradores ocorridos no período de 01 de novembro a 31 de dezembro de 2005)'),
       ('5202', 'Devolução de compra para comercialização',
        'Classificam-se neste código as devoluções de mercadorias adquiridas para serem comercializadas, cujas entradas tenham sido classificadas como Compra para comercialização.'),
       ('5205', 'Anulação de valor relativo a aquisição de serviço de comunicação',
        'Classificam-se neste código as anulações correspondentes a valores faturados indevidamente, decorrentes das aquisições de serviços de comunicação.'),
       ('5206', 'Anulação de valor relativo a aquisição de serviço de transporte',
        'Classificam-se neste código as anulações correspondentes a valores faturados indevidamente, decorrentes das aquisições de serviços de transporte.'),
       ('5207', 'Anulação de valor relativo à compra de energia elétrica',
        'Classificam-se neste código as anulações correspondentes a valores faturados indevidamente, decorrentes da compra de energia elétrica.'),
       ('5208', 'Devolução de mercadoria recebida em transferência para industrialização ou produção rural',
        'Devolução de mercadoria recebida em transferência de outro estabelecimento da mesma empresa, para ser utilizada em processo de industrialização ou produção rural.\r\n(NR Ajuste SINIEF 05/2005) (Dec.28.868/2006 - Efeitos a partir de 01/01/2006, ficando facultada ao contribuinte a sua adoção para fatos geradores ocorridos no período de 01 de novembro a 31 de dezembro de 2005)'),
       ('5209', 'Devolução de mercadoria recebida em transferência para comercialização',
        'Classificam-se neste código as devoluções de mercadorias recebidas em transferência de outro estabelecimento da mesma empresa, para serem comercializadas.'),
       ('5210', 'Devolução de compra para utilização na prestação de serviço',
        'Classificam-se neste código as devoluções de mercadorias adquiridas para utilização na prestação de serviços, cujas entradas tenham sido classificadas no código 1.126 - Compra para utilização na prestação de serviço.'),
       ('5250', 'VENDAS DE ENERGIA ELÉTRICA', NULL),
       ('5251', 'Venda de energia elétrica para distribuição ou comercialização',
        'Classificam-se neste código as vendas de energia elétrica destinada à distribuição ou comercialização. Também serão classificadas neste código as vendas de energia elétrica destinada a cooperativas para distribuição aos seus cooperados.'),
       ('5252', 'Venda de energia elétrica para estabelecimento industrial',
        'Classificam-se neste código as vendas de energia elétrica para consumo por estabelecimento industrial. Também serão classificadas neste código as vendas de energia elétrica destinada a estabelecimento industrial de cooperativa.'),
       ('5253', 'Venda de energia elétrica para estabelecimento comercial',
        'Classificam-se neste código as vendas de energia elétrica para consumo por estabelecimento comercial. Também serão classificadas neste código as vendas de energia elétrica destinada a estabelecimento comercial de cooperativa.'),
       ('5254', 'Venda de energia elétrica para estabelecimento prestador de serviço de transporte',
        'Classificam-se neste código as vendas de energia elétrica para consumo por estabelecimento de prestador de serviços de transporte.'),
       ('5255', 'Venda de energia elétrica para estabelecimento prestador de serviço de comunicação',
        'Classificam-se neste código as vendas de energia elétrica para consumo por estabelecimento de prestador de serviços de comunicação.'),
       ('5256', 'Venda de energia elétrica para estabelecimento de produtor rural',
        'Classificam-se neste código as vendas de energia elétrica para consumo por estabelecimento de produtor rural.'),
       ('5257', 'Venda de energia elétrica para consumo por demanda contratada',
        'Classificam-se neste código as vendas de energia elétrica para consumo por demanda contratada, que prevalecerá sobre os demais códigos deste subgrupo.'),
       ('5258', 'Venda de energia elétrica a não contribuinte',
        'Classificam-se neste código as vendas de energia elétrica a pessoas físicas ou a pessoas jurídicas não indicadas nos códigos anteriores.'),
       ('5300', 'PRESTAÇÕES DE SERVIÇOS DE COMUNICAÇÃO', NULL),
       ('5301', 'Prestação de serviço de comunicação para execução de serviço da mesma natureza',
        'Classificam-se neste código as prestações de serviços de comunicação destinados às prestações de serviços da mesma natureza.'),
       ('5302', 'Prestação de serviço de comunicação a estabelecimento industrial',
        'Classificam-se neste código as prestações de serviços de comunicação a estabelecimento industrial. Também serão classificados neste código os serviços de comunicação prestados a estabelecimento industrial de cooperativa.'),
       ('5303', 'Prestação de serviço de comunicação a estabelecimento comercial',
        'Classificam-se neste código as prestações de serviços de comunicação a estabelecimento comercial. Também serão classificados neste código os serviços de comunicação prestados a estabelecimento comercial de cooperativa.'),
       ('5304', 'Prestação de serviço de comunicação a estabelecimento de prestador de serviço de transporte',
        'Classificam-se neste código as prestações de serviços de comunicação a estabelecimento prestador de serviço de transporte.'),
       ('5305',
        'Prestação de serviço de comunicação a estabelecimento de geradora ou de distribuidora de energia elétrica',
        'Classificam-se neste código as prestações de serviços de comunicação a estabelecimento de geradora ou de distribuidora de energia elétrica.'),
       ('5306', 'Prestação de serviço de comunicação a estabelecimento de produtor rural',
        'Classificam-se neste código as prestações de serviços de comunicação a estabelecimento de produtor rural.'),
       ('5307', 'Prestação de serviço de comunicação a não contribuinte',
        'Classificam-se neste código as prestações de serviços de comunicação a pessoas físicas ou a pessoas jurídicas não indicadas nos códigos anteriores.'),
       ('5350', 'PRESTAÇÕES DE SERVIÇOS DE TRANSPORTE', NULL),
       ('5351', 'Prestação de serviço de transporte para execução de serviço da mesma natureza',
        'Classificam-se neste código as prestações de serviços de transporte destinados às prestações de serviços da mesma natureza.'),
       ('5352', 'Prestação de serviço de transporte a estabelecimento industrial',
        'Classificam-se neste código as prestações de serviços de transporte a estabelecimento industrial. Também serão classificados neste código os serviços de transporte prestados a estabelecimento industrial de cooperativa.'),
       ('5353', 'Prestação de serviço de transporte a estabelecimento comercial',
        'Classificam-se neste código as prestações de serviços de transporte a estabelecimento comercial. Também serão classificados neste código os serviços de transporte prestados a estabelecimento comercial de cooperativa.'),
       ('5354', 'Prestação de serviço de transporte a estabelecimento de prestador de serviço de comunicação',
        'Classificam-se neste código as prestações de serviços de transporte a estabelecimento prestador de serviços de comunicação.'),
       ('5355',
        'Prestação de serviço de transporte a estabelecimento de geradora ou de distribuidora de energia elétrica',
        'Classificam-se neste código as prestações de serviços de transporte a estabelecimento de geradora ou de distribuidora de energia elétrica.'),
       ('5356', 'Prestação de serviço de transporte a estabelecimento de produtor rural',
        'Classificam-se neste código as prestações de serviços de transporte a estabelecimento de produtor rural.'),
       ('5357', 'Prestação de serviço de transporte a não contribuinte',
        'Classificam-se neste código as prestações de serviços de transporte a pessoas físicas ou a pessoas jurídicas não indicadas nos códigos anteriores.'),
       ('5359',
        'Prestação de serviço de transporte a contribuinte ou a não-contribuinte, quando a mercadoria transportada esteja dispensada de emissão de Nota Fiscal ',
        'Prestação de serviço de transporte a contribuinte ou a não-contribuinte, quando não existir a obrigação legal de emissão de Nota Fiscal para a mercadoria transportada. (ACR Ajuste SINIEF 03/2004) (DECRETO Nº 26.810, DE 10 DE JUNHO DE 2004) (a partir de 01.01.2005)'),
       ('5360',
        'Prestação de serviço de transporte a contribuinte-substituto em relação ao serviço de transporte (ACR) (Ajuste SINIEF 06/2007- Decreto nº 30.861/2007) – a partir de 01.01.2008',
        'Prestação de serviço de transporte a contribuinte a quem tenha sido atribuída a condição de contribuinte-substituto em relação ao imposto incidente na prestação dos serviços.'),
       ('5400', 'SAÍDAS DE MERCADORIAS SUJEITAS AO REGIME DE SUBSTITUIÇÃO TRIBUTÁRIA', NULL),
       ('5401',
        'Venda de produção do estabelecimento quando o produto esteja sujeito ao regime de substituição tributária',
        'Venda de produto industrializado ou produzido pelo estabelecimento, quando o referido produto estiver sujeito ao regime de substituição tributária, bem como a de produto industrializado, por estabelecimento industrial ou produtor rural de cooperativa, sujeito ao regime de substituição tributária.\r\n(NR Ajuste SINIEF 05/2005) (Dec.28.868/2006 - Efeitos a partir de 01/01/2006, ficando facultada ao contribuinte a sua adoção para fatos geradores ocorridos no período de 01 de novembro a 31 de dezembro de 2005)'),
       ('5402',
        'Venda de produção do estabelecimento de produto sujeito ao regime de substituição tributária, em operação entre contribuintes substitutos do mesmo produto',
        'Classificam-se neste código as vendas de produtos sujeitos ao regime de substituição tributária industrializados no estabelecimento, em operações entre contribuintes substitutos do mesmo produto'),
       ('5403',
        'Venda de mercadoria, adquirida ou recebida de terceiros, sujeita ao regime de substituição tributária, na condição de contribuinte-substituto',
        'Venda de mercadoria, adquirida ou recebida de terceiros, sujeita ao regime de substituição tributária, na condição de contribuinte-substituto.\r\n\r\n– (Decreto Nº 25.068/2003). a partir 01.01.2003'),
       ('5405',
        'Venda de mercadoria, adquirida ou recebida de terceiros, sujeita ao regime de substituição tributária, na condição de contribuinte-substituído',
        'Venda de mercadoria, adquirida ou recebida de terceiros, sujeita ao regime de substituição tributária, na condição de contribuinte-substituído.\r\n\r\n– (Decreto Nº 25.068/2003). a partir 01.01.2003'),
       ('5408',
        'Transferência de produção do estabelecimento quando o produto estiver sujeito ao regime de substituição tributária',
        'Transferência de produto industrializado ou produzido no estabelecimento, para outro estabelecimento da mesma empresa, quando o produto estiver sujeito ao regime de substituição tributária.\r\n(NR Ajuste SINIEF 05/2005) (Dec.28.868/2006 - Efeitos a partir de 01/01/2006, ficando facultada ao contribuinte a sua adoção para fatos geradores ocorridos no período de 01 de novembro a 31 de dezembro de 2005)'),
       ('5409',
        'Transferência de mercadoria adquirida ou recebida de terceiros em operação com mercadoria sujeita ao regime de substituição tributária',
        'Classificam-se neste código as transferências para outro estabelecimento da mesma empresa, de mercadorias adquiridas ou recebidas de terceiros que não tenham sido objeto de qualquer processo industrial no estabelecimento, em operações com mercadorias sujeitas ao regime de substituição tributária.'),
       ('5410', 'Devolução de compra para industrialização de mercadoria sujeita ao regime de substituição tributária',
        'Devolução de mercadoria adquirida para ser utilizada em processo de industrialização ou produção rural, cuja entrada tenha sido classificada como \"Compra para industrialização ou produção rural de mercadoria sujeita ao regime de substituição tributária\".\r\n(NR Ajuste SINIEF 05/2005) (Dec.28.868/2006 - Efeitos a partir de 01/01/2006, ficando facultada ao contribuinte a sua adoção para fatos geradores ocorridos no período de 01 de novembro a 31 de dezembro de 2005)'),
       ('5411',
        'Devolução de compra para comercialização em operação com mercadoria sujeita ao regime de substituição tributária',
        'Classificam-se neste código as devoluções de mercadorias adquiridas para serem comercializadas, cujas entradas tenham sido classificadas como Compra para comercialização em operação com mercadoria sujeita ao regime de substituição tributária.'),
       ('5412',
        'Devolução de bem do ativo imobilizado, em operação com mercadoria sujeita ao regime de substituição tributária',
        'Classificam-se neste código as devoluções de bens adquiridos para integrar o ativo imobilizado do estabelecimento, cuja entrada tenha sido classificada no código 1.406 - Compra de bem para o ativo imobilizado cuja mercadoria está sujeita ao regime de substituição tributária.'),
       ('5413',
        'Devolução de mercadoria destinada ao uso ou consumo, em operação com mercadoria sujeita ao regime de substituição tributária.',
        'Classificam-se neste código as devoluções de mercadorias adquiridas para uso ou consumo do estabelecimento, cuja entrada tenha sido classificada no código 1.407 - Compra de mercadoria para uso ou consumo cuja mercadoria está sujeita ao regime de substituição tributária.'),
       ('5414',
        'Remessa de produção do estabelecimento para venda fora do estabelecimento, quando o produto estiver sujeito ao regime de substituição tributária',
        'Remessa de produto industrializado ou produzido pelo estabelecimento para ser vendido fora do estabelecimento, inclusive por meio de veículo, quando o mencionado produto estiver sujeito ao regime de substituição tributária.\r\n\r\n(NR Ajuste SINIEF 05/2005) (Dec.28.868/2006 - Efeitos a partir de 01/01/2006, ficando facultada ao contribuinte a sua adoção para fatos geradores ocorridos no período de 01 de novembro a 31 de dezembro de 2005)'),
       ('5415',
        'Remessa de mercadoria adquirida ou recebida de terceiros para venda fora do estabelecimento, em operação com mercadoria sujeita ao regime de substituição tributária',
        'Classificam-se neste código as remessas de mercadorias adquiridas ou recebidas de terceiros para serem vendidas fora do estabelecimento, inclusive por meio de veículos, em operações com mercadorias sujeitas ao regime de substituição tributária.'),
       ('5450', 'SISTEMAS DE INTEGRAÇÃO', NULL),
       ('5451', 'Remessa de animal e de insumo para estabelecimento produtor',
        'Classificam-se neste código as saídas referentes à remessa de animais e de insumos para criação de animais no sistema integrado, tais como: pintos, leitões, rações e medicamentos.'),
       ('5500',
        'REMESSAS PARA FORMAÇÃO DE LOTE E COM FIM ESPECÍFICO DE EXPORTAÇÃO E EVENTUAIS DEVOLUÇÕES (NR Ajuste SINIEF 09/2005)',
        '(NR Ajuste SINIEF 09/2005) (Dec. 28.868/2006 - a sua aplicação será obrigatória em relação aos fatos geradores ocorridos a partir de 01 de julho de 2006, ficando facultada ao contribuinte a sua adoção para fatos geradores ocorridos no período de 01 de janeiro a 30 de junho de 2006)'),
       ('5501', 'Remessa de produção do estabelecimento, com fim específico de exportação',
        'Saída de produto industrializado ou produzido pelo estabelecimento, remetido com fim específico de exportação a \"trading company\", empresa comercial exportadora ou outro estabelecimento do remetente\r\n(NR Ajuste SINIEF 05/2005) (Dec.28.868/2006 - Efeitos a partir de 01/01/2006, ficando facultada ao contribuinte a sua adoção para fatos geradores ocorridos no período de 01 de novembro a 31 de dezembro de 2005)'),
       ('5502', 'Remessa de mercadoria adquirida ou recebida de terceiros, com fim específico de exportação',
        'Classificam-se neste código as saídas de mercadorias adquiridas ou recebidas de terceiros, remetidas com fim específico de exportação a trading company, empresa comercial exportadora ou outro estabelecimento do remetente.'),
       ('5503', 'Devolução de mercadoria recebida com fim específico de exportação',
        'Classificam-se neste código as devoluções efetuadas por trading company, empresa comercial exportadora ou outro estabelecimento do destinatário, de mercadorias recebidas com fim específico de exportação, cujas entradas tenham sido classificadas no código 1.501 - Entrada de mercadoria recebida com fim específico de exportação.'),
       ('5504',
        'Remessa de mercadoria para formação de lote de exportação, de produto industrializado ou produzido pelo próprio estabelecimento.',
        'Remessa de mercadoria para formação de lote de exportação, de produto industrializado ou produzido pelo próprio estabelecimento.\r\n\r\n(ACR Ajuste SINIEF 09/2005) (Dec. 28.868/2006 - a sua aplicação será obrigatória em relação aos fatos geradores ocorridos a partir de 01 de julho de 2006, ficando facultada ao contribuinte a sua adoção para fatos geradores ocorridos no período de 01 de janeiro a 30 de junho de 2006)'),
       ('5505', 'Remessa de mercadoria, adquirida ou recebida de terceiros, para formação de lote de exportação.',
        'Remessa de mercadoria, adquirida ou recebida de terceiros, para formação de lote de exportação.\r\n\r\n(ACR Ajuste SINIEF 09/2005) (Dec. 28.868/2006 - a sua aplicação será obrigatória em relação aos fatos geradores ocorridos a partir de 01 de julho de 2006, ficando facultada ao contribuinte a sua adoção para fatos geradores ocorridos no período de 01 de janeiro a 30 de junho de 2006)'),
       ('5550', 'OPERAÇÕES COM BENS DE ATIVO IMOBILIZADO E MATERIAIS PARA USO OU CONSUMO', NULL),
       ('5551', 'Venda de bem do ativo imobilizado',
        'Classificam-se neste código as vendas de bens integrantes do ativo imobilizado do estabelecimento.'),
       ('5552', 'Transferência de bem do ativo imobilizado',
        'Classificam-se neste código os bens do ativo imobilizado transferidos para outro estabelecimento da mesma empresa.'),
       ('5553', 'Devolução de compra de bem para o ativo imobilizado',
        'Classificam-se neste código as devoluções de bens adquiridos para integrar o ativo imobilizado do estabelecimento, cuja entrada foi classificada no código 1.551 - Compra de bem para o ativo imobilizado.'),
       ('5554', 'Remessa de bem do ativo imobilizado para uso fora do estabelecimento',
        'Classificam-se neste código as remessas de bens do ativo imobilizado para uso fora do estabelecimento.'),
       ('5555', 'Devolução de bem do ativo imobilizado de terceiro, recebido para uso no estabelecimento',
        'Classificam-se neste código as saídas em devolução, de bens do ativo imobilizado de terceiros, recebidos para uso no estabelecimento, cuja entrada tenha sido classificada no código 1.555 - Entrada de bem do ativo imobilizado de terceiro, remetido para uso no estabelecimento.'),
       ('5556', 'Devolução de compra de material de uso ou consumo',
        'Classificam-se neste código as devoluções de mercadorias destinadas ao uso ou consumo do estabelecimento, cuja entrada tenha sido classificada no código 1.556 - Compra de material para uso ou consumo.'),
       ('5557', 'Transferência de material de uso ou consumo',
        'Classificam-se neste código os materiais para uso ou consumo transferidos para outro estabelecimento da mesma empresa.'),
       ('5600', 'CRÉDITOS E RESSARCIMENTOS DE ICMS', NULL),
       ('5601', 'Transferência de crédito de ICMS acumulado',
        'Classificam-se neste código os lançamentos destinados ao registro da transferência de créditos de ICMS para outras empresas.'),
       ('5602',
        'Transferência de saldo credor do ICMS, para outro estabelecimento da mesma empresa, destinado à compensação de saldo devedor do ICMS',
        'Lançamento destinado ao registro da transferência de saldo credor do ICMS, para outro estabelecimento da mesma empresa, destinado à compensação do saldo devedor desse estabelecimento, inclusive no caso de apuração centralizada do imposto. (NR Ajuste SINIEF 09/2003 – a partir 01.01.2004)'),
       ('5603', 'Ressarcimento de ICMS retido por substituição tributária',
        'Classificam-se neste código os lançamentos destinados ao registro de ressarcimento de ICMS retido por substituição tributária a contribuinte substituído, efetuado pelo contribuinte substituto, nas hipóteses previstas na legislação aplicável.'),
       ('5605', 'Transferência de saldo devedor do ICMS de outro estabelecimento da mesma empresa ',
        'Lançamento destinado ao registro da transferência de saldo devedor do ICMS para outro estabelecimento da mesma empresa, para efetivação da apuração centralizada do imposto. (ACR Ajuste SINIEF 03/2004) (DECRETO Nº 26.810/2004) (a partir de 01.01.2005)'),
       ('5606', 'Utilização de saldo credor do ICMS para extinção por compensação de débitos fiscais',
        'Lançamento destinado ao registro de utilização de saldo credor do ICMS em conta gráfica para extinção por compensação de débitos fiscais desvinculados de conta gráfica. (ACR Ajuste SINIEF 02/2005 – a partir de 01.01.2006). (DECRETO Nº 27.995 de 06.06.2005) a partir de 01.01.2006'),
       ('5650', 'SAÍDAS DE COMBUSTÍVEIS, DERIVADOS OU NÃO DE PETRÓLEO, E LUBRIFICANTES',
        ' (ACR Ajuste SINIEF 9/2003 - a partir 01.01.2004) ( Decreto Nº 26.174 de 26/11/2003)'),
       ('5651',
        'Venda de combustível ou lubrificante de produção do estabelecimento destinados à industrialização subseqüente',
        'Venda de combustível ou lubrificante, industrializados no estabelecimento e destinados à industrialização do próprio produto, inclusive aquela decorrente de encomenda para entrega futura, cujo faturamento tenha sido classificado no código 5.922 – \"Lançamento efetuado a título de simples faturamento decorrente de venda para entrega futura\".(a partir 01.01.2004 -  Decreto Nº 26.174 de 26/11/2003)'),
       ('5652', 'Venda de combustível ou lubrificante, de produção do estabelecimento, destinados à comercialização',
        'Venda de combustível ou lubrificante, industrializados no estabelecimento, destinados à comercialização, inclusive aquela decorrente de encomenda para entrega futura, cujo faturamento tenha sido classificado no código 5.922 – \"Lançamento efetuado a título de simples faturamento decorrente de venda para entrega futura\".(a partir 01.01.2004 -  Decreto Nº 26.174 de 26/11/2003)'),
       ('5653',
        'Venda de combustível ou lubrificante, de produção do estabelecimento, destinados a consumidor ou usuário final',
        'Venda de combustível ou lubrificante, industrializados no estabelecimento, destinados a consumo em processo de industrialização de outro produto, à prestação de serviço ou a usuário final, inclusive aquela decorrente de encomenda para entrega futura, cujo faturamento tenha sido classificado no código 5.922 – \"Lançamento efetuado a título de simples faturamento decorrente de venda para entrega futura\".(a partir 01.01.2004 -  Decreto Nº 26.174 de 26/11/2003)'),
       ('5654',
        'Venda de combustível ou lubrificante, adquiridos ou recebidos de terceiros, destinados à industrialização subseqüente',
        'Venda de combustível ou lubrificante, adquiridos ou recebidos de terceiros, destinados à industrialização do próprio produto, inclusive aquela decorrente de encomenda para entrega futura, cujo faturamento tenha sido classificado no código 5.922 – \"Lançamento efetuado a título de simples faturamento decorrente de venda para entrega futura\".(a partir 01.01.2004 -  Decreto Nº 26.174 de 26/11/2003)'),
       ('5655',
        'Venda de combustível ou lubrificante, adquiridos ou recebidos de terceiros, destinados à comercialização',
        'Venda de combustível ou lubrificante, adquiridos ou recebidos de terceiros, destinados à comercialização, inclusive aquela decorrente de encomenda para entrega futura, cujo faturamento tenha sido classificado no código 5.922 – \"Lançamento efetuado a título de simples faturamento decorrente de venda para entrega futura\".(a partir 01.01.2004 -  Decreto Nº 26.174 de 26/11/2003)'),
       ('5656',
        'Venda de combustível ou lubrificante, adquiridos ou recebidos de terceiros, destinados a consumidor ou usuário final',
        'Venda de combustível ou lubrificante, adquiridos ou recebidos de terceiros, destinados a consumo em processo de industrialização de outro produto, à prestação de serviço ou a usuário final, inclusive aquela decorrente de encomenda para entrega futura, cujo faturamento tenha sido classificado no código 5.922 – \"Lançamento efetuado a título de simples faturamento decorrente de venda para entrega futura\".(a partir 01.01.2004 -  Decreto Nº 26.174 de 26/11/2003)'),
       ('5657',
        'Remessa de combustível ou lubrificante, adquiridos ou recebidos de terceiros, para venda fora do estabelecimento',
        'Remessa de combustível ou lubrificante, adquiridos ou recebidos de terceiros, para ser vendidos fora do estabelecimento, inclusive por meio de veículos. (a partir 01.01.2004 -  Decreto Nº 26.174 de 26/11/2003)'),
       ('5658', 'Transferência de combustível ou lubrificante de produção do estabelecimento',
        'Transferência de combustível ou lubrificante, industrializados no estabelecimento, para outro estabelecimento da mesma empresa. (a partir 01.01.2004 -  Decreto Nº 26.174 de 26/11/2003)'),
       ('5659', 'Transferência de combustível ou lubrificante adquiridos ou recebidos de terceiros',
        'Transferência de combustível ou lubrificante, adquiridos ou recebidos de terceiros, para outro estabelecimento da mesma empresa. (a partir 01.01.2004 -  Decreto Nº 26.174 de 26/11/2003)'),
       ('5660', 'Devolução de compra de combustível ou lubrificante adquiridos para industrialização subseqüente',
        'Devolução de compra de combustível ou lubrificante, adquiridos para industrialização do próprio produto, cuja entrada tenha sido classificada como \"Compra de combustível ou lubrificante para industrialização subseqüente\".(a partir 01.01.2004 -  Decreto Nº 26.174 de 26/11/2003)'),
       ('5661', 'Devolução de compra de combustível ou lubrificante adquiridos para comercialização',
        'Devolução de compra de combustível ou lubrificante, adquiridos para comercialização, cuja entrada tenha sido classificada como \"Compra de combustível ou lubrificante para comercialização\".(a partir 01.01.2004 -  Decreto Nº 26.174 de 26/11/2003)'),
       ('5662', 'Devolução de compra de combustível ou lubrificante adquiridos por consumidor ou usuário final',
        'Devolução de compra de combustível ou lubrificante, adquiridos para consumo em processo de industrialização de outro produto, na prestação de serviço ou por usuário final, cuja entrada tenha sido classificada como \"Compra de combustível ou lubrificante por consumidor ou usuário final\".(a partir 01.01.2004 -  Decreto Nº 26.174 de 26/11/2003)'),
       ('5663', 'Remessa para armazenagem de combustível ou lubrificante',
        'Remessa para armazenagem de combustível ou lubrificante. (a partir 01.01.2004 -  Decreto Nº 26.174 de 26/11/2003)'),
       ('5664', 'Retorno de combustível ou lubrificante recebidos para armazenagem',
        'Remessa, em devolução, de combustível ou lubrificante, recebidos para armazenagem. (a partir 01.01.2004 -  Decreto Nº 26.174 de 26/11/2003)'),
       ('5665', 'Retorno simbólico de combustível ou lubrificante recebidos para armazenagem',
        'Retorno simbólico de combustível ou lubrificante, recebidos para armazenagem, quando a mercadoria armazenada tenha sido objeto de saída, a qualquer título, e não deva retornar ao estabelecimento depositante. (a partir 01.01.2004 -  Decreto Nº 26.174 de 26/11/2003)'),
       ('5666', 'Remessa, por conta e ordem de terceiros, de combustível ou lubrificante recebidos para armazenagem',
        'Saída, por conta e ordem de terceiros, de combustível ou lubrificante, recebidos anteriormente para armazenagem. (a partir 01.01.2004 -  Decreto Nº 26.174 de 26/11/2003)'),
       ('5667',
        'Venda de combustível ou lubrificante a consumidor ou usuário final estabelecido em outra Unidade da Federação',
        'Venda de combustível ou lubrificante a consumidor ou a usuário final estabelecido em outra Unidade da Federação, cujo abastecimento tenha sido efetuado na unidade da Federação do remetente. ACR Ajuste SINIEF 05/2009 – a partir de 01.07.2009)(Decreto nº 34.490/2009)'),
       ('5900', 'OUTRAS SAÍDAS DE MERCADORIAS OU PRESTAÇÕES DE SERVIÇOS', NULL),
       ('5901', 'Remessa para industrialização por encomenda',
        'Classificam-se neste código as remessas de insumos remetidos para industrialização por encomenda, a ser realizada em outra empresa ou em outro estabelecimento da mesma empresa.'),
       ('5902', 'Retorno de mercadoria utilizada na industrialização por encomenda',
        'Classificam-se neste código as remessas, pelo estabelecimento industrializador, dos insumos recebidos para industrialização e incorporados ao produto final, por encomenda de outra empresa ou de outro estabelecimento da mesma empresa. O valor dos insumos nesta operação deverá ser igual ao valor dos insumos recebidos para industrialização.'),
       ('5903', 'Retorno de mercadoria recebida para industrialização e não aplicada no referido processo',
        'Classificam-se neste código as remessas em devolução de insumos recebidos para industrialização e não aplicados no referido processo.'),
       ('5904', 'Remessa para venda fora do estabelecimento',
        'Classificam-se neste código as remessas de mercadorias para venda fora do estabelecimento, inclusive por meio de veículos.'),
       ('5905', 'Remessa para depósito fechado ou armazém geral',
        'Classificam-se neste código as remessas de mercadorias para depósito em depósito fechado ou armazém geral.'),
       ('5906', 'Retorno de mercadoria depositada em depósito fechado ou armazém geral',
        'Classificam-se neste código os retornos de mercadorias depositadas em depósito fechado ou armazém geral ao estabelecimento depositante.'),
       ('5907', 'Retorno simbólico de mercadoria depositada em depósito fechado ou armazém geral',
        'Classificam-se neste código os retornos simbólicos de mercadorias recebidas para depósito em depósito fechado ou armazém geral, quando as mercadorias depositadas tenham sido objeto de saída a qualquer título e que não devam retornar ao estabelecimento depositante.'),
       ('5908', 'Remessa de bem por conta de contrato de comodato',
        'Classificam-se neste código as remessas de bens para o cumprimento de contrato de comodato.'),
       ('5909', 'Retorno de bem recebido por conta de contrato de comodato',
        'Classificam-se neste código as remessas de bens em devolução após cumprido o contrato de comodato.'),
       ('5910', 'Remessa em bonificação, doação ou brinde',
        'Classificam-se neste código as remessas de mercadorias a título de bonificação, doação ou brinde.'),
       ('5911', 'Remessa de amostra grátis',
        'Classificam-se neste código as remessas de mercadorias a título de amostra grátis.'),
       ('5912', 'Remessa de mercadoria ou bem para demonstração',
        'Classificam-se neste código as remessas de mercadorias ou bens para demonstração.'),
       ('5913', 'Retorno de mercadoria ou bem recebido para demonstração',
        'Classificam-se neste código as remessas em devolução de mercadorias ou bens recebidos para demonstração.'),
       ('5914', 'Remessa de mercadoria ou bem para exposição ou feira',
        'Classificam-se neste código as remessas de mercadorias ou bens para exposição ou feira.'),
       ('5915', 'Remessa de mercadoria ou bem para conserto ou reparo',
        'Classificam-se neste código as remessas de mercadorias ou bens para conserto ou reparo.'),
       ('5916', 'Retorno de mercadoria ou bem recebido para conserto ou reparo',
        'Classificam-se neste código as remessas em devolução de mercadorias ou bens recebidos para conserto ou reparo.'),
       ('5917', 'Remessa de mercadoria em consignação mercantil ou industrial',
        'Classificam-se neste código as remessas de mercadorias a título de consignação mercantil ou industrial.'),
       ('5918', 'Devolução de mercadoria recebida em consignação mercantil ou industrial',
        'Classificam-se neste código as devoluções de mercadorias recebidas anteriormente a título de consignação mercantil ou industrial.'),
       ('5919',
        'Devolução simbólica de mercadoria vendida ou utilizada em processo industrial, recebida anteriormente em consignação mercantil ou industrial',
        'Classificam-se neste código as devoluções simbólicas de mercadorias vendidas ou utilizadas em processo industrial, que tenham sido recebidas anteriormente a título de consignação mercantil ou industrial.'),
       ('5920', 'Remessa de vasilhame ou sacaria', 'Classificam-se neste código as remessas de vasilhame ou sacaria.'),
       ('5921', 'Devolução de vasilhame ou sacaria',
        'Classificam-se neste código as saídas por devolução de vasilhame ou sacaria.'),
       ('5922', 'Lançamento efetuado a título de simples faturamento decorrente de venda para entrega futura',
        'Classificam-se neste código os registros efetuados a título de simples faturamento decorrente de venda para entrega futura.'),
       ('5923', 'Remessa de mercadoria por conta e ordem de terceiros, em venda à ordem',
        'Classificam-se neste código as saídas correspondentes à entrega de mercadorias por conta e ordem de terceiros, em vendas à ordem, cuja venda ao adquirente originário, foi classificada nos códigos 5.118 - Venda de produção do estabelecimento entregue ao destinatário por conta e ordem do adquirente originário, em venda à ordem ou 5.119'),
       ('5924',
        'Remessa para industrialização por conta e ordem do adquirente da mercadoria, quando esta não transitar pelo estabelecimento do adquirente',
        'Classificam-se neste código as saídas de insumos com destino a estabelecimento industrializador, para serem industrializados por conta e ordem do adquirente, nas hipóteses em que os insumos não tenham transitado pelo estabelecimento do adquirente dos mesmos.'),
       ('5925',
        'Retorno de mercadoria recebida para industrialização por conta e ordem do adquirente da mercadoria, quando aquela não transitar pelo estabelecimento do adquirente',
        'Classificam-se neste código as remessas, pelo estabelecimento industrializador, dos insumos recebidos, por conta e ordem do adquirente, para industrialização e incorporados ao produto final, nas hipóteses em que os insumos não tenham transitado pelo estabelecimento do adquirente. O valor dos insumos nesta operação deverá ser igual ao valor dos insumos recebidos para industrialização.'),
       ('5926',
        'Lançamento efetuado a título de reclassificação de mercadoria decorrente de formação de kit ou de sua desagregação',
        'Classificam-se neste código os registros efetuados a título de reclassificação decorrente de formação de kit de mercadorias ou de sua desagregação.'),
       ('5927', 'Lançamento efetuado a título de baixa de estoque decorrente de perda, roubo ou deterioração',
        'Classificam-se neste código os registros efetuados a título de baixa de estoque decorrente de perda, roubou ou deterioração das mercadorias.'),
       ('5928', 'Lançamento efetuado a título de baixa de estoque decorrente de perda, roubo ou deterioração',
        'Classificam-se neste código os registros efetuados a título de baixa de estoque decorrente de perda, roubou ou deterioração das mercadorias.'),
       ('5929',
        'Lançamento efetuado em decorrência de emissão de documento fiscal relativo a operação ou prestação também registrada em equipamento Emissor de Cupom Fiscal - ECF',
        'Classificam-se neste código os registros relativos aos documentos fiscais emitidos em operações ou prestações que também tenham sido registradas em equipamento Emissor de Cupom Fiscal - ECF.'),
       ('5931',
        'Lançamento efetuado em decorrência da responsabilidade de retenção do imposto por substituição tributária, atribuída ao remetente ou alienante da mercadoria, pelo serviço de transporte realizado por t',
        'Classificam-se neste código exclusivamente os lançamentos efetuados pelo remetente ou alienante da mercadoria quando lhe for atribuída a responsabilidade pelo recolhimento do imposto devido pelo serviço de transporte realizado por transportador autônomo ou por transportador não inscrito na unidade da Federação onde iniciado o serviço.'),
       ('5932',
        'Prestação de serviço de transporte iniciada em unidade da Federação diversa daquela onde inscrito o prestador',
        'Classificam-se neste código as prestações de serviço de transporte que tenham sido iniciadas em unidade da Federação diversa daquela onde o prestador está inscrito como contribuinte.'),
       ('5933', 'Prestação de serviço tributado pelo Imposto Sobre Serviços de Qualquer Natureza',
        'Prestação de serviço, cujo imposto é de competência municipal, desde que informado em Nota Fiscal modelo 1 ou 1-A. (NR Ajuste SINIEF 06/2005)a partir de 01/01/2006'),
       ('5949', 'Outra saída de mercadoria ou prestação de serviço não especificado',
        'Classificam-se neste código as outras saídas de mercadorias ou prestações de serviços que não tenham sido especificados nos códigos anteriores.'),
       ('6000', 'SAÍDAS OU PRESTAÇÕES DE SERVIÇOS PARA OUTROS ESTADOS',
        'Classificam-se, neste grupo, as operações ou prestações em que o estabelecimento remetente esteja localizado em unidade da Federação diversa daquela do destinatário'),
       ('6101', 'Venda de produção do estabelecimento',
        'Venda de produto industrializado ou produzido pelo estabelecimento, bem como a de mercadoria por estabelecimento industrial ou produtor rural de cooperativa destinada a seus cooperados ou a estabelecimento de outra cooperativa.\r\n\r\n(NR Ajuste SINIEF 05/2005) (Dec.28.868/2006 - Efeitos a partir de 01/01/2006, ficando facultada ao contribuinte a sua adoção para fatos geradores ocorridos no período de 01 de novembro a 31 de dezembro de 2005)'),
       ('6102', 'Venda de mercadoria adquirida ou recebida de terceiros',
        'Classificam-se neste código as vendas de mercadorias adquiridas ou recebidas de terceiros para industrialização ou comercialização, que não tenham sido objeto de qualquer processo industrial no estabelecimento. Também serão classificadas neste código as vendas de mercadorias por estabelecimento comercial de cooperativa destinadas a seus cooperados ou estabelecimento de outra cooperativa.'),
       ('6103', 'Venda de produção do estabelecimento, efetuada fora do estabelecimento',
        'venda efetuada fora do estabelecimento, inclusive por meio de veículo, de produto industrializado no estabelecimento.\r\n\r\n(NR Ajuste SINIEF 05/2005) (Dec.28.868/2006 - Efeitos a partir de 01/01/2006, ficando facultada ao contribuinte a sua adoção para fatos geradores ocorridos no período de 01 de novembro a 31 de dezembro de 2005)'),
       ('6104', 'Venda de mercadoria adquirida ou recebida de terceiros, efetuada fora do estabelecimento',
        'venda efetuada fora do estabelecimento, inclusive por meio de veículo, de mercadoria adquirida ou recebida de terceiro para industrialização ou comercialização, que não tenha sido objeto de qualquer processo industrial no estabelecimento.'),
       ('6105', 'Venda de produção do estabelecimento que não deva por ele transitar',
        'Classificam-se neste código as vendas de produtos industrializados no estabelecimento, armazenados em depósito fechado, armazém geral ou outro sem que haja retorno ao estabelecimento depositante.'),
       ('6106', 'Venda de mercadoria adquirida ou recebida de terceiros, que não deva por ele transitar',
        'Vendas de mercadoria adquirida ou recebida de terceiro para industrialização ou comercialização, armazenada em depósito fechado, armazém geral ou outro, que não tenha sido objeto de qualquer processo industrial no estabelecimento sem que haja retorno ao estabelecimento depositante. Bem como venda de mercadoria importada, cuja saída ocorra do recinto alfandegado ou da repartição alfandegária onde se processou o desembaraço aduaneiro, com destino ao estabelecimento do comprador, sem que tenha transitado pelo estabelecimento do'),
       ('6107', 'Venda de produção do estabelecimento, destinada a não contribuinte',
        'Vendas de produto industrializado no estabelecimento, ou produzido no estabelecimento do produtor rural, destinada a não contribuinte, bem como qualquer operação de venda destinada a não contribuinte\r\n\r\n (NR Ajuste SINIEF 05/2005) (Dec.28.868/2006 - Efeitos a partir de 01/01/2006, ficando facultada ao contribuinte a sua adoção para fatos geradores ocorridos no período de 01 de novembro a 31 de dezembro de 2005)'),
       ('6108', 'Venda de mercadoria adquirida ou recebida de terceiros, destinada a não contribuinte',
        'Classificam-se neste código as vendas de mercadorias adquiridas ou recebidas de terceiros para industrialização ou comercialização, que não tenham sido objeto de qualquer processo industrial no estabelecimento, destinadas a não contribuintes. Quaisquer operações de venda destinadas a não contribuintes deverão ser classificadas neste código.'),
       ('6109', 'Venda de produção do estabelecimento destinada à Zona Franca de Manaus ou Áreas de Livre Comércio',
        'Venda de produto industrializado ou produzido pelo estabelecimento destinado à Zona Franca de Manaus ou Áreas de Livre Comércio.\r\n\r\n(NR Ajuste SINIEF 05/2005) (Dec.28.868/2006 - Efeitos a partir de 01/01/2006, ficando facultada ao contribuinte a sua adoção para fatos geradores ocorridos no período de 01 de novembro a 31 de dezembro de 2005)'),
       ('6110',
        'Venda de mercadoria, adquirida ou recebida de terceiros, destinada à Zona Franca de Manaus ou Áreas de Livre Comércio, de que trata o Anexo do Convênio SINIEF s/n, de 15 de dezembro de 1970, que dispõ',
        'Venda de mercadoria, adquirida ou recebida de terceiros, destinada à Zona Franca de Manaus ou Áreas de Livre Comércio, desde que alcançada pelos benefícios fiscais de que tratam o Decreto-Lei nº 288, de 28 de fevereiro de 1967, o Convênio ICM 65/88, de 06 de dezembro de 1988, o Convênio ICMS 36/97, de 23 de maio de 1997, e o Convênio ICMS 37/97, de 23 de maio de 1997. (NR Ajuste SINIEF 09/2004) (Decreto nº 26.955/2004) RETROAGINDO SEUS EFEITOS A 24.06.2004'),
       ('6111', 'Venda de produção do estabelecimento remetida anteriormente em consignação industrial',
        'Classificam-se neste código as vendas efetivas de produtos industrializados no estabelecimento remetidos anteriormente a título de consignação industrial.'),
       ('6112',
        'Venda de mercadoria adquirida ou recebida de Terceiros remetida anteriormente em consignação industrial',
        'Classificam-se neste código as vendas efetivas de mercadorias adquiridas ou recebidas de terceiros, que não tenham sido objeto de qualquer processo industrial no estabelecimento, remetidas anteriormente a título de consignação industrial.'),
       ('6113', 'Venda de produção do estabelecimento remetida anteriormente em consignação mercantil',
        'Classificam-se neste código as vendas efetivas de produtos industrializados no estabelecimento remetidos anteriormente a título de consignação mercantil.'),
       ('6114',
        'Venda de mercadoria adquirida ou recebida de terceiros remetida anteriormente em consignação mercantil',
        'Classificam-se neste código as vendas efetivas de mercadorias adquiridas ou recebidas de terceiros, que não tenham sido objeto de qualquer processo industrial no estabelecimento, remetidas anteriormente a título de consignação mercantil.'),
       ('6115',
        'Venda de mercadoria adquirida ou recebida de terceiros, recebida anteriormente em consignação mercantil',
        'Classificam-se neste código as vendas de mercadorias adquiridas ou recebidas de terceiros, recebidas anteriormente a título de consignação mercantil.'),
       ('6116', 'Venda de produção do estabelecimento originada de encomenda para entrega futura',
        'Venda de produto industrializado ou produzido pelo estabelecimento, quando da saída real do produto, cujo faturamento tenha sido classificado no código \"5.922 – Lançamento efetuado a título de simples faturamento decorrente de venda para entrega futura\".\r\n\r\n(NR Ajuste SINIEF 05/2005) (Dec.28.868/2006 - Efeitos a partir de 01/01/2006, ficando facultada ao contribuinte a sua adoção para fatos geradores ocorridos no período de 01 de novembro a 31 de dezembro de 2005)'),
       ('6117', 'Venda de mercadoria adquirida ou recebida de terceiros, originada de encomenda para entrega futura',
        'Classificam-se neste código as vendas de mercadorias adquiridas ou recebidas de terceiros, que não tenham sido objeto de qualquer processo industrial no estabelecimento, quando da saída real da mercadoria, cujo faturamento tenha sido classificado no código 6.922 - Lançamento efetuado a título de simples faturamento decorrente de venda para entrega futura.'),
       ('6118',
        'Venda de produção do estabelecimento entregue ao destinatário por conta e ordem do adquirente originário, em venda à ordem',
        'Classificam-se neste código as vendas à ordem de produtos industrializados pelo estabelecimento, entregues ao destinatário por conta e ordem do adquirente originário.'),
       ('6119',
        'Venda de mercadoria adquirida ou recebida de terceiros entregue ao destinatário por conta e ordem do adquirente originário, em venda à ordem',
        'Classificam-se neste código as vendas à ordem de mercadorias adquiridas ou recebidas de terceiros, que não tenham sido objeto de qualquer processo industrial no estabelecimento, entregues ao destinatário por conta e ordem do adquirente originário.'),
       ('6120',
        'Venda de mercadoria adquirida ou recebida de terceiros entregue ao destinatário pelo vendedor remetente, em venda à ordem',
        'Classificam-se neste código as vendas à ordem de mercadorias adquiridas ou recebidas de terceiros, que não tenham sido objeto de qualquer processo industrial no estabelecimento, entregues pelo vendedor remetente ao destinatário, cuja compra seja classificada, pelo adquirente originário, no código 2.118 - Compra de mercadoria pelo adquirente originário, entregue pelo vendedor remetente ao destinatário, em venda à ordem.'),
       ('6122',
        'Venda de produção do estabelecimento remetida para industrialização, por conta e ordem do adquirente, sem transitar pelo estabelecimento do adquirente',
        'Classificam-se neste código as vendas de produtos industrializados no estabelecimento, remetidos para serem industrializados em outro estabelecimento, por conta e ordem do adquirente, sem que os produtos tenham transitado pelo estabelecimento do adquirente.'),
       ('6123',
        'Venda de mercadoria adquirida ou recebida de terceiros remetida para industrialização, por conta e ordem do adquirente, sem transitar pelo estabelecimento do adquirente',
        'Classificam-se neste código as vendas de mercadorias adquiridas ou recebidas de terceiros, que não tenham sido objeto de qualquer processo industrial no estabelecimento, remetidas para serem industrializadas em outro estabelecimento, por conta e ordem do adquirente, sem que as mercadorias tenham transitado pelo estabelecimento do adquirente.'),
       ('6124', 'Industrialização efetuada para outra empresa',
        'Classificam-se neste código as saídas de mercadorias industrializadas para terceiros, compreendendo os valores referentes aos serviços prestados e os das mercadorias de propriedade do industrializador empregadas no processo industrial.'),
       ('6125',
        'Industrialização efetuada para outra empresa quando a mercadoria recebida para utilização no processo de industrialização não transitar pelo estabelecimento adquirente da mercadoria',
        'Classificam-se neste código as saídas de mercadorias industrializadas para outras empresas, em que as mercadorias recebidas para utilização no processo de industrialização não tenham transitado pelo estabelecimento do adquirente das mercadorias, compreendendo os valores referentes aos serviços prestados e os das mercadorias de propriedade do industrializador empregadas no processo industrial.'),
       ('6150', 'TRANSFERÊNCIAS DE PRODUÇÃO PRÓPRIA OU DE TERCEIROS', NULL),
       ('6151', 'Transferência de produção do estabelecimento',
        'Produtos industrializado ou produzido no estabelecimento e transferido para outro estabelecimento da mesma empresa.\r\n\r\n(NR Ajuste SINIEF 05/2005) (Dec.28.868/2006 - Efeitos a partir de 01/01/2006, ficando facultada ao contribuinte a sua adoção para fatos geradores ocorridos no período de 01 de novembro a 31 de dezembro de 2005)'),
       ('6152', 'Transferência de mercadoria adquirida ou recebida de terceiros',
        'Mercadoria adquirida ou recebida de terceiros para industrialização, comercialização ou utilização na prestação de serviço e que não tenha sido objeto de qualquer processo industrial no estabelecimento, transferida para outro estabelecimento da mesma empresa. A partir  10 de julho de 2003. (Decreto nº 26.020/2003)'),
       ('6153', 'Transferência de energia elétrica',
        'Classificam-se neste código as transferências de energia elétrica para outro estabelecimento da mesma empresa, para distribuição.'),
       ('6155', 'Transferência de produção do estabelecimento, que não deva por ele transitar',
        'Classificam-se neste código as transferências para outro estabelecimento da mesma empresa, de produtos industrializados no estabelecimento que tenham sido remetidos para armazém geral, depósito fechado ou outro, sem que haja retorno ao estabelecimento depositante.'),
       ('6156', 'Transferência de mercadoria adquirida ou recebida de terceiros, que não deva por ele transitar',
        'Classificam-se neste código as transferências para outro estabelecimento da mesma empresa, de mercadorias adquiridas ou recebidas de terceiros para industrialização ou comercialização, que não tenham sido objeto de qualquer processo industrial, remetidas para armazém geral, depósito fechado ou outro, sem que haja retorno ao estabelecimento depositante.'),
       ('6200', 'DEVOLUÇÕES DE COMPRAS PARA INDUSTRIALIZAÇÃO, COMERCIALIZAÇÃO OU ANULAÇÕES DE VALORES',
        '(NR Ajuste SINIEF 05/2005) (Dec.28.868/2006 - Efeitos a partir de 01/01/2006, ficando facultada ao contribuinte a sua adoção para fatos geradores ocorridos no período de 01 de novembro a 31 de dezembro de 2005)'),
       ('6201',
        'Devolução de compra para industrialização ou produção rural (NR Ajuste SINIEF 05/2005) (Decreto 28.868/2006)',
        'Devolução de mercadoria adquirida para ser utilizada em processo de industrialização ou produção rural, cuja entrada tenha sido classificada como \"1.101 - Compra para industrialização ou produção rural\".\r\n\r\n(NR Ajuste SINIEF 05/2005) (Dec.28.868/2006 - Efeitos a partir de 01/01/2006, ficando facultada ao contribuinte a sua adoção para fatos geradores ocorridos no período de 01 de novembro a 31 de dezembro de 2005)'),
       ('6202', 'Devolução de compra para comercialização',
        'Classificam-se neste código as devoluções de mercadorias adquiridas para serem comercializadas, cujas entradas tenham sido classificadas como Compra para comercialização.'),
       ('6205', 'Anulação de valor relativo a aquisição de serviço de comunicação',
        'Classificam-se neste código as anulações correspondentes a valores faturados indevidamente, decorrentes das aquisições de serviços de comunicação.'),
       ('6206', 'Anulação de valor relativo a aquisição de serviço de transporte',
        'Classificam-se neste código as anulações correspondentes a valores faturados indevidamente, decorrentes das aquisições de serviços de transporte.'),
       ('6207', 'Anulação de valor relativo à compra de energia elétrica',
        'Classificam-se neste código as anulações correspondentes a valores faturados indevidamente, decorrentes da compra de energia elétrica.'),
       ('6208', 'Devolução de mercadoria recebida em transferência para industrialização ou produção rural',
        'Devolução de mercadoria recebida em transferência de outro estabelecimento da mesma empresa, para ser utilizada em processo de industrialização ou produção rural.\r\n\r\n(NR Ajuste SINIEF 05/2005) (Dec.28.868/2006 - Efeitos a partir de 01/01/2006, ficando facultada ao contribuinte a sua adoção para fatos geradores ocorridos no período de 01 de novembro a 31 de dezembro de 2005)'),
       ('6209', 'Devolução de mercadoria recebida em transferência para comercialização',
        'Classificam-se neste código as devoluções de mercadorias recebidas em transferência de outro estabelecimento da mesma empresa, para serem comercializadas.'),
       ('6210', 'Devolução de compra para utilização na prestação de serviço',
        'Classificam-se neste código as devoluções de mercadorias adquiridas para utilização na prestação de serviços, cujas entradas tenham sido classificadas no código 2.126 - Compra para utilização na prestação de serviço.'),
       ('6250', 'VENDAS DE ENERGIA ELÉTRICA', NULL),
       ('6251', 'Venda de energia elétrica para distribuição ou comercialização',
        'Classificam-se neste código as vendas de energia elétrica destinada à distribuição ou comercialização. Também serão classificadas neste código as vendas de energia elétrica destinada a cooperativas para distribuição aos seus cooperados.'),
       ('6252', 'Venda de energia elétrica para estabelecimento industrial',
        'Classificam-se neste código as vendas de energia elétrica para consumo por estabelecimento industrial. Também serão classificadas neste código as vendas de energia elétrica destinada a estabelecimento industrial de cooperativa.'),
       ('6253', 'Venda de energia elétrica para estabelecimento comercial',
        'Classificam-se neste código as vendas de energia elétrica para consumo por estabelecimento comercial. Também serão classificadas neste código as vendas de energia elétrica destinada a estabelecimento comercial de cooperativa.'),
       ('6254', 'Venda de energia elétrica para estabelecimento prestador de serviço de transporte',
        'Classificam-se neste código as vendas de energia elétrica para consumo por estabelecimento de prestador de serviços de transporte.'),
       ('6255', 'Venda de energia elétrica para estabelecimento prestador de serviço de comunicação',
        'Classificam-se neste código as vendas de energia elétrica para consumo por estabelecimento de prestador de serviços de comunicação.'),
       ('6256', 'Venda de energia elétrica para estabelecimento de produtor rural',
        'Classificam-se neste código as vendas de energia elétrica para consumo por estabelecimento de produtor rural.'),
       ('6257', 'Venda de energia elétrica para consumo por demanda contratada',
        'Classificam-se neste código as vendas de energia elétrica para consumo por demanda contratada, que prevalecerá sobre os demais códigos deste subgrupo.'),
       ('6258', 'Venda de energia elétrica a não contribuinte',
        'Classificam-se neste código as vendas de energia elétrica a pessoas físicas ou a pessoas jurídicas não indicadas nos códigos anteriores.'),
       ('6300', 'PRESTAÇÕES DE SERVIÇOS DE COMUNICAÇÃO', NULL),
       ('6301', 'Prestação de serviço de comunicação para execução de serviço da mesma natureza',
        'Classificam-se neste código as prestações de serviços de comunicação destinados às prestações de serviços da mesma natureza.'),
       ('6302', 'Prestação de serviço de comunicação a estabelecimento industrial',
        'Classificam-se neste código as prestações de serviços de comunicação a estabelecimento industrial. Também serão classificados neste código os serviços de comunicação prestados a estabelecimento industrial de cooperativa.'),
       ('6303', 'Prestação de serviço de comunicação a estabelecimento comercial',
        'Classificam-se neste código as prestações de serviços de comunicação a estabelecimento comercial. Também serão classificados neste código os serviços de comunicação prestados a estabelecimento comercial de cooperativa.'),
       ('6304', 'Prestação de serviço de comunicação a estabelecimento de prestador de serviço de transporte',
        'Classificam-se neste código as prestações de serviços de comunicação a estabelecimento prestador de serviço de transporte.'),
       ('6305',
        'Prestação de serviço de comunicação a estabelecimento de geradora ou de distribuidora de energia elétrica',
        'Classificam-se neste código as prestações de serviços de comunicação a estabelecimento de geradora ou de distribuidora de energia elétrica.'),
       ('6306', 'Prestação de serviço de comunicação a estabelecimento de produtor rural',
        'Classificam-se neste código as prestações de serviços de comunicação a estabelecimento de produtor rural.'),
       ('6307', 'Prestação de serviço de comunicação a não contribuinte',
        'Classificam-se neste código as prestações de serviços de comunicação a pessoas físicas ou a pessoas jurídicas não indicadas nos códigos anteriores.'),
       ('6350', 'PRESTAÇÕES DE SERVIÇOS DE TRANSPORTE', NULL),
       ('6351', 'Prestação de serviço de transporte para execução de serviço da mesma natureza',
        'Classificam-se neste código as prestações de serviços de transporte destinados às prestações de serviços da mesma natureza.'),
       ('6352', 'Prestação de serviço de transporte a estabelecimento industrial',
        'Classificam-se neste código as prestações de serviços de transporte a estabelecimento industrial. Também serão classificados neste código os serviços de transporte prestados a estabelecimento industrial de cooperativa.'),
       ('6353', 'Prestação de serviço de transporte a estabelecimento comercial',
        'Classificam-se neste código as prestações de serviços de transporte a estabelecimento comercial. Também serão classificados neste código os serviços de transporte prestados a estabelecimento comercial de cooperativa.'),
       ('6354', 'Prestação de serviço de transporte a estabelecimento de prestador de serviço de comunicação',
        'Classificam-se neste código as prestações de serviços de transporte a estabelecimento prestador de serviços de comunicação.'),
       ('6355',
        'Prestação de serviço de transporte a estabelecimento de geradora ou de distribuidora de energia elétrica',
        'Classificam-se neste código as prestações de serviços de transporte a estabelecimento de geradora ou de distribuidora de energia elétrica.'),
       ('6356', 'Prestação de serviço de transporte a estabelecimento de produtor rural',
        'Classificam-se neste código as prestações de serviços de transporte a estabelecimento de produtor rural.'),
       ('6357', 'Prestação de serviço de transporte a não contribuinte',
        'Classificam-se neste código as prestações de serviços de transporte a pessoas físicas ou a pessoas jurídicas não indicadas nos códigos anteriores.'),
       ('6359',
        'Prestação de serviço de transporte a contribuinte ou a não-contribuinte, quando a mercadoria transportada esteja dispensada de emissão de Nota Fiscal ',
        'Prestação de serviço de transporte a contribuinte ou a não-contribuinte, quando não existir a obrigação legal de emissão de Nota Fiscal para a mercadoria transportada. (ACR Ajuste SINIEF 03/2004) (DECRETO Nº 26.810/2004) (a partir de 01.01.2005)'),
       ('6360', 'Prestação de serviço de transporte a contribuinte substituto em relação ao serviço de transporte  ',
        'Prestação de serviço de transporte a contribuinte a quem tenha sido atribuída a condição de contribuinte-substituto em relação ao imposto incidente na prestação dos serviços. (Ajuste SINIEF 03/2008) (Decreto nº 32.653, de 14.11.2008) a partir de 01.05.2008'),
       ('6400', 'SAÍDAS DE MERCADORIAS SUJEITAS AO REGIME DE SUBSTITUIÇÃO TRIBUTÁRIA', NULL),
       ('6401',
        'Venda de produção do estabelecimento quando o produto estiver sujeito ao regime de substituição tributária',
        'Venda de produto industrializado ou produzido no estabelecimento, quando o produto estiver sujeito ao regime de substituição tributária, bem como a venda de produto industrializado por estabelecimento industrial ou rural de cooperativa, quando o produto estiver sujeito ao referido regime.\r\n\r\n(NR Ajuste SINIEF 05/2005) (Dec.28.868/2006 - Efeitos a partir de 01/01/2006, ficando facultada ao contribuinte a sua adoção para fatos geradores ocorridos no período de 01 de novembro a 31 de dezembro de 2005)'),
       ('6402',
        'Venda de produção do estabelecimento de produto sujeito ao regime de substituição tributária, em operação entre contribuintes substitutos do mesmo produto',
        'Classificam-se neste código as vendas de produtos sujeitos ao regime de substituição tributária industrializados no estabelecimento, em operações entre contribuintes substitutos do mesmo produto.'),
       ('6403',
        'Venda de mercadoria adquirida ou recebida de terceiros em operação com mercadoria sujeita ao regime de substituição tributária, na condição de contribuinte substituto',
        'Classificam-se neste código as vendas de mercadorias adquiridas ou recebidas de terceiros, na condição de contribuinte substituto, em operação com mercadorias sujeitas ao regime de substituição tributária.'),
       ('6404',
        'Venda de mercadoria sujeita ao regime de substituição tributária, cujo imposto já tenha sido retido anteriormente',
        'Classificam-se neste código as vendas de mercadorias sujeitas ao regime de substituição tributária, na condição de substituto tributário, exclusivamente nas hipóteses em que o imposto já tenha sido retido anteriormente.'),
       ('6408',
        'Transferência de produção do estabelecimento quando o produto estiver sujeito ao regime de substituição tributária',
        'Transferência de produto industrializado ou produzido no estabelecimento, para outro estabelecimento da mesma empresa, quando o produto estiver sujeito ao regime de substituição tributária.\r\n\r\n(NR Ajuste SINIEF 05/2005) (Dec.28.868/2006 - Efeitos a partir de 01/01/2006, ficando facultada ao contribuinte a sua adoção para fatos geradores ocorridos no período de 01 de novembro a 31 de dezembro de 2005)'),
       ('6409',
        'Transferência de mercadoria adquirida ou recebida de terceiros, sujeita ao regime de substituição tributária',
        'Classificam-se neste código as transferências para outro estabelecimento da mesma empresa, de mercadorias adquiridas ou recebidas de terceiros que não tenham sido objeto de qualquer processo industrial no estabelecimento, em operações com mercadorias sujeitas ao regime de substituição tributária.'),
       ('6410',
        'Devolução de compra para industrialização ou ptrodução rural quando a mercadoria sujeita ao regime de substituição tributária',
        'Devolução de mercadoria adquirida para ser utilizada em processo de industrialização ou produção rural, cuja entrada tenha sido classificada como \"Compra para industrialização ou produção rural de mercadoria sujeita ao regime de substituição tributária\".\r\n\r\n(NR Ajuste SINIEF 05/2005) (Dec.28.868/2006 - Efeitos a partir de 01/01/2006, ficando facultada ao contribuinte a sua adoção para fatos geradores ocorridos no período de 01 de novembro a 31 de dezembro de 2005)'),
       ('6411',
        'Devolução de compra para comercialização em operação com mercadoria sujeita ao regime de substituição tributária',
        'Classificam-se neste código as devoluções de mercadorias adquiridas para serem comercializadas, cujas entradas tenham sido classificadas como Compra para comercialização em operação com mercadoria sujeita ao regime de substituição tributária.'),
       ('6412',
        'Devolução de bem do ativo imobilizado, em operação com mercadoria sujeita ao regime de substituição tributária',
        'Classificam-se neste código as devoluções de bens adquiridos para integrar o ativo imobilizado do estabelecimento, cuja entrada tenha sido classificada no código 2.406 - Compra de bem para o ativo imobilizado cuja mercadoria está sujeita ao regime de substituição tributária.'),
       ('6413',
        'Devolução de mercadoria destinada ao uso ou consumo, em operação com mercadoria sujeita ao regime de substituição tributária',
        'Classificam-se neste código as devoluções de mercadorias adquiridas para uso ou consumo do estabelecimento, cuja entrada tenha sido classificada no código 2.407 - Compra de mercadoria para uso ou consumo cuja mercadoria está sujeita ao regime de substituição tributária.'),
       ('6414',
        'Remessa de produção do estabelecimento para venda fora do estabelecimento, quando o produto estiver sujeito ao regime de substituição tributária',
        'Remessa de produto industrializado ou produzido pelo estabelecimento para ser vendido fora do estabelecimento, inclusive por meio de veículo, quando o mencionado produto estiver sujeito ao regime de substituição tributária.\r\n\r\n(NR Ajuste SINIEF 05/2005) (Dec.28.868/2006 - Efeitos a partir de 01/01/2006, ficando facultada ao contribuinte a sua adoção para fatos geradores ocorridos no período de 01 de novembro a 31 de dezembro de 2005)'),
       ('6415',
        'Remessa de mercadoria adquirida ou recebida de terceiros para venda fora do estabelecimento, quando a referida ração com mercadoria sujeita ao regime de substituição tributária',
        'Remessa de mercadoria adquirida ou recebida de terceiro para serem vendida fora do estabelecimento, inclusive por meio de veículo, quando a referida mercadorias estiver sujeita ao regime de substituição tributária.'),
       ('6500', 'REMESSAS COM FIM ESPECÍFICO DE EXPORTAÇÃO E EVENTUAIS DEVOLUÇÕES',
        '(NR Ajuste SINIEF 05/2005) (Dec. 28.868/2006 - a sua aplicação será obrigatória em relação aos fatos geradores ocorridos a partir de 01 de julho de 2006, ficando facultada ao contribuinte a sua adoção para fatos geradores ocorridos no período de 01 de janeiro a 30 de junho de 2006)'),
       ('6501', 'Remessa de produção do estabelecimento, com fim específico de exportação',
        'Saída de produto industrializado ou produzido pelo estabelecimento, remetido com fim específico de exportação a \"trading company\", empresa comercial exportadora ou outro estabelecimento do remetente.\r\n\r\n(NR Ajuste SINIEF 05/2005) (Dec. 28.868/2006 - a sua aplicação será obrigatória em relação aos fatos geradores ocorridos a partir de 01 de julho de 2006, ficando facultada ao contribuinte a sua adoção para fatos geradores ocorridos no período de 01 de janeiro a 30 de junho de 2006)'),
       ('6502', 'Remessa de mercadoria adquirida ou recebida de terceiros, com fim específico de exportação',
        'Classificam-se neste código as saídas de mercadorias adquiridas ou recebidas de terceiros, remetidas com fim específico de exportação a trading company, empresa comercial exportadora ou outro estabelecimento do remetente.'),
       ('6503', 'Devolução de mercadoria recebida com fim específico de exportação',
        'Classificam-se neste código as devoluções efetuadas por trading company, empresa comercial exportadora ou outro estabelecimento do destinatário, de mercadorias recebidas com fim específico de exportação, cujas entradas tenham sido classificadas no código 2.501 - Entrada de mercadoria recebida com fim específico de exportação.'),
       ('6504',
        'Remessa de mercadoria para formação de lote de exportação, de produto industrializado ou produzido pelo próprio estabelecimento.',
        'Remessa de mercadoria para formação de lote de exportação, de produto industrializado ou produzido pelo próprio estabelecimento.\r\n\r\n(ACR Ajuste SINIEF 09/2005) (Dec. 28.868/2006 - a sua aplicação será obrigatória em relação aos fatos geradores ocorridos a partir de 01 de julho de 2006, ficando facultada ao contribuinte a sua adoção para fatos geradores ocorridos no período de 01 de janeiro a 30 de junho de 2006)'),
       ('6505', 'Remessa de mercadoria, adquirida ou recebida de terceiros, para formação de lote de exportação.',
        'Remessa de mercadoria, adquirida ou recebida de terceiros, para formação de lote de exportação.\r\n\r\n(ACR Ajuste SINIEF 09/2005) (Dec. 28.868/2006 - a sua aplicação será obrigatória em relação aos fatos geradores ocorridos a partir de 01 de julho de 2006, ficando facultada ao contribuinte a sua adoção para fatos geradores ocorridos no período de 01 de janeiro a 30 de junho de 2006)'),
       ('6550', 'OPERAÇÕES COM BENS DE ATIVO IMOBILIZADO E MATERIAIS PARA USO OU CONSUMO', NULL),
       ('6551', 'Venda de bem do ativo imobilizado',
        'Vendas de bem integrante do ativo imobilizado do estabelecimento. –a  partir 01.01.2004-  Decreto Nº 26.174 de 26/11/2003'),
       ('6552', 'Transferência de bem do ativo imobilizado',
        'Transferência de bem do ativo imobilizado para outro estabelecimento da mesma empresa. –a  partir 01.01.2004-  Decreto Nº 26.174 de 26/11/2003'),
       ('6553', 'Devolução de compra de bem para o ativo imobilizado',
        'Devolução de bem adquirido para integrar o ativo imobilizado do estabelecimento, cuja entrada foi classificada no código 2.551 - Compra de bem para o ativo imobilizado. –a  partir 01.01.2004-  Decreto Nº 26.174 de 26/11/2003'),
       ('6554', 'Remessa de bem do ativo imobilizado para uso fora do estabelecimento',
        'Remessa de bem do ativo imobilizado para uso fora do estabelecimento. –a  partir 01.01.2004-  Decreto Nº 26.174 de 26/11/2003'),
       ('6555', 'Devolução de bem do ativo imobilizado de terceiro, recebido para uso no estabelecimento',
        'Saída em devolução, de bem do ativo imobilizado de terceiros, recebidos para uso no estabelecimento, cuja entrada tenha sido classificada no código 2.555 - Entrada de bem do ativo imobilizado de terceiro, remetido para uso no estabelecimento. –a  partir 01.01.2004-  Decreto Nº 26.174 de 26/11/2003'),
       ('6556', 'Devolução de compra de material de uso ou consumo',
        'Devolução de mercadoria destinada ao uso ou consumo do estabelecimento, cuja entrada tenha sido classificada no código 2.556 - compra de material para uso ou consumo –a partir 01.01.2004-  Decreto Nº 26.174 de 26/11/2003'),
       ('6557', 'Transferência de material de uso ou consumo',
        'Transferência de material de uso ou consumo para outro estabelecimento da mesma empresa. –a  partir 01.01.2004-  Decreto Nº 26.174 de 26/11/2003'),
       ('6600', 'CRÉDITOS E RESSARCIMENTOS DE ICMS', NULL),
       ('6603', 'Ressarcimento de ICMS retido por substituição tributária',
        'Classificam-se neste código os lançamentos destinados ao registro de ressarcimento de ICMS retido por substituição tributária a contribuinte substituído, efetuado pelo contribuinte substituto, nas hipóteses previstas na legislação aplicável.'),
       ('6650', 'SAÍDAS DE COMBUSTÍVEIS, DERIVADOS OU NÃO DE PETRÓLEO, E LUBRIFICANTE',
        '(ACR Ajuste SINIEF 9/2003 - a partir 01.01.2004) –  Decreto Nº 26.174 de 26/11/2003'),
       ('6651',
        'Venda de combustível ou lubrificante, de produção do estabelecimento, destinados à industrialização subseqüente',
        'Venda de combustível ou lubrificante, industrializados no estabelecimento e destinados à industrialização do próprio produto, inclusive aquela decorrente de encomenda para entrega futura, cujo faturamento tenha sido classificado no código 6.922 – \"Lançamento efetuado a título de simples faturamento decorrente de venda para entrega futura\".(a partir 01.01.2004 -  Decreto Nº 26.174 de 26/11/2003)'),
       ('6652', 'Venda de combustível ou lubrificante, de produção do estabelecimento, destinados à comercialização',
        'Venda de combustível ou lubrificante, industrializados no estabelecimento e destinados à comercialização, inclusive aquela decorrente de encomenda para entrega futura, cujo faturamento tenha sido classificado no código 6.922 – \"Lançamento efetuado a título de simples faturamento decorrente de venda para entrega futura\".(a partir 01.01.2004 -  Decreto Nº 26.174 de 26/11/2003)'),
       ('6653',
        'Venda de combustível ou lubrificante, de produção do estabelecimento, destinados a consumidor ou usuário final',
        'Venda de combustível ou lubrificante, industrializados no estabelecimento e destinados a consumo em processo de industrialização de outro produto, à prestação de serviço ou a usuário final, inclusive aquela decorrente de encomenda para entrega futura, cujo faturamento tenha sido classificado no código 6.922 – \"Lançamento efetuado a título de simples faturamento decorrente de venda para entrega futura\".(a partir 01.01.2004 -  Decreto Nº 26.174 de 26/11/2003)'),
       ('6654',
        'Venda de combustível ou lubrificante, adquiridos ou recebidos de terceiros, destinados à industrialização subseqüente',
        'Venda de combustível ou lubrificante, adquiridos ou recebidos de terceiros, destinados à industrialização do próprio produto, inclusive aquela decorrente de encomenda para entrega futura, cujo faturamento tenha sido classificado no código 5.922 – \"Lançamento efetuado a título de simples faturamento decorrente de venda para entrega futura\".(a partir 01.01.2004 -  Decreto Nº 26.174 de 26/11/2003)'),
       ('6655',
        'Venda de combustível ou lubrificante, adquiridos ou recebidos de terceiros, destinados à comercialização',
        'Venda de combustível ou lubrificante, adquiridos ou recebidos de terceiros, destinados à comercialização, inclusive aquela decorrente de encomenda para entrega futura, cujo faturamento tenha sido classificado no código 5.922 – \"Lançamento efetuado a título de simples faturamento decorrente de venda para entrega futura\".(a partir 01.01.2004 -  Decreto Nº 26.174 de 26/11/2003)'),
       ('6656',
        'Venda de combustível ou lubrificante, adquiridos ou recebidos de terceiros, destinados a consumidor ou usuário final',
        'Venda de combustível ou lubrificante, adquiridos ou recebidos de terceiros, destinados a consumo em processo de industrialização de outro produto, à prestação de serviço ou a usuário final, inclusive aquela decorrente de encomenda para entrega futura, cujo faturamento tenha sido classificado no código 5.922 – \"Lançamento efetuado a título de simples faturamento decorrente de venda para entrega futura\".(a partir 01.01.2004 -  Decreto Nº 26.174 de 26/11/2003)'),
       ('6657',
        'Remessa de combustível ou lubrificante, adquiridos ou recebidos de terceiros, para venda fora do estabelecimento',
        'Remessa de combustível ou lubrificante, adquiridos ou recebidos de terceiros, para ser vendidos fora do estabelecimento, inclusive por meio de veículos. (a partir 01.01.2004 -  Decreto Nº 26.174 de 26/11/2003)'),
       ('6658', 'Transferência de combustível ou lubrificante de produção do estabelecimento',
        'Transferência de combustível ou lubrificante, industrializados no estabelecimento, para outro estabelecimento da mesma empresa. (a partir 01.01.2004 -  Decreto Nº 26.174 de 26/11/2003)'),
       ('6659', 'Transferência de combustível ou lubrificante adquiridos ou recebidos de terceiros',
        'Transferência de combustível ou lubrificante, adquiridos ou recebidos de terceiros, para outro estabelecimento da mesma empresa. (a partir 01.01.2004 -  Decreto Nº 26.174 de 26/11/2003)'),
       ('6660', 'Devolução de compra de combustível ou lubrificante adquiridos para industrialização subseqüente',
        'Devolução de compra de combustível ou lubrificante, adquiridos para industrialização do próprio produto, cuja entrada tenha sido classificada como \"Compra de combustível ou lubrificante para industrialização subseqüente\".(a partir 01.01.2004 -  Decreto Nº 26.174 de 26/11/2003)'),
       ('6661', 'Devolução de compra de combustível ou lubrificante adquiridos para comercialização',
        'Devolução de compra de combustível ou lubrificante, adquiridos para comercialização, cuja entrada tenha sido classificada como \"Compra de combustível ou lubrificante para comercialização\".(a partir 01.01.2004 -  Decreto Nº 26.174 de 26/11/2003)'),
       ('6662', 'Devolução de compra de combustível ou lubrificante adquiridos por consumidor ou usuário final',
        'Devolução de compra de combustível ou lubrificante, adquiridos para consumo em processo de industrialização de outro produto, na prestação de serviço ou por usuário final, cuja entrada tenha sido classificada como \"Compra de combustível ou lubrificante por consumidor ou usuário final\".(a partir 01.01.2004 -  Decreto Nº 26.174 de 26/11/2003)'),
       ('6663', 'Remessa para armazenagem de combustível ou lubrificante',
        'Remessa para armazenagem de combustível ou lubrificante. (a partir 01.01.2004 -  Decreto Nº 26.174 de 26/11/2003)'),
       ('6664', 'Retorno de combustível ou lubrificante recebidos para armazenagem',
        'Remessa, em devolução, de combustível ou lubrificante, recebidos para armazenagem. (a partir 01.01.2004 -  Decreto Nº 26.174 de 26/11/2003)'),
       ('6665', 'Retorno simbólico de combustível ou lubrificante recebidos para armazenagem',
        'Retorno simbólico de combustível ou lubrificante, recebidos para armazenagem, quando a mercadoria armazenada tenha sido objeto de saída, a qualquer título, e não deva retornar ao estabelecimento depositante. (a partir 01.01.2004 -  Decreto Nº 26.174 de 26/11/2003)'),
       ('6666', 'Remessa, por conta e ordem de terceiros, de combustível ou lubrificante recebidos para armazenagem',
        'Saída, por conta e ordem de terceiros, de combustível ou lubrificante, recebidos anteriormente para armazenagem. (a partir 01.01.2004 -  Decreto Nº 26.174 de 26/11/2003)'),
       ('6667',
        'Venda de combustível ou lubrificante a consumidor ou usuário final estabelecido em outra Unidade da Federação diferente da que ocorrer o consumo',
        'Venda de combustível ou lubrificante a consumidor ou a usuário final, cujo abastecimento tenha sido efetuado em Unidade da Federação diferente do remetente e do destinatário. ACR Ajuste SINIEF 05/2009 – a partir de 01.07.2009)(Decreto nº 34.490/2009)'),
       ('6900', 'OUTRAS SAÍDAS DE MERCADORIAS OU PRESTAÇÕES DE SERVIÇOS', NULL),
       ('6901', 'Remessa para industrialização por encomenda',
        'Classificam-se neste código as remessas de insumos remetidos para industrialização por encomenda, a ser realizada em outra empresa ou em outro estabelecimento da mesma empresa.'),
       ('6902', 'Retorno de mercadoria utilizada na industrialização por encomenda',
        'Classificam-se neste código as remessas, pelo estabelecimento industrializador, dos insumos recebidos para industrialização e incorporados ao produto final, por encomenda de outra empresa ou de outro estabelecimento da mesma empresa. O valor dos insumos nesta operação deverá ser igual ao valor dos insumos recebidos para industrialização.'),
       ('6903', 'Retorno de mercadoria recebida para industrialização e não aplicada no referido processo',
        'Classificam-se neste código as remessas em devolução de insumos recebidos para industrialização e não aplicados no referido processo.'),
       ('6904', 'Remessa para venda fora do estabelecimento',
        'Classificam-se neste código as remessas de mercadorias para venda fora do estabelecimento, inclusive por meio de veículos.'),
       ('6905', 'Remessa para depósito fechado ou armazém geral',
        'Classificam-se neste código as remessas de mercadorias para depósito em depósito fechado ou armazém geral.'),
       ('6906', 'Retorno de mercadoria depositada em depósito fechado ou armazém geral',
        'Classificam-se neste código os retornos de mercadorias depositadas em depósito fechado ou armazém geral ao estabelecimento depositante.'),
       ('6907', 'Retorno simbólico de mercadoria depositada em depósito fechado ou armazém geral',
        'Classificam-se neste código os retornos simbólicos de mercadorias recebidas para depósito em depósito fechado ou armazém geral, quando as mercadorias depositadas tenham sido objeto de saída a qualquer título e que não devam retornar ao estabelecimento depositante.'),
       ('6908', 'Remessa de bem por conta de contrato de comodato',
        'Classificam-se neste código as remessas de bens para o cumprimento de contrato de comodato.'),
       ('6909', 'Retorno de bem recebido por conta de contrato de comodato',
        'Classificam-se neste código as remessas de bens em devolução após cumprido o contrato de comodato.'),
       ('6910', 'Remessa em bonificação, doação ou brinde',
        'Classificam-se neste código as remessas de mercadorias a título de bonificação, doação ou brinde.'),
       ('6911', 'Remessa de amostra grátis',
        'Classificam-se neste código as remessas de mercadorias a título de amostra grátis.'),
       ('6912', 'Remessa de mercadoria ou bem para demonstração',
        'Classificam-se neste código as remessas de mercadorias ou bens para demonstração.'),
       ('6913', 'Retorno de mercadoria ou bem recebido para demonstração',
        'Classificam-se neste código as remessas em devolução de mercadorias ou bens recebidos para demonstração.'),
       ('6914', 'Remessa de mercadoria ou bem para exposição ou feira',
        'Classificam-se neste código as remessas de mercadorias ou bens para exposição ou feira.'),
       ('6915', 'Remessa de mercadoria ou bem para conserto ou reparo',
        'Classificam-se neste código as remessas de mercadorias ou bens para conserto ou reparo.'),
       ('6916', 'Retorno de mercadoria ou bem recebido para conserto ou reparo',
        'Classificam-se neste código as remessas em devolução de mercadorias ou bens recebidos para conserto ou reparo.'),
       ('6917', 'Remessa de mercadoria em consignação mercantil ou industrial',
        'Classificam-se neste código as remessas de mercadorias a título de consignação mercantil ou industrial.'),
       ('6918', 'Devolução de mercadoria recebida em consignação mercantil ou industrial',
        'Classificam-se neste código as devoluções de mercadorias recebidas anteriormente a título de consignação mercantil ou industrial.'),
       ('6919',
        'Devolução simbólica de mercadoria vendida ou utilizada em processo industrial, recebida anteriormente em consignação mercantil ou industrial',
        'Classificam-se neste código as devoluções simbólicas de mercadorias vendidas ou utilizadas em processo industrial, que tenham sido recebidas anteriormente a título de consignação mercantil ou industrial.'),
       ('6920', 'Remessa de vasilhame ou sacaria', 'Classificam-se neste código as remessas de vasilhame ou sacaria.'),
       ('6921', 'Devolução de vasilhame ou sacaria',
        'Classificam-se neste código as saídas por devolução de vasilhame ou sacaria.'),
       ('6922', 'Lançamento efetuado a título de simples faturamento decorrente de venda para entrega futura',
        'Classificam-se neste código os registros efetuados a título de simples faturamento decorrente de venda para entrega futura.'),
       ('6923', 'Remessa de mercadoria por conta e ordem de terceiros, em venda à ordem',
        'Classificam-se neste código as saídas correspondentes à entrega de mercadorias por conta e ordem de terceiros, em vendas à ordem, cuja venda ao adquirente originário, foi classificada nos códigos 6.118 - Venda de produção do estabelecimento entregue ao destinatário por conta e ordem do adquirente originário, em venda à ordem ou 6.119 - Venda de mercadoria adquirida ou recebida de terceiros entregue ao destinatário por conta e ordem do adquirente originário, em venda à ordem.'),
       ('6924',
        'Remessa para industrialização por conta e ordem do adquirente da mercadoria, quando esta não transitar pelo estabelecimento do adquirente',
        'Classificam-se neste código as saídas de insumos com destino a estabelecimento industrializador, para serem industrializados por conta e ordem do adquirente, nas hipóteses em que os insumos não tenham transitado pelo estabelecimento do adquirente dos mesmos.'),
       ('6925',
        'Retorno de mercadoria recebida para industrialização por conta e ordem do adquirente da mercadoria, quando aquela não transitar pelo estabelecimento do adquirente',
        'Classificam-se neste código as remessas, pelo estabelecimento industrializador, dos insumos recebidos, por conta e ordem do adquirente, para industrialização e incorporados ao produto final, nas hipóteses em que os insumos não tenham transitado pelo estabelecimento do adquirente. O valor dos insumos nesta operação deverá ser igual ao valor dos insumos recebidos para industrialização.'),
       ('6929',
        'Lançamento efetuado em decorrência de emissão de documento fiscal relativo a operação ou prestação também registrada em equipamento Emissor de Cupom Fiscal - ECF',
        'Classificam-se neste código os registros relativos aos documentos fiscais emitidos em operações ou prestações que também tenham sido registradas em equipamento Emissor de Cupom Fiscal - ECF.'),
       ('6931',
        'Lançamento efetuado em decorrência da responsabilidade de retenção do imposto por substituição tributária, atribuída ao remetente ou alienante da mercadoria, pelo serviço de transporte realizado por t',
        'Classificam-se neste código exclusivamente os lançamentos efetuados pelo remetente ou alienante da mercadoria quando lhe for atribuída a responsabilidade pelo recolhimento do imposto devido pelo serviço de transporte realizado por transportador autônomo ou por transportador não inscrito na unidade da Federação onde iniciado o serviço.'),
       ('6932',
        'Prestação de serviço de transporte iniciada em unidade da Federação diversa daquela onde inscrito o prestador',
        'Classificam-se neste código as prestações de serviço de transporte que tenham sido iniciadas em unidade da Federação diversa daquela onde o prestador está inscrito como contribuinte.'),
       ('6933', 'Prestação de serviço tributado pelo Imposto Sobre Serviços de Qualquer Natureza',
        'Prestação de serviço, cujo imposto   é de competência municipal, desde que informado em nota fiscal modelo 1 ou 1-A. (ACR Ajuste SINIEF 03/2004 e Ajuste SINIEF 06/2005) (DECRETO Nº 26.868/2006)'),
       ('6949', 'Outra saída de mercadoria ou prestação de serviço não especificado',
        'Classificam-se neste código as outras saídas de mercadorias ou prestações de serviços que não tenham sido especificados nos códigos anteriores.'),
       ('7000', 'SAÍDAS OU PRESTAÇÕES DE SERVIÇOS PARA O EXTERIOR',
        'Classificam-se, neste grupo, as operações ou prestações em que o destinatário esteja localizado em outro país'),
       ('7100', 'VENDAS DE PRODUÇÃO PRÓPRIA OU DE TERCEIROS', NULL),
       ('7101', 'Venda de produção do estabelecimento',
        'Venda de produto industrializado ou produzido pelo estabelecimento, bem como a de mercadoria por estabelecimento industrial ou produtor rural de cooperativa destinada a seus cooperados ou a estabelecimento de outra cooperativa.\r\n\r\n(NR Ajuste SINIEF 05/2005) (Dec.28.868/2006 - Efeitos a partir de 01/01/2006, ficando facultada ao contribuinte a sua adoção para fatos geradores ocorridos no período de 01 de novembro a 31 de dezembro de 2005)'),
       ('7102', 'Venda de mercadoria adquirida ou recebida de terceiros',
        'Classificam-se neste código as vendas de mercadorias adquiridas ou recebidas de terceiros para industrialização ou comercialização, que não tenham sido objeto de qualquer processo industrial no estabelecimento. Também serão classificadas neste código as vendas de mercadorias por estabelecimento comercial de cooperativa.'),
       ('7105', 'Venda de produção do estabelecimento, que não deva por ele transitar',
        'Classificam-se neste código as vendas de produtos industrializados no estabelecimento, armazenados em depósito fechado, armazém geral ou outro sem que haja retorno ao estabelecimento depositante.'),
       ('7106', 'Venda de mercadoria adquirida ou recebida de terceiros, que não deva por ele transitar',
        'Classificam-se neste código as vendas de mercadorias adquiridas ou recebidas de terceiros para industrialização ou comercialização, armazenadas em depósito fechado, armazém geral ou outro, que não tenham sido objeto de qualquer processo industrial no estabelecimento sem que haja retorno ao estabelecimento depositante. Também serão classificadas neste código as vendas de mercadorias importadas, cuja saída ocorra do recinto alfandegado ou da repartição alfandegária onde se processou o desembaraço aduaneiro, com destino ao esta'),
       ('7127', 'Venda de produção do estabelecimento sob o regime de drawback',
        'Classificam-se neste código as vendas de produtos industrializados no estabelecimento sob o regime de drawback , cujas compras foram classificadas no código 3.127 - Compra para industrialização sob o regime de drawback.'),
       ('7200', 'DEVOLUÇÕES DE COMPRAS PARA INDUSTRIALIZAÇÃO, COMERCIALIZAÇÃO OU ANULAÇÕES DE VALORES',
        '(NR Ajuste SINIEF 05/2005) (Dec.28.868/2006 - Efeitos a partir de 01/01/2006, ficando facultada ao contribuinte a sua adoção para fatos geradores ocorridos no período de 01 de novembro a 31 de dezembro de 2005)'),
       ('7201',
        'Devolução de compra para industrialização ou produção rural (NR Ajuste SINIEF 05/2005) (Decreto 28.868/2006)',
        'Devolução de mercadoria adquirida para ser utilizada em processo de industrialização ou produção rural, cuja entrada tenha sido classificada como \"1.101 - Compra para industrialização ou produção rural\".\r\n\r\n(NR Ajuste SINIEF 05/2005) (Dec.28.868/2006 - Efeitos a partir de 01/01/2006, ficando facultada ao contribuinte a sua adoção para fatos geradores ocorridos no período de 01 de novembro a 31 de dezembro de 2005)'),
       ('7202', 'Devolução de compra para comercialização',
        'Classificam-se neste código as devoluções de mercadorias adquiridas para serem comercializadas, cujas entradas tenham sido classificadas como Compra para comercialização.'),
       ('7205', 'Anulação de valor relativo à aquisição de serviço de comunicação',
        'Classificam-se neste código as anulações correspondentes a valores faturados indevidamente, decorrentes das aquisições de serviços de comunicação.'),
       ('7206', 'Anulação de valor relativo a aquisição de serviço de transporte',
        'Classificam-se neste código as anulações correspondentes a valores faturados indevidamente, decorrentes das aquisições de serviços de transporte.'),
       ('7207', 'Anulação de valor relativo à compra de energia elétrica',
        'Classificam-se neste código as anulações correspondentes a valores faturados indevidamente, decorrentes da compra de energia elétrica.'),
       ('7210', 'Devolução de compra para utilização na prestação de serviço',
        'Classificam-se neste código as devoluções de mercadorias adquiridas para utilização na prestação de serviços, cujas entradas tenham sido classificadas no código 3.126 - Compra para utilização na prestação de serviço.'),
       ('7211', 'Devolução de compras para industrialização sob o regime de drawback',
        'Classificam-se neste código as devoluções de mercadorias adquiridas para serem utilizadas em processo de industrialização sob o regime de drawback e não utilizadas no referido processo, cujas entradas tenham sido classificadas no código 3.127 - Compra para industrialização sob o regime de drawback.'),
       ('7250', 'VENDAS DE ENERGIA ELÉTRICA', NULL),
       ('7251', 'Venda de energia elétrica para o exterior',
        'Classificam-se neste código as vendas de energia elétrica para o exterior.'),
       ('7300', 'PRESTAÇÕES DE SERVIÇOS DE COMUNICAÇÃO', NULL),
       ('7301', 'Prestação de serviço de comunicação para execução de serviço da mesma natureza',
        'Classificam-se neste código as prestações de serviços de comunicação destinados às prestações de serviços da mesma natureza.'),
       ('7358', 'Prestação de serviço de transporte',
        'Classificam-se neste código as prestações de serviços de transporte destinado a estabelecimento no exterior.'),
       ('7500', 'EXPORTAÇÃO DE MERCADORIAS RECEBIDAS COM FIM ESPECÍFICO DE EXPORTAÇÃO', NULL),
       ('7501', 'Exportação de mercadorias recebidas com fim específico de exportação',
        'Classificam-se neste código as exportações das mercadorias recebidas anteriormente com finalidade específica de exportação, cujas entradas tenham sido classificadas nos códigos 1.501 - Entrada de mercadoria recebida com fim específico de exportação ou 2.501 - Entrada de mercadoria recebida com fim específico de exportação.'),
       ('7550', 'OPERAÇÕES COM BENS DE ATIVO IMOBILIZADO E MATERIAIS PARA USO OU CONSUMO', NULL),
       ('7551', 'Venda de bem do ativo imobilizado',
        'Classificam-se neste código as vendas de bens integrantes do ativo imobilizado do estabelecimento.'),
       ('7553', 'Devolução de compra de bem para o ativo imobilizado',
        'Classificam-se neste código as devoluções de bens adquiridos para integrar o ativo imobilizado do estabelecimento, cuja entrada foi classificada no código 3.551 - Compra de bem para o ativo imobilizado.'),
       ('7556', 'Devolução de compra de material de uso ou consumo',
        'Classificam-se neste código as devoluções de mercadorias destinadas ao uso ou consumo do estabelecimento, cuja entrada tenha sido classificada no código 3.556 - Compra de material para uso ou consumo.'),
       ('7650', 'SAÍDAS DE COMBUSTÍVEIS, DERIVADOS OU NÃO DE PETRÓLEO, E LUBRIFICANTES',
        '(a partir 01.01.2004 -  Decreto Nº 26.174 de 26/11/2003)'),
       ('7651', 'Venda de combustível ou lubrificante de produção do estabelecimento',
        'Venda de combustível ou lubrificante industrializados no estabelecimento e destinados ao exterior. (a partir 01.01.2004 -  Decreto Nº 26.174 de 26/11/2003)'),
       ('7654', 'Venda de combustível ou lubrificante adquiridos ou recebidos de terceiros',
        'Venda de combustível ou lubrificante, adquiridos ou recebidos de terceiros, destinados ao exterior. (a partir 01.01.2004 -  Decreto Nº 26.174 de 26/11/2003)'),
       ('7667', 'Venda de combustível ou lubrificante a consumidor ou usuário final',
        'Venda de combustível ou lubrificante a consumidor ou a usuário final, cuja operação tenha sido equiparada a uma exportação. ACR Ajuste SINIEF 05/2009 – a partir de 01.07.2009)(Decreto nº 34.490/2009)'),
       ('7900', 'OUTRAS SAIDAS DE MERCADORIA OU PRESTAÇÕES DE SERVIÇOS', NULL),
       ('7930',
        'Lançamento efetuado a título de devolução de bem cuja entrada tenha ocorrido sob amparo de regime especial aduaneiro de admissão temporária',
        'Classificam-se neste código os lançamentos efetuados a título de saída em devolução de bens cuja entrada tenha ocorrido sob amparo de regime especial aduaneiro de admissão temporária.'),
       ('7949', 'Outra saída de mercadoria ou prestação de serviço não especificado',
        'Classificam-se neste código as outras saídas de mercadorias ou prestações de serviços que não tenham sido especificados nos códigos anteriores.');

