package com.example.convidados.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.convidados.R
import com.example.convidados.service.constants.GuestConstants
import com.example.convidados.ui.GuestFormViewModel

class GuestFormActivity : AppCompatActivity(), View.OnClickListener{

    private lateinit var mViewModel:GuestFormViewModel
    private var mGuestID:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guest_form)

        mViewModel= ViewModelProvider(this).get(GuestFormViewModel::class.java)
        val radio_presence = findViewById<RadioButton>(R.id.radio_present)


        setListeners()
        observe()
        loadData()

        radio_presence.isChecked = true
    }

    override fun onClick(v: View)
    {

        val edt_name = findViewById<EditText>(R.id.edt_name)
        val rbtn_presence = findViewById<RadioButton>(R.id.radio_present)
        val rbtn_absence = findViewById<RadioButton>(R.id.radio_absent)

        val id = v.id
        if (id == R.id.btn_save) {
            mViewModel.save(mGuestID,edt_name.text.toString(),rbtn_presence.isChecked)

            Toast.makeText(this@GuestFormActivity, "Clicked button", Toast.LENGTH_SHORT).show()
        }


    }
    private fun setListeners() {
        var btn_save = findViewById<Button>(R.id.btn_save)
//        @TODO: Verificar porque adicionar btn_save abaixo da declaração de mviewModel quebra o código
        loadData()
        btn_save.setOnClickListener(this)
        observe()

    }

    private fun loadData() {
        val bundle = intent.extras
        if (bundle != null){
            mGuestID = bundle.getInt(GuestConstants.GUESTID)
            mViewModel.load(mGuestID)
        }
    }


    private fun observe(){
        mViewModel.saveGuest.observe(this, Observer {
            if (it){
                Toast.makeText(applicationContext, "Sucesso ",Toast.LENGTH_SHORT).show()
            }else
            {
                Toast.makeText(applicationContext, "Falha ",Toast.LENGTH_SHORT).show()
            }
            finish()
        })
        mViewModel.guest.observe(this, Observer {
            val edt_name = findViewById<TextView>(R.id.edt_name)
            val radio_present = findViewById<RadioButton>(R.id.radio_present)
            val radio_absent = findViewById<RadioButton>(R.id.radio_absent)

            edt_name.text = it.name
            if (it.presence){
                radio_present.isChecked = true
            }
            else{
                radio_absent.isChecked = true

            }
        })
    }



}




//Durante a criação do código foi detectado um problema com o onListenner; View.OnListener era esperado; Durante a procura pela solução do problema, descobri 5 formas de aplicar um listenner em um botão
//There are five ways to use SetOnClickListener:
//
//First:
//
//button.setOnClickListener {
//    // Do some work here
//}
//Second:
//
//button.setOnClickListener(object : View.OnClickListener {
//    override fun onClick(view: View?) {
//        // Do some work here
//    }
//
//})
//Third:
//
//button.setOnClickListener(View.OnClickListener { view ->
//    // Do some work here
//})
//Forth:
//
//class MainActivity : AppCompatActivity(), View.OnClickListener{
//
//    lateinit var button : Button
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//        button = findViewById(R.id.button1)
//        button.setOnClickListener(this)
//    }
//
//    override fun onClick(view: View?) {
//        when(view?.id){
//            R.id.button1->{
//                // do some work here
//            }
//        }
//    }
//}
//Fifth:
//
//class MainActivity : AppCompatActivity(){
//
//    lateinit var button : Button
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//        button = findViewById(R.id.button1)
//        button.setOnClickListener(listener)
//    }
//
//    val listener= View.OnClickListener { view ->
//        when (view.getId()) {
//            R.id.button1 -> {
//                // Do some work here
//            }
//        }
//    }
//}
