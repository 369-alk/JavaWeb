package com.htmlstatic;  
  
import java.io.ByteArrayOutputStream;  
import java.io.File;  
import java.io.FileNotFoundException;  
import java.io.FileOutputStream;  
import java.io.IOException;  
import java.io.OutputStreamWriter;  
import java.io.PrintWriter;  
import java.util.ArrayList;  
import java.util.List;  
  
import javax.servlet.RequestDispatcher;  
import javax.servlet.ServletContext;  
import javax.servlet.ServletException;  
import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
import javax.servlet.http.HttpServletResponseWrapper;  
  
@SuppressWarnings("serial")
public class ToHtml extends HttpServlet {  
    private static final String CONTENT_TYPE = "text/html; charset=GBK"; //$NON-NLS-1$  
  
    private static ServletContext sc = null;  
    private HttpServletRequest request = null;  
    private HttpServletResponse response = null;  
    private static String staticHtmlRefreshTime = "1";//刷新间隔时间  
    private static String path = "D:\\jingtaihua";//文件生成路径  
    private static String url = "/index.jsp";//需要静态化的页面  
  
      
    // Initialize global variables  
    public void init() 
    		throws ServletException {  
    } 
  
    // Process the HTTP Get request  
    public void doGet(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {  
        response.setContentType(CONTENT_TYPE);  
        service(request, response);  
    }  
  
    // Process the HTTP Post request  
    public void doPost(HttpServletRequest request, HttpServletResponse response) 
    			throws ServletException, IOException {  
        doGet(request, response);  
    }  
  
    public void destroy() {  
    }  
  
    @SuppressWarnings({ "static-access", "unchecked", "rawtypes" })
	public void service(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {  
        response.setContentType(CONTENT_TYPE);  
        long SPACING = 0;  
        if (staticHtmlRefreshTime == null) {  
            SPACING = 1000 * 60 * 10;  
        } else {  
            SPACING = 1000 * 60 * Long.parseLong(staticHtmlRefreshTime);  
        }  
  
        sc = getServletContext();  
        this.request = request;  
        this.response = response;  
          
        String[] urls = url.split(",");  
  
        List names = new ArrayList();  
        for (int i = 0; i < urls.length; i++) {  
            String tmp = urls[i].replaceAll(".jsp", ".html");  
            names.add(tmp);  
        }  
        while (true) {  
            System.err.println("静态化开始");  
            myService(urls, names);  
            System.err.println("静态化结束");  
            try {  
                Thread.currentThread().sleep(SPACING);  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
        }  
    }  
  
    @SuppressWarnings("rawtypes")
	public synchronized void myService(String[] urls, List names) {  
  
        String name = "";  
        for (int i = 0; i < urls.length; i++) {  
            FileOutputStream fos = null;  
            try {  
                name = path + names.get(i).toString().replaceAll(".html", ".html");  
                RequestDispatcher rd = sc.getRequestDispatcher("/" + urls[i]);  
                final ByteArrayOutputStream os = new ByteArrayOutputStream();  
                final ServletOutputStream stream = new ServletOutputStream() {  
                    public void write(byte[] data, int offset, int length) {  
                        os.write(data, offset, length);  
                    }  
  
                    public void write(int b) throws IOException {  
                        os.write(b);  
                    }

					@Override
					public boolean isReady() {
						// TODO Auto-generated method stub
						return false;
					}

					@Override
					public void setWriteListener(WriteListener arg0) {
						// TODO Auto-generated method stub
						
					}  
                };  
                final PrintWriter pw = new PrintWriter(new OutputStreamWriter(os ,"UTF-16"));
                
                HttpServletResponse rep = new HttpServletResponseWrapper(response) {  
                    public ServletOutputStream getOutputStream() {  
                        return stream;  
                    }  
  
                    public PrintWriter getWriter() {  
                        return pw;  
                    }  
                };  
  
                rd.include(request, rep);  
                pw.flush();  
                System.out.println("name=="+name);  
                  
                fos = new FileOutputStream(name);  
                // 把jsp输出的内容写到xxx.htm  
                os.writeTo(fos);  
            } catch (FileNotFoundException e) {  
                e.printStackTrace();  
            } catch (ServletException e) {  
                e.printStackTrace();  
            } catch (IOException e) {  
                e.printStackTrace();  
            } finally {  
                try {  
                    fos.close();  
                } catch (Exception e) {  
                }  
            }  
  
        }         
    }  
  
    @SuppressWarnings("unused")
    //写文件
	private void writeFile(String fileName, String content) {  
        OutputStreamWriter writer = null;  
        try {  
            File f = new File(path + "\\" + fileName);  
            if (!f.exists()) {  
                f.createNewFile();  
            }  
            writer = new OutputStreamWriter(new FileOutputStream(f), "GBK");  
            writer.write(content);  
            writer.flush();  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            try {  
                writer.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
    }  
}  