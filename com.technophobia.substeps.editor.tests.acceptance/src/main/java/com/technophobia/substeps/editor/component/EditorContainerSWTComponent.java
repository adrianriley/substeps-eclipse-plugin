package com.technophobia.substeps.editor.component;

import java.util.List;

import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;

import com.technophobia.substeps.editor.steps.SWTBotInitialiser;

public class EditorContainerSWTComponent extends AbstractSWTLocatable<List<? extends SWTBotEditor>> {

    public void closeAll() {
        final List<? extends SWTBotEditor> editors = locate();
        for (final SWTBotEditor editor : editors) {
            editor.close();
        }
    }


    @Override
    protected List<? extends SWTBotEditor> doLocate() {
        return SWTBotInitialiser.bot().editors();
    }

}
