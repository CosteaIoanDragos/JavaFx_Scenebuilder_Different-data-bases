package com.example.lab5.repository;
import com.example.lab5.domain.Entity;

import java.util.Vector;


public abstract class IRepository<TElem extends Entity> {
    protected Vector<TElem> elements=new Vector<TElem>();

    public IRepository() {
        this.elements = new Vector<TElem>();
    }

    public abstract void add(TElem el) ;
    public abstract TElem searchById(Integer elId) ;
    public abstract void updateAtIndex(TElem el,Integer index) ;
    public abstract void remove (TElem el) ;
    public int size(){return this.elements.size();}


}
