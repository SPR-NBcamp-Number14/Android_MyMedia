package com.example.android_mymedia.home.viewmdoel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.android_mymedia.home.data.ButtonModel
import com.example.android_mymedia.home.data.PlayListModel
import com.example.android_mymedia.home.repository.HomeRepository
import com.example.android_mymedia.home.repository.HomeRepositoryImpl
import com.example.android_mymedia.retrofit.RetrofitClient
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: HomeRepository
) : ViewModel() {

    //메인 뷰 리스트
    private val _categoryList: MutableLiveData<Set<PlayListModel>?> = MutableLiveData()
    val categoryList: LiveData<Set<PlayListModel>?> get() = _categoryList

    //다음 페이지 판안 여부 토큰

    private val _pageToken: MutableLiveData<String?> = MutableLiveData()
    val pageToken: LiveData<String?> get() = _pageToken

    //버튼 카테고리
    private val _btnList: MutableLiveData<List<ButtonModel>> = MutableLiveData()

    val btnList: LiveData<List<ButtonModel>> get() = _btnList

    //카테고리
    private val _liveCategory: MutableLiveData<String?> = MutableLiveData()
    val liveCategory: LiveData<String?> get() = _liveCategory

    //로딩 여부

    private val _loading: MutableLiveData<Boolean> = MutableLiveData()
    val loading: LiveData<Boolean> get() = _loading

    init {
        setPopularList() //이걸 주석한 뒤 디버깅을 하면 홈 화면 API 사용 x
        _btnList.value = mutableListOf<ButtonModel>().apply {
            add(
                ButtonModel(
                    category = "0",
                    btnTitle = "최신 인기"
                )
            )
        }
        setBtnList()
    }

    private fun setPopularList() {
        viewModelScope.launch {
            val token = pageToken.value
            val response = repository.getPopularVideo(token, "0")
            val list = response.first
            val nextToken = response.second
            var currentList = categoryList.value.orEmpty().toMutableSet()

            currentList = list.toMutableSet()

            _pageToken.value = nextToken
            _categoryList.value = currentList
        }
    }

    private fun setBtnList() {
        viewModelScope.launch {
            val currentList = btnList.value.orEmpty().toMutableList()
            val responseList = repository.getCategory() ?: return@launch

            currentList.addAll(responseList)

            _btnList.value = currentList
        }
    }

    fun setNextPage() {
        viewModelScope.launch {
            if (_loading.value == true) return@launch
            _loading.value = true

            val token = pageToken.value
            val category = liveCategory.value.orEmpty()

            val response = repository.getPopularVideo(token, category)
            val list = response.first
            val nextToken = response.second

            val currentList = categoryList.value.orEmpty().toMutableSet().apply {
                addAll(list)
            }
            _pageToken.value = nextToken
            _categoryList.value = currentList

            _loading.value = false
        }
    }

    fun setCategory(category: String) {
        viewModelScope.launch {
            val token = pageToken.value
            val response = repository.getPopularVideo(token, category)

            val list = response.first
            val nextToken = response.second
            var currentList = categoryList.value.orEmpty().toMutableSet()
            var updateCategory = liveCategory.value.orEmpty()

            currentList = list.toMutableSet()
            updateCategory = category

            _pageToken.value = null
            _categoryList.value = null
            _liveCategory.value = null

            _pageToken.value = nextToken
            _categoryList.value = currentList
            _liveCategory.value = updateCategory
        }
    }

}


class HomeViewModelFactory(

) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(
                HomeRepositoryImpl(RetrofitClient)
            ) as T
        } else {
            throw IllegalArgumentException("Not found ViewModel class.")
        }
    }
}
