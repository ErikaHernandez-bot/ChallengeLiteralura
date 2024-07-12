package com.eihl.alurafinal.consumoapi;

public interface IConvertirDatos {
    <T> T getDatos(String json, Class<T> tClass);
}
