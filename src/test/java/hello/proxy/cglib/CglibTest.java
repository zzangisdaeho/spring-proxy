package hello.proxy.cglib;

import hello.proxy.cglib.code.TimeMethodInterceptor;
import hello.proxy.common.service.ConcreteService;
import hello.proxy.common.service.ServiceImpl;
import hello.proxy.common.service.ServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.cglib.proxy.Enhancer;

@Slf4j
public class CglibTest {

    /**
     * CGLIB 제약
     * 클래스 기반 프록시는 상속을 사용하기 때문에 몇가지 제약이 있다.
     * 부모 클래스의 생성자를 체크해야 한다. CGLIB는 자식 클래스를 동적으로 생성하기 때문에 기본 생성자가 필요하다.
     * 클래스에 final 키워드가 붙으면 상속이 불가능하다. CGLIB에서 예외가 발생한다.
     * 메서드에 final 키워드가 붙으면 해당 메서드를 오버라이딩 할 수 없다. CGLIB에서 해당 메서드는는프록시 로직이 동작하지 않는다.
     */
    @Test
    @DisplayName("Cglib는 구현클래스로 프록시 생성 가능하다")
    void cglib(){
        ConcreteService target = new ConcreteService();

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
//        enhancer.setSuperclass(ConcreteService.class);
        enhancer.setCallback(new TimeMethodInterceptor(target));
        //proxy 생성
        ConcreteService proxy = (ConcreteService) enhancer.create();
        log.info("targetClass = {}", target.getClass());
        log.info("proxyClass = {}", proxy.getClass());

        proxy.call();
    }

    @Test
    @DisplayName("Cglib는 인터페이스로도 프록시 생성이 가능하다")
    void cglibInterface(){
        ServiceInterface target = new ServiceImpl();

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
//        enhancer.setSuperclass(ConcreteService.class);
        enhancer.setCallback(new TimeMethodInterceptor(target));
        //proxy 생성
        ServiceImpl proxy = (ServiceImpl) enhancer.create();
        log.info("targetClass = {}", target.getClass());
        log.info("proxyClass = {}", proxy.getClass());

        proxy.find();
    }

    @Test
    @DisplayName("Cglib는 구현체를 주어도 프록시 생성이 가능하다")
    void cglibImpl(){
        ServiceImpl target = new ServiceImpl();

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
//        enhancer.setSuperclass(ConcreteService.class);
        enhancer.setCallback(new TimeMethodInterceptor(target));
        //proxy 생성
        ServiceImpl proxy = (ServiceImpl) enhancer.create();
        log.info("targetClass = {}", target.getClass());
        log.info("proxyClass = {}", proxy.getClass());

        proxy.find();
    }
}
