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
	/** �ϴ�Ŀ¼ */
	private String uploadDir = "upload";
	/** ��ʱĿ¼ */
	private String tempDir = "temp";
	/** �����ļ��ϴ����ƣ�Ĭ��Ϊ5M */
	private long sizeLimit = 5242880;
	/** ���ļ��ϴ����ƣ�Ĭ��Ϊ20M */
	private long totalLimit = 20971520;
	/** �����ϴ�ͼƬ���� */
	private String imageFile = "gif,jpg,jpeg,png,bmp";
	/** �����ϴ�Flash���� */
	private String flashFile = "swf,flv";
	/** �����ϴ���ý���ļ����� */
	private String mediaFile = "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb";
	/** �����ϴ��ļ����� */
	private String plainFile = "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2";

	private File uploadDirectory, tempDirectory;
	private HashMap<String, String> extMap = new HashMap<String, String>();

	@Override
	public void init() throws ServletException {
		// ���������ϴ����ļ���չ��
		extMap.put("image", imageFile);
		extMap.put("flash", flashFile);
		extMap.put("media", mediaFile);
		extMap.put("file", plainFile);

		// �����ϴ�Ŀ¼����ʱĿ¼
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

		// �ϴ����ͣ��ֱ�Ϊimage��Ĭ�ϣ���flash��media��file
		String dirName = request.getParameter("dir");
		if (dirName == null) {
			dirName = "image";
		}
		if (!extMap.containsKey(dirName)) {
			out.println("{\"error\" : 1, \"message\" : \"�ϴ����Ͳ���ȷ\"}");
			return;
		}

		// 1.�����ϴ�����
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(1 * 1024 * 1024); // �趨��ֵ������1M�Ľ��ᱻд�뵽��ʱ�ļ��С�
		factory.setRepository(tempDirectory); // ������ʱĿ¼

		// 2. �����ϴ��ļ�������
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setHeaderEncoding("UTF-8"); // ָ����ȡʱʹ�õ��ַ���
		upload.setSizeMax(totalLimit); // �趨�����ϴ�����

		try {
			List<FileItem> items = upload.parseRequest(request); // �������󣬻�ȡ���б���
			for (FileItem item : items) {
				if (!item.isFormField()) {
					long fileSize = item.getSize();
					String fileName = item.getName();

					// ����ļ���С
					if (fileSize > sizeLimit) {
						out.println("{\"error\":1, \"message\":\"�ϴ��ļ���С��������" + sizeLimit + "��\"}");
						return;
					}

					// �����չ��
					String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
					if (!Arrays.<String> asList(extMap.get(dirName).split(",")).contains(fileExt)) {
						out.println("{\"error\":1, \"message\":\"���������չ����ֻ����" + extMap.get(dirName) + "��ʽ��\"}");
						return;
					}

					// �ϴ��ļ�
					File target = new File(uploadDirectory, dirName + File.separator + UUID.randomUUID().toString() + "."
							+ fileExt);
					item.write(target);
					String path = request.getContextPath() + "/" + uploadDir + "/" + dirName + "/" + target.getName();
					out.println("{\"error\":0, \"url\":\"" + path + "\"}");
				}
			}
		} catch (Exception e) {
			out.println("{\"error\":1, \"message\":\"�ϴ��ļ�ʧ�ܣ�" + e.getMessage() + "��\"}");
			e.printStackTrace();
		}
	}

	/**
	 * �����ϴ�Ŀ¼
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
