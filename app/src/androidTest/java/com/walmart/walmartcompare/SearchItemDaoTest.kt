package com.walmart.walmartcompare

import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.walmart.walmartcompare.db.SearchItemDatabase
import com.walmart.walmartcompare.model.SearchItem
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by Midori on 9/17/18
 */
@RunWith(AndroidJUnit4::class)
class SearchItemDaoTest {
    lateinit var searchItemDatabase: SearchItemDatabase
    private val searchItemEntityList: MutableList<SearchItem> = mutableListOf()
    private val searchItemEntity = SearchItem(ITEM_ID, PARENT_ITEM_ID, NAME, MSRP, SALE_PRICE,
            UPC, CATEGORY_PATH, CATEGORY_NODE, SHORT_DESCRIPTION, LONG_DESCRIPTION, BRAND_NAME,
            THUMBNAIL_IMAGE, MEDIUM_IMAGE, LARGE_IMAGE, PRODUCT_TRACKING_URL,
            NINETY_SEVEN_CENT_SHIPPING, STANDARD_SHIP_RATE, TWO_THREE_DAY_SHIPPING_RATE, COLOR,
            MARKETPLACE, SELLER_INFO, SHIP_TO_STORE, FREE_SHIP_TO_STORE, MODEL_NUMBER,
            PRODUCT_URL, AVAILABLE_ONLINE, STOCK, CUSTOMER_RATING, CUSTOMER_RATING_IMAGE,
            NUM_REVIEWS, CLEARANCE, PRE_ORDER, PRE_ORDER_SHIPS_ON, OFFER_TYPE, RHID, BUNDLE,
            ADD_TO_CART_URL, AFFILIATE_ADD_TO_CART_URL, FREE_SHIPPING_OVER_35_DOLLARS, GENDER,
            AGE, IS_TWO_DAY_SHIPPING_ELIGIBLE)
    private val searchItemEntity2 = SearchItem(ITEM_ID_2, PARENT_ITEM_ID_2, NAME_2, MSRP_2, SALE_PRICE_2,
            UPC_2, CATEGORY_PATH_2, CATEGORY_NODE_2, SHORT_DESCRIPTION_2, LONG_DESCRIPTION_2,
            BRAND_NAME_2,
            THUMBNAIL_IMAGE_2, MEDIUM_IMAGE_2, LARGE_IMAGE_2, PRODUCT_TRACKING_URL_2,
            NINETY_SEVEN_CENT_SHIPPING_2, STANDARD_SHIP_RATE_2, TWO_THREE_DAY_SHIPPING_RATE_2, COLOR_2,
            MARKETPLACE_2, SELLER_INFO_2, SHIP_TO_STORE_2, FREE_SHIP_TO_STORE_2, MODEL_NUMBER_2,
            PRODUCT_URL_2, AVAILABLE_ONLINE_2, STOCK_2, CUSTOMER_RATING_2, CUSTOMER_RATING_IMAGE_2,
            NUM_REVIEWS_2, CLEARANCE_2, PRE_ORDER_2, PRE_ORDER_SHIPS_ON_2, OFFER_TYPE_2, RHID_2, BUNDLE_2,
            ADD_TO_CART_URL_2, AFFILIATE_ADD_TO_CART_URL_2, FREE_SHIPPING_OVER_35_DOLLARS_2, GENDER_2,
            AGE_2, IS_TWO_DAY_SHIPPING_ELIGIBLE_2);
    companion object {
        const val ITEM_ID = 825997802
        const val PARENT_ITEM_ID = 682696374
        const val NAME = "Better Homes and Gardens 4-Cube Organizer Bench, Multiple Finishes"
        const val MSRP = 72.0
        const val SALE_PRICE = 79.99
        const val UPC = "764053472305"
        const val CATEGORY_PATH = "Home/Furniture/Bedroom Furniture/Benches"
        const val CATEGORY_NODE = "4044_103150_102547"
        const val SHORT_DESCRIPTION = "Better Homes and Gardens 4-Cube Organizer Bench, Weathered"
        const val LONG_DESCRIPTION = "* Versatile Design Creates Multiple Storage &amp; Extra Seating &lt;br&gt;" +
                "* Transitional Style Great for Entryway, Mudroom or Living Space &lt;br&gt;" +
                "* Comfortable Neutral Beige-Toned Faux Linen Upholstered Cushion &lt;br&gt;" +
                "* Compatible with our 13 inch BHG Storage Bins &amp; Baskets &lt;br&gt;" +
                "* Solid Black Finish"
        const val BRAND_NAME = "Better Homes and Gardens"
        const val THUMBNAIL_IMAGE = "https://i5.walmartimages.com/asr/d5060a04-1d6f-4fa8-9c65-6dd935b6072e_1.544273490d71eed6148751bbe10818d6.jpeg?odnHeight=100&odnWidth=100&odnBg=FFFFFF"
        const val MEDIUM_IMAGE = "https://i5.walmartimages.com/asr/d5060a04-1d6f-4fa8-9c65-6dd935b6072e_1.544273490d71eed6148751bbe10818d6.jpeg?odnHeight=180&odnWidth=180&odnBg=FFFFFF"
        const val LARGE_IMAGE = "https://i5.walmartimages.com/asr/d5060a04-1d6f-4fa8-9c65-6dd935b6072e_1.544273490d71eed6148751bbe10818d6.jpeg?odnHeight=450&odnWidth=450&odnBg=FFFFFF"
        const val PRODUCT_TRACKING_URL = "http://linksynergy.walmart.com/fs-bin/click?id=|LSNID|&offerid=223073.7200&type=14&catid=8&subid=0&hid=7200&tmpid=1082&RD_PARM1=https%253A%252F%252Fwww.walmart.com%252Fip%252FBetter-Homes-and-Gardens-4-Cube-Organizer-Bench-Multiple-Finishes%252F825997802%253Faffp1%253Dpk5ybzF3PHr0M_fY48U5WZ6oAyzf8_vjtyuL5sS8jfg%2526affilsrc%253Dapi"
        const val NINETY_SEVEN_CENT_SHIPPING = false
        const val STANDARD_SHIP_RATE = 0.0
        const val TWO_THREE_DAY_SHIPPING_RATE = 0
        const val COLOR = "Weathered"
        const val MARKETPLACE = false
        const val SELLER_INFO = "Best Choice Products"
        const val SHIP_TO_STORE = false
        const val FREE_SHIP_TO_STORE = false
        const val MODEL_NUMBER = "BH47-022-499-08"
        const val PRODUCT_URL = "http://c.affil.walmart.com/t/api02?l=https%3A%2F%2Fwww.walmart.com%2Fip%2FBetter-Homes-and-Gardens-4-Cube-Organizer-Bench-Multiple-Finishes%2F825997802%3Faffp1%3Dpk5ybzF3PHr0M_fY48U5WZ6oAyzf8_vjtyuL5sS8jfg%26affilsrc%3Dapi%26veh%3Daff%26wmlspartner%3Dreadonlyapi"
        const val AVAILABLE_ONLINE = true
        const val STOCK = "Available"
        const val CUSTOMER_RATING = "4.294"
        const val CUSTOMER_RATING_IMAGE = "http://i2.walmartimages.com/i/CustRating/4_3.gif"
        const val NUM_REVIEWS = 17
        const val CLEARANCE = false
        const val PRE_ORDER= false
        const val PRE_ORDER_SHIPS_ON = "9/24/2018"
        const val OFFER_TYPE = "ONLINE_ONLY"
        const val RHID = "36298"
        const val BUNDLE = false
        const val ADD_TO_CART_URL = "http://c.affil.walmart.com/t/api02?l=http%3A%2F%2Faffil.walmart.com%2Fcart%2FaddToCart%3Fitems%3D825997802%7C1%26affp1%3Dpk5ybzF3PHr0M_fY48U5WZ6oAyzf8_vjtyuL5sS8jfg%26affilsrc%3Dapi%26veh%3Daff%26wmlspartner%3Dreadonlyapi"
        const val AFFILIATE_ADD_TO_CART_URL = "http://linksynergy.walmart.com/fs-bin/click?id=|LSNID|&offerid=223073.7200&type=14&catid=8&subid=0&hid=7200&tmpid=1082&RD_PARM1=http%253A%252F%252Faffil.walmart.com%252Fcart%252FaddToCart%253Fitems%253D825997802%257C1%2526affp1%253Dpk5ybzF3PHr0M_fY48U5WZ6oAyzf8_vjtyuL5sS8jfg%2526affilsrc%253Dapi"
        const val FREE_SHIPPING_OVER_35_DOLLARS = false
        const val GENDER = "none"
        const val AGE = "none"
        const val IS_TWO_DAY_SHIPPING_ELIGIBLE = false

        const val ITEM_ID_2 = 762669341
        const val PARENT_ITEM_ID_2 = 762669341
        const val NAME_2 = "Better Homes and Gardens 3-Cube Organizer Bench, Multiple Finishes"
        const val MSRP_2 = 89.1
        const val SALE_PRICE_2 = 69.99
        const val UPC_2 = "764053472305"
        const val CATEGORY_PATH_2 = "Home/Furniture/Bedroom Furniture/Benches"
        const val CATEGORY_NODE_2 = "4044_103150_102547"
        const val SHORT_DESCRIPTION_2 = "Better Homes and Gardens 3-Cube Organizer Bench, Rustic Gray"
        const val LONG_DESCRIPTION_2 = "Better Homes&amp;gardens Bhg Organizer Bench"
        const val BRAND_NAME_2 = "Better Homes and Gardens"
        const val THUMBNAIL_IMAGE_2 = "https://i5.walmartimages.com/asr/e2ad7377-8722-4703-ab6b-882c" +
                "7410d503_1.aec83e34e01ef65fecda8bf9fd639c98.jpeg?odnHeight=100&odnWidth=100&odnBg=FFFFFF"
        const val MEDIUM_IMAGE_2 = "https://i5.walmartimages.com/asr/e2ad7377-8722-4703-ab6b-882c7410" +
                "d503_1.aec83e34e01ef65fecda8bf9fd639c98.jpeg?odnHeight=180&odnWidth=180&odnBg=FFFFFF"
        const val LARGE_IMAGE_2 = "https://i5.walmartimages.com/asr/e2ad7377-8722-4703-ab6b-882c7410" +
                "d503_1.aec83e34e01ef65fecda8bf9fd639c98.jpeg?odnHeight=450&odnWidth=450&odnBg=FFFFFF"
        const val PRODUCT_TRACKING_URL_2 = "http://linksynergy.walmart.com/fs-bin/click?id=|LSNID|" +
                "&offerid=223073.7200&type=14&catid=8&subid=0&hid=7200&tmpid=1082&RD_PARM1=https%25" +
                "3A%252F%252Fwww.walmart.com%252Fip%252FBetter-Homes-and-Gardens-3-Cube-Organizer-" +
                "Bench-Multiple-Finishes%252F762669341%253Faffp1%253Dpk5ybzF3PHr0M_fY48U5WZ6oAyzf8_" +
                "vjtyuL5sS8jfg%2526affilsrc%253Dapi"
        const val NINETY_SEVEN_CENT_SHIPPING_2 = false
        const val STANDARD_SHIP_RATE_2 = 0.0
        const val TWO_THREE_DAY_SHIPPING_RATE_2 = 0
        const val COLOR_2 = "Weathered"
        const val MARKETPLACE_2 = false
        const val SELLER_INFO_2 = "Best Choice Products"
        const val SHIP_TO_STORE_2 = false
        const val FREE_SHIP_TO_STORE_2 = false
        const val MODEL_NUMBER_2 = "BH47-022-499-09"
        const val PRODUCT_URL_2 = "http://c.affil.walmart.com/t/api02?l=https%3A%2F%2Fwww.walmart.com" +
                "%2Fip%2FBetter-Homes-and-Gardens-3-Cube-Organizer-Bench-Multiple-Finishes%2F762669341" +
                "%3Faffp1%3Dpk5ybzF3PHr0M_fY48U5WZ6oAyzf8_vjtyuL5sS8jfg%26affilsrc%3Dapi%26veh%3Daff" +
                "%26wmlspartner%3Dreadonlyapi"
        const val AVAILABLE_ONLINE_2 = false
        const val STOCK_2 = "Not available"
        const val CUSTOMER_RATING_2 = "4.5"
        const val CUSTOMER_RATING_IMAGE_2 = "http://i2.walmartimages.com/i/CustRating/4_5.gif"
        const val NUM_REVIEWS_2 = 2
        const val CLEARANCE_2 = false
        const val PRE_ORDER_2 = false
        const val PRE_ORDER_SHIPS_ON_2 = "9/24/2018"
        const val OFFER_TYPE_2 = "ONLINE_ONLY"
        const val RHID_2 = "36298"
        const val BUNDLE_2 = false
        const val ADD_TO_CART_URL_2 = "http://c.affil.walmart.com/t/api02?l=http%3A%2F%2Faffil." +
                "walmart.com%2Fcart%2FaddToCart%3Fitems%3D762669341%7C1%26affp1%3Dpk5ybzF3PHr0M_fY" +
                "48U5WZ6oAyzf8_vjtyuL5sS8jfg%26affilsrc%3Dapi%26veh%3Daff%26wmlspartner%3Dreadonlyapi"
        const val AFFILIATE_ADD_TO_CART_URL_2 = "http://linksynergy.walmart.com/fs-bin/click?id=|LSNID" +
                "|&offerid=223073.7200&type=14&catid=8&subid=0&hid=7200&tmpid=1082&RD_PARM1=http" +
                "%253A%252F%252Faffil.walmart.com%252Fcart%252FaddToCart%253Fitems%253D762669341%257" +
                "C1%2526affp1%253Dpk5ybzF3PHr0M_fY48U5WZ6oAyzf8_vjtyuL5sS8jfg%2526affilsrc%253Dapi"
        const val FREE_SHIPPING_OVER_35_DOLLARS_2 = false
        const val GENDER_2 = "none"
        const val AGE_2 = "none"
        const val IS_TWO_DAY_SHIPPING_ELIGIBLE_2 = false
    }

