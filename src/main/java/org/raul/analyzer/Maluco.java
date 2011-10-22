package org.raul.analyzer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.raul.model.Message;
import org.raul.model.PTR;

/**
 * Maluco is an utility that will analyze all the PTRs and IRs assigned to a
 * given team and return a list of {@link Message}s associated to them.
 * 
 * @author lgomes
 * 
 */
public class Maluco {

	private Beleza beleza;

	public Maluco(Beleza beleza) {
		this.beleza = beleza;
	}

	public Maluco() {
	}

	public Map<String, List<Message>> analyzeFor(String team) {

		Map<String, List<Message>> msgs = new HashMap<String, List<Message>>();
		
		if (beleza == null) {
			beleza = Beleza.createUsingRulesFrom(team);
		}

		for (PTR ptr : beleza.ptrs()) {
			List<Message> msgsForPTR = beleza.analyze(ptr);
			if (msgsForPTR.size() > 0) {
				msgs.put(ptr.getTitle(), msgsForPTR);
			}
		}

		return msgs;
	}

}
