DROP TABLE IF EXISTS reservations;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS alltable;

CREATE TABLE users(
    userid SMALLSERIAL PRIMARY KEY,
    firstname VARCHAR(30),
    lastname VARCHAR(30),
    phonenumber VARCHAR(30),
    email VARCHAR(30),
    username VARCHAR(30),
    password VARCHAR(30)
);

CREATE TABLE reservations(
    reservationid SMALLSERIAL PRIMARY KEY,
    customerid SMALLINT,
    reservation_date DATE NOT NULL DEFAULT CURRENT_DATE,
    accepted BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (customerid) REFERENCES users (userid)
);

CREATE TABLE alltable(
    tableid SMALLSERIAL PRIMARY KEY,
    numberofseats SMALLINT NOT NULL
);

INSERT INTO users (firstname,lastname,phonenumber,email,username,password) VALUES
('Béla','Kiss','06707654321','bela@bela.hu', 'bela','bela'),
('Martin','Cserép','06701234567','martin@martin.hu', 'martin','martin');
