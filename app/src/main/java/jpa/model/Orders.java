package jpa.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Orders {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	private Member member;
	@OneToMany(mappedBy = "orders")
	private List<OrderItem> orderItems = new ArrayList<>();
	@OneToOne
	private Delivery delivery;
	private Date orderDate;
	@Enumerated(EnumType.STRING)
	private OrderStatus status;
}