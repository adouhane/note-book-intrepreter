package org.opendevup.metier.compilator;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.python.core.Options;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.opendevup.dto.ProgrammeInformationDto;
import org.opendevup.exception.MethodeTakeMuchTimeToCompileException;
import org.springframework.stereotype.Service;

@Service
public class CompilatorImpl implements ICompilator {

	public String compileCodeJs(List<ProgrammeInformationDto> programmeInformations)
			throws MethodeTakeMuchTimeToCompileException {
		ScriptEngineManager manager = new ScriptEngineManager();
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		final Runnable runnable = new Thread() {
			@Override
			public void run() {
				try {
					ScriptEngine engine = manager.getEngineByName("javascript");
					engine.getContext().setWriter(pw);
					for (ProgrammeInformationDto programmeInformationDto : programmeInformations) {
						if (programmeInformationDto.getLang().equals("js")) {
							engine.eval(programmeInformationDto.getCode());

						}
					}
				} catch (ScriptException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		final ExecutorService executor = Executors.newSingleThreadExecutor();
		final Future future = executor.submit(runnable);
		executor.shutdown();
		try {
			future.get(30, TimeUnit.SECONDS);
		} catch (InterruptedException ie) {
			ie.printStackTrace();

		} catch (ExecutionException ee) {
			ee.printStackTrace();

		} catch (TimeoutException te) {
			throw new MethodeTakeMuchTimeToCompileException();
		}
		if (!executor.isTerminated())
			executor.shutdownNow();
		return sw.toString().replaceAll("(\r|\n)", " ").trim();
	}

	public String compileCodePython(List<ProgrammeInformationDto> programmeInformations) throws MethodeTakeMuchTimeToCompileException {
		ScriptEngineManager manager = new ScriptEngineManager();
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		final Runnable runnable = new Thread() {
			@Override
			public void run() {
				try {
					ScriptEngine engine = manager.getEngineByName("python");
					engine.getContext().setWriter(pw);

					for (ProgrammeInformationDto programmeInformationDto : programmeInformations) {
						if (programmeInformationDto.getLang().equals("python")) {
							engine.eval(programmeInformationDto.getCode());

						}
					}
				} catch (ScriptException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		};
		final ExecutorService executor = Executors.newSingleThreadExecutor();
		final Future future = executor.submit(runnable);
		executor.shutdown();
		try {
			future.get(30, TimeUnit.SECONDS);
		} catch (InterruptedException ie) {
			ie.printStackTrace();

		} catch (ExecutionException ee) {
			ee.printStackTrace();

		} catch (TimeoutException te) {
			throw new MethodeTakeMuchTimeToCompileException();
		}
		if (!executor.isTerminated())
			executor.shutdownNow();
		return sw.toString().replaceAll("(\r|\n)", " ").trim();
	}

}
