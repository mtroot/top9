DELETE FROM user_roles;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES
  ('Admin', 'admin@mail.ru', 'password'),
  ('User', 'user@mail.ru', '12345');

INSERT INTO user_roles (user_id, role)
VALUES
  (100000, 'ROLE_ADMIN'),
  (100001, 'ROLE_USER');
