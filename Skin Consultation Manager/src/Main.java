import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Main implements SkinConsultationManager {

    ArrayList<Doctor> doctorsList = new ArrayList<>();
    int indexNum;
    final private int listLength = 10;
    static boolean exit = false;
    Scanner input = new Scanner(System.in);
    private String file = "doctors.csv";

    public ArrayList<Doctor> getDoctorsList() {
        return doctorsList;
    }

    public static void main(String[] args) throws IOException, FileNotFoundException, ClassNotFoundException {
        Main system = new Main();

        while (!exit) {
            system.menuLoop();
        }
    }

    // Getting details and adding Doctor to ArrayList
    @Override
    public void addDoctor() {
        if (!(doctorsList.size() < listLength)) {
            System.out.println("Cannot add anymore doctors at the moment.");
        } else {
            String gender = getGender();
            String fn = getFn();
            String sn = getSn();
            String mobileNum = getNum();
            int dobDate = getDate();
            int dobMonth = getMonth();
            int dobYear = getYear();
            String specialisation = getSpecialisation();

            Date DOB = new Date(dobDate, dobMonth, dobYear);
            Doctor doctor = new Doctor(fn, sn);
            doctor.setGender(gender);
            doctor.setSpecialisation(specialisation);
            doctor.setMobileNum(mobileNum);
            doctor.setDOB(DOB);

            doctorsList.add(doctor);
            printDash();
            System.out.println(fn + " " + sn + " (Medical Number: " + doctor.getMedicalLicenceNumber() + ") has been added");
        }
    }

    // Getting Medical Number and removing if exists
    @Override
    public void removeDoctor() {
        if ((doctorsList.isEmpty())) {
            System.out.println("The list is empty, cannot remove anyone");
        } else {
            doctorsList();
            System.out.println("Enter the ID of the doctor you wish to remove");
            int medicalNum = getMedicalNumber();
            boolean deleted = false;

            for (int i = 0; i < doctorsList.size(); i++) {
                if (doctorsList.get(i).getMedicalLicenceNumber() == medicalNum) {
                    doctorsList.remove(i);
                    System.out.println("Doctor ID: " + medicalNum + " has been removed");
                    System.out.println("There are now " + doctorsList.size() + " doctor(s)");
                    deleted = true;
                    break;
                }
            }

            if (!deleted) {
                System.out.println("Doctor does not exist");
            }
        }
    }

    // Printing out all the doctors in the ArrayList
    @Override
    public void doctorsList() {
        Collections.sort(doctorsList);
        if (doctorsList.isEmpty()) {
            System.out.println("No doctors have been created");
        } else {
            System.out.print("Doctors: ");
            System.out.println("");
            for (int i = 0; i < doctorsList.size(); i++) {
                System.out.println(doctorsList.get(i).toString());
            }
        }
    }

    // Save Doctors to file
    @Override
    public void saveData() throws IOException {
        boolean saved = false;
        try {
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            for (int i = 0; i < doctorsList.size(); i++) {
                bw.write(doctorsList.get(i).saveToFile());
                saved = true;
            }
            bw.close();
            fw.close();
        } catch (IOException e) {
            System.out.println("Error");
        }

        if (saved) {
            System.out.println("Data saved");
        } else {
            System.out.println("Data could not be saved");
        }
    }

    // Load Doctors from file
    private void loadData() throws FileNotFoundException, IOException {
        doctorsList.clear();
        boolean restored = false;
        String[] stringArray = new String[20];
        String stringDOB;
        String[] dob = new String[20];
        int medicalNum;
        String line = "";
        String splitBy = ", ";
        String splitDOB = "/";
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            while ((line = br.readLine()) != null) {
                stringArray = line.split(splitBy);
                stringDOB = stringArray[3];
                dob = stringDOB.split(splitDOB);
                medicalNum = Integer.parseInt(stringArray[5]);
                int date = Integer.parseInt(dob[0]);
                int month = Integer.parseInt(dob[1]);
                int year = Integer.parseInt(dob[2]);
                Date DOB = new Date(date, month, year);
                Doctor doc = new Doctor(stringArray[0], stringArray[1]);
                doc.setMobileNum(stringArray[2]);
                doc.setDOB(DOB);
                doc.setSpecialisation(stringArray[4]);
                doc.setMedicalLicenceNumber(medicalNum);
                doc.setGender(stringArray[6]);
                doctorsList.add(doc);
                restored = true;

            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error");
        } catch (NullPointerException e) {
            System.out.println("File is empty");
        }

        if (restored) {
            System.out.println("Data restored");
        } else {
            System.out.println("Data could not be restored");
        }

    }

    // View Dotors in a table
    private void doctorTable() {
        if (doctorsList.isEmpty()) {
            System.out.println("There are currently no Doctors to be displayed");
        } else {
            DoctorsList listOfDoctors = new DoctorsList(doctorsList);
            JTable myTable = new JTable(listOfDoctors);
            myTable.setBackground(Color.orange);
            myTable.setAutoCreateRowSorter(true);
            JScrollPane panel = new JScrollPane(myTable);
            JFrame frame = new JFrame();
            frame.add(panel);
            frame.setTitle("List of Doctors");
            frame.setVisible(true);
            frame.setSize(1050, 400);
        }
    }

    // Table to select a Doctor
    public void addingConsultation(ArrayList<Doctor> doctorsList) {
        if (doctorsList.isEmpty()) {
            System.out.println("There are currently no Doctors to be displayed");
        } else {
            DoctorsAvailable doctors = new DoctorsAvailable(doctorsList);
            JTable table = new JTable(doctors);
            table.setAutoCreateRowSorter(true);
            table.setBackground(Color.orange);
            JScrollPane p1 = new JScrollPane(table);
            JFrame f = new JFrame();
            f.add(p1);
            f.setTitle("Select a Doctor");
            f.setVisible(true);
            f.setSize(1050, 400);
            ListSelectionModel model = table.getSelectionModel();
            // Listener to open consultation form
            model.addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    if (!model.isSelectionEmpty()) {
                        indexNum = model.getMinSelectionIndex();
                        f.dispose();
                        AddConsultation con = new AddConsultation(indexNum, doctorsList);
                    }
                }
            });
        }
    }

    // View consultation details in a table
    private void viewConsultations() {
        if (doctorsList.isEmpty()) {
            System.out.println("There are currently no Doctors to be displayed");
        } else {
            ViewConsultation doc = new ViewConsultation(doctorsList);
            JTable myTable = new JTable(doc);
            myTable.setBackground(Color.orange);
            myTable.setAutoCreateRowSorter(true);
            JScrollPane panel = new JScrollPane(myTable);
            JFrame frame = new JFrame();
            frame.add(panel);
            frame.setTitle("View Consultations");
            frame.setVisible(true);
            frame.setSize(1280, 400);
            frame.setResizable(false);
        }
    }

    // View consultation in the console
    private void viewConsInConsole() {
        if (doctorsList.isEmpty()) {
            System.out.println("There are currently no Doctors to be displayed");
        } else {
            for (int i = 0; i < doctorsList.size(); i++) {
                if (!(doctorsList.get(i).getConsultationList().isEmpty())) {
                    System.out.println(doctorsList.get(i).consultations());
                } else {
                    System.out.println("No consultations have been added yet with " + doctorsList.get(i).getFirstName() + " " + doctorsList.get(i).getSurname());
                }
            }
        }
    }

    // Exit the program
    private void exit() {
        exit = true;
        System.exit(0);
    }

    // Save and Exit the program
    private void saveAndExit() throws IOException {
        saveData();
        exit();
    }

    // Loop through the options
    private void menuLoop() throws IOException, FileNotFoundException, ClassNotFoundException {
        int num = -1;
        while (num < 0) {
            menuOptions();
            try {
                int choice = Integer.valueOf(input.nextLine());
                num = 1;
                switch (choice) {
                    case 1:
                        addDoctor();
                        break;
                    case 2:
                        removeDoctor();
                        break;
                    case 3:
                        doctorsList();
                        break;
                    case 4:
                        saveData();
                        break;
                    case 5:
                        loadData();
                        break;
                    case 6:
                        doctorTable();
                        break;
                    case 7:
                        addingConsultation(doctorsList);
                        break;
                    case 8:
                        viewConsultations();
                        break;
                    case 9:
                        viewConsInConsole();
                        break;
                    case 10:
                        saveAndExit();
                    case 11:
                        exit();
                        break;
                    default:
                        System.out.println("Option unavailable");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid option entered");
                num = -1;
            }
        }
    }

    // Validating name
    private boolean isWord(String name) {
        return Pattern.matches("[a-zA-Z]+", name);
    }

    // Validating specialisation
    private boolean isSpecialisation(String input) {
        return Pattern.matches("[a-zA-Z\\s]+", input);
    }

    // Validating gender
    private boolean isGender(String gender) {
        return gender.equalsIgnoreCase("Male") || gender.equalsIgnoreCase("Female") || gender.equalsIgnoreCase("Other");
    }

    // Validating date
    private boolean validDate(int date) {
        return date >= 1 && date <= 31;
    }

    // Validating month
    private boolean validMonth(int month) {
        return month >= 1 && month <= 12;
    }

    // Validating year
    private boolean validYear(int year) {
        return year >= 1888 && year <= 2005;
    }

    // Validating phone number
    private boolean validateNum(String mobileNum) {
        return mobileNum.charAt(0) == '0' && mobileNum.charAt(1) == '7' && mobileNum.length() == 11 && mobileNum.matches("[0-9]+");
    }

    // Getting gender
    private String getGender() {
        String gender = "a";
        while (!isGender(gender)) {
            System.out.print("Enter your gender (Male, Female or Other): ");
            gender = input.next();
            gender = gender.substring(0, 1).toUpperCase() + gender.substring(1);
        }
        return gender;
    }

    // Getting first name
    private String getFn() {
        String fn = "123";
        while (!isWord(fn)) {
            System.out.print("Enter First Name: ");
            fn = input.next();
            fn = fn.substring(0, 1).toUpperCase() + fn.substring(1);
            input.nextLine();
        }
        return fn;
    }

    // Getting surname
    private String getSn() {
        String sn = "123";
        while (!isWord(sn)) {
            System.out.print("Enter Surname: ");
            sn = input.next();
            sn = sn.substring(0, 1).toUpperCase() + sn.substring(1);
            input.nextLine();
        }
        return sn;
    }

    // Getting date of birth
    private int getDate() {
        int date = 123;
        while (!validDate(date)) {
            System.out.print("Enter the date you were born: ");
            try {
                date = Integer.valueOf(input.nextLine());
            } catch (InputMismatchException e) {
                System.out.println("Invalid date entered");
                date = 123;
            }
        }
        return date;
    }

    // Getting month of birth
    private int getMonth() {
        int month = 123;
        while (!validMonth(month)) {
            System.out.print("Enter the month you were born: ");
            try {
                month = Integer.valueOf(input.nextLine());
            } catch (InputMismatchException e) {
                System.out.println("Invalid month entered");
                month = 123;
            }
        }
        return month;
    }

    // Getting year of birth
    private int getYear() {
        int year = 123;
        while (!validYear(year)) {
            System.out.print("Enter the year you were born: ");
            try {
                year = Integer.valueOf(input.nextLine());
            } catch (InputMismatchException e) {
                System.out.println("Invalid year entered");
                year = 123;
            }
        }
        return year;
    }

//    // Getting specialisation
//    private String getSpecialisation() {
//        String specialisation = "123";
//        while (!isWord(specialisation)) {
//            System.out.print("Enter your specialisation: ");
//            specialisation = input.nextLine();
//            specialisation = specialisation.substring(0, 1).toUpperCase() + specialisation.substring(1);
//            input.nextLine();
//        }
//        return specialisation;
//    }

    // Getting specialization
    private String getSpecialisation() {
        String specialisation = "123";
        while (!isSpecialisation(specialisation)) {
            System.out.print("Enter your specialization: ");
            specialisation = input.nextLine();

            // Check if the input string has a non-zero length before manipulating it
            if (specialisation.length() > 0) {
                specialisation = specialisation.substring(0, 1).toUpperCase() + specialisation.substring(1);
            } else {
                System.out.println("Invalid specialization. Please enter a valid specialization.");
            }
        }
        return specialisation;
    }

    // Getting phone number
    private String getNum() {
        String mobileNum = "07";
        while (!validateNum(mobileNum)) {
            System.out.print("Enter UK Mobile num: ");
            mobileNum = input.next();
            input.nextLine();
        }
        return mobileNum;
    }

    // Getting Medical Number
    private int getMedicalNumber() {
        int medicalNum = -1;
        while (medicalNum < 0) {
            System.out.print("Enter Medical Licence Number: ");
            try {
                medicalNum = Integer.valueOf(input.nextLine());
            } catch (InputMismatchException e) {
                System.out.println("Invalid medicalNum entered");
                medicalNum = -1;
            }
        }
        return medicalNum;
    }

    // Options available to user
    private void menuOptions() {
        printDash();
        System.out.println("Choose one of the following options: ");
        printStar();
        System.out.println("1: Add a doctor");
        printStar();
        System.out.println("2: Delete a doctor");
        printStar();
        System.out.println("3: View all doctors");
        printStar();
        System.out.println("4: Save data into file");
        printStar();
        System.out.println("5: Load data from file");
        printStar();
        System.out.println("6: View doctors in table");
        printStar();
        System.out.println("7: Add a consultation");
        printStar();
        System.out.println("8: View consultations in table");
        printStar();
        System.out.println("9: View consultations in console");
        printStar();
        System.out.println("10: Save & Exit");
        printStar();
        System.out.println("11: Exit");
        printDash();
    }

    // Printing dash for aesthetics
    private void printDash() {
        System.out.println("----------------------------------------");
    }

    // Prining star for aesthetics
    private void printStar() {
        System.out.print("* ");
    }
}
