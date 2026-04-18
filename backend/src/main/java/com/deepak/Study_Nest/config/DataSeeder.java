package com.deepak.Study_Nest.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.deepak.Study_Nest.dao.ModuleRepository;
import com.deepak.Study_Nest.dao.QuestionRepository;
import com.deepak.Study_Nest.entity.Module;
import com.deepak.Study_Nest.entity.Question;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataSeeder implements CommandLineRunner {

    private final ModuleRepository moduleRepository;
    private final QuestionRepository questionRepository;

    @Override
    public void run(String... args) {
        // Check if data already exists
        if (questionRepository.count() > 0) {
            log.info("Questions already exist. Skipping seeding.");
            return;
        }

        log.info("Starting data seeding...");
        seedQuestions();
        log.info("Data seeding completed!");
    }

    private void seedQuestions() {
        // Get all modules
        var modules = moduleRepository.findAll();
        
        if (modules.isEmpty()) {
            log.warn("No modules found. Please ensure modules are created first.");
            return;
        }

        int totalQuestions = 0;
        
        // Seed 10 questions for each module
        for (Module module : modules) {
            for (int i = 1; i <= 10; i++) {
                Question question = Question.builder()
                        .questionText(getQuestionText(module, i))
                        .option1(getOption(module, i, 1))
                        .option2(getOption(module, i, 2))
                        .option3(getOption(module, i, 3))
                        .option4(getOption(module, i, 4))
                        .correctAnswer("option1") // First option is correct
                        .module(module)
                        .build();
                
                questionRepository.save(question);
                totalQuestions++;
            }
            log.info("Seeded 10 questions for module: {} (Semester {})", 
                    module.getName(), module.getSemester());
        }
        
        log.info("Total questions seeded: {}", totalQuestions);
    }

    private String getQuestionText(Module module, int questionNumber) {
        // Generate meaningful questions based on module name
        String moduleName = module.getName().toLowerCase();
        
        // Subject-specific questions
        if (moduleName.contains("mathematics") || moduleName.contains("math")) {
            return getMathQuestion(questionNumber);
        } else if (moduleName.contains("data structure")) {
            return getDataStructureQuestion(questionNumber);
        } else if (moduleName.contains("digital") || moduleName.contains("logic")) {
            return getDigitalLogicQuestion(questionNumber);
        } else if (moduleName.contains("oop") || moduleName.contains("object oriented")) {
            return getOOPQuestion(questionNumber);
        } else if (moduleName.contains("network")) {
            return getNetworkQuestion(questionNumber);
        } else if (moduleName.contains("operating system") || moduleName.contains("os")) {
            return getOSQuestion(questionNumber);
        } else if (moduleName.contains("database") || moduleName.contains("dbms")) {
            return getDatabaseQuestion(questionNumber);
        } else if (moduleName.contains("algorithm")) {
            return getAlgorithmQuestion(questionNumber);
        } else if (moduleName.contains("software") || moduleName.contains("engineering")) {
            return getSoftwareQuestion(questionNumber);
        } else if (moduleName.contains("web")) {
            return getWebQuestion(questionNumber);
        } else if (moduleName.contains("cyber") || moduleName.contains("security")) {
            return getCyberSecurityQuestion(questionNumber);
        } else if (moduleName.contains("machine learning") || moduleName.contains("ml")) {
            return getMachineLearningQuestion(questionNumber);
        } else if (moduleName.contains("cloud")) {
            return getCloudComputingQuestion(questionNumber);
        } else if (moduleName.contains("artificial intelligence") || moduleName.contains("ai")) {
            return getAIQuestion(questionNumber);
        } else if (moduleName.contains("iot") || moduleName.contains("internet of things")) {
            return getIoTQuestion(questionNumber);
        } else if (moduleName.contains("big data")) {
            return getBigDataQuestion(questionNumber);
        } else if (moduleName.contains("compiler")) {
            return getCompilerQuestion(questionNumber);
        } else if (moduleName.contains("theory of computation") || moduleName.contains("toc")) {
            return getTOCQuestion(questionNumber);
        } else if (moduleName.contains("computer graphics") || moduleName.contains("cg")) {
            return getComputerGraphicsQuestion(questionNumber);
        } else if (moduleName.contains("cryptography") || moduleName.contains("crypto")) {
            return getCryptographyQuestion(questionNumber);
        } else if (moduleName.contains("physics")) {
            return getPhysicsQuestion(questionNumber);
        } else if (moduleName.contains("programming in c") || moduleName.contains("c programming")) {
            return getCProgrammingQuestion(questionNumber);
        } else if (moduleName.contains("discrete")) {
            return getDiscreteMathQuestion(questionNumber);
        } else if (moduleName.contains("computer organization") || moduleName.contains("co")) {
            return getComputerOrganizationQuestion(questionNumber);
        } else if (moduleName.contains("graphics") && !moduleName.contains("computer")) {
            return getEngineeringGraphicsQuestion(questionNumber);
        }
        
        // Default generic question
        return String.format("What is a key concept in %s?", module.getName());
    }

    private String getOption(Module module, int questionNumber, int optionNumber) {
        String moduleName = module.getName().toLowerCase();
        
        // Generate meaningful options based on subject
        if (moduleName.contains("mathematics") || moduleName.contains("math")) {
            return getMathOption(questionNumber, optionNumber);
        } else if (moduleName.contains("data structure")) {
            return getDataStructureOption(questionNumber, optionNumber);
        } else if (moduleName.contains("digital") || moduleName.contains("logic")) {
            return getDigitalLogicOption(questionNumber, optionNumber);
        } else if (moduleName.contains("oop") || moduleName.contains("object oriented")) {
            return getOOPOption(questionNumber, optionNumber);
        } else if (moduleName.contains("network")) {
            return getNetworkOption(questionNumber, optionNumber);
        } else if (moduleName.contains("operating system") || moduleName.contains("os")) {
            return getOSOption(questionNumber, optionNumber);
        } else if (moduleName.contains("database")) {
            return getDatabaseOption(questionNumber, optionNumber);
        } else if (moduleName.contains("algorithm")) {
            return getAlgorithmOption(questionNumber, optionNumber);
        } else if (moduleName.contains("software")) {
            return getSoftwareOption(questionNumber, optionNumber);
        } else if (moduleName.contains("web")) {
            return getWebOption(questionNumber, optionNumber);
        } else if (moduleName.contains("cyber") || moduleName.contains("security")) {
            return getCyberSecurityOption(questionNumber, optionNumber);
        } else if (moduleName.contains("machine learning") || moduleName.contains("ml")) {
            return getMachineLearningOption(questionNumber, optionNumber);
        } else if (moduleName.contains("cloud")) {
            return getCloudComputingOption(questionNumber, optionNumber);
        } else if (moduleName.contains("artificial intelligence") || moduleName.contains("ai")) {
            return getAIOption(questionNumber, optionNumber);
        } else if (moduleName.contains("iot") || moduleName.contains("internet of things")) {
            return getIoTOption(questionNumber, optionNumber);
        } else if (moduleName.contains("big data")) {
            return getBigDataOption(questionNumber, optionNumber);
        } else if (moduleName.contains("compiler")) {
            return getCompilerOption(questionNumber, optionNumber);
        } else if (moduleName.contains("theory of computation") || moduleName.contains("toc")) {
            return getTOCOption(questionNumber, optionNumber);
        } else if (moduleName.contains("computer graphics") || moduleName.contains("cg")) {
            return getComputerGraphicsOption(questionNumber, optionNumber);
        } else if (moduleName.contains("cryptography") || moduleName.contains("crypto")) {
            return getCryptographyOption(questionNumber, optionNumber);
        } else if (moduleName.contains("physics")) {
            return getPhysicsOption(questionNumber, optionNumber);
        } else if (moduleName.contains("programming in c") || moduleName.contains("c programming")) {
            return getCProgrammingOption(questionNumber, optionNumber);
        } else if (moduleName.contains("discrete")) {
            return getDiscreteMathOption(questionNumber, optionNumber);
        } else if (moduleName.contains("computer organization") || moduleName.contains("co")) {
            return getComputerOrganizationOption(questionNumber, optionNumber);
        } else if (moduleName.contains("graphics") && !moduleName.contains("computer")) {
            return getEngineeringGraphicsOption(questionNumber, optionNumber);
        }
        
        // Default options
        if (optionNumber == 1) return "Correct Answer";
        return "Wrong Answer " + optionNumber;
    }

    // Mathematics Questions
    private String getMathQuestion(int num) {
        String[] questions = {
            "What is the Laplace transform of 1?",
            "What is the derivative of sin(x)?",
            "Integration of 1/x is?",
            "What is a matrix?",
            "Determinant of identity matrix is?",
            "What is a vector?",
            "Fourier series is used for?",
            "What is eigenvalue?",
            "Gradient of scalar field gives?",
            "What is divergence?"
        };
        return questions[(num - 1) % questions.length];
    }

    private String getMathOption(int qNum, int optNum) {
        String[][] options = {
            {"1/s", "s", "1", "s^2"},
            {"cos(x)", "-cos(x)", "sin(x)", "-sin(x)"},
            {"ln|x|", "x", "x^2", "1/x^2"},
            {"Rectangular array", "Linear list", "Tree", "Graph"},
            {"1", "0", "-1", "Undefined"},
            {"Magnitude & direction", "Only magnitude", "Only direction", "Neither"},
            {"Periodic functions", "Sorting", "Searching", "Hashing"},
            {"Av = λv", "A + v = λ", "Av = v", "A = λv"},
            {"Vector field", "Scalar field", "Matrix", "Constant"},
            {"Scalar", "Vector", "Matrix", "Tensor"}
        };
        return options[(qNum - 1) % options.length][optNum - 1];
    }

    // Data Structures Questions
    private String getDataStructureQuestion(int num) {
        String[] questions = {
            "What is a stack?",
            "Queue follows which principle?",
            "Time complexity of binary search?",
            "Which is not linear data structure?",
            "Linked list uses?",
            "What is a binary tree?",
            "Hash table uses?",
            "Graph traversal methods?",
            "Heap is a?",
            "AVL tree is?"
        };
        return questions[(num - 1) % questions.length];
    }

    private String getDataStructureOption(int qNum, int optNum) {
        String[][] options = {
            {"LIFO structure", "FIFO structure", "Random access", "Tree structure"},
            {"FIFO", "LIFO", "Random", "Priority"},
            {"O(log n)", "O(n)", "O(n^2)", "O(1)"},
            {"Tree", "Array", "Stack", "Queue"},
            {"Pointers", "Arrays", "Stacks", "Queues"},
            {"Max 2 children", "Max 3 children", "Any children", "No children"},
            {"Hash function", "Sort function", "Search function", "Merge function"},
            {"BFS and DFS", "Only BFS", "Only DFS", "Neither"},
            {"Complete binary tree", "Linked list", "Array", "Stack"},
            {"Self-balancing BST", "Binary tree", "Linked list", "Graph"}
        };
        return options[(qNum - 1) % options.length][optNum - 1];
    }

    // Digital Logic Questions
    private String getDigitalLogicQuestion(int num) {
        String[] questions = {
            "AND gate output is 1 when?",
            "OR gate output is 0 when?",
            "NOT gate is also called?",
            "XOR gate output is 1 when?",
            "Boolean algebra invented by?",
            "Flip-flop is a?",
            "Multiplexer is a?",
            "Demultiplexer is a?",
            "K-map is used for?",
            "Counter is a?"
        };
        return questions[(num - 1) % questions.length];
    }

    private String getDigitalLogicOption(int qNum, int optNum) {
        String[][] options = {
            {"All inputs 1", "Any input 1", "All inputs 0", "Any input 0"},
            {"All inputs 0", "Any input 0", "All inputs 1", "Any input 1"},
            {"Inverter", "Buffer", "Amplifier", "Multiplexer"},
            {"Inputs differ", "Inputs same", "All inputs 1", "All inputs 0"},
            {"George Boole", "Alan Turing", "John von Neumann", "Charles Babbage"},
            {"Bistable device", "Monostable device", "Astable device", "Tristable device"},
            {"Data selector", "Data distributor", "Decoder", "Encoder"},
            {"Data distributor", "Data selector", "Encoder", "Decoder"},
            {"Simplification", "Addition", "Subtraction", "Multiplication"},
            {"Sequential circuit", "Combinational circuit", "Memory device", "Logic gate"}
        };
        return options[(qNum - 1) % options.length][optNum - 1];
    }

    // OOP Questions
    private String getOOPQuestion(int num) {
        String[] questions = {
            "What is encapsulation?",
            "Inheritance allows?",
            "Polymorphism means?",
            "Abstract class can have?",
            "Interface contains?",
            "Constructor is called when?",
            "Destructor is called when?",
            "Method overloading is?",
            "Method overriding is?",
            "this keyword refers to?"
        };
        return questions[(num - 1) % questions.length];
    }

    private String getOOPOption(int qNum, int optNum) {
        String[][] options = {
            {"Data hiding", "Data showing", "Data sorting", "Data searching"},
            {"Code reuse", "Code deletion", "Code hiding", "Code sorting"},
            {"Many forms", "One form", "No form", "Two forms"},
            {"Abstract methods", "Only concrete methods", "No methods", "Static only"},
            {"Abstract methods", "Concrete methods", "Variables only", "Constructors"},
            {"Object created", "Object destroyed", "Method called", "Variable declared"},
            {"Object destroyed", "Object created", "Method called", "Variable declared"},
            {"Compile-time polymorphism", "Runtime polymorphism", "Not polymorphism", "Multiple inheritance"},
            {"Runtime polymorphism", "Compile-time polymorphism", "Not polymorphism", "Multiple inheritance"},
            {"Current object", "Parent object", "Child object", "Static object"}
        };
        return options[(qNum - 1) % options.length][optNum - 1];
    }

    // Network Questions
    private String getNetworkQuestion(int num) {
        String[] questions = {
            "OSI model has how many layers?",
            "TCP is a?",
            "UDP is a?",
            "IP address is?",
            "MAC address is?",
            "HTTP works on which port?",
            "HTTPS works on which port?",
            "DNS stands for?",
            "Router works at which layer?",
            "Switch works at which layer?"
        };
        return questions[(num - 1) % questions.length];
    }

    private String getNetworkOption(int qNum, int optNum) {
        String[][] options = {
            {"7", "5", "4", "6"},
            {"Connection-oriented", "Connectionless", "Physical layer", "Data link layer"},
            {"Connectionless", "Connection-oriented", "Physical layer", "Data link layer"},
            {"Logical address", "Physical address", "Port number", "MAC address"},
            {"Physical address", "Logical address", "Port number", "IP address"},
            {"80", "443", "21", "22"},
            {"443", "80", "21", "22"},
            {"Domain Name System", "Data Name System", "Domain Network System", "Data Network System"},
            {"Network layer", "Data link layer", "Physical layer", "Transport layer"},
            {"Data link layer", "Network layer", "Physical layer", "Transport layer"}
        };
        return options[(qNum - 1) % options.length][optNum - 1];
    }

    // Operating System Questions
    private String getOSQuestion(int num) {
        String[] questions = {
            "What is an OS?",
            "Process is?",
            "Thread is?",
            "Deadlock occurs when?",
            "Semaphore is used for?",
            "Virtual memory uses?",
            "FCFS is?",
            "Round Robin uses?",
            "Page fault occurs when?",
            "File system manages?"
        };
        return questions[(num - 1) % questions.length];
    }

    private String getOSOption(int qNum, int optNum) {
        String[][] options = {
            {"System software", "Application software", "Utility software", "Firmware"},
            {"Program in execution", "Program on disk", "Compiled code", "Source code"},
            {"Lightweight process", "Heavy process", "Program", "Application"},
            {"Circular wait", "No wait", "Single process", "No resources"},
            {"Synchronization", "Scheduling", "Memory management", "File management"},
            {"Paging", "Caching", "Buffering", "Spooling"},
            {"First Come First Serve", "Fast CPU First", "First CPU First", "None"},
            {"Time quantum", "Priority", "FCFS", "SJF"},
            {"Page not in memory", "Page in memory", "No pages", "All pages loaded"},
            {"Files and directories", "Only files", "Only directories", "Neither"}
        };
        return options[(qNum - 1) % options.length][optNum - 1];
    }

    // Database Questions
    private String getDatabaseQuestion(int num) {
        String[] questions = {
            "What is DBMS?",
            "Primary key is?",
            "Foreign key is?",
            "SQL stands for?",
            "Normalization is used for?",
            "What is a transaction?",
            "ACID properties ensure?",
            "JOIN operation is used to?",
            "Index is used for?",
            "What is a view?"
        };
        return questions[(num - 1) % questions.length];
    }

    private String getDatabaseOption(int qNum, int optNum) {
        String[][] options = {
            {"Database Management System", "Data Base Management Software", "Database Manipulation System", "None"},
            {"Unique identifier", "Foreign reference", "Index", "Constraint"},
            {"References primary key", "Unique identifier", "Index", "Constraint"},
            {"Structured Query Language", "Simple Query Language", "Standard Query Language", "None"},
            {"Reduce redundancy", "Increase redundancy", "Delete data", "Insert data"},
            {"Unit of work", "Database", "Table", "Query"},
            {"Data consistency", "Data inconsistency", "Data deletion", "Data insertion"},
            {"Combine tables", "Split tables", "Delete tables", "Create tables"},
            {"Faster retrieval", "Slower retrieval", "Data storage", "Data deletion"},
            {"Virtual table", "Physical table", "Index", "Constraint"}
        };
        return options[(qNum - 1) % options.length][optNum - 1];
    }

    // Algorithm Questions
    private String getAlgorithmQuestion(int num) {
        String[] questions = {
            "Time complexity of bubble sort?",
            "Best sorting algorithm?",
            "Binary search requires?",
            "Greedy algorithm is?",
            "Dynamic programming uses?",
            "Divide and conquer example?",
            "BFS uses which data structure?",
            "DFS uses which data structure?",
            "Dijkstra's algorithm finds?",
            "What is recursion?"
        };
        return questions[(num - 1) % questions.length];
    }

    private String getAlgorithmOption(int qNum, int optNum) {
        String[][] options = {
            {"O(n^2)", "O(n log n)", "O(n)", "O(log n)"},
            {"Quick Sort", "Bubble Sort", "Selection Sort", "Insertion Sort"},
            {"Sorted array", "Unsorted array", "Linked list", "Tree"},
            {"Local optimum", "Global optimum", "No optimum", "Random"},
            {"Memoization", "Recursion only", "Iteration only", "None"},
            {"Merge Sort", "Bubble Sort", "Selection Sort", "Insertion Sort"},
            {"Queue", "Stack", "Array", "Tree"},
            {"Stack", "Queue", "Array", "Tree"},
            {"Shortest path", "Longest path", "All paths", "No path"},
            {"Function calling itself", "Loop", "Iteration", "None"}
        };
        return options[(qNum - 1) % options.length][optNum - 1];
    }

    // Software Engineering Questions
    private String getSoftwareQuestion(int num) {
        String[] questions = {
            "SDLC stands for?",
            "Waterfall model is?",
            "Agile methodology focuses on?",
            "What is a sprint?",
            "Version control is used for?",
            "Unit testing tests?",
            "What is refactoring?",
            "Design pattern is?",
            "What is CI/CD?",
            "Scrum is a?"
        };
        return questions[(num - 1) % questions.length];
    }

    private String getSoftwareOption(int qNum, int optNum) {
        String[][] options = {
            {"Software Development Life Cycle", "System Development Life Cycle", "Software Design Life Cycle", "None"},
            {"Sequential model", "Iterative model", "Incremental model", "Spiral model"},
            {"Iterative development", "Sequential development", "No development", "Random development"},
            {"Time-boxed iteration", "Full project", "Single task", "Testing phase"},
            {"Track changes", "Delete code", "Write code", "Test code"},
            {"Individual units", "Entire system", "Integration", "Performance"},
            {"Improve code structure", "Delete code", "Write new code", "Test code"},
            {"Reusable solution", "Problem", "Bug", "Error"},
            {"Continuous Integration/Deployment", "Code Integration/Development", "Continuous Improvement/Deployment", "None"},
            {"Agile framework", "Waterfall framework", "Sequential framework", "None"}
        };
        return options[(qNum - 1) % options.length][optNum - 1];
    }

    // Web Development Questions
    private String getWebQuestion(int num) {
        String[] questions = {
            "HTML stands for?",
            "CSS is used for?",
            "JavaScript is?",
            "What is DOM?",
            "HTTP method for reading?",
            "REST API uses?",
            "JSON stands for?",
            "What is AJAX?",
            "React is a?",
            "Node.js is?"
        };
        return questions[(num - 1) % questions.length];
    }

    private String getWebOption(int qNum, int optNum) {
        String[][] options = {
            {"HyperText Markup Language", "High Text Markup Language", "Hyper Transfer Markup Language", "None"},
            {"Styling", "Structure", "Behavior", "Database"},
            {"Scripting language", "Markup language", "Style language", "Database language"},
            {"Document Object Model", "Data Object Model", "Document Oriented Model", "None"},
            {"GET", "POST", "PUT", "DELETE"},
            {"HTTP methods", "FTP methods", "SMTP methods", "None"},
            {"JavaScript Object Notation", "Java Standard Object Notation", "JavaScript Oriented Notation", "None"},
            {"Asynchronous JavaScript", "Synchronous JavaScript", "Advanced JavaScript", "None"},
            {"JavaScript library", "Programming language", "Database", "Server"},
            {"JavaScript runtime", "Programming language", "Database", "Framework"}
        };
        return options[(qNum - 1) % options.length][optNum - 1];
    }

    // Cyber Security Questions
    private String getCyberSecurityQuestion(int num) {
        String[] questions = {
            "What is encryption?",
            "Firewall is used for?",
            "What is phishing?",
            "SQL injection is?",
            "What is malware?",
            "Two-factor authentication provides?",
            "What is a VPN?",
            "DDoS attack means?",
            "What is ransomware?",
            "SSL/TLS provides?"
        };
        return questions[(num - 1) % questions.length];
    }

    private String getCyberSecurityOption(int qNum, int optNum) {
        String[][] options = {
            {"Data protection", "Data deletion", "Data sorting", "Data searching"},
            {"Network security", "Data storage", "File management", "Printing"},
            {"Social engineering attack", "Hardware failure", "Software bug", "Network error"},
            {"Code injection attack", "Hardware attack", "Physical attack", "Social attack"},
            {"Malicious software", "Useful software", "System software", "Application software"},
            {"Extra security layer", "Less security", "No security", "Single security"},
            {"Virtual Private Network", "Very Private Network", "Virtual Public Network", "None"},
            {"Distributed Denial of Service", "Direct Denial of Service", "Data Denial of Service", "None"},
            {"Encrypts and demands payment", "Free software", "Antivirus", "Firewall"},
            {"Secure communication", "Insecure communication", "No communication", "Fast communication"}
        };
        return options[(qNum - 1) % options.length][optNum - 1];
    }

    // Machine Learning Questions
    private String getMachineLearningQuestion(int num) {
        String[] questions = {
            "What is supervised learning?",
            "Unsupervised learning uses?",
            "What is overfitting?",
            "Neural network mimics?",
            "What is gradient descent?",
            "Classification predicts?",
            "Regression predicts?",
            "What is a feature?",
            "Training data is used for?",
            "What is cross-validation?"
        };
        return questions[(num - 1) % questions.length];
    }

    private String getMachineLearningOption(int qNum, int optNum) {
        String[][] options = {
            {"Learning with labeled data", "Learning without labels", "No learning", "Random learning"},
            {"Unlabeled data", "Labeled data", "No data", "Test data"},
            {"Model too complex", "Model too simple", "Perfect model", "No model"},
            {"Human brain", "Computer", "Network", "Database"},
            {"Optimization algorithm", "Sorting algorithm", "Search algorithm", "None"},
            {"Categories", "Continuous values", "Text", "Images"},
            {"Continuous values", "Categories", "Text", "Images"},
            {"Input variable", "Output variable", "Model", "Algorithm"},
            {"Model learning", "Model testing", "Model deployment", "Model deletion"},
            {"Model validation technique", "Training technique", "Testing technique", "None"}
        };
        return options[(qNum - 1) % options.length][optNum - 1];
    }

    // Cloud Computing Questions
    private String getCloudComputingQuestion(int num) {
        String[] questions = {
            "What is cloud computing?",
            "IaaS stands for?",
            "PaaS stands for?",
            "SaaS stands for?",
            "Public cloud is?",
            "Private cloud is?",
            "What is virtualization?",
            "AWS is a?",
            "What is scalability?",
            "Load balancing is?"
        };
        return questions[(num - 1) % questions.length];
    }

    private String getCloudComputingOption(int qNum, int optNum) {
        String[][] options = {
            {"Internet-based computing", "Local computing", "No computing", "Manual computing"},
            {"Infrastructure as a Service", "Internet as a Service", "Information as a Service", "None"},
            {"Platform as a Service", "Program as a Service", "Product as a Service", "None"},
            {"Software as a Service", "System as a Service", "Security as a Service", "None"},
            {"Shared resources", "Private resources", "No resources", "Limited resources"},
            {"Dedicated resources", "Shared resources", "No resources", "Public resources"},
            {"Multiple OS on one hardware", "One OS only", "No OS", "Physical machines"},
            {"Cloud service provider", "Operating system", "Programming language", "Database"},
            {"Handle increased load", "Handle decreased load", "No load handling", "Fixed load"},
            {"Distribute traffic", "Block traffic", "No traffic", "Single server"}
        };
        return options[(qNum - 1) % options.length][optNum - 1];
    }

    // AI Questions
    private String getAIQuestion(int num) {
        String[] questions = {
            "What is AI?",
            "Machine learning is?",
            "Deep learning uses?",
            "What is NLP?",
            "Expert system is?",
            "What is computer vision?",
            "Turing test checks?",
            "What is a neural network?",
            "Reinforcement learning uses?",
            "What is an agent?"
        };
        return questions[(num - 1) % questions.length];
    }

    private String getAIOption(int qNum, int optNum) {
        String[][] options = {
            {"Machine intelligence", "Human intelligence", "No intelligence", "Animal intelligence"},
            {"Subset of AI", "Superset of AI", "Not related to AI", "None"},
            {"Neural networks", "Decision trees", "Linear regression", "None"},
            {"Natural Language Processing", "Network Language Processing", "None", "New Language Processing"},
            {"AI with domain knowledge", "General AI", "No knowledge", "Random system"},
            {"Image understanding", "Text understanding", "Audio understanding", "None"},
            {"Machine intelligence", "Machine speed", "Machine memory", "Machine storage"},
            {"Interconnected nodes", "Single node", "No nodes", "Linear structure"},
            {"Rewards and penalties", "Only rewards", "Only penalties", "No feedback"},
            {"Perceives and acts", "Only perceives", "Only acts", "Neither"}
        };
        return options[(qNum - 1) % options.length][optNum - 1];
    }

    // IoT Questions
    private String getIoTQuestion(int num) {
        String[] questions = {
            "What is IoT?",
            "IoT devices are?",
            "What is a sensor?",
            "What is an actuator?",
            "MQTT is?",
            "What is edge computing?",
            "Smart home uses?",
            "What is M2M?",
            "IoT gateway is?",
            "What is fog computing?"
        };
        return questions[(num - 1) % questions.length];
    }

    private String getIoTOption(int qNum, int optNum) {
        String[][] options = {
            {"Internet of Things", "Internet of Technology", "Internet of Thoughts", "None"},
            {"Connected devices", "Disconnected devices", "No devices", "Single device"},
            {"Detects physical changes", "Displays data", "Stores data", "Processes data"},
            {"Performs actions", "Senses only", "Stores only", "Displays only"},
            {"Messaging protocol", "Programming language", "Operating system", "Database"},
            {"Processing near source", "Cloud processing", "No processing", "Central processing"},
            {"IoT devices", "No devices", "Manual devices", "Mechanical devices"},
            {"Machine to Machine", "Man to Machine", "Machine to Man", "None"},
            {"Connects IoT to internet", "Stores data", "Displays data", "None"},
            {"Distributed computing", "Centralized computing", "No computing", "Cloud only"}
        };
        return options[(qNum - 1) % options.length][optNum - 1];
    }

    // Big Data Questions
    private String getBigDataQuestion(int num) {
        String[] questions = {
            "What is Big Data?",
            "3 Vs of Big Data?",
            "Hadoop is?",
            "MapReduce is?",
            "What is HDFS?",
            "NoSQL databases are?",
            "Data mining is?",
            "What is data warehouse?",
            "Spark is?",
            "What is data lake?"
        };
        return questions[(num - 1) % questions.length];
    }

    private String getBigDataOption(int qNum, int optNum) {
        String[][] options = {
            {"Large datasets", "Small datasets", "No data", "Medium data"},
            {"Volume, Velocity, Variety", "Volume, Value, Variety", "Volume, Velocity, Value", "None"},
            {"Big data framework", "Programming language", "Operating system", "Database"},
            {"Parallel processing", "Sequential processing", "No processing", "Single processing"},
            {"Hadoop Distributed File System", "Hard Disk File System", "None", "High Data File System"},
            {"Non-relational databases", "Relational databases", "No databases", "File systems"},
            {"Extract patterns", "Store data", "Delete data", "Display data"},
            {"Centralized repository", "Distributed storage", "No storage", "Temporary storage"},
            {"Fast processing engine", "Slow engine", "No engine", "Storage engine"},
            {"Raw data storage", "Processed data only", "No data", "Structured only"}
        };
        return options[(qNum - 1) % options.length][optNum - 1];
    }

    // Compiler Questions
    private String getCompilerQuestion(int num) {
        String[] questions = {
            "What is a compiler?",
            "Lexical analysis produces?",
            "Parser checks?",
            "What is a token?",
            "Semantic analysis checks?",
            "Code optimization improves?",
            "What is intermediate code?",
            "Symbol table stores?",
            "What is code generation?",
            "Cross compiler generates?"
        };
        return questions[(num - 1) % questions.length];
    }

    private String getCompilerOption(int qNum, int optNum) {
        String[][] options = {
            {"Translates high-level to machine code", "Executes code", "Debugs code", "None"},
            {"Tokens", "Parse tree", "Assembly code", "Machine code"},
            {"Syntax", "Semantics", "Optimization", "Execution"},
            {"Lexical unit", "Syntax unit", "Semantic unit", "None"},
            {"Meaning", "Syntax", "Lexical", "Optimization"},
            {"Performance", "Syntax", "Semantics", "Lexical"},
            {"Between source and target", "Source code", "Machine code", "Assembly code"},
            {"Identifiers info", "Tokens", "Parse tree", "Machine code"},
            {"Target code production", "Parsing", "Lexical analysis", "Optimization"},
            {"Code for different platform", "Same platform code", "No code", "Source code"}
        };
        return options[(qNum - 1) % options.length][optNum - 1];
    }

    // Theory of Computation Questions
    private String getTOCQuestion(int num) {
        String[] questions = {
            "What is an automaton?",
            "DFA stands for?",
            "NFA stands for?",
            "Regular expression describes?",
            "Context-free grammar generates?",
            "Turing machine is?",
            "What is decidability?",
            "P vs NP is?",
            "What is a language?",
            "Chomsky hierarchy has?"
        };
        return questions[(num - 1) % questions.length];
    }

    private String getTOCOption(int qNum, int optNum) {
        String[][] options = {
            {"Abstract machine", "Physical machine", "No machine", "Real machine"},
            {"Deterministic Finite Automaton", "Direct Finite Automaton", "None", "Distributed Finite Automaton"},
            {"Non-deterministic Finite Automaton", "New Finite Automaton", "None", "Normal Finite Automaton"},
            {"Regular language", "Context-free language", "All languages", "No language"},
            {"Context-free language", "Regular language", "All languages", "No language"},
            {"Most powerful model", "Least powerful", "Medium powerful", "No power"},
            {"Problem solvability", "Problem complexity", "Problem size", "None"},
            {"Complexity classes", "Automata types", "Grammar types", "None"},
            {"Set of strings", "Set of numbers", "Set of symbols", "None"},
            {"4 types of grammars", "2 types", "3 types", "5 types"}
        };
        return options[(qNum - 1) % options.length][optNum - 1];
    }

    // Computer Graphics Questions
    private String getComputerGraphicsQuestion(int num) {
        String[] questions = {
            "What is rasterization?",
            "Vector graphics uses?",
            "What is a pixel?",
            "3D transformation uses?",
            "What is rendering?",
            "Ray tracing is?",
            "What is anti-aliasing?",
            "Bezier curve is?",
            "What is clipping?",
            "Frame buffer stores?"
        };
        return questions[(num - 1) % questions.length];
    }

    private String getComputerGraphicsOption(int qNum, int optNum) {
        String[][] options = {
            {"Convert vector to raster", "Convert raster to vector", "No conversion", "Image compression"},
            {"Mathematical equations", "Pixels", "Both", "Neither"},
            {"Picture element", "Picture quality", "Picture size", "None"},
            {"Matrices", "Vectors", "Scalars", "None"},
            {"Generate image", "Store image", "Display image", "Delete image"},
            {"Realistic rendering", "Fast rendering", "No rendering", "Simple rendering"},
            {"Smooth edges", "Sharp edges", "No edges", "Thick edges"},
            {"Parametric curve", "Straight line", "Circle", "None"},
            {"Remove outside viewport", "Add to viewport", "No change", "Zoom viewport"},
            {"Pixel values", "Vector data", "Text data", "Audio data"}
        };
        return options[(qNum - 1) % options.length][optNum - 1];
    }

    // Cryptography Questions
    private String getCryptographyQuestion(int num) {
        String[] questions = {
            "What is cryptography?",
            "Symmetric encryption uses?",
            "Asymmetric encryption uses?",
            "What is a hash function?",
            "Digital signature provides?",
            "RSA is?",
            "AES is?",
            "What is a cipher?",
            "Public key is used for?",
            "Private key is used for?"
        };
        return questions[(num - 1) % questions.length];
    }

    private String getCryptographyOption(int qNum, int optNum) {
        String[][] options = {
            {"Secure communication", "Fast communication", "No communication", "Open communication"},
            {"Same key", "Different keys", "No key", "Multiple keys"},
            {"Key pair", "Single key", "No key", "Three keys"},
            {"One-way function", "Two-way function", "No function", "Reversible function"},
            {"Authentication", "Encryption", "Compression", "None"},
            {"Asymmetric algorithm", "Symmetric algorithm", "Hash algorithm", "None"},
            {"Symmetric algorithm", "Asymmetric algorithm", "Hash algorithm", "None"},
            {"Encryption algorithm", "Compression algorithm", "Sorting algorithm", "None"},
            {"Encryption", "Decryption", "Both", "Neither"},
            {"Decryption", "Encryption", "Both", "Neither"}
        };
        return options[(qNum - 1) % options.length][optNum - 1];
    }

    // Physics Questions
    private String getPhysicsQuestion(int num) {
        String[] questions = {
            "Newton's first law is?",
            "What is force?",
            "Energy is measured in?",
            "What is momentum?",
            "Ohm's law relates?",
            "What is frequency?",
            "Light travels at?",
            "What is gravity?",
            "Kinetic energy depends on?",
            "What is power?"
        };
        return questions[(num - 1) % questions.length];
    }

    private String getPhysicsOption(int qNum, int optNum) {
        String[][] options = {
            {"Law of inertia", "Law of acceleration", "Law of action-reaction", "None"},
            {"Mass × acceleration", "Mass × velocity", "Mass × distance", "None"},
            {"Joules", "Watts", "Newtons", "Meters"},
            {"Mass × velocity", "Mass × acceleration", "Force × distance", "None"},
            {"V = IR", "V = I/R", "V = R/I", "None"},
            {"Cycles per second", "Seconds per cycle", "Distance per second", "None"},
            {"3×10^8 m/s", "3×10^6 m/s", "3×10^10 m/s", "None"},
            {"Attractive force", "Repulsive force", "No force", "Magnetic force"},
            {"Mass and velocity", "Mass only", "Velocity only", "Neither"},
            {"Work per time", "Force per time", "Energy per distance", "None"}
        };
        return options[(qNum - 1) % options.length][optNum - 1];
    }

    // C Programming Questions
    private String getCProgrammingQuestion(int num) {
        String[] questions = {
            "What is a pointer?",
            "Array index starts from?",
            "What is a structure?",
            "malloc() is used for?",
            "What is a function?",
            "printf() is used for?",
            "What is a loop?",
            "if-else is?",
            "What is recursion?",
            "Header file contains?"
        };
        return questions[(num - 1) % questions.length];
    }

    private String getCProgrammingOption(int qNum, int optNum) {
        String[][] options = {
            {"Stores address", "Stores value", "Stores name", "None"},
            {"0", "1", "-1", "2"},
            {"User-defined data type", "Built-in type", "No type", "Pointer type"},
            {"Dynamic memory allocation", "Static allocation", "No allocation", "Stack allocation"},
            {"Reusable code block", "Variable", "Constant", "None"},
            {"Output", "Input", "Both", "Neither"},
            {"Repeated execution", "Single execution", "No execution", "Conditional execution"},
            {"Conditional statement", "Loop statement", "Function", "None"},
            {"Function calling itself", "Function calling another", "No calling", "None"},
            {"Function declarations", "Function definitions", "Main function", "Variables only"}
        };
        return options[(qNum - 1) % options.length][optNum - 1];
    }

    // Discrete Mathematics Questions
    private String getDiscreteMathQuestion(int num) {
        String[] questions = {
            "What is a set?",
            "Graph has?",
            "What is a relation?",
            "Function is?",
            "What is combinatorics?",
            "Permutation is?",
            "Combination is?",
            "What is a tree?",
            "Boolean algebra uses?",
            "What is logic?"
        };
        return questions[(num - 1) % questions.length];
    }

    private String getDiscreteMathOption(int qNum, int optNum) {
        String[][] options = {
            {"Collection of elements", "Single element", "No elements", "Ordered list"},
            {"Vertices and edges", "Only vertices", "Only edges", "Neither"},
            {"Set of ordered pairs", "Single pair", "No pairs", "Unordered pairs"},
            {"Special relation", "Any relation", "No relation", "Multiple relations"},
            {"Counting", "Measuring", "Weighing", "None"},
            {"Order matters", "Order doesn't matter", "No order", "Random order"},
            {"Order doesn't matter", "Order matters", "No selection", "Random selection"},
            {"Connected acyclic graph", "Cyclic graph", "Disconnected graph", "None"},
            {"0 and 1", "0, 1, 2", "All numbers", "No numbers"},
            {"Reasoning", "Counting", "Measuring", "None"}
        };
        return options[(qNum - 1) % options.length][optNum - 1];
    }

    // Computer Organization Questions
    private String getComputerOrganizationQuestion(int num) {
        String[] questions = {
            "CPU stands for?",
            "ALU performs?",
            "What is a register?",
            "Cache memory is?",
            "What is pipelining?",
            "Von Neumann architecture has?",
            "What is instruction cycle?",
            "Bus is used for?",
            "What is addressing mode?",
            "RISC stands for?"
        };
        return questions[(num - 1) % questions.length];
    }

    private String getComputerOrganizationOption(int qNum, int optNum) {
        String[][] options = {
            {"Central Processing Unit", "Central Program Unit", "Computer Processing Unit", "None"},
            {"Arithmetic and logic operations", "Only arithmetic", "Only logic", "Neither"},
            {"Fast CPU memory", "Slow memory", "Disk storage", "None"},
            {"Fast memory", "Slow memory", "Permanent storage", "Input device"},
            {"Parallel execution", "Sequential execution", "No execution", "Random execution"},
            {"Shared memory", "Separate memory", "No memory", "Cache only"},
            {"Fetch-decode-execute", "Fetch only", "Execute only", "Decode only"},
            {"Data transfer", "Data storage", "Data processing", "None"},
            {"Access operand method", "Execute method", "Fetch method", "None"},
            {"Reduced Instruction Set Computer", "Rich Instruction Set Computer", "None", "Random Instruction Set Computer"}
        };
        return options[(qNum - 1) % options.length][optNum - 1];
    }

    // Engineering Graphics Questions
    private String getEngineeringGraphicsQuestion(int num) {
        String[] questions = {
            "Orthographic projection shows?",
            "Isometric view is?",
            "What is a section view?",
            "Dimensioning is?",
            "What is scaling?",
            "First angle projection is?",
            "Third angle projection is?",
            "What is hatching?",
            "Auxiliary view shows?",
            "What is a detail drawing?"
        };
        return questions[(num - 1) % questions.length];
    }

    private String getEngineeringGraphicsOption(int qNum, int optNum) {
        String[][] options = {
            {"Multiple 2D views", "Single 3D view", "Perspective view", "None"},
            {"3D representation", "2D representation", "Perspective view", "None"},
            {"Internal features", "External features", "Both", "Neither"},
            {"Size specification", "Shape specification", "Material specification", "None"},
            {"Size adjustment", "Shape adjustment", "Color adjustment", "None"},
            {"Object below plane", "Object above plane", "No plane", "Side plane"},
            {"Object above plane", "Object below plane", "No plane", "Side plane"},
            {"Section indication", "Dimension indication", "Scale indication", "None"},
            {"Inclined surfaces", "Horizontal surfaces", "Vertical surfaces", "None"},
            {"Single part details", "Assembly details", "Material details", "None"}
        };
        return options[(qNum - 1) % options.length][optNum - 1];
    }
}
