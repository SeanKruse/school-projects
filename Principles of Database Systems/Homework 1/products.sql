CREATE DATABASE products;

\c products;

DROP TABLE IF EXISTS Products;

CREATE TABLE Products (
id SERIAL PRIMARY KEY,
descr VARCHAR(30) NOT NULL,
price FLOAT NOT NULL,
cond VARCHAR DEFAULT 'new'
);

CREATE OR REPLACE FUNCTION cond_check() 
    RETURNS TRIGGER 
    LANGUAGE PLPGSQL
AS $$
BEGIN
    IF UPPER(new.cond) <> 'USED' 
        THEN new.cond = 'new';
    END IF;
    RETURN new;
END;
$$; 
    
CREATE OR REPLACE TRIGGER prod_validate
    BEFORE INSERT 
    ON Products
    FOR EACH ROW
EXECUTE PROCEDURE cond_check();

--Testing Inserts

--INSERT INTO Products (descr, price, cond) VALUES ('Ninja Sword', 250, 'new');
--INSERT INTO Products (descr, price) VALUES ('Dummy', 50);
--INSERT INTO Products (descr, price, cond) VALUES ('Fake Blood', 5, 'used');
--INSERT INTO Products (descr, price, cond) VALUES ('Rubber Ducky', 1, 'used');
--INSERT INTO Products (descr, price, cond) VALUES ('Bathtub Soap', 3, 'used once');
--INSERT INTO Products (descr, price) VALUES ('Brazilian Coffee', 5);
--INSERT INTO Products (descr, price, cond) VALUES ('Running Shoes', 50, 'fair');

--Final Test

--SELECT * FROM Products;




