package manyToManyEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by Kim Donghoon on 2015-11-18.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="manyToManyEntityMemberProduct")
@IdClass(MemberProductId.class)
public class MemberProduct {
    @Id
    @ManyToOne
    @JoinColumn(name = "memberId")
    private Member member;

    @Id
    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;

    private int orderAmount;
}
