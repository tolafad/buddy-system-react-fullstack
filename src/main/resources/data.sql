DROP TABLE IF EXISTS users;

CREATE TABLE users (
  id INT PRIMARY KEY,
  NAME VARCHAR(250) NOT NULL,
  EMAIL VARCHAR(250) NOT NULL,
  BUDDY_ID INT);

ALTER TABLE users ADD FOREIGN KEY(BUDDY_ID) REFERENCES users(id);

INSERT INTO users (ID, FIRST_NAME,EMAIL) VALUES
  (1, 'first', 'abc1@gmail.com'),
  (2, 'first', 'abc2@gmail.com'),
  (3, 'first', 'abc3@gmail.com'),
  (4, 'first', 'abc4@gmail.com'),
  (5, 'first', 'abc5@gmail.com'),
  (6, 'first', 'abc6@gmail.com'),
  (7, 'first', 'abc7@gmail.com'),
  (8, 'first', 'abc8@gmail.com');
  
