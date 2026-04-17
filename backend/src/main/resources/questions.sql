-- Questions for all modules (10 questions per module)

-- SEMESTER 2 - Module 1: Engineering Mathematics II (10 questions)
INSERT INTO questions (question_text, option1, option2, option3, option4, correct_answer, module_id) VALUES
('What is the Laplace transform of 1?', '1/s', 's', '1', 's^2', '1/s', 1),
('Which method solves differential equations?', 'Laplace Transform', 'Bubble Sort', 'Binary Search', 'Hashing', 'Laplace Transform', 1),
('What is the order of d²y/dx² + 3dy/dx + 2y = 0?', '2', '1', '3', '0', '2', 1),
('Which function is not periodic?', 'e^x', 'sin(x)', 'cos(x)', 'tan(x)', 'e^x', 1),
('Fourier series is used for?', 'Approximating periodic functions', 'Sorting', 'Searching', 'Hashing', 'Approximating periodic functions', 1),
('Eigenvalue problem is represented as?', 'Ax = λx', 'A + B = C', 'x² + y² = r²', 'dy/dx = y', 'Ax = λx', 1),
('Value of i² in complex numbers?', '-1', '1', '0', 'i', '-1', 1),
('Rank of matrix equals number of?', 'Non-zero rows in echelon form', 'Zero columns', 'Elements', 'Rows', 'Non-zero rows in echelon form', 1),
('Which method solves linear equations?', 'Gaussian Elimination', 'Integration', 'Laplace', 'Taylor Series', 'Gaussian Elimination', 1),
('Taylor series expands function about?', 'A point', 'Infinity', 'Zero only', 'Variable', 'A point', 1);

-- SEMESTER 2 - Module 2: Physics (10 questions)
INSERT INTO questions (question_text, option1, option2, option3, option4, correct_answer, module_id) VALUES
('SI unit of force?', 'Newton', 'Joule', 'Watt', 'Pascal', 'Newton', 2),
('Newton''s first law is also called?', 'Law of Inertia', 'Law of Acceleration', 'Law of Action', 'Law of Gravity', 'Law of Inertia', 2),
('Formula for kinetic energy?', '1/2 mv²', 'mgh', 'mv', 'ma', '1/2 mv²', 2),
('Speed of light in vacuum?', '3 × 10^8 m/s', '3 × 10^6 m/s', '3 × 10^10 m/s', '3 × 10^4 m/s', '3 × 10^8 m/s', 2),
('Unit of electric current?', 'Ampere', 'Volt', 'Ohm', 'Watt', 'Ampere', 2),
('Ohm''s law formula?', 'V = IR', 'P = VI', 'E = mc²', 'F = ma', 'V = IR', 2),
('What is the unit of power?', 'Watt', 'Joule', 'Newton', 'Pascal', 'Watt', 2),
('Acceleration due to gravity?', '9.8 m/s²', '10 m/s²', '8 m/s²', '9 m/s²', '9.8 m/s²', 2),
('Which law explains rocket propulsion?', 'Newton''s Third Law', 'Newton''s First Law', 'Newton''s Second Law', 'Law of Gravity', 'Newton''s Third Law', 2),
('Formula for momentum?', 'mv', 'ma', 'mgh', '1/2 mv²', 'mv', 2);

-- SEMESTER 2 - Module 3: Programming in C (10 questions)
INSERT INTO questions (question_text, option1, option2, option3, option4, correct_answer, module_id) VALUES
('Who developed C language?', 'Dennis Ritchie', 'Bjarne Stroustrup', 'James Gosling', 'Guido van Rossum', 'Dennis Ritchie', 3),
('Which header file is used for printf()?', 'stdio.h', 'stdlib.h', 'string.h', 'math.h', 'stdio.h', 3),
('Size of int in C (typically)?', '4 bytes', '2 bytes', '8 bytes', '1 byte', '4 bytes', 3),
('Which loop checks condition first?', 'while', 'do-while', 'for', 'All of these', 'while', 3),
('What is the output of printf("%d", 5/2)?', '2', '2.5', '3', '0', '2', 3),
('Which operator is used for address?', '&', '*', '%', '#', '&', 3),
('Array index starts from?', '0', '1', '-1', 'Any number', '0', 3),
('Which is not a valid C keyword?', 'function', 'int', 'float', 'return', 'function', 3),
('What does NULL represent?', 'Zero address', 'Empty string', 'Zero value', 'Undefined', 'Zero address', 3),
('Which function is used to allocate memory?', 'malloc()', 'calloc()', 'free()', 'Both malloc() and calloc()', 'Both malloc() and calloc()', 3);

