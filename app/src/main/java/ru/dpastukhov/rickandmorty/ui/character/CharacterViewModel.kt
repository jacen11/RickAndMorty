package ru.dpastukhov.rickandmorty.ui.character

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import ru.dpastukhov.rickandmorty.data.ApiService
import ru.dpastukhov.rickandmorty.data.model.Character
import ru.dpastukhov.rickandmorty.ui.BaseViewModel
import javax.inject.Inject

class CharacterViewModel : BaseViewModel() {
    @Inject
    lateinit var apiService: ApiService

    private lateinit var subscription: Disposable

    val test: MutableLiveData<String> = MutableLiveData()

    init {
        load()
    }

    var adapter: MutableLiveData<List<Character?>?> = MutableLiveData()

    private fun load() {
        subscription = apiService.getAllCharacter()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrievePostListStart() }
            .doOnTerminate { onRetrievePostListFinish() }
            .subscribe(
                {response ->
                    onRetrievePostListSuccess(response.results)
                },
                { onRetrievePostListError(it) }
            )
    }

    private fun onRetrievePostListStart() {

        test.value = "sfsdfiosdfk"

    }

    private fun onRetrievePostListFinish() {

    }

    private fun onRetrievePostListSuccess(it: List<Character?>?) {
        adapter.value = it
        test.value = it?.get(0)?.name
    }

    private fun onRetrievePostListError(it: Throwable) {
        Log.e("Character", it.stackTrace.toString())
       // Log.e("Character", it.message)
        Log.e("Character", it.localizedMessage)
        test.value = it.message
    }

    //    private val _text = MutableLiveData<String>().apply {
//        value = "This is characters"
//    }
    val text: LiveData<String> = test

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }
}