package com.schedule_project.role;

import org.hibernate.annotations.NaturalId;
import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name="role_id")
    private Integer roleId;

    @Enumerated(EnumType.STRING)
    @NaturalId
//    @Column(name="role", length = 60)
    private RoleName name;

    public Role() {

    }

    public Role(RoleName name) {
        this.name = name;
    }

    public Integer getId() {
        return roleId;
    }

    public void setId(Integer roleId) {
        this.roleId = roleId;
    }

    public RoleName getName() {
        return name;
    }

    public void setName(RoleName name) {
        this.name = name;
    }
}