package readWriteCSV;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;

public class Audit {
    private Audit() {
    }

    private static Audit instance = null;

    public static Audit create_instance() {
        if (instance == null) {
            instance = new Audit();
        }
        return instance;
    }

    public void output_audit(String audit) {
        Timestamp time = new Timestamp(System.currentTimeMillis());

        try (var out = new BufferedWriter(new FileWriter("src/audit", true))) {
            out.write(audit + "," + time.toString() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}