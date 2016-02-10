package org.ex.doqi.service;

import org.ex.doqi.domain.Member;
import org.ex.doqi.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Kim Donghoon on 2016-02-10.
 */
@Service
@Transactional
public class MemberService {
    @Autowired
    private MemberRepository repository;

    public long join(Member member) {
        this.validateDuplicateMember(member);
        repository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = repository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    public List<Member> findMembers() {
        return repository.findAll();
    }

    public Member findOne(long memberId) {
        return repository.fineOne(memberId);
    }
}
