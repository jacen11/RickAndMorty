package ru.dpastukhov.rickandmorty.ui.character

import androidx.paging.PagingSource
import androidx.paging.PagingState
import ru.dpastukhov.rickandmorty.data.model.CharacterDto
import ru.dpastukhov.rickandmorty.data.repo.CharacterRepository

class CharacterPagingSource(private val repo: CharacterRepository, private val search: String?) : PagingSource<Int, CharacterDto>() {

    private companion object {
        const val FIRST_PAGE = 1
    }

    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, CharacterDto> {
        return try {
            // Load page 1 if undefined.
            val nextPageNumber = params.key ?: 1
            val response = repo.getCharacter(params.key ?: FIRST_PAGE, search)
            val data: List<CharacterDto> = response.results?.filterNotNull().orEmpty()
            LoadResult.Page(
                data = data,
                prevKey = null, // Only paging forward.
                nextKey = response.info?.next?.let { nextPageNumber + 1 }
                    ?: null  //if (response.infoPage.next == null) null else nextPageNumber + 1
            )
        } catch (e: Exception) {
            // Handle errors in this block
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, CharacterDto>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey
        }
    }

}