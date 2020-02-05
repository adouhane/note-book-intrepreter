package org.opendevup.metier.session;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpSession;

import org.opendevup.dto.ProgrammeInformationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManageSessionImpl implements IManageSession {
	@Autowired
	HttpSession httpSession;
	
	private Map<String, ArrayList<ProgrammeInformationDto>> sessionContexts = new ConcurrentHashMap<>();
	
	
	public ArrayList<ProgrammeInformationDto> getListProgrammeInformationDtoForSession(ProgrammeInformationDto programmeInformation){
		 String sessionId = programmeInformation.getSessionId() != null ? programmeInformation.getSessionId() : httpSession.getId();
		 programmeInformation.setSessionId(sessionId);
		 ArrayList<ProgrammeInformationDto> programmeInformations = getOrCreateListProgrammeInformationDtoForSessionContext(programmeInformation.getSessionId());
		 programmeInformations.add(programmeInformation);
		 return programmeInformations;
	}
	
	
	private ArrayList<ProgrammeInformationDto> getOrCreateListProgrammeInformationDtoForSessionContext(String sessionId) {
        return sessionContexts.computeIfAbsent(sessionId, key -> new ArrayList<ProgrammeInformationDto>());
    }

}
