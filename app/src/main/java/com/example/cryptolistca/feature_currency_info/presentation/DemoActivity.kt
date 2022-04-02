package com.example.cryptolistca.feature_currency_info.presentation

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.example.cryptolistca.R
import com.example.cryptolistca.databinding.ActivityDemoBinding
import com.example.cryptolistca.feature_currency_info.presentation.currency_list.CurrencyListFragment
import com.example.cryptolistca.feature_currency_info.presentation.currency_list.CurrencyListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class DemoActivity : AppCompatActivity() {

    private val viewModel by viewModel<CurrencyListViewModel>()
    private lateinit var binding: ActivityDemoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding =
            DataBindingUtil.setContentView(this, R.layout.activity_demo)

        binding.lifecycleOwner = this

        binding.viewmodel = viewModel

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<CurrencyListFragment>(R.id.fragment_container_view)
            }
        }

        setupObserver()
    }

    private fun setupObserver() {
        viewModel.initLoadingUI.observe(this) {
            if (it) {
                binding.loadBtn.isClickable = false
                binding.sortBtn.isClickable = false
            } else {
                binding.loadBtn.isClickable = true
                binding.sortBtn.isClickable = true
            }
        }
    }
}