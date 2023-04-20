package com.example.lab5.service;

import com.example.lab5.domain.Patient;
import com.example.lab5.repository.PatientRepo;

import java.time.Year;
import java.util.*;
import java.util.stream.Collectors;

public class PatientService {
    private PatientRepo appointment;




    public PatientService(PatientRepo repo) {
        this.appointment=repo;
    }
    public void AddAppointmentNoExtraInfo(Patient p){
        appointment.add(p);
    }
    public void AddAppointment(String name, String phonenumber, Integer id, int year, int month, int day, int hour, int minute){
        Date Date1=new Date();
        Date1.setYear(year);
        Date1.setMonth(month);
        Date1.setHours(hour);
        Date1.setDate(day);
        Date1.setMinutes(minute);
        Patient p=new Patient(name,phonenumber,id,Date1);
        appointment.add(p);
    }
    public void seeAll(){
        if (appointment==null) {System.out.println("It Empty!");}
        else{
        for(int i=0;i<appointment.size();i++){
            appointment.printOne(i);
        }}
    }
    public void CancelAppointment(int id){
        try {
            Patient p = appointment.searchById(id);
            System.out.println(p);
            appointment.remove(p);
            System.out.println("");
        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Exception thrown");
        }

    }
    public void ChangeAppointment(int id,String name,String phonenumber,Date Date1){
        Patient p=appointment.searchById(id);
        Patient nou=new Patient(name,phonenumber,id,Date1);
        appointment.remove(p);
        appointment.add(nou);



    }
    public  void AppointmentsMonth(){
        Scanner keyboard = new Scanner(System.in);
        System.out.println("What:-year:\n");
        int year=keyboard.nextInt();
        System.out.println(":What-month:\n");

        int month=keyboard.nextInt();
        appointment.getElemByYearMonth(month,year);

    }
    public  void NrOfAppointmentsMonth(){
        Scanner keyboard = new Scanner(System.in);
        System.out.println("What:-year:\n");
        int year=keyboard.nextInt();
        System.out.println(":What-month:\n");
        int month=keyboard.nextInt();
        System.out.println(appointment.getNumberOfElemByYearMonth(month,year));
        System.out.println(" appointment in that month");
    }
    public Vector<Patient> getAll(){
        return appointment.getVector();
    }
    public  String raport1(Integer nr1){
        String all = "Number of appoinments in year " + nr1 + " :";
        //Scanner key=new Scanner(System.in);
        //System.out.println("Number of appoinments in year "+ nr1+" :");
        //Integer nr1=key.nextInt();
        //System.out.println(appointment.getVector().stream().filter(patient -> patient.getDate().getYear() == nr1).count());
        return all.concat(String.valueOf(appointment.getVector().stream().filter(patient -> patient.getDate().getYear() == nr1).count()));
    }
    public String raport2(Integer nr1){
        String all = "Appointments of id "+nr1+" :";
        //Scanner key=new Scanner(System.in);
        //System.out.println("Appointments of id "+nr1+" :") ;
        //Integer nr1=key.nextInt();
        //appointment.getVector().stream().filter(patient -> patient.getId()==nr1).forEach(System.out::println);
        String newString= appointment.getVector().stream().filter(patient -> patient.getId()==nr1).map(i->String.valueOf(i.toString())).collect(Collectors.joining("\n"));
        String last=all+newString;
        //System.out.println(last);

        return last;
        }
    public String raport3(){
        String all ="List of appointments ordered:";
        //System.out.println("List of appointments ordered:");
        String newString=appointment.getVector().stream().sorted(Comparator.comparing(Patient::getDate)).map(i->String.valueOf(i.toString())).collect(Collectors.joining("\n"));
        String last=all+newString;
        return last;
    }
    public String raport4(){
        String all ="Appointment not in the current year: ";
        //System.out.println("Appointment with wrong year");
        String newString=appointment.getVector().stream().filter(patient -> patient.getDate().getYear()!= Year.now().getValue()).map(i->String.valueOf(i.toString())).collect(Collectors.joining("\n"));
        String last=all+newString;
        return last;
    }
    public String raport5(){
        String all ="Patients with wrong number:";
        //System.out.println("Patients with wrong number:");
        String newString=appointment.getVector().stream().filter(patient -> patient.getPhonenumber().length()<9).map(i->String.valueOf(i.toString())).collect(Collectors.joining("\n"));
        String last=all+newString;
        return last;
    }
}
