package content;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DownloadServlet
 */
public class DownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DownloadServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//设置文件下载的头
		String fileName = request.getParameter("filename");
		response.setContentType(getServletContext().getMimeType(fileName));
		response.setHeader("Content-Disposition", "attachment;filename="+fileName);
		
		//获得文件的绝对路径
		String path = this.getServletContext().getRealPath("/download/a.jpg");
		
		//向客户端写文件
		InputStream in = new FileInputStream(path);
		OutputStream out = response.getOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		
		while ((len=in.read()) > 0) {
			out.write(buffer, 0, len);
		}
		
		in.close();
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
