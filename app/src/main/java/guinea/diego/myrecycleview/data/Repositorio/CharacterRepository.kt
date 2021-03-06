package guinea.diego.myrecycleview.data.Repositorio


import RetrofitInitializer
import guinea.diego.myrecycleview.data.local.PrincipalRepo
import guinea.diego.myrecycleview.data.modelo.*
import guinea.diego.myrecycleview.utils.BaseCallback
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharacterRepository() {

    private val characterService = RetrofitInitializer(PrincipalRepo).characterService()
    private val characterIdService = RetrofitInitializer(PrincipalRepo).PersonId()
    private val urlOriginService = RetrofitInitializer(PrincipalRepo).OriginService()
    private val pageService = RetrofitInitializer(PrincipalRepo).PageCharacters()

    fun getCharacters(callback: BaseCallback<Characters>) {

        characterService.list().enqueue(object : Callback<Characters> {
            override fun onResponse(call: Call<Characters>, response: Response<Characters>) {
                if (response.body() == null) {
                    callback.onError(Error("esta vacio"))
                } else {
                    callback.onResult(response.body()!!)
                }
            }

            override fun onFailure(call: Call<Characters>, t: Throwable) {
                callback.onError(Error(t))

            }
        })
    }
    fun getCharactersID(callback: BaseCallback<CharacterRM>,personId:String) {

        characterIdService.getPerson(personId).enqueue(object : Callback<CharacterRM> {
            override fun onResponse(call: Call<CharacterRM>, response: Response<CharacterRM>) {
                if (response.body() == null) {
                    callback.onError(Error("esta vacio"))
                } else {
                    callback.onResult(response.body()!!)
                }
            }
            override fun onFailure(call: Call<CharacterRM>, t: Throwable) {
                callback.onError(Error(t))

            }
        })
    }

    fun getDataScroll(callback: BaseCallback<Characters>,page:String){
        pageService.getPage(page).enqueue((object : Callback<Characters> {
            override fun onResponse(call: Call<Characters>, response: Response<Characters>) {
                if (response.body() == null) {
                    callback.onError(Error("esta vacio"))
                } else {
                    callback.onResult(response.body()!!)
                }
            }
            override fun onFailure(call: Call<Characters>, t: Throwable) {
                callback.onError(Error(t))

            }
        }))

    }

    fun getUrlOrigin(callback: BaseCallback<UrlOrigin>, numLocation: String){
        urlOriginService.location(numLocation).enqueue(object : Callback<UrlOrigin>{
            override fun onFailure(call: Call<UrlOrigin>, t: Throwable) {
                callback.onError(Error(t))
            }

            override fun onResponse(call: Call<UrlOrigin>, response: Response<UrlOrigin>) {
                if (response.body() == null) {
                    callback.onError(Error("esta vacio"))
                } else {
                    callback.onResult(response.body()!!)
                }
            }
        })
    }



}






