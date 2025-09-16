-- usuários
create table if not exists users (
  id bigserial primary key,
  email varchar(255) not null unique,
  nome  varchar(120),
  senha varchar(255) not null,
  role  varchar(50) not null default 'USER',
  created_at timestamp not null default now()
);

-- bilhetes
create table if not exists bilhetes (
  id bigserial primary key,
  user_id bigint not null references users(id),
  tipo varchar(20) not null,          -- VITALICIO | ANUAL | QUINQUENAL
  codigo varchar(64) not null unique, -- código único do bilhete
  valido_ate date,                     -- null para vitalício
  ativo boolean not null default true,
  created_at timestamp not null default now()
);

-- pagamentos
create table if not exists pagamentos (
  id bigserial primary key,
  user_id bigint not null references users(id),
  bilhete_id bigint references bilhetes(id),
  provedor varchar(40) not null,    -- MERCADO_PAGO | ...
  status varchar(30) not null,      -- PENDING | PAID | FAILED
  valor_centavos bigint not null,
  referencia varchar(128),          -- id externo do provedor
  payload jsonb,                    -- eventos/webhook
  created_at timestamp not null default now()
);

-- sorteios
create table if not exists sorteios (
  id bigserial primary key,
  referencia varchar(50) not null unique, -- ex: 2025-09-S01
  seed_publica varchar(128) not null,
  executado_em timestamp,
  created_at timestamp not null default now()
);

-- resultados
create table if not exists sorteio_resultados (
  id bigserial primary key,
  sorteio_id bigint not null references sorteios(id),
  bilhete_id bigint not null references bilhetes(id),
  premio_centavos bigint not null,
  publicado boolean not null default false,
  created_at timestamp not null default now()
);

create index if not exists idx_bilhetes_user on bilhetes(user_id);
create index if not exists idx_pagamentos_user on pagamentos(user_id);
create index if not exists idx_resultados_sorteio on sorteio_resultados(sorteio_id);
