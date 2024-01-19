import java.io.Serializable;
import java.util.ArrayList;

public class Doctor extends  Person implements Serializable, Comparable<Doctor> {
    private  int medicalLicenceNumber;
    private  String specialisation;
    private ArrayList<Consultation> consultationList;
    static int count = 0;

    Doctor(String firstName, String surname) {
        super(firstName, surname);
        count++;
        consultationList = new ArrayList<>();
        medicalLicenceNumber = count;
    }

    public int getMedicalLicenceNumber() {
        return medicalLicenceNumber;
    }

    public void setMedicalLicenceNumber(int medicalLicenceNumber) {
        this.medicalLicenceNumber = medicalLicenceNumber;
    }

    public String getSpecialisation() {
        return specialisation;
    }

    public void setSpecialisation(String specialisation) {
        this.specialisation = specialisation;
    }

    public ArrayList<Consultation> getConsultationList() {
        return consultationList;
    }

    public void setConsultationList(ArrayList<Consultation> consultationList) {
        this.consultationList = consultationList;
    }

    public String getFullName() {
        String fn = firstName.substring(0, 1).toUpperCase() + firstName.substring(1);
        String sn = surname.substring(0, 1).toUpperCase() + surname.substring(1);
        return fn + " " + sn;
    }

    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        Doctor.count = count;
    }

    @Override
    public int compareTo(Doctor o) {
        return this.surname.compareTo(o.surname);
    }

    @Override
    public String toString() {
        return "ID: " + getMedicalLicenceNumber() + ", Name: " + getFirstName() + " " + getSurname() + ", Gender: " + getGender() + ", DOB: " + getDOB() + ", Specialisation: " + getSpecialisation() + ", Phone Number: " + getMobileNum();
    }

    public  String saveToFile() {
        return "ID: " + getMedicalLicenceNumber() + ", Name: " + getFirstName() + " " + getSurname() + ", Gender: " + getGender() + ", DOB: " + getDOB() + ", Specialisation: " + getSpecialisation() + ", Phone Number: " + getMobileNum();
    }

    public String consultations() {
        return "ID: " + getMedicalLicenceNumber() + ", Name: " + getFirstName() + " " + getSurname() + ", Specialisation: " + getSpecialisation() + ", " + getConsultationList();
    }
}
