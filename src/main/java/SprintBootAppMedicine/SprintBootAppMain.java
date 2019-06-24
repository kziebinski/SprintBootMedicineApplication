package SprintBootAppMedicine;

import SprintBootAppMedicine.DB.ConnectionDB;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SprintBootAppMain {
    private ConnectionDB connectionDB = new ConnectionDB();

    public SprintBootAppMain() throws Exception {
        connectionDB.createTablesDB();
    }

    public static void main(String[] args) {
        SpringApplication.run(SprintBootAppMain.class, args);
    }
}
