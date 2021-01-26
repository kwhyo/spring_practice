package hello.hellospring;

import hello.hellospring.aop.TimeTraceAop;
import hello.hellospring.repository.*;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    /*private EntityManager em;
    @Autowired
    public SpringConfig(EntityManager em){
        this.em = em;
    }*/

    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository);
    }

    //aop는 스프링빈에 직접 등록해서 쓰는게 좋음!
    //@Bean
    //public TimeTraceAop timeTraceAop(){
    //    return new TimeTraceAop();
    //}

 //   @Bean
   // public MemberRepository memberRepository(){
        //return new MemoryMemberRepository();
        //return new jpaMemberRepository(em);

//    }
}
