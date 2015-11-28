package valueTypeCollection;

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
@Entity(name = "valueTypeCollectionMember")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Embedded
    private Address address;

    @ElementCollection
    @CollectionTable(name = "valueTypeCollectionFavoriteFood", joinColumns = @JoinColumn(name = "memberId"))
    @Column(name = "foodName")
    private Set<String> favoriteFoods = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "valueTypeCollectionAddress", joinColumns = @JoinColumn(name = "memberId"))
    private List<Address> addressHistory = new ArrayList<>();
}
