/*
 *	Copyright Technophobia Ltd 2012
 *
 *   This file is part of Substeps.
 *
 *    Substeps is free software: you can redistribute it and/or modify
 *    it under the terms of the GNU Lesser General Public License as published by
 *    the Free Software Foundation, either version 3 of the License, or
 *    (at your option) any later version.
 *
 *    Substeps is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU Lesser General Public License for more details.
 *
 *    You should have received a copy of the GNU Lesser General Public License
 *    along with Substeps.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.technophobia.substeps.document.formatting.strategy;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.text.formatter.IFormattingStrategy;

import com.technophobia.substeps.FeatureEditorPlugin;
import com.technophobia.substeps.document.formatting.FormattingContext;
import com.technophobia.substeps.supplier.Supplier;

/**
 * Implementation of {@link IFormattingStrategy} that defers formatting to a
 * different {@link IFormattingStrategy}, wrapping it with relevant newlines
 * 
 * @author sforbes
 * 
 */
public class StartOfUnitFormattingStrategy extends DefaultFormattingStrategy {

    private final IFormattingStrategy formattingStrategy;
    private final Supplier<FormattingContext> formattingContextSupplier;
    private final int numLeadingLines;
    private final int numTrailingLines;


    public StartOfUnitFormattingStrategy(final int numLeadingLines, final int numTrailingLines,
            final Supplier<FormattingContext> formattingContextSupplier, final IFormattingStrategy formattingStrategy) {
        this.numLeadingLines = numLeadingLines;
        this.numTrailingLines = numTrailingLines;
        this.formattingContextSupplier = formattingContextSupplier;
        this.formattingStrategy = formattingStrategy;

    }


    @Override
    public String format(final String content, final boolean isLineStart, final String indentation,
            final int[] positions) {
        FeatureEditorPlugin.instance().log(IStatus.INFO,
                "Formatting line: " + content + ", isLineStart: " + isLineStart + ", indentation: " + indentation);
        /*
         * if (isLineStart) { final FormattingContext formattingContext =
         * formattingContextSupplier.get(); if
         * (formattingContext.hasPreviousContent()) { final FormattingContext
         * previousContext = formattingContext.previousContentContext(); if
         * (!previousContext.currentContentType().isOptional()) { prefixNewLine
         * = true; } } }
         */
        final String formattedContent = formattingStrategy.format(content.trim(), isLineStart, indentation, positions);

        final StringBuffer sb = new StringBuffer();
        addNewlinesTo(numLeadingLines, hasPreviousContent(), sb);
        sb.append(formattedContent);
        addNewlinesTo(numTrailingLines, hasNextContent() && !isOptional(), sb);
        return sb.toString();
    }


    private boolean hasPreviousContent() {
        return formattingContextSupplier.get().hasPreviousContent()
                && !formattingContextSupplier.get().inspectPreviousContentType().isOptional();
    }


    private boolean hasNextContent() {
        return formattingContextSupplier.get().hasMoreContent();
    }


    private boolean isOptional() {
        return formattingContextSupplier.get().currentContentType().isOptional();
    }


    private void addNewlinesTo(final int numLines, final boolean hasOtherContent, final StringBuffer sb) {
        if (hasOtherContent) {
            for (int i = 0; i < numLines; i++) {
                sb.append(NEWLINE);
            }
        }
    }
}
