package org.ohjic.jpa.ex05.biDirection;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Kim Donghoon
 * @version 1.0
 * @created 12-11-2015 ���� 9:04:36
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "team_bidirection")
public class Team {
    @Id
    private String id;
    private String name;
    @OneToMany(mappedBy = "team")
    private List<Member> members = new ArrayList<Member>();
}