package server.client;

import server.server.ServerWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientGUI extends JFrame {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;

    private JTextArea log = new JTextArea();
    private ServerWindow serverWindow;

    private JPanel panelTop;
    private JTextField tfIPAddress;
    private JTextField tfIPort;
    private JTextField tfLogin;
    private JPasswordField tfPassword;
    private JButton btnLogin;
    private String login;

    private JTextArea chat;
    private JPanel chatPanel;
    private JPanel panelBottom;
    private JTextField tfMessage;
    private JButton btnSend;

    public ClientGUI(ServerWindow serverWindow){
        this.serverWindow = serverWindow;
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(WIDTH, HEIGHT);
        setTitle("Chat Client");

        startLoginWindow(serverWindow);
        serverWindow.addToOnline(this);

        log.setEnabled(false);
        JScrollPane scrollLog = new JScrollPane(log);
        add(scrollLog);

        setVisible(true);
    }

    private void startLoginWindow(ServerWindow serverWindow){
        panelTop = new JPanel(new GridLayout(2,3));
        tfIPAddress = new JTextField("127.0.0.1");
        tfIPort = new JTextField("8189");
        tfLogin = new JTextField("Ivan");
        tfPassword = new JPasswordField("12345");
        btnLogin = new JButton("Login");

        panelTop.add(tfIPAddress);
        panelTop.add(tfIPort);
        panelTop.add(tfLogin);
        panelTop.add(tfPassword);
        panelTop.add(btnLogin);
        add(panelTop, BorderLayout.NORTH);

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (serverWindow.getServerStatus() == 0) {
                    throw new RuntimeException();
                }
                else {
                    login = tfLogin.getText();
                    serverWindow.serverMessage(login + " is connected" + "\n");
                    remove(panelTop);
                    startChatWindow();
                    revalidate();
                    repaint();
                }
            }
        });
    }

    private void startChatWindow(){
        chatPanel = new JPanel(new BorderLayout(5,0));
        chat = new JTextArea();
        chatPanel.add(chat);

        panelBottom = new JPanel(new BorderLayout());
        tfMessage = new JTextField();
        btnSend = new JButton("Send");

        panelBottom.add(tfMessage, BorderLayout.CENTER);
        panelBottom.add(btnSend, BorderLayout.EAST);

        add(panelBottom, BorderLayout.SOUTH);
        add(chatPanel, BorderLayout.NORTH);

        chat.append("Successful connection " + "\n");
        chat.append(serverWindow.loadChat().toString());

        btnSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = tfMessage.getText();
                if (!message.isEmpty()) {
                    sendMessage(message);
                    tfMessage.setText("");
                }
            }
        });
    }

    private void sendMessage(String m) {
        serverWindow.showMessageInChat(login + "\n" + m);
    }

    public void receiveMessage(String m) {
        chat.append(m + "\n");
    }
}
