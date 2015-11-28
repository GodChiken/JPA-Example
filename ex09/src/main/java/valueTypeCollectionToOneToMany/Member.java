package valueTypeCollectionToOneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Kim Donghoon on 2015-11-28.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "valueTypeCollectionToOneToManyMember")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Embedded
    private Address address;

    @ElementCollection
    @CollectionTable(name = "valueTypeCollectionToOneToManyFavoriteFood", joinColumns = @JoinColumn(name = "memberId"))
    @Column(name = "foodName")
    private Set<String> favoriteFoods = new HashSet<>();

    /*@ElementCollection
    @CollectionTable(name = "valueTypeCollectionToOneToManyAddress", joinColumns = @JoinColumn(name = "memberId"))
    private List<Address> addressHistory = new ArrayList<>();*/

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "memberId")
    private List<AddressEntity> addressHistory = new ArrayList<>();
}
