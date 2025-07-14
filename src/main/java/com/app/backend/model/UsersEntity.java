package com.app.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "app_users")
@Data // Getters, Setters...
public class UsersEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "fullname")
    private String fullname;
    @Column(name = "email")
    private String email;
    @Column(name = "date")
    private String date;
    @Column(name = "general_onb_status")
    private Boolean generalOnbStatus;
    @Column(name = "tech_onb_status")
    private Boolean techOnbStatus;
    @Column(name = "tech_onb_date")
    private String techOnbDate;
}
