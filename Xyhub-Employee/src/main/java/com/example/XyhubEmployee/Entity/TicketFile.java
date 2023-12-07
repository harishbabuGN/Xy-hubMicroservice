package com.example.XyhubEmployee.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import com.example.XyhubCommon.IdGenerator.IdGenerator;
import com.example.XyhubCommon.IdGenerator.IdPrefix;
import com.example.XyhubEmployee.basedata.model.AuditModel;


@Entity
@Table(name = "ticket_tool_files")
public class TicketFile extends AuditModel{

	@Id
	@IdPrefix(value = "TKF_")
	@GeneratedValue(generator = IdGenerator.ID_GENERATOR)
	@GenericGenerator(name = IdGenerator.ID_GENERATOR, strategy = "com.example.XyhubCommon.IdGenerator.IdGenerator")
	@Column(name="file_id")
	private String id;
	
	@Column(name = "mime_type")
	private String mimeType;

	@Column(name = "file_name")
	private String fileName;

	@Column(name = "file_Type")
	private String fileType;
	
	@Column(name = "file_extension")
	private String fileExtension;
	
	@Lob
	@Type(type = "org.hibernate.type.ImageType")
	private byte[] image;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}


	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getFileExtension() {
		return fileExtension;
	}

	public void setFileExtension(String fileExtension) {
		this.fileExtension = fileExtension;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}
	
	
}
