package SprintBootAppMedicine.DB;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Properties;

public class ConnectionDB {

    private Logger log = LogManager.getLogger(ConnectionDB.class);

    public Connection getConnectionDB() throws Exception {
        try (InputStream input = new FileInputStream("src/main/resources/configDB.properties")){
            Properties properties = new Properties();
            properties.load(input);
            Class.forName(properties.getProperty("db.driver"));
            Connection con = DriverManager.getConnection(
                    properties.getProperty("db.url"),
                    properties.getProperty("db.username"),
                    properties.getProperty("db.password"));
            log.info("Create Connection database");
            return con;
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e);
        }
        return null;
    }

    public void createTablesDB() throws Exception {
        try {
            Connection con = getConnectionDB();
            PreparedStatement patientTable = con.prepareStatement("CREATE TABLE IF NOT EXISTS Patient(id INT AUTO_INCREMENT, pesel varchar(255) NOT NULL, name varchar(255), surname varchar(255), age varchar(255), gender varchar(255), description varchar(255), creation_date TIMESTAMP DEFAULT NOW(), PRIMARY KEY(pesel));");
            PreparedStatement doctorTable = con.prepareStatement("CREATE TABLE IF NOT EXISTS Doctor(id_doctor INT AUTO_INCREMENT, name varchar(255), surname varchar(255), title varchar(255), age varchar(255), gender varchar(255), creation_date TIMESTAMP DEFAULT NOW(), PRIMARY KEY(id_doctor));");
            patientTable.executeUpdate();
            doctorTable.executeUpdate();
            log.info("Create tables IF NOT EXISTS");
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e);
        }
    }
}
