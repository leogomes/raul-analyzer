package org.raul.analyzer;

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

	private Beleza(String team) {
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
	 */
	public Collection<Message> analyzeAll() {
		
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
	
	private static List<PTR> ptrs() {
		return null;
	}
}