-- SEMESTER 2 - Module 4: Digital Electronics (10 questions)
INSERT INTO questions (question_text, option1, option2, option3, option4, correct_answer, module_id) VALUES
('AND gate output is 1 when?', 'All inputs are 1', 'Any input is 1', 'All inputs are 0', 'Any input is 0', 'All inputs are 1', 4),
('OR gate output is 0 when?', 'All inputs are 0', 'Any input is 1', 'All inputs are 1', 'Any input is 0', 'All inputs are 0', 4),
('NOT gate is also called?', 'Inverter', 'Buffer', 'Amplifier', 'Converter', 'Inverter', 4),
('How many inputs does XOR gate have?', '2', '1', '3', '4', '2', 4),
('Binary equivalent of decimal 10?', '1010', '1001', '1100', '1111', '1010', 4),
('Which gate is universal?', 'NAND', 'AND', 'OR', 'XOR', 'NAND', 4),
('Flip-flop is a?', 'Bistable device', 'Monostable device', 'Astable device', 'Tristable device', 'Bistable device', 4),
('How many flip-flops for 4-bit counter?', '4', '2', '8', '16', '4', 4),
('Decimal equivalent of binary 1111?', '15', '16', '14', '7', '15', 4),
('Which number system uses base 16?', 'Hexadecimal', 'Octal', 'Binary', 'Decimal', 'Hexadecimal', 4);

-- SEMESTER 2 - Module 5: Engineering Graphics (10 questions)
INSERT INTO questions (question_text, option1, option2, option3, option4, correct_answer, module_id) VALUES
('Orthographic projection has how many views?', '3', '2', '4', '1', '3', 5),
('Top view is also called?', 'Plan', 'Elevation', 'Side view', 'Front view', 'Plan', 5),
('Front view is also called?', 'Elevation', 'Plan', 'Profile', 'Section', 'Elevation', 5),
('Isometric projection angle?', '30°', '45°', '60°', '90°', '30°', 5),
('Which line is used for hidden edges?', 'Dashed line', 'Solid line', 'Dotted line', 'Chain line', 'Dashed line', 5),
('Scale 1:2 means?', 'Half size', 'Double size', 'Same size', 'Quarter size', 'Half size', 5),
('Dimension line is drawn as?', 'Thin continuous line', 'Thick line', 'Dashed line', 'Dotted line', 'Thin continuous line', 5),
('Section plane is shown by?', 'Cutting plane line', 'Center line', 'Hidden line', 'Dimension line', 'Cutting plane line', 5),
('Auxiliary view is used for?', 'Inclined surfaces', 'Parallel surfaces', 'Perpendicular surfaces', 'Curved surfaces', 'Inclined surfaces', 5),
('Leader line is used for?', 'Notes and dimensions', 'Hidden edges', 'Center lines', 'Section planes', 'Notes and dimensions', 5);

-- SEMESTER 3 - Module 6: Data Structures (10 questions)
INSERT INTO questions (question_text, option1, option2, option3, option4, correct_answer, module_id) VALUES
('Which data structure uses FIFO?', 'Queue', 'Stack', 'Tree', 'Graph', 'Queue', 6),
('Which data structure uses LIFO?', 'Stack', 'Queue', 'Array', 'Linked List', 'Stack', 6),
('Time complexity of binary search?', 'O(log n)', 'O(n)', 'O(n²)', 'O(1)', 'O(log n)', 6),
('Which traversal gives sorted output in BST?', 'Inorder', 'Preorder', 'Postorder', 'Level order', 'Inorder', 6),
('Best data structure for recursion?', 'Stack', 'Queue', 'Array', 'Linked List', 'Stack', 6),
('Time complexity of insertion at beginning in linked list?', 'O(1)', 'O(n)', 'O(log n)', 'O(n²)', 'O(1)', 6),
('Which is not a linear data structure?', 'Tree', 'Array', 'Stack', 'Queue', 'Tree', 6),
('Best structure for priority queue?', 'Heap', 'Stack', 'Array', 'Linked List', 'Heap', 6),
('Algorithm to find shortest path?', 'Dijkstra', 'DFS', 'BFS', 'Binary Search', 'Dijkstra', 6),
('Hashing is used for?', 'Fast data access', 'Sorting', 'Searching in tree', 'Graph traversal', 'Fast data access', 6);

-- SEMESTER 3 - Module 7: Discrete Mathematics (10 questions)
INSERT INTO questions (question_text, option1, option2, option3, option4, correct_answer, module_id) VALUES
('Which is a set?', '{a, b, c}', '(a, b, c)', '[a, b, c]', '<a, b, c>', '{a, b, c}', 7),
('Power set of {1,2} is?', '{{}, {1}, {2}, {1,2}}', '{1,2}', '{{1}, {2}}', '{1,2,3}', '{{}, {1}, {2}, {1,2}}', 7),
('Which is a tautology?', 'p ∨ ¬p', 'p ∧ ¬p', '¬(p ∨ q)', 'p ↔ ¬p', 'p ∨ ¬p', 7),
('Degree of vertex means?', 'Number of edges incident', 'Number of nodes', 'Number of paths', 'Weight of vertex', 'Number of edges incident', 7),
('Tree is?', 'Acyclic connected graph', 'Cyclic graph', 'Disconnected graph', 'Graph with loops', 'Acyclic connected graph', 7),
('p ∧ q represents which gate?', 'AND', 'OR', 'NOT', 'XOR', 'AND', 7),
('Bijective function is?', 'One-to-one and onto', 'Only one-to-one', 'Only onto', 'Neither', 'One-to-one and onto', 7),
('1 mod 2 equals?', '1', '0', '2', '-1', '1', 7),
('Relation is?', 'Set of ordered pairs', 'Set of numbers', 'Group of variables', 'None', 'Set of ordered pairs', 7),
('Pigeonhole principle is used in?', 'Combinatorics', 'Graph Theory', 'Calculus', 'Geometry', 'Combinatorics', 7);

