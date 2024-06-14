INSERT INTO products (product_name, price, description, availability) 
VALUES ('Product1', 324.5, 'Description for Product1', 'Y' );
INSERT INTO products ( product_name, price, description, availability) 
VALUES ('Product2', 93.1, 'Description for Product2', 'Y' );
INSERT INTO products ( product_name, price, description, availability) 
VALUES ('Product3', 724.7, 'Description for Product3', 'N' );
INSERT INTO products (product_name, price, description, availability) 
VALUES ('Product4', 510, 'Description for Product4', 'Y' );

INSERT INTO orders (order_number, order_date) 
VALUES ('Order1000', CURRENT_DATE);
INSERT INTO orders (order_number, order_date) 
VALUES ('Order1001', CURRENT_DATE);
INSERT INTO orders (order_number, order_date) 
VALUES ('Order1002', CURRENT_DATE);

INSERT INTO order_product (order_id, product_id) 
VALUES (1, 2);
INSERT INTO order_product (order_id, product_id) 
VALUES (1, 3);
INSERT INTO order_product (order_id, product_id) 
VALUES (2, 3);
