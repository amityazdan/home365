DROP ALL OBJECTS;

CREATE TABLE destination (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  latitude DOUBLE NOT NULL,
  longitude DOUBLE NOT NULL
);

CREATE TABLE airline (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  balance DECIMAL NOT NULL,
  created_date TIMESTAMP NOT NULL,
  home_base_id INT references destination(id)
);


CREATE TABLE aircraft (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  price INT NOT NULL,
  max_distance INT
);


CREATE TABLE airline_aircraft (
  id INT AUTO_INCREMENT PRIMARY KEY,
  airline_id INT references airline(id) ON DELETE CASCADE,
  aircraft_id INT references aircraft(id),
  bought_date TIMESTAMP NOT NULL
);


INSERT INTO destination (name, latitude, longitude) VALUES
  ('Ben Gurion', '32.004049', '34.886439'),
  ('Total Rf Heliport', '-74.93360137939453','40.07080078125'),
  ('Paris-Charles De Gaulle', '49.016189','2.540175'),
  ('Flying H Farms Airport', '-87.54139709472656','37.79169845581055');

INSERT INTO airline (name, balance, home_base_id, created_date) VALUES
  ('el-al', 1000000000, 1,'2019-10-17 12:19:20.288152'),
  ('delta airlines', 10000000000, 1,'2019-10-17 12:19:20.288152');

INSERT INTO aircraft (name, price, max_distance) VALUES
  ('boeing 777', 244700000, 20000),
  ('boeing 737', 90000000, 7084),
  ('boeing 747', 386000000,15000),
  ('boeing 727', 586000000,10000);


INSERT INTO airline_aircraft (airline_id, aircraft_id, bought_date) VALUES
  (1, 2,'2019-10-17 14:26:06.80232'),
  (2, 3,'2019-10-17 14:26:06.80232'),
  (2, 1,'2019-10-17 14:26:06.80232'),
  (2, 1,'2019-10-17 14:26:06.80232');
