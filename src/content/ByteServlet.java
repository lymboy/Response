package content;

import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 向客户端写入图片
 */
public class ByteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ByteServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//获得资源路径
		String realPath = this.getServletContext().getRealPath("a2.jpg");
		//创建文件输入流
		FileInputStream in = new FileInputStream(realPath);
		//获得响应输出流
		ServletOutputStream out = response.getOutputStream();
		//创建缓存渠
		byte[] buffer = new byte[1024];
		int len = 0;
		
		while ((len=in.read(buffer)) > 0) {
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
