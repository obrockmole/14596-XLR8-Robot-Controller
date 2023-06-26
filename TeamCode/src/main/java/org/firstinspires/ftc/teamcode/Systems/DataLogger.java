package org.firstinspires.ftc.teamcode.Systems;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class DataLogger {
    File file;
    FileWriter writer;

    public DataLogger(String fileName) {
        file = new File(fileName);
    }

    public DataLogger(File file) {
        this.file = file;
    }

    public void startLogging(String header) {
        try {
            writer = new FileWriter(file);
            writer.write(header);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeData(String data) {
        try {
            writer.write(data);
            writer.write(System.lineSeparator());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void stopLogging() {
        try {
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
