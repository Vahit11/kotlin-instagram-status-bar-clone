package com.vahitkeskin.kotlininstagramstatusbarclone.adapter

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.vahitkeskin.kotlininstagramstatusbarclone.R
import de.hdodenhof.circleimageview.CircleImageView
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList

class StatusBarAdapter(
    private val userName: ArrayList<String>,
    private val userImage: ArrayList<String>,
    private val context: Context
) : RecyclerView.Adapter<StatusBarAdapter.SBAViewHolder>() {

    var number = 0
    var runnable : Runnable = Runnable {  }
    var handler: Handler = Handler(Looper.getMainLooper())

    private val statusBorderColors: Array<String> = arrayOf(
        "#403e91",
        "#8ee81f",
        "#bd7ad5",
        "#973b5a",
        "#1cd678",
        "#bf2e4d",
        "#0a8afd",
        "#9ce1c8",
        "#f09d94",
        "#fe85fa",
        "#8586a3",
        "#84dcb8"
    )

    class SBAViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SBAViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.status_bar_item, parent, false)
        return SBAViewHolder(view)
    }

    override fun onBindViewHolder(holder: SBAViewHolder, position: Int) {
        val tvStatusBarUserName = holder.itemView.findViewById<TextView>(R.id.tvStatusBar)
        val civStatusBarUserImage = holder.itemView.findViewById<CircleImageView>(R.id.civStatusBar)
        val pbStatusBar = holder.itemView.findViewById<ProgressBar>(R.id.pbStatusBar)

        val borderColor = Color.parseColor(statusBorderColors[position % 12])
        val imageViewStr = userImage[position]
        val textViewStr = userName[position]
        civStatusBarUserImage.borderColor = borderColor

        tvStatusBarUserName.text = textViewStr
        println(userImage[position])
        Picasso.get().load(imageViewStr).into(civStatusBarUserImage, object : Callback {
            override fun onSuccess() {
                pbStatusBar.isVisible = false
            }

            override fun onError(e: Exception?) {
                pbStatusBar.isVisible = true
            }

        })

        holder.itemView.setOnClickListener {
            popUp(textViewStr,imageViewStr,borderColor)
        }
    }

    private fun popUp(strText: String, strImage: String, intColorCode: Int) {
        val dialog = Dialog(context,android.R.style.Theme_Translucent_NoTitleBar_Fullscreen)
        dialog.setContentView(R.layout.status_bar_pop_up)

        val popUpBackground = dialog.findViewById<LinearLayout>(R.id.statusBarPopUpLL)
        val popUpImageView = dialog.findViewById<ImageView>(R.id.statusBarPopUpIV)
        val popUpImageTextView = dialog.findViewById<TextView>(R.id.statusBarPopUpTV)
        val popUpImageCIV = dialog.findViewById<CircleImageView>(R.id.statusBarPopUpCIV)
        val popUpProgressBar = dialog.findViewById<ProgressBar>(R.id.statusBarPopUpPB)

        Picasso.get().load(strImage).into(popUpImageView)
        Picasso.get().load(strImage).into(popUpImageCIV)
        popUpImageTextView.text = strText

        popUpBackground.setBackgroundColor(intColorCode)
        number = 0

        val timer = Timer()
        val timerTask= object : TimerTask() {
            override fun run() {
                number++
                popUpProgressBar.progress = number
                if (number == 100) {
                    timer.cancel()
                    dialog.dismiss()
                }
            }
        }
        timer.schedule(timerTask,0,50)

        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()
    }

    override fun getItemCount(): Int {
        return userName.size
    }

}