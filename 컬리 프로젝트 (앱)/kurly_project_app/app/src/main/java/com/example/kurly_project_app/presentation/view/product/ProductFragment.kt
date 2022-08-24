package com.example.kurly_project_app.presentation.view.product

import android.graphics.Paint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.booksearchapp.di.AppModule.addCart
import com.example.booksearchapp.di.AppModule.getCart
import com.example.booksearchapp.di.AppModule.removeCart
import com.example.kurly_project_app.GsohaApplication
import com.example.kurly_project_app.R
import com.example.kurly_project_app.databinding.FragmentProductBinding
import com.example.kurly_project_app.presentation.MainActivity
import com.example.kurly_project_app.presentation.view.product.viewmodel.ActionType
import com.example.kurly_project_app.presentation.view.product.viewmodel.ProductViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.sql.SQLException
import java.sql.Types.NULL


@AndroidEntryPoint
class ProductFragment : Fragment() {
    private var _binding: FragmentProductBinding? = null
    private val binding get() = _binding!!
    private val productViewModel by viewModels<ProductViewModel>()
    private val args by navArgs<ProductFragmentArgs>()
    private val str_id: String = GsohaApplication.prefs.getString("user", "DEFAULT")
    private var cart_number_result : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mainAct = activity as MainActivity
        mainAct.HideBottomNavi(true)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProductBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val product = args.product

        if (str_id != "DEFAULT") {
            GlobalScope.launch() {
                cart_number_result = getCart(product.id!!.toInt(), str_id.toInt())
                if (cart_number_result != 0) {
                    binding.likeButton.apply {
                        CoroutineScope(Dispatchers.Main).launch {
                            setImageResource(R.drawable.pink_hart)
                            isSelected = true
                        }
                    }
                }
            }
        }

        productViewModel.apply {
            liveData.observe(viewLifecycleOwner) {
                var newStr: String? = null
                if (it >= 1000) {
                    var str = it.toString()
                    newStr = "${str.substring(0, str.length - 3)},${
                        str.substring(
                            str.length - 3,
                            str.length
                        )
                    }"
                } else {
                    newStr = it.toString()
                }

                binding.priceResult.setText("${newStr} 원")
            }
            liveDataCnt.observe(viewLifecycleOwner) {
                binding.productCnt.setText("${it.toString()} 개")
            }
        }

        binding.apply {
            explainTextview.paintFlags = Paint.UNDERLINE_TEXT_FLAG
            productTitle.setText(product.title)
            productImageview.load(product.url)
            titleSecondTextview.setText(product.title)
            priceTextview.setText(product.price)

            backButton.setOnClickListener {
                findNavController().popBackStack()
            }

            likeButton.apply {
                setOnClickListener {
                    if (isSelected) {
                        setImageResource(R.drawable.hart)
                        GlobalScope.launch() {
                            removeCart(cart_number_result)
                        }
                        isSelected = false
                    } else {
                        GlobalScope.launch() {
                            try {
                                val cnt = productViewModel.liveDataCnt.value!!.toInt()
                                if (str_id != "DEFAULT" && cnt != 0) {
                                    addCart(NULL, product.id!!.toInt(), str_id.toInt(), cnt)
                                    cart_number_result = getCart(product.id!!.toInt(), str_id.toInt())
                                    Snackbar.make(view, "장바구니에 추가 됐습니다.", Snackbar.LENGTH_SHORT).show()
                                    CoroutineScope(Dispatchers.Main).launch {
                                        setImageResource(R.drawable.pink_hart)
                                        isSelected = true
                                        productViewModel.clearValue()
                                    }
                                }
                                if (cnt == 0) {
                                    Snackbar.make(view, "1개 이상 담아주세요", Snackbar.LENGTH_SHORT).show()
                                }
                                if (str_id == "DEFAULT") {
                                    Snackbar.make(view, "로그인을 해주세요", Snackbar.LENGTH_SHORT).show()
                                }
                            } catch (e: SQLException) {
                                e.printStackTrace()
                            }
                        }

                    }
                }
            }

            callendarButton.setOnClickListener {
                if(str_id != "DEFAULT") {
                    val action = ProductFragmentDirections.actionFragmentProductToFragmentCalendar()
                    findNavController().navigate(action) // 페이지 이동!
                }
                else{
                    Snackbar.make(view, "로그인을 해주세요.", Snackbar.LENGTH_SHORT).show()
                }
            }

            var str: String = product.price.toString()
            str = str.replace("[^0-9]".toRegex(), "")

            plusButton.setOnClickListener {
                productViewModel.updateValue(ActionType.PLUS, str.toInt())
            }

            minusButton.setOnClickListener {
                productViewModel.updateValue(ActionType.MINUS, str.toInt())
            }

        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()

        val mainAct = activity as MainActivity
        mainAct.HideBottomNavi(true)
    }
}