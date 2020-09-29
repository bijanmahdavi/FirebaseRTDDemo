package com.example.firebasertddemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.firebasertddemo.model.User
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_insert.*

class InsertActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert)
        init()
    }

    private fun init() {
        button_insert.setOnClickListener {
            var email = edit_text_email.text.toString()
            var name = edit_text_name.text.toString()
            var user = User(name, email)

//            var firebaseDatabase = FirebaseDatabase.getInstance()
//            var databaseReference = firebaseDatabase.getReference("users")

            var databaseReference = FirebaseDatabase.getInstance().getReference("users")

            // insert blank record to generate unique id and save it in local varaible
            var userId = databaseReference.push().key

            databaseReference.child(userId!!).setValue(user)
            Toast.makeText(applicationContext, "inserted", Toast.LENGTH_SHORT).show()

        }
        button_delete.setOnClickListener {
            var databaseReference = FirebaseDatabase.getInstance().getReference("users")
            var userId = databaseReference.push().key
            Log.d("DB", databaseReference.child(userId!!).toString())
            databaseReference.child("-MIPXvdYzw7vZ3mUMqfE").setValue(null)
            Log.d("DB", databaseReference.child(userId).toString())
        }

        //update
        // var user= User("new name", "new email")
        // databaseReference.setValue(userId).setValue
    }
}