-- SEMESTER 3 - Module 8: Computer Organization (10 questions)
INSERT INTO questions (question_text, option1, option2, option3, option4, correct_answer, module_id) VALUES
('ALU stands for?', 'Arithmetic Logic Unit', 'Array Logic Unit', 'Application Logic Unit', 'Arithmetic Local Unit', 'Arithmetic Logic Unit', 8),
('Cache memory is used to?', 'Speed up access', 'Store permanently', 'Access hard disk', 'Replace RAM', 'Speed up access', 8),
('Which is fastest?', 'Register', 'Cache', 'RAM', 'Hard Disk', 'Register', 8),
('Von Neumann architecture uses?', 'Single bus', 'Two buses', 'Three buses', 'No bus', 'Single bus', 8),
('PC register holds?', 'Address of next instruction', 'Current instruction', 'Data', 'Flags', 'Address of next instruction', 8),
('Which is combinational circuit?', 'Adder', 'Flip-Flop', 'Register', 'Counter', 'Adder', 8),
('Control unit is part of?', 'CPU', 'RAM', 'Hard Disk', 'I/O Device', 'CPU', 8),
('Which memory is non-volatile?', 'ROM', 'RAM', 'Cache', 'Register', 'ROM', 8),
('Pipelining increases?', 'Throughput', 'Latency', 'Cost', 'Power', 'Throughput', 8),
('Which is not ROM type?', 'RAM', 'PROM', 'EPROM', 'EEPROM', 'RAM', 8);

-- SEMESTER 3 - Module 9: Object Oriented Programming (10 questions)
INSERT INTO questions (question_text, option1, option2, option3, option4, correct_answer, module_id) VALUES
('Which keyword inherits a class in Java?', 'extends', 'implements', 'super', 'this', 'extends', 9),
('JVM stands for?', 'Java Virtual Machine', 'Java Variable Method', 'Java Verified Memory', 'Java Vendor Mode', 'Java Virtual Machine', 9),
('Which is not primitive data type?', 'String', 'int', 'float', 'boolean', 'String', 9),
('Final keyword does what?', 'Prevents modification', 'Loops forever', 'Declares variable', 'Increments value', 'Prevents modification', 9),
('Which collection does not allow duplicates?', 'Set', 'List', 'Map', 'ArrayList', 'Set', 9),
('Exception handling uses?', 'try-catch', 'if-else', 'switch-case', 'goto', 'try-catch', 9),
('OOP stands for?', 'Object-Oriented Programming', 'Original Object Program', 'Open Object Processing', 'Only On Platform', 'Object-Oriented Programming', 9),
('Entry point method in Java?', 'main', 'start', 'run', 'init', 'main', 9),
('Interface for threads?', 'Runnable', 'Threadable', 'Executor', 'Callable', 'Runnable', 9),
('Default value of boolean?', 'false', 'true', '0', 'null', 'false', 9);

-- SEMESTER 3 - Module 10: Database Management Systems (10 questions)
INSERT INTO questions (question_text, option1, option2, option3, option4, correct_answer, module_id) VALUES
('DBMS stands for?', 'Database Management System', 'Data Based Management System', 'Database Machine System', 'Data Backup Management System', 'Database Management System', 10),
('Which language queries database?', 'SQL', 'HTML', 'Java', 'C++', 'SQL', 10),
('Which is DBMS type?', 'Hierarchical', 'Circular', 'Linear', 'Tabular', 'Hierarchical', 10),
('ACID stands for?', 'Atomicity, Consistency, Isolation, Durability', 'Access, Control, Integrity, Data', 'Availability, Consistency, Isolation, Durability', 'Atomicity, Control, Integrity, Dependency', 'Atomicity, Consistency, Isolation, Durability', 10),
('Which removes transitive dependency?', '3NF', '1NF', '2NF', 'BCNF', '3NF', 10),
('Which key uniquely identifies record?', 'Primary Key', 'Foreign Key', 'Unique Key', 'Composite Key', 'Primary Key', 10),
('Foreign key does what?', 'Links tables', 'Primary key', 'Temporary key', 'Duplicate key', 'Links tables', 10),
('Which clause filters results?', 'WHERE', 'SELECT', 'FROM', 'ORDER BY', 'WHERE', 10),
('Which removes all records?', 'TRUNCATE', 'DELETE', 'REMOVE', 'DROP', 'TRUNCATE', 10),
('Which is not SQL command?', 'PRINT', 'SELECT', 'INSERT', 'UPDATE', 'PRINT', 10);

