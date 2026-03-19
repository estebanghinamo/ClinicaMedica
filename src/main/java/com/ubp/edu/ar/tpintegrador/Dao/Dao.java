package com.ubp.edu.ar.tpintegrador.Dao;
import java.util.List;

/**
 *
 * @author agustin
 * @param <T>
 */
public interface Dao<T> {

    T buscar(T dto);

    List<T> listarPorCriterio(T dto);

    List<T> listarTodos();

    boolean insertar(T dto);

    boolean modificar(T dto);

    boolean borrar(T dto);

}
