package com.filkom.lazylayout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.filkom.lazylayout.ui.theme.LazyLayoutTheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.material3.CardDefaults

class MainActivity : ComponentActivity() {
    val gadgets = listOf(
        Gadget(1, "Smartphone", "Perangkat komunikasi genggam", R.drawable.smartphone),
        Gadget(2, "Laptop", "Komputer portabel untuk bekerja", R.drawable.laptop),
        Gadget(3, "Tablet", "Perangkat layar sentuh antara hp dan laptop", R.drawable.tablet),
        Gadget(4, "Smartwatch", "Jam tangan pintar pelacak kesehatan", R.drawable.smartwatch),
        Gadget(5, "Earbuds", "Perangkat audio nirkabel", R.drawable.earbuds),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LazyLayoutTheme {
                //GadgetList(gadgets)
                //GadgetGrid(gadgets)
                GadgetListHeader(gadgets)
            }
        }
    }
}

@Composable
fun GadgetItem(gadget: Gadget) {
    val cardColor = when (gadget.name) {
        "Smartphone", "Tablet","Earbuds" -> Color(0xFFF5E6CC)
        else               -> Color(0xFFE0E0E0)
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(containerColor = cardColor)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Image(
                painter = painterResource(id = gadget.image),
                contentDescription = gadget.name,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .padding(8.dp)
            )
            Row(modifier = Modifier.padding(top = 8.dp)) {
                Text(
                    text = gadget.name,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = gadget.description,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.weight(2f)
                )
            }
        }
    }
}

// Komponen List Vertikal
@Composable
fun GadgetList(gadgets: List<Gadget>) {
    LazyColumn(
        contentPadding = PaddingValues(bottom = 16.dp),
        modifier = Modifier.fillMaxSize().padding(top = 50.dp)
    ) {
        items(gadgets) { gadget ->
            GadgetItem(gadget)
        }
    }
}

// Komponen List Vertikal dg sticky header
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GadgetListHeader(gadgets: List<Gadget>) {
    LazyColumn(
        contentPadding = PaddingValues(bottom = 16.dp),
        modifier = Modifier.fillMaxSize().statusBarsPadding()
    ) {
        stickyHeader {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.LightGray)
                    .padding(vertical = 8.dp)
            ) {
                Text(
                    text = "NIM: 245150401111002",
                    modifier = Modifier.fillMaxWidth(),
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "Novrila Aulia Zahrin",
                    modifier = Modifier.fillMaxWidth(),
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Center
                )
            }
        }
        items(gadgets) { gadget ->
            GadgetItem(gadget)
        }
    }
}

@Composable
fun GadgetGrid(gadgets: List<Gadget>) {
    LazyVerticalGrid(
        //columns = GridCells.Fixed(2),
        columns = GridCells.Adaptive(minSize = 150.dp),
        contentPadding = PaddingValues(8.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(gadgets) { gadget ->
            GadgetItem(gadget)
        }
    }
}