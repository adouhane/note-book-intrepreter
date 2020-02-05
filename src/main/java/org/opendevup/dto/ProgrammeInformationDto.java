package org.opendevup.dto;

public class ProgrammeInformationDto {
	
	 private String lang;
	 private String code;
	 private String sessionId;
	 
	 public ProgrammeInformationDto(){
		 
	 }
	 public ProgrammeInformationDto(String lang,String code){
		 this.lang = lang;
		 this.code = code;
	 }
	
	public String getLang() {
		return lang;
	}
	public void setLang(String lang) {
		this.lang = lang;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	 
}
