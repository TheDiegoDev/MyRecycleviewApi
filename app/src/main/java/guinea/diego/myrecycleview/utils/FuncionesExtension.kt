package guinea.diego.myrecycleview.utils

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import androidx.core.graphics.drawable.toDrawable
import guinea.diego.myrecycleview.R



fun Context.showLoadingDialog(): Dialog{
        val progressDialog = Dialog(this)
        progressDialog.let {
            it.show()
            it.window?.setBackgroundDrawable(Color.TRANSPARENT.toDrawable())
            it.setContentView(R.layout.progress_dialog)
            it.setCancelable(false)
            it.setCanceledOnTouchOutside(false)
            return it
        }
    }

fun String.getNumericValues(): String {
    val sb = StringBuilder()
    for (i in this.indices) {
        var numeric = true
        try {
            val num =this[i].toString().toDouble()
        } catch (e: NumberFormatException) {
            numeric = false
        }
        if (numeric) {
            sb.append(this[i].toString())
        }
    }
    return sb.toString();
}

