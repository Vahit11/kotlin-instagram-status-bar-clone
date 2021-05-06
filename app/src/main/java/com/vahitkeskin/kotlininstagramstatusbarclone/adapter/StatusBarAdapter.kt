package com.vahitkeskin.kotlininstagramstatusbarclone.adapter

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
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

class StatusBarAdapter(
    private val userName: ArrayList<String>,
    private val userImage: ArrayList<String>,
    private val context: Context
) : RecyclerView.Adapter<StatusBarAdapter.SBAViewHolder>() {

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
        civStatusBarUserImage.borderColor = borderColor

        tvStatusBarUserName.text = userName[position]
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
            Toast.makeText(context,"Click item user name: ${userName[position]}",Toast.LENGTH_LONG).show()
            popUp(imageViewStr,borderColor)
        }
    }

    private fun popUp(strImage: String, intColorCode: Int) {
        val dialog = Dialog(context,android.R.style.Theme_Translucent_NoTitleBar_Fullscreen)
        dialog.setContentView(R.layout.status_bar_pop_up)

        val popUpBackground = dialog.findViewById<LinearLayout>(R.id.statusBarPopUpLL)
        val popUpImageView = dialog.findViewById<ImageView>(R.id.statusBarPopUpIV)
        Picasso.get().load(strImage).into(popUpImageView)

        popUpBackground.setBackgroundColor(intColorCode)

        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()
    }

    override fun getItemCount(): Int {
        return userName.size
    }

}