package org.opendevup.metier.compilator;

import java.util.List;

import org.opendevup.dto.ProgrammeInformationDto;
import org.opendevup.exception.MethodeTakeMuchTimeToCompileException;

public interface ICompilator {
	
	public String compileCodeJs(List<ProgrammeInformationDto> programmeInformations) throws MethodeTakeMuchTimeToCompileException;
	
	public String compileCodePython(List<ProgrammeInformationDto> programmeInformations) throws MethodeTakeMuchTimeToCompileException;

}
