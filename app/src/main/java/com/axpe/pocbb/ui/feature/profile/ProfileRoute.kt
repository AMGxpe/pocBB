package com.axpe.pocbb.ui.feature.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.axpe.pocbb.R

@Composable
fun ProfileRoute() {
    ProfileScreen(Modifier.fillMaxSize())
}

@Composable
fun ProfileScreen(modifier: Modifier = Modifier) {
    Box(modifier) {
        var offset = 0.dp
        images.shuffled().subList(0, 5).forEach { url ->
            Avatar(Modifier.offset(offset)) {
                AsyncImage(
                    model = url,
                    contentDescription = null,
                    placeholder = painterResource(R.drawable.baseline_boy_24),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(50.dp),
                    clipToBounds = true
                )
            }
            offset += 50.dp / 2
        }
    }
}

@Composable
fun Avatar(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
    Box(modifier
        .drawWithContent {
            drawContent()
            drawCircle(
                Color.Black,
                size.minDimension / 2,
                size.center,
                style = Stroke(2F)
            )
        }
        .clip(CircleShape)) {
        content()
    }
}

private val images: List<String> = listOf(
    "https://images.dog.ceo/breeds/shihtzu/n02086240_1078.jpg",
    "https://images.dog.ceo/breeds/germanshepherd/n02106662_4059.jpg",
    "https://images.dog.ceo/breeds/chippiparai-indian/Indian-Chippiparai.jpg",
    "https://images.dog.ceo/breeds/poodle-medium/WhatsApp_Image_2022-08-06_at_4.48.38_PM.jpg",
    "https://images.dog.ceo/breeds/hound-afghan/n02088094_3500.jpg",
    "https://images.dog.ceo/breeds/lhasa/n02098413_7441.jpg",
    "https://images.dog.ceo/breeds/mountain-bernese/n02107683_1542.jpg",
    "https://images.dog.ceo/breeds/segugio-italian/n02090722_001.jpg",
    "https://images.dog.ceo/breeds/otterhound/n02091635_699.jpg",
    "https://images.dog.ceo/breeds/spitz-japanese/beet-005.jpg",
    "https://images.dog.ceo/breeds/germanshepherd/n02106662_4021.jpg",
    "https://images.dog.ceo/breeds/basenji/n02110806_1102.jpg",
    "https://images.dog.ceo/breeds/terrier-dandie/n02096437_91.jpg",
    "https://images.dog.ceo/breeds/puggle/IMG_112010.jpg",
    "https://images.dog.ceo/breeds/germanshepherd/n02106662_21432.jpg",
    "https://images.dog.ceo/breeds/chihuahua/n02085620_7440.jpg",
    "https://images.dog.ceo/breeds/chihuahua/black_chihuahua.jpg"
)