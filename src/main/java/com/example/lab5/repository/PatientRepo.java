package com.example.lab5.repository;

import com.example.lab5.domain.Patient;

import java.util.Vector;

public class PatientRepo extends IRepository<Patient>{
    public PatientRepo() {super();}

    @Override
    public String toString() {
        return elements.toString();
    }

    @Override
    public void add(Patient el) {
       elements.add(el);
        //System.out.println(elements);
    }

    @Override
    public Patient searchById(Integer Id) {
        for (int i=0;i< elements.size();i++){
            if(Id==elements.get(i).getId()){
                //System.out.println(elements.get(i));
                return elements.get(i);

            }
        }
        return null;
    }

    @Override
    public void updateAtIndex(Patient el,Integer index) {
        for(int i=0;i<=elements.size();i++){
            if(i==index){
                elements.remove(i);
                elements.add(el);
            }
        }

    }
    public Integer getIndex(Patient el){
        return elements.indexOf(el);
    }
    @Override
    public void remove(Patient el) {
    elements.remove(el);
    System.out.println("Patietn repo:");
        System.out.println(el);

    }
    public void getElemByYearMonth(int month,int year){



        for(int i=0;i<elements.size();i++){
            if(elements.get(i).getDate().getMonth()==month&&elements.get(i).getDate().getYear()==year){
                System.out.println(elements.get(i));
            }
        }
    }
    public int getNumberOfElemByYearMonth(int month,int year){int z=0;
        for(int i=0;i<elements.size();i++){
            if(elements.get(i).getDate().getMonth()==month&&elements.get(i).getDate().getYear()==year) {
                z++;
            }
    }
       // System.out.println(z);
        return z;}
    public void printOne(int index){
        System.out.println(elements.get(index));

    }
    public Vector<Patient> getVector(){
        return (Vector<Patient>) elements.clone();
    }
}
