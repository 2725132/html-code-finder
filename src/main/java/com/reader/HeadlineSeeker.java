package com.reader;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import com.network.ProxyLogin;
import com.network.Website;

public class HeadlineSeeker {

	ProxyLogin proxy;
	public HeadlineSeeker(){
		this.proxy = new ProxyLogin("","");
	}
	
	public String readHTML(String link) {
		String html;
		try {
			Website page = new Website(link, proxy);
			html = page.readAll();
		} catch (Exception e) {
			System.out.println("Link inválido!");
			return "";
		}
		return html;
	}

	public List<String> getContentInHeadline(Headline headline, String html) {
		List<String> result = new ArrayList<String>();
		long from = 0, symbolPosition = 0;
		int to = 0;
		String foundObject = "";
		while (from <= html.length()) {

			symbolPosition = html.indexOf("<" + headline.toString(), (int) to);
			if (symbolPosition < 0)
				break;
			
			from = html.indexOf(">", (int) (symbolPosition));
			to = html.indexOf("</" + headline.getSymbol() + ">", (int) from);
			foundObject = html.substring((int) from + 1, to);
			result.add(foundObject);

		}
		return result;
	}

	public void getContent(String headline, String html) {

	}

	public static void main(String[] args) throws FileNotFoundException {
		HeadlineSeeker service = new HeadlineSeeker();
		String result = service.readHTML("http://stackoverflow.com/");
		// System.out.println(result);
		Headline headline = new Headline("a");
		headline.addProperty("href", "http://cultura.estadao.com.br/");
		FileOutputStream output = new FileOutputStream("resultado");
		PrintStream print = new PrintStream(output);
		print.print(result);
		
		print.close();
		System.out.println(headline.toString());
		List<String> titles = service.getContentInHeadline(headline, result);
		System.out.println(titles.toString());

	}
}
