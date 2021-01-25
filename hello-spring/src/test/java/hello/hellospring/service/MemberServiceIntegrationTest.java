package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest //진짜 스프링을 띄워서 같이 통합테스트할 수 있다.
@Transactional //테스트 완료 후 롤백함.->테스트에서만 롤백으로 작동, 서비스에서는 그냥 db반영
public class MemberServiceIntegrationTest {

    @Autowired  MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    // @Commit ->db에 반영시킴
    void 회원가입() {
        //given - 뭔가가 주어졌을때
        Member member = new Member();
        member.setName("hello");

        //when - 실행했을때
        Long saveId = memberService.join(member);

        //then - 이렇게 나와야한다.
        Member member1 = memberService.findOne(saveId).get();
        Assertions.assertThat(member.getName()).isEqualTo(member1.getName());
    }

    @Test
    void 중복회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");
        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));//조인 실행하면 이 에러가 나야된다.
        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
/* try {
            memberService.join(member2);
            fail();
        }catch (IllegalStateException e){
            Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }*/
        //then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}