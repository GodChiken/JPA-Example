package org.ex.doqi.service;

import org.ex.doqi.domain.Member;
import org.ex.doqi.repository.MemberRepository;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mariadb.jdbc.MySQLDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.transaction.Transactional;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.Assert.*;

/**
 * Created by Kim Donghoon on 2016-02-10.
 */

public class MemberServiceTest extends SpringTest {

    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void 회원_가입_테스트() throws Exception {
        Member member = new Member();
        member.setName("kim");

        long saveId = memberService.join(member);

        assertEquals(member, memberRepository.fineOne(saveId));
    }

    @Test(expected = IllegalStateException.class)
    public void 중복_회원_예외() {
        Member member1 = new Member();
        member1.setName("김동훈");

        Member member2 = new Member();
        member2.setName("김동훈");

        memberService.join(member1);
        memberService.join(member2);

        fail("예외 발생해야한다.");
    }
}