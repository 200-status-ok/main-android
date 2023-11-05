package com.example.haminjast.data.model

import com.example.haminjast.R

data class Ad(
    val title: String,
    val desc: String,
    val status: AdStatus,
    val imgUrl: String,
    val date: String,
    val location: String
)

enum class AdStatus(val stringRes:Int) {
    LOST(R.string.lost),
    FOUND(R.string.found),
}

val fakeAdList = mutableListOf<Ad>().apply {
    add(
        Ad(
            "لپتاپ",
            "یک لپتاپ ایسوس سفید دیشب در محدوده ی فلکه اول تهرانپارس گم شده.",
            AdStatus.LOST,
            "https://upload.wikimedia.org/wikipedia/commons/thumb/3/3c/WalletMpegMan.jpg/500px-WalletMpegMan.jpg",
            "دیروز", "ستارخان"
        )
    )
    add(
        Ad(
            "دوچرخه",
            "یک دوچرخه برقی آبی گم شده.",
            AdStatus.LOST,
            "https://upload.wikimedia.org/wikipedia/commons/thumb/2/25/HP_Pavilion_zv6000_series.jpg/440px-HP_Pavilion_zv6000_series.jpg",
            "دیروز", "ستارخان"
        )
    )
    add(
        Ad(
            "لپتاپ",
            "یک لپتاپ ایسوس سفید دیشب در محدوده ی فلکه اول تهرانپارس گم شده.",
            AdStatus.LOST,
            "https://storage.mixin.ir/9927609931-media/product-images/1686243801_81112.jpg",
            "دیروز", "تهرانپارس"
        )
    )
    add(
        Ad(
            "لپتاپ",
            "یک لپتاپ ایسوس سفید دیشب در محدوده ی فلکه اول تهرانپارس پیدا شده.",
            AdStatus.FOUND,
            "https://upload.wikimedia.org/wikipedia/commons/thumb/2/25/HP_Pavilion_zv6000_series.jpg/440px-HP_Pavilion_zv6000_series.jpg",
            "دیروز", "تهرانپارس"
        )
    )
    add(
        Ad(
            "لپتاپ",
            "یک لپتاپ ایسوس سفید دیشب در محدوده ی فلکه اول تهرانپارس پیدا شده.",
            AdStatus.FOUND,
            "https://storage.mixin.ir/9927609931-media/product-images/1686243801_81112.jpg",
            "دیروز", "تهرانپارس"
        )
    )
    add(
        Ad(
            "لپتاپ",
            "یک لپتاپ ایسوس سفید دیشب در محدوده ی فلکه اول تهرانپارس گم شده.",
            AdStatus.LOST,
            "https://upload.wikimedia.org/wikipedia/commons/thumb/2/25/HP_Pavilion_zv6000_series.jpg/440px-HP_Pavilion_zv6000_series.jpg",
            "دیروز", "ستارخان"
        )
    )
    add(
        Ad(
            "لپتاپ",
            "یک لپتاپ ایسوس سفید دیشب در محدوده ی فلکه اول تهرانپارس پیدا شده.",
            AdStatus.FOUND,
            "https://upload.wikimedia.org/wikipedia/commons/thumb/2/25/HP_Pavilion_zv6000_series.jpg/440px-HP_Pavilion_zv6000_series.jpg",
            "دیروز", "تهرانپارس"
        )
    )
    add(
        Ad(
            "لپتاپ",
            "یک لپتاپ ایسوس سفید دیشب در محدوده ی فلکه اول تهرانپارس گم شده.",
            AdStatus.LOST,
            "https://storage.mixin.ir/9927609931-media/product-images/1686243801_81112.jpg",
            "دیروز", "ستارخان"
        )
    )
}