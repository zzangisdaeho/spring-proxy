package hello.proxy.cglib.code;

import hello.proxy.common.service.ConcreteService;
import hello.proxy.common.service.ServiceImpl;
import hello.proxy.common.service.ServiceInterface;
import hello.proxy.jdkdynamic.code.AInterface;
import hello.proxy.jdkdynamic.code.BInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

@Slf4j
public class TimeMethodInterceptor implements MethodInterceptor {

    private final Object target;

    public TimeMethodInterceptor(Object target) {
        this.target = target;
    }


    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        log.info("obj instanceof ConcreteService : {}", obj instanceof ConcreteService);
        log.info("obj instanceof ServiceInterface : {}", obj instanceof ServiceInterface);
        log.info("obj instanceof ServiceImpl : {}", obj instanceof ServiceImpl);

        log.info("TimeProxy 실행");
        long startTime = System.currentTimeMillis();

//        Object result = method.invoke(target, args);
        Object result = methodProxy.invoke(target, args); // CGLIB왈 : methodProxy를 사용하는게 성능상 최적화되어있어서 더 빠르다

        long endTime = System.currentTimeMillis();

        long resultTime = endTime - startTime;

        log.info("TimeProxy 종료 resultTime = {}", resultTime);

        return result;
    }
}
