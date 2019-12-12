package naver.yh_park02pae1.view.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ViewServiceImpl implements ViewService {

	@Override
	public List<String> fileList() {
		List<String> list = new ArrayList<>();
		//파일 목록을 가져올 디렉토리 설정 
		File f = new File("/Users/tjoeun-304/Downloads");
		//디렉토리 안의 모든 파일과 디렉토리 이름 가져오기
		String [] s = f.list();
		//디렉토리가 아닌 이름들만 list에 추가
		//원래는 이름을 가지고 File 객체를 만든 후 isDirectory 함수로 
		//확인을 해야 하는데 
		//확장자가 없는 것은 디렉토리
		//.으로 시작하는 파일이나 디렉토리는 숨김이므로 제거하기
		for(String temp : s) {
			if(temp.indexOf(".") > 0) {
				list.add(temp);
			}
		}
		return list;
	}
}
