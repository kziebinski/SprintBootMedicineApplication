package SprintBootAppMedicine.DB;

import SprintBootAppMedicine.Model.PatientModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class PatientDAO {

    private Logger log = LogManager.getLogger(ConnectionDB.class);
    private String tempValue = "0";
    private PatientModel tempPatient;

    private Connection connectionDatabase() throws Exception {
        ConnectionDB connectionDB = new ConnectionDB();
        return connectionDB.getConnectionDB();
    }

    public void insertNewPatient(String pesel, String name, String surname, String age, String gender, String description) {
        try {
            String quesry = "INSERT INTO Patient(pesel, name, surname, age, gender, description) VALUES (?,?,?,?,?,?)";
            PreparedStatement insertPatient = connectionDatabase().prepareStatement(quesry);
            insertPatient.setString(1, pesel);
            insertPatient.setString(2, name);
            insertPatient.setString(3, surname);
            insertPatient.setString(4, age);
            insertPatient.setString(5, gender);
            insertPatient.setString(6, description);
            insertPatient.executeUpdate();
            log.info("Add new patient to DB" + insertPatient);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e);
        }
    }

    public String searchOnePatient(String pesel) {
        try {
            String selectQuery = "SELECT pesel FROM Patient WHERE pesel = ?";
            PreparedStatement selectPesel = connectionDatabase().prepareStatement(selectQuery);
            selectPesel.setString(1, pesel);
            ResultSet temp = selectPesel.executeQuery();
            while (temp.next()) {
                String tempPeselWhile = temp.getString("pesel");
                tempValue = tempPeselWhile;
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e);
        }
        return tempValue;
    }
    public PatientModel getPatientDb(String pesel){
        PatientModel patientModel = new PatientModel();
        try {
            String selectQuery = "SELECT * FROM Patient WHERE pesel = ?";
            PreparedStatement selectPesel = connectionDatabase().prepareStatement(selectQuery);
            selectPesel.setString(1, pesel);
            ResultSet temp = selectPesel.executeQuery();
            while (temp.next()) {
                if (temp.getString("pesel").equals(pesel)){
                    patientModel.setPesel(temp.getString("pesel"));
                    patientModel.setName(temp.getString("name"));
                    patientModel.setSurname(temp.getString("surname"));
                    patientModel.setAge(temp.getString("age"));
                    patientModel.setGender(temp.getString("gender"));
                    patientModel.setDescription(temp.getString("description"));
                    tempPatient = patientModel;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e);
        }
        return tempPatient;
    }

    public void deletePatientByPesel(String pesel) {
        try {
            String deleteQuesry = "DELETE FROM Patient WHERE pesel = ?";
            PreparedStatement deletePatientById = connectionDatabase().prepareStatement(deleteQuesry);
            deletePatientById.setString(1, pesel);
            deletePatientById.execute();
            log.info("Delete patient id: " + pesel);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e);
        }
    }
    public void updatePatientByPesel(String pesel, String name, String surname, String age, String gender, String description){
        try {
            String updateQuery = "UPDATE Patient " +
                    "SET name = ? , surname = ? , age = ? , gender = ? , description = ? " +
                    "WHERE pesel = ?";
            PreparedStatement updatePatientById = connectionDatabase().prepareStatement(updateQuery);
            updatePatientById.setString(1,name);
            updatePatientById.setString(2,surname);
            updatePatientById.setString(3,age);
            updatePatientById.setString(4,gender);
            updatePatientById.setString(5,description);
            updatePatientById.setString(6,pesel);
            updatePatientById.executeUpdate();
            log.info("Update completed successfull " + pesel);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e);
        }
    }
    public ArrayList<PatientModel> getAllPatients(){
        ArrayList<PatientModel> listPatient = new ArrayList<PatientModel>();
        PatientModel patient = new PatientModel();
        try{
            String selectQuery = "SELECT * FROM Patient";
            PreparedStatement findAllPatient = connectionDatabase().prepareStatement(selectQuery);
            ResultSet find = findAllPatient.executeQuery(selectQuery);
            while (find.next()){
                patient.setPesel(find.getString("pesel"));
                patient.setName(find.getString("name"));
                patient.setSurname(find.getString("surname"));
                patient.setAge(find.getString("age"));
                patient.setGender(find.getString("gender"));
                patient.setDescription(find.getString("description"));
                listPatient.add(patient);
            }
        }catch (Exception e){
            e.printStackTrace();
            log.error(e);
        }
        return listPatient;
    }
}