-- SEMESTER 4 - Module 11: Operating Systems (10 questions)
INSERT INTO questions (question_text, option1, option2, option3, option4, correct_answer, module_id) VALUES
('Main function of OS?', 'Manage hardware and software', 'Compile code', 'Browse internet', 'Edit documents', 'Manage hardware and software', 11),
('Which scheduling is non-preemptive?', 'FCFS', 'Round Robin', 'Priority Scheduling', 'Multilevel Queue', 'FCFS', 11),
('What is deadlock?', 'Processes waiting indefinitely', 'System crash', 'Fast execution', 'Memory overflow', 'Processes waiting indefinitely', 11),
('Paging divides memory into?', 'Fixed-size blocks', 'Variable blocks', 'Segments', 'Pages only', 'Fixed-size blocks', 11),
('What is thrashing?', 'Excessive paging', 'Fast CPU', 'Low memory', 'Disk failure', 'Excessive paging', 11),
('Which is process state?', 'Ready', 'Compile', 'Execute', 'Debug', 'Ready', 11),
('Semaphore is used for?', 'Synchronization', 'Scheduling', 'Memory management', 'File management', 'Synchronization', 11),
('Virtual memory uses?', 'Hard disk', 'RAM only', 'Cache only', 'Register', 'Hard disk', 11),
('Context switching is?', 'Switching between processes', 'Switching OS', 'Switching memory', 'Switching disk', 'Switching between processes', 11),
('Which algorithm prevents starvation?', 'Aging', 'FCFS', 'SJF', 'Priority', 'Aging', 11);

-- SEMESTER 4 - Module 12: Design and Analysis of Algorithms (10 questions)
INSERT INTO questions (question_text, option1, option2, option3, option4, correct_answer, module_id) VALUES
('Best case of bubble sort?', 'O(n)', 'O(n²)', 'O(log n)', 'O(1)', 'O(n)', 12),
('Worst case of quick sort?', 'O(n²)', 'O(n log n)', 'O(n)', 'O(log n)', 'O(n²)', 12),
('Merge sort time complexity?', 'O(n log n)', 'O(n²)', 'O(n)', 'O(log n)', 'O(n log n)', 12),
('Which uses divide and conquer?', 'Merge sort', 'Bubble sort', 'Insertion sort', 'Selection sort', 'Merge sort', 12),
('Greedy algorithm example?', 'Dijkstra', 'Bubble sort', 'Binary search', 'Linear search', 'Dijkstra', 12),
('Dynamic programming uses?', 'Memoization', 'Recursion only', 'Iteration only', 'Backtracking', 'Memoization', 12),
('Binary search requires?', 'Sorted array', 'Unsorted array', 'Linked list', 'Tree', 'Sorted array', 12),
('Master theorem is used for?', 'Recurrence relations', 'Sorting', 'Searching', 'Hashing', 'Recurrence relations', 12),
('NP-complete problem is?', 'Hard to solve', 'Easy to solve', 'Cannot be solved', 'Polynomial time', 'Hard to solve', 12),
('Asymptotic notation O represents?', 'Upper bound', 'Lower bound', 'Tight bound', 'Average case', 'Upper bound', 12);

-- SEMESTER 4 - Module 13: Computer Networks (10 questions)
INSERT INTO questions (question_text, option1, option2, option3, option4, correct_answer, module_id) VALUES
('TCP stands for?', 'Transmission Control Protocol', 'Transfer Control Protocol', 'Transitional Control Protocol', 'Transport Connection Protocol', 'Transmission Control Protocol', 13),
('Which device operates at network layer?', 'Router', 'Switch', 'Hub', 'Repeater', 'Router', 13),
('DHCP assigns?', 'IP addresses', 'MAC addresses', 'Port numbers', 'Domain names', 'IP addresses', 13),
('DNS function?', 'Resolve IP to domain names', 'Encrypt data', 'Route packets', 'Create firewalls', 'Resolve IP to domain names', 13),
('HTTP stands for?', 'HyperText Transfer Protocol', 'Hyper Terminal Transfer Protocol', 'High Transfer Text Protocol', 'Hypertext Text Transfer Protocol', 'HyperText Transfer Protocol', 13),
('Which layer handles end-to-end communication?', 'Transport Layer', 'Network Layer', 'Data Link Layer', 'Physical Layer', 'Transport Layer', 13),
('MAC address is?', 'Hardware identifier', 'IP address', 'Network type', 'None', 'Hardware identifier', 13),
('Star topology uses?', 'Central device', 'Ring connection', 'Bus connection', 'Mesh connection', 'Central device', 13),
('UDP is?', 'Connectionless', 'Connection-oriented', 'Reliable', 'Secure', 'Connectionless', 13),
('Which layer ensures error-free delivery?', 'Data Link', 'Session', 'Presentation', 'Application', 'Data Link', 13);

