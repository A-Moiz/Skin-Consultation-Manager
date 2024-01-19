import java.io.IOException;

public interface SkinConsultationManager {
    public abstract void addDoctor();
    public abstract void removeDoctor();
    public abstract void doctorsList();
    public abstract void saveData() throws IOException;
}
