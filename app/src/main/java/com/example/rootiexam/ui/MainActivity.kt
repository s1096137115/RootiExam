package com.example.rootiexam.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rootiexam.R
import com.example.rootiexam.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private val adapter by lazy { MainAdapter() }

    private val viewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initView()
        observeViewModel()
        viewModel.initData()
    }

    private fun initView() {
        binding.recyclerView.adapter = adapter
        val manager = LinearLayoutManager(this@MainActivity)
        binding.recyclerView.layoutManager = manager
        binding.recyclerView.addItemDecoration(DividerItemDecoration(this@MainActivity, manager.orientation))
    }

    private fun observeViewModel() {
        viewModel.list.observe(this@MainActivity) {
            adapter.submitList(it)
        }
    }
}