package spring1203hibernate;

import java.util.List;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import naver.yh_park02.dao.ItemDao;
import naver.yh_park02.domain.Item;
import naver.yh_park02.service.ItemService;

//JUnit에서 설정 파일을 사용할 수 있도록 해주는 설정 
@RunWith(SpringJUnit4ClassRunner.class)
//스프링 설정 파일 실행 
@ContextConfiguration(locations= {"classpath:applicationContext.xml"})

public class HibernateTest {
	@Autowired
	//private SessionFactory sessionFactory;
	private ItemDao itemDao;
	@Autowired
	private ItemService itemService;
	
	@Test
	public void hibernateTest(){
		//itemService.insert();
		//itemService.update();
		//itemService.delete();
		//itemService.list();
		itemService.get(1);
		
	}
}
