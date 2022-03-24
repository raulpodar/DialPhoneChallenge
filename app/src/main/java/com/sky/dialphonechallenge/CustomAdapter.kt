import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sky.dialphonechallenge.R
import com.sky.dialphonechallenge.databinding.PhoneNumberItemBinding

class CustomAdapter (private val items: List<String>): RecyclerView.Adapter<CustomAdapter.ViewHolder>() {
//    private var items = listOf<String>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = PhoneNumberItemBinding.inflate(
            LayoutInflater.from(
                parent.context),
            parent,
            false
        )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        with(holder.binding){
            phoneNumberText.text=item
        }

    }


    override fun getItemCount(): Int {
        Log.v("sizee", items.size.toString())
        return items.size
    }

    inner class ViewHolder( val binding: PhoneNumberItemBinding) : RecyclerView.ViewHolder(binding.root)
}
