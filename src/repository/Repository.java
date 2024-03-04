package repository;

import domain.Weather;
import org.sqlite.SQLiteDataSource;
import java.sql.*;
import java.util.ArrayList;

public class Repository {
    private ArrayList<Weather> weathers;
    private static final String JDBC_URL = "jdbc:sqlite:data/test_db.db";

    private static Connection conn = null;

    public static Connection getConnection() {
        if (conn == null)
            openConnection();
        return conn;
    }

    public Repository() {
        weathers = new ArrayList<>();
        openConnection();
        try {
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM Weather");

            while (rs.next()) {
                weathers.add(new Weather(
                        rs.getInt("id"),
                        rs.getInt("start_hour"),
                        rs.getInt("end_hour"),
                        rs.getInt("temperature"),
                        rs.getInt("precipitation_probability"),
                        rs.getString("description")
                ));
                ;
            }
            rs.close();
            s.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void openConnection()
    {
        try
        {
            SQLiteDataSource ds = new SQLiteDataSource();
            ds.setUrl(JDBC_URL);
            if (conn == null || conn.isClosed())
                conn = ds.getConnection();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void closeConnection()
    {
        try
        {
            conn.close();
            conn = null;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    public ArrayList<Weather> getWeathers(){
        return this.weathers;
    }
    public void update(Weather weather) throws SQLException {
        String updateSql="Update Weather set precipitation_probability= ?, description= ? WHERE id = ? ";
        PreparedStatement preparedStatement=getConnection().prepareStatement(updateSql);
        preparedStatement.setInt(1,weather.getPrecipitationProbability());
        preparedStatement.setString(2,weather.getDescription());
        preparedStatement.setInt(3, weather.getId());
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }
}
