package cn.sdcet.news.web;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class UploadServlet extends HttpServlet {
	/** 上传目录 */
	private String uploadDir = "upload";
	/** 临时目录 */
	private String tempDir = "temp";
	/** 单个文件上传限制，默认为5M */
	private long sizeLimit = 5242880;
	/** 总文件上传限制，默认为20M */
	private long totalLimit = 20971520;
	/** 允许上传图片类型 */
	private String imageFile = "gif,jpg,jpeg,png,bmp";
	/** 允许上传Flash类型 */
	private String flashFile = "swf,flv";
	/** 允许上传多媒体文件类型 */
	private String mediaFile = "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb";
	/** 允许上传文件类型 */
	private String plainFile = "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2";

	private File uploadDirectory, tempDirectory;
	private HashMap<String, String> extMap = new HashMap<String, String>();

	@Override
	public void init() throws ServletException {
		// 定义允许上传的文件扩展名
		extMap.put("image", imageFile);
		extMap.put("flash", flashFile);
		extMap.put("media", mediaFile);
		extMap.put("file", plainFile);

		// 创建上传目录和临时目录
		uploadDirectory = createUploadDir(uploadDir);
		createUploadDir(uploadDir + File.separator + "image");
		createUploadDir(uploadDir + File.separator + "flash");
		createUploadDir(uploadDir + File.separator + "media");
		createUploadDir(uploadDir + File.separator + "file");
		tempDirectory = createUploadDir(tempDir);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/plain;charset=utf-8");
		PrintWriter out = response.getWriter();

		// 上传类型，分别为image（默认）、flash、media、file
		String dirName = request.getParameter("dir");
		if (dirName == null) {
			dirName = "image";
		}
		if (!extMap.containsKey(dirName)) {
			out.println("{\"error\" : 1, \"message\" : \"上传类型不正确\"}");
			return;
		}

		// 1.创建上传工厂
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(1 * 1024 * 1024); // 设定阈值，超过1M的将会被写入到临时文件中。
		factory.setRepository(tempDirectory); // 设置临时目录

		// 2. 创建上传文件处理器
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setHeaderEncoding("UTF-8"); // 指定读取时使用的字符集
		upload.setSizeMax(totalLimit); // 设定最大的上传限制

		try {
			List<FileItem> items = upload.parseRequest(request); // 处理请求，获取所有表单项
			for (FileItem item : items) {
				if (!item.isFormField()) {
					long fileSize = item.getSize();
					String fileName = item.getName();

					// 检查文件大小
					if (fileSize > sizeLimit) {
						out.println("{\"error\":1, \"message\":\"上传文件大小超过限制" + sizeLimit + "。\"}");
						return;
					}

					// 检查扩展名
					String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
					if (!Arrays.<String> asList(extMap.get(dirName).split(",")).contains(fileExt)) {
						out.println("{\"error\":1, \"message\":\"不允许的扩展名，只允许" + extMap.get(dirName) + "格式。\"}");
						return;
					}

					// 上传文件
					File target = new File(uploadDirectory, dirName + File.separator + UUID.randomUUID().toString() + "."
							+ fileExt);
					item.write(target);
					String path = request.getContextPath() + "/" + uploadDir + "/" + dirName + "/" + target.getName();
					out.println("{\"error\":0, \"url\":\"" + path + "\"}");
				}
			}
		} catch (Exception e) {
			out.println("{\"error\":1, \"message\":\"上传文件失败，" + e.getMessage() + "。\"}");
			e.printStackTrace();
		}
	}

	/**
	 * 创建上传目录
	 */
	private File createUploadDir(String path) {
		ServletContext sc = getServletConfig().getServletContext();
		File uploadFile = new File(sc.getRealPath(path));
		if (!uploadFile.exists()) {
			uploadFile.mkdirs();
		}
		return uploadFile;
	}
}
