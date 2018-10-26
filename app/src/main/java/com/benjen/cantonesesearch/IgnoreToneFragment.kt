package com.benjen.cantonesesearch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_ignore_tone.*

/**
 *  @Author Benjen April
 *  @Date 2018/10/25
 *
 */
class IgnoreToneFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_ignore_tone, null);
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        wvWords.settings.javaScriptEnabled = true
        btnGo.setOnClickListener {
            if (edt_finals.text.isEmpty() && edt_initals.text.isEmpty()) {
                Toast.makeText(activity, "查询不能为空", Toast.LENGTH_LONG).show()
                wvWords.loadUrl("file:///android_asset/net_error.html")
                return@setOnClickListener
            }
            if (edt_initals.text.isEmpty()) {
                wvWords.loadUrl(AppConstant.SIMILAR_WORDS_SEARCH_URL + "s1=&s2=" + edt_finals.text)
                return@setOnClickListener
            }
            if (edt_finals.text.isEmpty()) {
                wvWords.loadUrl(AppConstant.SIMILAR_WORDS_SEARCH_URL + "s1=&s2=" + edt_initals.text)
                return@setOnClickListener
            }
            wvWords.loadUrl("${AppConstant.SIMILAR_WORDS_SEARCH_URL}s1=${edt_initals.text}&s2=${edt_finals.text}")
        }
    }


    companion object {
        fun newInstance(): IgnoreToneFragment {
            return IgnoreToneFragment().apply {
                arguments = Bundle()
            }
        }
    }

}