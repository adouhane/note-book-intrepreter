package org.opendevup.metier;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.servlet.http.HttpSession;

import org.opendevup.dto.NoteBookRequestDto;
import org.opendevup.dto.NoteBookResponseDto;
import org.opendevup.dto.ProgrammeInformationDto;
import org.opendevup.exception.LangageNotSupportException;
import org.opendevup.exception.NoteBookInterpreterException;
import org.opendevup.metier.compilator.ICompilator;
import org.opendevup.metier.parsingrequest.IParsingNoteBookRequestMetier;
import org.opendevup.metier.session.IManageSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class NoteBookInterpreterMetierImpl implements INoteBookInterpreterMetier{

	@Autowired
	IParsingNoteBookRequestMetier parsingNoteBookRequestMetier;
	
	@Autowired
	IManageSession manageSession;
	
	@Autowired
	ICompilator compilator;
	
	@Override
	public NoteBookResponseDto execute(NoteBookRequestDto  notebookRequest)
			throws NoteBookInterpreterException {
		String result="";

		ProgrammeInformationDto programmeInformation = parsingNoteBookRequestMetier.parseNoteBookRequest(notebookRequest);
		ArrayList<ProgrammeInformationDto> programmeInformations = manageSession.getListProgrammeInformationDtoForSession(programmeInformation);

		 if(programmeInformation.getLang().equals("js")) {
			 result=compilator.compileCodeJs(programmeInformations);		 
		 }else if(programmeInformation.getLang().equals("python")) {
			 result=compilator.compileCodePython(programmeInformations);	
		 }else{
		 
			 throw new LangageNotSupportException("You must use js or python");
		 }		
		return new NoteBookResponseDto(result);
		
	}


}
