package org.opendevup.metier;

import org.opendevup.dto.NoteBookRequestDto;
import org.opendevup.dto.NoteBookResponseDto;
import org.opendevup.dto.ProgrammeInformationDto;
import org.opendevup.exception.NoteBookInterpreterException;



public interface INoteBookInterpreterMetier {
	
	NoteBookResponseDto execute(NoteBookRequestDto  notebookRequest) throws NoteBookInterpreterException;

}
