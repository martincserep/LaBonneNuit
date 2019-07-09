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

INSERT INTO foods (name, price, image, category) VALUES
('Fried shrimps',2480,'','appetizers'),
('Fried muslitos',1980,'','appetizers'),
('Breaded butterfly shrimps',1980,'','appetizers'),
('mozzarella wrapped in sous vide sirilion stick with BBQ sauce and chives',1680,'','appetizers'),
('Spicy fried edamame beans with garlic',1580,'','appetizers'),
('Ramen',2780,'','soups'),
('Thai laksa soup',1780,'','soups'),
('Miso soup',680,'','soups'),
('Pho bo',1980,'','soups'),
('Fried noodles with vegetables, green curry and almond crusted fried chicken breast',2980,'','main-dishes'),
('sous vide duck breas with peach and teriyaki sauce',3580,'','main-dishes'),
('Crispy squid rings with miso mustard sauce on green salad',2720,'','main-dishes'),
('Boppatsu',2580,'','main-dishes'),
('chocolate spring rolls with vanilla ice cream',1280,'','desserts'),
('Cripsy tortilla rolls with mango puree and white chocolate mousse',1280,'','desserts'),
('Almond coated cottage cheese balls with strawberry sauce',1360,'','desserts'),
('Mochi',1580,'','desserts'),
('Mango-Passion fruit cake',1480,'','desserts'),
('Belgian chocolate cake',1480,'','desserts');
