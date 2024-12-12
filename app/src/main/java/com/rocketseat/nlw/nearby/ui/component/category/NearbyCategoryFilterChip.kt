package com.rocketseat.nlw.nearby.ui.component.category

import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rocketseat.nlw.nearby.data.model.Category
import com.rocketseat.nlw.nearby.ui.theme.Gray300
import com.rocketseat.nlw.nearby.ui.theme.Typography
import com.rocketseat.nlw.nearby.ui.theme.Gray400
import com.rocketseat.nlw.nearby.ui.theme.GreenBase

@Composable
fun NearbyCategoryFilterChip(
    modifier: Modifier = Modifier,
    category: Category,
    isSelected: Boolean,
    onClick: (isSelect: Boolean) -> Unit
) {
    FilterChip(
        modifier = modifier
            .padding(2.dp)
            .heightIn(min = 36.dp),
        elevation = FilterChipDefaults.filterChipElevation( elevation = 8.dp),
        leadingIcon = {
            category.icon?.let {
                Icon(
                    modifier = modifier.size(16.dp),
                    painter = painterResource(id = it),
                    tint = selectedColor(isSelected),
                    contentDescription = "Ícone de Filtro de Categoria"
                )
            }
        },
        border = FilterChipDefaults.filterChipBorder(
            enabled = false,
            selected = isSelected,
            disabledBorderColor = Gray300,
            borderWidth = 1.dp,
            selectedBorderWidth = 0.dp
        ),
        selected = isSelected,
        onClick = { onClick(!isSelected) },
        label = {
            Text(
                text = category.name,
                style = Typography.bodyMedium,
                color = selectedColor(isSelected)
            )
        },
        colors = FilterChipDefaults.filterChipColors(
            containerColor = White,
            selectedContainerColor = GreenBase
        )
    )
}

fun selectedColor( isSelected: Boolean) = if (isSelected) White else Gray400

@Preview
@Composable
private fun NearbyCategoryFilterChipSelectedPreview() {
    NearbyCategoryFilterChip(
        category = Category("1","Compras"),
        isSelected = true,
        onClick = { }
    )
}

@Preview
@Composable
private fun NearbyCategoryFilterChipNotSelectedPreview() {
    NearbyCategoryFilterChip(
        category = Category("2","Cinema"),
        isSelected = false,
        onClick = { }
    )
}

@Preview
@Composable
private fun NearbyCategoryFilterChipUnknownCategoryPreview() {
    NearbyCategoryFilterChip(
        category = Category("3","Desconhecido"),
        isSelected = false,
        onClick = { }
    )
}