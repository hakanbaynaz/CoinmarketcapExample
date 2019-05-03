package com.example.coinmarketcapexample.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.coinmarketcapexample.R
import com.example.coinmarketcapexample.fragment.CoinDetailFragment
import com.example.coinmarketcapexample.fragment.CoinListFragment
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasSupportFragmentInjector, CoinListFragment.OnCoinClickListener {

    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    private val mCoinListFragment = CoinListFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, mCoinListFragment)
            .commit()
    }

    override fun onCoinClick(coinId: Int) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.fragmentContainer, CoinDetailFragment.newInstance(coinId)).addToBackStack(null)
        fragmentTransaction.hide(mCoinListFragment)
        fragmentTransaction.commit()

    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.show(mCoinListFragment).commit()
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
        } else {
            super.onBackPressed()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return fragmentDispatchingAndroidInjector
    }

}
