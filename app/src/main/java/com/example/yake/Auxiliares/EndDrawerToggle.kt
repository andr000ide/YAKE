package com.example.yake.Auxiliares

import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.appcompat.graphics.drawable.DrawerArrowDrawable
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.Toolbar
import android.view.View
import com.example.yake.R


class EndDrawerToggle(
    activity: AppCompatActivity, private val drawerLayout: androidx.drawerlayout.widget.DrawerLayout, toolbar: Toolbar,
    openDrawerContentDescRes: Int, closeDrawerContentDescRes: Int
) : androidx.drawerlayout.widget.DrawerLayout.DrawerListener {
    private val arrowDrawable: DrawerArrowDrawable
    private val toggleButton: AppCompatImageButton
    private val openDrawerContentDesc: String
    private val closeDrawerContentDesc: String

    init {
        this.openDrawerContentDesc = activity.getString(openDrawerContentDescRes)
        this.closeDrawerContentDesc = activity.getString(closeDrawerContentDescRes)

        arrowDrawable = DrawerArrowDrawable(toolbar.context)
        arrowDrawable.direction = DrawerArrowDrawable.ARROW_DIRECTION_END

        toggleButton = AppCompatImageButton(
            toolbar.context, null,
            R.attr.toolbarNavigationButtonStyle
        )
        toolbar.addView(toggleButton, Toolbar.LayoutParams(GravityCompat.END))
        toggleButton.setImageDrawable(arrowDrawable)
        toggleButton.setOnClickListener { toggle() }
    }

    fun syncState() {
        if (drawerLayout.isDrawerOpen(GravityCompat.END)) {
            setPosition(1f)
        } else {
            setPosition(0f)
        }
    }

    fun toggle() {
        if (drawerLayout.isDrawerOpen(GravityCompat.END)) {
            drawerLayout.closeDrawer(GravityCompat.END)
        } else {
            drawerLayout.openDrawer(GravityCompat.END)
        }
    }

    fun setPosition(position: Float) {
        if (position == 1f) {
            arrowDrawable.setVerticalMirror(true)
            toggleButton.contentDescription = closeDrawerContentDesc
        } else if (position == 0f) {
            arrowDrawable.setVerticalMirror(false)
            toggleButton.contentDescription = openDrawerContentDesc
        }
        arrowDrawable.progress = position
    }

    override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
        setPosition(Math.min(1f, Math.max(0f, slideOffset)))
    }

    override fun onDrawerOpened(drawerView: View) {
        setPosition(1f)
    }

    override fun onDrawerClosed(drawerView: View) {
        setPosition(0f)
    }

    override fun onDrawerStateChanged(newState: Int) {}
}