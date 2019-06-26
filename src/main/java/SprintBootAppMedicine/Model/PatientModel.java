package SprintBootAppMedicine.Model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PatientModel {

    private String pesel;
    private String name;
    private String surname;
    private String age;
    private String gender;
    private String description;

    @JsonCreator
    public PatientModel(@JsonProperty("pesel") String pesel, @JsonProperty("name") String name,
                        @JsonProperty("surname") String surname, @JsonProperty("age") String age,
                        @JsonProperty("gender") String gender, @JsonProperty("description") String description) {
        this.pesel = pesel;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.gender = gender;
        this.description = description;
    }
    public PatientModel(){ }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
