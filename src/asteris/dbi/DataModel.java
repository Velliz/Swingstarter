package asteris.dbi;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

public class DataModel {

    private String table = null;

    public DataModel(String tablename) {
        table = tablename;
    }

    public ResultSet Get(String query, Object... param) {
        String kueri = "SELECT * FROM " + table;

        if (query != null) {
            kueri = query;
        }

        MySQLDriver.getInstance().ambil(kueri, param);

        if (MySQLDriver.LOG) {
            System.err.println("QUERY : " + kueri);
        }

        return MySQLDriver.getInstance().getResult();
    }

    public ResultSet Get(String column, String key) {
        String kueri = "SELECT * FROM `" + table + "` WHERE " + column + " LIKE '%" + key + "%'";
        MySQLDriver.getInstance().ambil(kueri);

        if (MySQLDriver.LOG) {
            System.err.println("QUERY : " + kueri);
        }

        return MySQLDriver.getInstance().getResult();
    }

    public boolean Save(ArrayList<String> col, HashMap<String, String> arrData) {
        String queryBuilder = "INSERT INTO `" + table + "` (";
        for (int i = 0; i < col.size(); i++) {
            if (i != (col.size() - 1)) {
                queryBuilder += "`" + col.get(i) + "`,";
            } else {
                queryBuilder += "`" + col.get(i) + "`)";
            }
        }
        queryBuilder += " VALUES (";
        for (int i = 0; i < col.size(); i++) {
            if (i != (col.size() - 1)) {
                queryBuilder += "'" + arrData.get(col.get(i)) + "',";
            } else {
                queryBuilder += "'" + arrData.get(col.get(i)) + "');";
            }
        }

        if (MySQLDriver.LOG) {
            System.err.println("QUERY : " + queryBuilder);
        }

        return MySQLDriver.getInstance().kirim(queryBuilder);
    }

    public boolean Delete(String column, String key) {
        String kueri = "DELETE FROM `" + table + "` WHERE " + column + " = '" + key + "'";

        if (MySQLDriver.LOG) {
            System.err.println("QUERY : " + kueri);
        }

        return MySQLDriver.getInstance().kirim(kueri);
    }

    public boolean RawUpdate(String kuer) {
        if (MySQLDriver.LOG) {
            System.err.println("QUERY : " + kuer);
        }
        return MySQLDriver.getInstance().kirim(kuer);
    }

    public boolean Update(ArrayList<String> colBefore, HashMap<String, String> dataBefore,
            ArrayList<String> colAfter, HashMap<String, String> dataAfter) {

        String queryBuilder = "UPDATE `" + table + "` SET ";
        for (int i = 0; i < colAfter.size(); i++) {
            if (i != (colAfter.size() - 1)) {
                queryBuilder += "`" + colAfter.get(i) + "` = '" + dataAfter.get(colAfter.get(i)) + "', ";
            } else {
                queryBuilder += "`" + colAfter.get(i) + "` = '" + dataAfter.get(colAfter.get(i)) + "' ";
            }
        }
        queryBuilder += " WHERE ";
        for (int i = 0; i < colBefore.size(); i++) {
            if (i != (colBefore.size() - 1)) {
                queryBuilder += "`" + colBefore.get(i) + "` = '" + dataBefore.get(colBefore.get(i)) + "', ";
            } else {
                queryBuilder += "`" + colBefore.get(i) + "` = '" + dataBefore.get(colBefore.get(i)) + "' ";
            }
        }

        if (MySQLDriver.LOG) {
            System.err.println("QUERY : " + queryBuilder);
        }

        return MySQLDriver.getInstance().kirim(queryBuilder);
    }

}
