package SprintBootAppMedicine.Controller;

import SprintBootAppMedicine.DB.ConnectionDB;
import SprintBootAppMedicine.DB.PatientDAO;
import SprintBootAppMedicine.Model.PatientModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/patient")
public class PatientController {

    private PatientDAO patientDAO = new PatientDAO();
    private List<PatientModel> listPatients = new ArrayList<>();
    private Logger log = LogManager.getLogger(PatientController.class);

    @PostMapping()
    public ResponseEntity<String> postCreatePatient(
            @RequestBody PatientModel patienr) throws Exception {
        if (patienr.getPesel() == null || patienr.getPesel().isEmpty()) {
            return new ResponseEntity<>(
                    "\"The 'pesel' parameter must not be null or empty\"", HttpStatus.BAD_REQUEST);
        } else if (patienr.getPesel().matches("[0-9]+") && patienr.getPesel().length() == 11) {
            if (patientDAO.selectValuePesel(patienr.getPesel()).equals(patienr.getPesel()) == false) {
                listPatients.add(patienr);
                System.out.println(patienr.getPesel().length() == 11);
                patientDAO.insertNewPatient(patienr.getPesel(), patienr.getName(), patienr.getSurname(),
                        patienr.getAge(), patienr.getGender(), patienr.getDescription());
                log.info("Add patient: " + patienr.getPesel());
                return new ResponseEntity<>(
                        "OK", HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(
                        "Value Pesel exist in database", HttpStatus.CONFLICT);
            }
        } else {
            return new ResponseEntity<>(
                    "Value Pesel is invalid", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<PatientModel> displayAllPatients() {
        log.info("dispaly list patients");
        ConnectionDB connectionDB = new ConnectionDB();
        try {
            connectionDB.createTablesDB();
        } catch (Exception e) {
            log.info(e);
        }
        return listPatients;
    }

    @GetMapping("/{pesel}")
    @ResponseStatus(HttpStatus.OK)
    public PatientModel displayOnePatitent(@PathVariable("pesel") String id) {
        for (PatientModel patient : listPatients) {
            if (patient.getPesel() == id) {
                log.info("display patient: " + patient.getPesel());
                return patient;
            }
        }
        throw new IllegalArgumentException();
    }

    @DeleteMapping("/{pesel}")
    public ResponseEntity<String> deletePatient(
            @PathVariable("pesel") String pesel) {
        if (patientDAO.selectValuePesel(pesel) == "0") {
            log.info("BAD_REQUEST Delete invalid. Patient not exist");
            return new ResponseEntity<>(
                    "Delete invalid. Patient not exist", HttpStatus.BAD_REQUEST);
        } else {
            patientDAO.deletePatientByPesel(pesel);
            log.info("OK DELETE " + pesel);
            return new ResponseEntity<>(
                    "DELETE " + pesel, HttpStatus.OK);
        }
    }

    @PutMapping("/{pesel}")
    public ResponseEntity<String> updatePatient(
            @PathVariable("pesel") String pesel,
            @Valid @RequestBody PatientModel patient) {
        if (patientDAO.selectValuePesel(pesel) != "0" &&
                patientDAO.selectValuePesel(pesel).equals(pesel) &&
                pesel.matches("[0-9]+") &&
                pesel.length() == 11) {
            patientDAO.updatePatientByPesel(pesel, patient.getName(), patient.getSurname(), patient.getAge(), patient.getGender(), patient.getDescription());
            return new ResponseEntity<>
                    ("Update complete succesfully", HttpStatus.OK);
        } else {
            log.error("Update patient invalid " + HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>
                    ("Update patient invalid", HttpStatus.BAD_REQUEST);

        }
    }

}
