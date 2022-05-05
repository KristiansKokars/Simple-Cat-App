package com.kristianskokars.schoolsimplecatapp.feature.cat_list

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshState
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.kristianskokars.schoolsimplecatapp.data.model.Cat
import com.kristianskokars.schoolsimplecatapp.ui.components.BackgroundSurface

@Composable
fun CatListScreen(
    viewModel: CatListViewModel = hiltViewModel()
) {
    val cats by viewModel.cats.collectAsState()
    val isRefreshing by viewModel.isRefreshing.collectAsState()
    val refreshState = rememberSwipeRefreshState(isRefreshing = isRefreshing)

    CatListContent(
        cats = cats,
        refreshState = refreshState,
        onRefresh = viewModel::refreshCats
    )
}

@Composable
fun CatListContent(
    cats: List<Cat>,
    refreshState: SwipeRefreshState,
    onRefresh: () -> Unit,
) {
    SwipeRefresh(state = refreshState, onRefresh = onRefresh) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(cats) { cat ->
                AsyncImage(model = cat.url, contentDescription = null)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CatListScreenPreview() {
    BackgroundSurface {
        // CatListScreen(emptyList())
    }
}

// Cat("https://placekitten.com/500/500")
