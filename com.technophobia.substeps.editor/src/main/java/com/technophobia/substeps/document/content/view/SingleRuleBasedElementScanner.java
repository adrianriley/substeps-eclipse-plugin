package com.technophobia.substeps.document.content.view;

import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.RuleBasedScanner;

public class SingleRuleBasedElementScanner extends RuleBasedScanner {

	public SingleRuleBasedElementScanner(final IRule rule) {
		final IRule[] rules = new IRule[1];

		rules[0] = rule;

		setRules(rules);
	}

}