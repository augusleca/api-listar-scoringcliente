//package com.bcba.nosisensolnet.db.converters;
//
//import jakarta.persistence.AttributeConverter;
//import jakarta.persistence.Converter;
//
//@Converter
//public class Trimmer implements AttributeConverter<String, String> {
//
//    @Override
//    public String convertToDatabaseColumn(String attribute) {
//        return attribute; // No conversion needed when writing to the database.
//    }
//
//    @Override
//    public String convertToEntityAttribute(String dbData) {
//        return dbData != null ? dbData.trim() : null; // Trim the value when reading from the database.
//    }
//}