package manytag.common.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = -2902173830548702555L;

	private String uploadPath;
	private String tempPath;

	/**
	 * Constructor of the object.
	 */
	public UploadServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		try {
			this.uploadPath = config.getInitParameter("upload_path");
			this.tempPath = config.getInitParameter("temp_path");

			makeDir(this.uploadPath);
			makeDir(this.tempPath);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}

		super.init(config);
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.getWriter().println("请以POST方式上传文件");
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fileName = null;
		String storePath = request.getSession().getServletContext().getRealPath(this.uploadPath);
		try {
			request.setCharacterEncoding("UTF-8");

			DiskFileItemFactory factory = new DiskFileItemFactory();
			// 设置临时文件的存放路径
			factory.setRepository(new File(this.tempPath));
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setProgressListener(new ProgressListener() {
				public void update(long pBytesRead, long pContentLength, int pItems) {
					System.out.println(
							"已读" + pBytesRead + ",总大小:" + pContentLength + ",第几项:" + pItems + "上传进度=" + (pBytesRead / pContentLength * 100) + "%");
					System.out.println(pBytesRead / pContentLength);
				}
			});
			// 设置单个文件的大小
			upload.setFileSizeMax(12 * 1024 * 1024);
			upload.setSizeMax(40 * 1024 * 1024);

			boolean isMultipart = ServletFileUpload.isMultipartContent(request);
			if (isMultipart) {
				List<FileItem> items = upload.parseRequest(request);
				for (FileItem item : items) {
					if (item.isFormField()) {
						fileName = item.getFieldName();
					} else {
						String mimeType = item.getContentType();
						if (mimeType != null) {
							InputStream in = item.getInputStream();// 文件内容的输入流
							fileName = item.getName();// 上传文件的文件名
							if (fileName == null || "".equals(fileName)) {
								continue;
							}
							fileName = fileName.replace('\\', '/');
							fileName = fileName.substring(fileName.lastIndexOf("/") + 1);// 原来的文件名
							System.out.println(request.getRemoteAddr() + "上传了" + fileName);

							File file = new File(storePath, fileName);

							OutputStream out = new FileOutputStream(file);

							int len = -1;
							byte[] b = new byte[1024];
							while ((len = in.read(b)) != -1) {
								out.write(b, 0, len);
							}
							in.close();
							out.close();
							item.delete();// 删除临时文件
						}
					}
				}
			}
			PrintWriter out = null;
			try {
				response.setContentType("text/html;charset=UTF-8");
				out = response.getWriter();
				storePath = storePath.replace("\\", "\\\\");
				out.write("{\"fileName\":" + "\"" + fileName + "\"" + ",\"storePath\":\"" + storePath + fileName + "\"" + "}");
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (out != null) {
					out.flush();
					out.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void makeDir(String path) {
		File file = new File(path);
		if (!file.exists()) {
			file.mkdirs();
		}
	}
}