package com.reader;

import java.util.ArrayList;
import java.util.List;

import edu.princeton.cs.introcs.In;

public class HeadlineSeeker {

	public String readHTML(String link) {
		String html;
		try {
			In page = new In(link);
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
		// System.out.println("symbolposition: " + symbolPosition + " from
		// :" + from + " to: " + to);
		symbolPosition = html.indexOf("<" + headline.toString(), (int) to);
		if (symbolPosition < 0)
			break;
		// break;
		from = html.indexOf(">", (int) (symbolPosition));
		to = html.indexOf("</" + headline.getSymbol() + ">", (int) from);

		foundObject = html.substring((int) from + 1, to);
		result.add(foundObject);

		 }
		return result;
	}

	public void getContent(String headline, String html) {

	}

	public static void main(String[] args) {
		HeadlineSeeker service = new HeadlineSeeker();
		String result = service.readHTML("http://www.estadao.com.br/");
		// System.out.println(result);
		Headline headline = new Headline("h3");
		headline.addProperty("class", "minhaclasse");
		headline.addProperty("data", "");
		headline.addProperty("data-area", "home");

		System.out.println(headline.toString());
		//List<String> titles = service.getContentInHeadline(headline, result);
		//System.out.println(titles.toString());

	}
}
