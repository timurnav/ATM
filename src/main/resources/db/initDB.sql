DROP TABLE IF EXISTS operations;
DROP TABLE IF EXISTS accounts;
DROP TABLE IF EXISTS users;

DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START 100000;

CREATE TABLE users
(
  id       INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  email    VARCHAR      NOT NULL,
  name     VARCHAR(255) NOT NULL,
  password VARCHAR      NOT NULL,
  role     VARCHAR(10)  NOT NULL,
  enabled  BOOL                DEFAULT TRUE

);
CREATE UNIQUE INDEX users_unique_email_idx ON users (email);

CREATE TABLE accounts
(
  id        INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  user_id   INTEGER DEFAULT 0  NOT NULL,
  number    VARCHAR            NOT NULL,
  pin       VARCHAR            NOT NULL,
  balance   INTEGER DEFAULT 0  NOT NULL,
  attempt   INTEGER DEFAULT 0  NOT NULL,
  date_time TIMESTAMP           DEFAULT now(),
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE operations
(
  id         INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  date_time  TIMESTAMP           DEFAULT now(),
  amount     NUMERIC DEFAULT 0  NOT NULL,
  account_id INTEGER DEFAULT 0  NOT NULL,
  opt_type   VARCHAR            NOT NULL,
  FOREIGN KEY (account_id) REFERENCES accounts (id) ON DELETE CASCADE
);
