/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Haja
 */
public class DateTimeSerializing extends TypeAdapter<DateTime> implements JsonSerializer<DateTime>{
    private final SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    @Override
    public void write(JsonWriter writer, DateTime t) throws IOException {
        if(t != null)
            writer.value(t.globalFormat());
        else
            writer.nullValue();
    }

    @Override
    public DateTime read(JsonReader reader) throws IOException {
        String str = reader.nextString();
        if(str == null)
            return null;
        try {
            return new DateTime(format.parse(str.replace("-", "/")).getTime());
        } catch (ParseException ex) {
        }
        return null;
    }

    @Override
    public JsonElement serialize(DateTime t, Type type, JsonSerializationContext jsc) {
        return jsc.serialize((Date)t);
    }
}