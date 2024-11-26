# Library Management System (LMS)

Welcome to the **Library Management System**! This project is designed to streamline library operations and provide an efficient way to manage books, members, and borrowing activities. The system follows an **object-oriented programming (OOP)** approach, ensuring scalability, maintainability, and security.

---

## 🚀 Features
### User Management
- **Secure Login System**: Passwords are securely hashed using SHA-256.
- **Role-Based Access**:
  - **Librarians**: Can manage books and view borrowing records of all members.
  - **Members**: Can borrow and return books, view their borrowing history, and pay fines.

### Library Operations
- **Librarian Functions**:
  - Add, update, or remove books.
  - View all borrowing records.
  - Manage overdue fines.
- **Member Functions**:
  - Borrow and return books.
  - View borrowing history.
  - Pay overdue fines.

### Additional Features
- **Input Validation**: Prevents invalid data entry.
- **Fine Management**: Calculates overdue fines automatically.
- **OOP Best Practices**: Abstract classes, inheritance, encapsulation, and method overriding.

---

## 🛠️ How to Use
### Prerequisites
- **Java Development Kit (JDK)**: Version 8 or higher.
- **IDE**: IntelliJ IDEA, Eclipse, or any Java-compatible IDE.

### Steps to Run the Project
1. Clone the repository:
   ```bash
   git clone https://github.com/Deepak2212-coder/LMS.git
   ```
2. Open the project in your preferred IDE.
3. Compile and run the `LibrarySystem.java` file.
4. Use the following test credentials:
   - **Librarian**:
     - Username: `librarian1`
     - Password: `password`
   - **Member**:
     - Username: `member1`
     - Password: `password`

---

## 👥 Team Members
This project was developed collaboratively by:

| **Name**   | **Role**                               | **Contributions**                                                                 |
|------------|---------------------------------------|-----------------------------------------------------------------------------------|
| **Deepak**  | Lead Developer                        | Designed class hierarchies and implemented borrowing/returning and fine management systems. |
| **Aryan** | Security Specialist & Validation Lead | Developed secure login features, password hashing, and input validation mechanisms. |
| **Noval**  | Librarian Module Developer            | Focused on book management, borrowing record handling, and ensuring role-based access control. |

### Contribution Acknowledgment
We also used **AI tools** like ChatGPT for:
- Drafting the initial project structure.
- Debugging and refining code.
- Enhancing readability and performance.

---

## 📊 Project Results
### Achievements
- A functional library management system with user-friendly features.
- Secure login system with role-based access control.
- Smooth operations for borrowing/returning books and fine management.

### Sample Output
#### Librarian:
```plaintext
Enter username: librarian1
Enter password: password
Login successful!
Options: [Add Book, Remove Book, View Records, Logout]
```

#### Member:
```plaintext
Enter username: member1
Enter password: password
Login successful!
Options: [Borrow Book, Return Book, View History, Pay Fine, Logout]
Book borrowed: "To Kill a Mockingbird"
```

---

## 📝 Future Enhancements
1. **Database Integration**: Persist user and book data.
2. **Advanced Security**: Use bcrypt for password hashing.
3. **Notifications**: Send reminders for overdue books and unpaid fines.
4. **GUI Interface**: Replace the console application with a graphical interface for better usability.

---

## 🤝 Acknowledgments
This project wouldn't have been possible without the combined effort of:
- **Aryan**, **Deepak**, and **Noval** for their hard work and dedication.
- AI tools for providing guidance and debugging support.

---

## 💻 Repository Link
Find the complete project here: [Library Management System](https://github.com/Deepak2212-coder/LMS.git)  

---

### Contact
Feel free to reach out to the team for suggestions or queries:  
- Aryan: bu2022ugcs13@bahrauniversity.edu.in 
- Deepak: bu2022ugcs17@bahrauniversity.edu.in  
- Noval: bu2022ugcs40@bahrauniversity.edu.in

Let us know how you find the system and any improvements you'd like to see!
