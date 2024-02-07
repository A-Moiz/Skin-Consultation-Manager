import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.regex.Pattern;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddConsultation extends JFrame implements ActionListener {

    JFrame frame = new JFrame();
    JFrame warning;
    String title = "Enter Patient Details";
    JPanel p1 = new JPanel();

    // width and height for frame and panel
    int width = 280;
    int height = 720;

    ArrayList<Doctor> docList = new ArrayList<>();
    Consultation con = new Consultation();
    Patient patient = new Patient("", "");

    // Array required for encryption
    char[] chars;

    JLabel gender, fn, sn, mn, dobDate, dobMonth, dobYear, conDate, conMonth, conYear, conHour, notes;
    JTextField genderTxt, fnTxt, snTxt, mnTxt, dobdate, dobmonth, dobyear, condate, conmonth, conyear, conhour, conNotes;

    int num;
    int incrementNum = 5;

    // Checking if date and time has been taken with the chosen doctor
    boolean dateTaken = false;
    boolean timeTaken = false;

    // Checking if every field is correctly entered
    boolean validGender = false;
    boolean validFn = false;
    boolean validSn = false;
    boolean validNum = false;

    boolean validDate = false;
    boolean validMonth = false;
    boolean validYear = false;

    boolean validConDate = false;
    boolean validConMonth = false;
    boolean validConYear = false;

    boolean validHour = false;

    boolean patientExists = false;

    JButton submit;

    private Time time;
    private Date consulDate;

    AddConsultation(int indexNum, ArrayList<Doctor> doctors) {
        frame.setTitle(title);
        frame.setSize(width, height);
        frame.setVisible(true);
        frame.setResizable(false);

        // Setting num to the index number of the doctor selected
        num = indexNum;

        docList = doctors;

        warning = new JFrame();

        gender = new JLabel("Enter your gender (Male, Female or Other): ");
        genderTxt = new JTextField(15);

        fn = new JLabel("Enter First name: ");
        fnTxt = new JTextField(15);

        sn = new JLabel("Enter Surname: ");
        snTxt = new JTextField(15);

        mn = new JLabel("Enter UK Mobile number: ");
        mnTxt = new JTextField(15);

        dobDate = new JLabel("Enter the date you were born: ");
        dobdate = new JTextField(15);

        dobMonth = new JLabel("Enter the month you were born: ");
        dobmonth = new JTextField(15);

        dobYear = new JLabel("Enter the year you were born: ");
        dobyear = new JTextField(15);

        conDate = new JLabel("Enter the date of your consultation: ");
        condate = new JTextField(15);

        conMonth = new JLabel("Enter the month of your consultation: ");
        conmonth = new JTextField(15);

        conYear = new JLabel("Enter the year of your consultation: ");
        conyear = new JTextField(15);

        conHour = new JLabel("Enter hour time of consultation: ");
        conhour = new JTextField(15);

        notes = new JLabel("Enter any notes: ");
        conNotes = new JTextField(15);

        submit = new JButton("Submit");
        submit.addActionListener(this);

        p1.setSize(width, height);
        p1.setBackground(Color.orange);

        p1.add(gender);
        p1.add(genderTxt);

        p1.add(fn);
        p1.add(fnTxt);

        p1.add(sn);
        p1.add(snTxt);

        p1.add(mn);
        p1.add(mnTxt);

        p1.add(dobDate);
        p1.add(dobdate);

        p1.add(dobMonth);
        p1.add(dobmonth);

        p1.add(dobYear);
        p1.add(dobyear);

        p1.add(conDate);
        p1.add(condate);

        p1.add(conMonth);
        p1.add(conmonth);

        p1.add(conYear);
        p1.add(conyear);

        p1.add(conHour);
        p1.add(conhour);

        p1.add(notes);
        p1.add(conNotes);

        p1.add(submit);

        frame.add(p1);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit) {
            Main wscm = new Main();

            // Checking if everything has been filled in
            if (genderTxt.getText().isEmpty() || fnTxt.getText().isEmpty() || snTxt.getText().isEmpty() || mnTxt.getText().isEmpty() || dobDate.getText().isEmpty() || dobMonth.getText().isEmpty() || dobYear.getText().isEmpty() || condate.getText().isEmpty() || conmonth.getText().isEmpty() || conyear.getText().isEmpty() || conhour.getText().isEmpty() || conNotes.getText().isEmpty()) {
                JOptionPane.showMessageDialog(warning, "Please fill in all the blank spaces", "Alert", JOptionPane.WARNING_MESSAGE);
            } else {

                // Getting information from Text Fields and validating them
                String gender = genderTxt.getText();
                String fn = fnTxt.getText();
                String sn = snTxt.getText();
                String mn = mnTxt.getText();
                String note = conNotes.getText();

                if (!isGender(gender)) {
                    JOptionPane.showMessageDialog(warning, "Invalid gender entered", "Alert", JOptionPane.WARNING_MESSAGE);
                } else {
                    gender = gender.substring(0, 1).toUpperCase() + gender.substring(1);
                    gender(gender);
                    validGender = true;
                }

                if (!isWord(fn)) {
                    JOptionPane.showMessageDialog(warning, "Invalid first name entered", "Alert", JOptionPane.WARNING_MESSAGE);
                } else {
                    fn = fn.substring(0, 1).toUpperCase() + fn.substring(1);
                    firstName(fn);
                    validFn = true;
                }

                if (!isWord(sn)) {
                    JOptionPane.showMessageDialog(warning, "Invalid surname entered", "Alert", JOptionPane.WARNING_MESSAGE);
                } else {
                    sn = sn.substring(0, 1).toUpperCase() + sn.substring(1);
                    surname(sn);
                    validSn = true;
                }

                if (!validateNum(mn)) {
                    JOptionPane.showMessageDialog(warning, "Invalid UK number entered", "Alert", JOptionPane.WARNING_MESSAGE);
                } else {
                    mobileNum(mn);
                    validNum = true;
                }

                try {
                    int date = Integer.parseInt(dobdate.getText().trim());
                    int month = Integer.parseInt(dobmonth.getText().trim());
                    int year = Integer.parseInt(dobyear.getText().trim());

                    int conDate = Integer.parseInt(condate.getText().trim());
                    int conMonth = Integer.parseInt(conmonth.getText().trim());
                    int conYear = Integer.parseInt(conyear.getText().trim());

                    if (!validDate(date)) {
                        JOptionPane.showMessageDialog(warning, "Invalid date entered for DOB", "Alert", JOptionPane.WARNING_MESSAGE);
                    } else {
                        validDate = true;
                    }

                    if (!validMonth(month)) {
                        JOptionPane.showMessageDialog(warning, "Invalid month entered for DOB", "Alert", JOptionPane.WARNING_MESSAGE);
                    } else {
                        validMonth = true;
                    }

                    if (!validYear(year)) {
                        JOptionPane.showMessageDialog(warning, "Invalid year entered for DOB.\nYou must be at least 18 years of age to book a consultation", "Alert", JOptionPane.WARNING_MESSAGE);
                    } else {
                        validYear = true;
                    }

                    if (validDate && validMonth && validYear) {
                        Date DOB = new Date(date, month, year);
                        dob(DOB);
                    }

                    if (!validDate(conDate)) {
                        JOptionPane.showMessageDialog(warning, "Invalid date entered", "Alert", JOptionPane.WARNING_MESSAGE);
                    } else {
                        validConDate = true;
                    }

                    if (!validMonth(conMonth)) {
                        JOptionPane.showMessageDialog(warning, "Invalid month entered", "Alert", JOptionPane.WARNING_MESSAGE);
                    } else {
                        validConMonth = true;
                    }

                    if (!validConYear(conYear)) {
                        JOptionPane.showMessageDialog(warning, "Invalid year entered.\nConsultation can be booked for 2023 and onwards", "Alert", JOptionPane.WARNING_MESSAGE);
                    } else {
                        validConYear = true;
                    }

                    if (validConDate && validConMonth && validConYear) {
                        consulDate = new Date(conDate, conMonth, conYear);
                        conDate(consulDate);
                    }

                    int hour = Integer.parseInt(conhour.getText().trim());

                    if (!validHour(hour)) {
                        JOptionPane.showMessageDialog(warning, "Invalid hours entered\nConsultations can be booked from 6:00 to 21:00", "Alert", JOptionPane.WARNING_MESSAGE);
                    } else {
                        validHour = true;
                    }

                    if (validHour) {
                        time = new Time(hour, 0);
                        conTime(time);
                    }

                    dateTaken(consulDate);
                    timeTaken(time);

                    // Assigning new Doctor if not available 
                    if (validGender && validFn && validSn && validNum && validDate && validMonth && validYear && validConDate && validConMonth && validConYear && validHour) {
                        if (docList.get(num).getConsultationList().isEmpty() || (!(dateTaken && timeTaken))) {
                            encrypt(note);
                            addConsultation();
                        } else {
                            if ((dateTaken && timeTaken)) {
                                if (num >= 0 && num < docList.size() - 1) {
                                    num++;
                                    dateTaken(consulDate);
                                    timeTaken(time);
                                    if (docList.get(num).getConsultationList().isEmpty() || (!(dateTaken && timeTaken))) {
                                        encrypt(note);
                                        JOptionPane.showMessageDialog(warning, "Selected Doctor was not available therefore a new Doctor has been assigned", "Alert", JOptionPane.WARNING_MESSAGE);
                                        addConsultation();
                                    } else {
                                        JOptionPane.showMessageDialog(warning, "All Doctors are currently busy at the seleted date and time.\nAdd some more doctors or choose a different date or time.", "Alert", JOptionPane.WARNING_MESSAGE);
                                        frame.dispose();
                                        frame.dispose();
                                        wscm.addingConsultation(docList);
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(warning, "All Doctors are currently busy at the seleted date and time.\nAdd some more doctors or choose a different date or time.", "Alert", JOptionPane.WARNING_MESSAGE);
                                    frame.dispose();
                                    frame.dispose();
                                    wscm.addingConsultation(docList);
                                }
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(warning, "Some details have not been entered correctly", "Alert", JOptionPane.WARNING_MESSAGE);
                        patientExists = false;
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(warning, "DOB, Date or Time is not in the correct format", "Alert", JOptionPane.WARNING_MESSAGE);
                }
            }
        }
    }

    // Encrypting Patient Notes
    private void encrypt(String note) {
        setNotes(note);
        chars = con.getNotes().toCharArray();

        for (int i = 0; i < chars.length; i++) {
            chars[i] += incrementNum;
        }
    }

    // Checking if date has already been taken
    private void dateTaken(Date consulDate) {
        try {
            for (int i = 0; i < docList.size(); i++) {
                if (!docList.get(num).getConsultationList().isEmpty()) {
                    if (docList.get(num).getConsultationList().get(i).getDate().equals(consulDate)) {
                        dateTaken = true;
                        break;
                    }
                }
            }
        } catch (IndexOutOfBoundsException e) {
        }
    }

    // Checking if time has already been taken
    private void timeTaken(Time consulTime) {
        try {
            for (int i = 0; i < docList.size(); i++) {
                if (!docList.get(num).getConsultationList().isEmpty()) {
                    if (docList.get(num).getConsultationList().get(i).getTime().equals(consulTime)) {
                        timeTaken = true;
                        break;
                    }
                }
            }
        } catch (IndexOutOfBoundsException e) {
        }
    }

    // Setting details
    private void firstName(String fn) {
        patient.setFirstName(fn);
    }

    private void surname(String sn) {
        patient.setSurname(sn);
    }

    private void gender(String gender) {
        patient.setGender(gender);
    }

    private void mobileNum(String mn) {
        patient.setMobileNum(mn);
    }

    private void dob(Date dob) {
        patient.setDOB(dob);
    }

    private void conDate(Date date) {
        con.setDate(date);
    }

    private void conTime(Time time) {
        con.setTime(time);
    }

    private void setNotes(String notes) {
        con.setNotes(notes);
    }

    private void addPatient(Patient patient) {
        con.setPatient(patient);
    }

    // Checking if Patient already exists
    private boolean checkPatient() {
        for (Doctor d : docList) {
            for (Consultation c : d.getConsultationList()) {
                if (c.getPatient().equals(this.patient)) {
                    patientExists = true;
                }
            }
        }
        return patientExists;
    }

    // Adding Patient, Cost and Consultation
    private void addConsultation() {
        addPatient(patient);
        if (checkPatient()) {
            con.setCost(25);
        } else {
            con.setCost(15);
        }
        docList.get(num).getConsultationList().add(con);
        completed();
    }

    private void completed() {
        genderTxt.setEditable(false);
        fnTxt.setEditable(false);
        snTxt.setEditable(false);
        mnTxt.setEditable(false);
        dobdate.setEditable(false);
        dobmonth.setEditable(false);
        dobyear.setEditable(false);
        condate.setEditable(false);
        conmonth.setEditable(false);
        conyear.setEditable(false);
        conhour.setEditable(false);
        conNotes.setEditable(false);
        submit.setEnabled(false);
        JOptionPane.showMessageDialog(frame, "Consultation has been booked for " + con.getDate() + ", at " + con.getTime() + "\nWith " + docList.get(num).getFirstName() + " " + docList.get(num).getSurname() + "\nCost: £" + con.getCost() + "\nPatient Notes (Encrypted): " + "" + new String(chars) + "\nTo view the decrypted version choose option 8 or 9 in the console menu" + "\nYou may now close this window.");
    }

    // Validate Gender
    private boolean isGender(String gender) {
        return gender.equalsIgnoreCase("Male") || gender.equalsIgnoreCase("Female") || gender.equalsIgnoreCase("Other");
    }

    // Validate Name
    private boolean isWord(String name) {
        return Pattern.matches("[a-zA-Z]+", name);
    }

    // Validate UK Phone Number
    private boolean validateNum(String mobileNum) {
        return mobileNum.charAt(0) == '0' && mobileNum.charAt(1) == '7' && mobileNum.length() == 11 && mobileNum.matches("[0-9]+");
    }

    // Validate date
    private boolean validDate(int date) {
        return date >= 1 && date <= 31;
    }

    // Validate month
    private boolean validMonth(int month) {
        return month >= 1 && month <= 12;
    }

    // Validate year
    private boolean validYear(int year) {
        return year >= 1980 && year <= 2005;
    }

    // Validate year
    private boolean validConYear(int year) {
        return year >= 2023 && year <= 2099;
    }

    // Validate time
    private boolean validHour(int hour) {
        return hour >= 6 && hour <= 21;
    }
}
