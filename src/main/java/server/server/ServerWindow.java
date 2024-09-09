package server.server;

import server.client.ClientGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ServerWindow extends JFrame {
    private static final int POS_X = 500;
    private static final int POS_Y = 550;
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;

    private final  JButton btnStart = new JButton("Start");
    private final  JButton btnStop = new JButton("Stop");
    private final JTextArea log = new JTextArea();
    public static boolean isServerWorking;
    List<ClientGUI> accountsList = new ArrayList<>();
    StringBuilder messageHistory;


    public ServerWindow(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(POS_X, POS_Y, WIDTH, HEIGHT);
        setResizable(false);
        setTitle("Chat server");
        setAlwaysOnTop(true);

        isServerWorking = false;

        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isServerWorking = false;
                log.append("Server stopped " + isServerWorking + "\n");
            }
        });

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isServerWorking = true;
                log.append("Server started " + isServerWorking + "\n");
                messageHistory = FileHandler.read();
            }
        });


        JPanel panBottom = new JPanel(new GridLayout(1,2));
        panBottom.add(btnStart);
        panBottom.add(btnStop);
        add(panBottom, BorderLayout.SOUTH);
        add(log);

        setVisible(true);
    }

    public void serverMessage(String s) {
        log.append(s);
    }
}
