import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
class BankAccount {
    private int balance;
    public BankAccount() {
        balance=1000;
    }
    public void withdraw(int amount) {
        if (amount>balance) {
            JOptionPane.showMessageDialog(null, "Insufficient funds in the account");
        }else {
            balance -= amount;
            JOptionPane.showMessageDialog(null, "Wait for a moment...\nYour cash is being dispensed.\nTransaction completed.");
        }
    }
    public void deposit(int amount) {
        if (amount>0) {
            balance+=amount;
            JOptionPane.showMessageDialog(null, "Wait for a moment...\nYour deposit is being processed.\nTransaction completed.");
        }else {
            JOptionPane.showMessageDialog(null, "Invalid deposit amount.");
        }
    }
    public int checkBalance() {
        return balance;
    }
}
public class ATM extends JFrame implements ActionListener {
    private BankAccount account = new BankAccount();
    private JTextField amountField;
    public ATM() {
        setTitle("ATM Simulator");
        setLayout(new FlowLayout());
        JLabel label=new JLabel("Enter your choice:");
        add(label);
        JButton withdrawButton = new JButton("Withdraw");
        add(withdrawButton);
        withdrawButton.addActionListener(this);
        JButton depositButton=new JButton("Deposit");
        add(depositButton);
        depositButton.addActionListener(this);
        JButton checkBalanceButton=new JButton("Check Balance");
        add(checkBalanceButton);
        checkBalanceButton.addActionListener(this);
        amountField=new JTextField(10);
        add(amountField);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent e) {
        String choice = e.getActionCommand();
        switch (choice) {
            case "Withdraw":
                int withdrawAmount=Integer.parseInt(amountField.getText());
                account.withdraw(withdrawAmount);
                JOptionPane.showMessageDialog(null, "Your account balance is: ₹" + account.checkBalance());
                break;
            case "Deposit":
                int depositAmount=Integer.parseInt(amountField.getText());
                account.deposit(depositAmount);
                JOptionPane.showMessageDialog(null, "Your account balance is: ₹" + account.checkBalance());
                break;
            case "Check Balance":
                JOptionPane.showMessageDialog(null, "Your account balance is: ₹" + account.checkBalance());
                break;
        }
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ATM());
    }
}

