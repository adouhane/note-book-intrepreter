package org.opendevup.metier.parsingrequest;

import org.opendevup.dto.NoteBookRequestDto;
import org.opendevup.dto.ProgrammeInformationDto;
import org.opendevup.exception.IllegalCodeFormatException;

public interface IParsingNoteBookRequestMetier {
	
	ProgrammeInformationDto parseNoteBookRequest(NoteBookRequestDto request) throws IllegalCodeFormatException;

}
