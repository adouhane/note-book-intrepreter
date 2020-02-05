package org.opendevup.service;


import org.opendevup.dto.NoteBookRequestDto;
import org.opendevup.dto.NoteBookResponseDto;
import org.opendevup.exception.NoteBookInterpreterException;
import org.opendevup.metier.INoteBookInterpreterMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NoteBookInterpreterRest {
	
	@Autowired
	private INoteBookInterpreterMetier noteBookInterpreterMetier;
	
	@RequestMapping(value="/execute" , method = RequestMethod.POST)
	public NoteBookResponseDto excute(NoteBookRequestDto notebookRequest ) throws NoteBookInterpreterException{
		return  noteBookInterpreterMetier.execute(notebookRequest);
	}

}
