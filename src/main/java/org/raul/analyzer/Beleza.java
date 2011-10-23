package org.raul.analyzer;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.raul.model.Message;
import org.raul.model.PTR;
import org.raul.rules.Engine;

/**
 * Vou ficar, ficar com certeza 
 * Maluco Beleza!
 * 
 * @author lgomes
 */
public class Beleza {
	
	private String team;

	Beleza(String team) {
		this.team = team;
	}
	
	public static Beleza createUsingRulesFrom(String team) {
		return new Beleza(team);
	}
	
	/*
	 * - Reads all PTRs for a given team
	 * - Execute those team's rules against the PTRs
	 * - Extract and analyze results
	 * 
	 * TODO: For the time being it doesn't make sense to expose this method, because there's 
	 * no link between Message and PTR, so if I get all messages it will be difficult to correlate them.
	 * I don't want to add support for that right now, because I want to focus on having simple .drls firt.
	 */
	Collection<Message> analyzeAll() {
		
		Engine<Message> drools = new Engine<Message>(getRuleFileName(), Message.class ); 
		return drools.runWith(ptrs());
	}
	
	public List<Message> analyze(PTR ptr) {
		
		Engine<Message> drools = new Engine<Message>(getRuleFileName(), Message.class ); 
		return drools.runWith(Collections.singletonList(ptr));
	}


	private String getRuleFileName() {
		return this.team + ".drl";
	}
	
	public List<PTR> ptrs() {
		
		PTR ptr1 = mock(PTR.class);
		when(ptr1.getTitle()).thenReturn("PTR 1234567");
		
		PTR ptr2 = mock(PTR.class);
		when(ptr2.getTitle()).thenReturn("PTR 0567384");

		PTR ptr3 = mock(PTR.class);
		when(ptr3.getTitle()).thenReturn("PTR 0123456");

		PTR ptr4 = mock(PTR.class);
		when(ptr4.getTitle()).thenReturn("PTR 0767532");

		
		PTR dwmPtr = mock(PTR.class);
		when(dwmPtr.getTitle()).thenReturn("PTR 2345 - Blabla DWM bla");
		when(dwmPtr.hasAttachement()).thenReturn(true);
		
		List ptrs = new ArrayList();
		ptrs.add(ptr1);
		ptrs.add(ptr2);
		ptrs.add(ptr3);
		ptrs.add(ptr4);
		ptrs.add(dwmPtr);
		
		return ptrs;
	}
}
