-- Multiline INSERT is NOT working
INSERT INTO category (name) VALUES ('On a budget');
INSERT INTO category (name) VALUES ('Luxury');
INSERT INTO category (name) VALUES ('Food and drink');
INSERT INTO category (name) VALUES ('Country side');
INSERT INTO category (name) VALUES ('History');


INSERT INTO season (name) VALUES ('Spring');
INSERT INTO season (name) VALUES ('Summer');
INSERT INTO season (name) VALUES ('Autumn');
INSERT INTO season (name) VALUES ('Winter');


INSERT INTO destination(name) VALUES ('Africa');
INSERT INTO destination(name) VALUES ('Antarctica');
INSERT INTO destination(name) VALUES ('Asia');
INSERT INTO destination(name) VALUES ('Australia');
INSERT INTO destination(name) VALUES ('Europe');
INSERT INTO destination(name) VALUES ('North America');
INSERT INTO destination(name) VALUES ('South America');


INSERT INTO place(title, description, min_price, max_price, weather, destination_id, season_id, image_src) VALUES ('Oman', 'Oman bursts into bloom every March, as the Damask roses that cover Jebel Akhdar (also known as the Green Mountain) begin to flower. Watch the sunset than from its aptly named Rose Lounge. What it does boast, with its rich heritage and society.', 100, 200, '15', 3, 4, 'oman.jpg');
INSERT INTO place(title, description, min_price, max_price, weather, destination_id, season_id, image_src) VALUES ('San Diego', 'Sunny San Diego has long drawn visitors with its taquerias and beaches—it''s home to some of the best in the state. And with 12 to 13 hours of spring daylight, you’ll have plenty of time to explore. Summers are warm, but not crushingly hot like other.', 150, 200, '25', 6, 1, 'san_diego.jpg');
INSERT INTO place(title, description, min_price, max_price, weather, destination_id, season_id, image_src) VALUES ('Machu Picchu, Perú', 'The sacred city of Machu Picchu was built in the 15th century and later abandoned. Probably the most famous archaeological places in South America, this Incan citadel is famous worldwide for its huge walls. The old town offers an array of architecture.', 430, 500, '15', 7, 3, 'machu.jpg');
INSERT INTO place(title, description, min_price, max_price, weather, destination_id, season_id, image_src) VALUES ('Maldives', 'There’s a reason this island cluster in the Indian Ocean has become a honeymoon mainstay: it offers year-round temperatures hovering in the low 80s. and rarely suffers from rain outside monsoon season. It is considered a part of South Asia.', 200, 250, '30', 3, 2, 'maldives.jpg');
INSERT INTO place(title, description, min_price, max_price, weather, destination_id, season_id, image_src) VALUES ('Cartagena', 'March is one of the hottest, driest months in Cartagena, but it’s the ideal climate for a trip to this oceanfront colonial-era hub, which offers a handy two-fer as both city break and beach getaway. Cartagena the home of wonderful art and literature.', 300, 350, '25', 3, 1, 'colombia.jpg');
INSERT INTO place(title, description, min_price, max_price, weather, destination_id, season_id, image_src) VALUES ('Crete', 'Of all the Greek islands, Crete is one of the most distinctive—not only because it’s the largest, but also because it has its own unmistakable culture. Trek the roughly 10-mile Samaria Gorge in southwest Crete. Home of the most enduring Greek legends.', 220, 300, '20', 3, 1, 'crete.jpg');
INSERT INTO place(title, description, min_price, max_price, weather, destination_id, season_id, image_src) VALUES ('Dubai', 'March brings postcard-perfect weather and two destination festivals to Dubai this year. Travelers can explore the Dubai Food Festival, which includes offerings like Dubai Restaurant Week and Beach Canteen. Situated in the Arabian Peninsula.', 300, 400, '25', 3, 1, 'dubai.jpg');
INSERT INTO place(title, description, min_price, max_price, weather, destination_id, season_id, image_src) VALUES ('Egypt', 'This 9-day Egypt tour serves up all the main Pharaonic highlights in and around Cairo and the Nile Valley as well as a relaxing overnight felucca sailboat cruise on the River Nile and time by the Red Sea. See the great Egypt pyramids.', 50, 100, '30', 1, 2, 'egypt.jpg');
INSERT INTO place(title, description, min_price, max_price, weather, destination_id, season_id, image_src) VALUES ('Zambia and Zimbabwe', 'Head to these two countries to see Victoria Falls, the largest waterfalls in the world. Plus, you can go white water rafting down the Zambezi river, chock-full of crocs and hippos. The most straightforward way to reach Victoria Falls is by plane', 110, 150, '25', 1, 3, 'zambia.jpg');
INSERT INTO place(title, description, min_price, max_price, weather, destination_id, season_id, image_src) VALUES ('Brasov, Romania', 'What would a trip to the heart of Transylvania be without experiencing some of Eastern Europe’s finest nature? Brașov is Romania’s gateway to the rural countryside of the Carpathian Mountains and all its outdoor activities such as hiking, camping.', 30, 80, '25', 5, 2, 'brasov.jpg');
INSERT INTO place(title, description, min_price, max_price, weather, destination_id, season_id, image_src) VALUES ('Batumi, Georgia', 'Georgia’s second largest city is yet to really be discovered. A charmingly quaint beach town, Batumi is a city filled with contrasts. An authentic old town comes alive with a booming street art scene, while 19th-century architecture and skyscrapers.', 60, 70, '20', 3, 3, 'georgia.jpg');
INSERT INTO place(title, description, min_price, max_price, weather, destination_id, season_id, image_src) VALUES ('Antarctic Archipelago', 'Many small islands are found off the Antarctic Peninsula – some more accessible than others. Cuverville Island is a breeding ground for gentoo penguins and brown skuas. Popular with Adelie and gentoo penguins is Petermann Island.', 80, 150, '-10', 2, 4, 'antarctic.gif');
INSERT INTO place(title, description, min_price, max_price, weather, destination_id, season_id, image_src) VALUES ('Perth, Australia','The capital of Western Australia, Perth is very isolated from the rest of the country, yet is routinely considered one of the most livable cities in the world thanks to its laidback vibe, fantastic modern cultural sites.', 300, 400, '20', 4, 1, 'perth.jpg');


