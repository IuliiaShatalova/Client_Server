package server.server;

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


//    public void writeToFile(Message message) {
//        String path = "src/main/resources/log.txt";
//        Path file = Paths.get(path);
//        if (Files.exists(file)) {
//            try (FileWriter writer = new FileWriter(path, true)) {
//                writer.append(message.getAuthorMessage()).append(";")
//                        .append(message.getTextMessage()).append(";")
//                        .append(message.getDateMessage())
//                        .append("\n");
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        } else {
//            new File(path);
//            writeToFile(message);
//        }
//    }


}