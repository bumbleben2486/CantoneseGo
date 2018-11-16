package com.benjen.cantonesesearch

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_specail_word.*
import taobe.tec.jcc.JChineseConvertor
import java.io.IOException

/**
 *  @Author Benjen April
 *  @Date 2018/10/26
 *
 */
class SpecailWordFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_specail_word, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        edtWord.imeOptions = EditorInfo.IME_ACTION_SEND
        edtWord.setOnEditorActionListener { v, actionId, event ->

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
        var searchWord = edtWord.text.toString()
        if (searchWord.isEmpty()) {
            Toast.makeText(activity, "查询不能为空", Toast.LENGTH_LONG).show()
            return
        }
        if (searchWord.length != 1) {
            Toast.makeText(activity, "一次只能查询一个字", Toast.LENGTH_LONG).show()
            return
        }
        Log.d("helloTAG", Utils.unicodeToBig5(searchWord))
        searchWord = change(searchWord)
        searchWord = Utils.unicodeToBig5(searchWord)
        val url = "${AppConstant.SPECAIL_WORD_SEARCH_URL}$searchWord"
        //            Toast.makeText(activity, url, Toast.LENGTH_LONG).show()
        wvShow.loadUrl(url)
    }

    /**
     * 简体转繁体
     */
    fun change(changeText: String): String {
        try {
            var jChineseConvertor = JChineseConvertor
                    .getInstance()
            return jChineseConvertor.s2t(changeText)

        } catch (e: IOException) {
            e.printStackTrace()
        }
        return changeText
    }

    /**
     * 转百分号编码
     */
    fun toUtf8String(s: String): String {
        val sb = StringBuffer()
        for (i in 0 until s.length) {
            val c = s[i]
            if (c.toInt() in 0..255) {
                sb.append(c)
            } else {
                var b: ByteArray
                try {
                    b = c.toString().toByteArray(charset("utf-8"))
                } catch (ex: Exception) {
                    println(ex)
                    b = ByteArray(0)
                }

                for (j in b.indices) {
                    var k = b[j].toInt()
                    if (k < 0)
                        k += 256
                    sb.append("%" + Integer.toHexString(k).toUpperCase())
                }
            }
        }
        return sb.toString()
    }


    companion object {
        fun newInstance(): SpecailWordFragment {
            return SpecailWordFragment().apply { arguments = Bundle() }
        }
    }
}