package naver.yh_park02.validation;

import java.io.File;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import naver.yh_park02.validation.domain.Birth;
import naver.yh_park02.validation.service.BirthValidator;

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@RequestMapping(value="birth", method=RequestMethod.GET)
	public String birth(@ModelAttribute("command") Birth birth)
	{
		return "birth";
	}
	
	@RequestMapping(value="birth", method=RequestMethod.POST)
	public String birth(@ModelAttribute("command") Birth birth,
			BindingResult result)
	{
		new BirthValidator().validate(birth, result);
		if(result.hasErrors()) {
			return "birth";
		}else {
			return "complete";
		}
	}
	
	@RequestMapping(value="upload", method=RequestMethod.POST)
	//MultipartHttpServletRequest를 이용하는 방법
	public String upload(MultipartHttpServletRequest request) {
		//여기서 이 설정을 하지 않고 web.xml 파일에 인코딩 필터 설정을 해도 된다. 
		//파라미터를 읽기 전에 파라미터 인코딩 설정 
		try {
			request.setCharacterEncoding("utf-8");
		}catch(Exception e) {
			System.err.println(e.getMessage());
		}
		
		//file 파라미터의 데이터 가져오기
		MultipartFile file = request.getFile("file");
		//파일 업로드 : 문서 디렉토리
		String filepath = "/Users/tjoeun-304/Documents/";
		//저장 경로와 원래의 파일 이름을 결합 
		//파일이름의 중복을 제거하기 위해서 랜덤한 문자열을 추가 
		filepath = filepath + 
				UUID.randomUUID().toString() +
				file.getOriginalFilename();
		try {
			//파일 업로드
			file.transferTo(new File(filepath));
		}catch(Exception e) {
			System.err.println(e.getMessage());
		}
		return "complete";
	}

	
}
