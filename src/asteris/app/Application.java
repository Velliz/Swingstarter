package asteris.app;

import asteris.app.view.Login;
import asteris.helper.DialogHelper;
import de.javasoft.plaf.synthetica.SyntheticaSilverMoonLookAndFeel;
import java.text.ParseException;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Application {

    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(new SyntheticaSilverMoonLookAndFeel());
            new Login().setVisible(true);
        } catch (UnsupportedLookAndFeelException | ParseException ex) {
            new DialogHelper(null).Error("Synthetica", "Unable to load Synthetica library. Error Log:\n" + ex.getMessage());
        }
    }
}
