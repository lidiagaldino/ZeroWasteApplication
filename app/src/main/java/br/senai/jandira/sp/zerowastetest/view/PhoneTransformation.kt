package br.senai.jandira.sp.zerowastetest.view

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class PhoneTransformation : VisualTransformation {

    override fun filter(text: AnnotatedString): TransformedText {
        return dateFilter(text)
    }

    private fun dateFilter(text: AnnotatedString): TransformedText {
        TODO("Not yet implemented")
    }

}
