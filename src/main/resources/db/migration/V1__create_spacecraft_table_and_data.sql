CREATE TABLE IF NOT EXISTS spacecrafts (
id BIGINT AUTO_INCREMENT PRIMARY KEY,
name VARCHAR(255) NOT NULL,
production_type VARCHAR(255) NOT NULL,
model VARCHAR(255) NOT NULL,
manufacturer VARCHAR(255) NOT NULL,
passengers INT NOT NULL
);

INSERT INTO spacecrafts(name, production_type, model, manufacturer, passengers) VALUES
('wing', 'Series', 'Model1', 'Manufacturer1', 5),
('x-wing', 'Movie', 'Model2', 'Manufacturer2', 10),
('Spacecraft3', 'Series', 'Model3', 'Manufacturer3', 8),
('Spacecraft4', 'Movie', 'Model4', 'Manufacturer4', 15),
('Spacecraft5', 'Series', 'Model5', 'Manufacturer5', 20),
('Spacecraft6', 'Movie', 'Model6', 'Manufacturer6', 25),
('Spacecraft7', 'Series', 'Model7', 'Manufacturer7', 30),
('Spacecraft8', 'Movie', 'Model8', 'Manufacturer8', 35),
('Spacecraft9', 'Series', 'Model9', 'Manufacturer9', 40),
('Spacecraft10', 'Movie', 'Model10', 'Manufacturer10', 45);
