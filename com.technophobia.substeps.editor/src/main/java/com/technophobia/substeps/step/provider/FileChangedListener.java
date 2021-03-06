package com.technophobia.substeps.step.provider;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;

import com.technophobia.substeps.FeatureEditorPlugin;
import com.technophobia.substeps.supplier.Callback1;

public class FileChangedListener implements IResourceChangeListener {

    private static final int[] VALID_EVENT_KINDS = new int[] { IResourceDelta.CHANGED, IResourceDelta.ADDED,
            IResourceDelta.REMOVED };

    private final String fileExtension;
    private final Callback1<IFile> fileChangedCallback;


    public FileChangedListener(final String fileExtension, final Callback1<IFile> fileChangedCallback) {
        this.fileExtension = fileExtension;
        this.fileChangedCallback = fileChangedCallback;
    }


    @Override
    public void resourceChanged(final IResourceChangeEvent event) {
        try {
            event.getDelta().accept(new IResourceDeltaVisitor() {

                @Override
                public boolean visit(final IResourceDelta delta) throws CoreException {
                    if (isValidKind(delta)) {
                        final IResource resource = delta.getResource();
                        if (resource instanceof IContainer) {
                            return true;
                        }

                        if (resource instanceof IFile) {
                            handleFile((IFile) resource);
                            return true;
                        }
                    }
                    return false;
                }
            });
        } catch (final CoreException ex) {
            FeatureEditorPlugin.instance().log(IStatus.WARNING, "Could not handle file change for event " + event);
        }
        System.out.println(event);
    }


    private boolean isValidKind(final IResourceDelta delta) {
        final int kind = delta.getKind();
        for (final int validEventKind : VALID_EVENT_KINDS) {
            if (kind == validEventKind) {
                return true;
            }
        }
        return false;
    }


    private void handleFile(final IFile file) {
        if (fileExtension.equals(file.getFileExtension())) {
            fileChangedCallback.doCallback(file);
        }
    }
}
