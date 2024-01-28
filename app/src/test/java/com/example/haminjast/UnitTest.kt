package com.example.haminjast

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import com.example.haminjast.data.model.Ad
import com.example.haminjast.data.model.AdStatus
import com.example.haminjast.data.model.Address
import com.example.haminjast.data.model.Address1Poster
import com.example.haminjast.data.model.ConversationCoverResponse
import com.example.haminjast.data.model.ConversationHistoryResponse
import com.example.haminjast.data.model.CreatePosterRequest
import com.example.haminjast.data.model.EntityResponse
import com.example.haminjast.data.model.EntityResponseForPoster
import com.example.haminjast.data.model.Image
import com.example.haminjast.data.model.Location
import com.example.haminjast.data.model.Poster
import com.example.haminjast.data.model.Tag
import com.example.haminjast.data.model.Tag1Poster
import com.example.haminjast.data.model.posterToUiPoster
import com.example.haminjast.data.model.toUiPoster
import com.example.haminjast.ui.component.secondsToMinuteAndSecondsString
import com.example.haminjast.ui.model.Contact
import com.example.haminjast.ui.model.PosterStatus
import com.example.haminjast.ui.model.UiConversation
import com.example.haminjast.ui.model.UiPoster
import com.example.haminjast.ui.navigation.Ads
import com.google.gson.Gson
import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.random.Random

class UnitTest {

