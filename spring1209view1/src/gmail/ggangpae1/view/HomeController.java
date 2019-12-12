package gmail.ggangpae1.view;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import gmail.ggangpae1.view.domain.Book;
import gmail.ggangpae1.view.domain.BookReport;

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
	
	//excel.xls 요청을 처리하는 메소드
	@RequestMapping(
		value="excel.xls", method=RequestMethod.GET)
	public String excel(Model model) {
		
		//서비스의 메소드를 호출해서 데이터를 가져와야 하지만
		//편의상 직접 생성
		List<String> list = new ArrayList<>();
		list.add("캡슐화");
		list.add("상속성");
		list.add("다형성");
		//데이터 저장
		model.addAttribute("oop", list);
		
		//뷰 이름 설정
		return "excel";
	}
	
	//oop.pdf 요청을 처리하는 메소드
		@RequestMapping(
			value="oop.pdf", method=RequestMethod.GET)
		public String pdf(Model model) {
			
			//서비스의 메소드를 호출해서 데이터를 가져와야 하지만
			//편의상 직접 생성
			List<String> list = new ArrayList<>();
			list.add("캡슐화");
			list.add("상속성");
			list.add("다형성");
			//데이터 저장
			model.addAttribute("oop", list);
			
			//뷰 이름 설정
			return "pdf";
		}
		
		@RequestMapping(
				value="view.json", method=RequestMethod.GET)
		public String json(Model model) {
			Map<String,Object>map = 
				new HashMap<String, Object>();
			map.put("name", "겨울왕국");
			map.put("company", "픽사");
			
			model.addAttribute("data", map);
			return "json";
		}
		
		@RequestMapping(value="book.xml", method=RequestMethod.GET)
		public String xml(Model model) {
			//출력할 데이터 생성 
			BookReport report = new BookReport();
			
			Book book = new Book();
			book.setTitle("삼국지");
			book.setAuthor("나관중");
			List<Book> list = new ArrayList<Book>();
			list.add(book);
			
			book = new Book();
			book.setTitle("군주론");
			book.setAuthor("마키아벨리");
			list.add(book);
			
			report.setList(list);
			
			model.addAttribute("list", report);
			return "xml";
		}
		
		//add라는 요청에 first와 second라는 파라미터가 있는 
		//요청을 처리하는 메소드
		@RequestMapping(value="add", method=RequestMethod.GET)
		public String add(@RequestParam("first")int first,
				@RequestParam("secind")int second,
				Model model) {
			int result = first + second;
			model.addAttribute("result", result);
			
			return "result";
		}
		
		//Controller에 Exception이 발생하면 
		//return하는 뷰를 출력하도록 하는 설정
		@ExceptionHandler(Exception.class)
		public String exception(Model model) {
			model.addAttribute("msg", "빠른 시간에 처리하겠습니다.");
			return "error/exception";
		}
		
}






