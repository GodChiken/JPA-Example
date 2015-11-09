package org.ohjic.jpa.model;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by Kim Donghoon on 2015-11-09.
 */
@Data
@Entity
@Table
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String city;
    private String street;
    private String zipcode;
}
