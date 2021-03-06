package com.technophobia.substeps.document.content.feature;

import com.technophobia.substeps.document.content.AbstractContentDefinitionFactory;
import com.technophobia.substeps.document.content.feature.definition.AndContentTypeDefinition;
import com.technophobia.substeps.document.content.feature.definition.CommentContentTypeDefinition;
import com.technophobia.substeps.document.content.feature.definition.DefineContentTypeDefinition;
import com.technophobia.substeps.document.content.feature.definition.GivenContentTypeDefinition;
import com.technophobia.substeps.document.content.feature.definition.TagContentTypeDefinition;
import com.technophobia.substeps.document.content.feature.definition.ThenContentTypeDefinition;
import com.technophobia.substeps.document.content.feature.definition.WhenContentTypeDefinition;

public class SubstepsContentDefinitionFactory extends AbstractContentDefinitionFactory {

    public SubstepsContentDefinitionFactory() {
        super(new DefineContentTypeDefinition(), //
                new CommentContentTypeDefinition(), //
                new TagContentTypeDefinition(), //
                new GivenContentTypeDefinition(), //
                new WhenContentTypeDefinition(), //
                new ThenContentTypeDefinition(), //
                new AndContentTypeDefinition());
    }
}
