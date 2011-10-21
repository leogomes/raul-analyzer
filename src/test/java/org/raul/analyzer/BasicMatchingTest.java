package org.raul.analyzer;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Test;
import org.mockito.Mockito;
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

	private void log(List<Message> msgs) {
		for (Message m : msgs) {
			System.out.println(m);
		}
	}

}
