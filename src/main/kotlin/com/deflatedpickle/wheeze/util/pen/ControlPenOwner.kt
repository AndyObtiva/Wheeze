package com.deflatedpickle.wheeze.util.pen

import java.util.Arrays
import jpen.owner.PenClip
import jpen.owner.PenOwner
import jpen.PenEvent
import jpen.PenProvider
import jpen.provider.osx.CocoaProvider
import jpen.provider.wintab.WintabProvider
import jpen.provider.xinput.XinputProvider
import org.eclipse.swt.events.MouseEvent
import org.eclipse.swt.events.MouseTrackAdapter
import org.eclipse.swt.widgets.Control

// https://sourceforge.net/p/jpen/discussion/753961/thread/d6028fb2/
class ControlPenOwner(val activeControl: Control) : PenOwner {

    private var penManagerHandle: PenOwner.PenManagerHandle? = null
    private val controlPenClip = ControlPenClip(this)
    @Volatile
    private var mayBeDraggingOut: Boolean = false

    override fun setPenManagerHandle(penManagerHandle: PenOwner.PenManagerHandle) {
        this.penManagerHandle = penManagerHandle
        activeControl.addMouseTrackListener(object : MouseTrackAdapter() {
            override fun mouseEnter(ev: MouseEvent?) {
                mayBeDraggingOut = true
                // ControlPenClip has to wait for the UI thread in its operations so we need an asynch call here to avoid deadlock (setPenManagerPaused acquires the pen scheduler lock)
                Thread { penManagerHandle.setPenManagerPaused(false) }.start()
            }

            override fun mouseExit(ev: MouseEvent?) {
                mayBeDraggingOut = false
                Thread { penManagerHandle.setPenManagerPaused(true) }.start()
            }
        }
        )
    }

    override fun getPenProviderConstructors(): Collection<PenProvider.Constructor> {
        return Arrays.asList(
            XinputProvider.Constructor(), WintabProvider.Constructor(), CocoaProvider.Constructor()
        )
    }

    internal fun invokeInGuiThreadAndWait(control: Control, runnable: Runnable) {
        synchronized(penManagerHandle!!.penSchedulerLock) {
            control.display.syncExec(runnable)
        }
    }

    override fun getPenClip(): PenClip {
        return controlPenClip
    }

    override fun isDraggingOut(): Boolean {
        return mayBeDraggingOut
    }

    override fun evalPenEventTag(ev: PenEvent): Any? {
        return null
    }

    override fun enforceSinglePenManager(): Boolean {
        return false
    }
}