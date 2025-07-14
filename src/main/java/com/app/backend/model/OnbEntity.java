package com.app.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "app_onboardings")
@Data // Getters, Setters...
public class OnbEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "event_name")
    private String eventName;
    @Column(name = "start_date")
    private String startDate;
    @Column(name = "end_date")
    private String endDate;
}
