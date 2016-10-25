package com.network;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Authenticator;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Website {

	private static final String CHARSET_NAME = "UTF-8";

	private static final Locale LOCALE = Locale.US;

	private static final Pattern WHITESPACE_PATTERN = Pattern.compile("\\p{javaWhitespace}+");

	private static final Pattern EVERYTHING_PATTERN = Pattern.compile("\\A");

	private Scanner scanner;

	public Website(String urlName, final ProxyLogin login) {
		URL url;
		try {
			url = new URL(urlName);

			Authenticator.setDefault(new Authenticator() {
				@Override
				public PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(login.getUser(), login.getPassword().toCharArray());
				}
			});

			Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("lnx237in.sjk.emb", 9090));
			URLConnection site = url.openConnection(proxy);

			InputStream is = site.getInputStream();
			scanner = new Scanner(new BufferedInputStream(is), CHARSET_NAME);
			scanner.useLocale(LOCALE);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String readAll() {
		if (!scanner.hasNextLine())
			return "";

		String result = scanner.useDelimiter(EVERYTHING_PATTERN).next();

		scanner.useDelimiter(WHITESPACE_PATTERN);
		return result;
	}

}
