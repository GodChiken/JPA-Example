package valueTypeCollectionToOneToMany;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Set;

/**
 * Created by Kim Donghoon on 2015-11-28.
 */
public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("fin_jpa_unit");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();


        System.out.println("===============================================================");
        System.out.println("값 타입의 저장");
        System.out.println("===============================================================");

        Member member = new Member();
        member.setAddress(new Address("통영", "몽돌해수욕장", "660-123"));
        member.getFavoriteFoods().add("짬뽕");
        member.getFavoriteFoods().add("짜장");
        member.getFavoriteFoods().add("탕수육");

        member.getAddressHistory().add(new AddressEntity(1, new Address("서울", "강남", "123-123")));
        member.getAddressHistory().add(new AddressEntity(2, new Address("서울", "강북", "000-000")));

        em.persist(member);
        em.flush();
        em.clear();


        System.out.println("===============================================================");
        System.out.println("값 타입의 조회");
        System.out.println("===============================================================");

        member = em.find(Member.class, 1L);
        Address homeAddress = member.getAddress();

        Set<String> favoriteFoods = member.getFavoriteFoods();
        favoriteFoods.stream()
                .forEach(food -> System.out.println(food));

        List<AddressEntity> addressHistory = member.getAddressHistory();
        addressHistory.stream()
                .forEach(address -> System.out.println(address));

        System.out.println(
                addressHistory.stream()
                        .findFirst()
        );

        em.flush();
        em.clear();


        System.out.println("===============================================================");
        System.out.println("값 타입의 수정");
        System.out.println("===============================================================");

        member = em.find(Member.class, 1L);
        member.setAddress(new Address("새로운도시", "신도시1", "123456"));
        favoriteFoods = member.getFavoriteFoods();
        favoriteFoods.remove("탕수육");
        favoriteFoods.add("치킨");
        addressHistory = member.getAddressHistory();
        addressHistory.remove(new AddressEntity(2, new Address("서울", "강북", "000-000")));
        addressHistory.add(new AddressEntity(3, new Address("새로운도시", "새로운주소", "123-456")));

        tx.commit();
        em.close();
        emf.close();
    }
}
