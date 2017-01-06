package cn.itcast.action.uploadFile;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

public class UploadFiles {
	private File[] files;
	private String[] filesContentType;
	private String[] filesFileName;
	private String uploadResult;  //上传结果字符串
	private boolean SuccessUpload; //是否上传成功

	public String getUploadResult() {
		return uploadResult;
	}

	public void setUploadResult(String uploadResult) {
		this.uploadResult = uploadResult;
	}

	public String[] getFilesContentType() {
		return filesContentType;
	}

	public void setFilesContentType(String[] filesContentType) {
		this.filesContentType = filesContentType;
	}

	public String[] getFilesFileName() {
		return filesFileName;
	}

	public void setFilesFileName(String[] filesFileName) {
		this.filesFileName = filesFileName;
	}

	public File[] getFiles() {
		return files;
	}

	public void setFiles(File[] files) {
		System.out.println("setFiles:files:"+files);
		this.files = files;
	}
	
	public String uploadFiles() throws IOException{
		if(files == null) return "upload";
		String realpath = ServletActionContext.getServletContext().getRealPath("/upload");
		System.out.println("realpath"+realpath);
		File file = new File(realpath);
		if(!file.exists())
			file.mkdirs();
		for(int i = 0; i < files.length; ++i){
			FileUtils.copyFile(files[i], new File(file, filesFileName[i]));
		}
		uploadResult = "上传成功";
		SuccessUpload = true;
		//ActionContext.getContext().put("isSuccessUpload", SuccessUpload);
		return "upload";
	}

	public boolean isSuccessUpload() {
		return SuccessUpload;
	}
	
	public void setSuccessUpload(boolean isSuccessUpload) {
		this.SuccessUpload = isSuccessUpload;
	}
}