-- SEMESTER 4 - Module 14: Software Engineering (10 questions)
INSERT INTO questions (question_text, option1, option2, option3, option4, correct_answer, module_id) VALUES
('SDLC stands for?', 'Software Development Life Cycle', 'System Development Life Cycle', 'Software Design Life Cycle', 'System Design Life Cycle', 'Software Development Life Cycle', 14),
('Waterfall model is?', 'Sequential', 'Iterative', 'Incremental', 'Agile', 'Sequential', 14),
('Which is agile methodology?', 'Scrum', 'Waterfall', 'V-Model', 'Spiral', 'Scrum', 14),
('SRS stands for?', 'Software Requirements Specification', 'System Requirements Specification', 'Software Requirement Standard', 'System Requirement Standard', 'Software Requirements Specification', 14),
('Black box testing tests?', 'Functionality', 'Code structure', 'Logic', 'Syntax', 'Functionality', 14),
('White box testing tests?', 'Internal structure', 'Functionality', 'User interface', 'Performance', 'Internal structure', 14),
('Which diagram shows system behavior?', 'Use case diagram', 'Class diagram', 'ER diagram', 'Flowchart', 'Use case diagram', 14),
('Regression testing is?', 'Re-testing after changes', 'First time testing', 'Performance testing', 'Security testing', 'Re-testing after changes', 14),
('Version control tool?', 'Git', 'Eclipse', 'NetBeans', 'Visual Studio', 'Git', 14),
('Spiral model combines?', 'Waterfall and prototyping', 'Agile and waterfall', 'V-model and agile', 'Incremental and waterfall', 'Waterfall and prototyping', 14);

-- SEMESTER 4 - Module 15: Web Technologies (10 questions)
INSERT INTO questions (question_text, option1, option2, option3, option4, correct_answer, module_id) VALUES
('HTML stands for?', 'HyperText Markup Language', 'High Text Markup Language', 'Hyper Transfer Markup Language', 'High Transfer Markup Language', 'HyperText Markup Language', 15),
('CSS stands for?', 'Cascading Style Sheets', 'Computer Style Sheets', 'Creative Style Sheets', 'Colorful Style Sheets', 'Cascading Style Sheets', 15),
('JavaScript is?', 'Client-side scripting', 'Server-side scripting', 'Database language', 'Markup language', 'Client-side scripting', 15),
('Which tag creates hyperlink?', '<a>', '<link>', '<href>', '<url>', '<a>', 15),
('PHP stands for?', 'Hypertext Preprocessor', 'Personal Home Page', 'Private Home Page', 'Public Hypertext Processor', 'Hypertext Preprocessor', 15),
('Which is not HTTP method?', 'SEND', 'GET', 'POST', 'PUT', 'SEND', 15),
('JSON stands for?', 'JavaScript Object Notation', 'Java Standard Object Notation', 'JavaScript Oriented Notation', 'Java Serialized Object Notation', 'JavaScript Object Notation', 15),
('AJAX stands for?', 'Asynchronous JavaScript and XML', 'Advanced JavaScript and XML', 'Asynchronous Java and XML', 'Advanced Java and XML', 'Asynchronous JavaScript and XML', 15),
('Which is frontend framework?', 'React', 'Django', 'Spring', 'Laravel', 'React', 15),
('REST stands for?', 'Representational State Transfer', 'Remote State Transfer', 'Representational System Transfer', 'Remote System Transfer', 'Representational State Transfer', 15);

-- SEMESTER 5 - Module 16: Theory of Computation (10 questions)
INSERT INTO questions (question_text, option1, option2, option3, option4, correct_answer, module_id) VALUES
('DFA stands for?', 'Deterministic Finite Automata', 'Dynamic Function Automata', 'Data Flow Algorithm', 'Defined Function Array', 'Deterministic Finite Automata', 16),
('NFA stands for?', 'Non-deterministic Finite Automata', 'New Function Automata', 'Non-functional Automata', 'Network Flow Automata', 'Non-deterministic Finite Automata', 16),
('Regular expression is recognized by?', 'Finite Automata', 'Pushdown Automata', 'Turing Machine', 'Linear Bounded Automata', 'Finite Automata', 16),
('Context-free grammar is recognized by?', 'Pushdown Automata', 'Finite Automata', 'Turing Machine', 'Linear Bounded Automata', 'Pushdown Automata', 16),
('Turing machine can solve?', 'Any computable problem', 'Only regular languages', 'Only context-free languages', 'Only context-sensitive languages', 'Any computable problem', 16),
('Pumping lemma is used for?', 'Proving non-regularity', 'Proving regularity', 'Designing automata', 'Minimizing automata', 'Proving non-regularity', 16),
('Which is most powerful?', 'Turing Machine', 'DFA', 'NFA', 'PDA', 'Turing Machine', 16),
('Chomsky hierarchy has how many levels?', '4', '2', '3', '5', '4', 16),
('Halting problem is?', 'Undecidable', 'Decidable', 'NP-complete', 'P-complete', 'Undecidable', 16),
('Regular languages are closed under?', 'Union', 'Intersection', 'Complement', 'All of these', 'All of these', 16);

