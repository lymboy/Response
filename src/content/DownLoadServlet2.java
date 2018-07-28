package content;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 下载中文名文件
 */
public class DownLoadServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DownLoadServlet2() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html; charset=utf-8;");
		
		String fileName = request.getParameter("filename");

//		response.getWriter().write(fileName);
		
		//获得请求头中的User-Agent
		String agent = request.getHeader("User-Agent");
		
//		response.getWriter().write(agent);
		
		//根据不同浏览器进行不同的编码
		String filenameEncoder = fileName;
		if (agent.equals("Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; LCTE; rv:11.0) like Gecko")) {
			// IE浏览器
			filenameEncoder = URLEncoder.encode(fileName, "utf-8");
			filenameEncoder = filenameEncoder.replace("+", " ");
		}
		
		response.setContentType(getServletContext().getMimeType(fileName));
		response.setHeader("Content-Disposition", "attachment;filename="+filenameEncoder);
		
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
