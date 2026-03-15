# Westminster Skin Consultation Manager

A Java-based desktop application designed to manage doctors and patient consultations for a skin consultation clinic. The system allows administrators to manage doctor records, schedule consultations, and store data persistently using file storage.

The application combines a **console-based management system** with **Swing GUI tables** for viewing doctors and consultations.

---

## Features

### Doctor Management
- Add new doctors with details including:
  - First name
  - Surname
  - Gender
  - Date of birth
  - Mobile number
  - Specialisation
- Remove doctors using their **medical licence number**
- View all registered doctors sorted alphabetically
- Limit of **10 doctors** enforced by the system

### Consultation Management
- Select doctors through an interactive **Swing JTable**
- Add consultations linked to a selected doctor
- View consultations in:
  - Console view
  - Graphical table view

### Data Persistence
- Save doctor data to a **CSV file**
- Load previously saved data from file
- Ensures data can be restored when the program restarts

### Graphical Interface
- Java **Swing GUI tables** for:
  - Viewing doctor records
  - Selecting doctors
  - Viewing consultations
- Tables support **sorting and selection**

### Input Validation
The system includes validation for:
- Names (alphabetic characters only)
- Gender values
- Specialisation fields
- UK mobile numbers
- Valid dates of birth
- Medical licence numbers

---

## Technologies Used

- **Java**
- **Java Swing** for GUI components
- **Collections Framework**
- **File I/O (BufferedReader / BufferedWriter)**
- **Regex validation**
- **Object-Oriented Programming**

---

## System Architecture

The system follows an **object-oriented design** using inheritance, encapsulation, and modular class structures.

### Core Class Hierarchy

```
Person (Abstract Class)
│
├── Doctor
│     └── Consultation (list associated with each doctor)
│
└── Patient
```
### Core Classes

| Class | Responsibility |
|------|------|
| `Main` | Core application controller that manages the console menu, system operations, and program flow |
| `Person` | Abstract base class that stores shared attributes such as first name, surname, gender, mobile number, and date of birth |
| `Doctor` | Extends `Person` and represents a doctor in the clinic, including medical licence number, specialisation, and a list of consultations |
| `Patient` | Extends `Person` and represents a patient with a unique ID and personal details |
| `Consultation` | Represents a consultation between a patient and a doctor |
| `DoctorsList` | Swing `TableModel` used to display doctors in a GUI table |
| `DoctorsAvailable` | Table model used when selecting a doctor for a consultation |
| `AddConsultation` | Handles the creation of consultations through the GUI |
| `ViewConsultation` | Displays consultation records in a graphical table view |
| `Date` | Stores and manages date-related information |

### Key Design Concepts

**Inheritance**
- `Doctor` and `Patient` inherit common properties from the `Person` class.
- Reduces duplication by centralising shared attributes.

**Encapsulation**
- Fields are private or protected and accessed through getters and setters.
- Ensures controlled access to object data.

**Polymorphism**
- `Doctor` implements `Comparable<Doctor>` to enable sorting doctors by surname.
- `equals()` is overridden in `Patient` to compare patients based on personal details.

**Composition**
- Each `Doctor` contains an `ArrayList<Consultation>` representing all consultations associated with that doctor.

**Serialization**
- The `Doctor` class implements `Serializable`, allowing doctor objects to be saved and restored when persisting system data.

This modular architecture separates **data models**, **business logic**, and **user interface components**, making the system easier to maintain and extend.

---

## How the System Works

1. The program launches a **console menu interface**.
2. The user selects options to:
   - Manage doctors
   - Add consultations
   - View data
3. Doctor information is stored in an **ArrayList** during runtime.
4. Data can be **saved to or restored from a CSV file**.
5. Swing tables provide a **graphical way to view and select data**.

---

## Example Menu
```
1: Add a doctor
2: Delete a doctor
3: View all doctors
4: Save data into file
5: Load data from file
6: View doctors in table
7: Add a consultation
8: View consultations in table
9: View consultations in console
10: Save & Exit
```
---

## Key Learning Outcomes

This project demonstrates:

- Object-Oriented Programming in Java
- Data validation and error handling
- File persistence using CSV
- Building graphical interfaces with Swing
- Managing collections with ArrayLists
- Designing modular software systems

---

## Author

Developed as part of a **Computer Science coursework project** focusing on software design, Java programming, and GUI development.
