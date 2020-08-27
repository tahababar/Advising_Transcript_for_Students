import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * The CourseHistory class reads a set of courses from the file finishedcourses.txt.  Each
 * course consists of a department, a course number, a credit amount, a competency (W, Q, S, 
 * or X), and a distribution area.
 * 
 * The class provides methods for searching for courses based on specific criterion.
 * 
 * @author Taha Babar and Ian Brundige 
 * @version December, 2019
 */
public class CourseHistory
{   private ArrayList <CompletedCourse> courseList;

    public CourseHistory()
    {   courseList = new ArrayList <CompletedCourse> ();

        String department;    //For example "CSC"
        String courseNumber;  //For example 121
        String semesterTaken; //For example 10708  or 20708
        String  credit;       //The number of credits the course is worth, for example 1.0, .5, .25 
        String grade;         //For example 4.0, 3.67, 3.33, 3.0, 2.67, 2.33, 2.0, 1.67, 1.33, 1.0, 0.0
        String competency;    //Can be 'W', 'Q', 'S', or 'X' (for none)
        String distArea;      //Can be AH, SS, SM, LA or NONE (for no distArea). 

        try
        {
            FileReader reader = new FileReader("finishedcourses.txt");
            Scanner in = new Scanner( reader );
            // Scanner in = new Scanner( file );

            while( in.hasNextLine() )   
            {   department = in.nextLine();  
                courseNumber = in.nextLine();
                semesterTaken = in.nextLine();
                credit = in.nextLine(); 
                grade = in.nextLine();
                competency = in.nextLine();
                distArea = in.nextLine();
                CompletedCourse theCourse = new CompletedCourse(department, courseNumber, 
                semesterTaken, credit, grade, competency, distArea);
                courseList.add(theCourse);  
            }
            in.close();  //Close the file when we are done reading it
        } catch (IOException exception)
        {
            System.out.println("Error processing file: " + exception);
        }   

    }

    /*
     * This method lists all of the courses in the ArrayList in the order they appear in
     * the input file (which is in the order the courses were taken).
     */
    public void displayCourseHistory()
    {   System.out.println("Course History");

        for (int i=0; i<courseList.size(); i++)
        {   courseList.get(i).displayCourse();
            System.out.println();
        }
    }

    /* 
     * This method displays a summary report based on the data in the ArrayList. The summary
     * reports the total number of credits earned and the total GPA. 
     */
    public void summaryReport()
    {   double sum1=0.0;
        double sum2=0.0;
        double sum3 = 0.0;
        double sum4 = 0.0;
        double GPA = 0.0;
        System.out.println("Summary Report");
        for(int i=0; i<courseList.size();i++)
        {   sum1 = sum1 + courseList.get(i).getCredit();
        }

        for (int i=0; i<courseList.size(); i++)
        {   if(courseList.get(i).getCredit() == 0.5)
            {   sum2 = sum2 + courseList.get(i).getGrade()/2;
            }
            else if (courseList.get(i).getCredit() == 0.25)
            {   sum3 = sum3 + courseList.get(i).getGrade()/4;
            }
            else
            {   sum4 = sum4 + courseList.get(i).getGrade();
            }
        }
        System.out.println("Total Credits =" + sum1);
        System.out.println("Total GPA  = " + (sum2+sum3+sum4)/courseList.size());
    }

    /*
     * The method displays a report that shows the user’s status toward meeting
     * distribution area requirements.
     */
    public void distributionAreaFulfillment()
    {   int ct1=0;
        int ct2=0;
        int ct3=0;
        int ct4=0;               
        System.out.println("Distribution Area");
        for (int i=0; i<courseList.size();i++)
        {   if(courseList.get(i).getDistArea().equals("AH") && courseList.get(i).getCredit() > 0.0)
            {   courseList.get(i).displayCourse();

                ct1++;
            }
        }
        System.out.println ("Total credits earned in AH =" + ct1);

        System.out.println();

        for (int i=0; i<courseList.size();i++)
        {   if(courseList.get(i).getDistArea().equals("SS") && courseList.get(i).getCredit() > 0.0)
            {   courseList.get(i).displayCourse();
               
                ct2++;
            }
        }
        System.out.println ("Total credits earned in SS =" + ct2);

        System.out.println();

        for (int i=0; i<courseList.size();i++)
        {   if(courseList.get(i).getDistArea().equals("LA") && courseList.get(i).getCredit() > 0.0)
            {   courseList.get(i).displayCourse();
              
                ct3++;
            }
        }
        System.out.println ("Total credits earned in LA =" + ct3);

        System.out.println();

        for (int i=0; i<courseList.size();i++)
        {   if(courseList.get(i).getDistArea().equals("SM") && courseList.get(i).getCredit() > 0.0)
            {   courseList.get(i).displayCourse();
               
                ct4++;
            }
        }
        System.out.println ("Total credits earned in SM ="  + ct4);

    }

