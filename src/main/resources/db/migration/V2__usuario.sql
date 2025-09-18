-- Tabela de usuários do sistema
create table if not exists usuario (
  id bigserial primary key,
  nome varchar(120) not null,
  email varchar(255) not null unique,
  created_at timestamptz not null default now(),
  updated_at timestamptz not null default now()
);

create index if not exists idx_usuario_email on usuario (email);
