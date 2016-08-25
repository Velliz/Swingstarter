package asteris.app;

import asteris.helper.DialogHelper;
import asteris.util.Cache;
import java.awt.EventQueue;
import java.awt.HeadlessException;
import java.awt.event.WindowEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import static javax.swing.JFrame.setDefaultLookAndFeelDecorated;
import javax.swing.JLabel;

public class AbstractView extends JFrame {

    protected DialogHelper dialog;

    public AbstractView() throws HeadlessException {
        dialog = new DialogHelper(this);
        setTitle("Aplikasi Optik Setia");
        setDefaultLookAndFeelDecorated(true);
        setIconImage(new ImageIcon("icon.png").getImage());
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
    }

    public void Login(String username, String name, String id, String hp, String alamat, String email, String jabatan) {
        Cache.username = username;
        Cache.name = name;
        Cache.id_user = id;
        Cache.no_hp = hp;
        Cache.alamat = alamat;
        Cache.email = email;
        Cache.jabatan = jabatan;
    }

    public void Logout() {
        Cache.username = "";
        Cache.name = "";
        Cache.id_user = "";
        Cache.no_hp = "";
        Cache.alamat = "";
        Cache.email = "";
        Cache.jabatan = "";
        Cache.themePosition = 1;
    }

    public void renderUserCredentials(JLabel username, JLabel nama, JLabel id, JLabel hp, JLabel alamat, JLabel email, JLabel jabatan) {
        username.setText(Cache.username);
        nama.setText(Cache.name);
        id.setText(Cache.id_user);
        hp.setText(Cache.no_hp);
        alamat.setText(Cache.alamat);
        email.setText(Cache.email);
        jabatan.setText(Cache.jabatan);
    }

    public void Redirect(final JFrame from, final JFrame destination) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                destination.setVisible(true);
            }
        });

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(300);
                    from.dispose();
                } catch (InterruptedException ex) {
                }
            }
        });
        thread.start();
    }

    public void Open(JFrame from, final JFrame destination) {
        EventQueue.invokeLater(() -> {
            destination.setVisible(true);
        });
    }

    public void Exit() {
        System.exit(0);
    }

    public boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }

    public boolean isValiPhoneNumber(String phone) {
        String regexStr = "^[0-9]*$";
        return phone.matches(regexStr);
    }

    public String MD5(String md5) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
        }
        return null;
    }

    @Override
    protected void processWindowEvent(final WindowEvent e) {
        if (e.getID() == WindowEvent.WINDOW_CLOSING) {
            if (dialog.Confirmation("Keluar Aplikasi", "Yakin ingin keluar dari aplikasi?") == 0) {
                Exit();
            }
        }
    }
}
