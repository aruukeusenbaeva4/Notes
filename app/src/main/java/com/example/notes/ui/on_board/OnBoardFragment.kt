package com.example.notes.ui.on_board

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.notes.ui.on_board.adapter.OnBoardAdapter
import com.example.notes.data.models.OnBoardModel
import com.example.notes.data.local.Pref
import com.example.notes.R
import com.example.notes.databinding.FragmentOnBoardBinding

class OnBoardFragment : Fragment() {
   private lateinit var binding: FragmentOnBoardBinding
   private  lateinit var adapter: OnBoardAdapter
   private lateinit var pref: Pref

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
            binding = FragmentOnBoardBinding.inflate(inflater, container, false)
            pref = Pref(requireContext())
            return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadOnBoardData()
        initView()
        }
    private fun initView() {
        adapter = OnBoardAdapter(loadOnBoardData(), ::onStartBoard)
        binding.viewPager.adapter = adapter
        binding.wormDotsIndicator.attachTo(binding.viewPager)
    }
    private fun onStartBoard(){
        pref.saveOnBoard(true)
        findNavController().navigate(R.id.action_onBoardFragment_to_mainFragment)
    }

    private fun loadOnBoardData():List<OnBoardModel> {
        return listOf<OnBoardModel>(
            OnBoardModel(
                "Удобство",
                "Создавайте заметки в два клика! Записывайте мысли, идеи и важные задачи мгновенно.",
                "lottie1.json"
            ),
            OnBoardModel(
                "Организация",
                "Организуйте заметки по папкам и тегам. Легко находите нужную информацию в любое время.",
                "lottie2.json"
            ),
            OnBoardModel(
                "Синхронизация",
                "Синхронизация на всех устройствах. Доступ к записям в любое время и в любом месте.",
                "lottie3.json"
            )
        )
    }
}