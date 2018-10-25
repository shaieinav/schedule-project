package com.schedule_project.student;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.schedule_project.enrolled.Enrolled;
import com.schedule_project.role.Role;
import com.schedule_project.studies.Studies;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name = "student", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "username"
        }),
        @UniqueConstraint(columnNames = {
                "email"
        })
})
@EqualsAndHashCode
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id", updatable = false, nullable = false)
    private Long studentId;

    @NotBlank
    @Size(max = 20)
    @Column(name = "name")
    private String name;

    @NotBlank
    @Size(max = 20)
    @Column(name = "username")
    private String username;

    @NaturalId
    @NotBlank
    @Size(max = 40)
    @Email
    @Column(name = "email")
    private String email;

    @NotBlank
    @Size(max = 100)
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "studentJson1")
    private List<Studies> studentsCourses = new ArrayList<>();

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "studentJson2")
    private List<Enrolled> studentsCoursesGroups = new ArrayList<>();

    public Student() {

    }

    public Student(String name, String username, String email, String password) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public List<Studies> getStudentsCourses() {
        return studentsCourses;
    }

    public List<Enrolled> getStudentsCoursesGroups() {
        return studentsCoursesGroups;
    }

    public void setStudentsCoursesGroups(List<Enrolled> studentsCoursesGroups) {
        this.studentsCoursesGroups = studentsCoursesGroups;
    }

    public void setStudentsCourses(List<Studies> studentsCourses) {
        this.studentsCourses = studentsCourses;
    }

    @Override
    public String toString() {
        return "Semester{" +
                "studentId=" + studentId +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", courses=" + studentsCourses +
                '}';
    }
}