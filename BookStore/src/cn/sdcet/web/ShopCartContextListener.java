package cn.sdcet.web;

import java.io.*;
import java.util.*;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ShopCartContextListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		ServletContext context = sce.getServletContext();
		context.removeAttribute("books");

	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext context = sce.getServletContext();
		HashMap<Integer, Book> books = new HashMap<Integer, Book>();
		String file = context.getRealPath("list.txt");
		try {
			BufferedReader in = new BufferedReader(new FileReader(file));
			String line = "";
			int id = 0;
			while ((line = in.readLine()) != null) {
				String[] info = line.split(",");
				if (info.length != 5) {
					continue;
				}
				Book book = new Book(++id, info[0], info[1], info[2], Integer.parseInt(info[3]), info[4]);
				books.put(book.getId(), book);
				System.out.println("数据信息："+book);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		context.setAttribute("books", books);

	}

}
