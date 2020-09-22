package guinea.diego.myrecycleview
import android.content.Context
import android.content.Intent
import android.transition.Fade
import android.transition.Slide
import android.transition.Transition
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import guinea.diego.myrecycleview.modelo.CharacterRM
import guinea.diego.myrecycleview.modelo.Characters
import kotlinx.android.synthetic.main.characters.view.*


class RecyclerAdapter(private val characters: Characters,
                      private val context: Context): RecyclerView.Adapter<RecyclerAdapter.BaseViewHolder>() {


    //Funcion encargada de la creacion del ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.characters, parent, false)
        return BaseViewHolder(view)
    }

    //Funcion encargada de contar cuantos items son
    override fun getItemCount(): Int {
        return characters.results.size
    }

    //Funcion encargada de montar cada elemento del recycler
    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val character = characters.results[position]
        holder?.let {
            it.bindView(character)
        }
    }

    inner class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        //Funcion encargada de emparejar los elementos del layaout con los datos de la Api
        fun bindView(character: CharacterRM) {
            val title = itemView.profile_name
            val desc = itemView.profile_desc
            val image = itemView.imageView

            title.text = character.name
            desc.text = character.species
            Glide.with(image.context)
                .load(character.image)
                .into(image)


        }
        init {
            //Evento de click en un item del recyclerView
            itemView.setOnClickListener{
                val pos = characters.results[adapterPosition]
                val intent: Intent = Intent(itemView.context, InfoCharacter::class.java)
                intent.putExtra("persons", pos)
                context.startActivity(intent)
            }
        }
    }
}