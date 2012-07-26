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
package com.technophobia.substeps.document.content.feature;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.technophobia.substeps.document.content.ContentTypeDefinitionFactory;
import com.technophobia.substeps.document.content.NullContentTypeDefinition;
import com.technophobia.substeps.document.content.feature.definition.AndContentTypeDefinition;
import com.technophobia.substeps.document.content.feature.definition.CommentContentTypeDefinition;
import com.technophobia.substeps.document.content.feature.definition.DefineContentTypeDefinition;
import com.technophobia.substeps.document.content.feature.definition.GivenContentTypeDefinition;
import com.technophobia.substeps.document.content.feature.definition.TagContentTypeDefinition;
import com.technophobia.substeps.document.content.feature.definition.ThenContentTypeDefinition;
import com.technophobia.substeps.document.content.feature.definition.WhenContentTypeDefinition;

public class SubstepsContentTypeDefinitionFactoryTest {

    private ContentTypeDefinitionFactory contentTypeDefinitionFactory;


    @Before
    public void initialiseFactory() {
        this.contentTypeDefinitionFactory = new SubstepsContentDefinitionFactory();
    }


    @Test
    public void canGetAllSubstepsContentTypeDefinitionsByName() {
        assertThat(contentTypeDefinitionFactory.contentTypeDefintionByName("__substeps_define"),
                is(DefineContentTypeDefinition.class));
        assertThat(contentTypeDefinitionFactory.contentTypeDefintionByName("__feature_tag"),
                is(TagContentTypeDefinition.class));
        assertThat(contentTypeDefinitionFactory.contentTypeDefintionByName("__feature_comment"),
                is(CommentContentTypeDefinition.class));
        assertThat(contentTypeDefinitionFactory.contentTypeDefintionByName("__feature_given"),
                is(GivenContentTypeDefinition.class));
        assertThat(contentTypeDefinitionFactory.contentTypeDefintionByName("__feature_when"),
                is(WhenContentTypeDefinition.class));
        assertThat(contentTypeDefinitionFactory.contentTypeDefintionByName("__feature_then"),
                is(ThenContentTypeDefinition.class));
        assertThat(contentTypeDefinitionFactory.contentTypeDefintionByName("__feature_and"),
                is(AndContentTypeDefinition.class));
    }


    @Test
    public void returnsNullDefinitionWhenInvalidTypeRequested() {

        assertThat(contentTypeDefinitionFactory.contentTypeDefintionByName("invalid_type"),
                is(NullContentTypeDefinition.class));
    }
}