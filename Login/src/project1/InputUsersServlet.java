package project1;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
 
 
public class InputUsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String TMP_DIR_PATH = "C:\\Temp";
	private File tmpDir;
	private static final String DESTINATION_DIR_PATH ="\\files";
	private File destinationDir;
	private String realPath;
	private String ret;
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		tmpDir = new File(TMP_DIR_PATH);
		if(!tmpDir.isDirectory()) {
			throw new ServletException(TMP_DIR_PATH + " is not a directory");
		}
		realPath = getServletContext().getRealPath(DESTINATION_DIR_PATH);
		System.out.println(realPath);
		destinationDir = new File(realPath);
		if(!destinationDir.isDirectory()) {
			throw new ServletException(DESTINATION_DIR_PATH+" is not a directory --"+realPath);
		}
 
	}
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    response.setContentType("text/plain");
		DiskFileItemFactory  fileItemFactory = new DiskFileItemFactory ();
		/*
		 *Set the size threshold, above which content will be stored on disk.
		 */
		fileItemFactory.setSizeThreshold(1*1024*1024); //1 MB
		/*
		 * Set the temporary directory to store the uploaded files of size above threshold.
		 */
		fileItemFactory.setRepository(tmpDir);
 
		ServletFileUpload uploadHandler = new ServletFileUpload(fileItemFactory);
		try {
			/*
			 * Parse the request
			 */
			List items = uploadHandler.parseRequest(request);
			Iterator itr = items.iterator();
			while(itr.hasNext()) {
				FileItem item = (FileItem) itr.next();
				File file = new File(destinationDir,item.getName());
				item.write(file);
				ReadExcelFile xls = new ReadExcelFile();
				xls.ReadCSV(realPath+"/"+item.getName());
				xls.toString();
				ret = xls.addToDB();
				File file1 = new File(realPath+"/"+item.getName());
	    		file1.delete();
			}
		}catch(FileUploadException ex) {
			log("Error encountered while parsing the request",ex);
		} catch(Exception ex) {
			log("Error encountered while uploading file",ex);
		}
		try {
			ServletContext sc = this.getServletContext();
			RequestDispatcher rd = sc.getRequestDispatcher("/InputUsers.jsp?ret="+ret);
			rd.forward(request, response);
			return;
		}catch(Exception e){
			e.printStackTrace();
		}
 
	}
}
 