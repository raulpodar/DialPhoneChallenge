package com.sky.dialphonechallenge.presentation


import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.sky.dialphonechallenge.R
import com.sky.dialphonechallenge.databinding.MainFragmentBinding
import com.sky.dialphonechallenge.presentation.uiModels.DialPhoneNumberUiModel
import com.sky.dialphonechallenge.presentation.utils.CustomOnClickListener
import com.sky.dialphonechallenge.presentation.utils.DebouncedOnClickListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val viewModel: MainViewModel by viewModels()
    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.uiModelLiveData.observe(viewLifecycleOwner) {
            handleShowDialButtonEvent(it)
            handleButtonEvent(it)
        }

        onUserTyping()
    }

    private fun handleButtonEvent(uiModel: DialPhoneNumberUiModel?) {
        binding.textView.text=uiModel?.currentTypedPhoneNumber
    }

    private fun handleShowDialButtonEvent(uiModel: DialPhoneNumberUiModel?) {
        Log.e("sizee",uiModel?.dialedPhoneNumbers?.size.toString())
        uiModel?.let {
            if (it.shouldShowDialButton ) {
                var dialButton=binding.dialButton
                dialButton.visibility=View.VISIBLE
            }
            else {
                var dialButton=binding.dialButton
                dialButton.visibility=View.GONE
            }
//            (view?.findViewById(R.id.message) as? TextView)?.text = it.launchCountMessage
        }

    }

    private fun onUserTyping(){
        binding.button.setOnClickListener(object : CustomOnClickListener(binding,binding.button,viewModel){})
        binding.button2.setOnClickListener(object : CustomOnClickListener(binding,binding.button2,viewModel){})
        binding.button3.setOnClickListener(object : CustomOnClickListener(binding,binding.button3,viewModel){})
        binding.button4.setOnClickListener(object : CustomOnClickListener(binding,binding.button4,viewModel){})
        binding.button5.setOnClickListener(object : CustomOnClickListener(binding,binding.button5,viewModel){})
        binding.button6.setOnClickListener(object : CustomOnClickListener(binding,binding.button6,viewModel){})
        binding.button7.setOnClickListener(object : CustomOnClickListener(binding,binding.button7,viewModel){})
        binding.button8.setOnClickListener(object : CustomOnClickListener(binding,binding.button8,viewModel){})
        binding.button9.setOnClickListener(object : CustomOnClickListener(binding,binding.button9,viewModel){})
        binding.button0.setOnClickListener(object : CustomOnClickListener(binding,binding.button0,viewModel){})
        binding.dialButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                viewModel.userHasDialed(binding.textView.text.toString())
            }

        })
        binding.deleteButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                if(binding.textView.text.length>0){
                    viewModel.userHasDeleted(binding.textView.text.toString())

                }
            }

        })

    }



    override fun onStart() {
        super.onStart()
        viewModel.onAppLaunched()
    }
}
