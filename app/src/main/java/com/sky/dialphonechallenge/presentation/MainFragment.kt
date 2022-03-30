package com.sky.dialphonechallenge.presentation


import CustomAdapter
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.fragment.app.viewModels
import com.sky.dialphonechallenge.R
import com.sky.dialphonechallenge.databinding.MainFragmentBinding
import com.sky.dialphonechallenge.presentation.uiModels.DialPhoneNumberUiModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {


    companion object {
        fun newInstance() = MainFragment()
    }

    private val viewModel: MainViewModel by viewModels()
    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter:CustomAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        val view = binding.root

        val spinner: Spinner = binding.spinner
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            container!!.context,
            R.array.options_numbers,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.uiModelLiveData.observe(viewLifecycleOwner) {
            handleShowDialButtonEvent(it)
            handleButtonEvent(it)
            Log.v("list", it.dialedPhoneNumbers.size.toString())
            binding.phoneNumberList.adapter=CustomAdapter(it.dialedPhoneNumbers)
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
        val listener= object : View.OnClickListener {

            override fun onClick(view: View){
                var textView: TextView =binding.textView
                var newText=textView.text.toString()+view?.getTag()
                Log.v("tagText",newText)
                viewModel.userHasTyped(newText)
            }
        }
        binding.button.setOnClickListener(listener)
        binding.button2.setOnClickListener(listener)
        binding.button3.setOnClickListener(listener)
        binding.button4.setOnClickListener(listener)
        binding.button5.setOnClickListener(listener)
        binding.button6.setOnClickListener(listener)
        binding.button7.setOnClickListener(listener)
        binding.button8.setOnClickListener(listener)
        binding.button9.setOnClickListener(listener)
        binding.button0.setOnClickListener(listener)

        binding.dialButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                viewModel.userHasDialed(binding.textView.text.toString())
            }

        })
        binding.deleteButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {

                    viewModel.userHasDeleted(binding.textView.text.toString())

            }

        })

    }



    override fun onStart() {
        super.onStart()
        viewModel.onAppLaunched()
    }
}
