package com.largeblueberry.bitquest.feature_FieldSelection

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.largeblueberry.bitquest.feature_FieldSelection.ui.FieldSelectionViewModel
import com.largeblueberry.bitquest.feature_main.util.BitQuestColors
import com.largeblueberry.bitquest.ui.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FieldSelectionScreen(
    navController: NavController,
    viewModel: FieldSelectionViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.navigationEvent.collect { fieldId ->
            when(fieldId) {
                2 -> navController.navigate("${Screen.QuizDetail.route}?category=Android")
                3 -> navController.navigate("${Screen.QuizDetail.route}?category=Git")
            }
        }
    }

    Scaffold(
        containerColor = BitQuestColors.White,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "테마 선택",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = BitQuestColors.TextDark
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            painter = painterResource(id = android.R.drawable.ic_menu_revert),
                            contentDescription = "뒤로가기",
                            tint = BitQuestColors.TextDark
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = BitQuestColors.White
                )
            )
        }
    ) { innerPadding ->
        when {
            uiState.isLoading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = BitQuestColors.PrimaryGreen)
                }
            }

            uiState.errorMessage != null -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = uiState.errorMessage!!,
                        color = Color.Red
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = { viewModel.loadField() },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = BitQuestColors.PrimaryGreen
                        )
                    ) {
                        Text("다시 시도")
                    }
                }
            }

            else -> {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(uiState.field) { field ->
                        FieldCard(
                            field = field,
                            onClick = { viewModel.onFieldClick(field) }
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ThemeSelectionScreenPreview() {
    MaterialTheme {
        FieldSelectionScreen(navController = rememberNavController())
    }
}