package com.deepak.Study_Nest.config;

import com.deepak.Study_Nest.dao.ModuleRepository;
import com.deepak.Study_Nest.dao.QuestionRepository;
import com.deepak.Study_Nest.entity.Module;
import com.deepak.Study_Nest.entity.Question;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

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
        } else if (moduleName.contains("digital logic") || moduleName.contains("logic design")) {
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
        } else if (moduleName.contains("digital logic")) {
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
}
