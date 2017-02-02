/********************************************************	
 * File:			CQuickSort.java						*
 * Description:		Java class for running quick sort	*
 * Author:			Eric Torman (etorman@hotmail.com)	*
 * Date created:	July 20, 1999 (ET)					*
 * ******************************************************
 */

// Include utility header file (contains Java's Vector class)
import java.util.*;
 
// CQuickSort class is derived from CSortAlgorithm class
class CQuickSort extends CSortAlgorithm 
	
	* Function:		CQuickSort() -- Constructor			*
	* Description:	Simple constructor function.		*
	*				Initializes variables of this and	*
	*				derived (CSortAlgorithm) classes.	*
	*													*
	* Input:		Sorting applet object				*
	*				(SortApplet parent)					*
	* Output:		NONE								*
	* Return value:	NONE								*
	* Side effects:	Note that in Java there is no		*
	*				destructor. You can override		*
	*				protected void finalize() function,	*
	*				and it'll get called by Java's		*
	*				garbage	collector					*	
	* ***************************************************
	*/
	public CQuickSort(SortApplet parent)
	{
		SetApplet(parent);
		// Build quick sort code
	    
	/****************************************************	
	* Function:		OnSort()							*
	* Description:	Function responsible for running	*
	*				actual sorting algorithm			*
	* 													*
	* Input:		Array of numbers to sort			*
	*				(int arr[])							*
	* Output:		NONE								*
	* Return value:	NONE								*
	* Side effects:	Replaces the unsorted array by		*
	*				sorted array						*
	* ***************************************************
	*/
	protected void OnSort(int a[])
		if (m_showAnimation)
			m_sortAnimator.ChangeColor(0, a.length - 1, m_sortAnimator.SORTED_COLOR);  
	* Function:		QuickSortSort()						*
	* Description:	Function that actually performs the *
	*				quick sort							*
	* 													*
	* Input:		Array of numbers to sort			*
	*				(int arr[])							*
	*				Left index  (int lo0)				*
	*				Right index (int ho0)				*
	* Output:		NONE								*
	* Return value:	NONE								*
	* Side effects:	The function is recursive			*
	*				See any Data Structures book for	*
	*				explanation of QuickSort algorithm	*
	*				NOTE: This implementation of quick	*
	*				sort uses middle point as pivot.	*
	*****************************************************
	*/
	private void QuickSort(int a[], int lo0, int hi0)
	{
		// Variables of quick sort
		int hi = hi0;
		int mid;
	
		// Select line from the debug window
		SelectLine(3);
		// Quick sort code
		if (lo0 >= hi0) 
		SelectLine(4);
		// Establish pivot in the middle

		// Select line from the debug window
		
		while( lo <= hi ) 
			// Select line from the debug window
			
			// the partition element starting from the left Index.
			while( a[lo] < mid ) 
			{
				lo++;

			// Select 'while (a[hi] > mid)' line
			SelectLine(8);
			while( a[hi] > mid ) 
			{
				// Select '--hi' line

			// Select 'if (lo <= hi)' line

			// Compare a[lo] with a[hi] 
				
			// Compare() function also animates comparisons 
			// process (if necessary), updates number of 
			// comparisons and updates the text box with
			// new number of comparisons (if necessary)
			Compare(a, lo, hi);
			if( lo <= hi ) {
				// Select 'Swap(a[lo], a[hi])' line
				// Finally, swap
				Swap(a, lo, hi, true);
				// Select '++lo' line
			}
		}

		// Select 'QuickSort(a, lo0, hi)' line
		QuickSort( a, lo0, hi );
		// Change color of cells to sorted
			m_sortAnimator.ChangeColor(lo0, hi, m_sortAnimator.SORTED_COLOR);  
		// Select 'QuickSort(a, lo, hi0)' line
		
		QuickSort( a, lo, hi0 );
		if (m_showAnimation)
			m_sortAnimator.ChangeColor(lo, hi0, m_sortAnimator.SORTED_COLOR);  
		
	/****************************************************	
	* Function:		BuildCode()							*
	* Description:	Builds Java's vector object, which	*
	*				will be used for loading quick sort	*
	* 				to debug window						*
	* 													*
	* Input:		NONE								*
	* Output:		NONE								*
	* Return value:	NONE								*
	* Side effects:	NONE								*
	* ***************************************************
	*/
	private void BuildCode()
	{
		// Load, line by line, quick sort code to 
		// our Vector (code) object
		code.addElement("{");
		code.addElement("   int lo = lo0, hi = hi0;");
		code.addElement("   if ( lo0 >= hi0) return;");
        code.addElement("         lo++; hi--;");
		code.addElement("      }");
        code.addElement("   QuickSort(a, lo0, hi);");
        code.addElement("   QuickSort(a, lo, hi0);");
		code.addElement("}");			
	}
	
	/****************************************************	
	* Function:		loadDebugCode()						*
	* Description:	Loads our quick sort code to		*
	*				debug's window list box				*
	* 													*
	* Input:		NONE								*
	* Output:		NONE								*
	* Return value:	NONE								*
	* Side effects:	NONE								*
	* ***************************************************
	*/
	public void loadDebugCode()
	{
		// Clear debug's window list box
		m_debugWindow.ClearList();		
		// Add a string to the debug's window list box
		for (int i = 0; i < code.size(); i++)
			m_debugWindow.AddLine((String)(code.elementAt(i))); 
	}	