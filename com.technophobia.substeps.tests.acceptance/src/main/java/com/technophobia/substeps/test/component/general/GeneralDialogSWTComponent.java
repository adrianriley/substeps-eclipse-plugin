package com.technophobia.substeps.test.component.general;

import org.eclipse.swtbot.eclipse.finder.SWTWorkbenchBot;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.TimeoutException;

import com.technophobia.substeps.test.component.AbstractSWTLocatable;
import com.technophobia.substeps.test.component.SWTRootComponent;
import com.technophobia.substeps.test.steps.SWTBotInitialiser;
import com.technophobia.substeps.test.steps.ShellStack;

public class GeneralDialogSWTComponent extends AbstractSWTLocatable<SWTBot> implements SWTRootComponent<SWTBot> {

    private final ShellStack shellStack;
    private final String dialogName;


    public GeneralDialogSWTComponent(final String dialogName) {
        this.dialogName = dialogName;
        this.shellStack = SWTBotInitialiser.shellStack();
    }


    //
    // Commands
    //

    // probably needs moving - not specific to dialogs
    public void clickButton(final String buttonText) {
        locate().button(buttonText).click();
    }


    public void setFocus() {
        System.out.println("Gaining focus to " + dialogName);
        final SWTBot bot = locate();
        shellStack.dialogHasOpened(bot);
        final SWTBotShell shell = new SWTBotShell(shellStack.currentShell());
        shell.activate();
        // SWTTestUtil.setActiveShellHack(shell.widget);
        shell.setFocus();
    }


    public void loseFocus() {
        System.out.println("Losing focus to " + dialogName);
        shellStack.dialogHasClosed();

        try {
            Thread.sleep(2000);
        } catch (final InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    public TreeSWTComponent tree() {
        return new TreeSWTComponent(this);
    }


    public ListSWTComponent list() {
        return new ListSWTComponent(this);
    }


    public TableSWTComponent table() {
        return new TableSWTComponent(this);
    }


    //
    // Queries
    //
    public boolean isDialogPresent(final String dialogTitle) {
        try {
            locate();
            return true;
        } catch (final TimeoutException ex) {
            return false;
        }
    }


    @Override
    public SWTBot doLocate() {
        // If there are 2 shells open, then we are ready (in this, we assume no
        // more than 1 dialog can be open at any time
        final SWTWorkbenchBot bot = SWTBotInitialiser.bot();
        final int visibleShellSize = shellStack.visibleShellSize(bot);
        if (visibleShellSize > 1) {
            return bot;
        }

        return null;
    }
}
