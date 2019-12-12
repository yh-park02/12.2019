package naver.yh_park02.validation.service;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import naver.yh_park02.validation.domain.Birth;

public class BirthValidator implements Validator {

	//유효성 검사를 수행할 클래스를 설정하는 역할을 하는 메소드
	@Override
	public boolean supports(Class<?> clazz) {
		return Birth.class.isAssignableFrom(clazz);
	}

	//유효성 검사 실패 조건과 메시지를 설정
	@Override
	public void validate(Object target, Errors errors) {
		//유효성 검사 수행할 객체로 변환
		Birth birth = (Birth)target;
		
		//유효성 검사 실패 조건과 메시지를 설정
		int year = birth.getYear();
		Calendar cal = new GregorianCalendar();
		if(year < 1920 || year > cal.get(Calendar.YEAR)) {
			//error.year 메시지를 출력
			errors.rejectValue("year", "error");
		}
		
		int month = birth.getMonth();
		if(month < 1 || month > 12) {
			errors.rejectValue("month", "error");
		}
		
		int day = birth.getDay();
		if(day < 1 || day > 31) {
			errors.rejectValue("day", "error");
		}

	}

}
