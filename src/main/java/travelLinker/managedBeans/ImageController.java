package travelLinker.managedBeans;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


public class ImageController {

	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    if (ServletFileUpload.isMultipartContent(request)) {
	        FileItemFactory factory = new DiskFileItemFactory();
	        ServletFileUpload upload = new ServletFileUpload(factory);
	        try {
	            List<FileItem> items = upload.parseRequest(request);
	            for (FileItem item : items) {
	                if (!item.isFormField()) {
	                    // Traitez l'élément de fichier (l'image)
	                    byte[] imageBytes = item.get();
	                    // Continuez le traitement ici
	                }
	            }
	        } catch (FileUploadException e) {
	            e.printStackTrace();
	        }
	    }
	}

	
}
