CREATE TABLE usersDt
(
  id int IDENTITY (1, 1) PRIMARY KEY NOT NULL,
  name VARCHAR (128),
  username VARCHAR (128) unique,
  password VARCHAR (128),
  email VARCHAR (256)
)