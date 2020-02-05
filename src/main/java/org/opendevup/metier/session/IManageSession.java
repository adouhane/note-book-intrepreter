package org.opendevup.metier.session;

import java.util.ArrayList;

import org.opendevup.dto.ProgrammeInformationDto;

public interface IManageSession {
	
	ArrayList<ProgrammeInformationDto> getListProgrammeInformationDtoForSession(ProgrammeInformationDto programmeInformation);

}
