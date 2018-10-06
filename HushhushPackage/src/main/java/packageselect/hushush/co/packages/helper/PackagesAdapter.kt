package packageselect.hushush.co.packages.helper

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.package_card.view.*
import packageselect.hushush.co.R
import packageselect.hushush.co.packages.dao.Packages


class PackagesAdapter(val pkg: Packages, val seatCount: String) : RecyclerView.Adapter<PackagesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder(LayoutInflater
                    .from(parent.context).inflate(R.layout.package_card, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(pkg.packages[position])
    }

    override fun getItemCount(): Int = pkg.packages.size

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        fun bind(data: Packages.Package) {
            with(view) {
                Glide.with(this).load(data.picUrl).into(image)
                packageName.text = data.name
                price.text = "₹ " + (((seatCount.toFloat() - data.defaultTicketCount) * data.extraOneUserAmount) + data.defaultPackageAmount).toString()
                var item = ""
                for (i in data.items) {
                    item += i.itemname
                    if (i.category == "person")
                        item += ": $seatCount"
                    else
                        item += ": 1"
                    item += ", "
                }
                items.text = item
            }
        }

    }
}