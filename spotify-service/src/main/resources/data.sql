DROP TABLE IF EXISTS album;
DROP TABLE IF EXISTS artist;
DROP TABLE IF EXISTS track;

CREATE TABLE album(
    id_album BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_artist INT NOT NULL,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE artist(
    id_artist BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    genre VARCHAR(255),
    image VARCHAR(255),
    reproductions BIGINT
);

CREATE TABLE track(
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255),
  id_artist BIGINT,
  id_album BIGINT,
  reproductions BIGINT,
  duration INT
);

INSERT INTO album (id_album, id_artist, name) VALUES
(1, 1, 'El espiritu del vino'),
(2, 2, 'Ride the lightning'),
(3, 2, '... And justice for all'),
(4, 3, 'Texas flood');

INSERT INTO artist (id_artist, name, genre, image, reproductions) VALUES
 (1, 'Heroes del silencio', 'Rock', 'https://phantom-marca.unidadeditorial.es/dfc731b5cc59092c5d9d8d70b10e3cad/resize/1320/f/jpg/assets/multimedia/imagenes/2021/04/05/16176436962285.jpg', 1123),
 (2, 'Metallica','Heavy metal', 'https://www.futuro.cl/wp-content/uploads/2021/04/metallica-1983-mustaine-web-768x432.jpg', 50400),
 (3, 'Stevie Ray Vaughan', 'Blues', 'https://i1.wp.com/jessicakristie.com/wp-content/uploads/2012/02/StevieRayVaughan.jpg', 6826);

INSERT INTO track (id, name, id_artist, id_album, duration, reproductions) VALUES
 (1, 'La apariencia no es sincera', 1, 1, 421800, 1123),
 (2, 'Blackened', 2, 2, 385200, 8213),
 (3, '...And justice for all', 2, 3, 567600, 7413),
 (4, 'Eye of the Beholder', 2, 3, 375000, 1494),
 (5, 'One', 2, 3, 435600, 12087),
 (6, 'The Shortest Straw', 2, 3,381000, 5413),
 (7, 'Harvester of Sorrow', 2, 3, 327000, 6954),
 (8, 'The Frayed Ends of Sanity', 2, 3, 445800, 3413),
 (9, 'To Live Is to Die', 2, 3, 569400, 5413),
 (10, 'Dyers Eve', 2, 3, 308400, 3413),
 (11, 'Pride and Joy', 3, 4, 204000, 3413);


