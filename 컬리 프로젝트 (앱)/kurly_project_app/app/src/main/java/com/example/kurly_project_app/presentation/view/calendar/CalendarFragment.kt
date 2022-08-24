package com.example.kurly_project_app.presentation.view.calendar

import android.app.AlertDialog
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.booksearchapp.di.AppModule
import com.example.booksearchapp.di.AppModule.addCallendar
import com.example.booksearchapp.di.AppModule.cartGetWhoId
import com.example.booksearchapp.di.AppModule.getAllCallendarDate
import com.example.booksearchapp.di.AppModule.getAllrecommend
import com.example.booksearchapp.di.AppModule.getCartProduct
import com.example.booksearchapp.di.AppModule.getProductCountByUserId
import com.example.booksearchapp.ui.adapter.ListViewAdapter
import com.example.booksearchapp.ui.adapter.ProductAdapter
import com.example.kurly_project_app.GsohaApplication
import com.example.kurly_project_app.R
import com.example.kurly_project_app.data.model.ListItem
import com.example.kurly_project_app.databinding.FragmentCalendarBinding
import com.example.kurly_project_app.presentation.view.calendar.adapter.ListViewHolder
import com.example.kurly_project_app.presentation.view.home.HomeFragmentDirections
import com.github.sundeepk.compactcalendarview.CompactCalendarView
import com.github.sundeepk.compactcalendarview.CompactCalendarView.CompactCalendarViewListener
import com.github.sundeepk.compactcalendarview.domain.Event
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.sql.SQLException
import java.sql.Types.NULL
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


@AndroidEntryPoint
class CalendarFragment : Fragment() {
    private var _binding: FragmentCalendarBinding? = null
    private val binding get() = _binding!!
    private lateinit var cartAdapter: ProductAdapter
    private lateinit var recoAdapter: ProductAdapter
    val TAG = "calendar test"
    private val dateFormatForDisplaying = SimpleDateFormat("yyyy-MM-dd", Locale.KOREA)
    private val dateFormatForMonth = SimpleDateFormat("yyyy년 MM월", Locale.KOREA)
    private lateinit var listAdapter: ListViewAdapter