    @Before
    fun initDb() {
        searchItemDatabase = Room.inMemoryDatabaseBuilder(
                InstrumentationRegistry.getContext(),
                SearchItemDatabase::class.java).build()
    }

    @After
    fun closeDb() {
        searchItemDatabase.close()
    }

    @Before
    fun setUp() {
        searchItemEntityList.add(SearchItem(ITEM_ID, PARENT_ITEM_ID, NAME, MSRP, SALE_PRICE,
                UPC, CATEGORY_PATH, CATEGORY_NODE, SHORT_DESCRIPTION, LONG_DESCRIPTION, BRAND_NAME,
                THUMBNAIL_IMAGE, MEDIUM_IMAGE, LARGE_IMAGE, PRODUCT_TRACKING_URL,
                NINETY_SEVEN_CENT_SHIPPING, STANDARD_SHIP_RATE, TWO_THREE_DAY_SHIPPING_RATE, COLOR,
                MARKETPLACE, SELLER_INFO, SHIP_TO_STORE, FREE_SHIP_TO_STORE, MODEL_NUMBER,
                PRODUCT_URL, AVAILABLE_ONLINE, STOCK, CUSTOMER_RATING, CUSTOMER_RATING_IMAGE,
                NUM_REVIEWS, CLEARANCE, PRE_ORDER, PRE_ORDER_SHIPS_ON, OFFER_TYPE, RHID, BUNDLE,
                ADD_TO_CART_URL, AFFILIATE_ADD_TO_CART_URL, FREE_SHIPPING_OVER_35_DOLLARS, GENDER,
                AGE, IS_TWO_DAY_SHIPPING_ELIGIBLE))
    }

