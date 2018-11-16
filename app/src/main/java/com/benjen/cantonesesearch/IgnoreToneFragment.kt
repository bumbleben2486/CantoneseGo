package com.benjen.cantonesesearch

import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
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
        edt_finals.imeOptions = EditorInfo.IME_ACTION_SEND
        edt_finals.setOnEditorActionListener { v, actionId, event ->

            if (actionId == EditorInfo.IME_ACTION_SEND) {
                doTheSearchWork()
            }
            false
        }
        btnGo.setOnClickListener {
            doTheSearchWork()
        }
    }

    private fun doTheSearchWork() {
        var scemeFinals = edt_finals.text.toString()
        scemeFinals = scemeFinals.toLowerCase()
        var scemeInitals = edt_initals.text.toString()
        scemeInitals = scemeInitals.toLowerCase()
        if (edt_finals.text.isEmpty() && edt_initals.text.isEmpty()) {
            Toast.makeText(activity, "查询不能为空", Toast.LENGTH_LONG).show()
            wvWords.loadUrl("file:///android_asset/net_error.html")
            return
        }
        if (edt_initals.text.isEmpty()) {
            wvWords.loadUrl(AppConstant.SIMILAR_WORDS_SEARCH_URL + "s1=&s2=" + scemeFinals)
            return
        }
        if (edt_finals.text.isEmpty()) {
            wvWords.loadUrl(AppConstant.SIMILAR_WORDS_SEARCH_URL + "s1=&s2=" + scemeInitals)
            return
        }
        wvWords.loadUrl("${AppConstant.SIMILAR_WORDS_SEARCH_URL}s1=$scemeInitals&s2=$scemeFinals")
    }


    companion object {
        fun newInstance(): IgnoreToneFragment {
            return IgnoreToneFragment().apply {
                arguments = Bundle()
            }
        }
    }

}