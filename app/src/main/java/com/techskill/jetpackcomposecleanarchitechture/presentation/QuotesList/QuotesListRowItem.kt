package com.techskill.jetpackcomposecleanarchitechture.presentation.QuotesList

import android.content.ActivityNotFoundException
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.techskill.jetpackcomposecleanarchitechture.domain.model.Quote

@Composable
fun QuotesListItem(quote: Quote, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .padding(2.dp)
            .fillMaxWidth()
            .background(color = Color.Blue, shape = RoundedCornerShape(10.dp))

    ) {
        Row {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(7.dp)
                    .weight(1f)
            ) {
                Text(text = quote.quote, fontSize = 18.sp, color = Color.White)
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "~${quote.author}",
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 22.sp,
                    color = Color.White,
                    modifier = modifier
                        .align(Alignment.End)
                        .padding(4.dp)
                )
            }
            Icon(
                tint = Color(0xff2abf52),
                imageVector = Icons.Default.Share,
                contentDescription = "share",
                modifier = Modifier
                    .padding(horizontal = 10.dp, vertical = 20.dp)
                    .size(30.dp)
                    .clickable {
                        val sendIntent = Intent().apply {
                            action = Intent.ACTION_SEND
                            putExtra(Intent.EXTRA_TEXT, "${quote.quote}\n~${quote.author}")
                            type = "text/plain"
                        }
                        try {
                            context.startActivity(sendIntent)
                        } catch (e: ActivityNotFoundException) {
                            Toast.makeText(context,"Something went wrong!!",Toast.LENGTH_SHORT).show()
                        }



                    },
            )
        }

    }

}