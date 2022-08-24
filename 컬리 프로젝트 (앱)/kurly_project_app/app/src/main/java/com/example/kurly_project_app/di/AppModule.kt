package com.example.booksearchapp.di

import android.util.Log
import com.example.kurly_project_app.data.model.Product
import com.example.kurly_project_app.data.model.ProductOrder
import com.example.kurly_project_app.presentation.view.home.HomeFragment
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.sql.Date
import java.sql.DriverManager

import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun utilFun(): MutableList<Product> {
        val productList: MutableList<Product> = mutableListOf()

        try {
            Class.forName("com.mysql.jdbc.Driver")
            val connection = DriverManager.getConnection(
                HomeFragment.url,
                HomeFragment.username,
                HomeFragment.password
            )

            val statement = connection.createStatement()
            val rs = statement.executeQuery("SELECT * FROM " + HomeFragment.TABLE_NAME)

            while (rs.next()) {
                val product = Product(
                    rs.getInt(1),
                    rs.getString(2),
                    "${rs.getString(3).substring(0, rs.getString(3).length - 3)},${
                        rs.getString(3)
                            .substring(rs.getString(3).length - 3, rs.getString(3).length)
                    } 원",
                    rs.getString(4)
                )
                productList.add(product)
            }
            connection.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return productList
    }


    @Singleton
    @Provides
    fun getUser(id: Int): Boolean {
        try {
            Class.forName("com.mysql.jdbc.Driver")
            val connection = DriverManager.getConnection(
                HomeFragment.url,
                HomeFragment.username,
                HomeFragment.password
            )
            val statement = connection.createStatement()
            val rs = statement.executeQuery("SELECT * FROM user WHERE user_id = ${id}")

            if (rs.last() == true) {
                connection.close()
                return true
            }
            connection.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return false
    }

    @Singleton
    @Provides
    fun addCart(cart_number: Int, product_id: Int, user_id: Int, product_count: Int) {
        try {
            Class.forName("com.mysql.jdbc.Driver")
            val connection = DriverManager.getConnection(
                HomeFragment.url,
                HomeFragment.username,
                HomeFragment.password
            )
            val statement = connection.createStatement()

            statement.execute("INSERT INTO cart(cart_number, product_id, user_id, product_count) VALUES('" + cart_number + "', '" + product_id + "', '" + user_id + "', '" + product_count + "')")
            connection.close()
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }

    @Singleton
    @Provides
    fun removeCart(cart_number: Int) {
        try {
            Class.forName("com.mysql.jdbc.Driver")
            val connection = DriverManager.getConnection(
                HomeFragment.url,
                HomeFragment.username,
                HomeFragment.password
            )
            val statement = connection.createStatement()

            statement.execute("DELETE FROM cart WHERE cart_number=${cart_number}")
            connection.close()
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }

    @Singleton
    @Provides
    fun getCart(product_id: Int, user_id: Int): Int {
        try {
            Class.forName("com.mysql.jdbc.Driver")
            val connection = DriverManager.getConnection(
                HomeFragment.url,
                HomeFragment.username,
                HomeFragment.password
            )
            val statement = connection.createStatement()
            val rs =
                statement.executeQuery("SELECT * FROM cart WHERE user_id = ${user_id} and product_id = ${product_id}")

            if (rs.last() == true) {
                return rs.getInt(1)
            }
            connection.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return 0
    }

    @Singleton
    @Provides
    fun cartGetWhoId(user_id: Int): MutableList<Int> {
        val product_id_list: MutableList<Int> = mutableListOf()
        try {
            Class.forName("com.mysql.jdbc.Driver")
            val connection = DriverManager.getConnection(
                HomeFragment.url,
                HomeFragment.username,
                HomeFragment.password
            )
            val statement = connection.createStatement()
            val rs = statement.executeQuery("SELECT * FROM cart WHERE user_id = ${user_id}")

            while (rs.next()) {
                product_id_list.add(rs.getInt(2))
            }
            connection.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return product_id_list
    }

    @Singleton
    @Provides
    fun getCartProduct(product_id_list: MutableList<Int>): MutableList<Product> {
        val productList: MutableList<Product> = mutableListOf()

        try {
            Class.forName("com.mysql.jdbc.Driver")
            val connection = DriverManager.getConnection(
                HomeFragment.url,
                HomeFragment.username,
                HomeFragment.password
            )

            val statement = connection.createStatement()
            for (i in 0..product_id_list.size-1) {
                val rs = statement.executeQuery("SELECT * FROM " + HomeFragment.TABLE_NAME + " WHERE product_id = ${product_id_list[i]}")

                while (rs.next()) {
                    val product = Product(
                        rs.getInt(1),
                        rs.getString(2),
                        "${rs.getString(3).substring(0, rs.getString(3).length - 3)},${
                            rs.getString(3)
                                .substring(rs.getString(3).length - 3, rs.getString(3).length)
                        } 원",
                        rs.getString(4)
                    )
                    productList.add(product)
                }
            }
            connection.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return productList
    }

    @Singleton
    @Provides
    fun addCallendar(order_num: Int, product_id: Int, user_id: Int, product_count: Int, order_success : Int, product_price : Int, delivery_date : Date) {
        try {
            Class.forName("com.mysql.jdbc.Driver")
            val connection = DriverManager.getConnection(
                HomeFragment.url,
                HomeFragment.username,
                HomeFragment.password
            )
            val statement = connection.createStatement()

            statement.execute("INSERT INTO product_order(order_num, product_id, user_id, product_count, order_success, product_price, delivery_date) VALUES('" + order_num + "', '" + product_id + "', '" + user_id + "', '" + product_count + "', '" + order_success + "', '" + product_price + "', '" + delivery_date + "')")
            connection.close()
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }

    @Singleton
    @Provides
    fun getProductCountByUserId(product_id : Int, user_id: Int): Int {
        var productCount: Int = 0

        try {
            Class.forName("com.mysql.jdbc.Driver")
            val connection = DriverManager.getConnection(
                HomeFragment.url,
                HomeFragment.username,
                HomeFragment.password
            )

            val statement = connection.createStatement()
            val rs = statement.executeQuery("SELECT product_count FROM cart WHERE user_id = ${user_id} and product_id = ${product_id}")

            while (rs.next()) {
                productCount = rs.getInt(1)
            }

            connection.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return productCount
    }

    @Singleton
    @Provides
    fun getProduct(product_id : Int): Product? {
        var product : Product? = null

        try {
            Class.forName("com.mysql.jdbc.Driver")
            val connection = DriverManager.getConnection(
                HomeFragment.url,
                HomeFragment.username,
                HomeFragment.password
            )

            val statement = connection.createStatement()
            val rs = statement.executeQuery("SELECT * FROM product WHERE product_id = ${product_id}")

            while (rs.next()) {
                product = Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4))
                return product
            }

            connection.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return product
    }

    @Singleton
    @Provides
    fun getAllCallendarDate(user_id: Int): MutableList<ProductOrder> {
        val productOrderList : MutableList<ProductOrder> = mutableListOf()

        try {
            Class.forName("com.mysql.jdbc.Driver")
            val connection = DriverManager.getConnection(
                HomeFragment.url,
                HomeFragment.username,
                HomeFragment.password
            )

            val statement = connection.createStatement()
            val rs = statement.executeQuery("SELECT * FROM product_order WHERE user_id = ${user_id}")

            while (rs.next()) {
                val productOrder = ProductOrder(
                    rs.getInt(1),
                    rs.getInt(2),
                    rs.getInt(3),
                    rs.getInt(4),
                    rs.getInt(5),
                    rs.getInt(6),
                    rs.getDate(7)
                )
                productOrderList.add(productOrder)
            }

            connection.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return productOrderList
    }

    @Singleton
    @Provides
    fun getOrderNum(product_id: Int, day : Date): Int {
        var orderRealNum : Int = 0

        try {
            Class.forName("com.mysql.jdbc.Driver")
            val connection = DriverManager.getConnection(
                HomeFragment.url,
                HomeFragment.username,
                HomeFragment.password
            )

            val statement = connection.createStatement()

            val rs = statement.executeQuery("SELECT * FROM product_order WHERE product_id = ${product_id} and delivery_date = ${day}")

            Log.d("this", "${day} dayday")
            while (rs.next()) {
                orderRealNum = rs.getInt(1)
            }

            connection.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return orderRealNum
    }

    @Singleton
    @Provides
    fun removeOrder(order_num: Int){
        try {
            Class.forName("com.mysql.jdbc.Driver")
            val connection = DriverManager.getConnection(
                HomeFragment.url,
                HomeFragment.username,
                HomeFragment.password
            )

            val statement = connection.createStatement()

            val rs = statement.executeQuery("DELETE FROM product_order WHERE order_num = ${order_num}")


            connection.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    @Singleton
    @Provides
    fun getAllrecommend(user_id: Int): MutableList<Int> {
        val productList : MutableList<Int> = mutableListOf()

        try {
            Class.forName("com.mysql.jdbc.Driver")
            val connection = DriverManager.getConnection(
                HomeFragment.url,
                HomeFragment.username,
                HomeFragment.password
            )

            val statement = connection.createStatement()
            val rs = statement.executeQuery("SELECT * FROM recommend_table WHERE user_id = ${user_id}")

            while (rs.next()) {
                productList.add(rs.getInt(2))
            }

            connection.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return productList
    }
}