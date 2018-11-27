package utils;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class CustomHttpRequest {
	private HttpServletRequest request;
	private boolean isMultiPart;
	private HashMap<String,FileItem> files;
	private HashMap<String,String> params;
	private List<String> parameterNames;
	private HashMap<String, List<String>> values;
	
	public HttpServletRequest getRequest() {
		return request;
	}

	public CustomHttpRequest(HttpServletRequest request){
		this.request = request;
		isMultiPart = ServletFileUpload.isMultipartContent(request);
		parameterNames = new ArrayList<>();
		values = new HashMap<>();
		if(isMultiPart){
			files = new HashMap<>();
			params = new HashMap<>();
			try {
				List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
				for(FileItem item : multiparts){
					if(item.isFormField()){
						if(!params.containsKey(item.getFieldName()))
							params.put(item.getFieldName(), new String(item.getString().getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
						else{
							if(!values.containsKey(item.getFieldName())){
								values.put(item.getFieldName(), new ArrayList<String>());
								values.get(item.getFieldName()).add(params.get(item.getFieldName()));
							}
							values.get(item.getFieldName()).add(new String(item.getString().getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
							String value = params.get(item.getFieldName())+";"+new String(item.getString().getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
							params.put(item.getFieldName(), value);
						}
					}
					else
						files.put(item.getFieldName(), item);
					parameterNames.add(item.getFieldName());
				}
			} catch (FileUploadException e) {
				e.printStackTrace();
			}
		} else{
			Enumeration<String> enume = request.getParameterNames();
			while(enume.hasMoreElements())
				parameterNames.add(enume.nextElement());
		}
	}
	
	public String getParameter(String paramName){
		if(!isMultiPart)
			return request.getParameter(paramName);
		else{
			return params.get(paramName);
		}
	}
	
	public String[] getValues(String paramName){
		if(!isMultiPart)
			return request.getParameterValues(paramName);
		else{
			List<String> list = values.get(paramName);
			if(list == null)
				return new String[0];
			String[] str = new String[list.size()];
			list.toArray(str);
			return str;
		}
	}
	
	public FileItem getFile(String paramName){
		if(isMultiPart){
			if(files.get(paramName)==null || files.get(paramName).getSize()==0)
				return null;
			return files.get(paramName);
		}
		return null;
	}
	
	public List<String> getParameterNames(){
		return this.parameterNames;
	}
}