package com.example.barvolume

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity(), View.OnClickListener {

    // 1. Langkah pertama untuk menuliskan kode logika adalah mengenalkan object-object View pada halaman layout ke berkas MainActivity. Tambahkan beberapa bari berikut untuk mengenalkan variabel yang akan digunakan untuk menampung View.
    private lateinit var edtWidth: EditText
    private lateinit var edtHeight: EditText
    private lateinit var edtLength: EditText
    private lateinit var btnCalculate: Button
    private lateinit var tvResult: TextView

    // Ketika nilai volume sudah dihitung dan kemudian terjadi pergantian orientasi pada peranti (portrait-landscape), hasil perhitungan tadi akan hilang.
    companion object {
        private const val STATE_RESULT = "state_result"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 2. Kemudian inisiasi variabel yang telah kita buat dengan menambahkan kode berikut di dalam metode onCreate.
        edtWidth = findViewById(R.id.edt_width)
        edtHeight = findViewById(R.id.edt_height)
        edtLength = findViewById(R.id.edt_length)
        btnCalculate = findViewById(R.id.btn_calculate)
        tvResult = findViewById(R.id.tv_result)
        btnCalculate.setOnClickListener(this)

        // Pada onCreate inilah kita menggunakan nilai bundle yang telah kita simpan sebelumnya pada onSaveInstanceState. Nilai tersebut kita dapatkan dengan menggunakan Key yang sama dengan saat menyimpan, yaitu STATE_RESULT. Kemudian kita isikan kembali pada tvResult.
        if (savedInstanceState != null) {
            val result = savedInstanceState.getString(STATE_RESULT)
            tvResult.text = result
        }
    }

    // Ketika nilai volume sudah dihitung dan kemudian terjadi pergantian orientasi pada peranti (portrait-landscape), hasil perhitungan tadi akan hilang. Untuk mengatasinya, tambahkan metode onSaveInstanceState() pada MainActivitydan sesuaikan seperti berikut:
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(STATE_RESULT, tvResult.text.toString())
    }
    // Di dalam metode tersebut, hasil perhitungan yang ditampilkan pada tvResult dimasukkan pada bundle kemudian disimpan isinya. Untuk menyimpan data disini menggunakan konsep Key-Value, dengan STATE_RESULT sebagai key dan isi dari tvResult sebagai value. Fungsi onSaveInstanceState akan dipanggil secara otomatis sebelum sebuah Activity hancur. Di sini kita perlu menambahkan onSaveInstanceState karena ketika orientasi berubah, Activity tersebut akan di-destroy dan memanggil fungsi onCreate lagi, sehingga kita perlu menyimpan nilai hasil perhitungan tersebut supaya data tetap terjaga dan tidak hilang ketika orientasi berubah.


    // Setelah terbentuknya function onClick hasil implementasi OnClickListener, tambahkan kode logika berikut ke dalamnya.
    override fun onClick(view: View?) {
        if (view?.id == R.id.btn_calculate) {
            // Mengambl value dari EditText
            // Sintaks .text.toString() di atas berfungsi untuk mengambil isi dari sebuah EditText kemudian menyimpannya dalam sebuah variabel. Tambahan .trim() berfungsi untuk menghiraukan spasi jika ada, sehingga nilai yang didapat hanya berupa angka.
            val inputLength = edtLength.text.toString().trim()
            val inputWidth = edtWidth.text.toString().trim()
            val inputHeight = edtHeight.text.toString().trim()
            var isEmptyFields = false
            // Sintaks .isEmpty() berfungsi untuk mengecek apakah inputan dari Editext itu masih kosong. Jika iya, maka kita akan menampilkan pesan error dengan menggunakan .setError("Field ini tidak boleh kosong") dan mengganti variabel Boolean isEmptyField menjadi true supaya bisa lanjut ke proses selanjutnya.
            if (inputLength.isEmpty()) {
                isEmptyFields = true
                edtLength.error = "Field ini tidak boleh kosong"
            }
            if (inputWidth.isEmpty()) {
                isEmptyFields = true
                edtWidth.error = "Field ini tidak boleh kosong"
            }
            if (inputHeight.isEmpty()) {
                isEmptyFields = true
                edtHeight.error = "Field ini tidak boleh kosong"
            }
            if (!isEmptyFields) {
                val volume = inputLength.toDouble() * inputWidth.toDouble() * inputHeight.toDouble()
                tvResult.text = volume.toString()
            }
            val volume = inputLength.toDouble() * inputWidth.toDouble() * inputHeight.toDouble()
            tvResult.text = "Maka Volume dari Bangun Ruang yang anda maksud adalah ${volume.toString()}"
        }
    }
}