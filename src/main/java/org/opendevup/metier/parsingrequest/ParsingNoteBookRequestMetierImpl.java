package org.opendevup.metier.parsingrequest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.opendevup.dto.NoteBookRequestDto;
import org.opendevup.dto.ProgrammeInformationDto;
import org.opendevup.exception.IllegalCodeFormatException;
import org.springframework.stereotype.Service;

@Service
public class ParsingNoteBookRequestMetierImpl implements IParsingNoteBookRequestMetier {
	
	private static final String formatSpecifier = "%(\\w+)\\s+(.*)";

	private static final Pattern formatToken = Pattern.compile(formatSpecifier);

	@Override
	public ProgrammeInformationDto parseNoteBookRequest(
			NoteBookRequestDto request)
			throws IllegalCodeFormatException {
		final Matcher matcher = formatToken.matcher(request.getCode());
        if (matcher.matches()) {
            return new ProgrammeInformationDto(matcher.group(1),matcher.group(2));     
        }else{
        	throw new IllegalCodeFormatException();
        }
        
	}
	
	

}
