package com.example.haminjast

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import com.example.haminjast.data.model.Ad
import com.example.haminjast.data.model.AdStatus
import com.example.haminjast.data.model.AddPoster
import com.example.haminjast.data.model.Address
import com.example.haminjast.data.model.Address1Poster
import com.example.haminjast.data.model.AddressAddPoster
import com.example.haminjast.data.model.EntityResponseForPoster
import com.example.haminjast.data.model.Image
import com.example.haminjast.data.model.Location
import com.example.haminjast.data.model.Poster
import com.example.haminjast.data.model.RequestBodyForAddPoster
import com.example.haminjast.data.model.Tag
import com.example.haminjast.data.model.Tag1Poster
import com.example.haminjast.data.model.posterToUiPoster
import com.example.haminjast.data.model.toUiPoster
import com.example.haminjast.ui.component.secondsToMinuteAndSecondsString
import com.example.haminjast.ui.model.Contact
import com.example.haminjast.ui.model.PosterStatus
import com.example.haminjast.ui.model.UiConversation
import com.example.haminjast.ui.model.UiMessage
import com.example.haminjast.ui.model.UiPoster
import com.example.haminjast.ui.navigation.Ads
import com.example.haminjast.ui.screen.chat.component.MessageType
import org.junit.Test

import org.junit.Assert.*
import kotlin.random.Random

class UnitTest {

    @Test
    fun `test RequestBodyForAddPoster properties`() {
        val address = AddressAddPoster(
            "Sample Address",
            "Sample City",
            123.456,
            789.012,
            "Sample Province"
        )

        val requestBody = RequestBodyForAddPoster(
            listOf(address),
            listOf("url1", "url2"),
            AddPoster(
                true,
                42,
                true,
                "Sample Description",
                "Sample Type",
                "Sample State",
                "Sample Status",
                "sampleTelId",
                "Sample Title",
                "sampleUserPhone"
            ),
            listOf("tag1", "tag2")
        )

        assertEquals(listOf(address), requestBody.addresses)
        assertEquals(listOf("url1", "url2"), requestBody.img_urls)
        assertEquals(
            AddPoster(
                true,
                42,
                true,
                "Sample Description",
                "Sample Type",
                "Sample State",
                "Sample Status",
                "sampleTelId",
                "Sample Title",
                "sampleUserPhone"
            ),
            requestBody.poster
        )
        assertEquals(listOf("tag1", "tag2"), requestBody.tags)
    }


    @Test
    fun testToUiPoster() {
        val entityResponseForPoster = EntityResponseForPoster(
            id = 1,
            title = "Mock Title",
            status = "lost",
            description = "Mock Description",
            telegramId = "Mock Telegram ID",
            userPhone = "1234567890",
            addresses = listOf(
                Address1Poster(
                    id = 1,
                    createdAt = "2023-01-01T12:00:00",
                    updatedAt = "2023-01-02T12:00:00",
                    deletedAt = null,
                    province = "Mock Province",
                    city = "Mock City",
                    addressDetail = "Mock Address Detail",
                    latitude = 123.456,
                    longitude = 789.012,
                    addressId = 1
                )
            ),
            images = listOf(
                Image(
                    id = 1,
                    createdAt = "2023-01-01T12:00:00",
                    updatedAt = "2023-01-02T12:00:00",
                    deletedAt = null,
                    url = "image1.jpg",
                    posterId = 1
                )
            ),
            tags = listOf(
                Tag1Poster(
                    id = 1,
                    createdAt = "2023-01-01T12:00:00",
                    updatedAt = "2023-01-02T12:00:00",
                    deletedAt = null,
                    name = "Tag1",
                    state = "Mock State",
                    posters = null
                )
            ),
            userId = 123,
            award = 100,
            createdAt = "2023-01-01T12:00:00",
            updatedAt = "2023-01-02T12:00:00",
            state = "Mock State",
            specialType = "Mock Special Type"
        )

        val expectedUiPoster = UiPoster(
            id = 1,
            title = "Mock Title",
            description = "Mock Description",
            imageUrls = listOf("image1.jpg"),
            timeCreatedTimeStamp = 0L,
            status = PosterStatus.Lost,
            vicinity = "Mock Province",
            reward = 100L,
            lat = 123.456,
            lng = 789.012,
            issuerId = 123,
            contacts = listOf(
                Contact("شماره تماس", "1234567890"),
                Contact("تلگرام", "Mock Telegram ID")
            )
        )

        val actualUiPoster = entityResponseForPoster.toUiPoster()
        assertEquals(expectedUiPoster, actualUiPoster)
    }

