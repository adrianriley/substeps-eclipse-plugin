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

import org.eclipse.jface.text.formatter.IFormattingStrategy;

import com.technophobia.substeps.document.formatting.FormattingContext;
import com.technophobia.substeps.supplier.Supplier;

/**
 * Implementation of {@link IFormattingStrategy} used to format optional content
 * blocks (e.g. comments), whose indent strategy is dependent on the text it
 * precedes
 * 
 * @author sforbes
 * 
 */
public class OptionalUnitPrefixFormattingStrategy extends DefaultFormattingStrategy {

    private final Supplier<FormattingContext> formattingContextSupplier;


    public OptionalUnitPrefixFormattingStrategy(final Supplier<FormattingContext> formattingContextSupplier) {
        this.formattingContextSupplier = formattingContextSupplier;
    }


    @Override
    public String format(final String content, final boolean isLineStart, final String indentation,
            final int[] positions) {
        final FormattingContext formattingContext = formattingContextSupplier.get();
        if (formattingContext.hasMoreContent()) {
            final FormattingContext nextFormattingContext = formattingContext.impersonateNextContentContext();
            final IFormattingStrategy formattingStrategy = nextFormattingContext.currentContentType()
                    .formattingStrategy(formattingContextSupplierFor(nextFormattingContext));
            return formatContent(content, isLineStart, indentation, positions, formattingStrategy);
        }
        return content;
    }


    private String formatContent(final String content, final boolean isLineStart, final String indentation,
            final int[] positions, final IFormattingStrategy formattingStrategy) {
        final String[] contentLines = content.split(NEWLINE);
        final StringBuilder sb = new StringBuilder();

        for (final String contentLine : contentLines) {
            if (contentLine.trim().length() > 0) {
                sb.append(formattingStrategy.format(contentLine, isLineStart, indentation, positions));
                sb.append(NEWLINE);
            }
        }

        return sb.toString();
    }


    private Supplier<FormattingContext> formattingContextSupplierFor(final FormattingContext formattingContext) {
        return new Supplier<FormattingContext>() {
            @Override
            public FormattingContext get() {
                return formattingContext;
            }
        };
    }
}
