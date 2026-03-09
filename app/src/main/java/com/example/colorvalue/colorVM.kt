package com.example.colorvalue

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.lifecycle.ViewModel

class ColorVM(application: Application) : AndroidViewModel(application) {

    private val dao = ColorDatabase.getInstance(application).colorDao()

    val allColors: LiveData<List<Color>> = dao.getAll().asLiveData()

    fun addColor(name: String, red: Int, green: Int, blue: Int) {
        val hex = String.format("#%02X%02X%02X", red, green, blue)
        viewModelScope.launch {
            dao.insert(Color(name = name, hex = hex, red = red, green = green, blue = blue))
        }
    }

    fun deleteColor(color: Color) {
        viewModelScope.launch {
            dao.delete(color)
        }
    }
}

//class ScoresVM: ViewModel() {
//    private var _scoreA = MutableLiveData<Int>(0)
//    private var _scoreB = MutableLiveData<Int>(0)
//
//    val scoreA: LiveData<Int>
//        get() = _scoreA
//    val scoreB: LiveData<Int>
//        get() =_scoreB
//
//    fun incrementScoreByOne(isTeamA: Boolean){
//        if (isTeamA)
//            _scoreA.value = _scoreA.value?.plus(1)
//        else
//            _scoreB.value = _scoreB.value?.plus(1)
//    }
//    fun incrementScoreByTwo(isTeamA: Boolean){
//        if (isTeamA)
//            _scoreA.value = _scoreA.value?.plus(2)
//
//        else
//            _scoreB.value = _scoreB.value?.plus(2)
//    }
//    fun resetScore(isReset: Boolean){
//        if (isReset){
//            _scoreA.value = 0
//            _scoreB.value = 0
//        }
//    }
//}
