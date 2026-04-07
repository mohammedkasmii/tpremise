CREATE TABLE Remise (
                        id INT AUTO_INCREMENT PRIMARY KEY,
                        montant_min DOUBLE NOT NULL,
                        montant_max DOUBLE NOT NULL,
                        taux DOUBLE NOT NULL
);