INSERT INTO place_category(place_id, category_id) VALUES (1, 4);
INSERT INTO place_category(place_id, category_id) VALUES (2, 5);
INSERT INTO place_category(place_id, category_id) VALUES (3, 5);
INSERT INTO place_category(place_id, category_id) VALUES (4, 2);
INSERT INTO place_category(place_id, category_id) VALUES (5, 3);
INSERT INTO place_category(place_id, category_id) VALUES (6, 2);
INSERT INTO place_category(place_id, category_id) VALUES (7, 2);
INSERT INTO place_category(place_id, category_id) VALUES (8, 5);
INSERT INTO place_category(place_id, category_id) VALUES (9, 3);
INSERT INTO place_category(place_id, category_id) VALUES (10, 4);
INSERT INTO place_category(place_id, category_id) VALUES (11, 1);
INSERT INTO place_category(place_id, category_id) VALUES (12, 1);
INSERT INTO place_category(place_id, category_id) VALUES (13, 2);

INSERT INTO users(username, password, enabled, email) VALUES('userHello', '$2a$10$/YvXuJlS3PfHKUts3YKWHO/XsXduM2iEbLDInKbMohgVgs/Fs7H9G', 1, 'test@mail.com');
INSERT INTO users(username, password, enabled, email) VALUES('admin', '$2a$10$8KgbuwYGA3a00mkemz0krOI5hd3d.Gh83rrj3E2Bcqgv6I816CB8u', 1, 'some@mail.com');

INSERT INTO authorities(username, authority) VALUES('userHello', 'HELLO');
INSERT INTO authorities(username, authority) VALUES('admin', 'HELLO');
INSERT INTO authorities(username, authority) VALUES('admin', 'ADMIN');
