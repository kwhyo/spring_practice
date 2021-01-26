package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService {
    private final MemberRepository memberRepository ;

    //@Autowired //아 너는 레포지토리가 필요하구나, 레포지토리를 스프링 컨테이너의 레포지토리를 넣어준다.(주입해줌.)
    public MemberService(MemberRepository memberRepository) { //레포지토리를 외부에서 넣어줌. -> DI개념
        this.memberRepository = memberRepository;
    }


    /**
     *
     회원가입
     */
    public Long join(Member member){
        validateDuplicateMember(member);//중복회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
        .ifPresent(member1 -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }
    /**
     *
     전체조회
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }
    /**
     *
    한 명 찾기
     */
    public  Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
