package org.raul.analyzer;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Test;
import org.raul.model.Message;
import org.raul.model.PTR;

public class BasicMatchingTest {

	@Test
	public void shoudMatchDWM() {

		// Create PTR
		PTR dwmPtr = mock(PTR.class);
		when(dwmPtr.getTitle()).thenReturn("Blabla DWM bla");
		when(dwmPtr.hasAttachement()).thenReturn(true);

		Beleza analyzer = Beleza.createUsingRulesFrom("FRW");
		List<Message> msgs = analyzer.analyze(dwmPtr);

		assertNotNull(msgs);
		assertTrue(msgs.size() > 0);

		log(msgs);

	}

	@Test
	public void shouldMatchPTRsWithoutAttachments() {
		// Create PTR
		final PTR ptr = mock(PTR.class);
		when(ptr.getTitle()).thenReturn("MyPTR");

		Maluco maluco = new Maluco(new Beleza("FRW") {

			@Override
			public List<PTR> ptrs() {
				return Collections.singletonList(ptr);
			}

		});

		// FIXME: Team twice!
		Map<String, List<Message>> msgs = maluco.analyzeFor("FRW");
		for (Entry<String, List<Message>> entry : msgs.entrySet()) {
			log("PTR [" + entry.getKey() + "]");
			log(entry.getValue());
		}

	}

	// Logging methods
	private void log(List<Message> msgs) {
		for (Message m : msgs) {
			log(m.toString());
		}
	}

	private void log(String m) {
		System.out.println(m);
	}

}
