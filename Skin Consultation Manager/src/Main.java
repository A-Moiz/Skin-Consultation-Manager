import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main implements  SkinConsultationManager {
    ArrayList<Doctor> doctorsList = new ArrayList<>();
    int indexNum;
    final private int listLength = 10;
    static boolean exit = false;
    Scanner input = new Scanner(System.in);
    private String file = "doctors.csv";

    public  ArrayList<Doctor> getDoctorsList() {
        return doctorsList;
    }

    public static void main(String[] args) {

    }

    @Override
    public void addDoctor() {
        // Checking if space if available in list
        if (!(doctorsList.size() < listLength)) {
            System.out.println("Cannot add anymore doctors at the moment. Please try again later.");
        } else {
            // Getting Doctor details
            String fn = getFn();
            String sn = getSn();
            String mobileNum = getNum();
            String gender = getGender();
            int dobDate = getDate();
            int dobMonth = getMonth();
            int dobYear = getYear();
            String specialisation = getSpecialisation();

            // Creating DOB
            Date DOB = new Date(dobDate, dobMonth, dobYear);
            // Creating Doctor
            Doctor doctor = new Doctor(fn, sn);
            // Adding input details to Doctor
            doctor.setMobileNum(mobileNum);
            doctor.setGender(gender);
            doctor.setDOB(DOB);
            doctor.setSpecialisation(specialisation);
            // Adding Doctor to list
            doctorsList.add(doctor);
            printDash();
            System.out.println(doctor.getFullName() + " (Medical Number: )" + doctor.getMedicalLicenceNumber() + " has been added.");
        }
    }

    @Override
    public void removeDoctor() {

    }

    @Override
    public void doctorsList() {

    }

    @Override
    public void saveData() throws IOException {

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

    // Validating name
    private boolean isWord(String name) {
        return Pattern.matches("[a-zA-Z]+", name);
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

    // Getting specialisation
    private String getSpecialisation() {
        String specialisation = "123";
        while (!isWord(specialisation)) {
            System.out.print("Enter your specialisation: ");
            specialisation = input.next();
            specialisation = specialisation.substring(0, 1).toUpperCase() + specialisation.substring(1);
            input.nextLine();
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

    // Printing dash for separation
    private void printDash() {
        System.out.println("----------------------------------------");
    }

    // Printing star for separation
    private void printStar() {
        System.out.print("* ");
    }
}