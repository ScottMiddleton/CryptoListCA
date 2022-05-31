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
import com.example.cryptolistca.feature_currency_info.data.local.entity.CurrencyInfoEntity
import com.example.cryptolistca.feature_currency_info.domain.model.CurrencyInfo
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

        provideCurrencyDataSet()

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<CurrencyListFragment>(R.id.fragment_container_view)
            }
        }

        setupObserver()
    }

    private fun provideCurrencyDataSet() {
        val currencyInfoList = mutableListOf<CurrencyInfo>()

        currencyInfoList.add(CurrencyInfo("BTC", "Bitcoin", "BTC"))
        currencyInfoList.add(CurrencyInfo("ETH", "Ethereum", "ETH"))
        currencyInfoList.add(CurrencyInfo("XRP", "XRP", "XRP"))
        currencyInfoList.add(CurrencyInfo("BCH", "Bitcoin Cash", "BCH"))
        currencyInfoList.add(CurrencyInfo("LTC", "Litecoin", "LTC"))
        currencyInfoList.add(CurrencyInfo("EOS", "EOS", "EOS"))
        currencyInfoList.add(CurrencyInfo("BNB", "Binance Coin", "BNB"))
        currencyInfoList.add(CurrencyInfo("LINK", "Chainlink", "LINK"))
        currencyInfoList.add(CurrencyInfo("NEO", "NEO", "NEO"))
        currencyInfoList.add(CurrencyInfo("ETC", "Ethereum Classic", "ETC"))
        currencyInfoList.add(CurrencyInfo("ONT", "Ontology", "ONT"))
        currencyInfoList.add(CurrencyInfo("CRO", "Crypto.com Chain", "CRO"))
        currencyInfoList.add(CurrencyInfo("CUC", "Cucumber", "CUC"))
        currencyInfoList.add(CurrencyInfo("USDC", "USD Coin", "USDC"))

        viewModel.insertCurrencyInfo(currencyInfoList)
    }

    private fun setupObserver() {
        viewModel.initLoadingUI.observe(this) {
            if (it) {
                binding.loadingPb.visibility = VISIBLE
            } else {
                binding.loadingPb.visibility = GONE
            }
        }
    }
}