-- SEMESTER 5 - Module 17: Compiler Design (10 questions)
INSERT INTO questions (question_text, option1, option2, option3, option4, correct_answer, module_id) VALUES
('First phase of compiler?', 'Lexical Analysis', 'Syntax Analysis', 'Semantic Analysis', 'Code Generation', 'Lexical Analysis', 17),
('Syntax analysis uses?', 'Stack', 'Queue', 'Tree', 'Array', 'Stack', 17),
('Parsing is?', 'Syntax checking', 'Code execution', 'Memory allocation', 'None', 'Syntax checking', 17),
('Lex is used for?', 'Lexical analysis', 'Syntax analysis', 'Semantic analysis', 'Code generation', 'Lexical analysis', 17),
('Intermediate code is?', 'Between source and target', 'Final machine code', 'Binary code', 'None', 'Between source and target', 17),
('Which phase checks variable declaration?', 'Semantic', 'Lexical', 'Syntax', 'Code Optimization', 'Semantic', 17),
('Symbol table does what?', 'Tracks variables', 'Stores syntax rules', 'Manages memory', 'Converts code', 'Tracks variables', 17),
('LL parser is?', 'Top-down', 'Bottom-up', 'Both', 'Neither', 'Top-down', 17),
('Register allocation is done in?', 'Code Generation', 'Lexical', 'Optimization', 'Parsing', 'Code Generation', 17),
('Which grammar for programming languages?', 'Context-free', 'Regular', 'Phrase structure', 'Unrestricted', 'Context-free', 17);

-- SEMESTER 5 - Module 18: Artificial Intelligence (10 questions)
INSERT INTO questions (question_text, option1, option2, option3, option4, correct_answer, module_id) VALUES
('AI goal is?', 'Build thinking machines', 'Design faster CPUs', 'Replace humans', 'Fix bugs automatically', 'Build thinking machines', 18),
('Which is AI branch?', 'Neural Networks', 'Database Management', 'Web Development', 'Compilers', 'Neural Networks', 18),
('Minimax algorithm is for?', 'Decision-making', 'Backtracking', 'Greedy Algorithm', 'Bubble Sort', 'Decision-making', 18),
('NLP stands for?', 'Natural Language Processing', 'Neural Logic Processing', 'Network Learning Program', 'Natural Logic Parser', 'Natural Language Processing', 18),
('AI example in daily life?', 'Face Unlock', 'Flashlight', 'Wallpaper app', 'Calculator', 'Face Unlock', 18),
('Common AI language?', 'Python', 'HTML', 'CSS', 'PHP', 'Python', 18),
('Learning based on rewards?', 'Reinforcement Learning', 'Supervised Learning', 'Unsupervised Learning', 'Batch Learning', 'Reinforcement Learning', 18),
('Common AI framework?', 'TensorFlow', 'Bootstrap', 'Laravel', 'jQuery', 'TensorFlow', 18),
('Test for machine intelligence?', 'Turing Test', 'RAM test', 'Ping Test', 'Heuristic Test', 'Turing Test', 18),
('Technique inspired by brain?', 'Neural Networks', 'Binary Trees', 'Hash Maps', 'Stacks', 'Neural Networks', 18);

-- SEMESTER 5 - Module 19: Computer Graphics (10 questions)
INSERT INTO questions (question_text, option1, option2, option3, option4, correct_answer, module_id) VALUES
('DDA stands for?', 'Digital Differential Analyzer', 'Direct Drawing Algorithm', 'Digital Drawing Analyzer', 'Direct Differential Algorithm', 'Digital Differential Analyzer', 19),
('Bresenham algorithm is for?', 'Line drawing', 'Circle drawing', 'Polygon filling', 'Clipping', 'Line drawing', 19),
('Which is raster graphics?', 'Bitmap', 'Vector', 'Both', 'Neither', 'Bitmap', 19),
('RGB stands for?', 'Red Green Blue', 'Red Gray Blue', 'Red Green Black', 'Red Gray Black', 'Red Green Blue', 19),
('Transformation that changes size?', 'Scaling', 'Translation', 'Rotation', 'Shearing', 'Scaling', 19),
('Transformation that changes position?', 'Translation', 'Scaling', 'Rotation', 'Reflection', 'Translation', 19),
('Cohen-Sutherland is for?', 'Line clipping', 'Polygon filling', 'Circle drawing', 'Transformation', 'Line clipping', 19),
('Z-buffer algorithm is for?', 'Hidden surface removal', 'Line drawing', 'Polygon filling', 'Clipping', 'Hidden surface removal', 19),
('Bezier curve uses?', 'Control points', 'Pixels', 'Vectors', 'Matrices', 'Control points', 19),
('Frame buffer stores?', 'Pixel values', 'Vertices', 'Edges', 'Transformations', 'Pixel values', 19);

