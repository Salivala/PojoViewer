
import View.Display;
import View.TestPojo;

import javax.swing.*;

public class Driver {
    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        System.out.println(TestPojo.class.getName());
        Display display = new Display();
    }
}
