package com.softdream.drunkersapp.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat


// Material 3 color schemes
private val drunkersApppDarkColorScheme = darkColorScheme(
    primary = drunkersAppDarkPrimary,
    onPrimary = drunkersAppDarkOnPrimary,
    primaryContainer = drunkersAppDarkPrimaryContainer,
    onPrimaryContainer = drunkersAppDarkOnPrimaryContainer,
    inversePrimary = drunkersAppDarkPrimaryInverse,
    secondary = drunkersAppDarkSecondary,
    onSecondary = drunkersAppDarkOnSecondary,
    secondaryContainer = drunkersAppDarkSecondaryContainer,
    onSecondaryContainer = drunkersAppDarkOnSecondaryContainer,
    tertiary = drunkersAppDarkTertiary,
    onTertiary = drunkersAppDarkOnTertiary,
    tertiaryContainer = drunkersAppDarkTertiaryContainer,
    onTertiaryContainer = drunkersAppDarkOnTertiaryContainer,
    error = drunkersAppDarkError,
    onError = drunkersAppDarkOnError,
    errorContainer = drunkersAppDarkErrorContainer,
    onErrorContainer = drunkersAppDarkOnErrorContainer,
    background = drunkersAppDarkBackground,
    onBackground = drunkersAppDarkOnBackground,
    surface = drunkersAppDarkSurface,
    onSurface = drunkersAppDarkOnSurface,
    inverseSurface = drunkersAppDarkInverseSurface,
    inverseOnSurface = drunkersAppDarkInverseOnSurface,
    surfaceVariant = drunkersAppDarkSurfaceVariant,
    onSurfaceVariant = drunkersAppDarkOnSurfaceVariant,
    outline = drunkersAppDarkOutline
)

private val drunkersAppLightColorScheme = lightColorScheme(
    primary = drunkersAppLightPrimary,
    onPrimary = drunkersAppLightOnPrimary,
    primaryContainer = drunkersAppLightPrimaryContainer,
    onPrimaryContainer = drunkersAppLightOnPrimaryContainer,
    inversePrimary = drunkersAppLightPrimaryInverse,
    secondary = drunkersAppLightSecondary,
    onSecondary = drunkersAppLightOnSecondary,
    secondaryContainer = drunkersAppLightSecondaryContainer,
    onSecondaryContainer = drunkersAppLightOnSecondaryContainer,
    tertiary = drunkersAppLightTertiary,
    onTertiary = drunkersAppLightOnTertiary,
    tertiaryContainer = drunkersAppLightTertiaryContainer,
    onTertiaryContainer = drunkersAppLightOnTertiaryContainer,
    error = drunkersAppLightError,
    onError = drunkersAppLightOnError,
    errorContainer = drunkersAppLightErrorContainer,
    onErrorContainer = drunkersAppLightOnErrorContainer,
    background = drunkersAppLightBackground,
    onBackground = drunkersAppLightOnBackground,
    surface = drunkersAppLightSurface,
    onSurface = drunkersAppLightOnSurface,
    inverseSurface = drunkersAppLightInverseSurface,
    inverseOnSurface = drunkersAppLightInverseOnSurface,
    surfaceVariant = drunkersAppLightSurfaceVariant,
    onSurfaceVariant = drunkersAppLightOnSurfaceVariant,
    outline = drunkersAppLightOutline
)

@Composable
fun DrunkersAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val drunkersAppColorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> drunkersApppDarkColorScheme
        else -> drunkersAppLightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = drunkersAppColorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = drunkersAppColorScheme,
        typography = drunkersAppTypography,
        shapes = shapes,
        content = content
    )
}