-- SEMESTER 5 - Module 20: Cryptography (10 questions)
INSERT INTO questions (question_text, option1, option2, option3, option4, correct_answer, module_id) VALUES
('Purpose of cryptography?', 'Data security', 'Data transmission', 'Data compression', 'Data storage', 'Data security', 20),
('AES is?', 'Encryption algorithm', 'Sorting algorithm', 'Search algorithm', 'Graph algorithm', 'Encryption algorithm', 20),
('RSA stands for?', 'Rivest–Shamir–Adleman', 'Random Security Algorithm', 'Reverse Secure Access', 'Rapid Secure Algorithm', 'Rivest–Shamir–Adleman', 20),
('Hash function generates?', 'Fixed-size output', 'Variable output', 'Encrypted text', 'Compressed data', 'Fixed-size output', 20),
('Symmetric encryption uses?', 'Same key', 'Two keys', 'No key', 'Multiple keys', 'Same key', 20),
('HTTPS uses?', 'Cryptography', 'Compression', 'Caching', 'Cookies', 'Cryptography', 20),
('Weakness of symmetric key?', 'Key distribution', 'Slower performance', 'Complex math', 'Needs internet', 'Key distribution', 20),
('Asymmetric encryption uses?', 'Two keys', 'One key', 'No key', 'Three keys', 'Two keys', 20),
('Digital signature uses?', 'Private key', 'Public key', 'Both keys', 'No key', 'Private key', 20),
('SSL stands for?', 'Secure Sockets Layer', 'Secure Software Layer', 'Security Socket Layer', 'Soft Secure Lock', 'Secure Sockets Layer', 20);

-- SEMESTER 6 - Module 21: Machine Learning (10 questions)
INSERT INTO questions (question_text, option1, option2, option3, option4, correct_answer, module_id) VALUES
('Machine Learning is?', 'Learning from data', 'Data storage', 'Programming', 'App development', 'Learning from data', 21),
('Supervised learning algorithm?', 'Linear Regression', 'K-Means', 'Apriori', 'DBSCAN', 'Linear Regression', 21),
('Classification technique?', 'Decision Tree', 'Linear Regression', 'Clustering', 'Histogram', 'Decision Tree', 21),
('Common ML library?', 'Scikit-learn', 'NumPy', 'Matplotlib', 'BeautifulSoup', 'Scikit-learn', 21),
('Overfitting means?', 'Good on training, poor on test', 'Good on all data', 'Does not train', 'None', 'Good on training, poor on test', 21),
('Clustering algorithm?', 'K-Means', 'SVM', 'KNN', 'Linear Regression', 'K-Means', 21),
('Cross-validation is?', 'Model selection technique', 'Model deployment', 'Data scaling', 'Model debugging', 'Model selection technique', 21),
('Neural network activation?', 'ReLU', 'max()', 'sort()', 'pow()', 'ReLU', 21),
('Confusion matrix is?', 'Tool to test accuracy', 'Matrix for encryption', 'Database table', 'UI design', 'Tool to test accuracy', 21),
('Which uses labeled data?', 'Supervised', 'Unsupervised', 'Reinforcement', 'None', 'Supervised', 21);

-- SEMESTER 6 - Module 22: Cloud Computing (10 questions)
INSERT INTO questions (question_text, option1, option2, option3, option4, correct_answer, module_id) VALUES
('Cloud computing is?', 'Computing services over internet', 'New hardware', 'Storage device', 'Network topology', 'Computing services over internet', 22),
('Cloud benefit?', 'Scalability', 'High upfront costs', 'Limited access', 'Fixed resources', 'Scalability', 22),
('IaaS provides?', 'Hardware resources', 'Software', 'Platform', 'Database', 'Hardware resources', 22),
('Major cloud provider?', 'Amazon Web Services', 'Microsoft Excel', 'Skype', 'Photoshop', 'Amazon Web Services', 22),
('Cloud storage advantage?', 'On-demand availability', 'Higher cost', 'Increased bandwidth', 'Hardware failure', 'On-demand availability', 22),
('SaaS stands for?', 'Software as a Service', 'System as a Service', 'Storage as a Service', 'Server as a Service', 'Software as a Service', 22),
('Public cloud example?', 'Google Drive', 'Private data center', 'Local file storage', 'On-premise servers', 'Google Drive', 22),
('Secure cloud protocol?', 'HTTPS', 'HTTP', 'FTP', 'SMTP', 'HTTPS', 22),
('Virtualization purpose?', 'Divide resources', 'Create backups', 'Improve hardware', 'Speed up network', 'Divide resources', 22),
('PaaS allows?', 'Build apps without infrastructure', 'Only storage', 'Only compute', 'Only network', 'Build apps without infrastructure', 22);

