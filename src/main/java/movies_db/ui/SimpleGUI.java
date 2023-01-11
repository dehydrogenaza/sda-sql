package movies_db.ui;

import javax.swing.*;

public class SimpleGUI implements IUserInterface {
    @Override
    public void display(String msg) {
        JOptionPane.showMessageDialog(null, msg);
    }

    @Override
    public String ask(String msg) {
        if (msg == null) msg = "";
        return JOptionPane.showInputDialog(msg);
    }
}
