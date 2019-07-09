DROP TABLE IF EXISTS addresses;
DROP TABLE IF EXISTS orders_foods;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS foods;
DROP TABLE IF EXISTS orders;

CREATE TABLE users(
    userid SMALLSERIAL PRIMARY KEY,
    firstname VARCHAR(30),
    lastname VARCHAR(30),
    phonenumber VARCHAR(30),
    email VARCHAR(30),
    username VARCHAR(30),
    password VARCHAR(30)
);

CREATE TABLE foods(
  foodid SMALLSERIAL PRIMARY KEY,
  name VARCHAR(30) NOT NULL,
  description VARCHAR(30),
  price FLOAT NOT NULL,
  image varchar(255),
  category varchar(30)
);

CREATE TABLE orders(
    orderid SMALLSERIAL PRIMARY KEY,
    total FLOAT NOT NULL,
    addressid SMALLINT NOT NULL,
    orderdate TIMESTAMP
);

CREATE TABLE orders_foods(
    ofid SMALLSERIAL PRIMARY KEY,
    orderid SMALLINT,
    foodid SMALLINT,
    quantity SMALLINT,
    FOREIGN KEY (orderid) REFERENCES orders(orderid),
    FOREIGN KEY (foodid) REFERENCES foods(foodid)
);

CREATE TABLE addresses(
  addressid SMALLSERIAL PRIMARY KEY,
  city VARCHAR(30),
  address VARCHAR(30),
  postal_code VARCHAR(30)
);

INSERT INTO users (firstname,lastname,phonenumber,email,username,password) VALUES
('Béla','Kiss','06707654321','bela@bela.hu', 'bela','bela'),
('Martin','Cserép','06701234567','martin@martin.hu', 'martin','martin');
