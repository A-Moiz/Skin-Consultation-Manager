public class Patient extends Person {
    private int uniqueID;
    private static int count = 0;

    Patient(String firstName, String surname) {
        super(firstName, surname);
        count++;
        uniqueID = count;
    }

    public int getUniqueID() {
        return uniqueID;
    }

    public void setUniqueID(int UniqueID) {
        this.uniqueID = UniqueID;
    }

    @Override
    public String toString() {
        return "Gender: " + getGender() + ", Name: " + getFirstName() + " " + getSurname() + ", Phone number: " + getMobileNum() + ", DOB: " + getDOB() + ", Unique ID: " + getUniqueID() + "\n";
    }

    // Comparing 2 Patients
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }

        Patient patient = (Patient) obj;
        return this.getFirstName().equalsIgnoreCase(patient.getFirstName()) && this.getSurname().equalsIgnoreCase(patient.getSurname()) && this.getDOB().getDay() == patient.getDOB().getDay() && this.getDOB().getMonth() == patient.getDOB().getMonth() && this.getDOB().getYear() == patient.getDOB().getYear() && this.getMobileNum().equalsIgnoreCase(patient.getMobileNum());
    }
}