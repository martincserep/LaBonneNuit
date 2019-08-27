DROP TABLE IF EXISTS cart CASCADE;
DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS foods CASCADE;
DROP TABLE IF EXISTS orders CASCADE;

CREATE TABLE users(
                      userid SMALLSERIAL PRIMARY KEY,
                      firstname VARCHAR(30),
                      lastname VARCHAR(30),
                      phonenumber VARCHAR(30),
                      email VARCHAR(30),
                      username VARCHAR(30),
                      password VARCHAR(30),
                      userrole VARCHAR(20),
                      city VARCHAR(30),
                      address VARCHAR(30),
                      postalcode VARCHAR(30)
);

CREATE TABLE foods(
                      foodid SMALLSERIAL PRIMARY KEY,
                      name VARCHAR(100) NOT NULL,
                      price INT NOT NULL,
                      image varchar(400),
                      category varchar(30)
);

CREATE TABLE cart(
                     userid SMALLINT,
                     foodid SMALLINT,
                     quantity SMALLINT DEFAULT 1,
                     price smallint,
                     FOREIGN KEY (userid) REFERENCES users(userid),
                     FOREIGN KEY (foodid) REFERENCES foods(foodid)
);

CREATE TABLE orders(
                       orderid SMALLSERIAL PRIMARY KEY,
                       userid SMALLINT NOT NULL,
                       total FLOAT NOT NULL,
                       addressid SMALLINT NOT NULL,
                       orderdate TIMESTAMP
);

CREATE TABLE ordersdetail (
                       id SMALLSERIAL PRIMARY KEY,
                       productid SMALLINT NOT NULL,
                       quantity SMALLINT NOT NULL DEFAULT 1,
                       unitcost FLOAT NOT NULL
);

INSERT INTO users (firstname,lastname,phonenumber,email,username,password,userrole) VALUES
('Béla','Kiss','06707654321','bela@bela.hu', 'bela','bela','EMPLOYEE'),
('Sándor','Nagy','06707654321','sanyi@sanyi.hu', 'sanyi','sanyi','CUSTOMER'),
('Martin','Cserép','06701234567','martin@martin.hu', 'martin','martin','MANAGER');

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
