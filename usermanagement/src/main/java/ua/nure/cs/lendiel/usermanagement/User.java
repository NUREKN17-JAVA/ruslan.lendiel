package ua.nure.cs.lendiel.usermanagement;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

public class User {
    private Long id;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public Date getDateOfBirth() {
        return dateOfBirth;
    }
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    public String getFullName() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.lastName);
        sb.append(", ");
        sb.append(this.firstName);
        return sb.toString();
    }
    public Object getAge() {
        LocalDate currentDate = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate birthDate = getDateOfBirth().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        
        return Period.between(birthDate, currentDate).getYears();
    }
}
