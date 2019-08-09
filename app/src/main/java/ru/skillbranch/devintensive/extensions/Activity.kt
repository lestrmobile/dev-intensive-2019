package ru.skillbranch.devintensive.extensions

import android.app.Activity
import android.graphics.Rect
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.view.ViewTreeObserver
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import ru.skillbranch.devintensive.R

fun Activity.hideKeyboard() {
    val currentFocus:View? = getCurrentFocus()
    val view: View = if (currentFocus == null) View(this) else currentFocus
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}
//
//fun Activity.isKeyboardOpen():Boolean{
//
//    val ActivityRootView: View = findViewById(R.id.activityRoot)
//    ActivityRootView.getViewTreeObserver().addOnGlobalLayoutListener(ViewTreeObserver.OnGlobalLayoutListener());
//
//
//    override fun OnGlobalLayoutListener(){
//
//
//
//
//
//    return false
//}
//
//fun Activity.isKeyboardClosed():Boolean{
//    return true
//}


fun Activity.isKeyboardOpen(): Boolean {
    val permissibleError = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50F, resources.displayMetrics).toInt()
    val rootView = findViewById<View>(android.R.id.content)
    var rect = Rect()
    rootView.getWindowVisibleDisplayFrame(rect)
//    Log.d("M_Activity","${rootView.height}/${rect.height()}")
    val difference = rootView.height - rect.height()
    return (difference > permissibleError)
}

fun Activity.isKeyboardClosed():Boolean{
    val permissibleError = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50F, resources.displayMetrics).toInt()
    val rootView = findViewById<View>(android.R.id.content)
    var rect = Rect()
    rootView.getWindowVisibleDisplayFrame(rect)
//    Log.d("M_Activity","${rootView.height}/${rect.height()}")
    val difference = rootView.height - rect.height()
    return (difference < permissibleError)
}

/*
 Реализуй extension для проверки, открыта или нет Software Keyboard с применением метода rootView.getWindowVisibleDisplayFrame(Rect())
*/