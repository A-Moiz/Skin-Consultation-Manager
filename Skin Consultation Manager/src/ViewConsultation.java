import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class ViewConsultation extends AbstractTableModel {

    private ArrayList<Doctor> doctorList;
    private String[] colNames = {"First name", "Surname", "Consultations"};

    ViewConsultation(ArrayList<Doctor> doctorList) {
        this.doctorList = doctorList;
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

        // Column 2 (Consultation):
        if (columnIndex == 2) {
            temp = doctorList.get(rowIndex).getConsultationList();
        }

        return temp;
    }

    @Override
    public String getColumnName(int column) {
        return colNames[column];
    }
}
