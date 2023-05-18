package by.krokam.belarus_bible.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import by.krokam.belarus_bible.databinding.FragmentTextBinding

class TextFragment : Fragment(){

    var text  = MutableLiveData<String>()
    private lateinit var mBinding: FragmentTextBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = FragmentTextBinding.inflate(layoutInflater)

        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        text.observe(viewLifecycleOwner) {
            mBinding.text.text = it!!
        }
    }
}