package naver.yh_park02.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
//AOP 클래스로 설정 
@Aspect
public class AOPAdvice {
	@Around("execution(public * naver.yh_park02.aop..*Controller.*(..))")
	public Object invoke(ProceedingJoinPoint joinpoint) {	
		Object obj = null;
		try {
			System.err.println("메소드 수행 전에 호출");
			obj = joinpoint.proceed();
		}catch(Throwable e) {
			
		}
		return obj;
	}
}