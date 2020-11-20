package guinea.diego.myrecycleview

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import guinea.diego.myrecycleview.local.DB_Helper
import guinea.diego.myrecycleview.modelo.*
import guinea.diego.myrecycleview.servicios.BaseCallback
import guinea.diego.myrecycleview.servicios.Connectivity
import guinea.diego.myrecycleview.servicios.getNumericValues
import guinea.diego.myrecycleview.viewmodel.OriginViewModel
import kotlinx.android.synthetic.main.origin_detall.*



class OriginDetail: AppCompatActivity() {

    private val viewModel = OriginViewModel()
    lateinit var handler: DB_Helper

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.origin_detall)
            handler = DB_Helper(this)

            configPage()
        }

    private fun configPage() {
        val urlRepo = intent.getStringExtra("url")
        val planet = intent.getStringExtra("name")
        val numLocation = urlRepo?.getNumericValues().toString()
        Observer(planet)
        viewModel.getOriginUrl(numLocation)
    }

    fun Observer(name: String?){
        if(Connectivity().isConnected(this)){
            viewModel.viewMLD.observe(this, Observer {
                handler.importDataUrl(it)
                importData(it)
            })
        }else{
            name?.let { onFaild(it) }
        }
    }

    private fun onFaild(planet: String) {
        val response = handler.readUrlData(planet)
            name_planet.text = response.name
            type_planet.text = response.type
            dimension.text = response.dimension
    }

    private fun importData(result: UrlOrigin) {
            name_planet.text = result.name
            type_planet.text = result.type
            dimension.text = result.dimension
    }
}