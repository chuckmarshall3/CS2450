/********************************************************
 *
 *  Project :  AcmeNote
 *  File    :  AcmeNote.java
 *  Name    :  Matthew Harker
 *  Date    :  2014.03.24
 *
 *  Description : The AcmeNote class contains an array list of course objects. The class contains the graphical user interface and associated methods.
 *
 *  Changes :  2014.03.27 by Shaun Christensen. Updated serialization methods to display a pop-up message upon error rather than print to the console. Added javadoc comments, cleaned up formatting of source code, and added header comment.
 *             2014.03.30 by Shaun Christensen. Added method stubs for graphical user interface.
 *             2014.03.31 by Shaun Christensen. Implemented graphical user interface methods to create and launch application. Added temporary image to default null panel, then added search panel and default null panel to frame.
 *             2014.04.01 by Shaun Christensen. Completed initial graphical user interface.
 *             2014.04.02 by Shaun Christensen. Extracted graphical user interface, event listeners, and utility methods into new class AcmeNoteGraphicalUserInterface.
 *
 ********************************************************/

package acmeNote;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * <tt>AcmeNote</tt> class contains an <tt>ArrayList</tt> of <tt>Course</tt> objects and serialization methods.
 * 
 * @author Matthew Harker, Shaun Christensen
 */
public class AcmeNote
{
	// fields

	/**
	 * <tt>ArrayList</tt> object containing <tt>Course</tt> objects.
	 */
	private ArrayList<Course> courses;
	
	// constructors
	
	/**
	 * No argument default constructor.
	 */
	public AcmeNote()
	{
		deserialize();
	}

	// accessor methods

	/**
	 * Return courses.
	 * 
	 * @return <tt>ArrayList</tt> object containing <tt>Course</tt> objects.
	 */
	public ArrayList<Course> getCourses()
	{
		return courses;
	}

	/**
	 * Return course object.
	 * 
	 * @param index <tt>Integer</tt> value of <tt>Course</tt> object within the <tt>ArrayList</tt> of sections.  
	 * @return <tt>Course</tt> object.
	 */
	public Course getCourse(int index)
	{
		return getCourses().get(index);
	}

	// mutator methods

	/**
	 * Set <tt>ArrayList</tt> of <tt>Course</tt> objects.
	 * 
	 * @param courses <tt>ArrayList</tt> of <tt>Course</tt> objects.
	 * @return <tt>Boolean</tt> value of set operation.
	 */
	public boolean setCourses(ArrayList<Course> courses)
	{
		this.courses = courses;

		return true;
	}

	/**
	 * Add <tt>Course</tt> object to <tt>ArrayList</tt>.
	 * 
	 * @param course <tt>Course</tt> object.
	 * @return <tt>Boolean</tt> value of set operation.
	 */
	public boolean addCourse(Course course)
	{
		getCourses().add(course);

		return true;
	}

	/**
	 * Remove <tt>Course</tt> object from <tt>ArrayList</tt>.
	 * 
	 * @param index <tt>Integer</tt> value of <tt>Course</tt> object in <tt>ArrayList</tt>.
	 * @return <tt>Boolean</tt> value of set operation.
	 */
	public boolean removeCourse(int index)
	{
		try
		{
			getCourses().remove(index);
		}
		catch (IndexOutOfBoundsException e)
		{
			return false;
		}

		return true;
	}

	/**
	 * Set <tt>Course</tt> object in <tt>ArrayList</tt>.
	 * 
	 * @param index <tt>Integer</tt> value of <tt>Course</tt> object in <tt>ArrayList</tt>.
	 * @param course <tt>Course</tt> object to set in <tt>ArrayList</tt>.
	 * @return <tt>Boolean</tt> value of set operation.
	 */
	public boolean setCourse(int index, Course course)
	{
		try
		{
			getCourses().set(index, course);
		}
		catch (IndexOutOfBoundsException e)
		{
			return false;
		}

		return true;
	}

	// serialization methods

	/**
	 * Reads <tt>ArrayList</tt> of courses, sections, and notes from disk.
	 */
	@SuppressWarnings("unchecked")
	public void deserialize()
	{
		try
		{
			ObjectInputStream input = null;

			File file = new File("AcmeNote.ser");

			if (file.isFile() && file.canRead())
			{
				input = new ObjectInputStream(new FileInputStream("AcmeNote.ser"));
			}

			if (input != null)
			{
				courses = (ArrayList<Course>)input.readObject();

				input.close();
			}

			if (courses == null)
			{
				courses = new ArrayList<Course>();
			}
		}
		catch (ClassNotFoundException e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage(), "Class Not Found Exception", JOptionPane.ERROR_MESSAGE);
		}
		catch (NotSerializableException e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage(), "Not Serializable Exception", JOptionPane.ERROR_MESSAGE);
		}
		catch (IOException e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage(), "Input/Output Exception", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Writes <tt>ArrayList</tt> of courses, sections, and notes to disk.
	 */
	public void serialize()
	{
		try
		{
			ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("AcmeNote.ser"));

			if (output != null)
			{
				output.writeObject(courses);
				output.flush();
				output.close();
			}
		}
		catch (NotSerializableException e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage(), "Not Serializable Exception", JOptionPane.ERROR_MESSAGE);
		}
		catch (IOException e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage(), "Input/Output Exception", JOptionPane.ERROR_MESSAGE);
		}
	}
}