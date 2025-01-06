package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Objects;

public class Login extends JFrame implements ActionListener {
    JLabel label1, label2, label3;
    JTextField textField2;
    JPasswordField passwordField3;

    JButton button1, button2, button3;

    Login() {
        super("Bank Management System");

        // Bank image icon
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/bank.png"));
        Image i2 = i1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(350, 10, 100, 100);
        add(image);

        // Card image icon
        ImageIcon ii1 = new ImageIcon(ClassLoader.getSystemResource("icon/card.png"));
        Image ii2 = ii1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        ImageIcon ii3 = new ImageIcon(ii2);
        JLabel iimage = new JLabel(ii3);
        iimage.setBounds(630, 350, 100, 100);
        add(iimage);

        // Welcome label
        label1 = new JLabel("WELCOME TO ATM");
        label1.setForeground(Color.WHITE);
        label1.setFont(new Font("AvantGarde", Font.BOLD, 38));
        label1.setBounds(230, 125, 450, 40);
        add(label1);

        // Card number label
        label2 = new JLabel("Card No:");
        label2.setFont(new Font("Ralway", Font.BOLD, 28));
        label2.setForeground(Color.WHITE);
        label2.setBounds(150, 190, 375, 30);
        add(label2);

        // Card number input field
        textField2 = new JTextField(15);
        textField2.setBounds(325, 190, 230, 30);
        textField2.setFont(new Font("Arial", Font.BOLD, 14));
        add(textField2);

        // PIN label
        label3 = new JLabel("PIN: ");
        label3.setFont(new Font("Ralway", Font.BOLD, 28));
        label3.setForeground(Color.WHITE);
        label3.setBounds(150, 250, 375, 30);
        add(label3);

        // PIN input field
        passwordField3 = new JPasswordField(15);
        passwordField3.setBounds(325, 250, 230, 30);
        passwordField3.setFont(new Font("Arial", Font.BOLD, 14));
        add(passwordField3);

        // Sign In button
        button1 = new JButton("SIGN IN");
        button1.setFont(new Font("Arial", Font.BOLD, 14));
        button1.setForeground(Color.WHITE);
        button1.setBackground(Color.BLACK);
        button1.setBounds(300, 300, 100, 30);
        button1.addActionListener(this);
        add(button1);

        // Clear button
        button2 = new JButton("CLEAR");
        button2.setFont(new Font("Arial", Font.BOLD, 14));
        button2.setForeground(Color.WHITE);
        button2.setBackground(Color.BLACK);
        button2.setBounds(430, 300, 100, 30);
        button2.addActionListener(this);
        add(button2);

        // Sign Up button
        button3 = new JButton("SIGN UP");
        button3.setFont(new Font("Arial", Font.BOLD, 14));
        button3.setForeground(Color.WHITE);
        button3.setBackground(Color.BLACK);
        button3.setBounds(300, 350, 230, 30);
        button3.addActionListener(this);
        add(button3);

        // Background image
        ImageIcon iii1 = new ImageIcon(ClassLoader.getSystemResource("icon/backbg.png"));
        Image iii2 = iii1.getImage().getScaledInstance(850, 480, Image.SCALE_DEFAULT);
        ImageIcon iii3 = new ImageIcon(iii2);
        JLabel iiimage = new JLabel(iii3);
        iiimage.setBounds(0, 0, 850, 480);
        add(iiimage);

        setLayout(null);
        setSize(850, 480);
        setLocation(450, 200);
        setUndecorated(true);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == button1) {
                // Hardcoded card number and PIN check
                String cardno = textField2.getText().trim();  // Trim spaces
                String pin = new String(passwordField3.getPassword()).trim();  // Trim spaces

                if (cardno.equals("1234") && pin.equals("1234")) {
                    setVisible(false);
                    new main_Class(pin);  // Open the next window for hardcoded login
                } else {
                    // Database connection if hardcoded values do not match
                    Connn c = new Connn();
                    String q = "SELECT * FROM login WHERE card_number = '" + cardno + "' AND pin = '" + pin + "'";
                    ResultSet resultSet = c.statement.executeQuery(q);

                    if (resultSet.next()) {
                        setVisible(false);
                        new main_Class(pin);  // Open the next window
                    } else {
                        JOptionPane.showMessageDialog(null, "Incorrect Card Number or PIN");
                    }
                }

            } else if (e.getSource() == button2) {
                textField2.setText("");  // Clear card number
                passwordField3.setText("");  // Clear PIN
            } else if (e.getSource() == button3) {
                new Signup();  // Go to sign up page
                setVisible(false);
            }
        } catch (Exception E) {
            E.printStackTrace();  // Print the stack trace for debugging
        }
    }

    public static void main(String[] args) {
        new Login();  // Launch the login page
    }

	@Override
	public int hashCode() {
		return Objects.hash(button1, button2, button3, label1, label2, label3, passwordField3, textField2);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Login other = (Login) obj;
		return Objects.equals(button1, other.button1) && Objects.equals(button2, other.button2)
				&& Objects.equals(button3, other.button3) && Objects.equals(label1, other.label1)
				&& Objects.equals(label2, other.label2) && Objects.equals(label3, other.label3)
				&& Objects.equals(passwordField3, other.passwordField3) && Objects.equals(textField2, other.textField2);
	}

	@Override
	public String toString() {
		return "Login [label1=" + label1 + ", label2=" + label2 + ", label3=" + label3 + ", textField2=" + textField2
				+ ", passwordField3=" + passwordField3 + ", button1=" + button1 + ", button2=" + button2 + ", button3=" 
				+ button3 + "]";
	}
}
