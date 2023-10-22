//package com.example.foodapp.utils
//
//import com.example.foodapp.R
//import com.example.foodapp.model.Food
//
//
//object DataDummy {
//    private val image = arrayOf(
//        R.drawable.img_menu_chiken_madu,
//        R.drawable.img_chiken_bakar,
//        R.drawable.img_menu_mienaruto,
//        R.drawable.img_menu_mietelur,
//        R.drawable.img_menu_kentang,
//        R.drawable.img_menu_batagor,
//        R.drawable.img_menu_escream,
//        R.drawable.img_minuman_coffe
//
//    )
//    private val name = arrayOf(
//        "Ayam chiken Madu",
//        "Ayam chiken Bakar",
//        "Mie naruto",
//        "Mie telur",
//        "Kentang",
//        "Batagor",
//        "Escream",
//        "Coffe"
//
//    )
//
//    private val price = arrayOf(
//        50000,
//        45000,
//        40000,
//        30000,
//        20000,
//        20000,
//        30000,
//        30000
//    )
//
//    private val description = arrayOf(
//        "Ayam yang di balulirin dengan madu yang sangat manis dan gyurih dari ayamnya tersebut",
//        "Ayam bakar yang di bakar dengan  kayu jati yang spesial yang buat aroma ayam enak",
//        "Mie naruto adalah mie yang bertema mie yang di ambil dari serial animne naruto",
//        "Mie telur  yang toping telurnya banyak yang buat mie tersebut lezat",
//        "Kentang goren yang di rebuh terlebih dahulu baru di goreng supaya rasa dari kentangnya tidak ilang",
//        "Batagor yang berasal dari ikan tuna pilihan",
//        "escream yang terbuat dari bahan_bahan alami yang buat cita rasanya manis alami",
//        "cofe yang di pilih dari pertania  yang berkualitas tinggi"
//    )
//
//    private val location = arrayOf(
//        "Ayam chiken Madu",
//        "Ayam chiken Bakar",
//        "Mie naruto",
//        "Mie telur",
//        "Kentang",
//        "Batagor",
//        "Escream",
//        "Coffe"
//    )
//
//
//    val listData: ArrayList<Food>
//        get() {
//            val list = arrayListOf<Food>()
//            for (position in name.indices) {
//                list.add(
//                    Food(
//                        image[position],
//                        name[position],
//                        price[position],
//                        description[position],
//                        location[position]
//                    )
//                )
//            }
//            return list
//        }
//}