    private var product_id_list: MutableList<Int> = mutableListOf()
    private var recommand_id_list: MutableList<Int> = mutableListOf()
    private val str_id: String = GsohaApplication.prefs.getString("user", "DEFAULT")
    private lateinit var imageviewDetail: ImageView
    private lateinit var titleTextView: TextView
    private lateinit var productPrice: TextView
    private lateinit var cancleBtn: Button
    private lateinit var okBtn: Button
    private lateinit var putBtn: Button
    private lateinit var btn_delete : Button
    private lateinit var mondayCheckBox: CheckBox
    private lateinit var tuesdayCheckBox: CheckBox
    private lateinit var wednesdayCheckBox: CheckBox
    private lateinit var thurthdayCheckBox: CheckBox
    private lateinit var frideayCheckBox: CheckBox
    private lateinit var saturdayCheckBox: CheckBox
    private lateinit var sundayCheckBox: CheckBox


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCalendarBinding.inflate(inflater, container, false)
        return binding.root
    }

    var totalCnt: Int = 0
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            backButton.setOnClickListener {
                findNavController().popBackStack()
            }

            setupRecyclerView()

            GlobalScope.launch() {
                try {
                    toBeCallendar(compactcalendarView)

                    product_id_list = cartGetWhoId(str_id.toInt())
                    recommand_id_list = getAllrecommend(str_id.toInt())

                    recoAdapter.submitList(getCartProduct(recommand_id_list))
                    cartAdapter.submitList(getCartProduct(product_id_list))
                } catch (e: SQLException) {
                    e.printStackTrace()
                }
            }

            binding.apply {
                cartTextview.setText("${str_id}님의 장바구니")
                textViewMonth.setText(dateFormatForMonth.format(binding.compactcalendarView.getFirstDayOfCurrentMonth()))
                compactcalendarView.setFirstDayOfWeek(Calendar.MONDAY)
            }
            binding.recoTextView.setText("${str_id.toString()} 님의 추천 항목")

            cartClicked(cartAdapter)
            cartClicked(recoAdapter)

            // 이벤트 관련 코드
            binding.compactcalendarView.setListener(object : CompactCalendarViewListener {
                override fun onDayClick(dateClicked: Date?) {
                    val mDialogView = LayoutInflater.from(getActivity()).inflate(com.example.kurly_project_app.R.layout.deatil_callendar_view, null)
                    val mBuilder = AlertDialog.Builder(getActivity()).setView(mDialogView)
                    val mAlertDialog = mBuilder.show()

                    val events: List<Event> = binding.compactcalendarView.getEvents(dateClicked)

                    val pdRecyclerView : RecyclerView = mDialogView.findViewById(R.id.product_recyclerview)
                    listAdapter = ListViewAdapter()

                    pdRecyclerView.apply {
                        setHasFixedSize(true)
                        layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                        adapter = listAdapter
                    }

                    val mutableArr : MutableList<ListItem> = mutableListOf()

                    if (events.size > 0) {
                        for (i : Int in 0.. events.size-1){
                            mutableArr.add(ListItem(events[i].data.toString(), "1"))
                        }
                    }

                    listAdapter.submitList(mutableArr)


                    okBtn = mDialogView.findViewById(R.id.ok_button)

                    okBtn.setOnClickListener {
                        mAlertDialog.dismiss()
                    }

                }


                override fun onMonthScroll(firstDayOfNewMonth: Date?) {
                    binding.textViewMonth.setText(dateFormatForMonth.format(firstDayOfNewMonth))
                }
            })
        }
    }

    private fun FragmentCalendarBinding.cartClicked(adapter: ProductAdapter) {
        adapter.setOnItemClickListener { product ->
            val mDialogView = LayoutInflater.from(getActivity())
                .inflate(R.layout.product_put_callendar, null)
            val mBuilder = AlertDialog.Builder(getActivity()).setView(mDialogView)
            //show dialog
            //login button click of custom layout
            val mAlertDialog = mBuilder.show()

            titleTextView =
                mDialogView.findViewById(R.id.title_textView)
            imageviewDetail =
                mDialogView.findViewById(R.id.imageView)
            productPrice =
                mDialogView.findViewById(R.id.price_textView)
            cancleBtn = mDialogView.findViewById(R.id.cancle)
            putBtn = mDialogView.findViewById(R.id.put_item)

            val checkBoxArray: MutableList<CheckBox> = mutableListOf()
            mondayCheckBox =
                mDialogView.findViewById(R.id.monday_checkbox)
            tuesdayCheckBox =
                mDialogView.findViewById(R.id.tuesday_checkbox)
            wednesdayCheckBox =
                mDialogView.findViewById(R.id.wednesday_checkbox)
            thurthdayCheckBox =
                mDialogView.findViewById(R.id.thurthday_checkbox)
            frideayCheckBox =
                mDialogView.findViewById(R.id.friday_checkbox)
            saturdayCheckBox =
                mDialogView.findViewById(R.id.seturday_checkbox)
            sundayCheckBox =
                mDialogView.findViewById(R.id.sunday_checkbox)

            checkBoxArray.add(mondayCheckBox)
            checkBoxArray.add(tuesdayCheckBox)
            checkBoxArray.add(wednesdayCheckBox)
            checkBoxArray.add(thurthdayCheckBox)
            checkBoxArray.add(frideayCheckBox)
            checkBoxArray.add(saturdayCheckBox)
            checkBoxArray.add(sundayCheckBox)

            val allEventDate: MutableList<Date> = mutableListOf()

            putBtn.setOnClickListener {
                val cal = Calendar.getInstance()
                val lastDay: Int = cal.getMaximum(Calendar.DAY_OF_MONTH)
                val date: Date = binding.compactcalendarView.firstDayOfCurrentMonth

                var currentDate: Date = date

                var dayArrayList: MutableList<String> = mutableListOf()

                if (mondayCheckBox.isChecked) {
                    dayArrayList.add("월")
                }
                if (tuesdayCheckBox.isChecked) {
                    dayArrayList.add("화")
                }
                if (wednesdayCheckBox.isChecked) {
                    dayArrayList.add("수")
                }
                if (thurthdayCheckBox.isChecked) {
                    dayArrayList.add("목")
                }
                if (frideayCheckBox.isChecked) {
                    dayArrayList.add("금")
                }
                if (saturdayCheckBox.isChecked) {
                    dayArrayList.add("토")
                }
                if (sundayCheckBox.isChecked) {
                    dayArrayList.add("일")
                }
                // 흐름 : 월, 화, 수, 목, 금, 토, 일 중 체크돼있는 것,

                for (i: Int in 1..lastDay) {
                    if (getWeekDay(currentDate) in dayArrayList) {
                        allEventDate.add(currentDate)
                    }
                    currentDate = Date(currentDate.getTime() + 1000 * 60 * 60 * 24 * 1)
                }
    //fun addCallendar(order_num: Int, product_id: Int, user_id: Int, product_count: Int, order_success : Int, product_price : Int, delivery_date : Int) {
                for (i: Int in 0..allEventDate.size - 1) { // 가져와야할 정보, order_success
                    val user_id: Int =
                        GsohaApplication.prefs.getString("user", "DEFAULT").toInt()
                    val order_success: Int = 0 // 아직 결제 전
                    val product_id: Int = (product.id)!!.toInt()
                    val delivery_date: Date = allEventDate[i]
                    val product_price: Int =
                        (product.price.toString()).replace("[^0-9]".toRegex(), "").toInt()
                    val sqlDate = java.sql.Date(delivery_date.time)

                    var today = Calendar.getInstance()
                    var calcuDate = (today.time.time - allEventDate[i].time) / (60 * 60 * 24 * 1000)

                    if (calcuDate > 0) {
                        continue
                    }
                    GlobalScope.launch() {
                        try {
                            val product_count = getProductCountByUserId(product_id, user_id)
                            addCallendar(
                                NULL,
                                product_id,
                                user_id,
                                product_count,
                                order_success,
                                product_price,
                                sqlDate
                            )
                        } catch (e: SQLException) {
                            e.printStackTrace()
                        }
                    }

                    val date2 = dateFormatForDisplaying.format(delivery_date)
                    var trans_date2: Date? = null
                    try {
                        trans_date2 = dateFormatForDisplaying.parse(date2)
                    } catch (e: ParseException) {
                        e.printStackTrace()
                    }
                    val time2 = trans_date2!!.time

                    val ev2 = Event(Color.GREEN, time2, "${product_id} 100")
                    compactcalendarView.addEvent(ev2)

                    totalCnt += product_price!!.toInt()
                }

                var newStr: String = totalCnt.toString()
                newStr = "${newStr.substring(0, newStr.length - 3)},${
                    newStr.substring(
                        newStr.length - 3,
                        newStr.length
                    )
                }"
                CoroutineScope(Dispatchers.Main).launch {
                    binding.priceTotal.setText("${newStr.toString()} 원")
                }
                mAlertDialog.dismiss()
            }

            cancleBtn.setOnClickListener {
                mAlertDialog.dismiss()
            }

            titleTextView.setText(product.title)
            imageviewDetail.load(product.url)
            productPrice.setText(product.price.toString())

        }
    }

    private fun toBeCallendar(compactcalendarView: CompactCalendarView) {
        compactcalendarView.removeAllEvents();

        val callendarItems = getAllCallendarDate(str_id.toInt())

        for (i: Int in 0..callendarItems.size - 1) {
            val date2 = dateFormatForDisplaying.format(callendarItems[i].delivery_date)
            var trans_date2: Date? = null
            try {
                trans_date2 = dateFormatForDisplaying.parse(date2)
            } catch (e: ParseException) {
                e.printStackTrace()
            }
            CoroutineScope(Dispatchers.Main).launch {
                if (callendarItems[i].order_success == 0) {
                    val time2 = trans_date2!!.time
                    val ev2 = Event(Color.GREEN, time2, "${callendarItems[i].product_id.toString()} ${callendarItems[i].order_num.toString()}")
                    compactcalendarView.addEvent(ev2)
                    totalCnt += callendarItems[i].product_price!!.toInt()
                } else {
                    val time2 = trans_date2!!.time
                    val ev2 = Event(Color.RED, time2, "${callendarItems[i].product_id}")
                    compactcalendarView.addEvent(ev2)
                }
            }
        }

        var newStr: String? = null
        if (totalCnt >= 1000) {
            var str = totalCnt.toString()
            newStr = "${str.substring(0, str.length - 3)},${
                str.substring(
                    str.length - 3,
                    str.length
                )
            }"
        } else {
            newStr = totalCnt.toString()
        }

        CoroutineScope(Dispatchers.Main).launch {
            binding.priceTotal.setText("${newStr} 원")
        }
    }

    private fun setupRecyclerView() {
        cartAdapter = ProductAdapter()
        recoAdapter = ProductAdapter()
        binding.recomRecyclerview.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = recoAdapter
        }

        binding.cartRecyclerview.apply {
            setHasFixedSize(true)
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = cartAdapter
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun getWeekDay(date1: Date): String {
        var day: String
        val cal = Calendar.getInstance()
        cal.setTime(date1)
        val dayNum: Int = cal.get(Calendar.DAY_OF_WEEK)

        when (dayNum) {
            1 -> day = "일"
            2 -> day = "월"
            3 -> day = "화"
            4 -> day = "수"
            5 -> day = "목"
            6 -> day = "금"
            7 -> day = "토"
            else -> day = "Null"
        }

        return day

    }



}