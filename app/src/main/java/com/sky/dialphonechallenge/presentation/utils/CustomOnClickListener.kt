package com.sky.dialphonechallenge.presentation.utils

import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.sky.dialphonechallenge.databinding.MainFragmentBinding
import com.sky.dialphonechallenge.presentation.MainViewModel

open class CustomOnClickListener(private val viewBinding:MainFragmentBinding,
                                 private val buttonClicked:Button,
                                 private val model:MainViewModel
                            ) : View.OnClickListener {
    override fun onClick(p0: View?) {
        Log.v(buttonClicked.text.toString(),p0?.id.toString())
        var textView: TextView =viewBinding.textView
        var newText=textView.text.toString()+buttonClicked.text
        model.userHasTyped(newText)
    }
}