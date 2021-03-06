package com.technophobia.substeps.document.content.assist;

import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.jface.text.contentassist.IContextInformationValidator;

/**
 * Implementation of {@link IContentAssistProcessor} that uses a
 * {@link CompletionProposalProvider} to get the details
 * 
 * @author sforbes
 * 
 */
public class CompletionProvidedContentProcessor implements IContentAssistProcessor {

    private final CompletionProposalProvider completionProposalProvider;


    public CompletionProvidedContentProcessor(final CompletionProposalProvider completionProposalProvider) {
        this.completionProposalProvider = completionProposalProvider;
    }


    @Override
    public ICompletionProposal[] computeCompletionProposals(final ITextViewer viewer, final int offset) {
        return completionProposalProvider.get(viewer.getDocument(), offset);
    }


    @Override
    public IContextInformation[] computeContextInformation(final ITextViewer viewer, final int offset) {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public char[] getCompletionProposalAutoActivationCharacters() {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public char[] getContextInformationAutoActivationCharacters() {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public String getErrorMessage() {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public IContextInformationValidator getContextInformationValidator() {
        // TODO Auto-generated method stub
        return null;
    }

}
