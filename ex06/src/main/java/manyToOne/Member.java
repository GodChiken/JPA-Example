package manyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by Kim Donghoon on 2015-11-15.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "manyToOneMember")
public class Member {
    @Id
    @GeneratedValue
    private Long id;
    private String userName;
    @ManyToOne
    @JoinColumn(name = "teamId")
    private Team team;

    public void setTeam(Team team){
        this.team = team;
        if(!team.getMembers().contains(this)){
            team.getMembers().add(this);
        }
    }
}
