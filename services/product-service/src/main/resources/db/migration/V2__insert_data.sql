
INSERT INTO category (id, description, name) VALUES (nextval('category_seq'), 'Electronics and gadgets', 'Electronics');
INSERT INTO category (id, description, name) VALUES (nextval('category_seq'), 'Home appliances and tools', 'Home');
INSERT INTO category (id, description, name) VALUES (nextval('category_seq'), 'Books and literature', 'Books');
INSERT INTO category (id, description, name) VALUES (nextval('category_seq'), 'Clothing and accessories', 'Clothing');
INSERT INTO category (id, description, name) VALUES (nextval('category_seq'), 'Sports and outdoor gear', 'Sports');

INSERT INTO public.product (id, description, name, available_quantity, price, category_id) VALUES
                                                                                               (nextval('product_seq'), 'Smartphone with 128GB storage', 'Smartphone', 50, 699.99, (SELECT id FROM category WHERE name='Electronics')),
                                                                                               (nextval('product_seq'), 'Laptop with 16GB RAM', 'Laptop', 30, 1299.99, (SELECT id FROM category WHERE name='Electronics')),
                                                                                               (nextval('product_seq'), 'Wireless headphones', 'Headphones', 75, 199.99, (SELECT id FROM category WHERE name='Electronics')),
                                                                                               (nextval('product_seq'), 'LED Smart TV 55 inches', 'Smart TV', 20, 499.99, (SELECT id FROM category WHERE name='Electronics')),
                                                                                               (nextval('product_seq'), 'Portable Bluetooth speaker', 'Speaker', 100, 79.99, (SELECT id FROM category WHERE name='Electronics')),
                                                                                               (nextval('product_seq'), 'Smartwatch with GPS', 'Smartwatch', 70, 149.99, (SELECT id FROM category WHERE name='Electronics')),
                                                                                               (nextval('product_seq'), 'Tablet with 10-inch display', 'Tablet', 40, 249.99, (SELECT id FROM category WHERE name='Electronics')),
                                                                                               (nextval('product_seq'), 'Gaming console', 'PlayStation 5', 20, 499.99, (SELECT id FROM category WHERE name='Electronics')),
                                                                                               (nextval('product_seq'), 'Digital camera', 'DSLR Camera', 15, 899.99, (SELECT id FROM category WHERE name='Electronics')),
                                                                                               (nextval('product_seq'), 'E-reader with Wi-Fi', 'E-reader', 60, 99.99, (SELECT id FROM category WHERE name='Electronics')),

                                                                                               (nextval('product_seq'), 'Vacuum cleaner with HEPA filter', 'Vacuum Cleaner', 25, 149.99, (SELECT id FROM category WHERE name='Home')),
                                                                                               (nextval('product_seq'), 'Air conditioner 1.5 Ton', 'Air Conditioner', 15, 399.99, (SELECT id FROM category WHERE name='Home')),
                                                                                               (nextval('product_seq'), 'Refrigerator double door', 'Refrigerator', 10, 599.99, (SELECT id FROM category WHERE name='Home')),
                                                                                               (nextval('product_seq'), 'Microwave oven 20L', 'Microwave', 40, 99.99, (SELECT id FROM category WHERE name='Home')),
                                                                                               (nextval('product_seq'), 'Washing machine 7kg', 'Washing Machine', 12, 499.99, (SELECT id FROM category WHERE name='Home')),
                                                                                               (nextval('product_seq'), 'Ceiling fan', 'Energy Efficient Fan', 80, 49.99, (SELECT id FROM category WHERE name='Home')),
                                                                                               (nextval('product_seq'), 'Water heater 10L', 'Water Heater', 25, 199.99, (SELECT id FROM category WHERE name='Home')),
                                                                                               (nextval('product_seq'), 'Sewing machine', 'Compact Sewing Machine', 15, 129.99, (SELECT id FROM category WHERE name='Home')),
                                                                                               (nextval('product_seq'), 'Blender and juicer', 'Kitchen Blender', 50, 49.99, (SELECT id FROM category WHERE name='Home')),
                                                                                               (nextval('product_seq'), 'Electric kettle', 'Kettle', 100, 29.99, (SELECT id FROM category WHERE name='Home')),

                                                                                               (nextval('product_seq'), 'Mystery novel', 'The Silent Patient', 200, 14.99, (SELECT id FROM category WHERE name='Books')),
                                                                                               (nextval('product_seq'), 'Science fiction book', 'Dune', 150, 24.99, (SELECT id FROM category WHERE name='Books')),
                                                                                               (nextval('product_seq'), 'Self-help book', 'Atomic Habits', 250, 19.99, (SELECT id FROM category WHERE name='Books')),
                                                                                               (nextval('product_seq'), 'History book', 'Sapiens', 100, 29.99, (SELECT id FROM category WHERE name='Books')),
                                                                                               (nextval('product_seq'), 'Cookbook', 'The Joy of Cooking', 90, 39.99, (SELECT id FROM category WHERE name='Books')),
                                                                                               (nextval('product_seq'), 'Fantasy novel', 'The Hobbit', 120, 14.99, (SELECT id FROM category WHERE name='Books')),
                                                                                               (nextval('product_seq'), 'Poetry collection', 'Milk and Honey', 200, 12.99, (SELECT id FROM category WHERE name='Books')),
                                                                                               (nextval('product_seq'), 'Biography', 'Steve Jobs by Walter Isaacson', 90, 19.99, (SELECT id FROM category WHERE name='Books')),
                                                                                               (nextval('product_seq'), 'Business book', 'Good to Great', 150, 24.99, (SELECT id FROM category WHERE name='Books')),
                                                                                               (nextval('product_seq'), 'Travel guide', 'Lonely Planet Japan', 50, 29.99, (SELECT id FROM category WHERE name='Books')),

                                                                                               (nextval('product_seq'), 'Men T-shirt', 'Basic T-shirt', 300, 9.99, (SELECT id FROM category WHERE name='Clothing')),
                                                                                               (nextval('product_seq'), 'Women dress', 'Summer Dress', 150, 29.99, (SELECT id FROM category WHERE name='Clothing')),
                                                                                               (nextval('product_seq'), 'Men jeans', 'Slim Fit Jeans', 200, 39.99, (SELECT id FROM category WHERE name='Clothing')),
                                                                                               (nextval('product_seq'), 'Women handbag', 'Leather Handbag', 50, 79.99, (SELECT id FROM category WHERE name='Clothing')),
                                                                                               (nextval('product_seq'), 'Men sneakers', 'Running Shoes', 100, 49.99, (SELECT id FROM category WHERE name='Clothing')),
                                                                                               (nextval('product_seq'), 'Women sandals', 'Flat Sandals', 200, 19.99, (SELECT id FROM category WHERE name='Clothing')),
                                                                                               (nextval('product_seq'), 'Men jacket', 'Winter Jacket', 80, 89.99, (SELECT id FROM category WHERE name='Clothing')),
                                                                                               (nextval('product_seq'), 'Unisex sunglasses', 'Polarized Sunglasses', 120, 29.99, (SELECT id FROM category WHERE name='Clothing')),
                                                                                               (nextval('product_seq'), 'Women scarf', 'Wool Scarf', 150, 14.99, (SELECT id FROM category WHERE name='Clothing')),
                                                                                               (nextval('product_seq'), 'Men watch', 'Analog Watch', 100, 99.99, (SELECT id FROM category WHERE name='Clothing')),

                                                                                               (nextval('product_seq'), 'Football', 'Professional Football', 100, 19.99, (SELECT id FROM category WHERE name='Sports')),
                                                                                               (nextval('product_seq'), 'Tennis racket', 'Graphite Racket', 50, 89.99, (SELECT id FROM category WHERE name='Sports')),
                                                                                               (nextval('product_seq'), 'Camping tent', '4-Person Tent', 30, 129.99, (SELECT id FROM category WHERE name='Sports')),
                                                                                               (nextval('product_seq'), 'Yoga mat', 'Anti-slip Yoga Mat', 200, 19.99, (SELECT id FROM category WHERE name='Sports')),
                                                                                               (nextval('product_seq'), 'Mountain bike', '21-Speed Bike', 15, 499.99, (SELECT id FROM category WHERE name='Sports')),
                                                                                               (nextval('product_seq'), 'Cricket bat', 'English Willow Bat', 40, 149.99, (SELECT id FROM category WHERE name='Sports')),
                                                                                               (nextval('product_seq'), 'Swimming goggles', 'Anti-fog Goggles', 200, 14.99, (SELECT id FROM category WHERE name='Sports')),
                                                                                               (nextval('product_seq'), 'Fitness tracker', 'Fitness Band', 100, 49.99, (SELECT id FROM category WHERE name='Sports')),
                                                                                               (nextval('product_seq'), 'Hiking boots', 'Waterproof Boots', 30, 129.99, (SELECT id FROM category WHERE name='Sports')),
                                                                                               (nextval('product_seq'), 'Fishing rod', 'Telescopic Rod', 20, 69.99, (SELECT id FROM category WHERE name='Sports'));
