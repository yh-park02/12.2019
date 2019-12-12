package naver.yh_park02.view;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import naver.yh_park02.view.domain.Member;
import naver.yh_park02.view.validator.MemberValidator;

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		return "home";
	}	
	
	//단순한 페이지 이동의 경우는 매개변수 없이  
	//포워딩할 페이지 이름만 리턴하면 된다. 
	@RequestMapping(value="login", method=RequestMethod.GET)
	public String login() {
		return "login";
	}
	
	//join 요청이 GET 방식으로 온 경우 처리 
	//Spring 유효성 검사를 사용하는 경우에는 DTO 객체를 매개변수로 생성
	//Member 객체가 넘어갈 때 command라는 이름으로 넘어간다. 
	@RequestMapping(value="join", method=RequestMethod.GET)
	public String join(@ModelAttribute("command")Member member) {
		return "join";
	}
	
	//join 요청을 POST 방식으로 요청했을 때 호출되는 메소드
	//form의 데이터를 전송할 때는 BindingResult를 만들어서 
	//유효성 검사의 결과를 저장하도록 한다.
	@RequestMapping(value="join", method=RequestMethod.POST)
	public String join(@ModelAttribute("command")Member member, 
			BindingResult result) {
		//유효성 검사
		//member을 가지고 유효성 검사를 해서 결과를 result에 저장
		new MemberValidator().validate(member, result);
		//유효성 검사를 통과하지 못한 경우
		if(result.hasErrors()) {
			return "join";
		}
		return "joincomplete";
	}
}