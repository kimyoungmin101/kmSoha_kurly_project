package com.example.kurly_project_app.presentation.view.home

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.booksearchapp.di.AppModule.getUser
import com.example.booksearchapp.di.AppModule.utilFun
import com.example.booksearchapp.ui.adapter.ProductAdapter
import com.example.booksearchapp.util.Constants.HOSTNAME
import com.example.booksearchapp.util.Constants.PASSWORD
import com.example.kurly_project_app.GsohaApplication
import com.example.kurly_project_app.R
import com.example.kurly_project_app.data.model.Product
import com.example.kurly_project_app.databinding.FragmentHomeBinding
import com.example.kurly_project_app.presentation.view.home.adapter.ViewPagerAdapter
import com.example.kurly_project_app.presentation.view.home.viewmodel.HomeViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.sql.DriverManager
import java.sql.SQLException
import java.sql.Statement
import kotlin.collections.ArrayList

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var productAdapter: ProductAdapter
    private val homeViewModel by viewModels<HomeViewModel>()
    private lateinit var dialogLoginBtn : Button
    private lateinit var dialogCancleBtn : Button
    private lateinit var dialogNameEt : EditText

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val str_id : String = GsohaApplication.prefs.getString("user", "DEFAULT")

        if(str_id != "DEFAULT"){
            binding.apply {
                loginButton.setVisibility(View.GONE)
                userId.setText("${str_id} 님")
                logoutButton.setVisibility(View.VISIBLE)
                userId.setVisibility(View.VISIBLE)
            }
        }

        binding.viewpager.apply {
            adapter = ViewPagerAdapter(getItemList()) // 어댑터 생성
            orientation = ViewPager2.ORIENTATION_HORIZONTAL // 방향을 가로로
        }

        setupRecyclerView()

        GlobalScope.launch() {
            try {
                productAdapter.submitList(utilFun())
            } catch (e: SQLException) {
                e.printStackTrace()
            }
        }

        binding.logoutButton.setOnClickListener {
            binding.apply {
                GsohaApplication.prefs.setString("user", "DEFAULT")
                loginButton.setVisibility(View.VISIBLE)
                logoutButton.setVisibility(View.GONE)
                userId.setVisibility(View.GONE)
            }
        }

        binding.loginButton.setOnClickListener {
            val mDialogView = LayoutInflater.from(getActivity()).inflate(R.layout.login_alter_dialog, null)
            //AlertDialogBuilder
            val mBuilder = AlertDialog.Builder(getActivity())
                .setView(mDialogView)
                .setTitle("로그인")
            //show dialog
            //login button click of custom layout
            val  mAlertDialog = mBuilder.show()

            dialogLoginBtn = mDialogView.findViewById(R.id.dialogLoginBtn)
            dialogNameEt = mDialogView.findViewById(R.id.dialogNameEt)
            dialogCancleBtn = mDialogView.findViewById(R.id.dialogCancelBtn)

            dialogLoginBtn.setOnClickListener {
                //dismiss dialog
                mAlertDialog.dismiss()
                //get text from EditTexts of custom layout
                val id = dialogNameEt.text.toString()
                var value_bool : Boolean = false
                GlobalScope.launch {
                    value_bool = getUser(id.toInt())
                    if(value_bool){
                        CoroutineScope(Dispatchers.Main).launch {
                            GsohaApplication.prefs.setString("user", "${id}")
                            binding.apply {
                                loginButton.setVisibility(View.GONE)
                                userId.setText("${id} 님")
                                logoutButton.setVisibility(View.VISIBLE)
                                userId.setVisibility(View.VISIBLE)
                            }
                        }
                    }
                    else{
                        Snackbar.make(view, "없는 아이디 입니다.", Snackbar.LENGTH_SHORT).show()
                    }
                }
            }

            //cancel button click of custom layout
            dialogCancleBtn.setOnClickListener {
                mAlertDialog.dismiss()
            }

        }
    }

    private fun setupRecyclerView() {
        productAdapter = ProductAdapter()
        binding.productRecyclerview.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = productAdapter
        }
        productAdapter.setOnItemClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToProductFragment(it)
            findNavController().navigate(action) // 페이지 이동!
        }
    }

    companion object {
        const val DATABASE_NAME = "sohakurly"
        const val url = "jdbc:mysql://${HOSTNAME}:3306/" + DATABASE_NAME + "?autoReconnect=true&useSS=false"
        const val username = "user"
        const val password = PASSWORD
        const val TABLE_NAME = "product"
    }

    private fun getItemList(): ArrayList<Int> {
        return arrayListOf<Int>(R.drawable.img_first, R.drawable.img_second, R.drawable.img_third)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}