CREATE TABLE grupo (
  codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
  nome VARCHAR(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE permissao (
  codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
  nome VARCHAR(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE usuario (
  codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
  nome  VARCHAR(100) NOT NULL,
  email VARCHAR(50) NOT NULL,
  senha VARCHAR(100) NOT NULL,
  ativo BOOLEAN DEFAULT true,
  codigo_grupo BIGINT(20) NOT NULL,
  FOREIGN KEY (codigo_grupo) REFERENCES grupo (codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE grupo_permissoes (
  codigo_grupo BIGINT(20) NOT NULL,
  codigo_permissao BIGINT(20) NOT NULL,
  PRIMARY KEY (codigo_grupo, codigo_permissao),
  FOREIGN KEY (codigo_grupo) REFERENCES grupo (codigo),
  FOREIGN KEY (codigo_permissao) REFERENCES permissao (codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
