package test;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import dao.ReflectionDAO;
import model.Reflection;
import model.DateSet;


public class ReflectionDAOTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void 値が入力できるか() {
		Reflection reflection = new Reflection();
		reflection.setUserId("aaa");
		Date date = new Date(159721599999L);
		long miliseconds = System.currentTimeMillis();
		Date today = new Date(miliseconds);	
		reflection.setStartDate(today);
		reflection.setEndDate(today);
		reflection.setType(3);

		reflection.setComment("やっぱ氷菓は面白い");
		
		ReflectionDAO dao = new ReflectionDAO();
		boolean result = dao.create(reflection);
		
		assertEquals(result,true);
	}
	

	@Test
	public void データを取り出せるか() {
		
		String userId = "aaa";
		Date date = new Date(159721599999L);
		
		
		ReflectionDAO dao = new ReflectionDAO();
		List<Reflection> reflectionList = dao.findReflection(userId, date, date, 1); 
		
		
		for(Reflection reflection : reflectionList) {
			assertEquals(1,reflection.getId());
			assertEquals("aaa",reflection.getUserId());
			assertEquals(date,reflection.getStartDate());
			assertEquals(date,reflection.getEndDate());
			assertEquals(1,reflection.getType());
			assertEquals(true,reflection.getTodayDo());
			assertEquals("本を読む",reflection.getTodo());
			assertEquals("ちゃんとメモした",reflection.getGood());
			assertEquals("時間かかった",reflection.getBad());
			assertEquals("2ページ呼んだ",reflection.getProgress());
			assertEquals("別の本読みたい",reflection.getComment());

		}
	}	
	
	@Test
	public void データの変更ができるか() {
		
		Reflection oldReflection = new Reflection();
		oldReflection.setId(1);
		oldReflection.setUserId("aaa");
		Date date = new Date(159721599999L);
		oldReflection.setStartDate(date);
		oldReflection.setEndDate(date);
		oldReflection.setType(1);
		oldReflection.setTodayDo(true);
		oldReflection.setTodo("本を読む");
		oldReflection.setGood("ちゃんとメモした");
		oldReflection.setBad("時間かかった");
		oldReflection.setStuck("ちょっとこの本ムズイ");
		oldReflection.setProgress("2ページ呼んだ");
		oldReflection.setComment("別の本読みたい");
		
		Reflection newReflection = new Reflection();
		newReflection.setTodayDo(true);
		newReflection.setTodo("ご飯を食べる");
		newReflection.setGood("ちゃんとメモした");
		newReflection.setBad("時間かかった");
		newReflection.setStuck("ちょっとこの本ムズイ");
		newReflection.setProgress("2ページ呼んだ");
		newReflection.setComment("別の本読みたい");
		
		ReflectionDAO dao = new ReflectionDAO();
		boolean result = dao.change(oldReflection, newReflection);
		assertEquals(true,result);
	}
	
	
	@Test
	public void 一致した日付があると確認できるか() {
		String userId = "aaa";
		Date date = new Date(159721599999L);
		
		
		ReflectionDAO dao = new ReflectionDAO();
		boolean result = dao.dateCheck(userId, date, date);
		
		assertEquals(false,result);
		
	}
	
	@Test
	public void 一致した日付がないと確認できるか() {
		String userId = "aaa";
		long miliseconds = System.currentTimeMillis();
		Date date = new Date(miliseconds);	
		
		
		ReflectionDAO dao = new ReflectionDAO();
		boolean result = dao.dateCheck(userId, date, date);
		
		assertEquals(true,result);
		
	}
	
	@Test
	public void 日付の取り出しができるか() {
		String userId = "aaa";
		Date date = new Date(159721599999L);
		long miliseconds = System.currentTimeMillis();
		Date today = new Date(miliseconds);	
		
		ReflectionDAO dao = new ReflectionDAO();
		List<DateSet> dateSetList  = dao.findDate(userId);
		for(DateSet dateSet : dateSetList) {
		assertEquals(today,dateSet.getStartDate());
		assertEquals(today,dateSet.getEndDate());
		}
	}
	
}
