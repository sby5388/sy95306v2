package com.by5388.sy95306v2.common;

import com.by5388.sy95306v2.database.CommonStationTable;
import com.by5388.sy95306v2.database.MyDataBaseHelper;

import org.sqlite.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Administrator  on 2019/9/19.
 */
public class CommonDataBase implements IDataBaseApi {
    static {
        try {
            Class.forName(JDBC.class.getName());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    // TODO: 2019/9/19 增加单元测试下的数据库
    // TODO: 2019/9/19 如果文件不存在，改如何处理
    private static final String DATABASE_FILE_NAME = MyDataBaseHelper.DATABASE_NAME;

    private static CommonDataBase instance = new CommonDataBase();

    public static CommonDataBase getInstance() {
        return instance;
    }


    private Connection mConnection;

    private CommonDataBase() {
        try {
            mConnection = DriverManager.getConnection("jdbc:sqlite:" + DATABASE_FILE_NAME);
            initDataBase();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void initDataBase() {
        try {
            final Statement statement = mConnection.createStatement();
            // TODO: 2019/9/19 如果不存在时创建表格
            statement.execute(CommonStationTable.SQL_CREATE_TABLE);
            final ResultSet resultSet = statement.executeQuery("select count(*) from " + CommonStationTable.TableStation.TABLE_NAME);
            if (resultSet.wasNull()) {
                insertDefaultData();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void insertDefaultData() {
        // TODO: 2019/9/19 插入数据
    }


    @Override
    public String getTrainCode(String name) {
        try {
            final Statement statement = mConnection.createStatement();
            final String querySql = "select " + CommonStationTable.TableStation.NAME_UPPER + " from " + CommonStationTable.TableStation.TABLE_NAME +
                    " where " + CommonStationTable.TableStation.STATION_NAME + " = " + name;
            final ResultSet resultSet = statement.executeQuery(querySql);
            if (resultSet != null && resultSet.first()) {
                // FIXME: 2019/9/19 没有实现
//                resultSet.getString();
                final String string = resultSet.getString(CommonStationTable.TableStation.NAME_UPPER);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
