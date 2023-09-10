package com.example.multiplatform.android

import com.example.multiplatform.android.Const.ALL
import com.example.multiplatform.android.Const.BENEFIT
import com.example.multiplatform.android.Const.HOME
import com.example.multiplatform.android.Const.STOCK
import com.example.multiplatform.android.Const.TOSSPAY

sealed class BottomNavItem(
    val title: Int,
    val icon: Int,
    val screenRoute: String
) {
    object HomeFragment: BottomNavItem(R.string.home_fragment, R.drawable.tab1, HOME)
    object BenefitFragment: BottomNavItem(R.string.benefit_fragment, R.drawable.tab2, BENEFIT)
    object TossPayFragment: BottomNavItem(R.string.toss_pay_fragment, R.drawable.tab3, TOSSPAY)
    object StockFragment: BottomNavItem(R.string.stock_fragment, R.drawable.tab4, STOCK)
    object AllFragment: BottomNavItem(R.string.all_fragment, R.drawable.tab5, ALL)
}