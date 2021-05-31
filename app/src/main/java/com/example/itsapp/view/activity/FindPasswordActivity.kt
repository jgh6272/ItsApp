package com.example.itsapp.view.activity

import android.os.Bundle
import android.os.StrictMode
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.itsapp.R
import com.example.itsapp.util.MailSender
import com.example.itsapp.viewmodel.LoginViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_find_password.*
import org.mindrot.jbcrypt.BCrypt

class FindPasswordActivity : AppCompatActivity() {

    private val viewModel : LoginViewModel by viewModels()
    private lateinit var userId:String
    private lateinit var userPw:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find_password)

        thread()
        btnEvent()
        liveData()
    }
    private fun btnEvent(){
        back_btn.setOnClickListener {
            finish()
        }
        update_pw.setOnClickListener {
            userId = findPw_id_edt.text.toString()
            viewModel.userInfo(userId)
        }
    }
    private fun liveData(){
        viewModel.loginLiveData.observe(this, Observer {result ->
            if(result.code.equals("200")){
                val newPw = getRamdomPassword(8)
                if(newPw!=null){
                    userPw=BCrypt.hashpw(newPw,BCrypt.gensalt())
                }
                val sender = MailSender()
                sender.sendEmail("ItsApp 비밀번호 재설정","ItsApp의 새로운 비밀번호는 "+newPw+"(대문자)입니다. 마이페이지를 통해 비밀번호를 변경해주세요.",userId)
                viewModel.updatePw(userId,userPw)
            }else {
                Snackbar.make(find_pw_layout,"해당 아이디로 회원가입된 계정이 없습니다.",Snackbar.LENGTH_SHORT).show()
            }
        })
        viewModel.updatePwLiveData.observe(this, Observer { code->
            if(code.equals("200")){
                Snackbar.make(find_pw_layout,"해당 이메일로 새로운 비밀번호를 발급했습니다.",Snackbar.LENGTH_SHORT).show()
            }else {
                Snackbar.make(find_pw_layout,"에러",Snackbar.LENGTH_SHORT).show()
            }
        })
    }
    private fun getRamdomPassword(len: Int): String? {
        val charSet = charArrayOf(
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
            'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
            'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
        )
        var idx = 0
        val sb = StringBuffer()
        println("charSet.length :::: " + charSet.size)
        for (i in 0 until len) {
            idx = (charSet.size * Math.random()).toInt()
            println("idx :::: $idx")
            sb.append(charSet[idx])
        }
        return sb.toString()
    }
    private fun thread(){
        StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.Builder()
            .permitDiskReads()
            .permitDiskWrites()
            .permitNetwork().build());
    }
}