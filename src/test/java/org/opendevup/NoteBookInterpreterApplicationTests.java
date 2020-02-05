package org.opendevup;

import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.opendevup.dto.NoteBookRequestDto;
import org.opendevup.dto.NoteBookResponseDto;
import org.opendevup.exception.IllegalCodeFormatException;
import org.opendevup.exception.LangageNotSupportException;
import org.opendevup.exception.MethodeTakeMuchTimeToCompileException;
import org.opendevup.exception.NoteBookInterpreterException;
import org.opendevup.metier.INoteBookInterpreterMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NoteBookInterpreterApplicationTests {

	@Autowired
	private INoteBookInterpreterMetier noteBookInterpreterMetier;

	@Test(expected = LangageNotSupportException.class)
	public void unSupportedLangageException() throws NoteBookInterpreterException {
		NoteBookRequestDto requestDto = new NoteBookRequestDto();
		requestDto.setCode("%php echo \"Hello World!\";");
		NoteBookResponseDto responseDto = noteBookInterpreterMetier.execute(requestDto);

	}

	@Test(expected = IllegalCodeFormatException.class)
	public void incorrectFormatOfCodeException() throws NoteBookInterpreterException {
		NoteBookRequestDto requestDto = new NoteBookRequestDto();
		requestDto.setCode("js% print(1+1);");
		NoteBookResponseDto responseDto = noteBookInterpreterMetier.execute(requestDto);
	}

	@Test(expected = MethodeTakeMuchTimeToCompileException.class)
	public void takeMuchTimeToCompileException() throws NoteBookInterpreterException {
		NoteBookRequestDto requestDto = new NoteBookRequestDto();
		requestDto.setCode("%js while(true){};");
		requestDto.setSessionId("sessionexception");
		NoteBookResponseDto responseDto = noteBookInterpreterMetier.execute(requestDto);
	}

	@Test
	public void printHelloWordWithJSInterpreter() throws NoteBookInterpreterException {
		NoteBookRequestDto requestDto = new NoteBookRequestDto();
		requestDto.setCode("%js print(\"Hello World\");");
		NoteBookResponseDto responseDto = noteBookInterpreterMetier.execute(requestDto);
		assertThat(responseDto.getResult(), containsString("Hello World"));
	}

	@Test
	public void printHelloWordWithPythonInterpreter() throws NoteBookInterpreterException {
		NoteBookRequestDto requestDto = new NoteBookRequestDto();
		requestDto.setCode("%python print \"Hello World\";");
		NoteBookResponseDto responseDto = noteBookInterpreterMetier.execute(requestDto);
		assertThat(responseDto.getResult(), containsString("Hello World"));
	}

	@Test
	public void preserveStateForPythonInterpreter() throws NoteBookInterpreterException {
		NoteBookRequestDto requestDto = new NoteBookRequestDto();
		requestDto.setCode("%python a = 1");
		requestDto.setSessionId("sessionPython");
		NoteBookResponseDto responseDto = noteBookInterpreterMetier.execute(requestDto);
		NoteBookRequestDto requestDto2 = new NoteBookRequestDto();
		requestDto2.setCode("%python print a+1");
		requestDto2.setSessionId("sessionPython");
		NoteBookResponseDto responseDto2 = noteBookInterpreterMetier.execute(requestDto2);
		assertThat(responseDto2.getResult(), containsString("2"));
	}

	@Test
	public void preserveStateForJSInterpreter() throws NoteBookInterpreterException {
		NoteBookRequestDto requestDto = new NoteBookRequestDto();
		requestDto.setCode("%js var a = 1;");
		requestDto.setSessionId("sessionJS");
		NoteBookResponseDto responseDto = noteBookInterpreterMetier.execute(requestDto);
		NoteBookRequestDto requestDto2 = new NoteBookRequestDto();
		requestDto2.setCode("%js print(a+1);");
		requestDto2.setSessionId("sessionJS");
		NoteBookResponseDto responseDto2 = noteBookInterpreterMetier.execute(requestDto2);
		assertThat(responseDto2.getResult(), containsString("2"));
	}

}
