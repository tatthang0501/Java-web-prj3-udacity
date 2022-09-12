create database prj3db;
use prj3db;
SELECT * FROM customer;
SELECT * FROM humanoid;
SELECT * FROM pet;

INSERT INTO pet(id, birth_date, name, notes, pet_type, customer_id)
VALUES (1, '2019-12-16', 'Kilo', 'HI KILOS', 'CAT', 1);

SELECT c.* FROM customer c
WHERE c.id = (
    SELECT p.customer_id
    FROM pet p
    WHERE p.id = 1);