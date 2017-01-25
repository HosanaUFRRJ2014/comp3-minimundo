--sudo -u postgres psql -d postgres -f path/to/the/dump/file

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;


CREATE TABLE CONTRATOS
(
	id SERIAL,
	comissao float,
	valorContrato float,
	prazo float,
	primary key(id)
		
); 


CREATE TABLE AMBIENTES
(
	id SERIAL,
	numParedes int,
	numPortas int,
	metragem float,
	--id_contrato BIGINT REFERENCES CONTRATOS(id), --pode ser que seja long. Procurar long em sql
	id_contrato BIGINT,
	--talvez precise adicionar custo e tempo de entrega
	
	primary key(id)
	--private LinkedList<ItemVenda> itemsVendaAssociados = new LinkedList<ItemVenda>(); 
);


CREATE TABLE COMODOS
 (
  		id SERIAL,
		descricao VARCHAR(255) NOT NULL,
		tipo INT NOT NULL, 
		--id_comodo  BIGINT REFERENCES Comodos(id), --autoreferencia para indicar o comodo pai
		id_comodo  BIGINT,
		primary key(id),
		UNIQUE(descricao)
 );
 
 --ALTER TABLE COMODOS OWNER TO postgres;
 
CREATE TABLE ITENSVENDA
(
	id SERIAL,
	quantidade int,
	id_ambiente bigint,

	primary key(id)
);

CREATE TABLE MOBILIAS
(
	id SERIAL,
	descricao VARCHAR(255) NOT NULL,
	custoProducao float,
	tempoEntrega int, -- num semanas
--	id_itemVenda BIGINT REFERENCES ITENSVENDA(id),
	id_itemVenda BIGINT,
	
	--comodo tem mobilias associados a ele
	id_comodo BIGINT,
	
	primary key(id),
	UNIQUE(descricao)
		
);
--acho que deve ser alterada na associação de mobílas e comodos. Mais uma funcao em ComodoDAO envolvendo essa tabela
--Tabela representativa do relacionamento entre Cômodo e Mobília
CREATE TABLE COMMOB
(
	id_comodo BIGINT REFERENCES COMODOS(id),
	id_mobilia BIGINT REFERENCES MOBILIAS(id),
	primary key(id_comodo,id_mobilia)

);

--quando cada mobília tem um tipo de cômodo associado
CREATE TABLE TIPOCOMMOB
(
	id_mobilia BIGINT,
	tipo_comodo BIGINT,
	primary key(id_mobilia,tipo_comodo)

);


--CREATE TABLE COMODOSCOMPOSTOS
--(
-- 		id SERIAL NOT NULL,
--		descricao VARCHAR(255),
--		--tipoComodo INT NOT NULL, 
--		comodoComposto  varchar(255) REFERENCES ComodoCompostos(descricao),
--		primary key(descricao)
-- );
