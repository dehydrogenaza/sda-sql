package simple_gui_popups;

import javax.swing.*;
//import java.util.Scanner;

public class Demo {
    public static void main(String[] args) {
        //KONSOLA
        //Scanner scanner = new Scanner(System.in);
        //System.out.println("Ile masz lat?");
//        int age = scanner.nextInt();
//        System.out.println("Masz " + age + " lat!");

        //JOptionPane.showMessageDialog(null, "Ile masz lat?");
        int age = Integer.parseInt(JOptionPane.showInputDialog("Ile masz lat?"));
        JOptionPane.showMessageDialog(null, "Masz " + age + " lat!");
    }
}
