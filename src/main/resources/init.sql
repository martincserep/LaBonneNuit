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
  name VARCHAR(100) NOT NULL,
  price INT NOT NULL,
  image varchar(400),
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
('Fried shrimps',2480,'https://www.bbcgoodfood.com/sites/default/files/styles/recipe/public/recipe/recipe-image/2018/08/cajun-fried-shrimp.jpg?itok=e9Rzf_k5','appetizers'),
('Ramen',2780,'https://www.bbcgoodfood.com/sites/default/files/styles/recipe/public/recipe/recipe-image/2017/11/ramen.jpg?itok=whgIie33','soups'),
('Thai laksa soup',1780,'https://www.bbcgoodfood.com/sites/default/files/styles/recipe/public/recipe_images/recipe-image-legacy-id--837832_10.jpg?itok=VlFRLuJB','soups'),
('Miso soup',680,'https://www.bbcgoodfood.com/sites/default/files/styles/recipe/public/recipe_images/recipe-image-legacy-id--1091451_12.jpg?itok=qsmcxr8t','soups'),
('Buttermilk fried chicken',2980,'https://www.bbcgoodfood.com/sites/default/files/styles/recipe/public/recipe_images/southern-fried-chicken.jpg?itok=iJwfwvbq','main-dishes'),
('Moroccan-style barbecued leg of lamb',3580,'https://www.bbcgoodfood.com/sites/default/files/styles/recipe/public/recipe_images/recipe-image-legacy-id--684666_10.jpg?itok=m2Gm2dn6','main-dishes'),
('Twice-cooked pork belly with an onion & apple velouté',2720,'https://www.bbcgoodfood.com/sites/default/files/styles/recipe/public/recipe_images/recipe-image-legacy-id--151476_12.jpg?itok=wFQFxMaY','main-dishes'),
('Flambéed chicken with asparagus',2580,'https://www.bbcgoodfood.com/sites/default/files/styles/recipe/public/recipe_images/recipe-image-legacy-id--52847_12.jpg?itok=42e3D3QT','main-dishes'),
('Mango-Passion fruit roulade',1480,'https://www.bbcgoodfood.com/sites/default/files/styles/recipe/public/recipe_images/recipe-image-legacy-id--25529_12.jpg?itok=A6y7VfNE','desserts'),
('Belgian chocolate cake',1480,'https://www.bbcgoodfood.com/sites/default/files/styles/recipe/public/recipe_images/recipe-image-legacy-id--1043451_11.jpg?itok=Z_w2WOYB','desserts');