    @Test
    fun insertAndReadSearchItemEntityListAndValidateListNotEmpty() {
        val tempList = listOf(searchItemEntity)
        searchItemDatabase.searchItemsDao().insert(tempList)
        val tempSearchItemEntityList = LiveDataTestUtil.getValue(searchItemDatabase.searchItemsDao
        ().getSearchResults())
        assertNotNull("newSearchItemEntityList must not be null.", tempSearchItemEntityList)
    }

    @Test
    fun insertAndReadSearchItemEntityAndValidateQuantityOfElementsReturned() {
        searchItemDatabase.searchItemsDao().insert(searchItemEntityList)
        val tempSearchItemEntityList = LiveDataTestUtil.getValue(searchItemDatabase.searchItemsDao
        ().getSearchResults())
        assertEquals("SearchItemEntityList size must not be 1", tempSearchItemEntityList.size, 1)
    }

    @Test
    fun getAllSearchItems() {
        searchItemEntityList.add(searchItemEntity2)
        searchItemDatabase.searchItemsDao().insert(searchItemEntityList)
        val tempSearchItemEntityList = LiveDataTestUtil.getValue(searchItemDatabase.searchItemsDao
        ().getSearchResults())
        assertEquals(searchItemEntity2.itemId, tempSearchItemEntityList.get(0).itemId)
        assertEquals(searchItemEntity.itemId, tempSearchItemEntityList.get(1).itemId)
    }
}
