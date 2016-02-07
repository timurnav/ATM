DROP TABLE ACCOUNTS
IF EXISTS;
DROP TABLE OPERATIONS
IF EXISTS;
DROP SEQUENCE GLOBAL_SEQ
IF EXISTS;

CREATE SEQUENCE GLOBAL_SEQ
  AS INTEGER
    START WITH 100000;

CREATE TABLE accounts
(
  id        INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
  number    VARCHAR(255) NOT NULL,
  pin       VARCHAR(255) NOT NULL,
  balance   INTEGER DEFAULT 0,
  attempt   INTEGER DEFAULT 0,
  date_time TIMESTAMP
);

CREATE TABLE operations
(
  id         INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
  date_time  TIMESTAMP,
  amount     NUMERIC      NOT NULL,
  account_id INTEGER      NOT NULL,
  opt_type   VARCHAR(255) NOT NULL,
  FOREIGN KEY ( account_id ) REFERENCES ACCOUNTS (id)
    ON DELETE CASCADE
);







