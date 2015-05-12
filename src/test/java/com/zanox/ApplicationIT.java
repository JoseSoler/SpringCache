package com.zanox;

import etm.core.configuration.BasicEtmConfigurator;
import etm.core.configuration.EtmManager;
import etm.core.monitor.EtmMonitor;
import etm.core.monitor.EtmPoint;
import etm.core.renderer.SimpleTextRenderer;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by jsoler on 11.05.15.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class ApplicationIT {

    @Autowired
    EmployeesRepository employeesRepository;

    private static EtmMonitor etmMonitor;

    @BeforeClass
    public static void setUp(){
        BasicEtmConfigurator.configure();
        etmMonitor = EtmManager.getEtmMonitor();
        etmMonitor.start();
    }

    @Test
    public void shouldRetrieveFourTimesSameEmployee(){
        EtmPoint startPoint = etmMonitor.createPoint("Total Time of retrieving same Employee 4 Times");

        System.out.println(employeesRepository.loadEmployee(2));
        employeesRepository.saveEmployee(new Employee(2, "Another", "Guy", "Another Strasse", 10));
        System.out.println(employeesRepository.loadEmployee(2));
        System.out.println(employeesRepository.loadEmployee(2));
        System.out.println(employeesRepository.loadEmployee(2));


        startPoint.collect();
        etmMonitor.render(new SimpleTextRenderer());
    }

    @Test
    public void shouldRetrieveFourDifferentEmployees(){
        EtmPoint startPoint = etmMonitor.createPoint("Total Time of retrieving 4 different Employees");

        System.out.println(employeesRepository.loadEmployee(1));
        System.out.println(employeesRepository.loadEmployee(2));
        System.out.println(employeesRepository.loadEmployee(3));
        System.out.println(employeesRepository.loadEmployee(4));

        startPoint.collect();
        etmMonitor.render(new SimpleTextRenderer());
    }

    @Test
    public void shouldRetrieveTwoDifferentEmployeesInFourRoundTrips(){
        EtmPoint startPoint = etmMonitor.createPoint("Total Time of retrieving 2 different Employees");

        System.out.println(employeesRepository.loadEmployee(1));
        System.out.println(employeesRepository.loadEmployee(2));
        System.out.println(employeesRepository.loadEmployee(2));
        System.out.println(employeesRepository.loadEmployee(1));

        startPoint.collect();
        etmMonitor.render(new SimpleTextRenderer());
    }

    @Test
    public void shouldNotPerformBecauseLivelinessOfData(){
        EtmPoint startPoint = etmMonitor.createPoint("Total Time of retrieving 4 different Employees");

        System.out.println(employeesRepository.loadEmployee(1));
        employeesRepository.saveEmployee(new Employee(1, "Another", "Guy", "Another Strasse", 10));
        System.out.println(employeesRepository.loadEmployee(1));
        employeesRepository.saveEmployee(new Employee(1, "Another", "Guy2", "Another Strasse", 11));
        System.out.println(employeesRepository.loadEmployee(1));
        employeesRepository.saveEmployee(new Employee(1, "Another", "Guy3", "Another Strasse", 12));
        System.out.println(employeesRepository.loadEmployee(1));

        startPoint.collect();
        etmMonitor.render(new SimpleTextRenderer());
    }

}
