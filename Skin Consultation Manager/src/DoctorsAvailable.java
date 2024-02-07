import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class DoctorsAvailable extends AbstractTableModel{

    private ArrayList<Doctor> doctorList;
    private String[] colNames = {"First name", "Surname", "Gender", "Phone number", "DOB", "Specialisation", "Medical License Number"};

    DoctorsAvailable(ArrayList<Doctor> doctors) {
        doctorList = doctors;
    }

    @Override
    public int getRowCount() {
        return doctorList.size();
    }

    @Override
    public int getColumnCount() {
        return colNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object temp = null;

        // Column 0 (FirstName):
        if (columnIndex == 0) {
            temp = doctorList.get(rowIndex).getFirstName();
        }

        // Column 1 (Surname):
        if (columnIndex == 1) {
            temp = doctorList.get(rowIndex).getSurname();
        }

        // Column 2 (Gender):
        if (columnIndex == 2) {
            temp = doctorList.get(rowIndex).getGender();
        }

        // Column 3 (Mobile number):
        if (columnIndex == 3) {
            temp = doctorList.get(rowIndex).getMobileNum();
        }

        // Column 4 (DOB):
        if (columnIndex == 4) {
            temp = doctorList.get(rowIndex).getDOB();
        }

        // Column 5 (Specialisation):
        if (columnIndex == 5) {
            temp = doctorList.get(rowIndex).getSpecialisation();
        }

        // Column 6 (Medical License Number):
        if (columnIndex == 6) {
            temp = doctorList.get(rowIndex).getMedicalLicenceNumber();
        }

        return temp;
    }

    @Override
    public String getColumnName(int column) {
        return colNames[column];
    }
}