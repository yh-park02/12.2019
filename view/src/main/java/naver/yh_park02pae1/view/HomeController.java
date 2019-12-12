package naver.yh_park02pae1.view;

import java.io.File;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import naver.yh_park02pae1.view.service.ViewService;

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
	
	//Controller에서는 Service를 주입받아야 한다. 
	@Autowired
	private ViewService viewService;
	
	@RequestMapping(value="fileview", method=RequestMethod.GET)
	public String fileview(Model model) {
		//파일 목록을 가져오는 서비스 메소드를 호출
		List<String> list = viewService.fileList();
		//뷰애 전달할 데이터를 저장 
		model.addAttribute("list", list);
		
		return "fileview";
	}
	
	//download?filename=#{name}, 이 요청을 처리하는 메소드
	@RequestMapping(value="download", method=RequestMethod.GET)
	//filename이라는 파라미터를 filename이라는 변수에 저장 
	public String download(Model model, 
			@RequestParam("filename") String filename) {
		//다운로드 받을 파일 객체를 생성
		File file = new File("/Users/tjoeun-304/Downloads" + filename);
		//다운로드 받을 파일을 저장해서 뷰로 전송 
		model.addAttribute("file", file);
		
		return "download";
	}
}
