package org.firstinspires.ftc.teamcode.Systems;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataLogger {
    File file;
    List<String> data = new ArrayList<>();

    public DataLogger(String fileName) {
        file = new File(fileName);
    }

    public DataLogger(File file) {
        this.file = file;
        this.file.setWritable(true);
    }

    public void startLogging(String header) {
        data.add(header);
    }

    public void writeData(String data) {
        this.data.add(data);
    }

    public void stopLogging() {
        try (FileWriter writer = new FileWriter(file)) {
            for (String line : data) {
                writer.write(line + "\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