-- SEMESTER 6 - Module 23: Big Data Analytics (10 questions)
INSERT INTO questions (question_text, option1, option2, option3, option4, correct_answer, module_id) VALUES
('Big Data refers to?', 'Large datasets with variety, velocity, volume', 'Small local files', 'Low memory usage', 'Emails', 'Large datasets with variety, velocity, volume', 23),
('Big Data characteristic?', 'Velocity', 'Color', 'Opacity', 'Temperature', 'Velocity', 23),
('Tool for distributed processing?', 'Hadoop', 'MySQL', 'Oracle', 'MongoDB', 'Hadoop', 23),
('MapReduce is?', 'Programming model for large data', 'Storage device', 'Sorting technique', 'Security protocol', 'Programming model for large data', 23),
('Best for unstructured data?', 'MongoDB', 'MySQL', 'Excel', 'SQLite', 'MongoDB', 23),
('Data analytics is?', 'Analyzing data for patterns', 'Storing data', 'Cleaning room', 'Formatting text', 'Analyzing data for patterns', 23),
('Popular language for Big Data?', 'Python', 'C++', 'Kotlin', 'Swift', 'Python', 23),
('Platform using RDD?', 'Apache Spark', 'Excel', 'MongoDB', 'PostgreSQL', 'Apache Spark', 23),
('Data lake is?', 'Central repository for raw data', 'Water body', 'Encrypted database', 'Cloud function', 'Central repository for raw data', 23),
('Not a Big Data tool?', 'Photoshop', 'Hive', 'Pig', 'HBase', 'Photoshop', 23);

-- SEMESTER 6 - Module 24: Internet of Things (10 questions)
INSERT INTO questions (question_text, option1, option2, option3, option4, correct_answer, module_id) VALUES
('IoT stands for?', 'Internet of Things', 'Internet of Trends', 'Internal of Things', 'Internet of Time', 'Internet of Things', 24),
('IoT messaging protocol?', 'MQTT', 'HTTP', 'SMTP', 'FTP', 'MQTT', 24),
('IoT device example?', 'Smart Bulb', 'Printer', 'Scanner', 'Monitor', 'Smart Bulb', 24),
('Popular IoT language?', 'Python', 'HTML', 'C', 'PHP', 'Python', 24),
('Sensor does what?', 'Collect physical data', 'Display data', 'Store data', 'Delete data', 'Collect physical data', 24),
('Actuator is?', 'Device that performs action', 'Storage device', 'Data sender', 'None', 'Device that performs action', 24),
('IoT layer for communication?', 'Network Layer', 'Perception Layer', 'Application Layer', 'None', 'Network Layer', 24),
('Cloud use in IoT?', 'Store and process data remotely', 'Block devices', 'Cool devices', 'Increase voltage', 'Store and process data remotely', 24),
('Arduino created by?', 'Massimo Banzi', 'Google', 'Intel', 'IBM', 'Massimo Banzi', 24),
('Gateway in IoT?', 'Connects IoT to network', 'Router', 'Monitor', 'Scanner', 'Connects IoT to network', 24);

-- SEMESTER 6 - Module 25: Cyber Security (10 questions)
INSERT INTO questions (question_text, option1, option2, option3, option4, correct_answer, module_id) VALUES
('Cyber security protects?', 'Digital assets', 'Physical assets', 'Buildings', 'Vehicles', 'Digital assets', 25),
('Firewall is?', 'Network security device', 'Antivirus', 'Browser', 'Operating system', 'Network security device', 25),
('Phishing is?', 'Fraudulent attempt to obtain data', 'Fishing activity', 'Network protocol', 'Programming language', 'Fraudulent attempt to obtain data', 25),
('Malware is?', 'Malicious software', 'Good software', 'Hardware', 'Network device', 'Malicious software', 25),
('VPN stands for?', 'Virtual Private Network', 'Very Private Network', 'Virtual Public Network', 'Very Public Network', 'Virtual Private Network', 25),
('DDoS attack is?', 'Distributed Denial of Service', 'Direct Denial of Service', 'Distributed Data of Service', 'Direct Data of Service', 'Distributed Denial of Service', 25),
('Two-factor authentication uses?', 'Two verification methods', 'One method', 'Three methods', 'No methods', 'Two verification methods', 25),
('SQL injection targets?', 'Databases', 'Networks', 'Hardware', 'Operating systems', 'Databases', 25),
('Ransomware does what?', 'Encrypts data for ransom', 'Deletes data', 'Copies data', 'Backs up data', 'Encrypts data for ransom', 25),
('Penetration testing is?', 'Authorized hacking to find vulnerabilities', 'Unauthorized hacking', 'Software testing', 'Hardware testing', 'Authorized hacking to find vulnerabilities', 25);
