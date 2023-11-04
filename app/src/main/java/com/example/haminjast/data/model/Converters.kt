package com.example.haminjast.data.model

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class AddressListConverter {
    @TypeConverter
    fun fromAddressList(addressList: List<Address>?): String? {
        if (addressList == null) {
            return null
        }
        val gson = Gson()
        return gson.toJson(addressList)
    }

    @TypeConverter
    fun toAddressList(addressListJson: String?): List<Address>? {
        if (addressListJson == null) {
            return null
        }
        val gson = Gson()
        val type = object : TypeToken<List<Address>>() {}.type
        return gson.fromJson(addressListJson, type)
    }
}
class LocationConverter {
    @TypeConverter
    fun fromLocation(location: Location): String {
        // Convert the Location object to a JSON string
        val gson = Gson()
        return gson.toJson(location)
    }

    @TypeConverter
    fun toLocation(locationJson: String): Location {
        // Convert the JSON string back to a Location object
        val gson = Gson()
        return gson.fromJson(locationJson, Location::class.java)
    }
}

class TagListConverter {
    @TypeConverter
    fun fromTagList(tagList: List<Tag>?): String? {
        if (tagList == null) {
            return null
        }
        val gson = Gson()
        return gson.toJson(tagList)
    }

    @TypeConverter
    fun toTagList(tagListJson: String?): List<Tag>? {
        if (tagListJson == null) {
            return null
        }
        val gson = Gson()
        val type = object : TypeToken<List<Tag>>() {}.type
        return gson.fromJson(tagListJson, type)
    }
}

class TagConverter {
    @TypeConverter
    fun fromTag(tag: Tag): String {
        // Convert the Tag object to a JSON string
        val gson = Gson()
        return gson.toJson(tag)
    }

    @TypeConverter
    fun toTag(tagJson: String): Tag {
        // Convert the JSON string back to a Tag object
        val gson = Gson()
        return gson.fromJson(tagJson, Tag::class.java)
    }
}

class StringListConverter {
    @TypeConverter
    fun fromStringList(stringList: List<String>?): String? {
        if (stringList == null) {
            return null
        }
        val gson = Gson()
        return gson.toJson(stringList)
    }

    @TypeConverter
    fun toStringList(stringListJson: String?): List<String>? {
        if (stringListJson == null) {
            return null
        }
        val gson = Gson()
        val type = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(stringListJson, type)
    }
}