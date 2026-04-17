-- StudyNest Database Seeder
-- This file loads modules and questions data

-- Load modules first
-- Then load questions

-- SEMESTER 2 MODULES (5 subjects)
INSERT INTO modules (name, semester, image_url, pdf_url, video_link, external_links) VALUES
('Engineering Mathematics II', 2, '/images/sem2/math2.png', 'https://drive.google.com/math2', 'https://youtu.be/math2', 'https://www.geeksforgeeks.org/engineering-mathematics/'),
('Physics', 2, '/images/sem2/physics.png', 'https://drive.google.com/physics', 'https://youtu.be/physics', 'https://www.geeksforgeeks.org/physics/'),
('Programming in C', 2, '/images/sem2/c-programming.png', 'https://drive.google.com/c-prog', 'https://youtu.be/c-prog', 'https://www.geeksforgeeks.org/c-programming-language/'),
('Digital Electronics', 2, '/images/sem2/digital.png', 'https://drive.google.com/digital', 'https://youtu.be/digital', 'https://www.geeksforgeeks.org/digital-electronics-logic-design-tutorials/'),
('Engineering Graphics', 2, '/images/sem2/graphics.png', 'https://drive.google.com/graphics', 'https://youtu.be/graphics', 'https://www.geeksforgeeks.org/engineering-graphics/');

-- SEMESTER 3 MODULES (5 subjects)
INSERT INTO modules (name, semester, image_url, pdf_url, video_link, external_links) VALUES
('Data Structures', 3, '/images/sem3/ds.png', 'https://drive.google.com/ds', 'https://youtu.be/ds', 'https://www.geeksforgeeks.org/data-structures/'),
('Discrete Mathematics', 3, '/images/sem3/dm.png', 'https://drive.google.com/dm', 'https://youtu.be/dm', 'https://www.geeksforgeeks.org/discrete-mathematics-tutorial/'),
('Computer Organization', 3, '/images/sem3/co.png', 'https://drive.google.com/co', 'https://youtu.be/co', 'https://www.geeksforgeeks.org/computer-organization-and-architecture-tutorials/'),
('Object Oriented Programming', 3, '/images/sem3/oop.png', 'https://drive.google.com/oop', 'https://youtu.be/oop', 'https://www.geeksforgeeks.org/object-oriented-programming-oops-concept-in-java/'),
('Database Management Systems', 3, '/images/sem3/dbms.png', 'https://drive.google.com/dbms', 'https://youtu.be/dbms', 'https://www.geeksforgeeks.org/dbms/');

-- SEMESTER 4 MODULES (5 subjects)
INSERT INTO modules (name, semester, image_url, pdf_url, video_link, external_links) VALUES
('Operating Systems', 4, '/images/sem4/os.png', 'https://drive.google.com/os', 'https://youtu.be/os', 'https://www.geeksforgeeks.org/operating-systems/'),
('Design and Analysis of Algorithms', 4, '/images/sem4/daa.png', 'https://drive.google.com/daa', 'https://youtu.be/daa', 'https://www.geeksforgeeks.org/fundamentals-of-algorithms/'),
('Computer Networks', 4, '/images/sem4/cn.png', 'https://drive.google.com/cn', 'https://youtu.be/cn', 'https://www.geeksforgeeks.org/computer-network-tutorials/'),
('Software Engineering', 4, '/images/sem4/se.png', 'https://drive.google.com/se', 'https://youtu.be/se', 'https://www.geeksforgeeks.org/software-engineering/'),
('Web Technologies', 4, '/images/sem4/web.png', 'https://drive.google.com/web', 'https://youtu.be/web', 'https://www.geeksforgeeks.org/web-development/');

-- SEMESTER 5 MODULES (5 subjects)
INSERT INTO modules (name, semester, image_url, pdf_url, video_link, external_links) VALUES
('Theory of Computation', 5, '/images/sem5/toc.png', 'https://drive.google.com/toc', 'https://youtu.be/toc', 'https://www.geeksforgeeks.org/theory-of-computation-automata-tutorials/'),
('Compiler Design', 5, '/images/sem5/cd.png', 'https://drive.google.com/cd', 'https://youtu.be/cd', 'https://www.geeksforgeeks.org/compiler-design-tutorials/'),
('Artificial Intelligence', 5, '/images/sem5/ai.png', 'https://drive.google.com/ai', 'https://youtu.be/ai', 'https://www.geeksforgeeks.org/artificial-intelligence/'),
('Computer Graphics', 5, '/images/sem5/cg.png', 'https://drive.google.com/cg', 'https://youtu.be/cg', 'https://www.geeksforgeeks.org/computer-graphics/'),
('Cryptography', 5, '/images/sem5/crypto.png', 'https://drive.google.com/crypto', 'https://youtu.be/crypto', 'https://www.geeksforgeeks.org/cryptography/');

-- SEMESTER 6 MODULES (5 subjects)
INSERT INTO modules (name, semester, image_url, pdf_url, video_link, external_links) VALUES
('Machine Learning', 6, '/images/sem6/ml.png', 'https://drive.google.com/ml', 'https://youtu.be/ml', 'https://www.geeksforgeeks.org/machine-learning/'),
('Cloud Computing', 6, '/images/sem6/cloud.png', 'https://drive.google.com/cloud', 'https://youtu.be/cloud', 'https://www.geeksforgeeks.org/cloud-computing/'),
('Big Data Analytics', 6, '/images/sem6/bigdata.png', 'https://drive.google.com/bigdata', 'https://youtu.be/bigdata', 'https://www.geeksforgeeks.org/big-data-analytics/'),
('Internet of Things', 6, '/images/sem6/iot.png', 'https://drive.google.com/iot', 'https://youtu.be/iot', 'https://www.geeksforgeeks.org/introduction-to-internet-of-things-iot-set-1/'),
('Cyber Security', 6, '/images/sem6/security.png', 'https://drive.google.com/security', 'https://youtu.be/security', 'https://www.geeksforgeeks.org/cyber-security/');
