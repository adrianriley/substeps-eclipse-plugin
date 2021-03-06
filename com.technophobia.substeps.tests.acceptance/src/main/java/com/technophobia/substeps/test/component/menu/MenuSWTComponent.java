package com.technophobia.substeps.test.component.menu;

import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotMenu;

import com.technophobia.substeps.test.component.AbstractSWTLocatable;
import com.technophobia.substeps.test.component.SWTComponent;
import com.technophobia.substeps.test.component.SWTLocatable;

public class MenuSWTComponent extends AbstractSWTLocatable<SWTBotMenu> implements SWTComponent<SWTBotMenu> {

    private final String title;
    private final SWTLocatable<?> parent;


    public MenuSWTComponent(final String title, final SWTLocatable<?> parent) {
        this.title = title;
        this.parent = parent;
    }


    public MenuSWTComponent menuFor(final String newTitle) {
        return new MenuSWTComponent(newTitle, this);
    }


    public void click() {
        locate().click();
    }


    @Override
    public SWTBotMenu doLocate() {
        final Object locate = parent.locate();
        if (locate instanceof SWTBot) {
            return ((SWTBot) locate).menu(title);
        } else if (locate instanceof SWTBotMenu) {
            return ((SWTBotMenu) locate).menu(title);
        }
        throw new IllegalStateException("Could not locate menu from type " + locate.getClass().getName());
    }
}
