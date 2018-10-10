package com.deflatedpickle.wheeze.util.pen

import java.awt.geom.Point2D
import java.awt.Point
import jpen.owner.PenClip
import org.eclipse.swt.widgets.Control

// https://sourceforge.net/p/jpen/discussion/753961/thread/d6028fb2/
internal class ControlPenClip(val controlPenOwner: ControlPenOwner) : PenClip {

    private val pointOnScreenEvaluator = PointOnScreenEvaluator()

    private val containsEvaluator = ContainsEvaluator()

    override fun evalLocationOnScreen(pointOnScreen: Point) {
        val activeControl = controlPenOwner.activeControl
        pointOnScreenEvaluator.pointOnScreen = pointOnScreen
        pointOnScreenEvaluator.control = activeControl
        controlPenOwner.invokeInGuiThreadAndWait(activeControl, pointOnScreenEvaluator)
    }

    private class PointOnScreenEvaluator : Runnable {
        internal var control: Control? = null
        internal var pointOnScreen: Point? = null
        override fun run() {
            val location = control!!.toDisplay(0, 0)
            pointOnScreen!!.x = location.x
            pointOnScreen!!.y = location.y
        }
    }

    override fun contains(point: Point2D.Float): Boolean {
        if (point.x < 0 || point.y < 0)
            return false
        val activeControl = controlPenOwner.activeControl
        containsEvaluator.control = activeControl
        containsEvaluator.point = point
        controlPenOwner.invokeInGuiThreadAndWait(activeControl, containsEvaluator)
        return containsEvaluator.contains
    }

    private class ContainsEvaluator : Runnable {
        internal var control: Control? = null
        internal var point: Point2D.Float? = null
        internal var contains: Boolean = false
        override fun run() {
            val controlSize = control!!.size
            contains = point!!.x <= controlSize.x && point!!.y <= controlSize.y
        }
    }
}