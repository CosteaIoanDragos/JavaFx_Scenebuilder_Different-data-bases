package com.example.lab5.repository;
import com.example.lab5.domain.Patient;


import java.io.*;
import java.util.Vector;

public class BinaryFileRepository<Patient extends com.example.lab5.domain.Patient>extends  PatientRepo {
    private String filePath;


    private void readAllFromFile() {
        FileInputStream file = null;
        try {
            file = new FileInputStream(filePath);
            ObjectInputStream in = new ObjectInputStream(file);
            this.elements.clear();
            elements= (Vector<com.example.lab5.domain.Patient>) in.readObject();


            in.close();
            file.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (EOFException e) {

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }
    private void writeAllToFile() {

        FileOutputStream file = null;
        try {
            file = new FileOutputStream(this.filePath);

            ObjectOutputStream out = new ObjectOutputStream(file);

            out.writeObject(elements);

            out.close();
            file.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public BinaryFileRepository(String filePath) {
        super();
        this.filePath = filePath;
        this.readAllFromFile();
    }

    @Override
    public void add(com.example.lab5.domain.Patient el) {
        this.readAllFromFile();
        super.add(el);
        this.writeAllToFile();
    }

    @Override
    public com.example.lab5.domain.Patient searchById(Integer Id) {
        this.readAllFromFile();
        return super.searchById(Id);

    }

    @Override
    public void updateAtIndex(com.example.lab5.domain.Patient el, Integer index) {
        super.updateAtIndex(el, index);
        this.writeAllToFile();
    }

    @Override
    public void remove(com.example.lab5.domain.Patient el) {
        super.remove(el);
        this.writeAllToFile();
    }

    @Override
    public int size() {
        this.readAllFromFile();
        return super.size();
    }

    @Override
    public Vector<com.example.lab5.domain.Patient> getVector() {
        this.readAllFromFile();
        return super.getVector();
    }
}
