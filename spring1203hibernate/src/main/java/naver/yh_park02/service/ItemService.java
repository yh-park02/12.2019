package naver.yh_park02.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import naver.yh_park02.dao.ItemDao;
import naver.yh_park02.domain.Item;
//@Component, @Controller, @Service, @Repository
//위 4개의 어노테이션은 bean을 자동으로 생성해주는 어노테이션
//트랜잭션의 적용은 Service에서 한다. 
@Service
public class ItemService {
	//동일한 자료형의 bean이 있으면 아래 변수에 대입하도록 하는 설정 
	@Autowired
	private ItemDao itemDao;

	//데이터 삽입하는 메소드 
	@Transactional
	public void insert() {
		Item item = new Item();
		item.setCode(200);
		item.setName("사과");
		item.setPrice(3000);
		item.setManufacture("문경");
		itemDao.insert(item);
	}
	
	//데이터 수정하는 메소드 
	@Transactional
	public void update() {
		Item item = new Item();
		item.setCode(200);
		item.setName("배");
		item.setPrice(7000);
		item.setManufacture("배");
		itemDao.update(item);
	}
	
	//데이터 삭제하는 메소드 
	@Transactional
	public void delete() {
		Item item = new Item();
		item.setCode(200);
		itemDao.delete(item);
	}
	
	//전체 데이터 가져오는 메소드 
	@Transactional
	public void list() {
		List<Item> list = itemDao.allItem();
		for(Item item : list) {
			System.err.println(item);
		}
	}
	
	//데이터를 1개 가져오는 메소드 
	@Transactional
	public void get(int code) {
		Item item = itemDao.getItem(code);
		System.err.println(item);
	}
}
