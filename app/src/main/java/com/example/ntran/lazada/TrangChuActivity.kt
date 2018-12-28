package com.example.ntran.lazada

import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_trang_chu.*
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class TrangChuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trang_chu)

        btnGetData.setOnClickListener {
            val maloaicha = edtMaLoaiCha.text
            val duongDan = "http://192.168.1.2:81/weblazada/loaisanpham.php?maloaicha=$maloaicha"
            val downloadJSON = DownloadJSON()
            downloadJSON.execute(duongDan)
        }
    }

    class DownloadJSON : AsyncTask<String, Any, String?>(

    ) {
        override fun doInBackground(vararg params: String?): String? {
            //đường dẫn
            val url = URL(params[0])
            //Điền đường dẫn vào trình duyệt
            val connection = url.openConnection()
            //Nhấn enter
            connection.connect()
            //lấy dòng dữ liệu
            val inputStream = connection.getInputStream()
            val streamReader = InputStreamReader(inputStream)
            val reader = BufferedReader(streamReader)

            var line: String?
            var data = ""
            line = reader.readLine()
            while (line != null) {
                data += line
                line = reader.readLine()
            }
            Log.e("kiemtra", data)
            return data
        }
    }
}
