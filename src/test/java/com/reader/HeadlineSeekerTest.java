package com.reader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

public class HeadlineSeekerTest {
	
	@Test
	public void readHTMLTest(){
		HeadlineSeeker service = new HeadlineSeeker();
		String result = service.readHTML("http://www.pudim.com.br");
		String resultTwo = service.readHTML("http://www.pudim.com.br");
		assertNotNull(result);
		assertNotNull(resultTwo);
		
		assertTrue(result.contains("<title>Pudim</title>"));
		//see the lenght of pudim.html on resources
		assertTrue(result.length() >= 850);
		assertEquals(result, resultTwo);
		
		String error = service.readHTML("www.pudim.com.br");
		assertEquals(error, "");		
	}
	
	
	@Test
	public void getContentInHeadlineTest(){
		HeadlineSeeker service = new HeadlineSeeker();
		String html = "<h1>Olá eu sou o Goku</h1><h1>e digo, grandes poderes trazem grandes responsabilidades</h1>";
		String testOne = "h1";
		List<String> result = service.getContentInHeadline(testOne, html);
		System.out.println(result.toString());
		assertEquals(result.get(0), "Olá eu sou o Goku");
		assertEquals(result.get(1), "e digo, grandes poderes trazem grandes responsabilidades");
		
		String testTwo = "h2";
		List<String> resultTwo = service.getContentInHeadline(testTwo, html);
		assertEquals(resultTwo.size(), 0);
		
		
		
	}
}
