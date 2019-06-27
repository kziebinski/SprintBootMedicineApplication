package SprintBootAppMedicine.Model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DoctorModel {

    @JsonCreator
    public DoctorModel(
            @JsonProperty("id_doctor") int id_doctor,
            @JsonProperty("name")String name,
            @JsonProperty("surname")String surname,
            @JsonProperty("title")String title,
            @JsonProperty("age")String age,
            @JsonProperty("gender")String gender) {
        this.id_doctor = id_doctor;
        this.name = name;
        this.surname = surname;
        this.title = title;
        this.age = age;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getId_doctor() {
        return id_doctor;
    }

    public void setId_doctor(int id_doctor) {
        this.id_doctor = id_doctor;
    }

    private int id_doctor;
    private String name;
    private String surname;
    private String title;
    private String age;
    private String gender;

}
