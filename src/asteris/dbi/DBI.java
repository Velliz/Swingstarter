package asteris.dbi;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class DBI {

    private final DataModel mm;

    public DBI(String table) {
        mm = new DataModel(table);
    }

    public boolean Save(ArrayList<String> col, HashMap<String, String> arrData) {
        return mm.Save(col, arrData);
    }

    public boolean Update(ArrayList<String> colBefore, HashMap<String, String> dataBefore,
            ArrayList<String> colAfter, HashMap<String, String> dataAfter) {
        return mm.Update(colBefore, dataBefore, colAfter, dataAfter);
    }

    public ArrayList<Object[]> Get(int colLength, String query) throws SQLException {
        ArrayList<Object[]> data = new ArrayList<>();
        ResultSet rs = mm.Get(query);
        while (rs.next()) {
            Object[] temp = new Object[colLength];
            for (int i = 0; i < colLength; i++) {
                temp[i] = rs.getString(i + 1);
            }
            data.add(temp);
        }
        return data;
    }

    public boolean Delete(String col, String val) {
        return mm.Delete(col, val);
    }

    public boolean RawUpdate(String query) {
        return mm.RawUpdate(query);
    }

    public ResultSet RawSelect(String query) {
        return mm.Get(query, (Object) null);
    }

    public ResultSet RawSelect(String query, Object... param) {
        return mm.Get(query, param);
    }
}
