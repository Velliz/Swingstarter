package asteris.helper;

import java.awt.Component;
import javax.swing.JOptionPane;

public class DialogHelper {

    private static Component parrent;

    public DialogHelper(Component parrent) {
        DialogHelper.parrent = parrent;
    }

    public void Error(String title, String message) {
        JOptionPane.showMessageDialog(
                parrent,
                message,
                title,
                JOptionPane.ERROR_MESSAGE
        );
    }

    public int Confirmation(String title, String message) {
        return JOptionPane.showConfirmDialog(
                parrent,
                message,
                title,
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );
    }

    public int Warning(String title, String message) {
        return JOptionPane.showConfirmDialog(
                parrent,
                message,
                title,
                JOptionPane.WARNING_MESSAGE
        );
    }

    public void Info(String title, String message) {
        JOptionPane.showMessageDialog(
                parrent,
                message,
                title,
                JOptionPane.INFORMATION_MESSAGE
        );
    }

}