    @Test
    fun testPosterToUiPoster() {
        val poster = Poster(
            addresses = listOf(
                Address(
                    address_detail = "Mock Address",
                    city = "Mock City",
                    location = Location(lat = 123.456, lon = 789.012),
                    province = "Mock Province"
                )
            ),
            alert = true,
            award = 100,
            chat = true,
            created_at = "2023-01-01T12:00:00",
            description = "Mock Description",
            id = 1,
            images = listOf("image1.jpg", "image2.jpg"),
            special_type = "Mock Special Type",
            state = "Mock State",
            status = "lost",
            tags = listOf(Tag(id = 1, name = "Tag1", state = "Mock State")),
            tel_id = "Mock Telegram ID",
            title = "Mock Title",
            updated_at = "2023-01-02T12:00:00",
            user_id = 123,
            user_phone = "1234567890"
        )

        val expectedUiPoster = UiPoster(
            id = 1,
            title = "Mock Title",
            description = "Mock Description",
            imageUrls = listOf("image1.jpg", "image2.jpg"),
            timeCreatedTimeStamp = 0,
            status = PosterStatus.Lost,
            vicinity = "Mock Address",
            reward = 100L,
            lat = 123.456,
            lng = 789.012,
            issuerId = 123,
            contacts = listOf(
                Contact("شماره تماس", "1234567890"),
                Contact("تلگرام", "Mock Telegram ID")
            )
        )

        val actualUiPoster = posterToUiPoster(poster)
        assertEquals(expectedUiPoster, actualUiPoster)
    }
    @Test
    fun adPropertiesTest() {

        val ad = Ad(
            title = "Lost Cat",
            desc = "A black cat with green eyes",
            status = AdStatus.LOST,
            imgUrl = "http://example.com/cat.jpg",
            date = "2023-01-01",
            location = "City Park"
        )

        assertEquals("Lost Cat", ad.title)
        assertEquals("A black cat with green eyes", ad.desc)
        assertEquals(AdStatus.LOST, ad.status)
        assertEquals("http://example.com/cat.jpg", ad.imgUrl)
        assertEquals("2023-01-01", ad.date)
        assertEquals("City Park", ad.location)
    }

    @Test
    fun adStatusEnumTest() {

        val lostStatusStringRes = AdStatus.LOST.stringRes
        val foundStatusStringRes = AdStatus.FOUND.stringRes

        assertEquals(R.string.lost, lostStatusStringRes)
        assertEquals(R.string.found, foundStatusStringRes)
    }
    @Test
    fun testAdsProperties() {

        val expectedIcon = Icons.Filled.Home
        val expectedRoute = "ads"

        val actualIcon = Ads.icon
        val actualRoute = Ads.route

        assertEquals(expectedIcon, actualIcon)
        assertEquals(expectedRoute, actualRoute)
    }

    @Test
    fun `test secondsToMinuteAndSecondsString`() {
        val seconds = 90

        val result = secondsToMinuteAndSecondsString(seconds)

        assertEquals("01:30", result)
    }


    @Test
    fun testConversationListSize() {
        val fakeConversationList = mutableListOf<UiConversation>().apply {
            for (i in 0..10) {
                add(
                    UiConversation(
                        id = i,
                        title = "دوچرخه",
                        imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/3/3c/WalletMpegMan.jpg/500px-WalletMpegMan.jpg",
                        lastMessage = "سلام. دوچرخه رو پیدا کردم. اگه میخوای بیا بگیرش",
                        lastMessageDate = "۳ دقیقه پیش",
                        unreadCount = Random(i).nextInt(0, 10),
                        myPoster = Random(i).nextBoolean(),
                    )
                )
            }
        }

        assertEquals(11, fakeConversationList.size)
    }

    @Test
    fun `test UiConversation properties`() {

        val id = 1
        val title = "Test Conversation"
        val imageUrl = "http://example.com/image.jpg"
        val lastMessage = "Hello, how are you?"
        val lastMessageDate = "2023-11-17"
        val unreadCount = 3
        val myPoster = true


        val uiConversation = UiConversation(
            id = id,
            title = title,
            imageUrl = imageUrl,
            lastMessage = lastMessage,
            lastMessageDate = lastMessageDate,
            unreadCount = unreadCount,
            myPoster = myPoster
        )


        assertEquals(id, uiConversation.id)
        assertEquals(title, uiConversation.title)
        assertEquals(imageUrl, uiConversation.imageUrl)
        assertEquals(lastMessage, uiConversation.lastMessage)
        assertEquals(lastMessageDate, uiConversation.lastMessageDate)
        assertEquals(unreadCount, uiConversation.unreadCount)
        assertEquals(myPoster, uiConversation.myPoster)
    }

    @Test
    fun `test UiMessage properties`() {
        // Given
        val id = 1
        val text = "Hello, how are you?"
        val type = MessageType.Incoming
        val date = "2023-11-17"
        val time = "12:30 PM"

        // When
        val uiMessage = UiMessage(
            id = id,
            text = text,
            type = type,
            date = date,
            time = time
        )

        // Then
        assertEquals(id, uiMessage.id)
        assertEquals(text, uiMessage.text)
        assertEquals(type, uiMessage.type)
        assertEquals(date, uiMessage.date)
        assertEquals(time, uiMessage.time)
    }


    @Test
    fun `test UiPoster properties`() {
        // Given
        val id = 1
        val title = "Lost item"
        val description = "A description of the lost item"
        val imageUrls = listOf("http://example.com/image.jpg")
        val timeCreatedTimeStamp = System.currentTimeMillis()
        val status = PosterStatus.Lost
        val vicinity = "Nearby location"
        val reward = 100L
        val lat = 37.7749
        val lng = -122.4194
        val issuerId = 123
        val contacts = listOf(Contact("John Doe", "john@example.com"))

        // When
        val uiPoster = UiPoster(
            id = id,
            title = title,
            description = description,
            imageUrls = imageUrls,
            timeCreatedTimeStamp = timeCreatedTimeStamp,
            status = status,
            vicinity = vicinity,
            reward = reward,
            lat = lat,
            lng = lng,
            issuerId = issuerId,
            contacts = contacts
        )

        // Then
        assertEquals(id, uiPoster.id)
        assertEquals(title, uiPoster.title)
        assertEquals(description, uiPoster.description)
        assertEquals(imageUrls, uiPoster.imageUrls)
        assertEquals(timeCreatedTimeStamp, uiPoster.timeCreatedTimeStamp)
        assertEquals(status, uiPoster.status)
        assertEquals(vicinity, uiPoster.vicinity)
        assertEquals(reward, uiPoster.reward)
    }
}