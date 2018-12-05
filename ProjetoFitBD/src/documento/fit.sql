-- Database: "fit"

-- DROP DATABASE "fit";

CREATE DATABASE "fit"
  WITH OWNER = postgres
       ENCODING = 'UTF8'
       TABLESPACE = pg_default
       LC_COLLATE = 'Portuguese_Brazil.1252'
       LC_CTYPE = 'Portuguese_Brazil.1252'
       CONNECTION LIMIT = -1;



-- Schema: fit_academia

-- DROP SCHEMA "fit_academia";

CREATE SCHEMA "fit_academia"
  AUTHORIZATION postgres;




-- Table: "fit_academia"."agenda_semanal"

-- DROP TABLE "fit_academia"."agenda_semanal";

CREATE TABLE "fit_academia"."agenda_semanal"
(
  cod_agenda serial NOT NULL,
  CONSTRAINT "agenda_semanal_pkey" PRIMARY KEY (cod_agenda)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE "fit_academia"."agenda_semanal"
  OWNER TO postgres;


-- Table: "fit_academia"."caixa"

-- DROP TABLE "fit_academia"."caixa";

CREATE TABLE "fit_academia"."caixa"
(
  cod_caixa integer NOT NULL,
  data_entrada date NOT NULL,
  data_saida date NOT NULL,
  valores_entrada double precision NOT NULL,
  valores_saida double precision NOT NULL,
  CONSTRAINT "caixa_pkey" PRIMARY KEY (cod_caixa)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE "fit_academia"."caixa"
  OWNER TO postgres;

-- Table: "fit_academia"."cliente"

-- DROP TABLE "fit_academia"."cliente";

CREATE TABLE "fit_academia"."cliente"
(
  cod_cliente serial NOT NULL,
  nome character varying(50) NOT NULL,
  data_nascimento date NOT NULL,
  cpf character varying(11) NOT NULL,
  id_agenda_client serial NOT NULL,
  CONSTRAINT "cliente_pkey" PRIMARY KEY (cod_cliente),
  CONSTRAINT "fk_idAgenda" FOREIGN KEY (id_agenda_client)
      REFERENCES "fit_academia"."agenda_semanal" (cod_agenda) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT
)
WITH (
  OIDS=FALSE
);
ALTER TABLE "fit_academia"."cliente"
  OWNER TO postgres;

-- Table: "fit_academia"."exercicio"

-- DROP TABLE "fit_academia"."exercicio";

CREATE TABLE "fit_academia"."exercicio"
(
  cod_exercicio serial NOT NULL,
  descricao character varying(100) NOT NULL,
  cod_agenda_exer serial NOT NULL,
  CONSTRAINT "exercicio_pkey" PRIMARY KEY (cod_exercicio),
  CONSTRAINT "fk_idAgenda" FOREIGN KEY (cod_agenda_exer)
      REFERENCES "fit_academia"."agenda_semanal" (cod_agenda) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT
)
WITH (
  OIDS=FALSE
);
ALTER TABLE "fit_academia"."exercicio"
  OWNER TO postgres;

-- Table: "fit_academia"."fornecedores"

-- DROP TABLE "fit_academia"."fornecedores";

CREATE TABLE "fit_academia"."fornecedores"
(
  cod_fornecedores serial NOT NULL,
  descricao character varying(50) NOT NULL,
  cpf_cnpj character varying(11) NOT NULL,
  CONSTRAINT "fornecedores_pkey" PRIMARY KEY (cod_fornecedores)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE "fit_academia"."fornecedores"
  OWNER TO postgres;

-- Table: "fit_academia"."mensalidade"

-- DROP TABLE "fit_academia"."mensalidade";

CREATE TABLE "fit_academia"."mensalidade"
(
  cod_mensalidade serial NOT NULL,
  data_vencimento date NOT NULL,
  data_pagamento date NOT NULL,
  valor double precision NOT NULL,
  id_client_mensa serial NOT NULL,
  CONSTRAINT "mensalidade_pkey" PRIMARY KEY (cod_mensalidade),
  CONSTRAINT "fk_id_cliente" FOREIGN KEY (id_client_mensa)
      REFERENCES "fit_academia"."cliente" (cod_cliente) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT,
  CONSTRAINT "mensalidade_idx_id_client_mensa01" UNIQUE (id_client_mensa)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE "fit_academia"."mensalidade"
  OWNER TO postgres;

-- Table: "fit_academia"."nota_fiscal"

-- DROP TABLE "fit_academia"."nota_fiscal";

CREATE TABLE "fit_academia"."nota_fiscal"
(
  cod_nf serial NOT NULL,
  cod_vendas_nf integer NOT NULL DEFAULT nextval('"fit_academia"."nota_fiscal_cod_vendas_seq"'::regclass),
  CONSTRAINT "nota_fiscal_pkey" PRIMARY KEY (cod_nf),
  CONSTRAINT "fk_id_vendas" FOREIGN KEY (cod_vendas_nf)
      REFERENCES "fit_academia"."vendas" (cod_vendas) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT
)
WITH (
  OIDS=FALSE
);
ALTER TABLE "fit_academia"."nota_fiscal"
  OWNER TO postgres;

-- Table: "fit_academia"."produto"

-- DROP TABLE "fit_academia"."produto";

CREATE TABLE "fit_academia"."produto"
(
  cod_produto integer NOT NULL DEFAULT nextval(('"fit_academia"."produto_cod_produto_seq"'::text)::regclass),
  descricao character varying(100) NOT NULL,
  CONSTRAINT "Produto_pkey" PRIMARY KEY (cod_produto)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE "fit_academia"."produto"
  OWNER TO postgres;

-- Table: "fit_academia"."produtos_e_vendas"

-- DROP TABLE "fit_academia"."produtos_e_vendas";

CREATE TABLE "fit_academia"."produtos_e_vendas"
(
  cod_vendas_pv serial NOT NULL,
  cod_produto_pv serial NOT NULL,
  CONSTRAINT "produtos_e_vendas_pkey" PRIMARY KEY (cod_vendas_pv, cod_produto_pv),
  CONSTRAINT "fk_id_produtos" FOREIGN KEY (cod_produto_pv)
      REFERENCES "fit_academia"."produto" (cod_produto) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT,
  CONSTRAINT "fk_id_vendas" FOREIGN KEY (cod_vendas_pv)
      REFERENCES "fit_academia"."vendas" (cod_vendas) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT
)
WITH (
  OIDS=FALSE
);
ALTER TABLE "fit_academia"."produtos_e_vendas"
  OWNER TO postgres;

-- Table: "fit_academia"."produtos_fornecedores"

-- DROP TABLE "fit_academia"."produtos_fornecedores";

CREATE TABLE "fit_academia"."produtos_fornecedores"
(
  cod_produtos_pf serial NOT NULL,
  cod_fornecedores_pf serial NOT NULL,
  CONSTRAINT "produtos_fornecedores_pkey" PRIMARY KEY (cod_produtos_pf, cod_fornecedores_pf),
  CONSTRAINT "fk_idfornecedores" FOREIGN KEY (cod_fornecedores_pf)
      REFERENCES "fit_academia"."fornecedores" (cod_fornecedores) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT,
  CONSTRAINT "fk_id_produtos" FOREIGN KEY (cod_produtos_pf)
      REFERENCES "fit_academia"."produto" (cod_produto) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT
)
WITH (
  OIDS=FALSE
);
ALTER TABLE "fit_academia"."produtos_fornecedores"
  OWNER TO postgres;

-- Table: "fit_academia"."vendas"

-- DROP TABLE "fit_academia"."vendas";

CREATE TABLE "fit_academia"."vendas"
(
  cod_vendas serial NOT NULL,
  descricao character varying(30) NOT NULL,
  cod_caixa_vendas integer NOT NULL,
  CONSTRAINT "vendas_pkey" PRIMARY KEY (cod_vendas),
  CONSTRAINT "fk_idCaixa" FOREIGN KEY (cod_caixa_vendas)
      REFERENCES "fit_academia"."caixa" (cod_caixa) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT
)
WITH (
  OIDS=FALSE
);
ALTER TABLE "fit_academia"."vendas"
  OWNER TO postgres;
