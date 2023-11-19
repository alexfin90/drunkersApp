package com.softdream.drunkersapp.presentation.detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource

import androidx.lifecycle.ViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.softdream.drunkersapp.ErrorButton
import com.softdream.drunkersapp.R
import com.softdream.drunkersapp.domain.Cocktail


@Composable
fun CocktailDetailScreen(state: CocktailDetailScreenState, viewModel: ViewModel) {
    when {
        state.cocktail != null -> {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(
                        dimensionResource(R.dimen.largePadding)
                    )
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                CocktailDetails(
                    state.cocktail,
                    Modifier.padding(bottom = dimensionResource(id = R.dimen.extraLargePadding)),
                    Alignment.CenterHorizontally
                )

            }
        }

        state.isLoading -> {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator()
            }
        }
        state.error.isNotEmpty() -> {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                ErrorButton(errorText = state.error, viewModel)
            }
        }

    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CocktailDetails(
    cocktail: Cocktail,
    modifier: Modifier,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start
) {
    Column(modifier = modifier, horizontalAlignment = horizontalAlignment) {
        Text(text = cocktail.name, style = MaterialTheme.typography.titleMedium)
        Text(
            text = stringResource(id = R.string.catergory) + " " + cocktail.category,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .alpha(0.8f)
                .padding(top = dimensionResource(id = R.dimen.mediumPadding))
        )

        Text(
            text = stringResource(id = R.string.alcoholic) + " " + cocktail.alcoholic,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .alpha(0.8f)
                .padding(top = dimensionResource(id = R.dimen.mediumPadding))
        )
        Text(
            text = stringResource(id = R.string.glass) + " " + cocktail.glass,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .alpha(0.8f)
                .padding(top = dimensionResource(id = R.dimen.mediumPadding))
        )
        GlideImage(
            model = cocktail.imageURL,
            contentDescription = cocktail.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier.padding(top = dimensionResource(id = R.dimen.mediumPadding))
        )
        Text(
            text = stringResource(id = R.string.instructions) + " " + cocktail.instructions,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .alpha(0.8f)
                .padding(top = dimensionResource(id = R.dimen.mediumPadding))
        )
        Text(
            text = stringResource(id = R.string.ingredients) + " "
                    + cocktail.ingredientsMap?.mapNotNull { (key, value) -> key?.let { "${it}: $value" } },
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .alpha(0.8f)
                .padding(top = dimensionResource(id = R.dimen.mediumPadding))
        )

    }
}



