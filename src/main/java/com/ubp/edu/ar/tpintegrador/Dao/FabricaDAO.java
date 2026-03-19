package com.ubp.edu.ar.tpintegrador.Dao;

import java.lang.reflect.InvocationTargetException;

public  class FabricaDAO {

    //public abstract Dao getDao();

    public static Dao getFactory(String nombreClase) {
        try {
            return (Dao) Class.forName(Dao.class.getPackageName() + "." + nombreClase).getDeclaredConstructor().newInstance();
        } catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException | InstantiationException |
                 NoSuchMethodException | SecurityException |
                 InvocationTargetException e) {
            System.err.println(e);
            throw new IllegalArgumentException();
        }
    }
}