package org.raul.rules;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderError;
import org.drools.builder.KnowledgeBuilderErrors;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.runtime.ObjectFilter;
import org.drools.runtime.StatefulKnowledgeSession;

/**
 * Holds drools specific stuff. Currently reading and compiling rules every
 * time, but should add a maven plugin to compile them.
 * 
 * @author lgomes
 * @param <T>
 * 
 */
public final class Engine<T> {

	private String rulesFileName;
	
	private Class<T> returnType;

	/**
	 * Creates a new engine
	 * 
	 * @param rulesFileName
	 *            should be a valid name for a .drl file containing rules.
	 */
	public Engine(String rulesFileName, Class<T> returnType) {
		this.rulesFileName = rulesFileName;
		this.returnType = returnType;
	}
	
	public List<T> runWith(List<?> facts) {
		return new ArrayList<T>(runWithInternal(facts));
	}

	@SuppressWarnings("unchecked")
	private Collection<T> runWithInternal(List<?> facts) {

		// load up the knowledge base
		KnowledgeBase kbase = readKnowledgeBase(this.rulesFileName);
		StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();

		// go !
		for (Object fact : facts) {
			ksession.insert(fact);
		}

		ksession.fireAllRules();
		return (Collection<T>) ksession.getObjects(new ObjectFilter() {
			
			public boolean accept(Object object) {
				return returnType.isInstance(object);
			}
		});

	}

	/**
	 * Reads the KBases
	 */
	private KnowledgeBase readKnowledgeBase(String fileName) {

		KnowledgeBuilder kbuilder = KnowledgeBuilderFactory
				.newKnowledgeBuilder();
		kbuilder.add(ResourceFactory.newClassPathResource(fileName),
				ResourceType.DRL);
		KnowledgeBuilderErrors errors = kbuilder.getErrors();
		if (errors.size() > 0) {
			for (KnowledgeBuilderError error : errors) {
				System.err.println(error);
			}
			throw new IllegalArgumentException("Could not parse knowledge.");
		}
		KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
		kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());
		return kbase;
	}

}
