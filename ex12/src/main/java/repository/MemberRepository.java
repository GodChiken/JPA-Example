package repository;

import domain.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.List;

/**
 * Created by Kim Donghoon on 2016-02-13.
 */
public interface MemberRepository extends JpaRepository<Member, Long> {
    List<Member> findByAgeAndUserName(@Param("age") int age, @Param("name") String name);

    List<Member> findByUserNameAndParentName(String un, String pn);

    @Query(value = "select m from Member m where m.parentName = ?1")
    List<Member> findByUserName(String userName);

    @Query(value = "select * from member where user_name = ?1", nativeQuery = true)
    List<Member> findByUserNameNative(String userName);

    @Query(value = "select * from member where parent_name = :name", nativeQuery = true)
    List<Member> findByUserNameNativeParameterBind(@Param("name") String userName);

    @Modifying(clearAutomatically = true)
    @Query("update Member m set m.age = m.age * 10 where m.age > :age")
    int bulkAgeUp(@Param("age") int age);

    Page<Member> findByUserName(String name, Pageable pageable);

    /*List<Member> findByUserName(String name, Pageable pageable);*/
    List<Member> findByUserName(String name, Sort sort);

    Page<Member> findByUserNameStartingWith(String userName, Pageable pageable);

    @QueryHints(value = {@QueryHint(name = "org.hibernate.readOnly", value = "true")}, forCounting = true)
    Page<Member> findByParentName(String name, Pageable pageable);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    List<Member> findByParentName(String name);
}
