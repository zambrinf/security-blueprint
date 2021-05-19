insert into myschema.tb_user (id, username, password, enabled, roles) values
('1', 'myuser', '$2y$12$ORiem4d72XZLJvHbRej6OOP.ZCODSrz6EcgzmDvA6A1LNcQCybPsi', true, 'ROLE_USER,ROLE_ADMIN') -- password: mypassword
ON CONFLICT DO NOTHING;