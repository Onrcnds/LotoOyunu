package com.example.lotooyunu


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.random.Random

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LottoGame() {
    var bankoSayi1 by remember { mutableStateOf(TextFieldValue()) }
    var bankoSayi2 by remember { mutableStateOf(TextFieldValue()) }
    var secilenNumaralar by remember { mutableStateOf<List<Int>>(emptyList()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TextField(
            value = bankoSayi1,
            onValueChange = {
                bankoSayi1 = it
            },
            label = { Text("Birinci Banko Sayı") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        TextField(
            value = bankoSayi2,
            onValueChange = {
                bankoSayi2 = it
            },
            label = { Text("İkinci Banko Sayı (Opsiyonel)") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        Button(
            onClick = {
                val bankoNumara1 = bankoSayi1.text.toIntOrNull()
                val bankoNumara2 = bankoSayi2.text.toIntOrNull()
                if (bankoNumara1 != null) {
                    secilenNumaralar = List(6 - (if (bankoNumara2 != null) 2 else 1)) {
                        var rastgeleSayi: Int
                        do {
                            rastgeleSayi = Random.nextInt(1, 50)
                        } while (rastgeleSayi == bankoNumara1 || rastgeleSayi == bankoNumara2)
                        rastgeleSayi
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text("Oyunu Başlat")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Seçilen Numaralar: ${listOfNotNull(bankoSayi1.text.toIntOrNull(), bankoSayi2.text.toIntOrNull()) + secilenNumaralar}",
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}

@Preview
@Composable
fun LottoGamePreview() {
    LottoGame()
}