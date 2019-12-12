package naver.yh_park02.view.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import naver.yh_park02.view.domain.Member;

//Member 클래스에 대한 유효성 검사 클래스
public class MemberValidator implements Validator {
	
	//어떤 클래스를 유효성 검사할 것인지 설정하는 메소드
	@Override
	public boolean supports(Class<?> clazz) {
		//clazz에 Member 클래스의 객체를 할당할 수 있도록 해주는 설정
		return Member.class.isAssignableFrom(clazz);
	}
	//유효성 검사 메소드
	@Override
	public void validate(Object target, Errors errors) {
		//target을 유효성 검사할 객체로 형변환 
		Member member = (Member)target;
		
		//아이디는 null일 수 없고 글자수가 좌우 공백을 제외한 글자수가 0일 수 없다.
		if(member.getId() == null || member.getId().trim().length() <= 0) {
			//에러 메시지 설정
			//required.id라는 메시지를 사용
			errors.rejectValue("id", "required");
		}
		//비밀번호는 7자 이상
		if(member.getPw().trim().length() < 7) {
			//에러 메시지 설정
			//short,pw라는 메시지를 사용
			errors.rejectValue("pw", "short");
		}
		//비밀번호에 특수문자 사용
		String pw = member.getPw();
		char [] specials = {'@', '#', '$', '%'};
		int etc = 0;
		for(int i=0; i<pw.length(); i=i+1) {
			char ch = pw.charAt(i);
			for(char t : specials) {
				if(ch == t) {
					etc = etc + 1;
					break;
				}
			}
		}
		if(etc == 0) {
			errors.rejectValue("pw", "special");
		}
	}
}
