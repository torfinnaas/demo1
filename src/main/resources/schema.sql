
CREATE TABLE tur_pakke (
  kode CHAR(2) NOT NULL UNIQUE,
  navn VARCHAR(50) NOT NULL
);

CREATE TABLE tur (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  tur_pakke_kode CHAR(2) NOT NULL,
  tittel VARCHAR(100) NOT NULL,
  beskrivelse VARCHAR(2000) NOT NULL,
  pris VARCHAR(10) not null,
  grad VARCHAR(16) NOT NULL
);
ALTER TABLE tur ADD FOREIGN KEY (tur_pakke_kode) REFERENCES tur_pakke(kode);


CREATE TABLE tur_rating (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    tur_id BIGINT,
    customer_id BIGINT,
    score INT,
    comment VARCHAR(100));

ALTER TABLE tur_rating ADD FOREIGN KEY (tur_id) REFERENCES tur(id);
ALTER TABLE tur_rating ADD UNIQUE MyConstraint (tur_id, customer_id);
