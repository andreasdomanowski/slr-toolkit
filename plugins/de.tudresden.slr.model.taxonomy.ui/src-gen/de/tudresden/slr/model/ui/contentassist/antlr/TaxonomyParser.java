/*
 * generated by Xtext
 */
package de.tudresden.slr.model.ui.contentassist.antlr;

import java.util.Collection;
import java.util.Map;
import java.util.HashMap;

import org.antlr.runtime.RecognitionException;
import org.eclipse.xtext.AbstractElement;
import org.eclipse.xtext.ui.editor.contentassist.antlr.AbstractContentAssistParser;
import org.eclipse.xtext.ui.editor.contentassist.antlr.FollowElement;
import org.eclipse.xtext.ui.editor.contentassist.antlr.internal.AbstractInternalContentAssistParser;

import com.google.inject.Inject;

import de.tudresden.slr.model.services.TaxonomyGrammarAccess;

public class TaxonomyParser extends AbstractContentAssistParser {
	
	@Inject
	private TaxonomyGrammarAccess grammarAccess;
	
	private Map<AbstractElement, String> nameMappings;
	
	@Override
	protected de.tudresden.slr.model.ui.contentassist.antlr.internal.InternalTaxonomyParser createParser() {
		de.tudresden.slr.model.ui.contentassist.antlr.internal.InternalTaxonomyParser result = new de.tudresden.slr.model.ui.contentassist.antlr.internal.InternalTaxonomyParser(null);
		result.setGrammarAccess(grammarAccess);
		return result;
	}
	
	@Override
	protected String getRuleName(AbstractElement element) {
		if (nameMappings == null) {
			nameMappings = new HashMap<AbstractElement, String>() {
				private static final long serialVersionUID = 1L;
				{
					put(grammarAccess.getTermAccess().getGroup(), "rule__Term__Group__0");
					put(grammarAccess.getTermAccess().getGroup_1(), "rule__Term__Group_1__0");
					put(grammarAccess.getTermAccess().getGroup_1_1(), "rule__Term__Group_1_1__0");
					put(grammarAccess.getModelAccess().getDimensionsAssignment(), "rule__Model__DimensionsAssignment");
					put(grammarAccess.getTermAccess().getNameAssignment_0(), "rule__Term__NameAssignment_0");
					put(grammarAccess.getTermAccess().getSubclassesAssignment_1_1_0(), "rule__Term__SubclassesAssignment_1_1_0");
				}
			};
		}
		return nameMappings.get(element);
	}
	
	@Override
	protected Collection<FollowElement> getFollowElements(AbstractInternalContentAssistParser parser) {
		try {
			de.tudresden.slr.model.ui.contentassist.antlr.internal.InternalTaxonomyParser typedParser = (de.tudresden.slr.model.ui.contentassist.antlr.internal.InternalTaxonomyParser) parser;
			typedParser.entryRuleModel();
			return typedParser.getFollowElements();
		} catch(RecognitionException ex) {
			throw new RuntimeException(ex);
		}		
	}
	
	@Override
	protected String[] getInitialHiddenTokens() {
		return new String[] { "RULE_ML_COMMENT", "RULE_WS", "RULE_NEWLINE" };
	}
	
	public TaxonomyGrammarAccess getGrammarAccess() {
		return this.grammarAccess;
	}
	
	public void setGrammarAccess(TaxonomyGrammarAccess grammarAccess) {
		this.grammarAccess = grammarAccess;
	}
}
