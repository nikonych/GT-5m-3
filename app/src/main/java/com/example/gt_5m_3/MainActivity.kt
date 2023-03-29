package com.example.gt_5m_3

import android.os.Bundle
import android.util.Log
import android.view.inputmethod.EditorInfo
import android.widget.TextView.OnEditorActionListener
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.gt_5m_3.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    var page = 1
    lateinit var adapter: ImageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter = ImageAdapter(arrayListOf())
        binding.rwImages.adapter = adapter
        initChange()
    }

    private fun initChange() {
        binding.etSearch.setOnEditorActionListener(OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                //Clear focus here from edittext
                binding.etSearch.clearFocus()
                page = 1
                adapter.cleanList()
                if (isTextEmpty())
                    requestImage()

            }
            false
        })


        binding.rwImages.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1)) {
                    Toast.makeText(this@MainActivity, "Wait", Toast.LENGTH_SHORT).show()
                    ++page
                    if (isTextEmpty())
                        requestImage()

                }
            }
        })

    }

    private fun requestImage() {
        PixaService().api.getImage(binding.etSearch.text.toString(), page = page)
            .enqueue(object : Callback<PixaModel> {
                override fun onResponse(call: Call<PixaModel>, response: Response<PixaModel>) {
                    if (response.isSuccessful) {
                        adapter.addImage(response.body()!!.hits)
                    }
                }

                override fun onFailure(call: Call<PixaModel>, t: Throwable) {
                    Log.e("gg", t.message.toString())
                }
            })
    }

    private fun isTextEmpty() = binding.etSearch.text.toString().isNotEmpty()
}