package asteris.verifer;

import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JTextArea;
import asteris.helper.DialogHelper;

public class TextAreaVerifer extends InputVerifier {

    @Override
    public boolean verify(JComponent input) {
        String text = ((JTextArea) input).getText();
        DialogHelper dialog = new DialogHelper(null);
        if (text.length() == 0) {
            dialog.Error("Input Salah", "Inputan ini tidak boleh dikosongkan.");
        }

        return text.length() > 0;
    }

}
