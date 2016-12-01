

public interface List<T> {
	/* * appends the specified value to the end of this list
	 * @param value the value to be appended
	 */
	public void add(T value);
	
	/**
	 * inserts the specified value at the specified position in this list
	 * @param index - index at which the specified value has to be inserted
	 * @param value - value to be inserted
	 * @throws IndexOutOfBoundsException if the index is out of range (<0 or >size())
	 */
	public void add(int index, T value);

	/**
	 * tests if the specified value is contained in this list
	 * @param value - value whose presence in this list is to be tested
	 * @return the value, if value is contained in this list;
	 *         null, otherwise
	 */
	public T contains(T value);
	
	/**
	 * returns the index of the first occurrence of the specified value in this list
	 * @param value - value to search for
	 * @return the index of the first occurrence of the specified value in this list, 
	 *          or -1, if this list does not contain the value
	 */
	public int indexOf (T value);
	
	/**
	 * removes the first occurrence of the specified value from this list
	 * @param value - the value to be removed from this list, if present
	 * @return the removed value, it it is contained in the list;
	 *        null, otherwise
	 */
	public T remove(T value);
	
	/**
	 * removes the value at the specified index in this list
	 * @param index - the index of the element to be removed
	 * @return the element previously at the specified position
	 * @throws IndexOutOfBoundsException if the index is out of range (<0 or >=size())
	 */
	public T remove(int index);
	
	/**
	 * returns the value at the specified position in this list
	 * @param index - the index of the value to be returned
	 * @return the value at the specified position in this list
	 * @throws IndexOutOfBoundsException if the index is out of range (<0 or >=size())
	 */
	public T get(int index);
	
	/**
	 * gives the number of elements in this list
	 * @return the number of values in this list
	 */
	public int size();

}
