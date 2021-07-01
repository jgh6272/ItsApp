package com.example.itsapp.view.activity

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.PopupMenu
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.view.menu.MenuView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.itsapp.viewmodel.CommentViewModel
import com.example.itsapp.R
import com.example.itsapp.model.vo.Comment
import com.example.itsapp.view.adapter.CommentAdapter
import com.example.itsapp.viewmodel.HomeViewModel
import com.example.itsapp.viewmodel.ReviewViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_review_detail.*
import kotlinx.android.synthetic.main.activity_review_detail.back_btn
import kotlinx.android.synthetic.main.activity_review_detail.device_brand
import kotlinx.android.synthetic.main.activity_review_detail.device_name
import kotlinx.android.synthetic.main.activity_review_detail.review_point
import kotlinx.android.synthetic.main.activity_review_write.*
import kotlinx.android.synthetic.main.comment_item.*

class ReviewDetailActivity : AppCompatActivity() {

    private val commentViewModel : CommentViewModel by viewModels()
    private val reviewViewModel: ReviewViewModel by viewModels()
    private val homeViewModel : HomeViewModel by viewModels()
    var commentList = arrayListOf<Comment>()
    val commentAdapter = CommentAdapter(commentList)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review_detail)

        back_btn.setOnClickListener {
            finish()
        }
        val intent = intent
        val deviceName = intent.getStringExtra("deviceName")
        val writer = intent.getStringExtra("writer")
        Log.i("deviceName1",deviceName.toString())
        Log.i("writer1",writer.toString())

        reviewViewModel.getChoiceReview(deviceName!!, writer!!)
        reviewViewModel.reviewLiveData.observe(this, Observer { reviewInfo ->
            if(reviewInfo.code.equals("200")){
                device_brand.text = reviewInfo.jsonArray[0].deviceBrand
                device_name.text = reviewInfo.jsonArray[0].deviceName
                review_writer.text = reviewInfo.jsonArray[0].writer
                review_point.rating = reviewInfo.jsonArray[0].reviewPoint.toFloat()
                review_write_time.text = reviewInfo.jsonArray[0].writeTime
                content_pros.text = reviewInfo.jsonArray[0].contentPros
                content_cons.text = reviewInfo.jsonArray[0].contentCons
            }
        })
        rv_comment.layoutManager = LinearLayoutManager(this)
        rv_comment.adapter = commentAdapter
        rv_comment.addItemDecoration(DividerItemDecoration(this, 1));

        commentViewModel.getComment(deviceName, writer)
        commentViewModel.commentLiveData.observe(this, Observer { commentInfo ->
            if(commentInfo.code.equals("200")){
                commentList = commentInfo.jsonArray as ArrayList<Comment>
                commentAdapter.updateItem(commentInfo.jsonArray)
            }
        })

        comment_write_btn.setOnClickListener {
            val deviceName = deviceName
            val reviewWriter = writer
            val writer = homeViewModel.getLoginSession()
            val commentContent = comment_edt.text.toString()
            if(commentContent.isEmpty()){
                Snackbar.make(review_detail_layout,"댓글을 입력해주세요.", Snackbar.LENGTH_SHORT).show()
            }else{
                commentViewModel.writeComment(deviceName,reviewWriter,writer,commentContent)
            }
        }

        commentViewModel.writeCommentLiveData.observe(this, Observer {
            if(it.code.equals("200")){
                hidekeyboard()
                comment_edt.setText(null)
                commentList.add(it.jsonArray[0])
                commentAdapter.updateItem(commentList)
            }
        })

        commentViewModel.commentLiveData.observe(this, Observer { commentInfo ->
            if(commentInfo.code.equals("200")){
                commentAdapter.setItemClickListener(object : CommentAdapter.OnItemClickListener{
                    override fun onClick(v: View, position: Int) {
                        var popup = PopupMenu(application, v)
                        menuInflater.inflate(R.menu.more_menu,popup.menu)
                        popup.setOnMenuItemClickListener { item ->
                            when (item.itemId){
                                R.id.action_delete -> {
                                    Toast.makeText(application, "댓글 삭제", Toast.LENGTH_SHORT).show()
                                    Log.i("getCommentId",commentInfo.jsonArray[position].commnetId.toString())
                                    commentViewModel.deleteComment(commentInfo.jsonArray[position].commnetId)
                                }
                                R.id.action_report ->
                                    Toast.makeText(application,"신고 하기",Toast.LENGTH_SHORT).show()
                            }
                            false
                        }
                        popup.show()
                    }
                })
            }
        })
//        commentAdapter.setItemClickListener(object : CommentAdapter.OnItemClickListener{
//            override fun onClick(v: View, position: Int) {
//                var popup = PopupMenu(application, v)
//                menuInflater.inflate(R.menu.more_menu,popup.menu)
//                popup.setOnMenuItemClickListener { item ->
//                    when (item.itemId){
//                        R.id.action_delete -> {
//                            Toast.makeText(application, "댓글 삭제", Toast.LENGTH_SHORT).show()
//                        }
//                        R.id.action_report ->
//                            Toast.makeText(application,"신고 하기",Toast.LENGTH_SHORT).show()
//                    }
//                    false
//                }
//                popup.show()
//            }
//        })

        delete_btn.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("리뷰 삭제")
            builder.setMessage("리뷰를 삭제하시겠습니까?")
            builder.setPositiveButton(
                "삭제"
            ) { dialog: DialogInterface?, which: Int ->
                val loginId = homeViewModel.getLoginSession()
                reviewViewModel.getLoginUserId(loginId)
                reviewViewModel.loginUserIdLiveData.observe(this, Observer { userInfo ->
                    if(userInfo.code.equals("200")) {
                        Log.i("userNickName", userInfo.jsonArray.userNickname)
                        if(writer.equals(userInfo.jsonArray.userNickname)){
                            reviewViewModel.deleteReview(deviceName,writer)
                        }else{
                            Toast.makeText(this,"리뷰 작성자만 해당 리뷰를 삭제할 수 있습니다.",Toast.LENGTH_SHORT).show()
                        }
                    }
                })
            }
            builder.setNegativeButton("취소",null)
            builder.show()
        }
        reviewViewModel.deleteReviewLiveData.observe(this, Observer {
            if(it.equals("200")){
                finish()
                Toast.makeText(this,"리뷰가 삭제되었습니다.",Toast.LENGTH_SHORT).show()
                val intent = Intent(this, DeviceInfoActivity::class.java)
                intent.putExtra("deviceName",deviceName)
                startActivity(intent)
            }
        })
    }

    // 키보드 내리기
    fun hidekeyboard(){
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(comment_edt.windowToken,0)
    }
}