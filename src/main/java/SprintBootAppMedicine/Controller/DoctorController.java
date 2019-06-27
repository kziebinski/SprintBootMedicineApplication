package SprintBootAppMedicine.Controller;

import SprintBootAppMedicine.Model.DoctorModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    private List<DoctorModel> listDoctors = new ArrayList<>();
    private Logger log = LogManager.getLogger(DoctorController.class.getName());

    @PostMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<DoctorModel> postAddDoctor(@RequestBody DoctorModel doctor) {
        listDoctors.add(doctor);
        log.info("Add doctor: " + doctor.getName() + " " + doctor.getSurname());
        return listDoctors;

    }

}
