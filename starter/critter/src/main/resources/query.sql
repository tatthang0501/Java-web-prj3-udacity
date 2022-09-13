create database prj3db1;
use prj3db1;
SELECT * FROM customer;
SELECT * FROM employee;
SELECT * FROM pet;
SELECT * FROM days_available;
SELECT * FROM employee_skill;
INSERT INTO pet(id, birth_date, name, notes, pet_type, customer_id)
VALUES (1, '2019-12-16', 'Kilo', 'HI KILOS', 'CAT', 2);

SELECT c.id, u.name, c.phone_number, c.notes, p.id
FROM customer c INNER JOIN `user` u ON c.id = u.id
                INNER JOIN pet p ON c.id = p.customer_id
WHERE p.id = 1;

SELECT e.id, u.name, es.skills, da.days_available
FROM employee e INNER JOIN `user` u ON e.id = u.id
                INNER JOIN employee_skill es ON e.id = es.employee_id
                INNER JOIN days_available da ON e.id = da.employee_id
WHERE es.skills IN ('PETTING', 'FEEDING') AND da.days_available = 'FRIDAY';