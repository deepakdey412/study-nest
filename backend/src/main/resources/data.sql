-- Sample Modules
INSERT INTO modules (name, semester, image_url, pdf_url, video_link, external_links) VALUES
('Computer Networks', 6, '/images/computer-networks.png', 'https://drive.google.com/sample1', 'https://youtu.be/VwN91x5i25g', 'https://www.geeksforgeeks.org/computer-network-tutorials/'),
('Compiler Design', 6, '/images/compiler-design.png', 'https://drive.google.com/sample2', 'https://youtu.be/XUsw5igq4DM', 'https://www.geeksforgeeks.org/compiler-design-tutorials/'),
('Machine Learning', 6, '/images/machine-learning.png', 'https://drive.google.com/sample3', 'https://youtu.be/ZftI2fEz0Fw', 'https://www.geeksforgeeks.org/machine-learning/'),
('Data Structures', 3, '/images/data-structures.png', 'https://drive.google.com/sample4', 'https://youtu.be/sample', 'https://www.geeksforgeeks.org/data-structures/'),
('Operating Systems', 4, '/images/operating-systems.png', 'https://drive.google.com/sample5', 'https://youtu.be/sample', 'https://www.geeksforgeeks.org/operating-systems/');

-- Sample Questions for Computer Networks (Module 1)
INSERT INTO questions (question_text, option1, option2, option3, option4, correct_answer, module_id) VALUES
('What does TCP stand for?', 'Transmission Control Protocol', 'Transfer Control Protocol', 'Transitional Control Protocol', 'Transport Connection Protocol', 'Transmission Control Protocol', 1),
('Which device operates at the network layer?', 'Router', 'Switch', 'Hub', 'Repeater', 'Router', 1),
('Which protocol is used to assign IP addresses?', 'HTTP', 'IP', 'DHCP', 'DNS', 'DHCP', 1),
('What is the function of DNS?', 'Encrypt data', 'Resolve IP addresses to domain names', 'Route data packets', 'Create firewalls', 'Resolve IP addresses to domain names', 1),
('What is the full form of HTTP?', 'HyperText Transfer Protocol', 'Hyper Terminal Transfer Protocol', 'High Transfer Text Protocol', 'Hypertext Text Transfer Protocol', 'HyperText Transfer Protocol', 1);

-- Sample Questions for Compiler Design (Module 2)
INSERT INTO questions (question_text, option1, option2, option3, option4, correct_answer, module_id) VALUES
('What is the first phase of a compiler?', 'Syntax Analysis', 'Lexical Analysis', 'Semantic Analysis', 'Code Generation', 'Lexical Analysis', 2),
('Which data structure is used in syntax analysis?', 'Queue', 'Stack', 'Tree', 'Array', 'Stack', 2),
('What is parsing?', 'Code execution', 'Syntax checking', 'Memory allocation', 'None', 'Syntax checking', 2),
('Which tool is used for lexical analysis?', 'Yacc', 'Lex', 'GCC', 'JavaCC', 'Lex', 2),
('What is an intermediate code?', 'Final machine code', 'Temporary code between source and target', 'Binary code', 'None', 'Temporary code between source and target', 2);

-- Sample Questions for Machine Learning (Module 3)
INSERT INTO questions (question_text, option1, option2, option3, option4, correct_answer, module_id) VALUES
('What is Machine Learning?', 'Data storage', 'Learning from data to make predictions', 'Programming', 'App development', 'Learning from data to make predictions', 3),
('Which of these is a supervised learning algorithm?', 'K-Means', 'Linear Regression', 'Apriori', 'DBSCAN', 'Linear Regression', 3),
('Which technique is used for classification?', 'Linear Regression', 'Decision Tree', 'Clustering', 'Histogram', 'Decision Tree', 3),
('Which library is commonly used in ML with Python?', 'NumPy', 'Matplotlib', 'Scikit-learn', 'BeautifulSoup', 'Scikit-learn', 3),
('What is overfitting?', 'Model performs well on all data', 'Model performs well on training but poorly on test', 'Model does not train', 'None', 'Model performs well on training but poorly on test', 3);

-- Sample Questions for Data Structures (Module 4)
INSERT INTO questions (question_text, option1, option2, option3, option4, correct_answer, module_id) VALUES
('Which data structure uses FIFO?', 'Stack', 'Queue', 'Tree', 'Graph', 'Queue', 4),
('What is the worst-case time for binary search?', 'O(n)', 'O(log n)', 'O(n log n)', 'O(1)', 'O(log n)', 4),
('Which data structure uses LIFO?', 'Queue', 'Stack', 'Graph', 'Tree', 'Stack', 4),
('Which traversal gives sorted output in BST?', 'Preorder', 'Inorder', 'Postorder', 'Level-order', 'Inorder', 4),
('Which structure is used in recursion?', 'Queue', 'Stack', 'Tree', 'Array', 'Stack', 4);

-- Sample Questions for Operating Systems (Module 5)
INSERT INTO questions (question_text, option1, option2, option3, option4, correct_answer, module_id) VALUES
('What is the main function of an OS?', 'Compile code', 'Manage hardware and software resources', 'Browse internet', 'Edit documents', 'Manage hardware and software resources', 5),
('Which scheduling algorithm is non-preemptive?', 'Round Robin', 'FCFS', 'Priority Scheduling', 'Multilevel Queue', 'FCFS', 5),
('What is a deadlock?', 'System crash', 'Processes waiting indefinitely for resources', 'Fast execution', 'Memory overflow', 'Processes waiting indefinitely for resources', 5),
('Which memory management technique divides memory into fixed-size blocks?', 'Segmentation', 'Paging', 'Virtual Memory', 'Cache', 'Paging', 5),
('What is thrashing?', 'Excessive paging activity', 'Fast CPU', 'Low memory', 'Disk failure', 'Excessive paging activity', 5);
