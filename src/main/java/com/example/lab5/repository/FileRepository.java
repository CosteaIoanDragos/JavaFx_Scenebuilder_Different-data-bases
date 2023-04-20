package com.example.lab5.repository;

import com.example.lab5.domain.Patient;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

 public class FileRepository<Patient>extends  PatientRepo {
    private String filePath;
     com.example.lab5.domain.Patient readFromParts(String[] parts){
         String name=parts[0];

         String phoneNr=parts[1];
         Integer id =Integer.parseInt(parts[2]);
         Integer year=Integer.parseInt(parts[3]);
         Integer month=Integer.parseInt(parts[4]);
         Integer day=Integer.parseInt(parts[5]);
         Integer hour=Integer.parseInt(parts[6]);
         Integer minute=Integer.parseInt(parts[7]);



         return new com.example.lab5.domain.Patient(name,phoneNr,id,year,month,day,hour,minute);

     }

     String writeToParts(com.example.lab5.domain.Patient e) {
         String parts=e.toString();
         return parts;
     }

     private void readAllFromFile() {
        File file = new File(filePath);
        try {
            Scanner reader = new Scanner(file);
            elements.clear();
            while (reader.hasNextLine()){
                String line = reader.nextLine();
                line = line.strip();
                if (!line.equals("")){
                    String[] parts = line.split(" ");
                    com.example.lab5.domain.Patient p=this.readFromParts(parts);
                    elements.add(p);
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
     private void writeAllToFile() {
         try {
             FileWriter writer = new FileWriter(filePath);
             for(com.example.lab5.domain.Patient elem : this.getVector()){
                 String parts = this.writeToParts(elem);
                 writer.write(parts +"\n");
             }
             writer.close();
         } catch (IOException e) {
             throw new RuntimeException(e);
         }
     }

     public FileRepository(String filePath) {
         super();
         this.filePath = filePath;
         this.readAllFromFile();
     }

     @Override
     public void add(com.example.lab5.domain.Patient el) {
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
 }
