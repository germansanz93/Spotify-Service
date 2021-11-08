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