    @Test
    fun `test RequestBodyForAddPoster properties`() {
        val address = CreatePosterRequest.Addresse(
            addressDetail = "Mock Address",
            city = "Mock City",
            latitude = 35,
            longitude = 35,
            province = "Mock Province"
        )

        val requestBody = CreatePosterRequest(
            addresses = listOf(address),
            imgUrls = listOf("url1", "url2"),
            poster = CreatePosterRequest.Poster(
                alert = true,
                award = 42,
                chat = true,
                description = "Sample Description",
                specialType = "Sample Type",
                state = "Sample State",
                status = "Sample Status",
                telId = "sampleTelId",
                title = "Sample Title",
                userPhone = "sampleUserPhone"
            ),
            tags = listOf("tag1", "tag2")
        )

        assertEquals(listOf(address), requestBody.addresses)
        assertEquals(listOf("url1", "url2"), requestBody.imgUrls)
        assertEquals(
            CreatePosterRequest.Poster(
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
                    createdAt = 0L,
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
                    createdAt = 0L,
                    updatedAt = "2023-01-02T12:00:00",
                    deletedAt = null,
                    url = "image1.jpg",
                    posterId = 1
                )
            ),
            tags = listOf(
                Tag1Poster(
                    id = 1,
                    createdAt = 0L,
                    updatedAt = "2023-01-02T12:00:00",
                    deletedAt = null,
                    name = "Tag1",
                    state = "Mock State",
                    posters = null
                )
            ),
            userId = 123,
            award = 100,
            createdAt = 0L,
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

    @Test
    fun conversationCoverResponseItemTest() {
        // Create a LastMessage instance for testing
        val lastMessage = ConversationCoverResponse.ConversationCoverResponseItem.LastMessage(
            "Test Content",
            1,
            System.currentTimeMillis(),
            123,
            456,
            789,
            0,
            "sent",
            "text",
            System.currentTimeMillis()
        )

        // Create a ConversationCoverResponseItem instance for testing
        val conversationCoverResponseItem = ConversationCoverResponse.ConversationCoverResponseItem(
            1,
            "https://example.com/image.jpg",
            true,
            lastMessage,
            "John Doe",
            789
        )

        // Verify the values
        assertEquals(1, conversationCoverResponseItem.id)
        assertEquals("https://example.com/image.jpg", conversationCoverResponseItem.imageUrl)
        assertEquals(true, conversationCoverResponseItem.isOwner)
        assertEquals("John Doe", conversationCoverResponseItem.name)
        assertEquals(789, conversationCoverResponseItem.posterId)

        // Verify LastMessage values
        assertEquals("Test Content", conversationCoverResponseItem.lastMessage.content)
        assertEquals(1, conversationCoverResponseItem.lastMessage.conversationId)
        // Add more assertions based on your LastMessage structure
    }

    // If you want to test JSON deserialization, you can use Mockito to mock the deserialization process
    @Test
    fun jsonDeserializationTest() {
        val json = """
            {
                "id": 1,
                "image_url": "https://example.com/image.jpg",
                "is_owner": true,
                "name": "John Doe",
                "poster_id": 789,
                "last_message": {
                    "content": "Test Content",
                    "conversation_id": 1,
                    "created_at": ${System.currentTimeMillis()},
                    "id": 123,
                    "receiver_id": 456,
                    "sender_id": 789,
                    "sequence_no": 0,
                    "status": "sent",
                    "type": "text",
                    "updated_at": ${System.currentTimeMillis()}
                }
            }
        """.trimIndent()

        val gson = Gson() // Assuming Gson is available in your project

        val conversationCoverResponseItem = gson.fromJson(json, ConversationCoverResponse.ConversationCoverResponseItem::class.java)

        // Verify the deserialized values
        assertEquals(1, conversationCoverResponseItem.id)
        assertEquals("https://example.com/image.jpg", conversationCoverResponseItem.imageUrl)
        assertEquals(true, conversationCoverResponseItem.isOwner)
        assertEquals("John Doe", conversationCoverResponseItem.name)
        assertEquals(789, conversationCoverResponseItem.posterId)

        // Verify LastMessage values
        assertEquals("Test Content", conversationCoverResponseItem.lastMessage.content)
        assertEquals(1, conversationCoverResponseItem.lastMessage.conversationId)
        // Add more assertions based on your LastMessage structure
    }

    @Test
    fun conversationHistoryResponseTest() {
        // Create a Message instance for testing
        val message = ConversationHistoryResponse.Message(
            "Test Content",
            1,
            System.currentTimeMillis(),
            123,
            456,
            789,
            0,
            "sent",
            "text",
            System.currentTimeMillis()
        )

        // Create a ConversationHistoryResponse instance for testing
        val conversationHistoryResponse = ConversationHistoryResponse(
            listOf(message),
            789
        )

        // Verify the values
        assertEquals(789, conversationHistoryResponse.userId)

        // Verify Message values
        assertEquals("Test Content", conversationHistoryResponse.messages[0].content)
        assertEquals(1, conversationHistoryResponse.messages[0].conversationId)
        // Add more assertions based on your Message structure
    }

    // If you want to test JSON deserialization, you can use Mockito to mock the deserialization process
    @Test
    fun jsonDeserializationTest2() {
        val json = """
            {
                "messages": [
                    {
                        "content": "Test Content",
                        "conversation_id": 1,
                        "created_at": ${System.currentTimeMillis()},
                        "id": 123,
                        "receiver_id": 456,
                        "sender_id": 789,
                        "sequence_no": 0,
                        "status": "sent",
                        "type": "text",
                        "updated_at": ${System.currentTimeMillis()}
                    }
                ],
                "user_id": 789
            }
        """.trimIndent()

        val gson = Gson() // Assuming Gson is available in your project

        val conversationHistoryResponse = gson.fromJson(json, ConversationHistoryResponse::class.java)

        // Verify the deserialized values
        assertEquals(789, conversationHistoryResponse.userId)

        // Verify Message values
        assertEquals("Test Content", conversationHistoryResponse.messages[0].content)
        assertEquals(1, conversationHistoryResponse.messages[0].conversationId)
        // Add more assertions based on your Message structure
    }

    @Test
    fun createPosterRequestTest() {
        // Create test data
        val addresses = listOf(
            CreatePosterRequest.Addresse(
                "Detail",
                "City",
                123,
                456,
                "Province"
            )
        )
        val imgUrls = listOf("url1", "url2")
        val poster = CreatePosterRequest.Poster(
            true,
            42,
            true,
            "Test Description",
            "Special",
            "State",
            "Status",
            "123456",
            "Test Title",
            "7890123456"
        )
        val tags = listOf("tag1", "tag2")

        // Create a CreatePosterRequest instance for testing
        val createPosterRequest = CreatePosterRequest(addresses, imgUrls, poster, tags)

        // Verify the values
        assertEquals(addresses, createPosterRequest.addresses)
        assertEquals(imgUrls, createPosterRequest.imgUrls)
        assertEquals(poster, createPosterRequest.poster)
        assertEquals(tags, createPosterRequest.tags)
    }

    // If you want to test JSON deserialization, you can use Mockito to mock the deserialization process
    @Test
    fun jsonDeserializationTest3() {
        val json = """
            {
                "addresses": [
                    {
                        "address_detail": "Detail",
                        "city": "City",
                        "latitude": 123,
                        "longitude": 456,
                        "province": "Province"
                    }
                ],
                "img_urls": ["url1", "url2"],
                "poster": {
                    "alert": true,
                    "award": 42,
                    "chat": true,
                    "description": "Test Description",
                    "special_type": "Special",
                    "state": "State",
                    "status": "Status",
                    "tel_id": "123456",
                    "title": "Test Title",
                    "user_phone": "7890123456"
                },
                "tags": ["tag1", "tag2"]
            }
        """.trimIndent()

        val gson = Gson() // Assuming Gson is available in your project

        val createPosterRequest = gson.fromJson(json, CreatePosterRequest::class.java)

        // Verify the deserialized values
        // You can use assertEquals for each field similar to the previous test
        assertEquals("Detail", createPosterRequest.addresses[0].addressDetail)
        assertEquals("url1", createPosterRequest.imgUrls[0])
        // Add more assertions based on your class structure
    }

    @Test
    fun entityResponseWithMaxPageAndTotal() {
        val maxPage = 10
        val total = 100
        val posters = listOf(Poster(
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
        ))

        val entityResponse = EntityResponse(maxPage, posters, total)

        assertEquals(maxPage, entityResponse.max_page)
        assertEquals(100, entityResponse.total)
        assertEquals(posters, entityResponse.posters)
    }

    @Test
    fun createValidPosterFromDataClass() {
        val addresses = listOf(Address(
            address_detail = "Mock Address",
            city = "Mock City",
            location = Location(lat = 123.456, lon = 789.012),
            province = "Mock Province"
        ))
        val alert = true
        val award = 100
        val chat = true
        val createdAt = "2023-10-04T15:23:23Z"
        val description = "This is a description"
        val id = 1
        val images = listOf("imageUrl1", "imageUrl2")
        val specialType = "Lost"
        val state = "active"
        val status = "lost"
        val tags = listOf(Tag(id = 1, name = "Tag1", state = "active"))
        val telId = "1234567890"
        val title = "My lost item"
        val updatedAt = "2023-10-04T15:23:23Z"
        val userId = 1
        val userPhone = "0912345678"

        val poster = Poster(addresses, alert, award, chat, createdAt, description, id, images, specialType, state, status, tags, telId, title, updatedAt, userId, userPhone)

        assertEquals(addresses, poster.addresses)
        assertEquals(award, poster.award)
        assertEquals(createdAt, poster.created_at)
        assertEquals(description, poster.description)
        assertEquals(id, poster.id)
        assertEquals(images, poster.images)
        assertEquals(specialType, poster.special_type)
        assertEquals(state, poster.state)
        assertEquals(status, poster.status)
        assertEquals(tags, poster.tags)
        assertEquals(telId, poster.tel_id)
        assertEquals(title, poster.title)
        assertEquals(updatedAt, poster.updated_at)
        assertEquals(userId, poster.user_id)
        assertEquals(userPhone, poster.user_phone)
    }

    @Test
    fun createValidAddressFromDataClass() {
        val addressDetail = "Vicinity"
        val city = "City"
        val location = Location(lat = 1.234, lon = 3.456)
        val province = "Province"

        val address = Address(addressDetail, city, location, province)

        assertEquals(addressDetail, address.address_detail)
        assertEquals(city, address.city)
        assertEquals(location, address.location)
        assertEquals(province, address.province)
    }

    @Test
    fun createValidLocationFromDataClass() {
        val lat = 1.234
        val lon = 3.456

        val location = Location(lat, lon)

        assertEquals(lat, location.lat,0.1)
        assertEquals(lon, location.lon,0.1)
    }

    @Test
    fun createValidTagFromDataClass() {
        val id = 1
        val name = "TagName"
        val state = "Active"

        val tag = Tag(id, name, state)

        assertEquals(id, tag.id)
        assertEquals(name, tag.name)
        assertEquals(state, tag.state)
    }
}