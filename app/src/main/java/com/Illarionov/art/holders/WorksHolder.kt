package com.Illarionov.art.holders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.Illarionov.art.OnClickItemListener
import com.Illarionov.art.R
import com.company.myartist.model.Media
import com.company.myartist.model.Work
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.view_work_item.view.*

class WorksHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {

    // у тебя постоянно используюется Picasso, уже как бы намекает сделать это использование в одном месте
    // во первых во всех практически случаях эту библиотеку будет использовать только ImageView,
    // во вторых если понадобиться заменить библиотеку Picasso например на Glide, ты или разработчик
    // который в последующем будет сопровождать твой код замахается выпиливать эту библиотеку
    // представь что у тебя сотня вьюхолдеров и в каждом несколько вьюимеджей
    // todo задача: создать функцию расширение для ImageView

    // в данном случае использовать пикасо было лишним с иконками, так как изображения для этих компонентов
    // мы добавляем статически на этапе верстки
    fun show(item: Work?, listener: OnClickItemListener? = null) {
        itemView.apply {
            val sizeWork = itemView.context.resources.getDimension(R.dimen.works_fragment_work_width).toInt()
            val url = item?.media?.makeUrl(Media.MediaRatio.o, Media.MediaSide.x, sizeWork)
//    а где подгрузка плейсхолдера, прежде чем изображение загрузится необходимо подкрашивать в цвет заданный item.colors?.middle

            Picasso.get().load(url).into(imageWork)
            titleWork.text = item?.name // а если у тебя в поле null то пользователь увидет в названии null ))
//            Picasso.get().load(R.drawable.ic_like).into(likeBtn)
            countWork.text = item?.counters?.likes // а если у тебя в поле null то пользователь увидет в количестве лайков null ))
//            Picasso.get().load(R.drawable.ic_sale)
//            Picasso.get().load(R.drawable.ic_info)
        }
    }
}