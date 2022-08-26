package co.micol.sorami;



import java.util.List;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


import co.micol.serviceImpl.NoticeServiceImpl;
import co.micol.vo.NoticeVO;


public class JacksonTest {
	
	//@Test
	public void toJson() throws JsonProcessingException {
		NoticeServiceImpl sevice = new NoticeServiceImpl();
		List<NoticeVO> list = sevice.noticeSelectList();
		
		ObjectMapper objectMapper = new ObjectMapper();
		String result = objectMapper.writeValueAsString(list);
		System.out.println(result);
	}
	
	@Test
	public void toObject() throws JsonMappingException, JsonProcessingException {
		String str = "{\"noticeId\":2,\"noticeWriter\":\"micol\",\"noticeTitle\":\"파일 업로드 텍스트2\",\"noticeSubject\":null,\"noticeDate\":\"2022-06-28\",\"noticeHit\":0,\"noticeAttech\":\"테스트.txt\",\"noticeAttechDir\":null}";
		ObjectMapper objectMapper = new ObjectMapper();
		NoticeVO vo = objectMapper.readValue(str, NoticeVO.class);
		System.out.println(vo.getNoticeTitle());
	}
	
	

}
