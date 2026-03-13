
package com.klef.fsad.exam;

import java.util.Scanner;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ClientDemo 
{
    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);

        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");

        SessionFactory sf = cfg.buildSessionFactory();
        Session session = sf.openSession();

        session.beginTransaction();

        System.out.println("------ INSERT SUPPLIER ------");

        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Description: ");
        String description = sc.nextLine();

        System.out.print("Enter Date: ");
        String date = sc.nextLine();

        System.out.print("Enter Status: ");
        String status = sc.nextLine();

        Supplier s = new Supplier();
        s.setName(name);
        s.setDescription(description);
        s.setDate(date);
        s.setStatus(status);

        session.save(s);

        System.out.println("Supplier Inserted Successfully");

        session.getTransaction().commit();

        session.beginTransaction();

        System.out.println("\n------ UPDATE SUPPLIER ------");

        System.out.print("Enter Supplier ID to update: ");
        int id = sc.nextInt();
        sc.nextLine();

        Supplier s1 = session.get(Supplier.class, id);

        if(s1 != null)
        {
            System.out.print("Enter New Name: ");
            String newname = sc.nextLine();

            System.out.print("Enter New Status: ");
            String newstatus = sc.nextLine();

            s1.setName(newname);
            s1.setStatus(newstatus);

            session.update(s1);

            System.out.println("Supplier Updated Successfully");
        }
        else
        {
            System.out.println("Supplier Not Found");
        }

        session.getTransaction().commit();

        session.close();
        sf.close();
    }
}

