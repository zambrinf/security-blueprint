INSERT INTO my_users (username, password, enabled) VALUES
('jdbc_admin', '$2y$12$ttBT7x4RO8EtmnKoyvDdXueawbEmdB3Vjnm8yoeX5xx2mGTXljpTi', true), -- password BCRYPT: admin
('jdbc_user', '$2y$12$Ff4YhfBZh3IdmeNa5uwLZOlTX3I.rhMzWszd1DmRABN1pX2./izqG', true); -- password BCRYPT: user

INSERT INTO my_authorities (username, authority) VALUES
('jdbc_admin', 'ROLE_ADMIN'),
('jdbc_admin', 'ROLE_USER'),
('jdbc_user', 'ROLE_USER');