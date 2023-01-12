
--- Initial users
INSERT INTO users VALUES (
    nextval('user_id_sequence'), 'admin', '$2a$10$dWarH285/FAC/17aPQ3vGOWpuQCftm.F1GR8wzJievsWBlecuXpKy', 'ACTIVE', 'CLIENT:MODERATOR:ADMINISTRATOR', '2022-12-15'
);
INSERT INTO users VALUES (
    nextval('user_id_sequence'), 'client', '$2a$10$dWarH285/FAC/17aPQ3vGOWpuQCftm.F1GR8wzJievsWBlecuXpKy', 'ACTIVE', 'CLIENT', '2022-12-15'
);

--- Initial products

INSERT INTO products VALUES (
    nextval('product_id_sequence'),
    'Cereal from WALMART', 100, 'GRAM', 379, 68, 7, 13
);
INSERT INTO products VALUES (
    nextval('product_id_sequence'),
    'Red apple', 100, 'GRAM', 59, 14.1, 0.2, 0.3
);
INSERT INTO products VALUES (
    nextval('product_id_sequence'),
    'Coca-Cola (zero)', 100, 'MILLI', 20, 5, 0, 0.1
);