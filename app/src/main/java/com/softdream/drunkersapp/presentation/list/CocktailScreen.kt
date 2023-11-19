package com.softdream.drunkersapp.presentation.list


import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.ViewModel
import com.softdream.drunkersapp.R
import com.softdream.drunkersapp.domain.Cocktail
import com.softdream.drunkersapp.presentation.detail.CocktailDetailViewModel

@Composable
fun CocktailScreen(
    state: CocktailScreenState,
    viewModel: ViewModel,
    onItemClick: (name: String) -> Unit = {}
) {

    when {
        state.cocktails.isNotEmpty() -> {
            LazyColumn(contentPadding = PaddingValues()) {
                items(state.cocktails) { cocktail ->
                    CocktailItem(item = cocktail, onItemClick)
                }
            }
            if (state.toastMessage.isNotBlank()) {
                Toast.makeText(LocalContext.current, state.toastMessage, Toast.LENGTH_SHORT).show()
            }
        }
        state.isLoading -> Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) { CircularProgressIndicator() }
        state.error.isNotBlank() -> Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            ErrorButton(errorText = state.error, viewModel)
        }

    }
}

@Composable
fun ErrorButton(errorText: String, viewModel: ViewModel) {
    Column(
        modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Button(
            onClick = {
                when (viewModel) {
                    is CocktailViewModel -> viewModel.retryGetCocktails()
                    is CocktailDetailViewModel -> viewModel.retryGetCocktail()
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.errorContainer,
                contentColor = MaterialTheme.colorScheme.onErrorContainer
            ),
            shape = MaterialTheme.shapes.small
        ) {
            Text(
                text = errorText,
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun CocktailItem(item: Cocktail, onItemClick: (name: String) -> Unit) {
    Card(
        elevation = CardDefaults.cardElevation(),
        modifier = Modifier
            .padding(dimensionResource(R.dimen.mediumPadding))
            .clickable { onItemClick(item.name) }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(dimensionResource(R.dimen.mediumPadding))
        ) {
            CocktailIcon(Icons.Filled.Info, Modifier.weight(0.15f))
            CocktailInfo(item.name, item.category, Modifier.weight(0.85f))
        }
    }
}

@Composable
fun CocktailInfo(
    title: String,
    category: String,
    modifier: Modifier,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start
) {
    Column(modifier = modifier, horizontalAlignment = horizontalAlignment) {
        Text(text = title, style = MaterialTheme.typography.titleMedium)
        Text(
            text = category,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .alpha(0.8f)
        )
    }
}

@Composable
fun CocktailIcon(icon: ImageVector, modifier: Modifier) {
    Image(
        imageVector = icon,
        contentDescription = stringResource(id = R.string.icon_cocktail),
        modifier = Modifier.padding(dimensionResource(id = R.dimen.mediumPadding))
    )
}





