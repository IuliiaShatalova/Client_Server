package server.server;

import javax.imageio.IIOException;
import java.io.FileReader;
import java.io.*;

public class FileHandler {
    static File messages = new File("src/main/java/server/messages.txt");

    public static StringBuilder read() {
        StringBuilder chatLog = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(messages))) {
            String line;
            while ((line = br.readLine()) != null) {
                chatLog.append(line).append("\n");
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return chatLog;
    }

    public void save(String message) throws IIOException {
        try (FileWriter fw = new FileWriter(messages, true)){
            fw.write(message + "\n");
            fw.flush();
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
