package com.example.projektbozedzialajpls
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class PersonAdapter(
    private val people: MutableList<ludzik>,
    private val onDelete: (ludzik) -> Unit
) : RecyclerView.Adapter<PersonAdapter.PersonViewHolder>() {


    class PersonViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imieTextView: TextView = view.findViewById(R.id.tv_imie)
        val nazwiskoTextView: TextView = view.findViewById(R.id.tv_nazwisko)
        val wiekTextView: TextView = view.findViewById(R.id.tv_wiek)
        val wzrostTextView: TextView = view.findViewById(R.id.tv_wzrost)
        val wagaTextView: TextView = view.findViewById(R.id.tv_waga)
        val deleteButton: Button = view.findViewById(R.id.btn_delete)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.ludzik, parent, false)
        return PersonViewHolder(view)
    }


    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        val person = people[position]
        holder.imieTextView.text = "Imię: ${person.imie}"
        holder.nazwiskoTextView.text = "Nazwisko: ${person.nazwisko}"
        holder.wiekTextView.text = "Wiek: ${person.wiek}"
        holder.wzrostTextView.text = "Wzrost: ${person.wzrost} cm"
        holder.wagaTextView.text = "Waga: ${person.waga} kg"
        holder.deleteButton.setOnClickListener {
            onDelete(person)
        }
    }


    override fun getItemCount(): Int = people.size


}