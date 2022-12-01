package com.example.cp470chatbot
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import com.example.cp470chatbot.databinding.ActivityChatWindowBinding

class ChatWindow : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityChatWindowBinding
    val chatMessages = arrayOf<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityChatWindowBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayShowTitleEnabled(false);


        var mItems: MutableList<String> = mutableListOf()

        val mListView = findViewById<ListView>(R.id.chatList)

        //mListView.adapter()//<----

        var chatEditText = findViewById<EditText>(R.id.chatInput);
        var sendButton = findViewById<Button>(R.id.sendButton);
        var adapter = ArrayAdapter<String>(this@ChatWindow,android.R.layout.simple_list_item_1,mItems)
        //binding.chatList.adapter=adapter
        mListView.adapter = adapter

        val chatText = chatEditText.text.toString().trim().lowercase()
        mItems.add(chatText)



        sendButton.setOnClickListener {
//            mItems.add(chatText)
            chatEditText.setText("")
            var result: View? = null
            val length= mItems.size
            val inflater = this@ChatWindow.layoutInflater

            if (length % 2 == 0) result =
                inflater.inflate(R.layout.activity_outgoing_chat, null) else result =
                inflater.inflate(R.layout.activity_outgoing_chat, null)
            val message: TextView = result.findViewById<View>(R.id.messageText) as TextView
            message.text = "hello" // get the string at position


            adapter.notifyDataSetChanged();







        }



    }
//    class ChatAdapter : ArrayAdapter(
//        context: Context,
//        resource: Int) {
//
//    }




    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_chat_window)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}