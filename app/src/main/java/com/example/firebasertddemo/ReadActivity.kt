package com.example.firebasertddemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firebasertddemo.adapter.UserListAdapter
import com.example.firebasertddemo.model.User
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_read.*

class ReadActivity : AppCompatActivity() {
    lateinit var databaseReference: DatabaseReference
    var mList: ArrayList<User> = ArrayList()
    var keysList: ArrayList<String> = ArrayList();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read)

        databaseReference = FirebaseDatabase.getInstance().getReference(User.COLLECTION_NAME)

        init()
    }

    private fun init() {
        getData()
    }

    private fun getData(){
        var adapter = UserListAdapter(this@ReadActivity, mList)
        databaseReference.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                mList.clear()
                keysList.clear()
                for(data in dataSnapshot.children){
                    var user = data.getValue(User::class.java)
                    var key = data.key
                    mList.add(user!!)
                    keysList.add(key!!)
                }
                users_RV.adapter = adapter
                users_RV.layoutManager = LinearLayoutManager(this@ReadActivity)
                adapter.setData(mList, keysList)
            }

            override fun onCancelled(p0: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}