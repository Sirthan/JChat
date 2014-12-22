package jchat.GUI;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JTextPane;
import javax.swing.JButton;
import javax.swing.border.Border;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import jchat.networking.MessageListener;
import jchat.networking.MessageTransmitter;
import jchat.networking.WriteableGui;

public class MainGui extends JFrame implements WriteableGui{
	JTextArea Chat;
	JTextArea IP;
	JTextArea Users;
	JTextArea Message;
	JTextArea ReceivePort;
	JTextArea HostNum;
	JScrollPane chat;
	JButton Send;
	JButton Listener;
	MessageListener MessageListener;
	public MainGui() {
		setFont(new Font("Times New Roman", Font.BOLD, 13));
		getContentPane().setBackground(Color.BLACK);
		setTitle("JChat");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setSize(550,390);
		getContentPane().setLayout(null);
		
		IP = new JTextArea();
		IP.setBounds(180, 10, 240, 20);
		IP.setText("Enter IP here");
		IP.setFont(new Font("Times New Roman", Font.BOLD, 12));
		IP.setBackground(Color.GRAY);
		getContentPane().add(IP);
		
		HostNum = new JTextArea();
		HostNum.setFont(new Font("Times New Roman", Font.BOLD, 12));
		HostNum.setBackground(Color.GRAY);
		HostNum.setText("Host#");
		HostNum.setBounds(430, 10, 80, 20);
		getContentPane().add(HostNum);
				
		Chat = new JTextArea();
		chat = new JScrollPane(Chat);
		Chat.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		Chat.setEditable(false);
		Chat.setBackground(Color.LIGHT_GRAY);
		Chat.setBounds(10, 40, 410, 230);
		chat.setBounds(10, 40, 410, 230);
		chat.setWheelScrollingEnabled(true);
		chat.getVerticalScrollBar();
		getContentPane().add(chat);
		
		
		Users = new JTextArea();
		Users.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		Users.setEditable(false);
		Users.setBackground(Color.LIGHT_GRAY);
		Users.setBounds(430, 40, 100, 230);
		getContentPane().add(Users);
		
		
		Message = new JTextArea();
		Message.setBackground(Color.WHITE);
		Message.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		Message.setBounds(10, 280, 410, 70);
		getContentPane().add(Message);
		
		
		Send = new JButton("Send!");
		Send.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SendActionPerformed(e);
			}
		});
		Send.setFont(new Font("Times New Roman", Font.BOLD, 20));
		Send.setBounds(430, 280, 100, 70);
		getContentPane().add(Send);
		
		
		ReceivePort = new JTextArea();
		ReceivePort.setText("Recive Port");
		ReceivePort.setFont(new Font("Times New Roman", Font.BOLD, 12));
		ReceivePort.setBackground(Color.GRAY);
		ReceivePort.setBounds(10, 10, 70, 20);
		getContentPane().add(ReceivePort);
		
		Listener = new JButton("Listen");
		Listener.setFont(new Font("Times New Roman", Font.BOLD, 12));
		Listener.addActionListener(new java.awt.event.ActionListener() {
		public void actionPerformed(java.awt.event.ActionEvent evt) {
		      ListenerActionPerformed(evt);
		      }
		});
		Listener.setBounds(90, 10, 80, 20);
		getContentPane().add(Listener);
		setVisible(true);
	}

	@Override
	public void write(String s) {
		Chat.append(s + System.lineSeparator());
		
	}
	public void ListenerActionPerformed(ActionEvent e) {
		MessageListener = new MessageListener(this, Integer.parseInt(ReceivePort.getText()));
		MessageListener.start();
	}
	public void SendActionPerformed(ActionEvent e){
		MessageTransmitter transmitter = new MessageTransmitter(Message.getText(), IP.getText(), Integer.parseInt(HostNum.getText()), this);
		transmitter.start();
	}
}
