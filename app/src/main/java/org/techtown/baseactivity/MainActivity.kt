package org.techtown.baseactivity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import org.techtown.baseactivity.databinding.ActivityMainBinding
import java.util.jar.Manifest

class MainActivity : BaseActivity() {
    val binding by lazy{ActivityMainBinding.inflate(layoutInflater)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnCamera.setOnClickListener {
            requirePermissions(arrayOf(android.Manifest.permission.CAMERA),10)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 10){
            if(resultCode == Activity.RESULT_OK){
                Log.d("카메라","촬영 성공")
            }else{
                Log.d("카메라","촬영 실패")
            }
        }
    }

    override fun permissionGranted(requestCode: Int) {  //카메라 호출
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(intent,99)
    }

    override fun permissionDenied(requestCode: Int) {  //카메라 거부
        Toast.makeText(baseContext,"권한 거부됨",Toast.LENGTH_LONG).show()
    }
}