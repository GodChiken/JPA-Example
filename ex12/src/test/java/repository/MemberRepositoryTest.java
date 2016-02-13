package repository;

import domain.Member;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import service.SpringTest;

import static org.junit.Assert.*;

/**
 * Created by Kim Donghoon on 2016-02-13.
 */
public class MemberRepositoryTest extends SpringTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void TestSpringDataRepository() {
        memberRepository.findByAgeAndUserName(12, "김동훈");
    }

    @Test
    public void TestFindByUserNameAndParentName() {
        memberRepository.findByUserNameAndParentName("본인이름", "부모이름");
        memberRepository.findByUserNameAndParentName("부모이름", "본인이름");
    }

    @Test
    public void testFindByUserName() throws Exception {
        memberRepository.findByUserName("김동훈");
    }

    @Test
    public void testFindByUserNameNative() throws Exception {
        memberRepository.findByUserNameNative("김동훈");
    }

    @Test
    public void testFindByUserNameNativeParameterBind() throws Exception {
        memberRepository.findByUserNameNativeParameterBind("김동훈");
    }

    @Test
    public void testBulkAgeUp() throws Exception {
        memberRepository.bulkAgeUp(10);
    }

    @Test
    public void testFindByUserNameStartingWith() throws Exception {
        PageRequest pageRequest = new PageRequest(0, 10, new Sort(Sort.Direction.DESC, "userName"));
        Page<Member> memberPage = memberRepository.findByUserNameStartingWith("1", pageRequest);

        assertEquals("조회된 데이터는 없어야 한다. ", 2, memberPage.getContent().size());
        assertEquals("전체 페이지 수는 0이어야한다. ", 1, memberPage.getTotalPages());
        assertEquals("다음페이지는 존재하지 않는다. ", false, memberPage.hasNext());

    }

    @Test
    public void testFindByParentName() throws Exception {
        PageRequest pageRequest = new PageRequest(0, 10, new Sort(Sort.Direction.DESC, "userName"));
        Page<Member> memberPage = memberRepository.findByParentName("3", pageRequest);
    }


    @Test
    public void testFindByParentName1() throws Exception {
        memberRepository.findByUserName("김동훈");
        memberRepository.findByParentName("3");
    }
}