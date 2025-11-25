package com.example.projectdraft

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    /*The parameter application is the whole application context. There are two different
    views, ViewModel and an AndroidViewModel. They both hold UI-related data but
    AndroidViewModel gives you access to the application context which Room needs
    to be able to create a database instance(next line)*/

    private val _products = MutableStateFlow<List<ProductWithName>>(emptyList())
    /*This line creates a variable that holds a list of whatever you will access using a
    * dao method. We write emptyList to initialize it. The list contains nothing yet
    * MutableStateFlow makes the list contents change if changes are made. This is a kind
    * of state but it is not a composable state. You'll see it converted to a Composable state
    * in the Fragment. This acts just like a state but Compose doesn't know about its changes directly
    * Sth you need to know is that MutableStateFlow only contains one value at a time
    * meaning only one list at a time. If we use getAllProducts() method, it will contain
    * only the list of all products. If you change the method to getAllCategories, that
    * is what _products will now contain. I know you are probably wondering why we have a somewhat
    * state that yes is observable but is private so can't be accessed outside this ViewModel. That
    * is what the next line is for*/
    val products: StateFlow<List<ProductWithName>> get() = _products
    /*So this is a public list that is now available to everything outside this view model.
    * This uses StateFlow instead of MutableStateFlow to make it read-only. Since this variable
    * is accessible to outsiders, we want to ensure that no accidental changes can be made by
    * outsiders. The first _products was private, right? So that the only thing that can change it
    * is this view model. Ok so what's the point of this then? This variable gets its data from
    * _products. When a change occurs, MutableStateFlow has the new list. StateFlow usually
    * contains only the updates so once MutableStateFlow changes, this changes too.
    * So using a rough explanation: MutableStateFlow in _products tells products that there are
    * updates then StateFlow in products tells Compose(after it hase been converted to a Composable
    * state) that there are updates. But to be safe, Compose elements outside can't edit the database,
    * only View Model can do it here.*/

    private val db = DatabaseProvider.getDatabase(application)
    //Now we access the database
    private val productDao = db.productDao()
    private val categoriesDao = db.categoriesDao()
    //Then we access the dao method we want using the database

    /*init {
        // init block populates database and loads initial data
        viewModelScope.launch {
            //viewModelScope.launch runs database operations in a background thread.

            withContext(Dispatchers.IO){
                if (categoriesDao.countCategories() == 0) {
                    insertDefaultCategories()
                    // wait until they’re actually in the DB
                    /*
                    while (categoriesDao.countCategories() == 0) {
                        delay(10)   // tiny pause – you can also use a suspend‑ready signal
                        //This is so that I can ensure there are foreign keys before using them in the next line
                    }*/

                    if (productDao.countProducts() == 0) {
                        insertDefaultProducts()
                        /*
                        while (productDao.countProducts() == 0) {
                            delay(10)
                        }//Another delay fto ensure the products have been inserted before we load them*/
                    }
                }

                loadAllProducts()
                /*The above if statement means that if the table is empty, insert the default products
                * so they'll be the first products. If it isn't, then just load the items in the
                * products table. It ensures that the default items are not inserted over and over again*/
            }
        }
    }*/
    init {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val categoriesCount = categoriesDao.countCategories()
                if (categoriesCount == 0) {
                    val ids = insertDefaultCategories()
                    insertDefaultProducts(ids)
                }
                loadAllProducts()
            }
        }
    }

    suspend fun insertDefaultCategories(): Map<String, Int> {
        val categoryIds = mutableMapOf<String, Int>()
        categoryIds["Electronics"] = categoriesDao.insertCategory(CategoriesEntity(name = "Electronics")).toInt()
        Log.d("HomeViewModel", "Inserted category: Electronics")
        categoryIds["Pastries"] = categoriesDao.insertCategory(CategoriesEntity(name = "Pastries")).toInt()
        Log.d("HomeViewModel", "Inserted category: Pastries")
        categoryIds["Detergents"] = categoriesDao.insertCategory(CategoriesEntity(name = "Detergents")).toInt()
        Log.d("HomeViewModel", "Inserted category: Detergents")
        categoryIds["Drinks"] = categoriesDao.insertCategory(CategoriesEntity(name = "Drinks")).toInt()
        Log.d("HomeViewModel", "Inserted category: Drinks")
        categoryIds["Beauty"] = categoriesDao.insertCategory(CategoriesEntity(name = "Beauty")).toInt()
        Log.d("HomeViewModel", "Inserted category: Beauty")
        categoryIds["Organic"] = categoriesDao.insertCategory(CategoriesEntity(name = "Organic")).toInt()
        Log.d("HomeViewModel", "Inserted category: Organic")
        return categoryIds
    }

    /*
    suspend fun insertDefaultCategories() {
        Log.d("HomeViewModel", "Inserting default categories...")
        categoriesDao.insertCategory(CategoriesEntity(name = "Electronics"))
        Log.d("HomeViewModel", "Inserted category: Electronics")
        categoriesDao.insertCategory(CategoriesEntity(name = "Pastries"))
        Log.d("HomeViewModel", "Inserted category: Pastries")
        categoriesDao.insertCategory(CategoriesEntity(name = "Detergents"))
        Log.d("HomeViewModel", "Inserted category: Detergents")
        categoriesDao.insertCategory(CategoriesEntity(name = "Drinks"))
        Log.d("HomeViewModel", "Inserted category: Drinks")
        categoriesDao.insertCategory(CategoriesEntity(name = "Beauty"))
        Log.d("HomeViewModel", "Inserted category: Beauty")
        categoriesDao.insertCategory(CategoriesEntity(name = "Organic"))
        Log.d("HomeViewModel", "Inserted category: Organics")
    }*/
    /*suspend fun insertDefaultProducts(){
        Log.d("HomeViewModel", "Inserting default products")
        // Home View default products
        productDao.insertProduct(ProductEntity(name ="Festive Bread", categoryId = 2, price = 5.99, imageRes = R.drawable.test_bread))
        Log.d("HomeViewModel", "Inserted product 1")
        productDao.insertProduct(ProductEntity(name ="Samsung 55\" TV", categoryId = 1 , price = 599.99, imageRes = R.drawable.test_tv))
        Log.d("HomeViewModel", "Inserted product 2")
        productDao.insertProduct(ProductEntity(name ="Hisense Washing Machine", categoryId = 3, price = 399.99, imageRes =R.drawable.test_washm))
        Log.d("HomeViewModel", "Inserted product 3")
        productDao.insertProduct(ProductEntity(name ="Samsung Fridge", categoryId = 1, price = 799.99, imageRes = R.drawable.test_fridge))
        Log.d("HomeViewModel", "Inserted product 4")
        productDao.insertProduct(ProductEntity(name ="Brookside Milk", categoryId = 4, price = 2.99, imageRes = R.drawable.test_milk))
        Log.d("HomeViewModel", "Inserted product 5")
        productDao.insertProduct(ProductEntity(name ="Ramtons Blender", categoryId = 1, price = 49.99, imageRes = R.drawable.test_blender))
        Log.d("HomeViewModel", "Inserted product 6")
    }*/
    suspend fun insertDefaultProducts(categoryIds: Map<String, Int>) {
        productDao.insertProduct(ProductEntity(name = "Festive Bread", categoryId = categoryIds["Pastries"]!!, price = 5.99, imageRes = R.drawable.test_bread))
        Log.d("HomeViewModel", "Inserted product Festive Bread")
        productDao.insertProduct(ProductEntity(name = "Samsung 55\" TV", categoryId = categoryIds["Electronics"]!!, price = 599.99, imageRes = R.drawable.test_tv))
        Log.d("HomeViewModel", "Inserted Samsung")
        productDao.insertProduct(ProductEntity(name = "Hisense Washing Machine", categoryId = categoryIds["Detergents"]!!, price = 399.99, imageRes = R.drawable.test_washm))
        Log.d("HomeViewModel", "Inserted Hisense Washing Machine")
        productDao.insertProduct(ProductEntity(name = "Samsung Fridge", categoryId = categoryIds["Electronics"]!!, price = 799.99, imageRes = R.drawable.test_fridge))
        Log.d("HomeViewModel", "Inserted Samsung Fridge")
        productDao.insertProduct(ProductEntity(name = "Brookside Milk", categoryId = categoryIds["Drinks"]!!, price = 2.99, imageRes = R.drawable.test_milk))
        Log.d("HomeViewModel", "Inserted Brookside Milk")
        productDao.insertProduct(ProductEntity(name = "Ramtons Blender", categoryId = categoryIds["Electronics"]!!, price = 49.99, imageRes = R.drawable.test_blender))
        Log.d("HomeViewModel", "Inserted Ramtons Blender")
    }


    fun loadAllProducts(){
        viewModelScope.launch {
            val allProducts = withContext(Dispatchers.IO) {
                productDao.getAllProductsWithCategoryName()
            }

            //A log to see if there's a problem with loading the products
            Log.d("HomeViewModel", "loadAllProducts: fetched ${allProducts.size} items")

            // Load all products into stateflow
            _products.value = allProducts
            /*We already ensured that products gets the values from _products so we don't need to
            repeat that*/
        }
    }

    // Optional: search function
    fun searchProducts(query: String) {
        viewModelScope.launch {
            val allProducts = productDao.getAllProductsWithCategoryName()
            _products.value = allProducts.filter {
                it.name.contains(query, ignoreCase = true)
                /*it → refers to each individual ProductEntity in the allProducts list.
                * it.name → the name of that product.If we wanted it to be compared with eg
                  id, we would've put it.id
                * .contains(query, ignoreCase = true) → checks if the product name contains the search
                  text typed by the user.
                * ignoreCase = true → matches regardless of upper/lowercase letters*/
            }
        }
    }
}
