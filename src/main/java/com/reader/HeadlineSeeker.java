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

	public List<String> getContentInHeadline(String headline, String html) {
		List<String> result = new ArrayList<String>();
		long from = 0, to = 0, symbolPosition = 0;
		String foundObject = "";
		while (from <= html.length()) {
			// System.out.println("symbolposition: " + symbolPosition + " from
			// :" + from + " to: " + to);
			symbolPosition = html.indexOf("<" + headline, (int) to);

			if (symbolPosition < 0)
				break;

			from = html.indexOf(">", (int) symbolPosition);

			to = html.indexOf("</" + headline + ">", (int) symbolPosition);

			foundObject = html.substring((int) from + 1, (int) to);

			result.add(foundObject);

		}
		return result;
	}

	public static void main(String[] args) {
		HeadlineSeeker service = new HeadlineSeeker();
		String result = service.readHTML("https://www.facebook.com/felipe.gouvea.7/photos?source_ref=pb_friends_tl");
		//List<String> titles = service.getContentInHeadline("i", result);
		//System.out.println(titles.toString());

	}
}
