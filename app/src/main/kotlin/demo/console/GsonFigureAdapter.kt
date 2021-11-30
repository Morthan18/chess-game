package demo.console

import com.google.gson.*
import demo.domain.Figure
import java.lang.reflect.Type

class GsonFigureAdapter : JsonSerializer<Figure?>,
    JsonDeserializer<Figure?> {
   
    override fun serialize(src: Figure?, typeOfSrc: Type?, context: JsonSerializationContext?): JsonElement {
        val result = JsonObject()
        result.add("type", JsonPrimitive(src!!.javaClass.simpleName))
        result.add("properties", context!!.serialize(src, src.javaClass))
        return result
    }

    @Throws(JsonParseException::class)
    override fun deserialize(json: JsonElement, typeOfT: Type?, context: JsonDeserializationContext): Figure {
        val jsonObject = json.asJsonObject
        val type = jsonObject["type"].asString
        val element = jsonObject["properties"]
        return try {
            context.deserialize(element, Class.forName("demo.domain.$type"))
        } catch (cnfe: ClassNotFoundException) {
            throw JsonParseException("Unknown element type: $type", cnfe)
        }
    }
   
}