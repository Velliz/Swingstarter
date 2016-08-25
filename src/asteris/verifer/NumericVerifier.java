package asteris.verifer;

import asteris.helper.DialogHelper;
import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JTextField;

public class NumericVerifier extends InputVerifier {

    @Override
    public boolean verify(JComponent input) {
        String text = ((JTextField) input).getText();
        try {
            Integer.parseInt(text);
        } catch (NumberFormatException ex) {
            DialogHelper dialog = new DialogHelper(null);
            dialog.Error("Input Salah", "Inputan harus angka 0-9 (numerik).");
            return false;
        }

        return true;
    }
}