    /*
     * The method displays a report that shows the user’s status toward meeting 
     * competency (W, Q, S) requirements.
     */
    public void CompetencyStatus()
    {   int ct1=0;
        int ct2=0;
        int ct3=0;
        System.out.println ("Competency Report");
        for(int i=0; i<courseList.size();i++)
        {   if (courseList.get(i).getCompetency().equals("W") && courseList.get(i).getGrade() > 0.0)
                {ct1++;
            }
            else if (courseList.get(i).getCompetency().equals("S") && courseList.get(i).getGrade() > 0.0)
                {ct2++;
            }
            else if (courseList.get(i).getCompetency().equals("Q") && courseList.get(i).getGrade() > 0.0)
                {ct3++;
            }
        }

        if (ct1 > 0 && ct2 >0 && ct3 > 0)
        {   System.out.println("All competencies completed.");
        }
        else if (ct1 == 0 && ct2 ==0 && ct3 == 0)
        {   System.out.println("No competencies completed.");
        }
        else if (ct1>0 && ct2==0 && ct3==0)
        {   System.out.println("Competencies Partially Completed.");
            System.out.println("W Completed");
            System.out.println("S Not Completed");
            System.out.println("Q Not Completed");
        } 
        else if (ct1==0 && ct2>0 && ct3==0)
        {   System.out.println("Competencies Partially Completed.");
            System.out.println("W Not Completed");
            System.out.println("S Completed");
            System.out.println("Q Not Completed");
        }
        else if (ct1==0 && ct2==0 && ct3>0)
        {   System.out.println("Competencies Partially Completed.");
            System.out.println("W Not Completed");
            System.out.println("S Not Completed");
            System.out.println("Q Completed");
        }
        else if (ct1>0 && ct2>0 && ct3==0)
        {   System.out.println("Competencies Partially Completed.");
            System.out.println("W Completed");
            System.out.println("S Completed");
            System.out.println("Q Not Completed");
        }
        else if (ct1==0 && ct2>0 && ct3>0)
        {   System.out.println("Competencies Partially Completed.");
            System.out.println("W Not Completed");
            System.out.println("S Completed");
            System.out.println("Q Completed");
        }
        else if (ct1>0 && ct2==0 && ct3>0)
        {   System.out.println("Competencies Partially Completed.");
            System.out.println("W Completed");
            System.out.println("S Not Completed");
            System.out.println("Q Completed");
        }          
    }

    /*
     * The method displays a full report that consists of the summary information of total credits, 
     * total GPA earned, distribution area fulfillment details, and competency status.
     */
    public void fullReport()
    {   System.out.println("Full Report");
        summaryReport();
        distributionAreaFulfillment();
        CompetencyStatus();
    }

    /*
     * The method displays a list of all the courses in the ArrayList, such that the courses are
     * sorted by GPA (from highest GPA to lowest GPA).
     */
    public void sort()
    {   CompletedCourse temp;
        int max;
        max = 0;
        for (int i = 0; i < courseList.size()-1; i++) 
        {   max = i;
            for (int j = i + 1; j < courseList.size(); j++) { 
                if (courseList.get(j).getGrade() > courseList.get(max).getGrade()) 
                {
                    max = j;  
                }
            }
            temp = courseList.get(max);
            courseList.set(max, courseList.get(i));
            courseList.set(i, temp);
        }
        displayCourseHistory();
    }
}

