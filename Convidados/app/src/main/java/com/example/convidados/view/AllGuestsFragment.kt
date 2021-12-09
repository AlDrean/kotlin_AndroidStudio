package com.example.convidados.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.convidados.R
import com.example.convidados.databinding.FragmentAllBinding
import com.example.convidados.service.constants.GuestConstants
import com.example.convidados.view.adapter.GuestAdapter
import com.example.convidados.view.listener.GuestListener
import com.example.convidados.viewmodel.AllGuestsViewModel


class AllGuestsFragment : Fragment() {

    private lateinit var allGuestsViewModel: AllGuestsViewModel
    private var _binding: FragmentAllBinding? = null

    private val mAdapter: GuestAdapter = GuestAdapter()
    private lateinit var mlistener:GuestListener

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        allGuestsViewModel =
            ViewModelProvider(this).get(AllGuestsViewModel::class.java)

        _binding = FragmentAllBinding.inflate(inflater, container, false)
        val root: View = binding.root


////        reciclerView - listagem de elementos
//        1 - obter o recycler
        var recycler = root.findViewById<RecyclerView>(R.id.recycler_all_guest)
//        2 - definir um layout
        recycler.layoutManager = LinearLayoutManager(context)
//        3 - definir um adapter -> interpolador de dados para o RecyclerView
        recycler.adapter = mAdapter

        observer()

        allGuestsViewModel.load()

        mlistener = object :GuestListener{
            override fun onClick(id: Int) {
                val intent = Intent(context,GuestFormActivity::class.java)
                val bundle = Bundle()
                bundle.putInt(GuestConstants.GUESTID,id)
                intent.putExtras(bundle)
                startActivity(intent)
            }

            override fun onDelete(id: Int) {
                allGuestsViewModel.delete(id)
                allGuestsViewModel.load()
            }

        }

        mAdapter.attachListener(mlistener)


        return root
    }

    override fun onResume() {
        super.onResume()
        allGuestsViewModel.load()
    }

    private fun observer() {
        allGuestsViewModel.guestList.observe(viewLifecycleOwner, Observer {
            mAdapter.updateGuests(it)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}