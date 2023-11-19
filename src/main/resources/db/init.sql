USE moodplant;

# INSERT INTO plantDto
#     (description, image, name, price)
# VALUES
#     ('', '', 'Aloe Vera', 25000),
#     ('','', 'Spider Plant', 12000),
#     ('','','Rubber Plant', 24000),
#     ('','','Fiddle Leaf Fig', 18000),
#     ('','','Philodendron', 24000),
#     ('','','Boston Fern', 20000),
#     ('','','Chinese Evergreen', 12000),
#     ('','','ZZ Plant', 24000),
#     ('','','Orchid', 29000),
#     ('','','Pothos',33000),
#     ('','','Jade Plant', 12000),
#     ('','','Monstera', 30000),
#     ('','','Bamboo Palm', 12000),
#     ('','','Areca Palm', 24000),
#     ('','','Yucca', 29000),
#     ('','','Dracaena', 18000),
#     ('','','Schefflera', 20000),
#     ('','','Croton', 30000),
#     ('','','Bird of Paradise', 19000),
#     ('','','Anthurium', 20000),
#     ('','','Calathea', 23000),
#     ('','','Maidenhair Fern', 17000),
#     ('','','Cast Iron Plant', 30000),
#     ('','','Aglaonema', 24000),
#     ('','','Ivy', 20000),
#     ('','','Parlor Palm', 29000),
#     ('','','String of Pearls', 30000),
#     ('','','Prayer Plant', 23000),
#     ('','','Asparagus Fern', 17000),
#     ('','','Dieffenbachia', 30000),
#     ('','','Heartleaf Philodendron', 19000),
#     ('','','Swiss Cheese Plant', 23000),
#     ('','','Kentia Palm', 24000),
#     ('','','English Ivy', 20000),
#     ('','','Lucky Bamboo', 19000),
#     ('','','Cactus', 29000),
#     ('','','Ponytail Palm', 20000),
#     ('','','Radiator Plant', 19000),
#     ('','','African Violet', 20000),
#     ('','','Hoya', 23000),
#     ('','','Ficus', 18000),
#     ('','','Poinsettia', 17000),
#     ('','','Christmas Cactus', 29000),
#     ('','','Norfolk Island pine', 18000)
# ;

INSERT INTO theme
    (`value`)
VALUE
    ('Spring Renewal'),
    ('Summer Vibrance'),
    ('Autumn Harvest'),
    ('Winter Solace'),
    ('Christmas Cheer'),
    ('New Year Fresh Start'),
    ('Valentine''s Romance'),
    ('Winter Wonderland')
;

INSERT INTO voice_and_tone
    (`value`)
VALUES
    ('Soothing'),
    ('Energetic'),
    ('Professional'),
    ('Bold'),
    ('Sophisticated'),
    ('Trendy'),
    ('Welcoming'),
    ('Classic'),
    ('Adaptable'),
    ('Minimalist'),
    ('Elegant'),
    ('Playful'),
    ('Calming'),
    ('Architectural'),
    ('Confident'),
    ('Artistic'),
    ('Chic'),
    ('Friendly')
;

INSERT INTO space_condition
    (`value`)
VALUES
    ('Hardwood Companion'),
    ('High Humidity Thrivers'),
    ('Low Light Tolerant'),
    ('Pet-Friendly'),
    ('Air-Purifying'),
    ('Drought-Resistant'),
    ('Small Spaces'),
    ('Large Spaces'),
    ('Minimalist Decor'),
    ('Tropical Vibes'),
    ('Desk Plants'),
    ('Hanging Varieties'),
    ('Feng Shui Plants'),
    ('Colorful Foliage'),
    ('Fragrant')
;
