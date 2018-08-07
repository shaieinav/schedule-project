package com.schedule_project.instructor;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.schedule_project.teaches.Teaches;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "instructor")
@EqualsAndHashCode
public class Instructor {

    @Id
    @Column(name = "instructor_id", updatable = false, nullable = false)
    private Integer instructorId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "instructor", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "instructorJson")
    private List<Teaches> instructorsCourses = new ArrayList<>();

    public Instructor() {

    }

    public Instructor(Integer instructorId, String firstName, String lastName, String email, List<Teaches> instructorsCourses) {
        this.instructorId = instructorId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.instructorsCourses = instructorsCourses;
    }

    public Integer getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(Integer instructorId) {
        this.instructorId = instructorId;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Teaches> getInstructorsCourses() {
        return instructorsCourses;
    }

    public void setInstructorsCourses(List<Teaches> instructorsCourses) {
        this.instructorsCourses = instructorsCourses;
    }

    @Override
    public String toString() {
        return "Instructor{" +
                "instructorId=" + instructorId +
                ", firstName='" + firstName + '\'' +
                ", lastName=" + lastName +
                ", email='" + email + '\'' +
                ", instructorsCourses=" + instructorsCourses +
                '}';
    }
}