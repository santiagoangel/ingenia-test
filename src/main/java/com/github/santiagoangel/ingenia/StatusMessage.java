package com.github.santiagoangel.ingenia;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Helps returning status messages in JSON Format
 * @author santiago
 *
 */
@XmlRootElement
public class StatusMessage implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String status="OK";
	private String message="OK";
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
}
