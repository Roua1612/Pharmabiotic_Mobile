
package com.esprit.services;

import java.util.List;


public interface IService<T> {

    public boolean ajouter(T t);

    public boolean modifier(T t);

    boolean supprimer(T t);

    public List<T> afficher();

}
