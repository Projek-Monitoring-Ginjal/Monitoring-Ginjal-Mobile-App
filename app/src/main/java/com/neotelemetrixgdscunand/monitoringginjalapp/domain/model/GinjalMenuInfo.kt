package com.neotelemetrixgdscunand.monitoringginjalapp.domain.model

import com.neotelemetrixgdscunand.monitoringginjalapp.R

data class MenuInfo(
    val title: String,
    val description: String,
    val bulletPoints: List<String> = emptyList(),
    val imageResId: Int
)


fun getGinjalMenuInfo(title: String): MenuInfo {
    return when (title) {
        "Pengertian gagal ginjal" -> MenuInfo(
            title = "Pengertian gagal ginjal",
            description = "Gagal ginjal kronis adalah menurunnya fungsi ginjal yang terjadi secara tiba-tiba, umumnya terjadi selama beberapa jam sampai beberapa hari dan mengakibatkan terjadinya gangguan pada cairan, elektrolit dan asam basa pada tubuh.",
            imageResId = R.drawable.doctor_image
        )
        "Fungsi ginjal normal" -> MenuInfo(
            title = "Fungsi ginjal normal",
            description = "Fungsi ginjal normal adalah menyaring zat sisa metabolisme tubuh. Jika zat ini tidak dikeluarkan akan menyebabkan penumpukan racun tubuh yang berbahaya bagi kesehatan. ",
            imageResId = R.drawable.doctor_image2
        )
        "Penyebab gagal ginjal kronis" -> MenuInfo(
            title = "Penyebab gagal ginjal kronis",
            description = "Penyebab gagal ginjal kronis bermacam-macam, namun kebanyakan disebabkan penyakit jangka panjang seperti hipertensi dan diabetes. Selain itu gagal ginjal juga disebabkan oleh  pola hidup yang tidak sehat seperti :",
            bulletPoints = listOf(
                "Banyak mengkonsumsi makanan tinggi gula, garam dan lemak jenuh. ",
                "Asupan cairan yang kurang dan menyebabkan dehidrasi.",
                "Penyakit Hipertensi (tekanan darah tinggi) dan Diabetes (kencing manis) yang tidak terkontrol"
            ),
            imageResId = R.drawable.doctor_image3
        )
        "Tanda dan gejala" -> MenuInfo(
            title = "Tanda dan gejala",
            description = "Pada stadium awal gejala gagal ginjal tidak begitu terlihat. Gejala gagal ginjal biasanya muncul ketika sudah mencapai stadium 4-5 diantaranya : ",
            bulletPoints = listOf(
                "Mudah lelah",
                "Kulit kering dan gatal",
                "Mual dan muntah",
                "Penurunan atau peningkatan frekuensi buang air kecil",
                "Sering buang air kecil dimalam hari",
                "Sesak nafas",
                "Bengkak pada kedua kaki",
                "Penurunan berat badan"
            ),
            imageResId = R.drawable.medical_result
        )
        "Pemeriksaan untuk diagnosa" -> MenuInfo(
            title = "Pemeriksaan untuk diagnosa",
            description = "Diagnosa gagal ginjal :",
            bulletPoints = listOf(
                "Pemeriksaan darah lengkap : untuk mendeteksi kadar limbah pada darah",
                "Tes urine : Dalam tes ini, akan dokter periksa kadar albumin dan kreatinin dalam urine, kandungan protein dalam urine "
            ),
            imageResId = R.drawable.diagnosis
        )
        "Pencegahan" -> MenuInfo(
            title = "Pencegahan",
            description = "Pencegahan dapat dilakukan dengan berbagai cara seperti :",
            bulletPoints = listOf(
                "Mengonsumsi makanan bergizi lengkap dan seimbang",
                "Batasi makan makanan yang mengandung tinggi garam, tinggi lemak jenuh, dan tinggi asam urat",
                "Cukup minum air putih, minimal 8 gelas sehari",
                "Menjaga berat badan ideal",
                "Tidak merokok",
                "Menghindari konsumsi obat pereda nyeri tanpa berkonsultasi terlebih dahulu dengan dokter",
                "Menghindari minuman berkemasan yang mengandung banyak pemanis buatan",
                "Mengurangi makanan instan yang mengandung zat pengawet, perasa, dan pewarna. ",
                "Menjaga tekanan darah tetap normal"
            ),
            imageResId = R.drawable.health_solutions
        )
        "Pengobatan" -> MenuInfo(
            title = "Pengobatan",
            description = "Pengobatan bertujuan untuk mengurangi gejala, bukan untuk memperbaiki fungsi ginjal. Beberapa jenis pengobatan yang diberikan untuk terapi gagal ginjal adalah :",
            bulletPoints = listOf(
                "Obat-obatan untuk mengurangi gejala seperti obat hipertensi, anemia, suplemen kalsium dan vitamin D",
                "Pembatasan asupan protein dan cairan. ",
                "Terapi pengganti ginjal. Terapi pengganti ginjal terbagi 2 :\n\n A. Dialisis  : Dialisis adalah proses penyaringan limbah dan cairan dalam tubuh. Ada dua jenis dialisis, yakni: \na.\tHemodialisis atau biasa dikenal dengan cuci darah, yakni prosedur dialisis yang menggunakan mesin\n" +
                        "b.\tContinuous ambulatory peritoneal dialysis (CAPD), yaitu metode cuci darah yang dilakukan lewat perut. Metode ini memanfaatkan selaput dalam rongga perut (peritoneum), yang memiliki permukaan luas dan banyak jaringan pembuluh darah, sebagai filter alami ketika dilewati oleh zat sisa.\n\n B. Transplantasi ginjal : Pada transplantasi ginjal, ginjal pasien akan diganti dengan ginjal sehat dari pendonor (organ cangkok)."
            ),
            imageResId = R.drawable.medical
        )
        "Kondisi lebih lanjut gagal ginjal kronis" -> MenuInfo(
            title = "Kondisi lebih lanjut gagal ginjal kronis",
            description = "Gagal ginjal kronis dapat memicu sejumlah keadaan berikut:",
            bulletPoints = listOf(
                "Gangguan elektrolit, seperti penumpukan fosfor dan hiperkalemia atau kenaikan kadar kalium yang tinggi dalam darah",
                "Gangguan keseimbangan asam dan basa di dalam tubuh, seperti asidosis",
                "Penyakit jantung dan pembuluh darah",
                "Penumpukan kelebihan cairan di rongga tubuh, misalnya edema paru atau asites",
                "Anemia, karena ginjal juga berfungsi untuk membentuk sel darah merah",
                "Kerusakan sistem saraf pusat yang bisa menyebabkan kejang"
            ),
            imageResId = R.drawable.old_woman
        )
        else -> MenuInfo(
            title = "Informasi tidak tersedia",
            description = "Deskripsi tidak tersedia.",
            imageResId = R.drawable.doctor_image
        )
    }
}
