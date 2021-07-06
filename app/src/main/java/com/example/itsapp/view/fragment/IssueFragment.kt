package com.example.itsapp.view.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.itsapp.R
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_issue.*

class IssueFragment : Fragment() {

    companion object{
        const val TAG : String = "로그"
        fun newInstance() : IssueFragment{
            return IssueFragment()
        }
        lateinit var rankFragment: RankFragment
        lateinit var newsFragment: NewsFragment
        lateinit var blogFragment: BlogFragment
    }

    // 메모리에 올라갔을때
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "NewsFragment -onCreate() called")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TAG, "NewsFragment -onAttach() called")
    }

    // 뷰가 생성됐을 때
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_issue,container,false)
        rankFragment = RankFragment.newInstance()
        activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.today_container,rankFragment)?.commit()

        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        today_story_tabs.addOnTabSelectedListener(object:TabLayout.OnTabSelectedListener{
            override fun onTabReselected(p0: TabLayout.Tab?) {}
            override fun onTabUnselected(p0: TabLayout.Tab?) {}
            override fun onTabSelected(p0: TabLayout.Tab?) {
                val position = p0?.position
                when(position){
                    0 -> {
                        rankFragment = RankFragment.newInstance()
                        activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.today_container,rankFragment)?.commit()
                    }
                    1->{
                        newsFragment = NewsFragment.newInstance()
                        activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.today_container,newsFragment)?.commit()
                    }
                    2->{
                        blogFragment = BlogFragment.newInstance()
                        activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.today_container, blogFragment)?.commit()
                    }
                }
            }

        })
    }
}