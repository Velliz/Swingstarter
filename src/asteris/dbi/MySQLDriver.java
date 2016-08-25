package asteris.dbi;

import asteris.app.DatabaseConfiguration;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class MySQLDriver implements DatabaseConfiguration {

    private static MySQLDriver MysqliConn;

    private ResultSet result;
    private Connection koneksi;
    private Statement statement;
    private PreparedStatement ps;
    private int insertId;

    private MySQLDriver() {
        try {
            Class.forName(DRIVERNAME);
            koneksi = DriverManager.getConnection("jdbc:mysql://" + HOST + "/" + DATABASE, USERNAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null, ex, "Kesalahan", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    private static MySQLDriver createKoneksi() {
        if (MysqliConn == null) {
            synchronized (MySQLDriver.class) {
                MysqliConn = new MySQLDriver();
            }
        }
        return MysqliConn;
    }

    public static MySQLDriver getInstance() {
        return createKoneksi();
    }

    public ResultSet getResult() {
        return result;
    }

    public void setResult(ResultSet result) {
        this.result = result;
    }

    public Connection getKoneksi() {
        return koneksi;
    }

    public void setKoneksi(Connection koneksi) {
        this.koneksi = koneksi;
    }

    public Statement getStatement() {
        return statement;
    }

    public void setStatement(Statement statement) {
        this.statement = statement;
    }

    public boolean kirim(String queri) {
        boolean resultData = false;
        try {
            statement = koneksi.createStatement();
            statement.executeUpdate(queri, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) insertId = rs.getInt(1);
            resultData = true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex, "Kesalahan", JOptionPane.ERROR_MESSAGE);
        }
        return resultData;
    }

    public ResultSet ambil(String queri, Object... param) {
        try {
            if (param == null || param.length == 0) {
                statement = koneksi.createStatement();
                result = statement.executeQuery(queri);
            } else if (param.length > 0) {
                ps = koneksi.prepareStatement(queri);
                int i = 1;
                for (Object val : param) {
                    String data = String.valueOf(val);
                    if (data.equals("") || val == null) {
                        break;
                    }
                    ps.setString(i, data);
                    i++;
                }
                result = ps.executeQuery();
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex, "Kesalahan", JOptionPane.ERROR_MESSAGE);
        }
        return result;
    }

    public PreparedStatement getPrepStatement() {
        return ps;
    }

    public void setPrepStatement(PreparedStatement ps) {
        this.ps = ps;
    }

    public int getInsertId() {
        return insertId;
    }
    
}
