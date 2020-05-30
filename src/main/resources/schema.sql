CREATE TABLE IF NOT EXISTS user (
  id 				VARCHAR(30) PRIMARY KEY,
  password 			VARCHAR(30) NOT NULL,
  nickname 			VARCHAR(30) NOT NULL,
  registeredDate	DATE NOT NULL
);

CREATE TABLE IF NOT EXISTS product (
  id 			INT IDENTITY PRIMARY KEY, 
  name 			VARCHAR(50) NOT NULL,
  category 		VARCHAR(50) NOT NULL,
  price 		INT NOT NULL,
  description 	VARCHAR(1000) NOT NULL,
  uploadDate 	DATETIME NOT NULL,
  updateDate	DATETIME,
  userId		VARCHAR(30) NOT NULL,
  location1 	VARCHAR(10) NOT NULL,
  location2 	VARCHAR(10) NOT NULL, 
  soldDate		DATETIME,
  image 		VARCHAR(255)
);

