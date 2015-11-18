package org.ohjic.jpa.ex05.memberToTeam;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "member_membertoteam")
public class Member {

	@Id
	private String id;
	@ManyToOne
	private Team team;
	private String username;

}