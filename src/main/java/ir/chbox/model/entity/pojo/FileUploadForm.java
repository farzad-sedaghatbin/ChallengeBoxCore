package ir.chbox.model.entity.pojo;

import org.jboss.resteasy.annotations.providers.multipart.PartType;

import javax.ws.rs.FormParam;

public class FileUploadForm {
 
    public FileUploadForm() {
    }
 
    private byte[] fileData;
    private String fileName;
 
    public String getFileName() {
        return fileName;
    }
 
    @FormParam("fileName")
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
 
    public byte[] getFileData() {
        return fileData;
    }
 
    @FormParam("selectedFile")
    @PartType("application/octet-stream")
    public void setFileData(byte[] fileData) {
        this.fileData = fileData;
    }
}